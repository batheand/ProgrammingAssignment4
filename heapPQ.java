import java.io.*;

public class heapPQ {
    int size, currSize = 1;
    customer [] pq;
    public heapPQ(String fileName) {
        // This will reference one line at a time
        String line = null;
        try {
            // FileReader reads text files in the default encoding
            FileReader fileReader = new FileReader(fileName);

            // Wrap FileReader in BufferedReader
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            line = bufferedReader.readLine().trim();
            size = Integer.parseInt(line)+1;
            // create a new array with the length we found
            pq = new customer[size];
            // Read each non-blank line of the file and store in the array
            for (int i = 1; i<size; i++) {
                line = bufferedReader.readLine().trim();
                String [] butcheredInfo = line.split(" ");
                if (butcheredInfo.length==4) {
                    customer temp = new customer(Integer.parseInt(butcheredInfo[0]), 2022-Integer.parseInt(butcheredInfo[1]), Integer.parseInt(butcheredInfo[2]), Integer.parseInt(butcheredInfo[3]));
                    enlist(temp);
                    print();
                }
            }
            // Close the file
            bufferedReader.close();
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }
    public void enlist (customer toBeEnlisted) {
        pq [currSize] = toBeEnlisted;
        swimUp(currSize);
        currSize++;
    }
    public void swimUp(int index) {
        while (index > 1 && pq[index].getPriorityLevel()>pq[index/2].getPriorityLevel()) { 
            /*    
            customer temp = pq[index];
            pq[index] = pq[index/2];
            pq[index/2] = temp;
            index = index/2;
            */
            exch(index, index/2);
        }


    }
    public void sinkDown(int index) {
        while (2*index <= size){
            int j = 2*index;
            if (j < size && less(j, j+1)) j++;
            if (!less(index, j)) break;
            exch(index, j);
            index = j;
}
    }
    public void print() {
        System.out.println("\n\n NEWEST VERSION OF THE LIST \n\n");
        for (int i = 1; i<currSize; i++) {
            pq[i].print();
        }
    }
    
    private static boolean less(Comparable v, Comparable w){ 
        return v.compareTo(w) < 0; 
    }

    private static void exch( int i, int j){ 
        int temp = i;
        i = j;
        j = temp;
    }
}