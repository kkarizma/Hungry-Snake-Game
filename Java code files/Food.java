import java.util.Random;
public class Food {
	private int foodCor[];
	Food(){
		foodCor = new int[2];
	}

	public int[] getfoodCor() {
		return foodCor;
	}
	public void generateNewFood(char[][] board) {
		int randxcor, randycor;
		while(true) {
			int boardSize = board.length;
			Random rand = new Random();
			randxcor= rand.nextInt(boardSize);
			randycor = rand.nextInt(boardSize);
			if (board[randxcor][randycor]=='.')
				break;
		}
		foodCor[0]= randxcor;
		foodCor[1] = randycor;
	}
}
