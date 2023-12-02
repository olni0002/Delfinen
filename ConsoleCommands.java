public class ConsoleCommands {
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
                new ProcessBuilder().command("cmd.exe", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder().command("/bin/sh", "-c", "clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {}
    }
}