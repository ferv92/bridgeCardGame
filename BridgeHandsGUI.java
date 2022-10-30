package BridgeHandsGUI;

import java.awt.GridLayout;
import java.awt.Image;
import java.util.Comparator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BridgeHandsGUI {

	public static void main(String[] args) {
		
		final int CARDSPERPLAYER = 13;	
		int count = 0;
		
		Comparator <Card> bridgeComparator = new Comparator <Card> () {
		public int compare(Card a, Card b) {
			return  (b.getSuit().ordinal() * 13 + b.getRank().ordinal()) - (a.getSuit().ordinal() * 13 + a.getRank().ordinal());
			}
		};

		ListInterface<Card> player1Deck = new SortedABList<Card>(bridgeComparator);
		ListInterface<Card> player2Deck = new SortedABList<Card>(bridgeComparator);
		ListInterface<Card> player3Deck = new SortedABList<Card>(bridgeComparator);
		ListInterface<Card> player4Deck = new SortedABList<Card>(bridgeComparator);
		
		CardDeck deck = new CardDeck();
		deck.shuffle();
		
		while(CARDSPERPLAYER > count) {
			player1Deck.add(deck.nextCard());
			count++;
		}
		count = 0;
		
		while(CARDSPERPLAYER > count) {
			player2Deck.add(deck.nextCard());
			count++;
		}
		count = 0;
		while(CARDSPERPLAYER > count) {
			player3Deck.add(deck.nextCard());
			count++;
		}
		count = 0;
		
		while(CARDSPERPLAYER > count) {
			player4Deck.add(deck.nextCard());
			count++;
		}
		
		//Prints out the CLI format of bridge cards
		System.out.println(" PLAYER 1 \t PLAYER2 \t PLAYER3 \t PLAYER4");
		System.out.println("----------\t-----------\t----------\t-----------");
		for(int i = 0; i < CARDSPERPLAYER; i++) {
			System.out.println(player1Deck.get(i) + "\t" + player2Deck.get(i) + "\t" + player3Deck.get(i) + "\t" + player4Deck.get(i));
		}
		
		
		//Calculates score for each player
		int player1Result = checkScore(player1Deck);
		int player2Result = checkScore(player2Deck);
		int player3Result = checkScore(player3Deck);
		int player4Result = checkScore(player4Deck);
		
		String player1 = Integer.toString(player1Result);
		String player2 = Integer.toString(player2Result);
		String player3 = Integer.toString(player3Result);
		String player4 = Integer.toString(player4Result);
		
		
		//GUI Part of the program --------------------------------
		
		
		JFrame frame = new JFrame("Bridge Hands GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1500, 800);
		
		JPanel panel = new JPanel(new GridLayout(4, 14, 0, 1));
		
		ImageIcon image;
		ImageIcon finalImage;
		Image copy;
		Image scaledFit;
		
		//Adding players scores to JLabels
		JLabel first = new JLabel();
		first.setText(player1);	
		first.setVerticalAlignment(JLabel.CENTER);
		first.setHorizontalAlignment(JLabel.CENTER);
		JLabel second = new JLabel();
		second.setText(player2);
		second.setVerticalAlignment(JLabel.CENTER);
		second.setHorizontalAlignment(JLabel.CENTER);
		JLabel third = new JLabel();
		third.setText(player3);
		third.setVerticalAlignment(JLabel.CENTER);
		third.setHorizontalAlignment(JLabel.CENTER);
		JLabel fourth = new JLabel();
		fourth.setText(player4);
		fourth.setVerticalAlignment(JLabel.CENTER);
		fourth.setHorizontalAlignment(JLabel.CENTER);
		
		panel.add(first);
		for(Card c : player1Deck) {
			image = c.getImage();
			copy = image.getImage();
			scaledFit = copy.getScaledInstance(110, 190, Image.SCALE_SMOOTH);
			finalImage = new ImageIcon(scaledFit);
			panel.add(new JButton(finalImage));	
		}
		panel.add(second);
		for(Card c : player2Deck) {
			image = c.getImage();
			copy = image.getImage();
			scaledFit = copy.getScaledInstance(110, 190, Image.SCALE_SMOOTH);
			finalImage = new ImageIcon(scaledFit);
			panel.add(new JButton(finalImage));	
		}
		panel.add(third);
		for(Card c : player3Deck) {
			image = c.getImage();
			copy = image.getImage();
			scaledFit = copy.getScaledInstance(110, 190, Image.SCALE_SMOOTH);
			finalImage = new ImageIcon(scaledFit);
			panel.add(new JButton(finalImage));	
		}
		panel.add(fourth);
		for(Card c : player4Deck) {
			image = c.getImage();
			copy = image.getImage();
			scaledFit = copy.getScaledInstance(110, 190, Image.SCALE_SMOOTH);
			finalImage = new ImageIcon(scaledFit);
			panel.add(new JButton(finalImage));	
		}
		
		frame.add(panel);
	
		frame.setVisible(true);

	}
	
	//Method to help calculate the scores for each player 
	public static int checkScore (ListInterface<Card> playerCards) {
		
		int score = 0;
		
		for(Card copy : playerCards) {
			if(copy.getRank().ordinal() == 12) {
				score += 4;
			}
			else if (copy.getRank().ordinal() == 11) {
				score += 3;
			}
			else if (copy.getRank().ordinal() == 10) {
				score += 2;
			}
			else if (copy.getRank().ordinal() == 9) {
				score++;
			}
		}
		
		int suitClub = 0;
		int suitDiamond = 0;
		int suitHeart = 0;
		int suitSpade = 0;
		
		for (Card copy2 : playerCards) {
			if (copy2.getSuit().ordinal() == 0) {
				suitClub++;
			}
			else if (copy2.getSuit().ordinal() == 1) {
				suitDiamond++;
			}
			else if (copy2.getSuit().ordinal() == 2) {
				suitHeart++;
			}
			else if (copy2.getSuit().ordinal() == 3) {
				suitSpade++;
			}
		}
		
		if(suitClub == 13 || suitDiamond == 13 || suitHeart == 13 || suitSpade == 13) {
			score += 9;
		}
		if (suitClub == 12 || suitDiamond == 12 || suitHeart == 12 || suitSpade == 12) {
			score += 8;
		}
		if (suitClub == 11 || suitDiamond == 11 || suitHeart == 11 || suitSpade == 11) {
			score += 7;
		}
		if (suitClub == 10 || suitDiamond == 10 || suitHeart == 10 || suitSpade == 10) {
			score += 6;
		}
		if (suitClub == 9 || suitDiamond == 9 || suitHeart == 9 || suitSpade == 9) {
			score += 5;
		}
		if (suitClub == 8 || suitDiamond == 8 || suitHeart == 8 || suitSpade == 8) {
			score += 4;
		}
		if (suitClub == 7 || suitDiamond == 7 || suitHeart == 7 || suitSpade == 7) {
			score += 3;
		}
		if (suitClub == 6 || suitDiamond == 6 || suitHeart == 6 || suitSpade == 6) {
			score += 2;
		}
		if (suitClub == 5 || suitDiamond == 5 || suitHeart == 5 || suitSpade == 5) {
			score += 1;
		}
 
		
		return score;
	}
	
}
