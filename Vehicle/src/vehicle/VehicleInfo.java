/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicle;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.IOException;  // Import the IOException class to handle errors
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class VehicleInfo {

    String car_type[] = {"Normal_Vehicle", "Sports_Vehicle", "Heavy_Vehicle"};
    String model_number;
    String engine_type;
    String engine_power;
    String tire_size;
    String turbo;
    float weight;
    int cnt;

    VehicleInfo() {

    }

    /**
     * @retun cars information by reading from text file
     */
    ArrayList<String> fileRead() {
        String info = "";
        ArrayList<String> vehicle_list = new ArrayList<String>();
        try {
            File myObj = new File("vehicle.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                vehicle_list.add(data);

            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }

        return vehicle_list;
    }

    /**
     * @write cars information to a text file
     */
    void fileWrite(ArrayList<String> info) {
        try {
            File myObj = new File("vehicle.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                // System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }

        try {
            FileWriter writer = new FileWriter("vehicle.txt");
            for (String i : info) {
                writer.write(i + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    /**
     * @param car_typeId
     * @param model_number
     * @param engine_type
     * @param engine_power
     * @param tire_size
     * @param turbo
     * @param weight
     * @param cnt
     * @add car information to our source
     */
    public void add(String car, String model_number, String engine_type, float engine_power, String tire_size, String turbo, float weight, int cnt) {
        //String car = car_type[car_typeId-1];
        ArrayList<String> information = fileRead();
        LocalDate date = LocalDate.now();
        String newCar = car + ' ' + model_number + ' ' + engine_type + ' ' + engine_power + ' ' + tire_size + ' ' + turbo + ' ' + weight + ' ' + cnt + ' ' + date + '\n';
        information.add(newCar);
        fileWrite(information);
        System.out.println("Successfully Added The Information");
    }

    /**
     * @display show car information to console
     */
    public void display() {
        ArrayList<String> vehicle_list = fileRead();
        String[] headers = {"Vehicle_Type", "Model_Number", "Engine_Type", "Engine_power", "Tire_Size", "Turbo", "Weight", "No.Of Availability", "Date_Added"};
        int visitors = 30;
        int flag = 0;
        LocalDate date = LocalDate.now();
        CommandLineTable st = new CommandLineTable();
        st.setRightAlign(true);//if true then cell text is right aligned
        st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
        st.setHeaders(headers);//optional - if not used then there will be no header and horizontal lines
        System.out.println(headers.length);
        for (String i : vehicle_list) {
            String[] arr = i.split(" ");
            if (arr[0].equals("Sports_Vehicle") && arr[8].equals(date.toString())) {
                flag = 1;
            }
            if (arr.length == headers.length) {
                st.addRow(arr);
            }
            System.out.println(arr.length);
        }

        st.print();
        if (flag == 1) {
            System.out.println("Today's Visitors : " + (visitors + 20));
        } else {
            System.out.println("Today's Visitors : " + (visitors));
        }

    }
   /**
     * @lookupCar search car info by car model and vehicle_type
     */
    String lookupCar(String model, int v_type) {

        ArrayList<String> vehicle_list = fileRead();
        String count = "";
        String vehicle = "";
        for (String i : vehicle_list) {
            if (i.length() < 9) {
                continue;
            }
            String model_type = i.split(" ")[1];

            vehicle = i.split(" ")[0];

            if ((model_type.equals(model)) && (vehicle.equals(car_type[v_type - 1]))) {
                count = i;
                break;
            }
        }

        System.out.println(count);
        return count;
    }
     /**
     * @delete deletes car info by car model and vehicle_type
     */
    void delete(String model, int v_type) {

        ArrayList<String> vehicle_list = fileRead();
        ArrayList<String> new_vehicle_list = new ArrayList<String>();
        String vehicle = "";
        int flag = 0;
        for (String i : vehicle_list) {
            if (i.length() < 9) {
                continue;
            }

            String model_type = i.split(" ")[1];
            vehicle = i.split(" ")[0];

            if (model_type.equals(model) && vehicle.equals(car_type[v_type - 1])) {
                flag = 1;
                continue;
            }
            new_vehicle_list.add(i);

        }

        fileWrite(new_vehicle_list);
        if (flag == 1) {
            System.out.println("Vehicle Information Successfully Deleted");
        } else {
            System.out.println("Nothing to delete!");
        }
        display();
    }
      /**
     * @update number of available car
     */
    void update(String[] up_split) {

        ArrayList<String> vehicle_list = fileRead();
        ArrayList<String> new_vehicle_list = new ArrayList<>();
        int flag = 0;
        for (String i : vehicle_list) {
            if (i.length() < 9) {
                continue;
            }
            String model_type = i.split(" ")[1];
            String vehicle = i.split(" ")[0];

            if (model_type.equals(up_split[1]) && vehicle.equals(up_split[0])) {
                String k = "";
                for (String j : up_split) {
                    k += j + " ";
                }
                new_vehicle_list.add(k);
                continue;

            }
            new_vehicle_list.add(i);

        }

        fileWrite(new_vehicle_list);
        if (flag == 1) {
            System.out.println("Vehicle Information Successfully Updated");
        }
        display();
    }

}
