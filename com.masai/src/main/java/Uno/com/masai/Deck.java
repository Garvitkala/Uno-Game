package Uno.com.masai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Data;
@Data
public class Deck {
	public List<Card> cards;

	
    
    public Deck() {
        cards = new ArrayList<>();
    }
    
    public void initializeDeck(int numberOfNumberedCards, int numberOfSpecialCards) {
        for (Color color : Color.values()) {
            if (color != Color.WILD) {
                for (int i = 0; i <= 9; i++) {
                    for (int j = 0; j < numberOfNumberedCards; j++) {
                        cards.add(new Card(color, String.valueOf(i), false));
                    }
                }
                for (int j = 0; j < numberOfSpecialCards; j++) {
                    cards.add(new Card(color.WILD, "+4", true));
                    cards.add(new Card(color, "+2", true));
                    cards.add(new Card(color, "Skip", true));
                    cards.add(new Card(color, "Reverse", true));
                    cards.add(new Card(Color.WILD, "Change Color", true));
                }
            }
        }
    	System.out.println("Deck is ready");

        
    }
    
    
    public void shuffleCards() {
    	System.out.println("Deck is shuffled");
    	Collections.shuffle(cards);
      
    }
    public void replenishDeck() {

        initializeDeck(1, 1); 
        shuffleCards();
    	System.out.println("Deck is remplenished");

    }
    
    public Card drawCard() {
        if (!cards.isEmpty()) {

            return cards.remove(0);
            
        }else {replenishDeck();}
        return cards.remove(0);

    }
}
