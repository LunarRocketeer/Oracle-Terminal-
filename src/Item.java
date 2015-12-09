import java.util.ArrayList;

/**
 * An item.  Holds stats and information about items.
 * Created by bal_csmiller on 12/8/2015.
 */
public class Item {
    private String name;
    private String description;
    private int value;
    private int weight;
    //TODO: Consider making an enchantment class- but probably best way is how it is now, analyze in Game class.
    private ArrayList<String> enchantments;

    //TODO: Consider making this an interface for different types of items (weapons, potions, etc.)  Or should inheritance be used?

    public Item(){

    }

    public void use(){
        System.out.println("There is no way to use this.");
    }

    public Item(String itemName, String itemDescription){
        name = itemName;
        description = itemDescription;
    }

    public void setName(String itemName){
        name = itemName;
    }
    public void setDescription(String itemDescription){
        description = itemDescription;
    }

    public void setValue(int goldValue){
        value = goldValue;
    }
    public void setWeight(int carryingWeight){
        weight = carryingWeight;
    }
    public void setEnchantments(ArrayList<String> enchantList){
        enchantments = enchantList;
    }


    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getValue(){
        return value;
    }
    public int getWeight(){
        return weight;
    }
    public ArrayList<String> getEnchantments(){
        return enchantments;
    }


}
