package com.eurocar.rental;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

class Vehicle { 
    private String name;
    private int mileage;
    private boolean rentalStatus;
    private boolean tankStatus;
    private float tankSize;
    private String licensePlate;
    private String manufacturer;
    private float consumption;
    private float maxTank;
    private int basePrice; // Preis pro tag
    private int rentalPrice;  //basePrice + rentalPeriod
    private int rentalPeriod;
    private String carType;
    //Tankstatus
    private Scanner scanner = new Scanner(System.in);

    //Konstruktor
    public Vehicle(String Name, int Mileage, boolean RentalStatus,  float TankSize, String LicensePlate, String Manufacturer, float Consumption, float MaxTank, int BasePrice, String CarType  ) {
        this.name=Name;
        this.mileage=Mileage;
        this.rentalStatus=RentalStatus;
        this.tankSize = TankSize;
        this.licensePlate = LicensePlate;
        this.manufacturer = Manufacturer;
        this.consumption = Consumption;
        this.maxTank = MaxTank;
        this.basePrice = BasePrice;
        this.carType = CarType;
    }
    //Schreib die Daten in die Datei
    public void WriteToFile(){
        try {
            FileWriter fileWriter = new FileWriter("com\\eurocar\\rental\\Vehicle_Database.bin", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.print(this.name + " ");
            printWriter.print(this.mileage + " ");
            printWriter.print(getRentalStatus() + " ");
            printWriter.print(getTankStatus() + " ");
            printWriter.print(this.tankSize + " ");
            printWriter.print(this.licensePlate + " ");
            printWriter.print(this.manufacturer + " ");
            printWriter.print(this.consumption + " ");
            printWriter.print(this.maxTank + " ");
            printWriter.print(this.basePrice + " ");

            printWriter.print(this.carType + " ");
            printWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//Tankt das Fahrzeug, soviel es geht
    public void Refuel(){
        System.out.print("How many liters you want to fill up: ");
        int toFuel = scanner.nextInt();

        float temp= tankSize += toFuel;
        float leftLiter;

        if(temp > maxTank){
            leftLiter = temp - maxTank;
            System.out.println("The car must not be overfilled. "+ leftLiter+" Liters left over");
            tankSize = maxTank;
        } else{
            System.out.println("Car was refueled.");
            tankSize = temp;
        }

    }
//Fährt soviel es geht, bis der tank leer ist
    public void Drive(){
        float tempLiter;
        float newKm;
        System.out.print("How many kilometers do you want to ride: ");
        int kilometerToDrive = scanner.nextInt();

        if(tankSize > 0){
            tempLiter = kilometerToDrive / consumption;

            if(tempLiter < maxTank){
                tankSize -= tempLiter;
                mileage += kilometerToDrive;
            }else{
                newKm = tankSize*(100/consumption);
                mileage += newKm;
                tankSize = 0;
                System.out.println("Your tank is empty. You have only driven "+newKm+" km");
            }
        }
    }
//Buchung vom Auto
    public void RentVehicle(){
        if(getRentalStatus()){
            System.out.print("Rental Period: ");
            rentalPeriod = scanner.nextInt();
            System.out.println("Rental Price: "+RentalPrice()+" Euro");

            setRentalStatus(false);
        }else
        {
            System.out.println("Car not available.");
        }
    }
//Berechnen vom RentalPrice
    public int RentalPrice()
    {
        int tempPrice = 0;
        int temp2 = 0;
        double percent = 0;
        tempPrice = rentalPeriod*basePrice;

       if(carType.equalsIgnoreCase("luxury")){
            percent = 30;
            temp2 = (int)(tempPrice +((percent/100)*tempPrice));
       }
        else if (carType.equalsIgnoreCase("middleClass")){
            percent = 20;
            temp2 = (int)(tempPrice +((percent/100)*tempPrice));
        }
        else if (carType.equalsIgnoreCase("smallCar")){
            percent = 10;
            temp2 = (int)(tempPrice +((percent/100)*tempPrice));
        }
        return rentalPrice= temp2;
    }

//Werden ausgeführt um daten des fahrzeugs zu ändern
    public void setName () {
        System.out.print("Name: ");
        String newName = scanner.next();
        this.name=newName;
    }
    public void setMileage () {
        System.out.print("Mileage: ");
        int newMileage = scanner.nextInt();
        this.mileage=newMileage;
    }
    public void setTankSize () {
        System.out.print("TankSize: ");
        int newtankSize = scanner.nextInt();
        this.tankSize=newtankSize;
    }
    public void setLicensePlate () {
        System.out.print("LicensePlate: ");
        String newLicensePlate = scanner.next();
        this.licensePlate=newLicensePlate;
    }
    public void setManuFacturer () {
        System.out.print("Manufacturer: ");
        String newManuFacturer = scanner.next();
        this.manufacturer=newManuFacturer;
    }
    public void setConsumption () {
        System.out.print("Consumtion: ");
        float newConsumption = Float.valueOf(scanner.next().substring(0));
        this.consumption=newConsumption;
    }
    public void setMaxTank ( ) {
        System.out.print("MaxTank: ");
        int newMaxTank = scanner.nextInt();
        this.maxTank= newMaxTank;
    }
    public void setBasePrice (){
        System.out.print("BasePrice: ");
        int newBasePrice = scanner.nextInt();
        this.basePrice= newBasePrice;
    }

//Werden nicht ausserhalb der klasse benutzt
    public void     setRentalPrice(int newRentalPrice) {this.rentalPrice= newRentalPrice;} 
    public void     setRentalStatus(boolean newRentalStatus) {this.rentalStatus= newRentalStatus;}
    public void     setRTankStatus(boolean newTankStatus) {this.tankStatus= newTankStatus;}
    public void     setRentalPeriod (int newRentalPeriod) {this.rentalPeriod= newRentalPeriod;}
     
    public String   getName () {return name;}
    public float    getTankSize () {return tankSize;}
    public String   getLicensePlate () {return licensePlate;}
    public String   getmanufacturer () {return manufacturer;}
    public int      getMileage () {return mileage;}
    public float    getConsumption () {return consumption;}
    public float    getMaxTank () {return maxTank;}
    public int      getBasePrice() {return basePrice;}  
    public int      getRentalPrice () {return rentalPrice;}
    public boolean  getRentalStatus() {return rentalStatus;}
    public boolean  getTankStatus() {return tankStatus;}
    public int      getRentalPeriod () {return rentalPeriod;}
    public String   getCarType(){return carType;}

    public String   RewriteRentalStatus () {return (rentalStatus) ? "Available" : "Vehicle already rented";}
    public String   RewriteTankStatus () {return (tankSize> maxTank/2) ? "half full" : "not half full";}

//Überschreibt die to string methode
    @Override
    public String toString() {
        return String.format("Name: %s\r\nMileage: %d km\r\nRentalState: %s\r\nTankState: %s\r\nTankSize: %f Liters\r\nLicensePlate: %s\r\nManufacturer: %s\r\nConsumption: %f l/100km\r\nMaxTank: %f Liters \r\nBasePrice: %d Euro/Day\r\nRentalPrice: %d Euro\r\nRentalPeriod: %d Days\r\nCarType: %s\r\n", 
                name, mileage, RewriteRentalStatus (), RewriteTankStatus(), tankSize, licensePlate, manufacturer, consumption, maxTank, basePrice, rentalPrice, rentalPeriod, carType);
    }
    

}
















