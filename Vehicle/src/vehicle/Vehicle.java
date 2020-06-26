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

    static float floatInput(Scanner scanner) {
        float a;
        
        while (true) {
            if (!scanner.hasNextFloat()) {
                scanner.nextLine();
                System.out.println("Enter valid input. Float Type.");
            } else {
                a = scanner.nextFloat();
                break;
            }
        }

        return a;
    }
    static int intInput(Scanner scanner) {
        int a;
        
        while (true) {
            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("Enter valid input. Int Type.");
            } else {
                a = scanner.nextInt();
                break;
            }
        }

        return a;
    }
    static void op_idCheck(int operation_id,Scanner scanner) {
        while (operation_id != 1 && operation_id != 2 && operation_id != 3 && operation_id != 4) {
            System.out.println("Enter valid input.");
            diplayWelcomemsg();
            operation_id = intInput(scanner);
        }

 
    }

    public static void main(String[] args) {
        // TODO code application logic here
        //test code

        int vehicle_type;
        String model_number;
        String engine_type;
        float engine_power;
        String tire_size;
        String turbo = "Null";
        float weight = 0;
        int cnt;
        int n;
        String verify;
        String car_type[] = {"Normal_Vehicle","Sports_Vehicle","Heavy_Vehicle"};
        Scanner scanner = new Scanner(System.in);

        String welcome_msg = "Welcome To Vehicle Shop";

        System.out.println(welcome_msg);
        diplayWelcomemsg();

        int operation_id = intInput(scanner);
        op_idCheck(operation_id,scanner);

        VehicleInfo vehicleInfo = new VehicleInfo();
        while (true) {
            if (operation_id == 1) {
                System.out.println("Vehicle Type??\n 1.Normal Vehicle 2.Sports Vehilce  3. Heavy Vehicle\nENTER 1,2 OR 3\n Vehicle_type:  ");
                vehicle_type = intInput(scanner);

                while (vehicle_type != 1 && vehicle_type != 2 && vehicle_type != 3) {
                    System.out.println("Enter valid input. 1.Normal Vehicle 2.Sports Vehilce  3. Heavy Vehicle\nENTER 1,2 OR 3");
                    vehicle_type = scanner.nextInt();
                }

                scanner.nextLine(); //clearing the buffer

                System.out.println("\nModel Number:  ");
                model_number = scanner.nextLine();

                if (!vehicleInfo.lookupCar(model_number, vehicle_type).equals("")) {

                    System.out.println("\nThis model already exist. Do you want to increase the number of vehicle? Y or N");
                    verify = scanner.nextLine().toUpperCase();
                    if (verify.equals("Y")) {
                        System.out.println("\nNumber of vehicle you want to increase: ");
                        cnt = intInput(scanner);
                        scanner.nextLine();
                        String[] car_info = vehicleInfo.lookupCar(model_number, vehicle_type).split(" ");
                        int new_cnt = Integer.parseInt(car_info[7]) + cnt;
                        car_info[7] = Integer.toString(new_cnt);
                        System.out.println("New number of car "+ car_info[7]);
                        vehicleInfo.update(car_info);
                    } else {
                        diplayWelcomemsg();
                        operation_id = intInput(scanner);
                        op_idCheck(operation_id,scanner);
                    }
                } else {

                    System.out.println("\nEngine Type: ");
                    engine_type = scanner.nextLine();
                    
                    System.out.println("\nEngine Power:  ");
                    engine_power = floatInput(scanner);

                    scanner.nextLine(); //clearing the buffer
                    System.out.println("\nTire Size ");
                    tire_size = scanner.nextLine();

                    if (vehicle_type == 2) {
                        System.out.println("\nTurbo  ");
                        turbo = scanner.nextLine();
                    }
                    if (vehicle_type == 3) {
                        System.out.println("\nWeight: ");
                         weight = floatInput(scanner);
         

                    }
                    System.out.println("\nNumber of vehicle: ");
                     cnt = intInput(scanner);
                   

                    vehicleInfo.add(car_type[vehicle_type-1], model_number, engine_type, engine_power, tire_size, turbo, weight, cnt);
                    diplayWelcomemsg();
                    operation_id = intInput(scanner);
                    op_idCheck(operation_id,scanner);
                }
            } else if (operation_id == 2) {
                System.out.println("Vehicle Type??\n1.Normal Vehicle 2.Sports Vehilce  3. Heavy Vehicle\nENTER 1,2 OR 3\nVehicle_type:  ");
                vehicle_type = intInput(scanner);
                System.out.println("\nModel Number:  ");
                scanner.nextLine();
                model_number = scanner.nextLine();
                vehicleInfo.delete(model_number, vehicle_type);
                diplayWelcomemsg();
                operation_id = intInput(scanner);
                op_idCheck(operation_id,scanner);
            } else if (operation_id == 3) {
                vehicleInfo.display();
                diplayWelcomemsg();
                operation_id = intInput(scanner);
                op_idCheck(operation_id,scanner);
            } else {
                System.exit(0);
            }
        }

    }

}
