package parking.repository;
import java.util.*;

import parking.models.parkingSpot;
import parking.models.parkingSpot.parkingSpotType;

public class parkingRepository {
     public final HashMap<Integer,parkingSpot> spots;

    public parkingRepository(){
        this.spots = new HashMap<>();
    }

    public void addParkingSpot(parkingSpot ParkingSpot){
      
            spots.put(ParkingSpot.getParkingSpotID(),ParkingSpot);
        
    }

    public parkingSpot getParkingSpot(int parkingSpotID){
        if(spots.containsKey(parkingSpotID)){
            return spots.get(parkingSpotID);
        }
        return null;
    }

    public boolean parkingExists(int parkingSpotID){
        if(spots.containsKey(parkingSpotID)){
            return true;
        }

        return false;
    }

    public List<parkingSpot> getAllParkingSpots(){
        return new ArrayList<>(spots.values());
    }
    

}
