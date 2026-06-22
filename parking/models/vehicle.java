package parking.models;
import java.util.*;



public class vehicle {
    public enum vehicleType{
    CAR,
    BIKE,
    TRUCK
}

    int vehicleID;
    vehicleType vehicleType;


    public vehicle(int vehicleID, vehicleType vehicleType){
        this.vehicleID = vehicleID;
        this.vehicleType = vehicleType;
    }

    public int getVehicleID(){
        return vehicleID;
    }

    public vehicleType getVehicleType(){
        return vehicleType;
    }
}
