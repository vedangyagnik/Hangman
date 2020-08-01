//Vedang Yagnik
//101285281
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Game {
	
	private int category;
	private boolean loadGame;
	
	public Game() {
		
	}
		
	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public boolean isLoadGame() {
		return loadGame;
	}

	public void setLoadGame(boolean loadGame) {
		this.loadGame = loadGame;
	}
	
	public Game(int category, boolean loadGame) {
		this.category = category;
		this.loadGame = loadGame;
	}
	
	/**
	 * Start a new Game or Load Game
	 */
	public void playGame() {
		int level = 1;
		int points = 10;
		String correctChars = "";
		String incorrectChars = "";
		String choosenChars = "";		
		String guessWord = "";
		Category cat = new Category();
		
		//Load Game
		if (this.loadGame == true) {
			Scanner fileInput = new Scanner(System.in);
			File getFileData = new File(fileInput.next());
			Scanner read;
			try {
				read = new Scanner(getFileData);
				level = Integer.parseInt(read.nextLine());
				choosenChars = read.next();
				correctChars = read.next();
				incorrectChars = read.next();
				read.nextLine();
				guessWord = read.nextLine();
				this.category = read.nextInt();
				points = read.nextInt();				
				System.out.println(guessWord);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		//Get random Words for selected categories
		String[] wordsList = cat.getCategory(this.category);		
		Random r = new Random();
		if(this.loadGame == false) {
			guessWord = wordsList[r.nextInt(5)];
		}
		
		//Loop until 5 level
		do{
			boolean wordMatch = false;
			if(this.loadGame == true) {
				displayHangman(points);
				System.out.println("Continue guessing below word using given letters.\n");
			} else {
				System.out.println("Guess below word using given letters.\n");
			}
			
			System.out.println("You have " + points + " chance(s) remaining\n");
			System.out.println("Points: " + points + "\t\tLevel: "+ level + "\n");
			
			System.out.println("Category: "+ cat.getCategoryName(this.category) + "\n");
			
			char[] guessWordArray = guessWord.toCharArray();
			//Print Guessed Word
			printGuessedWord(guessWordArray, correctChars);
			
			//Print Correct and Incorrect letters
			System.out.println("\n---------------------------------------------------");
			char alphabet[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
			System.out.print("Available Letters: ");
			for(int i=0; i<alphabet.length; i++) {
				if(choosenChars.toLowerCase().indexOf(Character.toLowerCase(alphabet[i])) != -1) {
					System.out.print("_ ");
				} else {
					System.out.print(alphabet[i] + " ");
				}
			}
			System.out.print("\nIncorrect Letters: ");
			for(char ch: incorrectChars.toUpperCase().toCharArray()){
				System.out.print(ch + " ");
			}
			System.out.println("\n---------------------------------------------------");
			
			
			Scanner input = new Scanner(System.in);
			System.out.println("Please choose one letter from above (Enter # to save the game and exit)");
			String choosenLetter = input.next();
			
			//Save Game
			if(choosenLetter.contentEquals("#")) {
				PrintWriter saveGame;
				try {
					saveGame = new PrintWriter("save_game_"+level+".txt");
					saveGame.println(level);
					saveGame.println(choosenChars);
					saveGame.println(correctChars);
					saveGame.println(incorrectChars);
					saveGame.println(guessWord);
					saveGame.println(this.category);
					saveGame.println(points);
					saveGame.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				System.out.println("Your game has been saved successfully...Exiting now");
				break;
			}

			if(choosenChars.toLowerCase().contains(choosenLetter.toLowerCase())) {
				System.out.println("Letter already guessed");
			} else {
				if(guessWord.toLowerCase().contains(choosenLetter.toLowerCase())) {
					System.out.println("Great...You have choosen the correct letter\n");
					correctChars += choosenLetter;
					choosenChars += choosenLetter;
				} else {
					points--;
					choosenChars += choosenLetter;
					incorrectChars += choosenLetter;
					if(points != 0) {
						System.out.println("Oops...You have choosen incorrect letter. Please try again.\n");
					}
				}
			}

			wordMatch = isWordCorrect(guessWord, correctChars);
			
			//Word matched then increase level and reset points
			if(wordMatch == true) {
				System.out.println("Great...You have guessed the correct word.");
				System.out.println("Word = " + guessWord);
				guessWord = wordsList[r.nextInt(5)];
				level++;
				points = 10;
				choosenChars = "";
				correctChars = "";
				incorrectChars = "";
				if (level==6) {
					System.out.println("You have won the game.");
				}
			}
			
			//Display Hangman
			displayHangman(points);
			
			if(points == 0) {
				System.out.println("Sorry...You lost the game. The word was: " + guessWord);
				break;
			}
		}while(level < 6);
	}

	/**
	 * Print Guessed Word
	 *
	 * @param guessWordArray
	 * @param correctChars
	 */
	private void printGuessedWord(char[] guessWordArray, String correctChars) {
		System.out.print("Word = \t");
		for(int i=0; i<guessWordArray.length; i++) {
			if(guessWordArray[i] == ' ') {
				System.out.print(" ");
			} else {
				if (correctChars.toLowerCase().indexOf(Character.toLowerCase(guessWordArray[i])) != -1) {
					System.out.print(guessWordArray[i] + " ");
				} else {
					System.out.print("_ ");
				}
			}
		}
	}
	
	/**
	 * Check word is correct or not
	 *
	 * @param guessWord
	 * @param correctChars
	 * @return boolean
	 */
	private boolean isWordCorrect(String guessWord, String correctChars) {
		char[] guessWordArray = guessWord.replace(" ", "").toCharArray();
		for(char ch : guessWordArray) {
			if(correctChars.toLowerCase().indexOf(Character.toLowerCase(ch)) == -1) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Display Hang-man based on points 
	 *
	 * @param points
	 */
	private void displayHangman(int points) {
		switch (points) {
			case 9:
				System.out.println("|            ");
				System.out.println("|            ");
				System.out.println("|            ");
				System.out.println("|            ");
				System.out.println("|            ");
				System.out.println("|            \n");
				break;
			case 8:
				System.out.println(" _______     ");
				System.out.println("|            ");
				System.out.println("|            ");
				System.out.println("|            ");
				System.out.println("|            ");
				System.out.println("|            ");
				System.out.println("|            \n");
				break;
			case 7:
				System.out.println(" _______     ");
				System.out.println("|/           ");
				System.out.println("|            ");
				System.out.println("|            ");
				System.out.println("|            ");
				System.out.println("|            ");
				System.out.println("|            \n");
				break;
			case 6:
				System.out.println(" _______     ");
				System.out.println("|/      |    ");
				System.out.println("|            ");
				System.out.println("|            ");
				System.out.println("|            ");
				System.out.println("|            ");
				System.out.println("|            \n");
				break;
			case 5:
				System.out.println(" _______     ");
				System.out.println("|       |    ");
				System.out.println("|       O    ");
				System.out.println("|            ");
				System.out.println("|            ");
				System.out.println("|            ");
				System.out.println("|            \n");
				break;		
			case 4:
				System.out.println(" _______     ");
				System.out.println("|       |    ");
				System.out.println("|       O    ");
				System.out.println("|      /     ");
				System.out.println("|            ");
				System.out.println("|            ");
				System.out.println("|            \n");
				break;
			case 3:
				System.out.println(" _______     ");
				System.out.println("|       |    ");
				System.out.println("|       O    ");
				System.out.println("|      /|    ");
				System.out.println("|            ");
				System.out.println("|            ");
				System.out.println("|            \n");
				break;
			case 2:
				System.out.println(" _______     ");
				System.out.println("|       |    ");
				System.out.println("|       O    ");
				System.out.println("|      /|\\  ");
				System.out.println("|            ");
				System.out.println("|            ");
				System.out.println("|            \n");
				break;
			case 1:
				System.out.println(" _______     ");
				System.out.println("|       |    ");
				System.out.println("|       O    ");
				System.out.println("|      /|\\  ");
				System.out.println("|      /     ");
				System.out.println("|            ");
				System.out.println("|            \n");
				break;
			case 0:
				System.out.println(" _______     ");
				System.out.println("|       |    ");
				System.out.println("|       O    ");
				System.out.println("|      /|\\  ");
				System.out.println("|      / \\  ");
				System.out.println("|            ");
				System.out.println("|            \n");
				break;
		}
	}
}
