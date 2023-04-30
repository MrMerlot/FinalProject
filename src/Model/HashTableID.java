/**
 * HashTableID.java
 *
 * This holds the information for the items (food). It converts an item's ID number
 * into a price or a name. It allows for access to our items so that we can
 * add or remove items easily.
 *
 * @author Miles Hoffman
 */

package Model;

import java.util.HashMap;

public class HashTableID {

    private HashMap<Integer, String> itemIDTable = new HashMap<>();
    private HashMap<Integer, Double> itemPriceTable = new HashMap<>();


    public HashTableID(){
        createItemIDTable();
        createitemPriceTable();
    }
    public String getItemIDName( int ID ){
        return itemIDTable.get( ID );
    }
    public double getItemPrice( int ID ){ return itemPriceTable.get( ID ); }

    public void createItemIDTable(){
        itemIDTable.put( 1, "Iced Tea");
        itemIDTable.put( 2, "Water");
        itemIDTable.put( 3, "Hot Coffee");
        itemIDTable.put( 4, "Hot Chocolate");
        itemIDTable.put( 5, "Iced Noodles");
        itemIDTable.put( 6, "Watermelon Salad");
        itemIDTable.put( 7, "Pineapple Pizza");
        itemIDTable.put( 8, "Cheeseburger");
        itemIDTable.put( 9, "Fries");
        itemIDTable.put( 10, "Fried Oreos");
        itemIDTable.put( 11, "Pickles");
        itemIDTable.put(12, "The Special");
    }

    public void createitemPriceTable(){
        itemPriceTable.put( 1, 8.99);
        itemPriceTable.put( 2, 9.99);
        itemPriceTable.put( 3, 4.99);
        itemPriceTable.put( 4, 6.99);
        itemPriceTable.put( 5, 22.99);
        itemPriceTable.put( 6, 18.99);
        itemPriceTable.put( 7, 39.99);
        itemPriceTable.put( 8, 19.99);
        itemPriceTable.put( 9, 15.99);
        itemPriceTable.put( 10, 13.99);
        itemPriceTable.put( 11, 22.99);
        itemPriceTable.put(12, 99.99);
    }
}
