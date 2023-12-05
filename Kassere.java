
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Kassere {

    public void menu(){
        boolean valg = true;
        Scanner scanner = new Scanner(System.in);
        while (valg){
            menuwriter();
            switch (scanner.nextInt()){
                case 1:
                    viewlist();
                    break;
                case 2:
                    reader();
                    break;
                case 3:
                    System.out.println("går ud . . .");
                    valg = false;
                    break;
                default:
                    System.out.println("dette er ikke en mulighed");
                    break;
            }
        }
    }
    public void menuwriter(){
        System.out.println("1. Se betalingsstatuser for alle");
        System.out.println("2. Ændre betalinstadus for person");
        System.out.println("3. Quit");
    }

    public void viewlist(){
        String value;
        File file = new File("file.csv");
        if(file.exists()){
            try {
                BufferedReader reader = new BufferedReader(new FileReader("file.csv"));
                while((value = reader.readLine()) != null){
                    System.out.println(value);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("der er ingen Begivenheder");
        }

    }

    public void reader() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> strings1 = new ArrayList<>();
        ArrayList<String> nameList = new ArrayList<>();
        String value;
        boolean hassan = true;

        File file = new File("file.csv");
        if(file.exists()){
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader("file.csv"));
                while ((value = bufferedReader.readLine()) != null) {
                    strings1.add(value);
                }
                for (int i = 0; i < strings1.size(); i++) {
                    String[] variableDeler = strings1.get(i).split(";");
                    String namesOnly = variableDeler[0];
                    nameList.add(namesOnly);
                }

                System.out.println("angiv en person du vil ændre betalingsstatus på");
                String name = scanner.nextLine();


                while (hassan) {
                    if (nameList.contains(name)) {

                        for (int i = 0; i < strings1.size(); i++) {
                            String[] variableDeler = strings1.get(i).split(";");
                            if (strings1.get(i).contains("false") && strings1.get(i).contains(name)) {
                                variableDeler[4] = "true";
                                hassan = false;
                            } else if (strings1.get(i).contains("true") && strings1.get(i).contains(name)) {
                                variableDeler[4] = "false";
                                hassan = false;
                            }
                            strings1.set(i, String.join(";", variableDeler));

                        }
                    } else {
                        System.out.println("name does not exist");
                        name = scanner.nextLine();
                    }
                }


                try (BufferedWriter writer = new BufferedWriter(new FileWriter("file.csv"))) {
                    for (int i = 0; i < strings1.size(); i++) {
                        writer.write(strings1.get(i));
                        writer.newLine();
                    }

                }

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println("DUES PAYMENT LIST");
            for (int i = 0; i < (strings1.size()-2); i++) {
                String[] variableDeler = strings1.get(i).split(";");
                String name = variableDeler[0];
                String birthDate = variableDeler[1];
                String activity = variableDeler[2];
                boolean paymentStatus = Boolean.parseBoolean(variableDeler[4]);

                int age = calculateAge(birthDate);

                double duesPayment = calculateDuesPayment(activity, age, paymentStatus);

                System.out.println(name + " - Dues Payment: " + duesPayment + " DKK - Payment Status: " + (paymentStatus ? "Paid" : "Not Paid"));
            }
        }else{
            System.out.println("Du har ikke nogen i din liste");
        }

    }
    private int calculateAge(String birthDate) {
        int currentYear = java.time.Year.now().getValue();
        int birthYear = Integer.parseInt(birthDate.split("-")[0]);
        return currentYear - birthYear;
    }

    private double calculateDuesPayment(String activity, int age, boolean paymentStatus) {
        double basePayment = 0;

        if (activity.equals("exercise") || activity.equals("competitive")) {
            if (age < 18) {
                basePayment = 1000;
            } else if (age >= 18 && age < 60) {
                basePayment = 1600;
            } else if (age >= 60) {
                basePayment = 1600 * 0.75; // 25% discount for members over 60
            }
        } else if (activity.equals("none")) {
            basePayment = 500;
        }

        return basePayment;
    }
}
