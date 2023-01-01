//----------------------------------------------------- 
// Title: Main Class
// Author: Batuhan Yıldız | Gökay Toğa
// ID: 41837144956 | 36932119060
// Section: 1
// Assignment: 4
// Description: Gives the txt file inputted by the user
// and the maxAvgTime again inputted by the user to simulateTest
//-----------------------------------------------------

import java.io.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        System.out.println("Enter input filename:");
        String fileName = stdin.nextLine();
        System.out.println("Enter the maximum average waiting time:");
        int maxAvgWaitingTime = stdin.nextInt();
        customer [] customerList = txtToArray(fileName);
        if(customerList.length<201) {
            heapPQ pq = new heapPQ(customerList.length);
            pq.simulateTest(customerList, maxAvgWaitingTime);
        }  
    }

    public static customer [] txtToArray(String fileName) {
    //-------------------------------------------------------- 
    // Summary: reads the txt file and turns it into a customer
    // array for the ease of enlisting
    // Precondition: filename has a corresponding file
    // in the directory
    // Postcondition: The array is constructed and returned.
    //--------------------------------------------------------
        // This will reference one line at a time
        String line = null;
        int size = 1;
        try {
            // FileReader reads text files in the default encoding
            FileReader fileReader = new FileReader(fileName);

            // Wrap FileReader in BufferedReader
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            line = bufferedReader.readLine().trim();
            size = Integer.parseInt(line)+1;
            // create a new array with the length we found
            //pq = new customer[size];
            customer [] temp = new customer [size];
            // Read each non-blank line of the file and store in the array
            for (int i = 1; i<size; i++) {
                line = bufferedReader.readLine().trim();
                String [] butcheredInfo = line.split(" ");
                if (butcheredInfo.length==4) {
                    temp [i] = new customer(Integer.parseInt(butcheredInfo[0]), 2022-Integer.parseInt(butcheredInfo[1]), Integer.parseInt(butcheredInfo[2]), Integer.parseInt(butcheredInfo[3]));
                    //temp[i].print();
                }
            }
            // Close the file
            bufferedReader.close();
            return temp;
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
            return null;
        }
    }
}
