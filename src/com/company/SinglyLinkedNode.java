/**
 * Uros Randelovic
 * urosr@brandeis.edu
 */
package com.company;

public class SinglyLinkedNode<T> {
    //internal variables
    private T data;
    private SinglyLinkedNode nextNode;

    //constructor
    public SinglyLinkedNode(T data, SinglyLinkedNode nextNode){
        this.data = data;
        this.nextNode = nextNode;
    }
    //setting the next node
    void setNext(SinglyLinkedNode nextNode){
        this.nextNode = nextNode;
    }
    //returning the data
    public T getData() {
        return data;
    }

    public SinglyLinkedNode getNextNode() {
        return nextNode;
    }

    //returning the string version of data
    public String toString() {
        return data.toString();
    }
}
