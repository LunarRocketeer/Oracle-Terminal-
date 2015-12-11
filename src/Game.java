import java.io.File;

/**
 * Takes in save
 * Completes actions based on user input
 * Allows for access to Character class
 * Contains reference material (maps and the like)
 * Allows for saving through Cradle class
 * Allows for return to main menu in Cradle class
 *
 * Created by bal_csmiller on 12/8/2015.
 */
public class Game {
    File currentSave;
    //If the current user is the game's Dungeon Master (this is confirmed via password)
    boolean isDM;
    String dmPassword;

    public Game(File saveGame){
        currentSave = saveGame;
        isDM = false;
        readSave();
    }

    /**
     * Initialize variables based on save game.  If this is a new game, the current user is automatically
     * made DM and is prompted for a password.  The user can then add maps, characters, items, and enemies to initialize their game.
     */
    private void readSave() {

    }
}
