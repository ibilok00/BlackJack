import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class BlackJack {
	
	ArrayList<Card> deck;
	
	// dealer
	Card hiddenCard;
	ArrayList<Card> dealerHand;
	int dealerSum;
	int dealerAceCount;

	// player
	ArrayList<Card> playerHand;
	int playerSum;
	int playerAceCount;
	
    int cardWidth = 110; //ratio should 1/1.4
    int cardHeight = 154;
    
    boolean player21 = false;
    boolean playerBlackJack = false;
    boolean dealerBlackJack = false;
    
    JPanel buttonPanel = new JPanel();
    JButton hitButton = new JButton("Hit");
    JButton stayButton = new JButton("Stay");
    JFrame frame = new JFrame("BlackJack");
    
    public BlackJack() {
		
		startGame();
		
		setFrame();
        
        hitButton.addActionListener(e -> {
            playerDrawsCards();
            gamePanel.repaint();
        });
        
        stayButton.addActionListener(e -> {
            hitButton.setEnabled(false);
            stayButton.setEnabled(false);
            dealerDrawsCards();
            gamePanel.repaint();
        });
        
        gamePanel.repaint();
	}
    
	public void startGame() {
		deck = BlackJackActions.buildDeck(deck);
		deck = BlackJackActions.shuffleDeck(deck);
		
		//dealer
		dealerHand = new ArrayList<Card>();
		dealerSum = 0;
		dealerAceCount = 0;
		
		hiddenCard = deck.remove(deck.size()-1); // remove last card from the deck
		dealerSum += hiddenCard.getValue();
		dealerAceCount += hiddenCard.isAce() ? 1 : 0;
		
		Card card = deck.remove(deck.size()-1);
		dealerSum += card.getValue();
		dealerAceCount += card.isAce() ? 1 : 0;
		dealerHand.add(card);
		
		// Check if dealer has two Aces at the beginning
	    if (dealerSum > 21 && dealerAceCount == 2) {
	        dealerSum -= 10; // Adjust dealer sum by reducing one Ace to 1
	        dealerAceCount--; // Reduce Ace count
	    }
	    
	    // Check for dealer Blackjack
	    checkDealerBlackjack();
		
		// player
		playerHand = new ArrayList<Card>();
		playerSum = 0;
		playerAceCount = 0;
		
		for (int i = 0; i < 2; i++) {
            card = deck.remove(deck.size()-1);
            playerHand.add(card);
            playerSum += card.getValue();
            playerAceCount += card.isAce() ? 1 : 0;
        }
		playerSum = reducePlayerAce();
		
		// Check for player Blackjack
	    checkPlayerBlackjack();
		
		if(playerBlackJack) {
			
			hitButton.setEnabled(false);
    		stayButton.setEnabled(false);
		}
		
	}
    
    JPanel gamePanel = new JPanel() {
    	@Override
    	public void paintComponent(Graphics g) {
    		super.paintComponent(g);
    		
    		try {
    			
    			g.setFont(new Font("Arial", Font.PLAIN, 30));
            	g.setColor(Color.white);
    			// draw hidden card
    			Image hiddneCardImage = new ImageIcon(getClass().getResource("./cards/BACK.png")).getImage();
    			if(!stayButton.isEnabled()) {
    				hiddneCardImage = new ImageIcon(getClass().getResource(hiddenCard.getImagePath())).getImage();
    			}
    			g.drawImage(hiddneCardImage, 20, 20, cardWidth, cardHeight, null);
    			
    			// draw dealer's hand
    			for (int i = 0; i < dealerHand.size(); i++) {
                    Card card = dealerHand.get(i);
                    Image cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
                    g.drawImage(cardImg, cardWidth + 25 + (cardWidth + 5)*i, 20, cardWidth, cardHeight, null);
                }
    			
    			//draw player's hand
                for (int i = 0; i < playerHand.size(); i++) {
                    Card card = playerHand.get(i);
                    Image cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
                    g.drawImage(cardImg, 20 + (cardWidth + 5)*i, 320, cardWidth, cardHeight, null);
                    g.drawString("Player: " + Integer.toString(playerSum), 20, 500);
                }
                
                if(!stayButton.isEnabled()) {
                	dealerSum = reduceDealerAce();
                	playerSum = reducePlayerAce();
                	
                	String message = "";
                	if(player21 && dealerBlackJack) {
                		message = "You Lose!";
                	}
                	else if(playerBlackJack && dealerBlackJack) {
                		message = "Tie!";
                	}
                	else if(playerSum > 21) {
                		message = "You Lose!";
                	}
                	else if(dealerSum > 21) {
                		message = "You Win!";
                	}
                	// both , you and dealer have <= 21
                	else if(playerSum == dealerSum) {
                		message = "Tie!";
                	}
                	else if(playerSum > dealerSum) {
                		message = "You Win!";
                	}
                	else if(playerSum < dealerSum) {
                		message = "You Lose!";
                	}
                	
                	g.drawString("Dealer: " + Integer.toString(dealerSum), 20, 200);
                	g.setFont(new Font("Arial", Font.PLAIN, 50));
                	g.setColor(Color.white);
                	g.drawString(message, 220, 250);
                }
    			
    		}
    		catch (Exception e){
    			e.printStackTrace();
    		}
    	}
    };
    
    public void setFrame() {
		frame.setVisible(true);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel.setLayout(new BorderLayout());
        gamePanel.setBackground(new Color(53, 101, 77));
        frame.add(gamePanel);
        
        hitButton.setFocusable(false);
        buttonPanel.add(hitButton);
        stayButton.setFocusable(false);
        buttonPanel.add(stayButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void playerDrawsCards() {
		Card card = deck.remove(deck.size()-1);
		playerSum += card.getValue();
		playerAceCount += card.isAce() ? 1 : 0;
		playerHand.add(card);
		
		if(reducePlayerAce() >= 21) {
			if(playerSum==21) {
				player21=true;
				dealerDrawsCards();				
			}
			hitButton.setEnabled(false);
    		stayButton.setEnabled(false);
		}
	}
	
	public void dealerDrawsCards() {
		while(dealerSum < 17) {
			Card card = deck.remove(deck.size()-1);
			dealerSum += card.getValue();
			dealerAceCount += card.isAce() ? 1 : 0;
			dealerHand.add(card);
			dealerSum = reduceDealerAce();
		}
	}
    
    public int reducePlayerAce() {
    	while(playerSum > 21 && playerAceCount > 0) {
    		playerSum -= 10;
    		playerAceCount -= 1;
    	}
    	return playerSum;
    }
    
    public int reduceDealerAce() {
    	while(dealerSum > 21 && dealerAceCount > 0) {
    		dealerSum -= 10;
    		dealerAceCount -= 1;
    	}
    	return dealerSum;
    }
    
    public void checkDealerBlackjack() {
    	dealerBlackJack = (dealerSum == 21);
    }
    
    public void checkPlayerBlackjack() {
    	playerBlackJack = (playerSum == 21);
    }
    
}
