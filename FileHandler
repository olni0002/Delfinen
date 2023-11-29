import java.io.*;
import java.util.*;

public class FileHandler {
    public static void main(String[] args) {
        try {
            File nameFile = new File("TopFive.txt");

            Scanner scanner = new Scanner(nameFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");

                if (parts.length >= 1) {
                    String name = parts[0];

                    // Udskriv navnet og søg efter den tilsvarende disciplin-fil
                    System.out.println(name);
                    String disciplinFileName = name + ".txt";

                    // Udskriv de fem bedste tider fra disciplin-filen
                    printTopFiveTimes(disciplinFileName);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }

    private static void printTopFiveTimes(String fileName) {
        try (Scanner activityScanner = new Scanner(new File(fileName))) {
            List<SwimActivity> activities = new ArrayList<>();

            while (activityScanner.hasNextLine()) {
                String line = activityScanner.nextLine();
                String[] parts = line.split(" ");
                String name = parts[1];
                String time = parts[4];
                String date = parts[7];

                SwimActivity swimActivity = new SwimActivity(name, time, date);
                activities.add(swimActivity);
            }

            // Sorter SwimActivity-objekterne i faldende rækkefølge baseret på tid
            activities.sort(Collections.reverseOrder());

            // Udskriv de fem hurtigste tider
            System.out.println("Top 5 tider for " + fileName + ":");
            for (int i = 0; i < Math.min(5, activities.size()); i++) {
                System.out.println(activities.get(i));
            }
            System.out.println("--------------");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }
}
