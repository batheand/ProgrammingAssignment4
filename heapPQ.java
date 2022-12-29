import java.io.*;

public class heapPQ {
    int size, currSize = 0;
    int count=0;
    customer [] pq;
    public heapPQ (int s) {
        pq = new customer [s];
        for (int i = 1; i<s; i++) {
            pq [i] = new customer(); 
        }
    }
    
    public void enlist (customer toBeEnlisted) {
        currSize++;
        pq [currSize] = toBeEnlisted;
        swimUp(currSize);
    }
    public void swimUp(int index) {
 /*       
        if (currSize==12) {
            int temporary = pq[index/2].getPriorityLevel();
            temporary = temporary;
            pq[index/2].print();
            pq[index/4].print();

        }
    
        currSize = currSize;
*/        
        while (index > 1 && pq[index].getPriorityLevel()>pq[index/2].getPriorityLevel()) { 
            /*    
            customer temp = pq[index];
            pq[index] = pq[index/2];
            pq[index/2] = temp;
            index = index/2;
            */
            exch(index, index/2);
            index = index/2;
        }
        currSize= currSize;
        pq = pq;


    }
    public void sinkDown(int index) {
        while (2*index <= currSize){
            int j = 2*index;
            if (j < currSize && less(j, j+1)) j++;
            if (!less(index, j)) break;
            exch(index, j);
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
        
        
        double avgTime;
        int courierCount = 0;

        do { //until you find the correct courierCount
            //initialize the couriers
            courierCount++;
            couriers [] allCouriers = new couriers [courierCount];
            int i=1;
            int time = 1;
            avgTime=0;
            System.out.println("\n\n ATTEMPTING WITH " + courierCount + " COURIERS");
            for (int j = 0; j<courierCount; j++) {
                couriers temp = new couriers();
                allCouriers [j] = temp; 
            }
            int[] times = new int [customerList.length];
            do {
                while (i<customerList.length) {
                    //enlist the current time customers
                    while (customerList[i].getOrderTime()==time) {
                        System.out.println("enlisted"+customerList[i].getId());
                        enlist(customerList[i]);i++;
                        if (i==customerList.length) {
                            break;
                        }
                    } 
                    times = blockAvailableCouriers(allCouriers, times, time,true);
                    allCouriers = updateCouriers(allCouriers, time);
                    time++;
                }

                allCouriers = updateCouriers(allCouriers, time);
                if(!pq[1].getId().equals(0)) {
                    times = blockAvailableCouriers(allCouriers, times, time, true);
                }  
                time++;
                if (count == 12) {
                    break;
                }

            } while ((!pq[1].getId().equals(0)));
                int totalTime = 0;
                
                for (int j = 1; j<customerList.length; j++) {
                    //System.out.println(times [j]);
                    totalTime += times[j];
                }
                
                avgTime = totalTime/(customerList.length-1);
            } while (avgTime>maxAvgWait);
            simulate(customerList, courierCount);
            System.out.println(avgTime);
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

            do {
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
                        if (i==13) {
                            break;
                        }
                    }
                    times = blockAvailableCouriers(allCouriers, times, time, false);
                    allCouriers = updateCouriers(allCouriers, time);
                    time++;
                    if (i==13) {
                        break;
                    }
                }
                allCouriers = updateCouriers(allCouriers, time);
                if(!pq[1].getId().equals(0)) {
                    times = blockAvailableCouriers(allCouriers, times, time, false);
                }      
                time++;
            } while (!pq[1].getId().equals(0));

            
    }
    public int [] blockAvailableCouriers (couriers [] allCouriers, int [] times, int time, boolean test) {
        for (int i = 0; i<allCouriers.length; i++){
            if(allCouriers[i].isAvailable) {
                customer temp = delMaxPriority();
                if(!temp.getId().equals(0)){
                    
                }
                times[temp.getId()] = time-temp.getOrderTime();
                System.out.println("ID: "+ temp.getId() + " order time: " + temp.getOrderTime() + " time: " + time);
                count++;
                //System.out.println(times[temp.getId()]);
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
    private customer delMaxPriority() {
        customer result = pq[1];
 
        // Replace the value
        // at the root with
        // the last leaf
        exch(1, currSize);
        pq[currSize] = new customer();
        currSize--;
        //pq[currSize+1] = null;
        // Shift down the replaced
        // element to maintain the
        // heap property
        sinkDown(1);
        return result;
    }
    private customer returnMaxPriority() {
        return pq[1];
        
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
    
    private boolean less(int i, int j) {
        if (pq[i].getPriorityLevel()<pq[j].getPriorityLevel()) return true;
        else return false;
    }

    private void exch( int i, int j){ 
        customer temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }
}