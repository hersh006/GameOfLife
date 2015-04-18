import java.awt.Color;

public class Wolf extends Animal {
	public Wolf(Cell myCell) {
		super(myCell);
	}

	public Wolf() {
		super();
		color = Color.RED;
	}
}
