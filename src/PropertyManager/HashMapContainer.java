package PropertyManager;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * This class contains the program's hashmap data.
 */

public class HashMapContainer {

    // HASH MAPS
    private static HashMap<Integer, Client> clientsHashMap = new HashMap<Integer, Client>();


    /**
     * Build a hashmap for the clients. ClientID and Client instance will be the Key and value pair.
     *
     * @param clients - the arraylist of all clients
     */
    public static void buildClientsHashMap(ArrayList<Client> clients) {
        for (int i = 0; i < clients.size(); i++) {
            clientsHashMap.put(clients.get(i).getID(), clients.get(i));
        }
    }


    /**
     * @return the hashmap for the clients
     */
    public static HashMap<Integer, Client> getClientsHashMap() {
        return clientsHashMap;
    }
}
