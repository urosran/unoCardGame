/**
 * Uros Randelovic
 * urosr@brandeis.edu
 */
package com.company;

import javax.print.attribute.standard.NumberUp;

/**
 *
 */
public class Player {
    private String name;
    private Player nextPlayer = null;
    private Player prevPlayer = null;
    private SinglyLinkedList<UnoCard> hand;

    /**
     * constructor o1
     * @param name
     */
    public Player(String name) {
        this.name = name;
        hand = new SinglyLinkedList<UnoCard>();
    }

    /**
     * add a card to the hand O1
     * @param c
     */
    public void addToHand(UnoCard c) {
        hand.regularInsert(c);
    }

    /**
     * o1 remove a card from the hand
     * @param index
     */
    public void removeFromHand(int index) {
        hand.remove(findCard(index).getData());
    }

    /**
     * returns a card at index i
     * @param index
     * @return
     */
    public UnoCard cardFromHand(int index){
        return findCard(index).getData();
    }

    /**
     * //find the card at the index that is asked for
     * @param index
     * @return
     */
    private SinglyLinkedNode<UnoCard> findCard(int index){
        int i = 0;
        //get the first card
        SinglyLinkedNode<UnoCard> unoCard = hand.getHead();
        //find the card
        while (i < index) {
            try {
                unoCard = unoCard.getNextNode();

            } catch (NullPointerException e){
                System.out.println("you don't have that card - please choose a different one");
            }
            i++;
        }
        return unoCard;
    }

    /**
     * tells you if the person is a winner
     * @return
     */
    public boolean winner() {
        return hand.getSize() == 0;
    }
//all the methods below are O1
    /**
     * get the next node
     * @return
     */
    public Player getNextPlayer() {
        return nextPlayer;
    }
    /**
     * set the next node
     * @return
     */
    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }
    /**
     * get the prev node
     * @return
     */
    public Player getPrevPlayer() {
        return prevPlayer;
    }
    /**
     * set the prev node
     * @return
     */
    public void setPrevPlayer(Player prevPlayer) {
        this.prevPlayer = prevPlayer;
    }

    /**
     * string representation 01
     * @return
     */
    public String toString() {
        return "Player [name=" + name + "]";
    }

    public String getName() {
        return name;
    }

    public int getHandSize() {
        return hand.getSize();
    }

    public SinglyLinkedList<UnoCard> getHand() {
        return hand;
    }

    /**
     * prints the  hand On
     */
    public void printHand(){
        SinglyLinkedNode card = hand.getHead();
        while (card!=null){
            System.out.println( "hand: " + card.getData().toString());
            card=card.getNextNode();
        }
    }

    /**
     * sets the new hand the begining of each game
     * @param hand
     */
    public void setHand(SinglyLinkedList<UnoCard> hand) {
        this.hand = hand;
    }
}
