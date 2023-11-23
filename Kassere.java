package test;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Kassere {




    public void reader(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<String> nameList = new ArrayList<>();
        String value;
        boolean hassan = true;


        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("file.csv"));
            while ((value = bufferedReader.readLine()) != null) {
                strings.add(value);
            }
            for (int i = 0; i <strings.size() ; i++) {
                String[] variableDeler = strings.get(i).split(";");
                String namesOnly = variableDeler[0];
                nameList.add(namesOnly);
            }
            System.out.println("angiv en person du vil ændre betalingsstatus på");
            String name = scanner.nextLine();


            while (hassan){
                if(nameList.contains(name)){

                    for (int i = 0; i < strings.size(); i++) {
                        String[] variableDeler = strings.get(i).split(";");
                        if (strings.get(i).contains("false") && strings.get(i).contains(name)) {
                            variableDeler[4] = "true";
                            hassan = false;
                        } else if (strings.get(i).contains("true") && strings.get(i).contains(name)) {
                            variableDeler[4] = "false";
                            hassan = false;
                        }
                        strings.set(i, String.join(";", variableDeler));

                    }
                }else {
                    System.out.println("name does not exist");
                    name = scanner.nextLine();
                }
            }


            try (BufferedWriter writer = new BufferedWriter(new FileWriter("file.csv"))) {
                for (int i = 0; i <strings.size() ; i++) {
                    writer.write(strings.get(i));
                    writer.newLine();
                }

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        System.out.println("RESTENCELISTE");
        for (int i = 0; i < strings.size(); i++) {
            if(strings.get(i).contains("false")){
                System.out.println(strings.get(i));
            }
        }


    }








}
