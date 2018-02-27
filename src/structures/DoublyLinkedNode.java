/**
 * Uros Randelovic
 * urosr@brandeis.edu
 */
package com.company;

public class DoublyLinkedNode<T> implements Comparable<T> {
    private  T data;
    private DoublyLinkedNode previousNode;
    private DoublyLinkedNode nextNode;

    /**
     *
     * @param previousNode
     * @param nextNode
     * @param data
     */
    public DoublyLinkedNode(DoublyLinkedNode previousNode,
                            DoublyLinkedNode nextNode,
                            T data){
        this.data = data;
        this.previousNode = previousNode;
        this.nextNode = nextNode;
    }

    /**
     *
     * @param nextNode
     */
    public void setNextNode(DoublyLinkedNode nextNode) {
        this.nextNode = nextNode;
    }

    /**
     *
     * @param previousNode
     */
    public void setPreviousNode(DoublyLinkedNode previousNode) {
        this.previousNode = previousNode;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return  data.toString();
    }

    /**
     *
     * @return
     */
    public DoublyLinkedNode getNextNode() {
        return nextNode;
    }

    /**
     *
     * @return
     */
    public DoublyLinkedNode getPreviousNode() {
        return previousNode;
    }

    /**
     *
     * @return
     */
    public T getData() {
        return data;
    }

    /**
     *Compares the nodes by their data and hash codes
     * @param o - should be the data that the node stores
     * @return
     */
    @Override
    public int compareTo(T o) {
        if (o.hashCode()>data.hashCode()){
            return 1;
        }else if (o.hashCode()==data.hashCode()){
            return 0;
        }else {
            return -1;
        }
    }
}
