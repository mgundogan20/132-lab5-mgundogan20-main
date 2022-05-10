package knighttour;

public class FullKnightTour extends KnightTour{
	private int[] moveTotals;
	final int MAX_TOUR_ALLOWED = 200000;
	
	public FullKnightTour(int boardSize, String knightType) {
		super(boardSize, knightType);
		moveTotals = new int[MAX_MOVES];
	}
	
	@Override
	public void tour() {
		fullTour();
	}
	@Override
	public void printResult() {
		int totalTries = 0;
		
		System.out.printf("# tours having # moves%n%n");
    	for(int i=1; i<MAX_MOVES ;i++) {
            System.out.printf("%-10d%-3d%n",moveTotals[i], i);
            totalTries += moveTotals[i];
        }
    	if(moveTotals[moveTotals.length -1] == 0)
    		System.out.printf("%nThere was no full tours in %d tries.%n", totalTries);
    	else
    		System.out.printf("%nIt took %d tries to get a full tour%n", totalTries);
	}
	
	private void fullTour() {
		int tours = 0;
		while(moveTotals[moveTotals.length - 1] == 0) {
			tours++;
			moveTotals[super.singleTour()]++;
			knight.reset(); //this also resets the board
			if(tours == MAX_TOUR_ALLOWED) {
				break;
			}
		}
	}
}
