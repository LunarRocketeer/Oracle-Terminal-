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
		println("Please enter the name of your new campaign.  You can change this later.");
		String saveName = input.nextLine();

		String newLocation = location + "\\" + saveName;
		try {
			println("Saving game...");
			/**
			 * 
			 */
			Path path = Paths.get(newLocation);
			while (Files.exists(path)) {
				println("A campaign by that name already exists.  Please choose another.");
				//TODO: Figure otu a better way to do this - shouldn't repeat so much code!
				saveName = input.nextLine();
				newLocation = location + "\\" + saveName;
				path = Paths.get(newLocation);
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

	private static void loadGame() {
		println("Please enter the name of your save.");
		inputS = input.nextLine();
		String saveName = inputS;
		String newLocation = location + saveName;

	}

	// TODO: Figure out how to print this all at once, but format.
	public static void printAbout() {
		println("Oracle is a program meant to manage a D&D style roleplaying game.");
		println("It can store maps and pictures, take care of stats and enemies, and save campaigns for later.");
		println("");
	}

	public static void println(String s) {
		System.out.println(s);
	}

	public static void print(String s) {
		System.out.print(s);
	}
}
