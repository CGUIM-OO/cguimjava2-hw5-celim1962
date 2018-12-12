

class Card {

	public enum Suit {
		Clubs, Diamonds, Hearts, Spades
	}

	private Suit suit; // Definition: 1~4, Clubs=1, Diamonds=2, Hearts=3, Spades=4
	private int rank; // 1~13

	/**
	 * @param s suit
	 * @param r rank
	 */
	public Card(Suit s, int r) {
		suit=s;
		rank = r;
	}

	// TODO: 1. Please implement the printCard method (20 points, 10 for suit, 10
	// for rank)
	public void printCard() {
		// Hint: print (System.out.println) card as suit,rank, for example: print 1,1 as
		// Clubs Ace
		
		if(rank==1) {
			System.out.println(getSuit()+","+"Ace");
		}else {
		 
		System.out.println(getSuit() + "," + getRank());
		}
	}

	public Suit getSuit() {
	
		
		return suit;
	}

	public int getRank() {
		
		
		return rank;
	}
	
}