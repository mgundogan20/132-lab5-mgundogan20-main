package knighttour;

import knighttour.*;
import knighttour.knight.*;

public class KnightTour {
	protected Board board;
	protected Knight knight;
	protected int MAX_MOVES;
	
	public KnightTour() {
		this(8, "regular");
	}
	public KnightTour(int boardSize, String knightType) {
		board = new Board(boardSize);
		MAX_MOVES = 1 + (int) Math.pow(board.getSize(), 2);
		switch(knightType) {
		case "regular": 
			knight = new Knight(board);
			break;
		case "heuristic": 
			knight = new HeuristicKnight(board);
			break;
		case "random":
			knight = new RandomKnight(board);
			break;		
		}
	}
	
	protected int singleTour() {
		boolean cont = true;
		while(cont) {
			cont = knight.makeMove();
		}
			
		return board.getMoveCount();
	}
	
	public void tour() {
		singleTour();
	}
	public void printResult() {
		System.out.printf("The tour ended with %d moves.%n",board.getMoveCount());
        if(board.getMoveCount() == MAX_MOVES-1)
            System.out.printf("This was a full tour%n  ");
        else
            System.out.printf("This was not a full tour%n  ");

        //polish the printer

        for(int i=0; i<board.getSize() ;i++){
            System.out.printf("%5d", i);
        }
        System.out.printf("%n%n");

        int rowCount = 0;
        for(int[] row : board.getBoard()){
            System.out.printf("%2d", rowCount++);
            for(int cell : row) {
            	System.out.printf("%5d", cell);
            }
            System.out.println();
        }
	}
}











