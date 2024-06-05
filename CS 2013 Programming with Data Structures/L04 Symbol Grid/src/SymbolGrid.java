/*
Noelani Mishina Hinh
Rodrigo Valdez
CS 2013
3/14/2024
 */

import java.util.LinkedList;

public class SymbolGrid {

	private static char[] SYMBOLS = {'~', '!', '@', '#', '$', '^', '&', '*'};



	// NOTE:
	//   Do not change this method signature.
	//   This method calls another recursive method, but it should
	//   not call itself.
	public static void findAllPaths(Grid grid, char[] sequence) {
		// TO DO:
		//     Add code to traverse the grid and search for paths
		//     starting at each cell using the findPathsAt() method.
		//
		//
		//Class Notes: Check each cell for starting symbol. If there is a match,
		// output. If there is no match, return.
		//Maybe use Cell.equals?


		//Loop through all rows
		for (int r = 0; r < grid.getSize(); r++) {
			for (int c = 0; c < grid.getSize(); c++) {
				findPathsAt(grid, new Cell(r, c), new Path(), sequence, 0);
			}
		}
		System.out.println("\n--- finished searching");
	}

	// TO DO:
	//     Implement recursive method with backtracking
	//
	// NOTE:
	//   You may change the list of parameters here, but before you do
	//   check out all the helpful helper methods in Grid, Cell and Path
	//   below.
	//Class Notes: For each cell that matches first in sequence, check
	//neighbors for next value in string, then so on. If there are no
	//matching values, move on to next starting cell that matches first in
	//sequence. If the values match, print the row and column and search
	//for next value in sequence. When the end is reached, print the whole
	//sequence. Need to make sure that the path doesn't go back to the previous
	//cell.
	private static void findPathsAt(Grid grid,
									Cell here,
									Path currentPath,
			                        char[] sequence,
									int n) {

		// Prints out the currentPath when a valid path has been found.





		//Class Notes:
		//grid[r][c] == sequence[path.getLength()]
		//grid[cell.r][grid.c] != sequence[path.getLength()]
		//Should return from findPathsAt() if r, c, is outside grid, r, c
		//is already on path, symbol at r, c is not what you are looking
		//for at moment

		//if current cell is on the grid and if the current path does not contain the current cell
		if (grid.isOnGrid(here) && !currentPath.contains(here)) {
			// grab the current symbol on the cell and store in "symbol"
			char symbol = grid.getSymbolAt(here);
			// if "symbol" equals the current sequence symbol we are on. then add the symbol to the cell.
			if (!currentPath.contains(here) && symbol == sequence[currentPath.getLength()]) {
				currentPath.add(here, symbol);
				if (currentPath.getLength() == sequence.length) {
					currentPath.display();
				}
				else {
					//Class Notes: Need 8 recursive functions (vertical, horizontal, diagonal)
						findPathsAt(grid, new Cell(here.r - 1, here.c), currentPath, sequence, n + 1);        //North
						findPathsAt(grid, new Cell(here.r + 1, here.c), currentPath, sequence, n + 1);        //South
						findPathsAt(grid, new Cell(here.r, here.c - 1), currentPath, sequence, n + 1);        //West
						findPathsAt(grid, new Cell(here.r, here.c + 1), currentPath, sequence, n + 1);        //East
						findPathsAt(grid, new Cell(here.r - 1, here.c - 1), currentPath, sequence, n + 1); //Northwest
						findPathsAt(grid, new Cell(here.r - 1, here.c + 1), currentPath, sequence, n + 1); //Northeast
						findPathsAt(grid, new Cell(here.r + 1, here.c - 1), currentPath, sequence, n + 1); //Southwest
						findPathsAt(grid, new Cell(here.r + 1, here.c + 1), currentPath, sequence, n + 1); //Southeast
				}
				currentPath.removeLast();
			}
		}
	}
	public static void main(String[] args) {
		// NOTE:
		//   You may modify this to let the user choose grid size.
		Grid grid = new Grid(7, SYMBOLS);
		grid.display();

		System.out.println();

		// NOTE:
		//   You may modify this to let the use choose length of
		//   the sequence and/or to enter the sequence they want
		//   the program to find.
		char[] seq = randomSymbolSequence(4);
		System.out.print("sequence: ");
		System.out.println(seq);

		System.out.println("\npaths:");

		findAllPaths(grid, seq);

	}

	/* Helper methods below -- you shouldn't need to alter them but you can add your own. */

	private static char[] randomSymbolSequence(int length) {
		char[] sequence = new char[length];
		for(int i = 0; i < length; i++) {
			sequence[i] = SYMBOLS[(int)(Math.random()*SYMBOLS.length)];
		}
		return sequence;
	}


}

/* Represents a cell on a grid -- just a convenient way of
 * packaging a pair of indices  */
class Cell {
	int r, c;

	Cell(int r, int c) {
		this.r = r;
		this.c = c;
	}

	@Override
	public boolean equals(Object o) {
		Cell cell = (Cell) o;
		return this.r == cell.r && this.c == cell.c;
	}

	@Override
	public String toString() {
		return "(" + r + ", " + c + ")";
	}
}

/* Represents a path of cells on a grid of symbols. */
class Path {
	private LinkedList<Cell> cells;
	private LinkedList<Character> symbols;

	Path() {
		cells = new LinkedList<Cell>();
		symbols = new LinkedList<Character>();
	}

	Path(Path copy) {
		this.cells = new LinkedList<>(copy.cells);
		this.symbols = new LinkedList<>(copy.symbols);
	}

	int getLength() {
		return cells.size();
	}

	void add(Cell location, char symbol) {
		cells.addLast(location);
		symbols.addLast(symbol);
	}

	void removeLast() {
		cells.removeLast();
		symbols.removeLast();
	}

	boolean contains(Cell cell) {
		return cells.contains(cell);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < cells.size(); i++) {
			sb.append(symbols.get(i));
			sb.append(cells.get(i).toString());
			sb.append("  ");
		}

		return sb.toString();
	}

	void display() {
		System.out.println(toString());
	}


}

/* Represents a grid of symbols. */
class Grid {
	private char[][] grid;

	Grid(int size, char[] symbols) {
		grid = initGrid(size, symbols);
	}

	private char[][] initGrid(int size, char[] symbols) {
		char[][] symbolGrid = new char[size][size];

		for(int row = 0; row < size; row++) {
			for(int col = 0; col < size; col++) {
				// picks a random symbol for each cell on the grid
				symbolGrid[row][col] = symbols[(int)(Math.random() * symbols.length)];
			}
		}

		return symbolGrid;
	}

	int getSize() {
		return grid.length;
	}

	char getSymbolAt(Cell location) {
		return getSymbolAt(location.r, location.c);
	}

	char getSymbolAt(int r, int c) {
		return grid[r][c];
	}

	boolean isOutside(Cell location) {
		return isOutside(location.r, location.c);
	}

	boolean isOutside(int r, int c) {
		return 0 > r || r >= grid.length || 0 > c || c >= grid[r].length;
	}

	boolean isOnGrid(Cell location) {
		return isOnGrid(location.r, location.c);
	}

	boolean isOnGrid(int r, int c) {
		return 0 <= r && r < grid.length && 0 <= c && c < grid[r].length;
	}

	void display() {
		// Display column indices
		System.out.print("\n    ");
		for(int i = 0; i < grid.length; i++) {
			System.out.print(i + "  ");
		}
		System.out.println();

		// Display grid
		for(int r = 0; r < grid.length; r++) {
			// Display row index
			System.out.print("  " + r + " ");
			for(int c = 0; c < grid[r].length; c++) {
				System.out.print(grid[r][c] + "  ");
			}
			System.out.println();
		}
	}
}
