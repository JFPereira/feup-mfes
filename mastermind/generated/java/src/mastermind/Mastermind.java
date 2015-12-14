package mastermind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.overture.codegen.runtime.VDMSeq;

public class Mastermind {

	public static boolean validOption = false;
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		startMenu();
	}

	public static void startMenu() throws IOException {
		System.out.println("####################################");
		System.out.println("####################################");
		System.out.println("#######                      #######");
		System.out.println("#######                      #######");
		System.out.println("#######      MasterMind      #######");
		System.out.println("#######                      #######");
		System.out.println("#######                      #######");
		System.out.println("####################################");
		System.out.println("####################################\n");

		System.out.println("\n#Options");
		System.out.println("  1. Play a game");
		System.out.println("  2. Build a Championship");
		System.out.println("  3. Help");
		System.out.println("  4. Credits");

		int option = insertOption();

		switch (option) {
		case 1:
			PlayAGameOption();
			break;

		case 2:
			BuildAChampionshipOption();
			break;

		case 3:
			ShowHelp();
			break;

		case 4:
			ShowCredits();

		default:
			System.err.println("Invalid option! Exiting...");
			break;
		}
	}

	public static int insertOption() throws IOException {
		int option = 0;

		System.out.print("Enter option: ");

		try {
			option = Integer.parseInt(br.readLine());
		} catch (NumberFormatException ex) {
		}

		return option;
	}

	public static void PlayAGameOption() throws IOException {
		System.out.println("\n####################################");
		System.out.println("####################################");
		System.out.println("#######                      #######");
		System.out.println("#######      MasterMind      #######");
		System.out.println("#######                      #######");
		System.out.println("#######   Play a Game Menu   #######");
		System.out.println("#######                      #######");
		System.out.println("####################################");
		System.out.println("####################################\n");

		System.out.println("\n#Options");
		System.out.println("  1. vs COM");
		System.out.println("  2. vs Player");
		System.out.println("  3. Back");

		int option = insertOption();

		switch (option) {
		case 1:
			playGameAgainstComputer();
			break;

		case 2:
			playGameAgainstPlayer();
			break;

		case 3:
			System.out.print("\n");
			startMenu();
			break;

		default:
			System.err.println("Invalid option! Exiting...");
			break;
		}

	}

	@SuppressWarnings("resource")
	private static void playGameAgainstComputer() throws IOException {
		String name = new String();

		System.out.print("\n#Insert your name: ");
		name = br.readLine();

		System.out.print("\n#Hello " + name + "! Let's start the game :) !\n\n");

		Player codeMaker = new Player("Computer", Player.GenerateRandomlyKey());
		Player codeBreaker = new Player(name);

		Game game = new Game(codeMaker, codeBreaker);

		runGame(game);

		System.out.print("\n\n");
		
		System.out.println("Press Any Key To Continue...");
        new Scanner(System.in).nextLine();

		startMenu();
	}

	@SuppressWarnings("resource")
	private static void playGameAgainstPlayer() throws IOException {
		String makerName = new String(), breakerName = new String();
		VDMSeq makerCode = new VDMSeq();

		System.out.print("\n#Code Maker, insert your name: ");
		makerName = br.readLine();

		System.out.print("\n#Hello " + makerName + "! Now insert your code (example: 1,1,1,1): ");
		makerCode = convertStringToKey(br.readLine());
		
		System.out.print("\n#Code Breaker, insert your name: ");
		breakerName = br.readLine();
		
		System.out.print("\n#Hello " + breakerName + "! Let's start the game :) !\n\n");
		
		Player codeMaker = new Player(makerName, makerCode);
		Player codeBreaker = new Player(breakerName);

		Game game = new Game(codeMaker, codeBreaker);

		runGame(game);

		System.out.print("\n\n");

		System.out.println("Press Any Key To Continue...");
        new Scanner(System.in).nextLine();
		
		startMenu();
	}

	private static void BuildAChampionshipOption() {
		// TODO Auto-generated method stub

	}

	private static void runGame(Game game) throws IOException {
		String str = new String();

		while (!game.IsFinished()) {
			System.out.print("#" + game.GetCodeBreaker().GetName() + ", insert your code (example: 1,1,1,1): ");
			str = br.readLine();

			game.MakeMove(convertStringToKey(str));
		}
	}

	@SuppressWarnings("unchecked")
	private static VDMSeq convertStringToKey(String str) {
		VDMSeq key = new VDMSeq();
		String[] elems = str.split(",");

		for (int i = 0; i < elems.length; i++)
			key.add(Long.parseLong(elems[i]));

		return key;
	}

	private static void ShowHelp() {
		// TODO Auto-generated method stub

	}

	private static void ShowCredits() {
		// TODO Auto-generated method stub

	}
}