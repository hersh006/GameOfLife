import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class GameOfLife {
	private List<Animal> Animals  = new ArrayList<Animal>();
	private GameBoard board;
	
	public GameOfLife(){
		Animals  = new ArrayList<Animal>();
		board = new GameBoard();
	}

    
    private void resolveConflicts() {
    	Iterator<Animal> AniIter = Animals.iterator();
    	while(AniIter.hasNext()){
    		Animal current = AniIter.next();
    		current.getCell().resolveConflicts();
    	}
    }
    
    private void endTurn(){
    	resolveConflicts();
    	board.endTurn();
    }
    
    public boolean test() {
    	boolean result = true;
		Wolf wolfie = new Wolf();
		Animals.add(wolfie);
		board.putAnimal(wolfie, 5, 5);
		endTurn();
		pause(1000);
		Sheep sheepie = new Sheep();
		Animals.add(sheepie);
		board.putAnimal(sheepie, 0, 0);
		endTurn();
		for (int turn = 1; turn < 15; turn++){
			pause(100);
			board.moveAnimal(sheepie, 0, 0 + turn);
			board.moveAnimal(wolfie, 5 + turn, 5);
			endTurn();
		}
		for (int turn = 1; turn < 30; turn++){
			pause(100);
			board.translateAnimal(sheepie, 0, 1);
			board.translateAnimal(wolfie, 1, 1);
			endTurn();
		}
		for (int turn = 1; turn < 30; turn++){
			pause(100);
			board.translateAnimal(sheepie, 1, -1);
			board.translateAnimal(wolfie, -1, -1);
			endTurn();
		}    	
    	return result;
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GameOfLife game = new GameOfLife();
		game.test();
	}
	
	private static void pause(int milliseconds){
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
