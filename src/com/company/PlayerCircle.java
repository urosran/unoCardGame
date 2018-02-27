/**
 * Uros Randelovic
 * urosr@brandeis.edu
 */
package com.company;


public class PlayerCircle  {
    private Player player;
    private int size = 0;

    /**
     * adds the player to the linked list O(n)
     * @param player
     */
    public void addUros(Player player){
        //in case there is noone here
        if (size == 0) {
            //creating the first player and setting the pointers
            this.player = player;
            size++;
            this.player.setNextPlayer(player);
            this.player.setPrevPlayer(player);
        }
        //there is a player before
        else {
            //find the last player
            Player currentPlayer = this.player;
            while (currentPlayer.getNextPlayer()!=this.player){
                currentPlayer = currentPlayer.getNextPlayer();
            }
            //add the player
            currentPlayer.setNextPlayer(player);
            player.setPrevPlayer(currentPlayer);
            player.setNextPlayer(this.player);
            this.player.setPrevPlayer(player);
            size++;
        }
    }

    /**
     * Remove the given player On
     * @param player
     */
    public void remove(Player player) {
        Player current = this.player;
        //find the player
        while (!player.getName().equals(current.getName())){
            current = current.getNextPlayer();
        }
        if (size > 1) {
            //if the list has more than one player
            current.getNextPlayer().setPrevPlayer(current.getPrevPlayer());
            current.getPrevPlayer().setNextPlayer(current.getNextPlayer());
            size--;
        //to remove a player if the list has only one player - special case
        } else {
            //set the first player to null
            this.player = null;
            //change the size
            size--;
        }
    }

    /**
     * Size of the list
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * get the head of the list
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

}
