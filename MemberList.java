import java.util.*;
import java.io.*;
import java.time.LocalDate;

public class MemberList {
    public static HashMap<String, Member> loadMemberList() {
        HashMap<String, Member> memberList = new HashMap<>();

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

    public static void saveListToFile(HashMap<String, Member> memberList) {
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
}