import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BlackJackActions {
	
	public static ArrayList<Card> buildDeck(ArrayList<Card> deck) {
        deck = new ArrayList<Card>();
        String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] types = {"C", "D", "H", "S"};

        for (String type : types) {
            for (String value : values) {
                deck.add(new Card(value, type));
            }
        }
        return deck;
    }
	
	public static ArrayList<Card> shuffleDeck(ArrayList<Card> deck) {
		Random random = new Random(); // Shuffle deck
		Collections.shuffle(deck, random);
		
        return deck;
    }
	
}
