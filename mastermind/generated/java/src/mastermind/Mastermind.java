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
			break;

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

		System.out.println("Press Enter To Return To Start Menu...");
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

		System.out.println("Press Enter To Return To Start Menu...");
		new Scanner(System.in).nextLine();

		startMenu();
	}

	@SuppressWarnings({ "unchecked", "resource" })
	private static void BuildAChampionshipOption() throws NumberFormatException, IOException {
		VDMSeq players = new VDMSeq();

		System.out.print("\n#Insert an even number of players: ");
		int numberOfPlayers = Integer.parseInt(br.readLine());

		for (int i = 0; i < numberOfPlayers; i++) {
			System.out.print("\n#Player " + (i + 1) + ", insert your name: ");
			players.add(new Player(br.readLine()));
		}

		Championship c = new Championship(players);

		for (int i = 0; i < c.GetNumberOfRounds().intValue(); i++) {
			VDMSeq games = c.CreateGames(c.GetCurrentPlayersOnChampionship());

			for (int j = 0; j < games.size(); j++) {
				Game g = (Game) games.get(j);
				System.out.println("\nGame " + (j + 1) + " - " + g.GetCodeMaker().GetName() + " VS "
						+ g.GetCodeBreaker().GetName());
				playChampionshipGame((Game) games.get(j));
			}

			c.PickAllWinnerPlayers(games);
			c.AddGames(games);
		}

		c.PrintStats();

		System.out.print("\n\n");

		System.out.println("Press Enter To Return To Start Menu...");
		new Scanner(System.in).nextLine();

		startMenu();
	}

	private static void playChampionshipGame(Game game) throws IOException {
		System.out.print("\n#" + game.GetCodeMaker().GetName()
				+ ", you are the Code Maker! Please, insert your code (example: 1,1,1,1): ");

		game.GetCodeMaker().SetKey(convertStringToKey(br.readLine()));

		System.out.print("\n#Let the game begins !\n\n");

		runGame(game);
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

	@SuppressWarnings("resource")
	private static void ShowHelp() throws IOException {
		System.out.println("\nMastermind or Master Mind is a code-breaking game for two players.");
		System.out.println("One player becomes the codemaker, the other the codebreaker.\n");

		System.out.println("The codemaker chooses a pattern of four code pegs. Duplicates are allowed,");
		System.out.println("so the player could even choose four code pegs of the same number. The chosen");
		System.out.println("pattern is placed in the four holes covered by the shield, visible to the");
		System.out.println("codemaker but not to the codebreaker.\n");

		System.out.println("The codebreaker tries to guess the pattern, in both order and value, within");
		System.out.println("ten turns.\n");
		
		System.out.println("The code pegs values available are 1, 2, 3, 4, 5 or 6.");
		
		System.out.print("\n\n");

		System.out.println("Press Enter To Return To Start Menu...");
		new Scanner(System.in).nextLine();

		startMenu();
	}

	@SuppressWarnings("resource")
	private static void ShowCredits() throws IOException {
		System.out.println("\nThis project was developed by ");
		System.out.println("\tHenrique Ferrolho");
		System.out.println("\tJoao Pereira");
		System.out.println("\tMario Macedo");
		System.out.println("using VDM++ on Overture IDE and later generated to JAVA.\n");

		System.out.println("Press Enter To Return To Start Menu...");
		new Scanner(System.in).nextLine();

		startMenu();
	}
}