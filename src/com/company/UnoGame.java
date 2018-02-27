/**
 * Uros Randelovic
 * urosr@brandeis.edu
 */
package com.company;


import java.util.Arrays;
import java.util.Scanner;

public class UnoGame {

    private static PlayerCircle circle;
    private static Queue<Player> bench;
    private static UnoDeck deck = new UnoDeck();
    private static String[] arrayNames;
    private static boolean result = true;
    private static boolean victory = false;
    private static int circleRounds = 0;
    private static Player winner;
    private static Player loser;
    private static boolean didntHaveCard = true;

    /**
     * main method for running the game
     * @param args
     */
    public static void main(String[] args) {
        circle = new PlayerCircle();
        //take in the number of players
        int numberOfPlayers = 0;
        Scanner scan = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the game of UNO\n How many players will be playing?");
        numberOfPlayers = scan.nextInt();
        int actualNoOfPlayers = numberOfPlayers;
        //record the player names
        if (numberOfPlayers > 5) {
            bench = new Queue(Player.class, numberOfPlayers - 5);
            arrayNames = new String[5];
        } else {
            arrayNames = new String[numberOfPlayers];
        }
        //read names from the user
        String name;
        //if there are less than 5 players add them to the list
        if (numberOfPlayers <= 5) {
            addPlayer(numberOfPlayers);
        } else {
            //read the first five if there are more than 5
            addPlayer(5);
            //add the rest of players to the queue
            for (int i = 0; i < numberOfPlayers - 5; i++) {
                System.out.println("Player " + (i + 1) + " name: ");
                name = scanner.nextLine();
                //enqueue the rest to the waitingroom
                bench.enqueue(new Player(name));
                //at this point the number of players is at maximum 5

            }
            numberOfPlayers = 5;
        }
        //sort names
        Arrays.sort(arrayNames);
        //adding each player to the circle
        for (String playerName : arrayNames) {
            circle.addUros(new Player(playerName));
        }
        //-----------------------------------------------main game------------------------------------------------------
        //loop that runs forever to keep the game running
        while (true) {
            //adding 7 cars to each players hand
            addCards(numberOfPlayers);

            //step 3 - putting down the first card
            UnoCard card = deck.drawCard();
            System.out.println("\nThe first card is: " + card.toString() + "\n");
            deck.discardCard(card);
            //starting with the first player
            Player nextPlayer = circle.getPlayer();
            //very first move
            move(nextPlayer);
            //get into the loop to end only when the game is done
            while (!victory) {
                //every time we cross the first person in the circle we count one round (
                //(not the most accurate but don't know what around the circle means when the
                //order reverses so often
                if (nextPlayer == circle.getPlayer()) {
                    circleRounds++;
                }

                //if the right to left
                if (deck.getLastDiscarded().isReverse() && didntHaveCard) {
                    if (numberOfPlayers > 2) {
                        nextPlayer = nextPlayer.getPrevPlayer();
                    }
                    //skipping the player if the card is skip
                    if (deck.getLastDiscarded().isSkip() && didntHaveCard) {
                        nextPlayer = nextPlayer.getNextPlayer();
                        didntHaveCard = true;
                    }
                    move(nextPlayer);

                    didntHaveCard = true;
                    //if left to right
                } else {
                    nextPlayer = nextPlayer.getNextPlayer();
                    //skipping the player if the card is skip
                    if (deck.getLastDiscarded().isSkip() && didntHaveCard) {
                        nextPlayer = nextPlayer.getNextPlayer();
                        didntHaveCard = true;
                    }
                    move(nextPlayer);
                }
            }
            //printing stats
            statistics();

            //getting the players out of the queue given that there is enough players
            repeatGame(actualNoOfPlayers);

            victory = false;
        }
    }

    /**
     * Gets the circle ready for the next game O1
     *
     * @param actualNoOfPlayers
     */
    private static void repeatGame(int actualNoOfPlayers){
        if (actualNoOfPlayers > 5) {
            Player player1 = bench.dequeue();
            //removing the loser and adding the first in the queue
            circle.remove(loser);
            circle.addUros(player1);
            bench.enqueue(loser);
        }
    }

    /**
     * Adds 7 cards to each persons hand On
     * @param numberOfPlayers
     */
    private static void addCards(int numberOfPlayers){
        Player player = circle.getPlayer();
        for (int i = 0; i < numberOfPlayers; i++) {
            player.setHand(new SinglyLinkedList<UnoCard>());
            for (int j = 0; j < 7; j++) {
                UnoCard card = deck.drawCard();
                player.addToHand(card);
            }
            //printing each players hand
            System.out.println(player.getName()+ ": " + player.getHand().toString());
            player = player.getNextPlayer();
        }
    }
    private static void statistics() {
        System.out.println("_______________________________ GAME OVER ___________________________________");
        loser = getLoser();
        System.out.println("Circle rounds: " + circleRounds);
        System.out.println("Winner: " + winner);
        System.out.println("Worst player: " + loser);
        System.out.println();
        circleRounds = 0;

    }

    /**
     * Finds the player with most cards in their hand
     * @return player with most cards in the hand
     */
    private static Player getLoser() {
        Player player = circle.getPlayer();
        Player sonja = circle.getPlayer();
        //in case of more than two people
        if (circle.getSize() > 2) {
            //go trough the circle and check each player
            while (player.getNextPlayer() != circle.getPlayer()) {
                if (player.getHand().getSize() > player.getNextPlayer().getHand().getSize()) {
                    sonja = player;
                }
                player = player.getNextPlayer();
            }
            return sonja;
        } else {
            //in case of only 2
            if (circle.getPlayer().getHandSize() > circle.getPlayer().getNextPlayer().getHandSize()) {
                return circle.getPlayer();
            } else {
                return circle.getPlayer().getNextPlayer();
            }
        }


    }

    /**
     *     method for handling each move On
     */
    private static void move(Player player) {
        //4a) give the user two or four cards
        if (deck.getLastDiscarded().isDrawTwo()) {
            for (int i = 0; i < 2; i++) {
                player.addToHand(deck.drawCard());
            }
        } else if (deck.getLastDiscarded().isWildDrawFour()) {
            for (int i = 0; i < 4; i++) {
                player.addToHand(deck.drawCard());
            }
        }
        /**
         * this part checks what cards can the user play in the next round
         */
        int z = 0;
        SinglyLinkedList hand = player.getHand();
        SinglyLinkedNode cardNode = hand.getHead();
        UnoCard card = (UnoCard) cardNode.getData();
        SinglyLinkedList<UnoCard> cards = new SinglyLinkedList<UnoCard>();
        boolean hasUseful = false;
        //finding cards from the hand that can be placed on the the discard pile
        System.out.println( player.getName()+ "'s move - hand size: " + hand.getSize());
        while (z < player.getHandSize()) {
            if (card.canBePlacedOn(deck.getLastDiscarded())) {
                cards.regularInsert(card);
                hasUseful = true;
            } else {
                cards.regularInsert(new UnoCard("cannot choose", 0));
            }
            //just avoiding null pointer in the last call
            if (z < player.getHandSize() - 1) {
                cardNode = cardNode.getNextNode();
            }
            //setting the next card
            card = (UnoCard) cardNode.getData();
            z++;
        }

        //if there are no cards the user can choose add one to their hand
        if (!hasUseful) {
            System.out.println("card on the table:" + deck.getLastDiscarded().toString());
            System.out.println(player.getName() + ": cards in your hand: " + player.getHand().toString());
            System.out.println(player.getName() + " have no cards AVAILABLE, you are getting an extra card");
            player.getHand().regularInsert(deck.drawCard());
            System.out.println(player.getName() + ": cards in your hand: " + player.getHand().toString() + "\n\n");
            didntHaveCard = false;
        } else {
            //else let the user choose which card hse wants to play
            Scanner scanner = new Scanner(System.in);
            System.out.println("card on the table:" + deck.getLastDiscarded().toString());
            System.out.println(player.getName() + ": cards in your hand: " + player.getHand().toString());
            System.out.println(player.getName() + " can choose from following cards, please tell me " +
                    "the index of the card you want to play (first card is #1): ");
            System.out.println(cards.toString());
            int userChoice = scanner.nextInt() - 1;
            //catching errors in user input
            while (userChoice > hand.getSize()) {
                System.out.println("You don't have that card, please enter a number less " +
                        "than " + hand.getSize());
                userChoice = scanner.nextInt() - 1;
            }
//            while (cards.getElementAtI(userChoice).getNumber()==0) {
//                System.out.println("You can't place that card, please choose another one");
//                userChoice = scanner.nextInt() - 1;
//            }

            //4d) discarding from users hand to the discard pile
            System.out.println("you played: " + player.cardFromHand(userChoice).toString()+"\n");
            deck.discardCard(player.cardFromHand(userChoice));
            //removing the card from the hand
            player.removeFromHand(userChoice);
        }
        //condition for victory - for our main game loop
        if (hand.getSize() == 0) {
            victory = true;
            winner = player;
        }
    }

    /**
     * adds players names to the array that will be sorted On
     *
     */
    private static void addPlayer(int numberOfPlayers) {
        String name = "";
        Scanner scan1 = new Scanner(System.in);
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Player " + (i + 1) + " name: ");
            name = scan1.nextLine();
            arrayNames[i] = name;
        }
    }
}
