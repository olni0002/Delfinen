import java.util.*;
import java.io.File;

public class ConfigureMember {
    Scanner console = new Scanner(System.in);

    public void run() {

        while (true) {
            System.out.println("""
                You have 4 options:
                
                \t1) Register new member
                \t2) Edit member details
                \t3) Delete member
                \t4) List members

                Pick an option (0 to cancel) [1-4]: """);

            String choice = console.nextLine().strip();
            ConsoleCommands.clearScreen();

            switch (choice) {
                case "0":
                    return;
                case "1":
                    new RegisterMember().run();
                    break;
                case "2":
                    System.out.print("Which member do you want to edit?: ");
                    String memberName = console.nextLine().strip();
                    ConsoleCommands.clearScreen();

                    if (!(CheckUserInput.nameExists(memberName))) {
                        System.out.println(memberName + " is not a registered member.\n");
                        break;
                    }

                    new EditMemberDetails(memberName).run();
                    break;
                case "3":
                    System.out.print("Which member(s) do you want to delete (semicolon seperated list)?: ");
                    String[] names = console.nextLine().strip().split(";");
                    ConsoleCommands.clearScreen();
                    deleteMember(names);
                    break;
                case "4":
                    listMembers();
                    break;
            }
        }
    }

    private void deleteMember(String[] names) {
        HashMap<String, Member> memberList = MemberList.loadMemberList();
 
        boolean canBeDeleted = false;
        String success = "";
        for (String name : names) {
            if (memberList.containsKey(name)) {
                canBeDeleted = true;
                memberList.remove(name); 
                success += name + " was succesfully deleted.\n";

            } else {
                success += name + " is not in the list of members.\n";
            }
        }

        if (!canBeDeleted) {
            System.out.println("No members were deleted.");
            return;
        }

        MemberList.saveListToFile(memberList);
        System.out.println(success);
    }

    private void listMembers() {
        try {
            var members = new File("Members.csv");
            if (!(members.exists())) {
                System.out.println("There are no registered members.\n");
                return;
            }

            var fileScanner = new Scanner(members);
            if (!(fileScanner.hasNextLine())) {
                fileScanner.close();
                System.out.println("There are no registered members.\n");
                return;
            }
            fileScanner.close();
        } catch (Exception e) {}

        HashMap<String, Member> memberList = MemberList.loadMemberList();
        for (String name : memberList.keySet()) {
            System.out.println(name);
        }
        System.out.println();
    }
}