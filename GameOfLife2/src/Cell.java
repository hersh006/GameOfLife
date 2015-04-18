import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Cell {
	private int X;
	private int Y;
	private GameBoard Board;
	private List<Animal> Animals  = new ArrayList<Animal>();
	
	public Cell(int Xpos, int Ypos, GameBoard MyBoard){
		X = Xpos;
		Y = Ypos;
		Board = MyBoard;
	}
	
//	Default constructor sets x,y to 0,0
	public Cell () {
		this(0, 0, new GameBoard());
	}
	
	public int getX(){
		return X;
	}
	public void setX(int myX){
		X = myX;
	}
	
	public int getY(){
		return Y;
	}
	public void setY(int myY){
		Y = myY;
	}
	
	public void setGameBoard(GameBoard GB){
		Board = GB;
	}
	
	public void putAnimal(Animal creature){
		Animals.add(creature);
		creature.setCell(this);
	}
	public void removeAnimal(Animal creature){
		if (Animals.contains(creature)) {
			Animals.remove(creature);
			creature.setCell(null);
		}
	}
	public boolean hasAnimal(){
		return !Animals.isEmpty();
	}
	public Animal getAnimal(){
		return Animals.get(0);
	}
	
	public void resolveConflicts() {
		//If there are 2 animals in the same cell we gotta check for conflicts
		if (Animals.size() > 1) {
			List<Animal> wolves = new ArrayList<Animal>();
			List<Animal> sheeps = new ArrayList<Animal>();
			Iterator<Animal> AniIter = Animals.iterator();
			//Count the wolves and sheep
			while (AniIter.hasNext()) {
				Animal current = AniIter.next();
				if (current instanceof Wolf) {
					wolves.add(current);
				} else if (current instanceof Sheep) {
					sheeps.add(current);
				} else {
					boolean wereInTrouble = true;
				}
			}
			//If there are any wolves, that's not good for the sheep
			if (!wolves.isEmpty()){
				if (!sheeps.isEmpty()){
					Iterator<Animal> sheepIter = sheeps.iterator();
					while(sheepIter.hasNext()){
						//Get rid of all the sheep in the list
						Animal poorSheep = sheepIter.next();
						poorSheep.euthanize();
						Animals.remove(poorSheep);
					}
				}
			}
		}
	}
}
