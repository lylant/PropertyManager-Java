package PropertyManager;

// This class represents a single expense event.

import java.text.DecimalFormat;
import java.util.HashMap;

public class Expense extends MonetaryEvent {

    // decimal format for the monetary value
    private static DecimalFormat df = EnvManager.getDecimalFormat();


    // DATA FIELDS
    private String description;   // a description of the expense
    private double cost;          // the cost of the expense


    /**
     * Non-Argument Constructor
     */
    public Expense() {
        super();
        description = "";
        cost = -1;
    }

    /**
     * Constructor
     *
     * @param ID - the ID of the property for which the expense was incurred
     * @param descr - the description of the expense
     * @param cost - the cost of the expense
     * @param date - the date on which the expense was incurred
     */
    public Expense(int ID, String descr, double cost, String date) {
        super(ID, date);
        description = descr;
        this.cost = cost;
    }


    /**
     * @return the summary details of the expense event
     */
    public String toString() {
        String toStr = "Property Address:    " + super.getPropertyAddress()
                + "\nProperty Owner:\t     " + super.getPropertyOwnerName()
                + "\nExpense Description: " + description
                + "\nMonetary Amount:     $ " + df.format(cost)
                + "\nCurrent Date:\t     " + super.getDate();
        return toStr;
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
}
