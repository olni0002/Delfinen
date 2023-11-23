import java.util.*;
import java.io.*;
import java.time.*;

public class RegisterMember {
    private Scanner console = new Scanner(System.in);
    
    public void run() {
        System.out.print("Write name of new member: ");
        String name = console.nextLine();
        nameExists(name);

        // Test whether input can be read as a LocalDate
        String birthDate = isDate();
        
        String activity = isActivity();

        // Combine member details into one String and save it to a file
        String newMember = name + ";" + birthDate + ";" + activity + ";" + LocalDate.now() + ";false\n";
        writeToFile(newMember);

    }

    private void nameExists(String name) {
        try {
            var fileReader = new Scanner(new File("Members.csv"));
            while (fileReader.hasNextLine()) {
                var tokenReader = new Scanner(fileReader.nextLine());
                tokenReader.useDelimiter(";");
                if (tokenReader.next().equals(name)) {
                    overrideOrConfigure();
                }
            }
            fileReader.close();
        } catch (Exception e) {}
    }

    private String isDate() {
        while (true) {
            System.out.print("Write date og birth of new member: ");
            String birthDate = console.nextLine();
            try {
                LocalDate.parse(birthDate);
                return birthDate;
            } catch (Exception e) {
                System.out.println("Date must be formatted as (yyyy-MM-dd).");
            }
        }
    }

    private String isActivity() {
        while (true) {
            System.out.print("Type of activity? [exercise/competitive/none]: ");
            String activity = console.nextLine();

            switch (activity) {
                case "exercise":
                    return "exercise";
                case "competitive":
                    return "competitive";
                case "none":
                    return "none";
                default:
                    System.out.println("Activity must be either \"exercise\", \"competitive\" or \"none\".");
            }
        }
    
    }

    private void writeToFile(String newMember) {
        try {
            var fileWriter = new FileWriter(new File("Members.csv"), true);
            fileWriter.append(newMember);
            fileWriter.close();
        } catch(Exception e) {}
    }

    private void overrideOrConfigure() {
        System.out.print("""
            Member with this name already exists.
            You can either:

            \t1) Override member
            \t2) Change member details
            """);

        while (true) {
            System.out.print("Which option do you choose (0 to cancel)? [1-2]: ");
            String choice = console.nextLine();
            switch (choice) {
                case "1":
                    return;
                case "2":
                    // run option to change member details when that class and method is written.
                    // run main options loop
                    System.exit(0);
                case "0":
                    // run main options loop
                    System.exit(0);
            }
        }
    }

}