
public class GameBoard {
	
	private int boardSize;
	private char board[][];
	private Snake snake;
	private Food food;
	private int foodCount;
	
	GameBoard(int boardSize){
		this.boardSize = boardSize;
		snake = new Snake(boardSize);
		board = new char[boardSize][boardSize];
		food = new Food();
	}
	
	public int getFoodCount() {
		return foodCount;
	}
	
	public void generateFood() {
		this.food.generateNewFood(board);
	}
	
	public void defaultGameboard() {//used to initialize the board matrix after creation of the GameBoard object
		for(int i=0; i< boardSize; i++) {
			for(int j = 0;j <boardSize;j++) {
				board[i][j]= '.';
				
			}
		}
		for(int i= 1; i<snake.getxcor().size();i++) {
			board[snake.getxcor().get(i)][snake.getycor().get(i)]= '*';
		}
		board[snake.getxcor().get(0)][snake.getycor().get(0)]= snake.getDirection();
	}
	
	public void updateGameboard() {
		for(int i=0; i< boardSize; i++) {
			for(int j = 0;j <boardSize;j++) {
				board[i][j]= '.';
				
			}
		}
		board[food.getfoodCor()[0]][food.getfoodCor()[1]] = '@';
		
		for(int i= 1; i<snake.getxcor().size();i++) {
			board[snake.getxcor().get(i)][snake.getycor().get(i)]= '*';
		}
		board[snake.getxcor().get(0)][snake.getycor().get(0)]= snake.getDirection();
	}
	
	public void displayGameboard() {
		for(int i=0; i< boardSize; i++) {
			for(int j = 0;j <boardSize;j++) {
				System.out.print(board[i][j]);
				
			}
			System.out.println();
		}
	}
	
	public boolean handleInput(char ch) {
		if(ch=='>'||ch=='<'||ch=='^'||ch=='d') {
		if(!(ch=='>' && snake.getDirection()=='<') && !(ch=='<' && snake.getDirection()=='>')&& !(ch=='^' && snake.getDirection()=='d') && !(ch=='d' && snake.getDirection()=='^')) {
			snake.setDirection(ch);
		}
		}
		
		boolean foodEaten = snake.updateSnake(food.getfoodCor());
		
		if(snake.collisionWithSelf()) return false;
		else if (snake.collisionWithWall()) return false;
		
		updateGameboard();
		if(foodEaten) {
			foodCount++;
			generateFood();
			updateGameboard();
		}
		
		
		return true;
	}
}
