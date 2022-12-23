public class node{
    private node next = null;
    private customer customer;

    public customer getCustomer() {
        return customer;
    }
    public void setCustomer(customer customer) {
        this.customer = customer;
    }
    public node (String customerInfo) { 
        customerInfo = customerInfo.trim();
        String [] butcheredInfo = customerInfo.split(" ");
        if (butcheredInfo.length==4) {
            customer.setId(Integer.parseInt(butcheredInfo[0]));
            customer.setPriorityLevel(2022-Integer.parseInt(butcheredInfo[1]));
            customer.setOrderTime(Integer.parseInt(butcheredInfo[2]));
            customer.setPreparationTime(Integer.parseInt(butcheredInfo[3]));
        }
    }
    public int compareTo(node another) {
        return this.getCustomer().getId().compareTo(another.getCustomer().getId());
    }


    public node getNext() { return next; }
    public void setNext( node next ) { this.next = next; } 
}
