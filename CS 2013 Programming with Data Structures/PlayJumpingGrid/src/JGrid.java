public class JGrid {
    private int size;
    private int[][] grid;

    public JGrid(int size, int minValue, int maxValue) {
        this.size = size;
        grid = init(minValue, maxValue);
    }

    public int getSize() {
        return size;
    }

    public boolean isOnGrid(int r, int c) {
        return (0 <= r && r < size && 0 <= c && c < size);
    }

    public boolean isOnGrid(JGCell location) {
        return isOnGrid(location.r, location.c);
    }

    public boolean isOutside(int r, int c) {
        return (0 > r || r >= size ||
                0 > c || c >= size);

    }

    public boolean isOutside(JGCell location) {
        return isOutside(location.r, location.c);
    }

    public int getValueAt(int r, int c) {
        return grid[r][c];
    }

    public int getValueAt(JGCell location) {
        return grid[location.r][location.c];
    }

    private int[][] init(int min, int max) {

        int[][] grid = new int[size][size];
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                grid[r][c] = (int)(min + Math.random() * (max - min + 1));
            }
        }
        return grid;
    }

    public void display() {

        System.out.print("\n    ");
        for(int i = 0; i < size; i++) {
            System.out.print(i + "  ");
        }
        System.out.print("\n   ");
        for(int i = 0; i < size; i++) {
            System.out.print("---");
        }
        System.out.println();

        for(int r = 0; r < size; r++) {
            System.out.print(" " + r + "| ");
            for(int c = 0; c < size; c++) {
                System.out.print(grid[r][c] + "  ");
            }
            System.out.println();
        }
    }
}
