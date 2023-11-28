import java.util.*;
import java.io.*;
import java.time.LocalDate;

public class ConfigureMember {
    Scanner console = new Scanner(System.in);

    public void run() {
        while (true) {
            System.out.println("""
                You have 3 options:
                
                \t1) Register new member
                \t2) Edit member details
                \t3) Delete member
                \t4) List members
                """);
            
            choice_loop:
            while (true) {
                System.out.print("Pick an option (0 to cancel) [1-4]: ");
                String choice = console.nextLine();

                switch (choice) {
                    case "0":
                        return;
                    case "1":
                        new RegisterMember().run();
                        break choice_loop;
                    case "2":
                        System.out.print("Which member do you want to edit?: ");
                        String memberName = console.nextLine();
                        
                        if (!(nameExists(memberName))) {
                            System.out.println(memberName + " is not a registered member.");
                            break choice_loop;
                        }

                        new EditMemberDetails(memberName).run();
                        break choice_loop;
                    case "3":
                        System.out.print("Which member(s) do you want to delete (semicolon seperated list)?: ");
                        String[] names = console.nextLine().strip().split(";");
                        deleteMember(names);
                        break choice_loop;
                    case "4":
                        listMembers();
                        break choice_loop;

                }
            }
        }
    }

    public HashMap<String, Member> loadMemberList() {
        var memberList = new HashMap<String, Member>();
        
        try {
            var fileScanner = new Scanner(new File("Members.csv"));

            while (fileScanner.hasNextLine()) {
                String[] ln = fileScanner.nextLine().split(";");
                var member = new Member(ln[0], LocalDate.parse(ln[1]), ln[2], LocalDate.parse(ln[3]), Boolean.parseBoolean(ln[4]));
                memberList.put(member.getName(), member);
            }

            fileScanner.close();
        } catch (Exception e) {}

        return memberList;
    }

    public void writeToFile(HashMap<String, Member> memberList) {
        try {
            var fileWriter = new FileWriter(new File("Members.csv"));

            for (Member member : memberList.values()) {
                fileWriter.write(member.getName() + ";");
                fileWriter.write(member.getBirthDate() + ";");
                fileWriter.write(member.getActivity());
                fileWriter.write(member.getRegistrationDate() + ";");
                fileWriter.write(member.getPaymentStatus() + "\n");
            }

            fileWriter.close();
        } catch (Exception e) {}
    }

    public boolean nameExists(String name) {
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

    private void deleteMember(String[] names) {
        HashMap<String, Member> memberList = loadMemberList();
 
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

        writeToFile(memberList);
        System.out.println(success);
    }

    private void listMembers() {
        try {
            var members = new File("Members.csv");
            if (!(members.exists())) {
                System.out.println("There are no registered members.\n");
                return;
            }
        } catch (Exception e) {}

        HashMap<String, Member> memberList = loadMemberList();
        for (String name : memberList.keySet()) {
            System.out.println(name);
        }
    }
}