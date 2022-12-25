import java.io.*;
public class Main {
    public static void main(String[] args) {
        customer [] customerList = txtToArray("sampleinput1.txt");
        heapPQ pq = new heapPQ(customerList.length);
        pq.simulateTest(customerList, 10);
    }

    public static customer [] txtToArray(String fileName) {
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
