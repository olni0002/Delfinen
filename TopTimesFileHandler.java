package DelfinKlubben;

import java.io.*;
import java.util.List;
public class TopTimesFileHandler {

    private static final String FILE_NAME = "topTimes.txt";

     public static void saveTopTimes(List<Swimmer> swimmers) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            outputStream.writeObject(swimmers);
            System.out.println("Top times saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving top times to file.");
        }
    }

    public static List<Swimmer> loadTopTimes() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Swimmer>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error loading top times from file. Returning an empty list.");
            return List.of();
        }
    }

}
