import java.util.*;
import java.io.*;
import java.time.*;

public class RegisterMember {
    private Scanner console = new Scanner(System.in);
    
    public void run() {
        System.out.print("Write name of new member: ");
        String name = console.nextLine();
        if (new ConfigureMember().nameExists(name)) {
            overrideOrConfigure(name);
        }

        String birthDate;
        
        birthDate_loop:
        while (true) {
            System.out.print("Write date og birth of new member: ");
            birthDate = console.nextLine();

            // Test whether input can be read as a LocalDate
            if (isDate(birthDate)) {
                break birthDate_loop;
            }
        }

        String activity;

        activity_loop:
        while (true) {
            System.out.print("Type of activity? [exercise/competitive/none]: ");
            activity = console.nextLine();
            if (isActivity(activity)) {
                break activity_loop;
            }
        }

        // Combine member details into one String and save it to a file
        String newMember = name + ";" + birthDate + ";" + activity + ";" + LocalDate.now() + ";false\n";
        writeToFile(newMember);

    }

    public boolean isDate(String birthDate) {
        try {
            LocalDate.parse(birthDate);
            return true;
        } catch (Exception e) {
            System.out.println("Date must be formatted as (yyyy-MM-dd).");
            return false;
        }
    }

    public boolean isActivity(String activity) {
        switch (activity) {
            case "exercise", "competitive", "none":
                return true;
            default:
                System.out.println("Activity must be either \"exercise\", \"competitive\" or \"none\".");
                return false;
        }
    }

    private void writeToFile(String newMember) {
        try {
            var fileWriter = new FileWriter(new File("Members.csv"), true);
            fileWriter.append(newMember);
            fileWriter.close();
        } catch(Exception e) {}
    }

    private void overrideOrConfigure(String name) {
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
                    new EditMemberDetails(name).run();
                case "0":
                    new ConfigureMember().run();
                    System.exit(0);
            }
        }
    }

}