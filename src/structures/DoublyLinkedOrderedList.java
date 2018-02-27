/**
 * Uros Randelovic
 * urosr@brandeis.edu
 */

package structures;

public class DoublyLinkedOrderedList<T> implements Comparable<T> {
    /**
     * Local variables
     */
    private int size = 0;
    private com.company.DoublyLinkedNode head;
    private com.company.DoublyLinkedNode tail;

    /**
     * Constructor
     */
    public DoublyLinkedOrderedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * inserts the node
     * O(n)
     *
     * @param data
     */
    public void insertOrdered(T data) {
        com.company.DoublyLinkedNode newNode = new com.company.DoublyLinkedNode(null, null, data);

        if (head == null) {
            head = newNode;
            size++;
        } else {
            if (head.compareTo(newNode) > -1) {
                newNode.setNextNode(head);
                head.setPreviousNode(newNode);
                head = newNode;
                size++;
            } else {

                com.company.DoublyLinkedNode current = head;
                while (current.getNextNode() != null) {
                    if (current.getNextNode().compareTo(newNode) > -1) {
                        break;
                    }
                }
                com.company.DoublyLinkedNode nextNode = newNode.getNextNode();

                nextNode = current.getNextNode();
                if (current.getNextNode() != null) {
                    current.getNextNode().setPreviousNode(newNode);
                }
                current.setNextNode(newNode);
                newNode.setPreviousNode(current);
                size++;
            }
        }


    }


    /**
     * Removes the first node with the given data O(n)
     *
     * @param dataToRemove
     */
    public void remove(T dataToRemove) {
        if (!head.getData().equals(dataToRemove)) {
            //start from the begining
            com.company.DoublyLinkedNode currentNode = head;
            //find the node for which the next node is the one
            //to be deleted
            while (!currentNode.getNextNode().getData().equals(dataToRemove)
                    && currentNode.getNextNode() != tail) {
                currentNode = currentNode.getNextNode();
            }
            //set the working node to be the one that needs to be
            //deleted
            com.company.DoublyLinkedNode nextNode = currentNode.getNextNode();
            //in case the element to be deleted is the last element
            if (nextNode == tail) {
                //tail becomes the current element
                tail = currentNode;
                //set the pointer to be null
                currentNode.setNextNode(null);
                size--;
                //in case the node is anywhere in between head and the tail
            } else {
                //take the current node and set it to point to the
                //one after the one to be deleted
                currentNode.setNextNode(nextNode.getNextNode());
                //take the one after to the one to be deleted
                //and set the pointer to the previous to point to the
                //one before the delete
                nextNode.getNextNode().setPreviousNode(currentNode);
                size--;
            }
            //in case the head needs to be removed
        } else {
            head = head.getNextNode();
            size--;
        }
    }


    /**
     * Method to get the head
     * O(1)
     *
     * @return
     */
    public com.company.DoublyLinkedNode getHead() {
        return head;
    }

    /**
     * Method to get a tail
     * O(1)
     *
     * @return
     */
    public com.company.DoublyLinkedNode getTail() {
        return tail;
    }

    @Override
    public int compareTo(T o) {

        if (o.hashCode()>o.hashCode()){
            return 1;
        }else if (o.hashCode()==o.hashCode()){
            return 0;
        }else {
            return -1;
        }
    }

    /**
     * Returns String representation of the doubly linked list
     * O(n)
     *
     * @return
     */
    @Override
    public String toString() {

        String finalString = "{ ";
        if (size != 0) {
            com.company.DoublyLinkedNode node = head;
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
