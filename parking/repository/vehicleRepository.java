package parking.repository;
import parking.models.vehicle;
import parking.models.vehicle.vehicleType;

import java.util.*;

public class vehicleRepository {
    public final HashMap<Integer,vehicle> vehicles;
    public final HashMap<Integer,vehicle> Bike;

    public vehicleRepository(){
        this.vehicles = new HashMap<>();
        this.Bike = new HashMap<>();
    }

    public void addVehicle(vehicle Vehicle){
        
             vehicles.put(Vehicle.getVehicleID(),Vehicle);
      
    }

    public vehicle getVehicle(int vehicleID){
        if(vehicles.containsKey(vehicleID)){
            return vehicles.get(vehicleID);
        }
        return null;
    }


    public boolean vehicleExists(int vehicleID){
         if(vehicles.containsKey(vehicleID)){
            return true;
        }
        return false;
    }

    public List<vehicle> getAllVehicles(){
        return new ArrayList<>(vehicles.values());
    }
}
