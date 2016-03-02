import java.util.ArrayList;

/**
 * Contains stats and info on characters.
 * Created by bal_csmiller on 12/8/2015.
 */
public class Character {
    private String name;
    //TODO: Not sure if conditions should be stored as strings and deciphered in this class, or if they should have their own class.
    private ArrayList<String> conditions;
    private ArrayList<Item> inventory;
    private ArrayList<Armor> equipped;
    //TODO: Figure out stats that you want to keep and list them here.  Initialize them in the constructor.

    public Character(String charName){
        name = charName;

        conditions = new ArrayList<>();
        inventory = new ArrayList<>();
        equipped = new ArrayList<>();
    }

    public void addToInventory(Item i){
        inventory.add(i);
        sortInventory();
    }
    public void dropItem(Item i){
        //TODO: Might have to supply a string and search for it here?  This may or may not work as is.
        inventory.remove(i);
        sortInventory();
    }
    public void sortInventory(){
        //TODO
    }
    public void equip(){
        //TODO: Add thing from inventory to equipped
    }
    public void unequip(){
        //TODO: Remove thing from equipped (but leave it in inventory)
    }

    public ArrayList<Item> getInventory(){
        return inventory;
    }
    public ArrayList<String> getConditions(){
        return conditions;
    }
}
