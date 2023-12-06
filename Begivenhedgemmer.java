import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Begivenhedgemmer {




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
                    writer();
                    break;
                case 3:
                    System.out.println("går ud af begivenheder . . .");
                    valg = false;
                    break;
                default:
                    System.out.println("dette er ikke en mulighed");
                    break;
            }
        }

    }
    public void menuwriter(){
        System.out.println("1. Se kommende begivenheder");
        System.out.println("2. Tilføj en ny begivenhed");
        System.out.println("3. Quit");
    }
    public void viewlist(){
        String value;
        File file = new File("Begivenheder.txt");
        if(file.exists()){
            try {
                BufferedReader reader = new BufferedReader(new FileReader("Begivenheder.txt"));
                while((value = reader.readLine()) != null){
                    System.out.println(value);
                }
                reader.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("der er ingen Begivenheder");
        }

    }

    public void writer(){

        ArrayList<String> list2 = new ArrayList<>();
        ArrayList<Begivenheder> list3 = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        LocalDate dag = null;
        while (dag == null) {
            System.out.println("Skriv datoen på begivenheden (dd-mm-yyyy):");
            String dato = scanner.nextLine();

            try {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                dag = LocalDate.parse(dato, dateTimeFormatter);
            } catch (Exception e) {
                System.out.println("Ugyldigt datoformat. Prøv igen.");
            }
        }

        System.out.println("Skriv en begivenhed:");
        String begivenhed = scanner.nextLine();

        LocalTime tid = null;
        while (tid == null){
            System.out.println("Skriv en tid begivelsen starter (HH:mm)");
            String time = scanner.nextLine();

            try {
                DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("HH:mm");
                tid = LocalTime.parse(time,dateTimeFormatter2);
            }catch (Exception e){
                System.out.println("Ugyldigt tidsformat. Prøv igen.");
            }
        }

        list3.add(new Begivenheder(dag, begivenhed,tid));

        String value;
        File file = new File("Begivenheder.txt");
        if(file.canRead()){
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader("Begivenheder.txt"));
                while ((value = bufferedReader.readLine()) != null) {
                    list2.add(value);
                }
                bufferedReader.close();

                for (int i = 0; i <list2.size() ; i++) {
                    String[] variableDeler = list2.get(i).split("  -  ");
                    String begivenhed1 = variableDeler[0];
                    String dates = variableDeler[1];
                    LocalDate dag1 = LocalDate.parse(dates);
                    String tider = variableDeler[2];
                    LocalTime tider1 = LocalTime.parse(tider);
                    list3.add(new Begivenheder(dag1,begivenhed1,tider1));
                }
                Collections.sort(list3,new DateComparetor());
                for (int i = 0; i < list3.size(); i++) {
                    System.out.println(list3.get(i));
                }


            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Begivenheder.txt"));
            for (int i = 0; i < list3.size(); i++) {
                bufferedWriter.write(list3.get(i)+"");
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
