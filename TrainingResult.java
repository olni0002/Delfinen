package DelfinKlubben;

import java.util.List;
import java.util.Scanner;

public class TrainingResult {

    private String discipline;
    private int bestTime;
    private String date;

    public TrainingResult(String discipline, int bestTime, String date) {
        this.discipline = discipline;
        this.bestTime = bestTime;
        this.date = date;
    }

    public String getDiscipline() {
        return discipline;
    }

    public int getBestTime() {
        return bestTime;
    }

    public String getDate() {
        return date;
    }

    public void updateResult(int newBestTime, String newDate) {
        this.bestTime = newBestTime;
        this.date = newDate;
    }

    public static void registerTrainingResults(Scanner scanner, List<Swimmer> swimmers) {
        System.out.println("Please write the swimmer's name: ");
        String swimmersName = scanner.nextLine();

        Swimmer swimmer = findOrCreateSwimmer(swimmersName, swimmers);

        System.out.println("Please choose a discipline (Freestyle, Butterfly, Crawl, Backstroke, Breaststroke):");
        String discipline = scanner.nextLine();

        System.out.println("Please input the swimmer's best training time:");
        int bestTime = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.println("Please choose the date the time was set on:");
        String date = scanner.nextLine();

        swimmer.addTrainingResult(discipline, bestTime, date);

        System.out.println("Training Time has now been registered, NICE JOB!");
    }

    public static void updateTrainingResult(Scanner scanner, List<Swimmer> swimmers) {
        System.out.println("Please write the swimmer's name:");
        String swimmersName = scanner.nextLine();

        Swimmer swimmer = findSwimmer(swimmersName, swimmers);

        if (swimmer != null) {
            System.out.println("Please choose discipline (Freestyle, Butterfly, Crawl, Backstroke, Breaststroke)");
            String discipline = scanner.nextLine();

            TrainingResult trainingResult = findTrainingResult(discipline, swimmer.getTrainingResults());

            if (trainingResult != null) {
                System.out.println("Please tell me the new best training time:");
                int bestTime = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Please tell me the date for the new best training time:");
                String date = scanner.nextLine();

                trainingResult.updateResult(bestTime, date);
                System.out.println("Training result updated NICE JOB!");
            } else {
                System.out.println("The chosen swimmer has not yet set a time in this Discipline.");
            }
        } else {
            System.out.println("Swimmer was not found!.");
        }
    }

    private static Swimmer findOrCreateSwimmer(String swimmersName, List<Swimmer> swimmers) {
        for (Swimmer swimmer : swimmers) {
            if (swimmer.getName().equals(swimmersName)) {
                return swimmer;
            }
        }

        // If the swimmer is not found, add a new one
        Swimmer newSwimmer = new Swimmer(swimmersName, "", "");
        swimmers.add(newSwimmer);
        return newSwimmer;
    }

    private static Swimmer findSwimmer(String name, List<Swimmer> swimmers) {
        for (Swimmer swimmer : swimmers) {
            if (swimmer.getName().equals(name)) {
                return swimmer;
            }
        }
        return null;
    }

    private static TrainingResult findTrainingResult(String discipline, List<TrainingResult> trainingResults) {
        for (TrainingResult result : trainingResults) {
            if (result.getDiscipline().equalsIgnoreCase(discipline)) {
                return result;
            }
        }
        return null;
    }
}