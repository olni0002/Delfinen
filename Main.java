import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            Menu.showMenu();
            
            Scanner console = new Scanner(System.in);
            String choice;

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
}