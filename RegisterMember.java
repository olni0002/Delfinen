import java.util.Scanner;
import java.time.LocalDate;

public class RegisterMember {
    Scanner console = new Scanner(System.in);
    
    public void run() {
        System.out.print("Write name of new member: ");
        String name = console.nextLine().strip();
        if (CheckUserInput.nameExists(name)) {
            overrideOrConfigure(name);
        }

        String birthDate;
        
        birthDate_loop:
        while (true) {
            System.out.print("Write date og birth of new member: ");
            birthDate = console.nextLine().strip();

            // Test whether input can be read as a LocalDate
            if (CheckUserInput.isDate(birthDate)) {
                break birthDate_loop;
            }
        }

        String activity;

        activity_loop:
        while (true) {
            System.out.print("Type of activity? [exercise/competitive/none]: ");
            activity = console.nextLine().strip();
            if (CheckUserInput.isActivity(activity)) {
                break activity_loop;
            }
        }

        // Combine member details into one String and save it to a file
        Member newMember = new Member(name, LocalDate.parse(birthDate), activity, LocalDate.now(), false);
        newMember.writeMemberToFile();
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
            String choice = console.nextLine().strip();
            ConsoleCommands.clearScreen();

            switch (choice) {
                case "1":
                    return;
                case "2":
                    new EditMemberDetails(name).run();
                case "0":
                    new ConfigureMember().run();

                    // run main options loop

                    ConsoleCommands.clearScreen();
                    System.exit(0);
            }
        }
    }
}