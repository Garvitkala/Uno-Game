package Uno.com.masai;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
@Data
public class Player {
    private int playerId;
    private String name;
    private List<Card> hand;
    

    
    public Player(String name,int i) {
    	this.name=name;
    	this.playerId=i;
    	this.hand=new ArrayList<>();
    }
    public void drawCardFromDeck(Deck deck) {
    	System.out.println(name+" is drawing card from the deck");
        Card drawnCard = deck.drawCard(); 
        if (drawnCard != null) {
            hand.add(drawnCard); 
        }
        
    }
    
    public Card playCard(Card cardToPlay,Color color,String num) {
        if (cardToPlay.getColor()==color||cardToPlay.value.equals(num)) {
        	hand.remove(cardToPlay);
            System.out.println(name + " played " + cardToPlay);
            return cardToPlay; 
        	
           
        }else if(cardToPlay.getColor()==color.WILD) {
        	
        	 hand.remove(cardToPlay);
             System.out.println(name + " played " + cardToPlay);
             return cardToPlay; 
        	
        }
        return null; 
    }
}


