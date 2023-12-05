import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        Scanner console = new Scanner(System.in);
        String choice;
        
        while (true) {
            showMenu();
            
            choice_loop:
            while (true) {
                System.out.print("Choose an option (0 to exit) [1-4]: ");
                choice = console.nextLine();

                switch (choice) {
                    case "0":
                        System.exit(0);
                    case "1":
                        Menu.configureMember();
                        break choice_loop;
                    case "2":
                    case "3":
                        Menu.registerTournamentResults();
                        break choice_loop;
                    case "4":
                }
            }
        }
    }

    private void showMenu() {
        System.out.println("""
                Welcome to the administration program og club Dolphin.

                You now have a number of options to choose from:

                \t1) Configure member (Registration, deletion etc.)
                \t2) Manage training results of swimmers
                \t3) Register tournament results of swimmers
                \t4) Manage financials (Payment status, revenue etc.)
                """);
    }
}