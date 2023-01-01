//----------------------------------------------------- 
// Title: Couriers Class
// Author: Batuhan Yıldız | Gökay Toğa
// ID: 41837144956 | 36932119060
// Section: 1
// Assignment: 4
// Description: has the elements of couriers, constructor, 
// blocking the courier and updating the availability of 
// the courier methods
//-----------------------------------------------------
public class couriers {
    boolean isAvailable;
    int availableAt;

    public couriers () {
        isAvailable = true;
        availableAt = -1;
    }

    public void blockCourier(int atX, int forY) {
        //blocks the courier and sets availableAt
        isAvailable=false;
        availableAt = atX+forY;
    }
    public void renewAvailability(int currTime) {
        //if currTime is bigger than the time the courier
        //is availableAt, set the courier free
        if (currTime>=availableAt) {
            isAvailable = true;
            availableAt = -1;
        }
    }
}
