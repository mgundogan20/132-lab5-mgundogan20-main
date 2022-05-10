package knighttour.knight;

import knighttour.Board;

//subclass of knight
public class HeuristicKnight extends Knight {
	
	private int[][] access =
			  {{2, 3, 4, 4, 4, 4, 3, 2},
			   {3, 4, 6, 6, 6, 6, 4, 3},
			   {4, 6, 8, 8, 8, 8, 6, 4},
			   {4, 6, 8, 8, 8, 8, 6, 4},
			   {4, 6, 8, 8, 8, 8, 6, 4},
			   {4, 6, 8, 8, 8, 8, 6, 4},
			   {3, 4, 6, 6, 6, 6, 4, 3},
			   {2, 3, 4, 4, 4, 4, 3, 2}};

	public HeuristicKnight(Board board) {
		super(board);
	}

	private boolean isReachable(int curRow, int curCol, int moveNumber) {

        int horizontal = super.horizontal[moveNumber];
        int vertical = super.vertical[moveNumber];

        int tarCol = horizontal + curCol;
        int tarRow = vertical + curRow;

        
        if((tarCol >= 0 && tarCol <= board.getSize()-1) && (tarRow >= 0 && tarRow <= board.getSize()-1)){
        	if(board.getCell(tarRow, tarCol) == 0)
                return true;
        	else                
                return false;
        }
        else
        	return false;
    }
	
	private int[] move(int moveNumber){
        //moves the knight by horizontal[index], vertical[index] if possible
        //returns an array of length 2
        //this either contains -1, -1 for an invalid move
        //or the coordinates of the target location

        int[] output = new int[2]; 
        int horizontal = super.horizontal[moveNumber];
        int vertical = super.vertical[moveNumber];

        int tarCol = horizontal + getCurrentCol();
        int tarRow = vertical + getCurrentRow();

        
        if((tarCol >= 0 && tarCol <= board.getSize()-1) && (tarRow >= 0 && tarRow <= board.getSize()-1)){
        	if(board.getCell(tarRow, tarCol) != 0){
                output[0] = -1;
                output[1] = -1;

                return output;
            }
        	else {
        		output[0] = tarRow;
                output[1] = tarCol;
                
                return output;
        	}
        }
        else{
            output[0] = -1;
            output[1] = -1;
            
            return output;
        }
    }
	
	private int[][] heuristicGen(){
    	int[][] heurTable = new int[board.getSize()][board.getSize()];
    	
    	for(int i=0; i<board.getSize() ;i++) {
    		for(int j=0; j<board.getSize() ;j++) {
    			for(int moveNum=0; moveNum<8 ;moveNum++) {
    				if(isReachable(i, j, moveNum)) {
    					heurTable[i][j]++;
    				}
    			}
    		}
    	}
    	
    	return heurTable;
    }
	
	@Override
	public boolean makeMove(){

        //loops through the moves of a knight, moves the knight to the first detected valid location
        //if there is a valid move, singleTourIncrement moves the knight and returns true
        //if not, it returns false
		access = heuristicGen();
		
    	int min = 64;
    	int minRow = -1;
    	int minCol = -1;
        for(int i=0; i<8 ;i++){
            int[] move = this.move(i); //returns -1, -1 if the move is not possible
            if(move[0] != -1) {
	            if(access[move[0]][move[1]] < min) {
	            	//transferring priority to the less accessible cell
	            	min = access[move[0]][move[1]];
	            	minRow = move[0];
	            	minCol = move[1];
	            }
            }
        }
        
        if(minRow != -1){ //checks whether the value of minRow has been updated
            setCurrentRow(minRow);
            setCurrentCol(minCol);
            board.addMove(minRow, minCol);
            return true;
        }

        return false;
    }
    
	@Override
	public void reset() {
		super.reset();
		access = new int[][]
			  {{2, 3, 4, 4, 4, 4, 3, 2},
			   {3, 4, 6, 6, 6, 6, 4, 3},
			   {4, 6, 8, 8, 8, 8, 6, 4},
			   {4, 6, 8, 8, 8, 8, 6, 4},
			   {4, 6, 8, 8, 8, 8, 6, 4},
			   {4, 6, 8, 8, 8, 8, 6, 4},
			   {3, 4, 6, 6, 6, 6, 4, 3},
			   {2, 3, 4, 4, 4, 4, 3, 2}};
	}
}
