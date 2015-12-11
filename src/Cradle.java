import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * An easy way to store D&D style roleplaying game campaigns.
 * <p>
 * What this class does:
 * Handles main menu
 * Handles saves
 * Handles user input
 * Interacts with Game class
 * <p>
 * v 0.0.1 - Last Updated on December 9th, 2015
 *
 * @author Cameron Miller
 */
public class Cradle {

    public static boolean mainMenu;
    public static boolean gameMenu;
    public static Scanner input;
    public static String inputS;
    public static String location;

    public static File currentSave;

    /**
     * Starts the game.
     *
     * @param args
     */
    public static void main(String[] args) {
        mainMenu = true;
        gameMenu = false;
        input = new Scanner(System.in);
        // TODO: Figure out how to change this location automatically
        location = "\\\\CSSDFS04\\Student_m$\\csmiller\\My Documents\\CS Projects\\Save Games";

        while (mainMenu) {
            println("Welcome to Oracle.  Enter input.");
            println("1 - New Game");
            println("2 - Load Game");
            println("3 - Settings.");
            println("4 - About");

            inputS = input.nextLine();
            if (inputS.equals("1")) {
                newGame();
            } else if (inputS.equals("2")) {
                loadGame();
            } else if (inputS.equals("3")) {
                settings();
            }
            else if(inputS.equals("4")){
                printAbout();
            }
            else {
                println("Invalid input.");
            }
        }
        blankLine();
        while(gameMenu){
            enterGame();
        }
        println("Thank you for using Oracle!  Now shutting down...");

    }

    /**
     * Menu for settings.
     */
    //TODO: Putting in a valid path doesn't work.
    //TODO: Establish an 'install' folder to keep these settings safe.
    private static void settings(){
        boolean settings = true;
        String inputS;
        println("Settings");
        while(settings) {
                        println("1- Set Directory");
            println("2 - Return to Main Menu");
            inputS = input.nextLine();
            if (inputS.equals("1")){
                println("Please enter directory path");
                inputS = input.nextLine();
                File file = new File(inputS + "\\tempCheckFile.bat");
                if (file.isDirectory()){
                    file = file.getParentFile();
                }
                else{
                    println("Meh.");
                }
            }
            else if (inputS.equals("2")){
                settings = false;
            }
            else{
                println("Invalid input.");
            }
        }
    }

    /**
     * Creates a new save which is named based on user input.
     */
    //TODO: Make new game current save.
    private static void newGame() {
        println("Please enter the name of your new campaign.  You can change this later.  Saves are case sensitive.  Enter " + getCommand("stop") + " to exit.");
        String saveName = input.nextLine();
        String newLocation = getNewPath(saveName);

        //TODO: Check to make sure savename doesn't equal ANY commands.
        //TODO: Might not need to do this - for some reason when typing /list the savegame just equals list.
        if (!saveName.equals(getCommand("stop"))) {
            try {
                println("Saving game...");
                while (fileExists(newLocation)) {
                    println("A campaign by that name already exists.  Please choose another.");
                    saveName = input.nextLine();
                    //TODO: could probably clean things up here with a do while
                    newLocation = getNewPath(saveName);
                }
                File newSave = new File(newLocation);
                FileWriter fw = new FileWriter(newSave);
                fw.write("NAME:" + saveName);
                fw.close();
                currentSave = newSave;
                switchMenus();
            } catch (IOException iox) {
                println("Oracle can't do that for some reason.");
                println("" + iox);
            }
        }
    }

    /**
     * Switches booleans to allow for movement between the Main Menu and the Game Menu.
     */
    private static void switchMenus(){
        if (gameMenu){
            gameMenu = false;
            mainMenu = true;
        }
        else{
            gameMenu = true;
            mainMenu = false;
        }
    }

    /**
     * Loads a save.
     * User can type in the name of a save, or choose from a list of saves in the current directory.
     */
    private static void loadGame() {
        boolean loading = true;
        do {
            println("Please enter the name of your save.  Enter " + getCommand("list") + " to see all saves in the current directory.  Enter " + getCommand("stop") + " to exit.");
            inputS = input.nextLine();
            if (inputS.equals(getCommand("stop"))) {
                loading = false;
            } else if (inputS.equals(getCommand("list"))) {
                File save = getSaveFromList();
                while (save == null && !inputS.equals(getCommand("stop"))) {
                    getSaveFromList();
                }
                currentSave = save;
                loading = false;
                switchMenus();
            } else {
                String saveName = inputS;
                String newLocation = getNewPath(saveName);
                if (fileExists(newLocation)) {
                    currentSave = getSaveFromPath(newLocation);
                    loading = false;
                    switchMenus();
                } else {
                    print("File does not exist.  ");
                }
            }

        } while (loading);
        blankLine();

    }

    /**
     * Enters new menu that allows for interaction with individual save files.
     */
    private static void enterGame(){

        println("Welcome to " + currentSave.getName());
        println("1 - Enter realm.");
        println("2 - Add content.");
        println("3 - Game info and settings.");
        println("4 - Return to Main Menu.");

        String inputS = input.nextLine();
    }

    /**
     * Gets the path for a save.
     * Adds the file name to the main directory.
     * @param currName
     * @return
     */
    private static String getNewPath(String currName) {
        return location + "\\" + currName;
    }

    /**
     * Returns a save file and prints a loading message.
     */
    private static File getSaveFromPath(String path) {
        File save = new File(path);
        printLoadingMessage(save);
        return save;
    }

    /**
     * Prints a loading message.
     * @param save
     */
    private static void printLoadingMessage(File save) {
        println("Loading save '" + save.getName() + "'...");
    }

    /**
     * Displays a list of the current saves in a directory.  User enters a number that corresponds with a save.
     * @return
     */
    private static File getSaveFromList() {
        println("Type in a number to select a save, or enter " + getCommand("stop") + " to go exit.");
        File folder = new File(location);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            println(i + ": " + listOfFiles[i].getName());
        }
        inputS = input.nextLine();
        if (inputS.equals(getCommand("stop"))) {
            return null;
        } else {
            File save = null;
            int loc = 0;
            try {
                loc = Integer.parseInt(inputS);
            } catch (NumberFormatException e) {
                println("Invalid input.  Type a number from 0 to " + listOfFiles.length);
            }
            if (loc < listOfFiles.length && loc > 0) {
                save = listOfFiles[Integer.parseInt(inputS)];
                printLoadingMessage(save);
            } else {
                println("Invalid input.  Type a number from 0 to " + listOfFiles.length);
            }
            return save;
        }
    }


    /**
     * Contains all / commands usable in the main menu.
     * @param command
     * @return
     */
    private static String getCommand(String command) {
        String addition = "/";
        String stop = "stop";
        String list = "list";

        String current = addition;

        if (command.equals("stop")) {
            current += stop;
        } else if (command.equals("list")) {
            current += list;
        } else {
            current = "Sorry, command does not exist.";
        }

        return current;
    }

    /**
     * Checks to see if an inputed path is legit.
     * @param path
     * @return
     */
    private static boolean fileExists(String path) {
        Path p = Paths.get(path);
        if (Files.exists(p)) {
            return true;
        }
        return false;
    }

    /**
     * Prints information about the program.
     */
    // TODO: Figure out how to print this all at once, but format.
    public static void printAbout() {
        println("Oracle is a program meant to manage a D&D style roleplaying game.");
        println("It can store maps and pictures, take care of stats and enemies, and save campaigns for later.");
        println("");
    }

    /**
     * Prints a blank line.
     */
    public static void blankLine() {
        println("");
    }

    /**
     * Lazy alternative to System.out.println
     * @param s
     */
    public static void println(String s) {
        System.out.println(s);
    }

    /**
     * Lazy alternative to System.out.print
     * @param s
     */
    public static void print(String s) {
        System.out.print(s);
    }
}
