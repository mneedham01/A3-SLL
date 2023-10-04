
/*
 *
 */
public class SLL<T> {

    private NodeSL<T> head;
    private NodeSL<T> tail;

    /*
     * Constructor that makes an empty list
     */
    public SLL() {
        this.head = null;
        this.tail = null;
    }

    /*
     * Accessor for head node
     * @return the head node
     */
    public NodeSL<T> getHead() {
        return this.head;
    }

    /*
     *  Accessor for tail node
     *  @return the tail node
     */
    public NodeSL<T> getTail() {
        return this.tail;
    }

    /*
     * Determines whether a list is empty
     * @return T/F is the list empty?
     */
    public boolean isEmpty() {
        return this.head == null && this.tail == null;
    }

    /*
     * Inserts the given item at the head of the list
     * @param v item to insert
     */
    public void addFirst(T v) {
        if (this.isEmpty()) {
            NodeSL<T> first = new NodeSL<T> (v, null);
            this.head = this.tail = first;
        } else {
            NodeSL<T> first = new NodeSL<T>(v, this.head);
            this.head = first;
        }
    }

    /*
     * Converts to a string representation
     */
    public String toString() {
        String string = "[";
        for (NodeSL<T> item = this.head; item != this.tail; item = item.getNext()) {
            string += item.getData()+ ",";
        }
        string += this.tail.getData()+"]";
        return string;
    }

    /*
     * Inserts the given item at the tail of the list
     *  @param item to insert
     */
    public void addLast(T v) {
        NodeSL<T> last = new NodeSL<T>(v, null);
        this.tail.setNext(last);
        this.tail = last;
    }

    /*
     * Inserts the given item after the specified node
     *  @param here node to insert after
     *  @param v item to insert
     */
    public void addAfter(NodeSL<T> here, T v) {
        NodeSL<T> toAdd = new NodeSL<T>(v, here.getNext());
        here.setNext(toAdd);
    }

    /*
     * Removes the given item from the head of the list
     *  @return v item removed
     */
    public T removeFirst(){
        T itemRemoved = this.head.getData();
        NodeSL<T> newHead = this.head.getNext();
        this.head.setNext(null);
        this.head = newHead;
        return itemRemoved;
    }

    /*
     * Removes the given item from the tail of the list
     *  @return item removed
     */
    public T removeLast() {
        T itemRemoved = this.tail.getData();
        for (NodeSL<T> item = this.head; item != this.tail; item = item.getNext()) {
            if (item.getNext().equals(this.tail)){
                item.setNext(null);
                this.tail = item;
            }
        }
        return itemRemoved;
    }

    /*
     * Removes the node after the given position
     * @param here marks position to remove after
     * @return item removed
     */
    public T removeAfter(NodeSL<T> here) {
        here.setNext(here.getNext().getNext());
        T toRemove = here.getNext().getData();
        return toRemove;
    }

    /*
     * Returns a count of the number of elements in the list
     * @return current number of nodes
     */
    public int size() {
        int size = 0;
        for (NodeSL<T> item = this.head; item != null; item = item.getNext()) {
            size +=1;
        }
        return size;
    }

    /*
     * Makes a copy of elements from the original list
     * @param here  starting point of copy
     * @param n  number of items to copy
     * @return the copied list
     */
    public SLL<T> subseqByCopy(NodeSL<T> here, int n){
        if (this.size() < n) {
            throw new RuntimeException();
        }
        // start a new SLL
        SLL<T> subseq = new SLL<T>();
        // add here to the head
        subseq.addFirst(here.getData());
        // initalize prevAdded
        NodeSL<T> prevAdded = here;
        // initalize counter
        int counter = 1;
        while (counter < n) {
            NodeSL<T> current = prevAdded.getNext();
            addAfter(prevAdded, current.getData());
            prevAdded = current;
            counter += 1;
        }
        return subseq;
    }

    /*
     * Places copy of the provided list into this after the specified node.
     * @param list  the list to splice in a copy of
     * @param afterHere  marks the position in this where the new list should go
     */
    public void spliceByCopy(SLL<T> list, NodeSL<T> afterHere){
        // create copy of provided list
        SLL<T> copy = new SLL<T>();
        // add to head of copy
        copy.addFirst(list.getHead().getData());
        // set up a prevAdded
        NodeSL<T> prevAdded = list.getHead();
        // loop through list
        while (prevAdded != null) {
            copy.addAfter(prevAdded,prevAdded.getNext().getData());
            prevAdded = prevAdded.getNext();
        }
        // if afterHere is null, add list to the head of this
        if (afterHere.equals(null)){
            copy.tail.setNext(this.head);
            this.head = copy.head;
        }
        // connect end of list to the beginning of second part of this
        copy.getTail().setNext(afterHere.getNext());
        // connect afterHere to the beginning of the list
        afterHere.setNext(list.getHead());
    }

    /*
     * Extracts a subsequence of nodes and returns them as a new list
     * @param afterHere  marks the node just before the extraction
     * @param toHere  marks the node where the extraction ends
     * @return  the new list
     */
    public SLL<T> subseqByTransfer(NodeSL<T> afterHere, NodeSL<T> toHere){
        SLL<T> list = new SLL<T>();
        return list;
    }

    /*
     *  Takes the provided list and inserts its elements into this
     *  after the specified node.  The inserted list ends up empty.
     *  @param list  the list to splice in.  Becomes empty after the call
     *  @param afterHere  Marks the place where the new elements are inserted
     */
    public void spliceByTransfer(SLL<T> list, NodeSL<T> afterHere) {

    }

}

