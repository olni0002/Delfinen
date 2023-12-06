import java.io.File;
import java.util.*;

public class ListTournamentResults {
    public void run() {
        ConsoleCommands.clearScreen();

        try {
            if (!(new File("TournamentResults.csv").exists())) {
                System.out.println("No tournament results registered for any member");
                return;
            }
        } catch (Exception e) {}

        HashMap<String, ArrayList<String>> tournaments = tournaments();
        printList(tournaments);
    }

    private void printList(HashMap<String, ArrayList<String>> tournaments) {
        for (Map.Entry<String, ArrayList<String>> tournament : tournaments.entrySet()) {
            System.out.println(tournament.getKey());

            ArrayList<String> tournamentMembers = tournament.getValue();
            for (int i = 0; i < tournamentMembers.size(); i++) {
                System.out.println("\t" + tournamentMembers.get(i));
            }
        }
    }

    private HashMap<String, ArrayList<String>> tournaments() {    
        var tournaments = new HashMap<String, ArrayList<String>>();

        try {
            var fileScanner = new Scanner(new File("TournamentResults.csv"));

            while (fileScanner.hasNextLine()) {
                
                String[] nextLine = fileScanner.nextLine().split(";");
                
                if (!(tournaments.containsKey(nextLine[1]))) {
                    var tournamentMembers = new ArrayList<String>();
                    tournamentMembers.add(nextLine[0]);
                    tournaments.put(nextLine[1], tournamentMembers);
                    continue;
                }

                ArrayList<String> tournamentMembers = tournaments.get(nextLine[1]);
                tournamentMembers.add(nextLine[0]);
            }
            fileScanner.close();
        } catch (Exception e) {}

        return tournaments;
    }
}