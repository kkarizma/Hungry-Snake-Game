import java.util.*;


public class Game {// brings together the functionalities of all classes
	
	public static void displayRules() { //displays basic instructions of the game
		System.out.println("Welcome to Hungry Snake Game!");
		System.out.println("The controls of the game:\nEnter '<' for moving your snake LEFT\nEnter '>' for moving your snake RIGHT\nEnter '^' for moving your snake UP"
				+ "\nEnter 'd' for moving your snake DOWN\n");
		System.out.println("Available board sizes are 10X10, 15X15, 20X20");
		System.out.println("For each board sizes, we have three difficulty levels available namely, Easy, Medium and Hard\n");
		System.out.println("Representation of the Game Board: @ - food\n"
				+ "^ * * * - snake position ( '^' means front of snake )\n'^' is used for showing that default movement will be upwards,\n'<' is be used for left default movement,\n'>' can be used for right default movements, "
				+ "\n'd' can be used for default downwards movements\nThe rest of the game board is represented by '.'");
		System.out.println("\nLet me tell you a secret!\n"
				+ "Beyond the edges of the gameboard you see, lies an invisible wall hitting which will kill your snake. So be careful!\n");
	}


	public static boolean validGameLevel(char c) {//checks if inputted difficulty level is valid
		if (c=='e'|| c=='m'||c=='h')
			return true;
		return false;
	}

	public static int getFoodPoint(char c) {// returns the score points for each food unit consumed on the basis of difficulty level
		if (c=='e') return 5;  //user gets 5 points for each food eaten in easy level
		else if(c=='m') return 10;  //user gets 10 points for each food eaten in easy level
		return 15;   //user gets 15 points for each food eaten in easy level
	}

	public static int getTime(char c) {// returns the the time allowed for user input on the basis of difficulty level
		if (c=='e') return 10; //user gets 10 seconds to input move in easy level
		else if(c=='m') return 7;  //user gets 7 seconds to input move in medium level
		else return 4;  //user gets 4 seconds to input move in hard level
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimedScanner scanner = new TimedScanner(); // to accept input
		boolean programRunning = true;
		char input;
		String temp;
		String username;
		String password;
		int boardSize=10;
		int time = 10;
		Score10 score10 = new Score10();
		Score15 score15 = new Score15();
		Score20 score20 = new Score20();

		int foodPoint = 5;
		char level='e';
		Player currentPlayer = null;
		LinkedList<Player> userDetails = new LinkedList<>(); //no upper limit for number of players that can login our game
		boolean gotostart = false;
		Game.displayRules(); //displays rules

		bb:
			while(programRunning) {

				gotostart = false;
				System.out.println("ENTER 'l' to LOGIN\nENTER 's' to SIGN UP\nENTER 'e' to END GAMING\nENTER 'w' to SHOW-LEADERBOARD"); //switch case is run over these choices
				temp = scanner.nextLine(-1);
				input = temp.trim().toLowerCase().charAt(0);
				switch(input) {
				case 'l': {
					System.out.println("Enter UserName: ");
					username = scanner.nextLine(-1);
					currentPlayer = Player.userExistence(userDetails, username);
					if(!(currentPlayer == null)) {
						System.out.println("Enter Password: ");
						if(scanner.nextLine(-1).equals(currentPlayer.getPassword()))
						{}
						else {gotostart = true;
						System.out.println("Invalid password!");}
					}
					else {
						System.out.println("User doesn't exist! Try Signing up!");
						gotostart=true;
					}
					break;}
				case 's': {
					System.out.println("Enter UserName: ");
					username = scanner.nextLine(-1);
					currentPlayer = Player.userExistence(userDetails, username);
					if(currentPlayer == null) {
						System.out.println("Enter new Password: ");
						password = scanner.nextLine(-1);
						currentPlayer= new Player();
						currentPlayer.setName(username);
						currentPlayer.setPassword(password);
						userDetails.add(currentPlayer);
					}
					else {
						System.out.println("SignUp unsuccessful! User Already Exists!");
						gotostart=true;
					}
					break;}
				case 'e': {
					System.out.println("Exitting Game. Thanks for playing!");
					programRunning = false;
					break;
				}

				case 'w': {
					gotostart = true;
					while(true) {
						System.out.println("Enter the type of leaderboard you want to see:\n'a' for 10x10\n'b' for 15x15\n'c' for 20x20");
						input = scanner.nextLine(-1).trim().toLowerCase().charAt(0);
						if(input =='a'||input=='b'||input=='c') {
							break;
						}
						else System.out.println("Invalid Choice! Try Again!");
					}
					
					System.out.print("Scoreboard for boardsize: ");
					if(input=='a') System.out.println("10");
					else if (input=='b') System.out.println("15");
					else System.out.println("20");
					System.out.println("Name\t\tScore");
					System.out.println("----\t\t-----");
					switch(input) {
					case 'a':{
						Collections.sort(userDetails, score10);
						for(int i = 0; i<userDetails.size(); i++) {
							System.out.println(userDetails.get(i).getName()+"\t\t"+userDetails.get(i).getScore10());
						}
						break;
					}
					case 'b':{
						Collections.sort(userDetails, score15);
						for(int i = 0; i<userDetails.size(); i++) {
							System.out.println(userDetails.get(i).getName()+"\t\t"+userDetails.get(i).getScore15());
						}
						break;
					}

					case 'c':{
						Collections.sort(userDetails, score20);
						for(int i = 0; i<userDetails.size(); i++) {
							System.out.println(userDetails.get(i).getName()+"\t\t"+userDetails.get(i).getScore20());
						}
						break;
					}



					}
					break;	
				}

				default: {
					System.out.println("Invalid input! Try Again!");
					gotostart = true;
				}


				}

				if(gotostart) continue;

				if(programRunning) {


					while(true) {
						System.out.println("ENTER 'l' to LOGOUT\nENTER 'p' to PLAY A GAME");  // Now after a successful login/signup user gets a choice to logout or play a game
						temp = scanner.nextLine(-1);
						input = temp.trim().toLowerCase().charAt(0);
						switch(input){
						case 'l': {
							System.out.println("LogOut successful!");continue bb;
						}
						case 'p':{

							boolean validBoardSize = false;
							while(!validBoardSize) {
								System.out.println("Available board sizes: 10, 15, 20\nEnter board size:"); //if the user decides to play a game, he must enter a valid board size and difficulty levels
								temp = scanner.nextLine(-1);
								String inputBoardSize = temp.trim();
								if(inputBoardSize.equals("10") || inputBoardSize.equals("15") || inputBoardSize.equals("20")) {
									boardSize = Integer.parseInt(inputBoardSize);
									validBoardSize = true;
									boolean validLevel = false;
									while(!validLevel) {
										System.out.println("Avalilable levels: EASY, MEDIUM, HARD\nENTER 'e' for EASY\nENTER 'm' for MEDIUM\nENTER 'h' for HARD");
										temp = scanner.nextLine(-1);
										input = temp.trim().toLowerCase().charAt(0);
										if(Game.validGameLevel(input)) {
											validLevel = true;
											level = input;
										}
										else System.out.println("Invalid Level! Try Again!");

									}
								}
								else System.out.println("Invalid BoardSize! Try Again!");
							}
							GameBoard b = new GameBoard(boardSize); // Now an object of Gameboard class is created with the inputted boardsize
							time = Game.getTime(level); //this is the time player will get to input the directions, based on the difficulty level selected
							foodPoint = Game.getFoodPoint(level);//this is the points that the player will get after eating 1 food unit based on the difficulty level
							b.defaultGameboard(); //default gameboard is generated
							b.generateFood(); //food is placed on a random location on the gameboard
							boolean continueGame = true;

							while(continueGame)
							{
								b.updateGameboard();  //gameboard is updated and displayed at every iteration 
								b.displayGameboard();


								System.out.println("Enter your move! (Mind the timer.)");
								String n = scanner.nextLine(time*1000); //user is given the designated time to input direction
								if (n == null)
								{
									System.out.println("you were too slow!"); //if the user takes longer than the designated time then snake moves in default direction
									continueGame = b.handleInput('n');
								}
								else
								{
									if(n.length()>0) {
										continueGame = b.handleInput(n.charAt(0));
									}
									else continueGame = b.handleInput('n');
								}}
							//if at any point, handleInput() returns  false due to collision with wall or self, the game gets over
							System.out.println("GAMEOVER!");
							System.out.println("Food Eaten: "+b.getFoodCount());// Food count and score is printed after the game gets over
							if(currentPlayer != null) currentPlayer.updateScore(boardSize, foodPoint*b.getFoodCount());
							System.out.println("Points Gained: "+foodPoint*b.getFoodCount());
							break;

						}
						default : System.out.println("Invalid choice! Try Again");
						}
					}


				}



			}}




}



