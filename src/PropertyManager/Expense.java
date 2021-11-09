package PropertyManager;

public class Expense {

    // DATA FIELDS
    private int propertyID;       // the ID of the property for which the expense was incurred
    private String description;   // a description of the expense
    private double cost;          // the cost of the expense
    private String date;          // the date on which the expense was incurred

    // the cost is declared as double datatype for the further flexibility
    // As this class is not used for processing date, the date is String datatype for the simplicity
    // the date is in yyyy-MM-dd format


    /**
     * Non-Argument Constructor
     */
    public Expense() {
        propertyID = -1;
        description = "";
        cost = -1;
        date = "2000-01-01";
    }


    /**
     * @return the ID of the property for which the expense was incurred
     */
    public int getPropertyID() {
        return propertyID;
    }

    /**
     * @return the description of the expense
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the cost of the expense
     */
    public double getCost() {
        return cost;
    }

    /**
     * @return the date on which the expense was incurred
     */
    public String getDate() {
        return date;
    }


    /**
     * @param ID - the ID of the property for which the expense was incurred
     */
    public void setPropertyID(int ID) {
        propertyID = ID;
    }

    /**
     * @param descr - the description of the expense
     */
    public void setDescription(String descr) {
        description = descr;
    }

    /**
     * @param cost - the cost of the expense
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * @param date - the date on which the expense was incurred
     */
    public void setDate(String date) {
        this.date = date;
    }
}
