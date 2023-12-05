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
                System.out.print("Choose an option (0 to exit) [1-7]: ");
                choice = console.nextLine();

                switch (choice) {
                    case "0":
                        System.exit(0);
                    case "1":
                        new ConfigureMember().run();
                        break choice_loop;
                    case "2":
                        Begivenhedgemmer beg = new Begivenhedgemmer();
                        beg.menu();
                        break;
                    case "3":
                        new RegisterTournamentResults();
                        break choice_loop;
                    case "4":
                        Kassere kas = new Kassere();
                        kas.menu();
                        break;
                          case "5":
                        SwimManager.registerTime();
                        break;
                    case "6":
                        System.out.print("Please tell me the discipline to print top 5 times: ");
                        String disciplinToPrint = console.nextLine();
                        SwimManager.printTopFiveTimes(disciplinToPrint + ".txt");
                        break;
                    case "7":
                        
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
                \t5) Register training result.
                \t6) Print top 5 times.
                """);
    }
}
