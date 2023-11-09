package Uno.com.masai;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Card {
  
	public Color color;
    public String value;
    public Boolean special;
    
    public Card(Color color,String value,Boolean special) {
    	this.color=color;
    	this.value=value;
    	this.special=special;
    };
    
    
    
    
    
}