package knighttour;
import java.util.Random;

public class BoardWithLetters extends Board{
	private int[][] letterBoard;
	private final char[] letters = {'C', 'O', 'M', 'P'};
	private final Random random = new Random();
	
	public BoardWithLetters(int size) {
		super(size);
		letterBoard = new int[size][size];
		for(int i=0; i<size ;i++) {
			for(int j=0; j<size ;j++) {
				letterBoard[i][j] = letters[random.nextInt(letters.length)];
			}
		}
	}
	
	public boolean checkCOMP(int row, int column) {
		String string = "";
		if(moveCount < 4)
			return false;
		for(int i=3; i>=0 ;i--) {
			string += (char) letterBoard[getMovePos(moveCount-i)[0]][getMovePos(moveCount-i)[1]];
		}
		if(string.equals("COMP")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private int[] getMovePos(int move) {
		for(int i=0; i<this.getSize() ;i++) {
			for(int j=0; j<this.getSize() ;j++) {
				if(this.getCell(i, j) == move) {
					int[] output = {i, j};
					return output;
				}
			}
		}
		
		return null;
	}
	
	public void printLetterBoard() {
		
        int rowCount = 0;
        for(int[] row : letterBoard){
            System.out.printf("%2d", rowCount++);
            for(int cell : row) {
            	System.out.printf("%5c", cell);
            }
            System.out.println();
        }
	}
	
	@Override
	public void addMove(int row, int col) {
		super.addMove(row, col);
		if(checkCOMP(0,0)) {
			System.out.printf("****COMP FOUND***** at move %d", moveCount);
			printBoard();
			System.out.printf("%n%n");
			printLetterBoard();
		}
	}

}
