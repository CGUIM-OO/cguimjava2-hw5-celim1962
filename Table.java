import java.util.ArrayList;

public class Table {

	final int MAXPLAYER = 4;  // 一張桌子最多4個人
	 private Deck atcard; //新增一個DECK物件叫做atcard meaning allthecard
	 
	 
	 private Player[] atplayer ; //meaning alltheplayer
	 private Dealer thehoster;    // meaning 莊家
 int[] pos_betArray = new int[MAXPLAYER];   //new 一個陣列用來裝player的下住
	 


	public Table(int snnDeck) {
		 atcard = new Deck(snnDeck);  //新增了四副牌 然後存到allthecard裡面
		
		atplayer = new Player[MAXPLAYER];  //實體化atplayer 格式是陣列 然後有四個人
		
	}

	public void set_player(int pos, Player p) {
		
			atplayer[pos] = p;//設定玩家的順序 然後把player物件加進去
		
	}

	public Player[] get_player() {//所有玩家的getter
		return atplayer;
	}

	public void set_dealer(Dealer d) {//new出來一個新的莊家物件 然後再讓他等於匯進來的dealer物件
		   thehoster = new Dealer();
		
		
		thehoster = d;
	}

	public Card get_face_up_card_of_dealer() { // *****等等再寫*****
		
		Card faceupCard = thehoster.getOneRoundCard().get(1);//mew一個新卡 然後讓他於莊家的這副排的第二個牌並回傳
		return faceupCard;

	}

	private void ask_each_player_about_bets() {
		for (int i = 0; i < MAXPLAYER; i++) {//從0~4 開始 要求每個玩家先打招呼 然後再把他們的賭注丟進 pos_betaray這個陣列裡面
			atplayer[i].sayHello();
			pos_betArray[i] = atplayer[i].makeBet();

		}
	}

	private void distribute_cards_to_dealer_and_players() {
		/*
		 
		 */
		atcard.shuffle();//把所有的排洗牌
		
		
		

		for(Player pp : atplayer) {//把atplayer裡面所有的玩家 依序 實體化各自的卡片陣列清單   然後新增進去兩張打開的卡
			ArrayList<Card> playercard = new ArrayList<Card>();
			playercard.add(atcard.getOneCard(true));
			playercard.add(atcard.getOneCard(true));
			pp.setOneRoundCard(playercard); //把上面兩張打開的排透過playercard 加入每個玩家各自的oneroundcard arraylist
			
		}
		
		
		
		
	
		
		ArrayList<Card> dealercard = new ArrayList<Card>(); // 實體化 一個 叫做莊家的卡的物件
		dealercard.add(atcard.getOneCard(false));//新增一張蓋著的卡
		dealercard.add(atcard.getOneCard(true));//再新增一張打開的卡
		thehoster.setOneRoundCard(dealercard);//然後透過dealercard加入莊家的oneroundcard
		
		
		
		System.out.print(
				"Dealer's face up card is : "); 
				
		get_face_up_card_of_dealer().printCard();//印出莊家的第二張牌
	}

	private void ask_each_player_about_hits() {
		for(Player pp: atplayer) {//把所有的玩家匯進去
			boolean hit = false;
	ArrayList<Card> playercard = new ArrayList<Card>();//實體化玩家的卡
System.out.println(pp.getName()+" 's cards now is : ");//把各個玩家的名字還有現在持有的卡印出來
			for(Card c:pp.getOneRoundCard()) {
				c.printCard();
			}
			
			do {
				hit = pp.hit_me(this);//讓hit 等同 現在這個table的玩家的hit_me為進來的布林參數
				if(hit == true) {//如果是打算要排
					playercard = pp.getOneRoundCard();//就讓playercard 等同這個玩家的oneroundcard 
			playercard.add(atcard.getOneCard(true));// 然後增加一張新的排到playercard
			
			
			
					System.out.println("hit! "+pp.getName()+" 's cards now is :");//印出目前玩家現有的所有排
					for(Card c : playercard) {
						c.printCard();
					}
					pp.setOneRoundCard(playercard);//更新玩家的oneroundcard
					if(pp.getTotalValue()>21) {//如果現在玩家的點數已經大於21 就把hit設為flase 然後印出hit is over
						hit = false;
						System.out.println(pp.getName()+" 's hit is over!");
						
					}else if(pp.getTotalValue()<=21&&pp.getTotalValue()>16) {//如果玩家的點數介於15到21之間 那就印出pass hit  and hit is over
						System.out.println("pass hit!");
						System.out.println(pp.getName()+" 's hit is over!");
					}
				}
				
			}while(hit == true);//如果 繼續要排就重來 一次
			//System.out.println(pp.getName()+" 's hit is over!");
			//System.out.println("pass hit!");
			
			
			
		}
		
		
		
		
		

	}

	private void ask_dealer_about_hits() {
		boolean hit2 = false;

		ArrayList<Card> dealercard = new ArrayList<Card>();//新增一個莊家的卡的物件
		
		do {
			hit2 = thehoster.hit_me(this);//讓這張桌子的莊家是否選擇要排的答案設定為hit2

			if (hit2 == true) {//如果莊家要排
				dealercard = thehoster.getOneRoundCard();//讓dealercard 等同 莊家的oneroundcard
				dealercard.add(atcard.getOneCard(true));// 給莊家一張打開的排
				thehoster.setOneRoundCard(dealercard);//設定dealercard韋莊家的oneroundcard 
				
				if(thehoster.getTotalValue() >21) {//如果莊家的排爆掉就不要在叫牌了
					hit2 = false;

			} 
			}
		} while (hit2 == true);
		System.out.println("dealer's hit is over!");
	}

	private void calculate_chips() {
		System.out.println("dealer's card value is "+thehoster.getTotalValue()+ " , cards : ");//把莊家的卡的點數還有莊家的卡全部印出來
		thehoster.printAllCard();
		
		for(int i=0;i<MAXPLAYER;i++) {
		System.out.println(atplayer[i].getName()+" 's cards : ");//依照順序 把各個玩家的名字 和牌 還有點數印出來
		System.out.print(atplayer[i].getName()+" card value is "+ atplayer[i].getTotalValue());
		
		if(atplayer[i].getTotalValue() <= 21 && thehoster.getTotalValue() <= 21) {//假設莊家和玩家的排都沒有爆掉
		
			if(atplayer[i].getTotalValue()<thehoster.getTotalValue()) {//如果玩家的排比莊家小
			System.out.print(" , Loss , "+ pos_betArray[i]+" chips");//印出輸多少錢
				atplayer[i].increaseChips(- pos_betArray[i]);//變動籌碼
			System.out.println(" , the chips now is : "+atplayer[i].getCurrentChips());//印出該玩家目前的籌碼
			}else if(atplayer[i].getTotalValue()>thehoster.getTotalValue()){//如果玩家的點數大於莊家
			System.out.print(" , Get , "+pos_betArray[i]+" chips");//印出一多少錢
				atplayer[i].increaseChips(pos_betArray[i]);//更改籌碼
			System.out.println(" , the chips now is : "+atplayer[i].getCurrentChips());//印出該玩家現在有多少籌碼
			}else {
			System.out.println(" , chips have no change! the chips now is "+atplayer[i].getCurrentChips());//假設平手 就印出現在籌碼沒偶變 然後把點數依樣印出來
			}
		
		}else if(atplayer[i].getTotalValue() > 21 && thehoster.getTotalValue()<=21) {//如果玩家的牌報了
			System.out.print(" , Loss , "+ pos_betArray[i]+" chips");//印出玩家輸多少錢
			atplayer[i].increaseChips(- pos_betArray[i]);//變動籌碼
		System.out.println(" , the chips now is : "+atplayer[i].getCurrentChips());//印出現在的籌碼
		}else if(atplayer[i].getTotalValue() <= 21 && thehoster.getTotalValue() > 21) {//如果是莊家爆掉
			System.out.print(" , Get , "+ pos_betArray[i]+" chips");//印出玩家贏多少錢
			atplayer[i].increaseChips(+ pos_betArray[i]);//變動籌碼
		System.out.println(" , the chips now is : "+atplayer[i].getCurrentChips());//把現在該玩家的點數印出來
		}else {
			System.out.println(" , chips have no change! the chips now is "+atplayer[i].getCurrentChips());//否則 就印出現在平手 然後再把目前該玩家的籌碼印出來
		}
		
		
		}
		
		
	}
	
	public int[] get_players_bet() {
		return pos_betArray;//回傳下注的金額
	}
	
	public void play() {
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}
	
}
