public class couriers {
    boolean isAvailable;
    int availableAt;
    public couriers () {
        isAvailable = true;
        availableAt = -1;
    }
    public void blockCourier(int atX, int forY) {
        isAvailable=false;
        availableAt = atX+forY;
    }
    public void renewAvailability(int currTime) {
        if (currTime>=availableAt) {
            isAvailable = true;
            availableAt = -1;
        }
    }
}
