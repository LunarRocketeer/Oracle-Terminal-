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
    //TODO: Figure out stats that you want to keep and list them here.  Initialize them in the constructor.

    public Character(String charName){
        name = charName;

        conditions = new ArrayList<String>();
        inventory = new ArrayList<Item>();
    }
}
