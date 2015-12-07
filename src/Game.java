import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * An easy way to store D&D style roleplaying game campaigns.
 * 
 * This class holds the main game, and manages user input.
 * 
 * v 0.0.1 - Last Updated on May 6th, 2015
 * 
 * @author Cameron Miller
 * 
 */
public class Game {

	public static boolean running;
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
		running = true;
		input = new Scanner(System.in);
		// TODO: Figure out how to change this location automatically
		location = "\\\\CSSDFS04\\Student_m$\\csmiller\\My Documents\\CS Projects\\Save Games";

		while (running) {
			println("Welcome to Oracle.  Enter input.");
			println("1 - New Game");
			println("2 - Load Game");
			println("3 - About");

			inputS = input.nextLine();
			if (inputS.equals("1")) {
				newGame();
			} else if (inputS.equals("2")) {
				loadGame();
			} else if (inputS.equals("3")) {
				printAbout();
			} else {
				println("it don't work");
			}
		}
		println("Shutting down...");

	}

	private static void newGame() {
		println("Please enter the name of your new campaign.  You can change this later.  Enter " + getCommand("stop") + " to exit.");
		String saveName = input.nextLine();
		String newLocation = getNewPath(saveName);

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
			} catch (IOException iox) {
				println("Oracle can't do that for some reason.");
				println("" + iox);
			}
		}
	}

	private static void loadGame() {
		boolean loading = true;
		do {
			println("Please enter the name of your save.  Enter " + getCommand("list") + " to see all saves in the current directory.  Enter " + getCommand("stop") + " to exit.");
			inputS = input.nextLine();
			if (inputS.equals(getCommand("stop"))) {
			loading = false;
			}
			else if (inputS.equals(getCommand("list"))) {
					File save = getSaveFromList();
					while (save == null && !inputS.equals(getCommand("stop"))){
						getSaveFromList();
					}
					currentSave = save;
					loading = false;
				}
			else{
					String saveName = inputS;
					String newLocation = getNewPath(saveName);
					if (fileExists(newLocation)) {
						currentSave = getSaveFromPath(newLocation);
						loading = false;
					}
					else{
						print("File does not exist.  ");
					}
				}

		}while (loading) ;
		blankLine();
	}

	private static String getNewPath(String currName){
		return location + "\\" + currName;
	}

	private static File getSaveFromPath(String path){
		File save = new File(path);
		printLoadingMessage(save);
		return save;
	}

	private static File getSaveFromList(){
		println("Type in a number to select a save, or enter " + getCommand("stop") + " to go exit.");
		File folder = new File(location);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++){
			println(i + ": " + listOfFiles[i].getName());
		}
		inputS = input.nextLine();
		if (inputS.equals(getCommand("stop"))){
			return null;
		}
		else {
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

	private static void printLoadingMessage(File save){
		println("Loading save '" + save.getName() + "'...");
	}

	private static String getCommand(String command){
		String addition = "/";
		String stop = "stop";
		String list = "list";

		String current = addition;

		if (command.equals("stop")){
			current += stop;
		}
		else if(command.equals("list")){
			current += list;
		}
		else{
			current = "Sorry, command does not exist.";
		}

		return current;
	}

	private static boolean fileExists(String path){
		Path p = Paths.get(path);
		if (Files.exists(p)){
			return true;
		}
		return false;
	}

	// TODO: Figure out how to print this all at once, but format.
	public static void printAbout() {
		println("Oracle is a program meant to manage a D&D style roleplaying game.");
		println("It can store maps and pictures, take care of stats and enemies, and save campaigns for later.");
		println("");
	}

	public static void blankLine(){
		println("");
	}

	public static void println(String s) {
		System.out.println(s);
	}

	public static void print(String s) {
		System.out.print(s);
	}
}
