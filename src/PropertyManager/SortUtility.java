package PropertyManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * This class provides the sort functionality using Collection.sort() method with Comparator.
 * The methods in this class are heavily inspired from: https://stackoverflow.com/a/34646172
 */

public class SortUtility {
    
    /**
     * Sort the arraylist of Properties by their ID as an ascending order.
     *
     * @param properties - the arraylist of Properties to be sorted
     * @return the sorted arraylist of Properties
     */
    public static void sortPropertyByID(ArrayList<Property> properties) {
        Collections.sort(properties, new Comparator<Property>() {
            @Override
            public int compare(Property a, Property b) {
                return Integer.compare(a.getID(), b.getID());
            }
        });
        return;
    }

    
    /**
     * Sort the arraylist of Properties by their client ID as an ascending order.
     *
     * @param properties - the arraylist of Properties to be sorted
     */
    public static void sortPropertyByClientID(ArrayList<Property> properties) {
        Collections.sort(properties, new Comparator<Property>() {
            @Override
            public int compare(Property a, Property b) {
                return Integer.compare(a.getClientID(), b.getClientID());
            }
        });
        return;
    }


    /**
     * Sort the arraylist of Expenses by their property ID as an ascending order.
     *
     * @param expenses - the arraylist of Expenses to be sorted
     */
    public static void sortExpenseByPropertyID(ArrayList<Expense> expenses) {
        Collections.sort(expenses, new Comparator<Expense>() {
            @Override
            public int compare(Expense a, Expense b) {
                return Integer.compare(a.getPropertyID(), b.getPropertyID());
            }
        });
        return;
    }


    /**
     * Sort the arraylist of Rents by their property ID as an ascending order.
     *
     * @param rents - the arraylist of Rents to be sorted
     */
    public static void sortRentByPropertyID(ArrayList<Rent> rents) {
        Collections.sort(rents, new Comparator<Rent>() {
            @Override
            public int compare(Rent a, Rent b) {
                return Integer.compare(a.getPropertyID(), b.getPropertyID());
            }
        });
        return;
    }


    /**
     * Return the sorted arraylist of Expenses by their property ID in ascending order. The parameter
     * arraylist will not be affected by this sorting. 
     *
     * @param expenses - the arraylist of Expenses to be sorted
     * @return the reference of the sorted arraylist
     */
    public static ArrayList<Expense> sortCopiedExpenseByPropertyID
    (ArrayList<Expense> expenses) {

        // instantiate a new arraylist of Expenses
        ArrayList<Expense> newExpenses = new ArrayList<>();

        // deep copy the arraylist
        for(int i = 0; i < expenses.size(); i++) {
            newExpenses.add(expenses.get(i));
        }

        // sort the new arraylist and return
        sortExpenseByPropertyID(newExpenses);
        return newExpenses;
    }


    /**
     * Return the sorted arraylist of Rents by their property ID in ascending order. The parameter
     * arraylist will not be affected by this sorting. 
     *
     * @param rents - the arraylist of Rents to be sorted
     * @return the reference of the sorted arraylist
     */
    public static ArrayList<Rent> sortCopiedRentsByPropertyID
    (ArrayList<Rent> rents) {

        // instantiate a new arraylist of Rents
        ArrayList<Rent> newRents = new ArrayList<>();

        // deep copy the arraylist
        for(int i = 0; i < rents.size(); i++) {
            newRents.add(rents.get(i));
        }

        // sort the new arraylist and return
        sortRentByPropertyID(newRents);
        return newRents;
    }
}
