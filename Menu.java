public class Menu {
    public static void showMenu() {
        System.out.println("""
                Welcome to the administration program og club Dolphin.

                You now have a number of options to choose from:

                \t1) Configure member (Registration, deletion etc.)
                \t2) Manage training results of swimmers
                \t3) Register tournament results of swimmers
                \t4) Manage financials (Payment status, revenue etc.)
                """);
    }
    
    public static void configureMember() {
        new ConfigureMember().run();
    }

    public static void registerTournamentResults() {
        new RegisterTournamentResults();
    }
}