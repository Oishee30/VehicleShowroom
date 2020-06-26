/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicle;

/**
 *
 * @author Asus
 */
import java.util.Scanner;

public class Vehicle {

    /**
     * @param args the command line arguments
     */
    static void diplayWelcomemsg() {
        String welcome_msg = "Please enter any operation id to proceed further.\n1.Add New Vehicle\n2.Remove Vehicle Info\n3.Show Current List\n4.Exit\ni.e. Press 1 to enter new vehicle info.";

        System.out.println(welcome_msg);

    }

    public static void main(String[] args) {
        // TODO code application logic here
        //test code

        int vehicle_type;
        String car_type;
        String model_number;
        String engine_type;
        float engine_power;
        String tire_size;
        String turbo = "";
        float weight = 0;
        int cnt;
        int n;
        String verify;
        Scanner scanner = new Scanner(System.in);

        String welcome_msg = "Welcome To Vehicle Shop";

        System.out.println(welcome_msg);
        diplayWelcomemsg();

        int operation_id = scanner.nextInt();

        VehicleInfo vehicleInfo = new VehicleInfo();
        while (true) {
            if (operation_id == 1) {
                System.out.println("Vehicle Type??\n 1.Normal Vehicle 2.Sports Vehilce  3. Heavy Vehicle\nENTER 1,2 OR 3\n Vehicle_type:  ");
                vehicle_type = scanner.nextInt();

                scanner.nextLine(); //clearing the buffer

                System.out.println("\nModel Number:  ");
                model_number = scanner.nextLine();

                if (!vehicleInfo.lookupCar(model_number).equals("")) {

                    System.out.println("\nThis model already exist. Do you want to increase the number of vehicle? Y or N");
                    verify = scanner.nextLine();
                    if (verify.equals('Y')) {
                        System.out.println("\nNumber of vehicle you want to increase: ");
                        cnt = scanner.nextInt();
                        String[] car_info = vehicleInfo.lookupCar(model_number).split(" ");
                        car_info[7] += Integer.toString(cnt);
                        vehicleInfo.add(Integer.parseInt(car_info[0]), car_info[1], car_info[2], Float.parseFloat(car_info[3]), car_info[4], car_info[5], Float.parseFloat(car_info[6]), Integer.parseInt(car_info[7]));
                    } else {
                        diplayWelcomemsg();
                        operation_id = scanner.nextInt();
                    }
                } else {

                    System.out.println("\nEngine Type: ");
                    engine_type = scanner.nextLine();

                    System.out.println("\nEngine Power:  ");
                    engine_power = scanner.nextFloat();

                    scanner.nextLine(); //clearing the buffer
                    System.out.println("\nTire Size ");
                    tire_size = scanner.nextLine();

                    if (vehicle_type == 2) {
                        System.out.println("\nTurbo  ");
                        turbo = scanner.nextLine();
                    }
                    if (vehicle_type == 3) {
                        System.out.println("\nWeight: ");
                        weight = scanner.nextFloat();
                    }
                    System.out.println("\nNumber of vehicle: ");
                    cnt = scanner.nextInt();

                    vehicleInfo.add(vehicle_type, model_number, engine_type, engine_power, tire_size, turbo, weight, cnt);
                }
            } else if (operation_id == 2) {
                System.out.println("\nModel Number:  ");
                scanner.nextLine();
                model_number = scanner.nextLine();
                vehicleInfo.delete(model_number);
                diplayWelcomemsg();
                operation_id = scanner.nextInt();
            } else if (operation_id == 3) {
                vehicleInfo.display();
                diplayWelcomemsg();
                operation_id = scanner.nextInt();
            } else {
                System.exit(0);
            }
        }

    }

}
