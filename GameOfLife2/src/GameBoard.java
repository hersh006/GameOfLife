import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class GameBoard {
	private int xDim = 41;
	private int yDim = 25;
	
    Grid grid;

    public GameBoard() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex){
        } catch (IllegalAccessException ex) {
        } catch (UnsupportedLookAndFeelException ex) {
        }

        grid = new Grid(this, xDim, yDim);
        JFrame window = new JFrame();
        window.setSize(xDim * 10 + 40, yDim * 10 + 80);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(grid);
        window.setVisible(true);
    }
    
    //Put a new animal down. By default we won't put a new animal down if there is already one in that Cell.
    public boolean putAnimal(Animal newAnimal, int X, int Y) {
    	return putAnimal(newAnimal, X, Y, false);
    }
    
    //This function still puts an animal down even if there is already in that cell
    public boolean putAnimal(Animal newAnimal, int X, int Y, boolean allowOverlap) {
    	boolean success = false;
    	if (isXYValid(X, Y)){
	    	if (!grid.getCellGrid()[X][Y].hasAnimal() || allowOverlap) {
	    		grid.getCellGrid()[X][Y].putAnimal(newAnimal);
	    		success = true;
	    	}
    	}
    	return success;
    }
    
    //Move the animal to a new absolute location
    public boolean moveAnimal(Animal oldAnimal, int X, int Y){
    	boolean success = false;
    	oldAnimal.getCell().removeAnimal(oldAnimal);
    	success = putAnimal(oldAnimal, X, Y, true);
    	return success;
    }
    
    //Move the animal but instead of taking absolute coordinates it moves the animal
    // relative to it's starting point
    public boolean translateAnimal(Animal oldAnimal, int X, int Y){
    	boolean success = false;
    	int oldX = oldAnimal.getCell().getX();
    	int oldY = oldAnimal.getCell().getY();
    	int newX = oldX + X;
    	int newY = oldY + Y;
    	if (newX < 0) {
    		newX = 0;
    	} else if (newX >= xDim){
    		newX = xDim-1;
    	}
    	if (newY < 0) {
    		newY = 0;
    	} else if (newY >= yDim){
    		newY = yDim-1;
    	}
    	oldAnimal.getCell().removeAnimal(oldAnimal);
    	success = putAnimal(oldAnimal, newX, newY, true);
    	return success;
    }
    
    private boolean isXValid(int X){
    	return ((X >= 0) && (X <= xDim));
    }
    
    private boolean isYValid(int Y){
    	return ((Y >= 0) && (Y <= xDim));
    }
    
    private boolean isXYValid(int X, int Y){
    	return isXValid(X) && isYValid(Y);
    }
    
    public void endTurn(){
    	grid.repaint();
    }
}
