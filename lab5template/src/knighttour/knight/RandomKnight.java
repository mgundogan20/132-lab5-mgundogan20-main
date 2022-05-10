package knighttour.knight;

import knighttour.Board;

//subclass of knight
public class RandomKnight extends Knight {

	public RandomKnight(Board board) {
		super(board);
	}
	
	@Override
	public boolean makeMove() {
		int randIdx = super.random.nextInt(8);
		for(int i=0; i<8 ;i++) {
			int tarHor = getCurrentCol() + horizontal[(i+randIdx)%8];
			int tarVer = getCurrentRow() + vertical[(i+randIdx)%8];
			if(board.isValid(tarVer, tarHor)) {
				this.setCurrentRow(tarVer);
				this.setCurrentCol(tarHor);
				board.addMove(tarVer, tarHor);
				
				return true;
			}
		}
		return false;
	}
	

}
