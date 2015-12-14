package mastermind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
		System.out.println("####################################");
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
			startMenu();
			break;

		default:
			System.err.println("Invalid option! Exiting...");
			break;
		}

	}

	private static void playGameAgainstPlayer() {
		// TODO Auto-generated method stub
		
	}

	private static void playGameAgainstComputer() {
		// TODO Auto-generated method stub
		
	}

	private static void BuildAChampionshipOption() {
		// TODO Auto-generated method stub

	}

	private static void ShowHelp() {
		// TODO Auto-generated method stub

	}

	private static void ShowCredits() {
		// TODO Auto-generated method stub

	}
}