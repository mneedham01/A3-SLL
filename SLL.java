
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
     * Copy constructor
     */
    public SLL(SLL<T> original) {
        // start list as empty
        this.head = null;
        this.tail = null;
        if (! original.isEmpty()) {
            for (NodeSL<T> item = original.getHead(); item != original.tail.getNext(); item = item.getNext()){
                this.addLast(item.getData());
            }
        }
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
        if (v.equals(null)) {
            throw new MissingElementException();
        }
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
        if (this.isEmpty()) {
            return "[]";
        }
        String string = "[";
        for (NodeSL<T> item = this.head; item != this.tail; item = item.getNext()) {
            string += item.getData()+ ", ";
        }
        string += this.tail.getData()+"]";
        return string;
    }

    /*
     * Inserts the given item at the tail of the list
     *  @param item to insert
     */
    public void addLast(T v) {
        if (v.equals(null)) {
            throw new MissingElementException();
        }
        NodeSL<T> last = new NodeSL<T>(v, null);
        // if the current list is empty
        if (this.isEmpty()) {
            this.head = last;
        } else{
            this.tail.setNext(last);
        }
        this.tail = last;
    }

    /*
     * Inserts the given item after the specified node
     *  @param here node to insert after
     *  @param v item to insert
     */
    public void addAfter(NodeSL<T> here, T v) {
        if (v.equals(null)) {
            throw new MissingElementException();
        }
        if (this.tail == here) {
            NodeSL<T> toAdd = new NodeSL<T>(v, null);
            here.setNext(toAdd);
            this.tail = toAdd;
        } else {
            NodeSL<T> toAdd = new NodeSL<T>(v, here.getNext());
            here.setNext(toAdd);
        }
    }

    /*
     * Removes the given item from the head of the list
     *  @return v item removed
     */
    public T removeFirst(){
        // if the list is empty
        if (this.head.equals(null)) {
            throw new MissingElementException();
        }
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
        if (this.tail.equals(null)) {
            throw new MissingElementException();
        }
        // if head and tail are the same (one item in list)
        if (this.size() == 1) {
            T itemRemoved = this.tail.getData();
            this.head = this.tail = null;
            return itemRemoved;
        }
        T itemRemoved = this.tail.getData();
        NodeSL<T> prev =null;
        for (NodeSL<T> item = this.head; item != this.tail; item = item.getNext()) {
            prev = item;
        }
        prev.setNext(null);
        this.tail = prev;
        return itemRemoved;
    }

    /*
     * Removes the node after the given position
     * @param here marks position to remove after
     * @return item removed
     */
    public T removeAfter(NodeSL<T> here) {
        T toRemove = null;
        if (here == null) {
            this.tail = this.head = null;
            toRemove = this.head.getData();
        } else {
            for (NodeSL<T> item = this.head; item != this.tail; item = item.getNext()) {
                if (item == here) {
                    toRemove = item.getNext().getData();
                    NodeSL<T> toConnect = item.getNext().getNext();
                    item.setNext(toConnect);
                    break;
                }
            }
        }
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
        // start a new SLL copying this
        SLL<T> subseq = new SLL<T>(this);
        // find the item before here to reset to null
        // not necessary if here is the head
        if (subseq.getHead().getData() != here.getData()) {
            NodeSL<T> current = subseq.head;
            while (current.getNext().getData() != here.getData()) {
                current = current.getNext();
            }
            // found node before here
            // set here as head
            subseq.head = current.getNext();
            // modify current's next
            current.setNext(null);
        }
        NodeSL<T> lastNode = subseq.getHead();
        // find the last node
        for (int i = 0; i < n - 1; i++) {
            lastNode = lastNode.getNext();
        }
        subseq.tail = lastNode;
        lastNode.setNext(null);
        return subseq;
    }

    /*
     * Places copy of the provided list into this after the specified node.
     * @param list  the list to splice in a copy of
     * @param afterHere  marks the position in this where the new list should go
     */
    public void spliceByCopy(SLL<T> list, NodeSL<T> afterHere){
        if (list.equals(this)) {
            throw new SelfInsertException();
        }
        if (list.isEmpty()) {
            return;
        }
        SLL<T> copy = new SLL<T>(list);
        if (afterHere == null) {
            // if afterHere is null, add list to start of this
            copy.getTail().setNext(this.head);
            this.head = copy.getHead();
            return;
        }
        boolean afterHereIsTail = afterHere == this.tail;
        if (afterHereIsTail) {
            afterHere.setNext(copy.getHead());
            this.tail = copy.tail;
            return;
        } else {
            NodeSL<T> afterAfterHere = afterHere.getNext();
            afterHere.setNext(copy.getHead());
            System.out.println("afterAfterHere = " + afterAfterHere.getData());
            copy.tail.setNext(afterAfterHere);
        }
    }

    /*
     * Extracts a subsequence of nodes and returns them as a new list
     * @param afterHere  marks the node just before the extraction
     * @param toHere  marks the node where the extraction ends
     * @return  the new list
     */
    public SLL<T> subseqByTransfer(NodeSL<T> afterHere, NodeSL<T> toHere){
        System.out.println("in subseq");
        SLL<T> subseq = new SLL<T>();
        // // add first node
        // subseq.addFirst(afterHere.getNext().getData());
        // System.out.println(subseq);
        // NodeSL<T> current = afterHere.getNext();
        // System.out.println("current = " + current.getData());
        // // loop through subseq, add to new list
        // System.out.println("while current != " + toHere.getData());
        // while (current.getData() != toHere.getNext().getData()) {
        //     System.out.println("In while loop. current = " + current.getData());
        //     subseq.addAfter(current, current.getNext().getData());
        //     System.out.println("subseq = " + subseq);
        //     current = current.getNext();
        //     System.out.println("current = " + current.getData());
        // }
        // // rewrite connection afterHere
        // afterHere.setNext(toHere.getNext());
        // System.out.println(subseq);
        return subseq;
    }

    /*
     *  Takes the provided list and inserts its elements into this
     *  after the specified node.  The inserted list ends up empty.
     *  @param list  the list to splice in.  Becomes empty after the call
     *  @param afterHere  Marks the place where the new elements are inserted
     */
    public void spliceByTransfer(SLL<T> list, NodeSL<T> afterHere) {
        if (list.equals(this)) {
            throw new SelfInsertException();
        }
        NodeSL<T> afterSplice = afterHere.getNext();
        afterHere.setNext(list.head);
        list.tail.setNext(afterSplice);
    }

}

