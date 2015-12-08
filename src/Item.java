/**
 * An item.  Holds stats and information about items.
 * Created by bal_csmiller on 12/8/2015.
 */
public class Item {
    private String name;
    private String description;

    //TODO: Consider making this an interface for different types of items (weapons, potions, etc.)  Or should inheritance be used?

    public Item(String itemName, String itemDescription){
        name = itemName;
        description = itemDescription;
    }
}
