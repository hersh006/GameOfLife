import java.awt.Color;

public abstract class Animal {
	private GameBoard Board;
	private Cell CurrentCell;
	private boolean Dead;
	protected Color color = Color.white;
	
	public Animal(Cell myCell) {
		CurrentCell = myCell;
		color = Color.white;
		Dead = false;
	}
	public Animal() {
		
	}
	public Color getColor(){
		return color;
	}
	public Cell getCell(){
		return CurrentCell;
	}
	public void setCell(Cell newCell){
		CurrentCell = newCell;
	}
	public void euthanize(){
		Dead = true;
		color = Color.GRAY;
	}
	public boolean isAlive(){
		return !Dead;
	}
}
