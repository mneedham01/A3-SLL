/** Keeps track of position in a linked list */
public class SLL_Iterator<T> implements Phase5SLL_Iterator<T> {

    private SLL<T> list;
    private NodeSL<T> next;

    /**
     * Creates a new iterator on the given list.
     * Default position is leftmost
     *
     * @param list the list to iterate on
     */
    public SLL_Iterator(SLL<T> list) {
        this.list = list;
        this.next = list.getHead();
    }

    /**
     * Tests whether there are any more
     *
     * @return T/F is it safe to call next()?
     */
    public boolean hasNext() {
        return this.next.getNext() != null;
    }

    /**
     * Returns next or throws an exception
     * and advances the iterator
     *
     * @return the next element
     */
    public T next() {
        return this.next.getData();
    }

    /**
     * Sets the data for the element just passed
     *
     * @param data value to set
     */
    public void set(T data) {

    }

    /**
     * Returns the data for the element just passed
     *
     * @return data value in the element just passed
     */
    public T get() {
        // TODO
        return null;
    }

    /**
     * Inserts a node with the specified data
     * Cannot be called twice in a row without intervening next()
     *
     * @param data the value to insert
     */
    public void add(T data) {
        // TODO
    }

    /**
     * Removes the node just passed
     * Cannot be called twice in a row without intervening next()
     */
    public void remove() {
        // TODO
    }
}