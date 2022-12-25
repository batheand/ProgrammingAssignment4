import java.io.*;

public class heapPQ {
    int currSize = 1;
    customer [] pq;
    
    public heapPQ (int s) {
        pq = new customer [s];
        for (int i = 1; i<s; i++) {
            pq [i] = new customer(); 
        }
    }
    public void enlist (customer toBeEnlisted) {
        pq [currSize] = toBeEnlisted;
        swimUp(currSize);
        currSize++;
    }
    public customer delMaxPriority () {

        customer max = pq[1];
        customer temp = pq[1];
        pq[1] = pq[currSize];
        pq[currSize] = temp;
        currSize--;
        pq[currSize+1] = null;
        sinkDown(1);
        return max;
    }
    public void swimUp(int index) {
        while (index > 1 && pq[index].getPriorityLevel()>pq[index/2].getPriorityLevel()) {    
            customer temp = pq[index];
            pq[index] = pq[index/2];
            pq[index/2] = temp;
            index = index/2;
        }
    }
    public void sinkDown(int index) {
        
        while (2*index <= currSize) {
            int j = 2*index;
            if (j < index && pq[j].getPriorityLevel()<pq[j+1].getPriorityLevel()) j++;
            //print();
            if (pq[index].getPriorityLevel()>=pq[j].getPriorityLevel()) break;
            customer temp = pq[index];
            pq[index] = pq[j];
            pq[j] = temp;
            index = j;
        }

    }

    public void simulateTest (customer [] customerList, int maxAvgWait) {
        //enlist the current time customers, use the current number of couriers
        //operate on the current customers based on the priority level
        //delete the customers taken care of
        //increment time
        //store the serving times, take avg compare to maxAvgWait
        //if bigger increment the courier count and try again
        //if less or equal print the needed courier count and the simulation
        int time = 1;
        double avgTime=0;
        int[] times = new int [customerList.length];
        int i=1;
        int courierCount = 0;

        do { //until you find the correct courierCount
            //initialize the couriers
            courierCount++;
            while (pq[1]!=null) {
                couriers [] allCouriers = new couriers [courierCount];
                for (int j = 0; j<courierCount; j++) {
                   couriers temp = new couriers();
                    allCouriers [j] = temp; 
                }
                while (i<customerList.length) {
                    //enlist the current time customers
                    while (customerList[i].getOrderTime()==time) {
                        enlist(customerList[i]);i++;
                    } 
                    i++;
                    times = blockAvailableCouriers(allCouriers, times, time,true);
                }
            }
                int totalTime = 0;
                for (int j = 1; j<customerList.length; j++) {
                    System.out.println(times [j]);
                    totalTime += times[j];
                }
                avgTime = totalTime/customerList.length;
            } while (avgTime>maxAvgWait);
            simulate(customerList, courierCount);
    }
    public void simulate (customer [] customerList, int courierCount) {
        //enlist the current time customers, use the current number of couriers
        //operate on the current customers based on the priority level
        //delete the customers taken care of
        //increment time
        //store the serving times, take avg compare to maxAvgWait
        //if bigger increment the courier count and try again
        //if less or equal print the needed courier count and the simulation
        int time = 1;
        double avgTime=0;
        int[] times = new int [customerList.length];
        int i=1;

            while (pq[1]!=null) {
                            //initialize the couriers
                couriers [] allCouriers = new couriers [courierCount];
                for (int j = 0; j<courierCount; j++) {
                    couriers temp = new couriers();
                    allCouriers [j] = temp; 
                }
                while (i<customerList.length) {
                    //enlist the current time customers
                    while (customerList[i].getOrderTime()==time) {
                        enlist(customerList[i]); i++;
                    }
                    i++;
                    times = blockAvailableCouriers(allCouriers, times, time, false);
                    allCouriers = updateCouriers(allCouriers, time);
                }
            }

            
    }
    public int [] blockAvailableCouriers (couriers [] allCouriers, int [] times, int time, boolean test) {
        for (int i = 0; i<allCouriers.length; i++){
            if(allCouriers[i].isAvailable) {
                customer temp = delMaxPriority();
                times[temp.getId()] = time-temp.getOrderTime();
                System.out.println(times[temp.getId()]);
                allCouriers[i].blockCourier(time, temp.getPreparationTime());
                //delete the root and block the courier
                //assign the root's time to times 
                if(!test) {
                    System.out.println("Courier " + i + " takes customer " + temp.getId() 
                    + " at minute " + time + " (wait: " + times[temp.getId()] + " mins");
                }
            }
        }
        return times;
    }
    public couriers[] updateCouriers(couriers [] c, int time) {
        for (int i = 0; i<c.length;i++) {
            c[i].renewAvailability(time);
        }
        return c;
    }
    public void print() {
        System.out.println("\n\n NEWEST VERSION OF THE LIST \n\n");
        for (int i = 1; i<currSize; i++) {
            pq[i].print();
        }
    }
    
    
}