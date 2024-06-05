public class GridNode<E> {
    private E element;
    GridNode nextRow;
    GridNode nextCol;

    public GridNode() {
    }

    public GridNode(E elem) {
        element = elem;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E elem) {
        element = elem;
    }
}
