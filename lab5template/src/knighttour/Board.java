package knighttour;

public class Board {
	protected int [][] chessBoard;
	protected int moveCount;
	protected int size;
	
	public Board(int size) {
		this.size = size;
		chessBoard = new int[size][size];
		moveCount = 0;
	}
	public Board() {
		this(8);
	}
	
	public int getMoveCount() {
		return moveCount;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getCell(int row, int col) {
		return chessBoard[row][col];
	}
	
	public int[][]getBoard(){
		return chessBoard;
	}
	
	public boolean isValid(int row, int column) {
		
		if(row < 0 || column < 0 || row >= this.getSize() || column >= this.getSize())
			return false;
		else if(this.chessBoard[row][column] != 0)
			return false;
		else 
			return true;
	}
	
	public void addMove(int row, int col) {
		if(this.isValid(row, col)) {
			this.moveCount++;
			this.chessBoard[row][col] = this.moveCount;
		}
	}
	
	public void clear() {
		this.chessBoard = new int[size][size];
		this.moveCount = 0;
	}
	
	public boolean isFull() {
		if(this.getMoveCount() == Math.pow(size, 2)) {
			return true;
		}
		else
			return false;
	}
	
	public void printBoard() {
		System.out.printf("%n  ");
		for(int i=0; i<size ;i++){
            System.out.printf("%5d", i);
        }
        System.out.printf("%n%n");

        int rowCount = 0;
        for(int[] row : chessBoard){
            System.out.printf("%2d", rowCount++);
            for(int cell : row) {
            	System.out.printf("%5d", cell);
            }
            System.out.println();
        }
	}
}
