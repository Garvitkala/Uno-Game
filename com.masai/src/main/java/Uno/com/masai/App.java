package Uno.com.masai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class App 
{
    static Deck deck = new Deck();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) 
    {
        System.out.println("Welcome to the game");
        System.out.println("Enter the number of players");
        int n = sc.nextInt();
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 1; i <= n; i++) 
        {
            System.out.println("Enter player " + i + " name");
            String name = sc.next();
            players.add(new Player(name, i));
        } 
        deck.initializeDeck(n, n);
        deck.shuffleCards();
        
        for (Player p : players) 
        {
            for (int i = 0; i < 10; i++) 
            {
                Card c = deck.drawCard();
                if (c == null) 
                {
                    deck.replenishDeck();
                }
                List<Card> cards = p.getHand();
                cards.add(c);
                p.setHand(cards);
            }
        }
        
        Player winner = play(players);
        System.out.println(winner);
    }

    public static Player play(ArrayList<Player> players) 
    {
    	
    	 
    	
        Color color = deck.drawCard().getColor();
        String value = null;
        boolean add4 = false;
        boolean add2 = false;
        boolean changec = false;
        int direction = 1;
        boolean skip = false;
        int currentPlayerIndex = 0;
        String num = deck.drawCard().getValue();

        while (true) 
        {
            for (Player p : players) 
            {
                if (skip) 
                {
                    skip = false;
                    continue;
                }
                System.out.println("Its player " + p.getName() + "'s turn");
                System.out.println("Select the card you want to play");
                System.out.println("The colour is " + color);
                System.out.println("The Number is " + num);

                List<Card> cards = p.getHand();

                if (add4) 
                {
                    for (int i = 0; i < 4; i++) 
                    {
                        Card c2 = deck.drawCard();
                        cards.add(c2);
                        p.setHand(cards);
                        System.out.println("Card " + c2 + " is Drawn by " + p.getName());
                    }
                    add4 = false;
                    continue;
                }

                if (add2) 
                {
                    for (int i = 0; i < 2; i++) 
                    {
                        Card c2 = deck.drawCard();
                        cards.add(c2);
                        p.setHand(cards);
                        System.out.println("Card " + c2 + " is Drawn by " + p.getName());
                    }
                    add2 = false;
                    continue;
                }

                for (int i = 1; i <= cards.size(); i++) 
                {
                    System.out.println("Press " + i + " to select " + cards.get(i - 1).getColor() + " " + cards.get(i - 1).value);
                }

                System.out.println("Press 0 to draw from the deck");

                int choice = sc.nextInt();
                if (choice != 0) 
                {
                    Card c = p.playCard(cards.get(choice - 1), color, num);
                    if (cards.size() == 0) 
                    {
                        return p;
                    }

                    if (c == null) 
                    {
                        System.out.println("This card is neither a wild card nor the same colour, as a penalty you receive an extra card");
                        Card c2 = deck.drawCard();
                        cards.add(c2);
                        p.setHand(cards);
                        System.out.println("Card " + c2 + " is Drawn by " + p.getName());
                        continue;
                    }
                    color = c.getColor();
                    value = c.getValue();
                    num = c.getValue();

                    if (c.getValue().equals("+4")) 
                    {
                        add4 = true;
                    } 
                    else if (c.getValue().equals("+2")) 
                    {
                        add2 = true;
                    } 
                    else if (c.getValue().equals("Skip")) 
                    {
                        skip = true;
                    } 
                    else if (c.getValue().equals("Reverse")) 
                    {
                    	   System.out.println(players);
                           Collections.reverse(players);
                           System.out.println(players);
                           skip=true;
                    	continue;
                    } 
                    else if (c.getValue().equals("Change Color")) 
                    {
                        changec = true;
                    }
                } 
                else if (choice == 0) 
                {
                    Card c = deck.drawCard();
                    cards.add(c);
                    p.setHand(cards);
                    System.out.println("Card " + c + " is Drawn");
                }
                while (color == color.WILD || changec == true) 
                {
                    System.out.println("Choose your colour");
                    System.out.println("Press 1 for RED");
                    System.out.println("Press 2 for BLUE");
                    System.out.println("Press 3 for GREEN");
                    System.out.println("Press 4 for YELLOW");
                    int k = sc.nextInt();
                    switch (k) 
                    {
                        case 1:
                            color = Color.RED;
                            break;
                        case 2:
                            color = Color.BLUE;
                            break;
                        case 3:
                            color = Color.GREEN;
                            break;
                        case 4:
                            color = Color.YELLOW;
                            break;
                        default:
                            System.out.println("Invalid choice. Defaulting to RED.");
                            color = Color.RED;
                            break;
                    }
                    System.out.println("You selected: " + color);
                    changec = false;
                }
            }
        }
    }
}
