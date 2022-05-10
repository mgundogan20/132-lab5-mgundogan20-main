package knighttour;
// subclass of knight tour
public class FixedKnightTour extends KnightTour{
	private int[] moveTotals;
	private static final int NUMBER_OF_TOURS = 1000;
	private int MAX_MOVES;
	
	public FixedKnightTour(int BoardSize, String knightType) {
		super(BoardSize, knightType);
		MAX_MOVES = 1+ (int) Math.pow(board.getSize(), 2);
		moveTotals = new int[MAX_MOVES];
	}
	
	private void fixedTour() {
		for(int i=0; i<NUMBER_OF_TOURS ;i++) {
			moveTotals[super.singleTour()]++;
			knight.reset(); //this also resets the board
		}
	}
	
	@Override
	public void tour() {
		fixedTour();
	}
	
	@Override
	public void printResult() {
		System.out.println("#tours   tour length");
    	for(int i=1; i<MAX_MOVES ;i++) {
            System.out.printf("%-10d%-3d%n", moveTotals[i], i);
    	}
	}

}
