import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Grid extends JPanel {

    private Cell[][] CellGrid;
    private int xDim;
    private int yDim;
    
    public Grid(GameBoard GB, int xDimension, int yDimension) {
    	xDim = xDimension;
    	yDim = yDimension;
    	CellGrid = new Cell[xDim][yDim];
        //Tell each cell it's position
        for (int x = 0; x < xDim; x++){
            for (int y = 0; y < yDim; y++){
            	CellGrid[x][y] = new Cell(x,y,GB);
            }
        }
    }
    
    public Cell[][] getCellGrid(){
    	return CellGrid;
    }        
    public void setCellGrid(Cell[][] CG){
    	CellGrid = CG;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //First Draw the grid
        g.setColor(Color.BLACK);
        g.drawRect(10, 10, xDim * 10, yDim * 10);

        for (int i = 10; i <= xDim * 10; i += 10) {
            g.drawLine(i, 10, i, yDim * 10 + 10);
        }

        for (int i = 10; i <= yDim * 10; i += 10) {
            g.drawLine(10, i, xDim * 10 + 10, i);
        }
        //Now add the animal positions
        for (int x = 0; x < xDim; x++){
            for (int y = 0; y < yDim; y++){
                if (CellGrid[x][y].hasAnimal()){
                    int cellX = 10 + (CellGrid[x][y].getX() * 10);
                    int cellY = 10 + (CellGrid[x][y].getY() * 10);
                	g.setColor(CellGrid[x][y].getAnimal().getColor());
                    g.fillRect(cellX, cellY, 10, 10);
                }
            }
        }
    }
}