import java.time.LocalDate;
import java.util.*;

public class EditMemberDetails {
    Scanner console = new Scanner(System.in);
    String memberName;
    HashMap<String, Member> memberList = new HashMap<>();

    public EditMemberDetails(String memberName) {
        this.memberName = memberName;
    }

    public void run() {
        
        choice_loop:
        while (true) {
            memberList = MemberList.loadMemberList();

            System.out.println("You can edit the following details of " + memberName + ":");
            System.out.println("""
                    \t1) Name
                    \t2) Date of birth
                    \t3) Type of activity
                    """);
            
            while (true) {
                System.out.print("Choose what to edit (0 to cancel) [1-3]: ");
                String choice = console.nextLine().strip();
                ConsoleCommands.clearScreen();

                switch (choice) {
                    case "0":
                        return;
                    case "1":
                        editName();
                        break choice_loop;
                    case "2":
                        editBirthDate();
                        break choice_loop;
                    case "3":
                        editActivity();
                        break choice_loop;
                }
            }
        }
    }

    private void editName() {
        Member member = memberList.get(memberName);

        System.out.println("Current name of " + memberName + " is: " + member.getName());
        System.out.print("Write new name of " + memberName + ": ");
        String newName = console.nextLine().strip();

        member.setName(newName);

        memberList.remove(memberName);
        memberList.put(newName, member);

        MemberList.saveListToFile(memberList);
    }

    private void editBirthDate() {
        Member member = memberList.get(memberName);
        
        String newBirthDate;

        newBirthDate_loop:
        while (true) {
            System.out.println("Current birthDate of " + memberName + " is: " + member.getBirthDate());
            System.out.print("Write new birthDate of " + memberName + ": ");
            newBirthDate = console.nextLine().strip();

            // Test whether input can be read as a LocalDate
            if (CheckUserInput.isDate(newBirthDate)) {
                break newBirthDate_loop;
            }
        }

        member.setBirthDate(LocalDate.parse(newBirthDate));

        memberList.replace(memberName, member);

        MemberList.saveListToFile(memberList);
    }

    private void editActivity() {
        Member member = memberList.get(memberName);
        
        String newActiviy;

        newActivity_loop:
        while (true) {
            System.out.println("Current activity of " + memberName + " is: " + member.getActivity());
            System.out.print("Write new activity of " + memberName + ": ");
            newActiviy = console.nextLine().strip();

            // Test whether input is an activity
            if (CheckUserInput.isActivity(newActiviy)) {
                break newActivity_loop;
            }
        }

        member.setActivity(newActiviy);

        memberList.replace(memberName, member);

        MemberList.saveListToFile(memberList);
    }
}