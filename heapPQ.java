//----------------------------------------------------- 
// Title: Heap PQ Class
// Author: Batuhan Yıldız | Gökay Toğa
// ID: 41837144956 | 36932119060
// Section: 1
// Assignment: 4
// Description: This class contains the customer 
// heap priority queue called pq, it has the basic 
// methods for heap pq such as enlist(), sinkDown(),
// swimUp() and delMax. Additionally it has simulateTest
// and simulate classes which works with the given input
// enlists the customers when their time comes, assigns
// the couriers to the customers and deletes the one who
// are assigned to a courier.
//-----------------------------------------------------
import java.io.*;

public class heapPQ {
    int currSize = 0; //stores the current size and changes it based on new enlists and deletes
    int count=0;
    customer [] pq; //our heap pq array

    public heapPQ (int s) {
    //-------------------------------------------------------- 
    // Summary: Constructs our array with the given length
    // Precondition: s is an integer
    // Postcondition: The array is constructed with the length s
    //--------------------------------------------------------
        pq = new customer [s];
        for (int i = 1; i<s; i++) {
            pq [i] = new customer(); 
        }
    }
    
    public void enlist (customer toBeEnlisted) {
    //-------------------------------------------------------- 
    // Summary: Enlists the given customer and resorts the heap
    // Precondition: toBeEnlisted is a customer
    // Postcondition: toBeEnlisted is enlisted and the array 
    // is sorted
    //--------------------------------------------------------
        currSize++;
        pq [currSize] = toBeEnlisted;
        swimUp(currSize);
    }
    public void swimUp(int index) {
        while (index > 1 && less(index/2,index)) { 
            exch(index, index/2);
            index = index/2;
        }
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
    //-------------------------------------------------------- 
    // Summary: enlists the current time customers, 
    // use the current number of couriers. operate on the current customers 
    // based on the priority level, delete the customers taken care of.
    // increment time with each step. store the serving times, take avg and 
    // compare to maxAvgWait; if bigger increment the courier count and try again,
    // if less or equal print the needed courier count and the simulation
    // Precondition: customer list is the customers in the txt file, maxAvgTime
    // is an integer
    // Postcondition: Correct avgTime and courierCount has been found and
    // the actual simulation is ran with the correct courierCount
    //--------------------------------------------------------
        /*
        CLARIFICATION:
        The list customerList is only used for reading the txt file purposes.
        It is in no way used to solve the problem, only to read the customers from.
        Basically functions as the text file in a format easy to enlist. 
        */      
        double avgTime;
        int courierCount = 0;
        int[] times;

        do { //until you find the correct courierCount

            int i=1;
            int time = 1;
            avgTime=0;

            // increment the count of couriers and initialize the couriers
            courierCount++;
            couriers [] allCouriers = new couriers [courierCount];
            for (int j = 0; j<courierCount; j++) {
                couriers temp = new couriers();
                allCouriers [j] = temp; 
            }

            // reset the times array
            times = new int [customerList.length];

            do { // until the heap queue is empty
                while (i<customerList.length) { //until all the customers are enlisted

                    //enlist the current time customers
                    while (customerList[i].getOrderTime()==time) {
                        enlist(customerList[i]);i++;
                        if (i==customerList.length) {
                            break;
                        }
                    } 
                    //update couriers, (checks if they became available int the current time)
                    allCouriers = updateCouriers(allCouriers, time);

                    //check if there are available couriers, if so assign them to the customer first in order
                    times = blockAvailableCouriers(allCouriers, times, time,true);

                    //increment time
                    time++;
                }
                //we do updating, blocking and incrementing time even after all the customers are enlisted
                allCouriers = updateCouriers(allCouriers, time);
                if(!pq[1].getId().equals(0)) {
                    times = blockAvailableCouriers(allCouriers, times, time, true);
                }  
                time++;
                if (count == 12) {
                    break;
                }
            } while ((!pq[1].getId().equals(0))); // stops when the queue is empty

                //calculate avgTime
                double totalTime = 0;
                for (int j = 1; j<customerList.length; j++) {
                    //System.out.println(times [j]);
                    totalTime += times[j];
                }
                avgTime = totalTime/(customerList.length-1);

                //check if it meets our conditions, if not loop again.
            } while (avgTime>maxAvgWait);

            //if the conditions are met and the algorithm came to this line,
            //we run the actual simulation and print the found avgTime.
            simulate(customerList, courierCount);
            avgTime = Math.round(avgTime * 100000);
            avgTime=avgTime/100000;
            if((avgTime%1)==0) {
            //check if the is an integer, if so print as an integer
            //to print as 3 rather than 3.0
                Integer temporaryInteger = (int) avgTime;
                System.out.println("\nAverage waiting time: " + temporaryInteger + " minutes");
            } else {
            //if not an integer but a decimal number print as such
                System.out.println("\nAverage waiting time: " + avgTime + " minutes");
            }
            
    }
    public void simulate (customer [] customerList, int courierCount) {
    //-------------------------------------------------------- 
    // Summary: the simulateTest method without avgTime checks or courier count tests.
    // Precondition: customer list is the customers in the txt file
    // Postcondition: The order of customers and their waiting times match our
    // expectations.
    //--------------------------------------------------------

            System.out.println("\nMinimum number of couriers required: " + courierCount + "\n");
            System.out.println("Simulation with " + courierCount + " couriers:\n");
            int i=1;
            int time = 1;
            double avgTime=0;

            //initialize couriers
            couriers [] allCouriers = new couriers [courierCount];
            for (int j = 0; j<courierCount; j++) {
                couriers temp = new couriers();
                allCouriers [j] = temp; 
            }
            //reset times array
            int [] times = new int [customerList.length];

            //simulate as explained on the inner circle of simulateTest
            do {
                while (i<customerList.length) {
                    //enlist the current time customers
                    while (customerList[i].getOrderTime()==time) {
                        //System.out.println("enlisted"+customerList[i].getId());
                        enlist(customerList[i]);i++;
                        if (i==customerList.length) {
                            break;
                        }
                    } 
                    allCouriers = updateCouriers(allCouriers, time);
                    times = blockAvailableCouriers(allCouriers, times, time,false);
                    time++;
                }

                allCouriers = updateCouriers(allCouriers, time);
                if(!pq[1].getId().equals(0)) {
                    times = blockAvailableCouriers(allCouriers, times, time, false);
                }  
                time++;
                if (count == 12) {
                    break;
                }

            } while ((!pq[1].getId().equals(0))); 
    }
    public int [] blockAvailableCouriers (couriers [] allCouriers, int [] times, int time, boolean test) {
    //-------------------------------------------------------- 
    // Summary: blocks the available couriers.
    // Precondition: allCouriers is a couriers array, times is 
    // an integer array, time is an integer, test is a boolean
    // Postcondition: Couriers who were initially available have been blocked,
    // customers who have been assigned are deleted
    //--------------------------------------------------------
        for (int i = 0; i<allCouriers.length; i++){ 
        //runs for each courier
            if(allCouriers[i].isAvailable) {
            //if the courier is available
                //delete the top customer, get the customer here and store in temp
                customer temp = delMaxPriority();
                //store the customers waiting time in the times array.
                times[temp.getId()] = time-temp.getOrderTime();
                count++;
                //block the courier after being assigned for as long as the customers order
                allCouriers[i].blockCourier(time, temp.getPreparationTime());
                //if this method is not being run as a test, print the orders.
                if(!test) {
                    System.out.println("Courier " + i + " takes customer " + temp.getId() 
                    + " at minute " + time + " (wait: " + times[temp.getId()] + " mins)");
                }
            }
        }
        //return times to update
        return times;
    }
    private customer delMaxPriority() {
    //-------------------------------------------------------- 
    // Summary: deletes and returns the top customer
    // Precondition: pq isnt blank
    // Postcondition: the customer is deleted, sinkDown is used,
    // currSize decremented and the customer returned
    //--------------------------------------------------------
        customer result = pq[1];
        // Replace the value
        // at the root with
        // the last leaf
        exch(1, currSize);
        pq[currSize] = new customer();
        currSize--;
        // Shift down the replaced
        // element to maintain the
        // heap property
        sinkDown(1);
        pq=pq;
        return result;
    }
    private customer returnMaxPriority() {
        return pq[1];
    }
    public couriers[] updateCouriers(couriers [] c, int time) {
    //-------------------------------------------------------- 
    // Summary: updates each courier on c array
    // Precondition: c is a couriers array, time is an integer.
    // Postcondition: the availability of the couriers has been updated
    //--------------------------------------------------------
        for (int i = 0; i<c.length;i++) {
            c[i].renewAvailability(time);
        }
        return c;
    }
    public void print() {
    //-------------------------------------------------------- 
    // Summary: prints the whole list on its current form 
    // (was used on debugging, useful tool to see the current version
    // of the list)
    // Precondition: pq isnt null
    // Postcondition: pq's each element has been printed
    //--------------------------------------------------------
        System.out.println("\n\n NEWEST VERSION OF THE LIST \n\n");
        for (int i = 1; i<currSize; i++) {
            pq[i].print();
        }
    }
    
    private boolean less(int i, int j) {
    //-------------------------------------------------------- 
    // Summary: checks the order of the customers, returns if i is less or not
    // Precondition: i and j are integers
    // Postcondition: the correct condition of being less is returned
    //--------------------------------------------------------
        if (pq[i].getPriorityLevel()<pq[j].getPriorityLevel()){
            return true;
        } else if (pq[i].getPriorityLevel().equals(pq[j].getPriorityLevel())&&pq[i].getOrderTime()>pq[j].getOrderTime()) {
            return true;
        }
        else{
            return false;
        } 
    }

    private void exch( int i, int j){ 
    //-------------------------------------------------------- 
    // Summary: exchanges pq[i] and pq[j]
    // Precondition: i and j are integers who have a corresponding customer in heap pq
    // Postcondition: customer i and j are exchanged orders.
    //--------------------------------------------------------
        customer temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }
}