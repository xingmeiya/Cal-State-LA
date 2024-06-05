// Noelani Mishina Hinh
//CS 2013
//16 May 2024

import java.util.ArrayList;
import java.util.Scanner;

public class LinkedGrid<E> {
    private int numRows;
    private int numCols;

    private GridNode<E> head;
    private GridNode<E> lastRowHead;
    private GridNode<E> lastColHead;

    public LinkedGrid() {
    }

    /*
     * A constructor which takes the initial number of rows and columns of
     * the LinkedGrid. This constructor needs to initialize the LinkedGrid with
     * grid nodes (which are initially empty)
     * 
     * Example: if I invoked the constructor with new LinkedGrid(2, 3), then I
     * would have to initialize my LinkedGrid of 6 empty nodes with 2 rows and
     * 3 nodes per row
     */
    public LinkedGrid(int numRows, int numCols) {
        // TO DO
        this.numRows = numRows;
        this.numCols = numCols;
    }

    /*
     * Constructor which takes a normal 2D array as an argument. This is the
     * ONLY place you can use a 2D array and this 2D array is only used to
     * initialize the values of your LinkedGrid object 
     */
    public LinkedGrid(E[][] elements) {
        // TO DO
        this.numRows = elements.length;
        this.numCols = elements[0].length;

        if (numRows > 0 && numCols > 0) {
            head = new GridNode<>(elements[0][0]);
            GridNode<E> current = head;
            GridNode<E> rowStart = head;

            // Create the first row
            for (int j = 1; j < numCols; j++) {
                current.nextCol = new GridNode<>(elements[0][j]);
                current = current.nextCol;
            }

            // Create the remaining rows
            for (int i = 1; i < numRows; i++) {
                rowStart.nextRow = new GridNode<>(elements[i][0]);
                rowStart = rowStart.nextRow;
                current = rowStart;

                for (int j = 1; j < numCols; j++) {
                    current.nextCol = new GridNode<>(elements[i][j]);
                    current = current.nextCol;
                }
            }
        }
    }

    // Get the number of rows
    public int getNumRows() {
        return this.numRows;
    }

    // Get the number of columns
    public int getNumCols() {
        return this.numCols;
    }

    /*
     * Returns the item at the given (row, col).  NOTE: You must return the
     * item stored in the GridNode, NOT the GridNode itself
     */
    public E get(int row, int col) {
        // TO DO
        // Checking if user input out of bounds
        if (row < 0 || row >= numRows || col < 0 || col >= numCols) {
            throw new IndexOutOfBoundsException("Invalid row or column index.");
        }

        GridNode<E> current = head;
        // Increments current row when less than total rows
        for (int i = 0; i < row; i++) {
            current = current.nextRow;
        }
        // Increments current col when less than total cols
        for (int j = 0; j < col; j++) {
            current = current.nextCol;
        }
        // Returns element inside current node
        return current.getElement();
        // return null;
    }

    /*
     * Assigns the given item to the GridNode at position (row, col)
     */
    public void set(int row, int col, E value) {
        // TO DO
        // Checking if user input out of bounds
        if (row < 0 || row >= numRows || col < 0 || col >= numCols) {
            throw new IndexOutOfBoundsException("Invalid row or column index.");
        }

        GridNode<E> current = head;
        // Increments current row when less than total rows
        for (int i = 0; i < row; i++) {
            current = current.nextRow;
        }
        // Increments current col when less than total cols
        for (int j = 0; j < col; j++) {
            current = current.nextCol;
        }
        // Setting given element to current node
        current.setElement(value);
    }

    /*
     * Get row at index. NOTE: This is not an arraylist of nodes, but of
     * the values in the nodes.
     */
    public ArrayList<E> getRow(int index) {
        // TO DO
        // Checking if user input out of bounds
        if (index < 0 || index >= numRows) {
            throw new IndexOutOfBoundsException("Invalid row index.");
        }

        // Creates a temporary array that is used to store elements
        ArrayList<E> rowValues = new ArrayList<>();
        GridNode<E> current = head;
        // Increments current row when less than index variable
        for (int i = 0; i < index; i++) {
            current = current.nextRow;
        }
        // If current node has a value, add it to the rowValues array list, then move
        // to next col
        while (current != null) {
            rowValues.add(current.getElement());
            current = current.nextCol;
        }
        // Returns rowValues array list
        return rowValues;
        // return null;
    }

    /*
     * Get column at index. NOTE: This is not an arraylist of nodes, but of
     * the values in the nodes.
     */
    public ArrayList<E> getCol(int index) {
        // TO DO
        // Checking if user input is out of bounds
        if (index < 0 || index >= numCols) {
            throw new IndexOutOfBoundsException("Invalid column index.");
        }

        // Creates a temporary array that is used to store elements
        ArrayList<E> colValues = new ArrayList<>();
        GridNode<E> current = head;
        // Increments current col when less than index variable
        for (int i = 0; i < index; i++) {
            current = current.nextCol;
        }
        // If current node has a value, add it to the colValues array list, then move
        // to next row
        while (current != null) {
            colValues.add(current.getElement());
            current = current.nextRow;
        }
        // Returns colValues array list
        return colValues;
        // return null;
    }

    /*
     * Adds a new row of empty nodes to the beginning of the list
     */
    public void addFirstRow() {
        // TO DO
        // Create newRowHead Node
        GridNode<E> newRowHead = new GridNode<>();
        // nextRow pointer is now pointing to head
        newRowHead.nextRow = head;
        // Create new node called current that is a copy of newRowHead
        GridNode<E> current = newRowHead;

        // Link all nodes in the new row
        GridNode<E> temp = head;
        while (temp != null && temp.nextCol != null) {
            current.nextCol = new GridNode<>();
            current = current.nextCol;
            temp = temp.nextCol;
        }

        // head is assigned to newRowHead, numRows is incremented
        head = newRowHead;
        numRows++;
        System.out.println("Added First Row");
    }

    /*
     * Adds a new column of empty nodes to the beginning of the list
     */
    public void addFirstCol() {
        // TO DO
        // Create a newColHead node
        GridNode<E> newColHead = new GridNode<>();
        // nextCol pointer is now pointing to head
        newColHead.nextCol = head;
        // Create new node called current that is a copy of newColHead
        GridNode<E> current = newColHead;

        // Link all nodes in the new column
        GridNode<E> temp = head;
        while (temp != null && temp.nextRow != null) {
            current.nextRow = new GridNode<>();
            current = current.nextRow;
            temp = temp.nextRow;
        }

        // head is assigned to newColHead, numCols is incremented
        head = newColHead;
        numCols++;
        System.out.println("Added First Column");
    }

    /*
     * Adds a new row of empty nodes to the end of the list
     */
    public void addLastRow() {
        // TO DO
        // Traverse to the last row
        // Assigns head to current
        GridNode<E> current = head;
        // While next row has a value, traverse to it
        while (current.nextRow != null) {
            current = current.nextRow;
        }

        // Creates a new row head node
        GridNode<E> newRowHead = new GridNode<>();
        // Assigns current.nextRow to newRowHead
        current.nextRow = newRowHead;
        // Assigns current to newRowHead
        current = newRowHead;

        // Link all nodes in the new row
        // Creates temp node and assigns to head
        GridNode<E> temp = head;
        // While nextCol has value, assigns the current node to the next column
        // Assigns temp to nextCol
        while (temp.nextCol != null) {
            current.nextCol = new GridNode<>();
            current = current.nextCol;
            temp = temp.nextCol;
        }

        // Update number of rows
        numRows++;
        System.out.println("Added Row at the end");
    }

    /*
     * Adds a new column of empty nodes to the end of the list
     */
    public void addLastCol() {
        // TO DO
        // Traverse to the last column
        // Creates new node called current and assigns head to it
        GridNode<E> current = head;
        // While next column has value, traverse to it
        while (current.nextCol != null) {
            current = current.nextCol;
        }

        // Create a new column head
        GridNode<E> newColHead = new GridNode<>();
        // Assigns nextCol to newColHead
        current.nextCol = newColHead;
        // Assigns newColHead to current
        current = newColHead;

        // Link all nodes in the new column
        GridNode<E> temp = head;
        // While temp.nextRow has value, traverses to it
        while (temp.nextRow != null) {
            current.nextRow = new GridNode<>();
            current = current.nextRow;
            temp = temp.nextRow;
        }

        // Update number of columns
        numCols++;
        System.out.println("Added Column at the end");
    }

    /*
     * Inserts a row at the given index. (Insert here means the rows
     * shift over by 1 from the insertion point onward)
     */
    public void insertRow(int index) {
        // TO DO
        // Validate the row index
        // Out of bounds to user
        if (index < 0 || index > numRows) {
            throw new IndexOutOfBoundsException("Invalid row index.");
        }

        // Adds first row
        if (index == 0) {
            addFirstRow();
            return;
        }

        // Traverse to the row before the insertion point
        GridNode<E> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.nextRow;
        }

        // Create a new row head and link it
        GridNode<E> newRowHead = new GridNode<>();
        newRowHead.nextRow = current.nextRow;
        current.nextRow = newRowHead;

        // Link all nodes in the new row
        GridNode<E> temp = current;
        current = newRowHead;
        // While temp.nextCol has value, traverses
        while (temp.nextCol != null) {
            temp = temp.nextCol;
            current.nextCol = new GridNode<>();
            current = current.nextCol;
        }

        // Update number of rows
        numRows++;
        System.out.println("Inserted row at index");
    }

    /*
     * Inserts a column at the given index. (Insert here means the columns
     * shift over by 1 from the insertion point onward)
     */
    public void insertCol(int index) {
        // TO DO
        // Validate the column index
        // Out of bounds to user
        if (index < 0 || index > numCols) {
            throw new IndexOutOfBoundsException("Invalid column index.");
        }

        // Adds first col
        if (index == 0) {
            addFirstCol();
            return;
        }

        // Traverse to the column before the insertion point
        GridNode<E> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.nextCol;
        }

        // Create a new column head and link it
        GridNode<E> newColHead = new GridNode<>();
        newColHead.nextCol = current.nextCol;
        current.nextCol = newColHead;

        // Link all nodes in the new column
        GridNode<E> temp = current;
        current = newColHead;
        // While temp.nextRow has value, traverses
        while (temp.nextRow != null) {
            temp = temp.nextRow;
            current.nextRow = new GridNode<>();
            current = current.nextRow;
        }

        // Update number of columns
        numCols++;
        System.out.println("Inserted column at index");
    }

    /*
     * Removes the first row
     */
    public void deleteFirstRow() {
        // TO DO
        // Validate if there are rows to delete
        if (numRows == 0) {
            throw new IllegalStateException("No rows to delete.");
        }

        // Update head to the next row and decrements
        head = head.nextRow;
        numRows--;
        System.out.println("Deleted First Row");
    }

    /*
     * Removes the first column
     */
    public void deleteFirstCol() {
        // TO DO
        // Validate if there are columns to delete
        if (numCols == 0) {
            throw new IllegalStateException("No columns to delete.");
        }

        // Traverse and update head for each row
        GridNode<E> current = head;
        while (current != null) {
            current = current.nextRow;
            head = head.nextCol;
        }

        // Decrements
        numCols--;
        System.out.println("Deleted First Column");
    }

    /*
     * Removes the last row
     */
    public void deleteLastRow() {
        // TO DO
        // Validate if there are rows to delete
        if (numRows == 0) {
            throw new IllegalStateException("No rows to delete.");
        }

        // If there is only one row, head is null and decrements
        if (numRows == 1) {
            head = null;
            numRows--;
            return;
        }

        // Traverse to the second last row
        GridNode<E> current = head;
        while (current.nextRow.nextRow != null) {
            current = current.nextRow;
        }

        // Remove the last row
        current.nextRow = null;
        numRows--;
        System.out.println("Deleted Last Row");
    }

    /*
     * Removes the last column
     */
    public void deleteLastCol() {
        // TO DO
        // Validate if there are columns to delete
        if (numCols == 0) {
            throw new IllegalStateException("No columns to delete.");
        }

        // If there is only one col, head is null and decrements
        if (numCols == 1) {
            head = null;
            numCols--;
            return;
        }

        // Traverse and remove the last column for each row
        GridNode<E> current = head;
        while (current.nextCol.nextCol != null) {
            current = current.nextCol;
        }
        current.nextCol = null;
        numCols--;
        System.out.println("Deleted Last Column");
    }

    /*
     * Removes the row at the given index
     */
    public void deleteRow(int index) {
        // TO DO
        // Validate the row index
        if (index < 0 || index >= numRows) {
            throw new IndexOutOfBoundsException("Invalid row index.");
        }

        // Deletes first row
        if (index == 0) {
            deleteFirstRow();
            return;
        }

        // Traverse to the row before the deletion point
        GridNode<E> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.nextRow;
        }

        // Remove the specified row
        current.nextRow = current.nextRow.nextRow;
        numRows--;
        System.out.println("Deleted Row at index");
    }

    /*
     * Removes the column at the given index
     */
    public void deleteCol(int index) {
        // TO DO
        // Validate the column index
        if (index < 0 || index >= numCols) {
            throw new IndexOutOfBoundsException("Invalid column index.");
        }

        // Deletes first col
        if (index == 0) {
            deleteFirstCol();
            return;
        }

        // Traverse to the column before the deletion point
        GridNode<E> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.nextCol;
        }

        // Remove the specified column
        current.nextCol = current.nextCol.nextCol;
        numCols--;
        System.out.println("Deleted Column at index");
    }

    void display() {
        // Check if the grid is empty
        if (head == null) {
            System.out.println("This instance of LinkedGrid is empty.");
            return;
        }

        // Traverse and display each row
        GridNode currRow = head;
        while (currRow != null) {
            GridNode curr = currRow;
            while (curr != null) {
                System.out.printf("%5s ", curr.getElement().toString());
                curr = curr.nextCol;
            }
            System.out.println();
            currRow = currRow.nextRow;
        }
    }

    public static void main(String[] args) {
        // Setup
        int row;
        int col;
        Scanner scan = new Scanner(System.in);
        System.out.print("How many rows?");
        row = scan.nextInt();
        System.out.print("How many columns?");
        col = scan.nextInt();
        LinkedGrid grid1 = new LinkedGrid(row, col);
        System.out.println("Number of Rows: " + grid1.getNumRows());
        System.out.println("Number of Col: " + grid1.getNumCols());

        System.out.println("Number of Rows: " + grid1.getNumRows());
        grid1.addFirstRow(); // adds row of empty nodes
        System.out.println("Number of Rows: " + grid1.getNumRows());

        System.out.println("Number of Cols: " + grid1.getNumCols());
        grid1.addFirstCol(); // adds col of empty nodes
        System.out.println("Number of Cols: " + grid1.getNumCols());

        System.out.println("Number of Rows: " + grid1.getNumRows());
        grid1.deleteRow(0); // delete row at index 0
        System.out.println("Number of Rows: " + grid1.getNumRows());

        System.out.println("Number of Cols: " + grid1.getNumCols());
        grid1.deleteCol(0); // deletes column at index 0
        System.out.println("Number of Cols: " + grid1.getNumCols());

        System.out.println("Exiting program");
    }

}
