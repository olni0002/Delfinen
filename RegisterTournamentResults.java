import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class RegisterTournamentResults {
    Scanner console = new Scanner(System.in);

    public RegisterTournamentResults() {
        System.out.print("Which member do you want to register tournament results for?: ");
        String memberName = console.nextLine().strip();

        if (!(CheckUserInput.nameExists(memberName))) {
            System.out.println(memberName + " is not a registered member.");
            return;
        }

        HashMap<String, Member> memberList = MemberList.loadMemberList();

        if (!(memberList.get(memberName).getActivity().equals("competitive"))) {
            System.out.println(memberName + " is not a competitive swimmer.");
            return;
        }

        run(memberName);
    }

    public void run(String memberName) {
        System.out.print("What tournament did " + memberName + " participate in?: ");
        String tournamentName = console.nextLine().strip();

        if (new File("TournamentResults.csv").exists()) {
            if (alreadyRegistered(memberName, tournamentName)) {
                System.out.println(memberName + " is already registered for this tournament.");

                yesOrNo_loop:
                while (true) {
                    System.out.print("Do you want to override these tournament results? [Y/n]: ");
                    String yesOrNo = console.nextLine().strip().toLowerCase();

                    switch (yesOrNo) {
                        case "y", "":
                            break yesOrNo_loop;
                        case "n":
                            ConsoleCommands.clearScreen();
                            whatNow(memberName);
                            return;
                    }
                }
            }
        }

        System.out.print("What placement did " + memberName + " get in " + tournamentName + "?: ");
        String placement = console.nextLine().strip();
    
        try {
            Integer.parseInt(placement);

        } catch (Exception e) {
            ConsoleCommands.clearScreen();
            System.out.println("Placement must be an integer.");
            System.out.println("Tournament results for " + memberName + " not saved.\n");
            whatNow(memberName);
            return;
        }

        if (Integer.parseInt(placement) < 1) {
            ConsoleCommands.clearScreen();
            System.out.println("Placement must be positive.");
            System.out.println("Tournament results for " + memberName + " not saved.\n");
            whatNow(memberName);
            return;
        }

        System.out.print("What time did " + memberName + " get in " + tournamentName);
        System.out.print(" (Format example: (1 Minute and 20 seconds))?: ");
        String timeString = console.nextLine();
        String[] time = timeString.strip().split(" ");

        ConsoleCommands.clearScreen();

        if (!isTimeCorrectFormat(time)) {
            System.out.println("Incorrect format");
            System.out.println("Tournament results for " + memberName + " not saved.\n");
            whatNow(memberName);
            return;
        }
        
        String tournamentResults = memberName + ";" + tournamentName + ";" + placement + ";" + timeString + "\n";
        saveResultsToFile(tournamentResults);
        whatNow(memberName);
    }

    private void whatNow(String memberName) {
        System.out.println("You can now either:\n");
        System.out.println("\t1) Register tournament results for " + memberName);
        System.out.println("\t2) Register tournament results for another member");
        System.out.println("\t3) Go back.\n");

        while (true) {
            System.out.print("What will you do? [1-3]: ");
            String choice = console.nextLine().strip();
            ConsoleCommands.clearScreen();

            switch (choice) {
                case "1":
                    run(memberName);
                    return;
                case "2":
                    new RegisterTournamentResults();
                    return;
                case "3":
                    return;
            }
        }
    }

    private boolean alreadyRegistered(String memberName, String tournamentName) {
        try {
            var fileScanner = new Scanner(new File("TournamentResults.csv"));

            while (fileScanner.hasNextLine()) {
                String nextLine = fileScanner.nextLine();
                String nameInLine = nextLine.substring(0, nextLine.indexOf(";"));
                String tournamentNameInLine = nextLine.substring(nextLine.indexOf(";"), nextLine.indexOf(";", nextLine.indexOf(";")));

                if (memberName.equalsIgnoreCase(nameInLine) && tournamentName.equalsIgnoreCase(tournamentNameInLine)) {
                    return true;
                }                        
            }
            fileScanner.close();
        } catch (Exception e) {}
        return false;
    }

    private boolean isTimeCorrectFormat(String[] time) {
        if (time.length != 5) {
            return false;
        }

        if (!(time[1].equals("Minute"))) {
            return false;
        }

        if (!(time[2].equals("and"))) {
            return false;
        }

        if (!(time[4].equals("seconds"))) {
            return false;
        }

        try {
            Integer.parseInt(time[0]);
            Integer.parseInt(time[3]);
        } catch (Exception e) {
            return false;
        }

        if (Integer.parseInt(time[0]) < 0) {
            return false;
        }

        if (Integer.parseInt(time[3]) < 0) {
            return false;
        }

        return true;
    }

    private void saveResultsToFile(String tournamentResults) {
        try {
            var fileWriter = new FileWriter(new File("TournamentResults.csv"), true);
            fileWriter.append(tournamentResults);
            fileWriter.close();
        } catch(Exception e) {}

        System.out.println("Tournament results were successfully saved.");
    }
}
