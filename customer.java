//----------------------------------------------------- 
// Title: Customer Class
// Author: Batuhan Yıldız | Gökay Toğa
// ID: 41837144956 | 36932119060
// Section: 1
// Assignment: 4
// Description: has the elements of customer, getters setters 
// constructor and print.
//-----------------------------------------------------

public class customer {
    private Integer priorityLevel = 0, id = 0, orderTime = 0, preparationTime = 0;

    public customer (int id, int priorityLevel, int orderTime, int preparationTime) {
        this.id=id;
        this.priorityLevel=priorityLevel;
        this.orderTime=orderTime;
        this.preparationTime=preparationTime;
    }
    public customer() {}
    public void print () {
        System.out.println("ID: " + id);
        System.out.println("Priority Level: " + priorityLevel);
        System.out.println("Order Time: " + orderTime);
        System.out.println("Preparation Time: " + preparationTime);

    }
    public Integer getPriorityLevel() {
        return priorityLevel;
    }

    public Integer getId() {
        return id;
    }

    public Integer getOrderTime() {
        return orderTime;
    }

    public Integer getPreparationTime() {
        return preparationTime;
    }
    
}
