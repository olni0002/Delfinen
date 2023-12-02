import java.util.Scanner;
import java.io.File;
import java.time.LocalDate;

public class CheckUserInput {
    public static boolean nameExists(String name) {
        try {
            var fileScanner = new Scanner(new File("Members.csv"));
            while (fileScanner.hasNextLine()) {
                String nextLine = fileScanner.nextLine();
                if (name.equalsIgnoreCase(nextLine.substring(0, nextLine.indexOf(";")))) {
                    fileScanner.close();
                    return true;
                }
            }
            fileScanner.close();
        } catch (Exception e) {}
        return false;
    }

    public static boolean isDate(String birthDate) {
        try {
            LocalDate.parse(birthDate);
            return true;
        } catch (Exception e) {
            System.out.println("Date must be formatted as (yyyy-MM-dd).");
            return false;
        }
    }

    public static boolean isActivity(String activity) {
        switch (activity) {
            case "exercise", "competitive", "none":
                return true;
            default:
                System.out.println("Activity must be either \"exercise\", \"competitive\" or \"none\".");
                return false;
        }
    }
}