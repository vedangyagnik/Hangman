//Vedang Yagnik
//101285281
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("..................Welcome to Hangman..................");
		System.out.println("Please Select from below option:");
		System.out.println("1. New Game");
		System.out.println("2. Load Game");
		System.out.println("3. Rules");
		System.out.println("4. Exit");
		System.out.println("......................................................");
		
		Scanner input = new Scanner(System.in);
		int option = input.nextInt();
		switch (option) {
			case 1:
				System.out.println("Please select from below word categories:");
				System.out.println("1. Foods");
				System.out.println("2. Sports");
				System.out.println("3. Games");
				int category = input.nextInt();
				Game newGame = new Game(category, false);
				newGame.playGame();
				break;
			case 2:
				Game loadGame = new Game(0, true);
				loadGame.playGame();
				break;
			case 3:
				System.out.println("You will guess the word displayed on screen which will be randomly generated from your choosen category. There are 5 levels in the game and on each level difficulty of game will increase. You will be given 10 chances to guess the correct word. It is fun and exciting..keep playing... ;)");
				break;
	
			default:
				System.out.println("Thank you for playing...See you soon... ;)");
				break;
		}
	}	
}
