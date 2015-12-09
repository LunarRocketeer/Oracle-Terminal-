import java.util.ArrayList;

/**
 * Created by bal_csmiller on 12/9/2015.
 */
public class Armor extends Item {

    private int defense;


    public Armor(){

    }
    public Armor(int defenseStat){
        defense = defenseStat;
    }
    public void setDefense(int defenseStat){
        defense = defenseStat;
    }
    public int getDefense(){
        return defense;
    }

}
