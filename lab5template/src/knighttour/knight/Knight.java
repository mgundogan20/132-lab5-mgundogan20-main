package knighttour.knight;
import knighttour.Board;
import java.util.Random;

public class Knight {
	protected int currentRow;
	protected int currentColumn;
	protected Board board;
	
	protected static final int[] horizontal = {2, 1, -1, -2, -2, -1, 1, 2};
	protected static final int[] vertical = {-1, -2, -2, -1, 1, 2, 2, 1};
	protected static final Random random = new Random();
	
	public Knight(Board board) {
		board.clear();
		this.board = board;
		this.currentRow = random.nextInt(board.getSize());
		this.currentColumn = random.nextInt(board.getSize());
		board.addMove(currentRow, currentColumn);
	}
	public int getCurrentCol(){
        return currentColumn;
    }
    
    public int getCurrentRow(){
        return currentRow;
    }

    public Board getBoard() {
    	return board;
    }
    
    protected void setCurrentCol(int currentCol){
        this.currentColumn = currentCol;
    }

    protected void setCurrentRow(int currentRow){
        this.currentRow = currentRow;
    }
	
    
	public void reset() {
		currentRow = random.nextInt(board.getSize());
		currentColumn = random.nextInt(board.getSize());
		board.clear();
		board.addMove(currentRow, currentColumn);
	}
	
	public boolean makeMove() {
		for(int i=0; i<8 ;i++) {
			int tarHor = getCurrentCol() + horizontal[i];
			int tarVer = getCurrentRow() + vertical[i];
			if(board.isValid(tarVer, tarHor)) {
				setCurrentRow(tarVer);
				setCurrentCol(tarHor);
				board.addMove(tarVer, tarHor);
				
				return true;
			}
		}
		
		return false;
	}
}
