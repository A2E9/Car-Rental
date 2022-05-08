package com.eurocar.rental;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class VehicleRental {

    final static ArrayList<Vehicle> productsList = new ArrayList<>();
 
    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(System.in);
        Boolean loop = true; //Closing switch-case loop

        InitalizeAllVehicles(); //Add all Vehicle-Objects to ArrayList

        //Entry Point
        while(loop){
            System.out.print("[1]List Vehicles [2]Find Vehicle [3]Add Vehicle [4]Exit |->|: ");     
            char c = reader.next().charAt(0); //Change to String cz Uppercase
            System.out.println();
            switch (c) {
                case '1':
                ListAllVehicles();
                    break;
                case '2':
                FindVehicle();
                    break;
                case '3':
                CreateVehicle();
                    break;
                case '4':
                loop = false;
                reader.close();
                    break;
                default:
                System.out.println("Option not available.");
                    break;
            }
        }
    }

    //Suchen vom Auto in der Datei
    public static void FindVehicle() throws IOException{

        Scanner scanner = new Scanner(System.in); //Cannot close Scanner cz of switch Scanner
        boolean loop = true;
        System.out.print("Vehicle name: ");
        
        String vehicleName = scanner.nextLine();

        System.out.println(" "); //placeholder

        Boolean itemFound = false;

        Boolean restartLoop = true;

        short vehicleIndex = 0;

        
        for (Vehicle element : productsList){
            if (element.getName().equalsIgnoreCase(vehicleName)){
                System.out.println(element);
                itemFound = true;
                 vehicleIndex = (short) productsList.indexOf(element);
            }
        }

        while(loop){
            System.out.print("[1]Change_Data [2]Use_vehicle [3]Back -> ");

            char cmd = scanner.next().charAt(0);
            System.out.println();
            switch (cmd) {
                case '1':
                OverrideVehicleData(vehicleIndex);
                    break;
                case '2':
                UsageOfVehicle(vehicleIndex);
                    break;
                    case '3':
                loop = false;
                    break;
                default:
                    System.out.println("option not available.");
                    break;
            }
            if(!itemFound){      //If vehicle not found
                while(restartLoop){
                System.out.print("Vehicle not found. Try again? [y/n]: ");
                char tryAgain = scanner.next().charAt(0);
                if(tryAgain == 'y'){
                    FindVehicle();
                    restartLoop = false;
                }
                else if(tryAgain == 'n')
                    restartLoop = false;
                }           
            }  
        }
        //scanner.close();
    }
    //Einzele Objekte des Fahrzeugs umschreiben
    public static void OverrideVehicleData(short index)  { 
        Scanner scanner = new Scanner(System.in);

        Map<Character,Runnable> commands = new HashMap<>();

        System.out.println("Which element you want to override?\n");

        System.out.print("[1]Name [2]Mileage [3]TankSize [4]LicensePlate [5]ManuFacturer [6]Consumption [7]MaxTank [8]BasePrice [9]Back |->|: ");

        char cmd = scanner.next().charAt(0);

        commands.put('1', () -> productsList.get(index).setName());
        commands.put('2', () -> productsList.get(index).setMileage());
        commands.put('3', () -> productsList.get(index).setTankSize());
        commands.put('4', () -> productsList.get(index).setLicensePlate());
        commands.put('5', () -> productsList.get(index).setManuFacturer());
        commands.put('6', () -> productsList.get(index).setConsumption());
        commands.put('7', () -> productsList.get(index).setMaxTank());
        commands.put('8', () -> productsList.get(index).setBasePrice());

        commands.put('9', () -> System.out.println(""));

        commands.get(cmd).run();

        System.out.flush();
        //scanner.close();
    }
    //Benutzung vom Fahrzeug 
    public static void UsageOfVehicle(int vehIndex){
        //Tanken 
        Map<Character,Runnable> commands = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("[1]Drive [2]Refuel [3]Rent Vehicle [4]Back |->|: ");
        char cmd = scanner.next().charAt(0);


        commands.put('1', () -> productsList.get(vehIndex).Drive());
        commands.put('2', () -> productsList.get(vehIndex).Refuel());
        commands.put('3', () -> productsList.get(vehIndex).RentVehicle());

        commands.put('4', () -> System.out.println(""));

        commands.get(cmd).run();
        //scanner.close();
    }
    //Fahrzeug erstellen -> Datei
    public static void CreateVehicle(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Name: "); //Excpetion only 1 name
        String name = scanner.nextLine();

        System.out.print("Mileage: ");//Excetion under 0
        int mileage = scanner.nextInt();

        System.out.print("Availability: ");//Excetion if not true or false
        Boolean availability = scanner.nextBoolean();

       // System.out.print("TankStatus: ");//Excetion if not true or false
       // Boolean tankStatus = scanner.nextBoolean();


        System.out.print("TankSize: "); //Excetion under 0
        float tankSize = Float.valueOf(scanner.next().substring(0));

        System.out.print("LicensePlate: "); //Excpetion only 1 name
        String licensePlate = scanner.next();

        System.out.print("Manufacturer: "); //Excpetion only 1 name
        String manufacturer = scanner.next();

        System.out.print("Consumption: ");//Excetion under 0
        float consumption = Float.valueOf(scanner.next().substring(0));

        System.out.print("MaxTank: ");//Excetion under 0
        float maxTank = Float.valueOf(scanner.next().substring(0));

        System.out.print("BasePrice: ");//Excetion under 0
        int basePrice = scanner.nextInt();

        //System.out.print("RentalPeriod: ");//Excetion under 0
       //int rentalPeriod = scanner.nextInt();

        System.out.print("CarType: "); //Excpetion only 1 name
        String carType = scanner.next();

        Vehicle vehicle1 = new Vehicle(name, mileage, availability,tankSize, licensePlate, manufacturer, consumption, maxTank, basePrice, carType );
        vehicle1.WriteToFile();
        InitalizeAllVehicles();
        //scanner.close();
    }
    //Alle Fahrzeuge in die ArrayList hinzufügen
    public static void InitalizeAllVehicles() {
        try {

            Scanner input = new Scanner(new File("com\\eurocar\\rental\\Vehicle_Database.bin"));

            while(input.hasNext()) {  
               
                String name = input.next();
                int mileage = input.nextInt();
                boolean availability = input.nextBoolean();
                boolean aaa = input.nextBoolean();
                float tankSize = Float.valueOf(input.next().substring(0));
                String licensePlate = input.next();
                String manufacturer = input.next();
                float consumption = Float.valueOf(input.next().substring(0));
                float maxTank = Float.valueOf(input.next().substring(0));
                int basePrice = input.nextInt();
                //int mietPrice = input.nextInt();
                //int rentalPeriod = input.nextInt();
                String carType = input.next();

                Vehicle newProduct = new Vehicle(name, mileage,  availability,  tankSize, licensePlate, manufacturer, consumption, maxTank, basePrice, carType );
                productsList.add(newProduct);
        
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
    }
    //Alle Fahrzeuge auflisten
    public static void ListAllVehicles(){
        for (Vehicle product : productsList)
            System.out.println(product);
    }
   
}
