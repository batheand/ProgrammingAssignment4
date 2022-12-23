public class heapPQ {
    int size;
    node root;
    public void initialize(String fileName) {

    }
    public void queue(node x) {
        getLast().setNext(x);
        size++;
    }
    public void swimUp(int index) {
        while (index > 1 && index(index).getCustomer().getId()>index(index/2).getCustomer().getId()) {
            node temp = index(index/2).getNext();
            index(index/2).setNext(index(index));
            index(index).setNext(temp);
            index(index-1).setNext(index(index/2));


        }
    }
    public void sinkDown() {}

    public void swap (node a, node b) {
        // If x and y are the same node, there is nothing to do
    if (a == b) {
        return;
      }
  
      // Find the previous nodes of x and y
      node prevA = null, prevB = null;
      node curr = root;
      while (curr != null) {
        if (curr.getNext() == a) {
          prevA = curr;
        }
        if (curr.getNext() == b) {
          prevB = curr;
        }
        curr = curr.getNext();
      }
  
      // If one of the nodes was not found, there is nothing to do
      if (prevA == null || prevB == null) {
        return;
      }
  
      // Swap the data of the nodes
      customer temp = a.getCustomer();
      a.setCustomer(b.getCustomer());
      b.setCustomer(temp);
    }
    public node getLast() { return index(size); }
    public node index(int k) {
        node currNode = root;
        if (k>size) {
            return null;
        } else {
            for (int i = 0; i<k-1; i++) {
                currNode = currNode.getNext();
            }
            return currNode;
        }
    }
    
}