import java.util.*;

class Deck {
	public int c1 = 0;
	public int c2 = 0;
	private ArrayList<Card> cards ;
	private ArrayList<Card> usedCard ;
	private ArrayList<Card> openCard;//存放打開的牌，且在洗牌時要清空
	
	public int nUsed = 0;
	
	// TODO: Please implement the constructor (30 points)
	public Deck(int nDeck) {
		cards = new ArrayList<Card>();
		usedCard = new ArrayList<Card>();
		openCard = new ArrayList<Card>();
		
		// 1 Deck have 52 cards, https://en.wikipedia.org/wiki/Poker
		// Hint: Use new Card(x,y) and 3 for loops to add card into deck
		// Sample code start
		// Card card=new Card(1,1); ->means new card as clubs ace
		// cards.add(card);
		// Sample code end

		for (int i = 0; i < nDeck; i++) {
		for(int x=0;x<4;x++) {
				for (int y = 1; y < 14; y++) {
					if(x==0) {
						Card card = new Card(Card.Suit.Clubs, y);
						cards.add(card);
					}else if(x==1) {
						Card card=new Card(Card.Suit.Diamonds,y);
						cards.add(card);
					}else if(x==2) {
						Card card=new Card(Card.Suit.Hearts,y);
						cards.add(card);
					}else if(x==3) {
						Card card=new Card(Card.Suit.Spades,y);
						cards.add(card);
					}
					
					

				}
		}
		}

	}

	public Deck() {
		// TODO Auto-generated constructor stub
	}

	public Card getOneCard(boolean isOpened) {
		Card n = new Card(null, 0);
		Random nc = new Random();
		if (nUsed == 52) {
			shuffle();
		}

		n= cards.get(nc.nextInt(52));
		
		for (int j = 0; j < usedCard.size(); j++) {
			while (n.equals(usedCard.get(j))) {
				n = cards.get(nc.nextInt(52));
			}
		}
		/*
		 * while(n.equals(usedCard)) { n=cards.get(nc.nextInt(52)); }
		 */
		usedCard.add(n);
		cards.remove(n);
		nUsed++;
		/*c1=c1+1;*/
		
		//把打開的牌加入openCard
		if(isOpened==true) {
			openCard.add(n);
		}
		
		return n;
	}

//交換 cards 的位置
	public void shuffle() {
		Random rnd = new Random();
		int rp = rnd.nextInt(52);
		Card rtemp = new Card(null, 0);
		Card emp = new Card(null, 0);

		for (int i = 0; i < 52; i++) {
			while (i == rp) {
				rp = rnd.nextInt(52);
			}
			rtemp = cards.get(rp);
			cards.set(rp, cards.get(i));
			cards.set(i, rtemp);

		}

		usedCard.add(emp);
		nUsed = 0;
		//清空openCard的內容
		/*for(int i=0;i<openCard.size();i++) {
			openCard.set(i, null);
		}*/
		openCard.clear();
	}

	// TODO: Please implement the method to print all cards on screen (10 points)
	public void printDeck() {
		// Hint: print all items in ArrayList<Card> cards,
		// TODO: please implement and reuse printCard method in Card class (5 points)
		for (int i = 0; i < 52; i++) {
			//
			// Card n=cards.get(i);
			// //System.out.println(n.getSuit()+","+n.getRank());
			// n.printCard();
			Card n = new Card(null, 0);
			n = cards.get(i);
			n.printCard();

		}

	}

	public ArrayList<Card> getAllCards() {
		return cards;
	}
	//回傳打開的牌
	public ArrayList<Card> getOpenedCard(){
			return openCard;
	}
}