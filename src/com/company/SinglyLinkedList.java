/**
 * Uros Randelovic
 * urosr@brandeis.edu
 */
package com.company;

import java.util.Random;

public class SinglyLinkedList<T> {
    //local variables
    private SinglyLinkedNode head;
    private SinglyLinkedNode tail;
    private int size;

    //constructor
    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * //inserting at the end4
     * O(1)
     *
     * @param data
     */
    public void regularInsert(T data) {
        SinglyLinkedNode newNode = new SinglyLinkedNode(data, null);
        //if the head is empty link to it else link from the tail
        if (size == 0) {
            //setting the next node to head
            head = newNode;
            tail = head;
        } else {
            tail.setNext(newNode);
            tail = tail.getNextNode();
        }
        size++;
    }

    /**
     * insert at the random spot O(1)
     *
     * @param data
     */
    public void randomInsert(T data) {
        //generating random numbers
        Random rand = new Random();
        int randomN = rand.nextInt(size + 1) + 0;
        int i = 0;
        int desired = randomN-2;
        //checking if the head is existent or not
        if (randomN == 0 && head != null) {
            //getting the first pointer
            SinglyLinkedNode randomNode = new SinglyLinkedNode(data, head);
            //setting the head to be equal to the new node
            head = randomNode;
            //if the list is completley new we have to account for
            //tail and the head
            if (size == 0) {
                size++;
                tail = head;
            }
            //if there is no head at all we add it first using the
            //regular insert method
        } else if (head == null) {
            regularInsert(data);
            //in general case
        } else {
            //start from the begining
            SinglyLinkedNode<T> cNode = head;
            //find the node that the random number has given us
            while (i<desired){
                cNode = cNode.getNextNode();
                i++;
            }

            //create a new node to be assigned and added to the
            //list at the random spot that we foud previously
            SinglyLinkedNode<T> newNode = new SinglyLinkedNode<>(data, cNode.getNextNode());
            //set it's pointer
            cNode.setNext(newNode);
            size++;
        }
    }

    /**
     * removing the node x
     * O(n)
     */
    public void remove(T dataToRemove) {
        // considering all cases in which head needs not to be removed
        if (!head.getData().equals(dataToRemove)) {
            //start from the begining
            SinglyLinkedNode current = head;
            //find the node that needs to be deleted (by looking at the next one)
            while (!current.getNextNode().getData().equals(dataToRemove) && current.getNextNode() != tail) {
                current = current.getNextNode();
            }
            //take the node found and make it
            SinglyLinkedNode nextNode = current.getNextNode();
            //in case the element that needs to be deleted is the last one
            if (nextNode == tail) {
                //set the tail to be the current element
                tail = current;
                //poin the last elemenet to the null to mark the tail
                current.setNext(null);
                size--;
            } else {
                //in case it is not the tail just take the node
                //and point it to the one node after the one
                //that needs to be deleted
                current.setNext(nextNode.getNextNode());
                size--;
            }
        } else {
            //if it is the head just make the head the node
            //that is the second one in the chain, right after the head
            head = head.getNextNode();
            size--;
        }
    }

    /**
     * O(1)
     *
     * @return
     */
    public SinglyLinkedNode getHead() {
        return head;
    }

    /**
     * o(1)
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    public T getElementAtI(int index) {
        return findElement(index).getData();
    }

    /**
     * //find the card at the index that is asked for On
     *
     * @param index
     * @return
     */

    private SinglyLinkedNode<T> findElement(int index) {
        int i = 0;
        //get the first card
        SinglyLinkedNode<T> element = getHead();
        //find the card
        while (i < index) {
            try {
                element = element.getNextNode();

            } catch (NullPointerException e) {
                System.out.println("you don't have that card - please choose a different one");
            }
            i++;
        }
        return element;
    }


    /**
     * O(n)
     *
     * @return
     */
    @Override
    public String toString() {
        String finalString = "{ ";
        if (size != 0) {
            SinglyLinkedNode node = head;
            for (int i = 0; i < size; i++) {
                finalString = finalString + "[" + node.toString() + "]";
                if (i < size - 1) {
                    finalString = finalString + ",";
                }
                node = node.getNextNode();
            }
            return finalString + " }";
        } else {
            return "[]";
        }
    }
}
