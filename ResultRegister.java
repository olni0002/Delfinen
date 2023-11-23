package DelfinKlubben;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ResultRegister {

    private List<Swimmer> swimmers = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ResultRegister resultRegister = new ResultRegister();
        resultRegister.run();
    }

    private void run() {
        while (true) {
            System.out.println("Please choose an option:");
            System.out.println("1. Register training result");
            System.out.println("2. Update training result");
            System.out.println("3. Print top 5 times");
            System.out.println("4. Exit program");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    TrainingResult.registerTrainingResults(scanner, swimmers);
                    break;
                case 2:
                    TrainingResult.updateTrainingResult(scanner, swimmers);
                    break;
                case 3:
                    printTop5Times();
                    break;

                case 4:
                    System.out.println("Exiting program");
                    System.exit(0);
                default:
                    System.out.println("Invalid input, please choose another action");
            }
        }
    }

    private void printTop5Times() {
        for (Swimmer swimmer : swimmers) {
            System.out.println("Swimmer: " + swimmer.getName());
            List<TrainingResult> top5Times = swimmer.getTop5Times();
            if (!top5Times.isEmpty()) {
                System.out.println("Top 5 Times:");
                for (TrainingResult result : top5Times) {
                    System.out.println("Discipline: " + result.getDiscipline() +
                            ", Time: " + result.getBestTime() +
                            ", Date: " + result.getDate());
                }
            } else {
                System.out.println("No top times available for this swimmer.");
            }
            System.out.println("-------------------------");
        }
    }
}