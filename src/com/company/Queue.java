/**
 * Uros Randelovic
 * urosr@brandeis.edu
 */
package com.company;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Queue<T> {

    private int SIZE = 0;
    private int userSize;
    private T[] array;
    int head = 0;
    int tail = 0;

    /**
     * constructor
     *
     * @param c
     * @param size
     */

    public Queue(Class<T> c, int size) {
        //setting the size of the queue
        this.userSize = size;
        //instatiating the array
        T[] newArray = (T[]) Array.newInstance(c, size);
        this.array = newArray;
    }

    /**
     * Enquues element O(1)
     *
     * @param data
     */

    public void enqueue(T data) {
       //if the queue is not full
        if (!isFull()) {
            //add the value at the end
            array[tail] = data;
            //increment the counter to keep track of the last element
            tail++;
            //increment the size
            SIZE++;
            //if the last element is the first one make it the first
            if (array.length == tail) {
                tail = 0;
            }
            //throw an error if user tries to add stuff to the full
            //queue
        } else {
            System.out.println("Cannot enqueue, queue full: " + toString());
        }
    }

    public int getHead() {
        return head;
    }

    /**
     * deques the element O(1)
     */

    public T dequeue() {
        //if the array is not empty
        if (!isEmpty()) {
            //instatiate the variable to return the element
            T topElement = array[head];
            head++;
            if (array.length==head){
                head=0;
            }
            SIZE--;
            return topElement;
            //capture the errors if people try to take from an empty set
        } else {
            System.out.println("Queue is empty, cannot dequeue");
            return null;
        }
    }


    /**
     * Returns the size of the array o(1)
     *
     * @return
     */
    public int getSIZE() {
        return userSize;
    }

    /**
     * Returns true if the array is not full
     * o(1)
     *
     * @return
     */
    public boolean isFull() {
        if (SIZE == userSize) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns if the array is empty
     * o(1)
     *
     * @return
     */
    public boolean isEmpty() {
        if (SIZE == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * o(1) returns the string
     *
     * @return
     */
    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
