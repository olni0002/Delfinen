import java.io.*;
import java.util.*;


public class SwimManager {
    public static void main(String[] args) {

        new SwimManager().run();

    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please choose one of the following options:");
            System.out.println("1. Register training result");
            System.out.println("2. Print top 5 times");
            System.out.println("3. Exit the program");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerTime();
                    break;
                case 2:

                    System.out.print("Please tell me the discipline to print top 5 times: ");
                    String disciplinToPrint = scanner.nextLine();
                    printTopFiveTimes(disciplinToPrint + ".txt");
                    break;
                case 3:
                    System.out.println("Exiting program");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input, please choose one of the options listed above");
                    break;
            }
        }
    }

    private static void saveSwimActivityToFile(SwimActivity swimActivity, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(swimActivity.toString());
            writer.newLine();
            System.out.println("Swim-activity saved in:  " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void registerTime() {
        Scanner scanner1 = new Scanner(System.in);
        System.out.print("Please input the swimmers name: ");
        String navn = scanner1.nextLine();

        System.out.print("please tell me the time-result of the practice: (Format example: (1 Minute and 20 seconds) ");
        String tid = scanner1.nextLine();

        System.out.print("Please tell me the date the time was swam on: ");
        String dato = scanner1.nextLine();

        // Opret en SwimActivity-objekt
        SwimActivity swimActivity = new SwimActivity(navn, tid, dato);

        // Vælg hvilken disciplin der er svømmet (f.eks. "Butterfly")
        System.out.print("Please tell me the discipline that was swam: ");
        String disciplin = scanner1.nextLine();

        // Vælg hvilken fil du vil gemme i baseret på disciplinen
        String fileName = disciplin + ".txt";

        // Gem svømmeaktiviteten i den specifikke fil
        saveSwimActivityToFile(swimActivity, fileName);

    }

    public static void printTopFiveTimes(String fileName) {
        try (Scanner activityScanner = new Scanner(new File(fileName))) {
            List<String> times = new ArrayList<>();

            while (activityScanner.hasNextLine()) {
                times.add(activityScanner.nextLine());
            }

            // Sort the times in ascending order
            Collections.sort(times, Comparator.comparingInt(SwimManager::parseTime));

            // Print top 5 times in ascending order
            System.out.println("Top 5 times for " + fileName + ":");
            for (int i = 0; i < Math.min(5, times.size()); i++) {
                System.out.println(times.get(i));
            }
            System.out.println("--------------");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }

    // Helper method to parse time in seconds (assumes "Minute and seconds" format)
    private static int parseTime(String activity) {
        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("Time: (\\d+) Minute(?:s)? and (\\d+) second(?:s)?").matcher(activity);

        if (matcher.find()) {
            int minutes = Integer.parseInt(matcher.group(1));
            int seconds = Integer.parseInt(matcher.group(2));
            return minutes * 60 + seconds;
        } else {
            // Handle the case where the format is not as expected
            System.err.println("Error parsing time: " + activity);
            return Integer.MAX_VALUE; // Set to a large value if parsing fails
        }
    }
}
