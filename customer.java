public class customer {
    private Integer priorityLevel = 0, id = 0, orderTime = 0, preparationTime = 0;

    public void setPriorityLevel(Integer priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setOrderTime(Integer orderTime) {
        this.orderTime = orderTime;
    }

    public void setPreparationTime(Integer preparationTime) {
        this.preparationTime = preparationTime;
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
