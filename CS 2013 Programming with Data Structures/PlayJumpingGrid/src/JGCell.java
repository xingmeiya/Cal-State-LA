public class JGCell {
    int r, c;

    public JGCell(int r, int c) {
        this.r = r;
        this.c = c;
    }

    // does this cell represent the lower left corner
    // in a grid of given size?
    boolean isEnd(int size) {
        return (r == size - 1) && (c == size - 1);
    }

    @Override
    public boolean equals(Object o) {
        JGCell cell = (JGCell) o;
        return this.r == cell.r && this.c == cell.c;
    }

    @Override
    public String toString() {
        return "(" + r + "," + c + ")";
    }
}
