import java.io.*;

public class heapPQ {
    int size;
    customer [] pq;
    public void heapPQ(String fileName) {
        // This will reference one line at a time
        String line = null;
        try {
            // FileReader reads text files in the default encoding
            FileReader fileReader = new FileReader(fileName);

            // Wrap FileReader in BufferedReader
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            line = bufferedReader.readLine().trim();
            size = Integer.parseInt(line);
            // create a new array with the length we found
            pq = new customer[size];
            // Read each non-blank line of the file and store in the array
            for (int i = 0; i<size; i++) {
                line = bufferedReader.readLine().trim();
                String [] butcheredInfo = line.split(" ");
                if (butcheredInfo.length==4) {
                    customer temp = new customer(Integer.parseInt(butcheredInfo[0]), 2022-Integer.parseInt(butcheredInfo[1]), Integer.parseInt(butcheredInfo[2]), Integer.parseInt(butcheredInfo[3]));
                    pq [i] = temp;
                }
            }
            // Close the file
            bufferedReader.close();
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }
    public void swimUp(int index) {
        if(size>index) {
            while (index > 1 && pq[index].getPriorityLevel()>pq[index/2].getPriorityLevel()) {
                customer temp = pq[index];
                pq[index] = pq[index/2];
                pq[index/2] = temp;
                index = index/2;
            }
        }
    }
    public void sinkDown() {}
    
    
}