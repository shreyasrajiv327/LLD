package parking.models;

public class parkingSpot {

        public enum parkingSpotType{
    CAR,
    BIKE,
    TRUCK
}
    public int parkingSpotID;
    parkingSpotType parkingSpotType;
    boolean parkingAvailability ;

    public parkingSpot(int parkingSpotID, parkingSpotType parkingSpotType, boolean parkingAvailability){
        this.parkingSpotID = parkingSpotID;
        this.parkingSpotType = parkingSpotType;
        this.parkingAvailability = parkingAvailability;
    }

    public int getParkingSpotID(){
        return parkingSpotID;
    }

    public parkingSpotType getParkingSpotType(){
        return parkingSpotType;
    }

    public boolean getParkingAvailability(){
        return parkingAvailability;
    }
    public void occupySpot() {
    this.parkingAvailability = false;
}

public void freeSpot() {
    this.parkingAvailability = true;
}

}
