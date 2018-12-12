import java.util.ArrayList;

public class Table {

	final int MAXPLAYER = 4;  // �@�i��l�̦h4�ӤH
	 private Deck atcard; //�s�W�@��DECK����s��atcard meaning allthecard
	 
	 
	 private Player[] atplayer ; //meaning alltheplayer
	 private Dealer thehoster;    // meaning ���a
 int[] pos_betArray = new int[MAXPLAYER];   //new �@�Ӱ}�C�ΨӸ�player���U��
	 


	public Table(int snnDeck) {
		 atcard = new Deck(snnDeck);  //�s�W�F�|�ƵP �M��s��allthecard�̭�
		
		atplayer = new Player[MAXPLAYER];  //�����atplayer �榡�O�}�C �M�ᦳ�|�ӤH
		
	}

	public void set_player(int pos, Player p) {
		
			atplayer[pos] = p;//�]�w���a������ �M���player����[�i�h
		
	}

	public Player[] get_player() {//�Ҧ����a��getter
		return atplayer;
	}

	public void set_dealer(Dealer d) {//new�X�Ӥ@�ӷs�����a���� �M��A���L����׶i�Ӫ�dealer����
		   thehoster = new Dealer();
		
		
		thehoster = d;
	}

	public Card get_face_up_card_of_dealer() { // *****�����A�g*****
		
		Card faceupCard = thehoster.getOneRoundCard().get(1);//mew�@�ӷs�d �M�����L����a���o�Ʊƪ��ĤG�ӵP�æ^��
		return faceupCard;

	}

	private void ask_each_player_about_bets() {
		for (int i = 0; i < MAXPLAYER; i++) {//�q0~4 �}�l �n�D�C�Ӫ��a�����۩I �M��A��L�̪���`��i pos_betaray�o�Ӱ}�C�̭�
			atplayer[i].sayHello();
			pos_betArray[i] = atplayer[i].makeBet();

		}
	}

	private void distribute_cards_to_dealer_and_players() {
		/*
		 
		 */
		atcard.shuffle();//��Ҧ����Ƭ~�P
		
		
		

		for(Player pp : atplayer) {//��atplayer�̭��Ҧ������a �̧� ����ƦU�۪��d���}�C�M��   �M��s�W�i�h��i���}���d
			ArrayList<Card> playercard = new ArrayList<Card>();
			playercard.add(atcard.getOneCard(true));
			playercard.add(atcard.getOneCard(true));
			pp.setOneRoundCard(playercard); //��W����i���}���Ƴz�Lplayercard �[�J�C�Ӫ��a�U�۪�oneroundcard arraylist
			
		}
		
		
		
		
	
		
		ArrayList<Card> dealercard = new ArrayList<Card>(); // ����� �@�� �s�����a���d������
		dealercard.add(atcard.getOneCard(false));//�s�W�@�i�\�۪��d
		dealercard.add(atcard.getOneCard(true));//�A�s�W�@�i���}���d
		thehoster.setOneRoundCard(dealercard);//�M��z�Ldealercard�[�J���a��oneroundcard
		
		
		
		System.out.print(
				"Dealer's face up card is : "); 
				
		get_face_up_card_of_dealer().printCard();//�L�X���a���ĤG�i�P
	}

	private void ask_each_player_about_hits() {
		for(Player pp: atplayer) {//��Ҧ������a�׶i�h
			boolean hit = false;
	ArrayList<Card> playercard = new ArrayList<Card>();//����ƪ��a���d
System.out.println(pp.getName()+" 's cards now is : ");//��U�Ӫ��a���W�r�٦��{�b�������d�L�X��
			for(Card c:pp.getOneRoundCard()) {
				c.printCard();
			}
			
			do {
				hit = pp.hit_me(this);//��hit ���P �{�b�o��table�����a��hit_me���i�Ӫ����L�Ѽ�
				if(hit == true) {//�p�G�O����n��
					playercard = pp.getOneRoundCard();//�N��playercard ���P�o�Ӫ��a��oneroundcard 
			playercard.add(atcard.getOneCard(true));// �M��W�[�@�i�s���ƨ�playercard
			
			
			
					System.out.println("hit! "+pp.getName()+" 's cards now is :");//�L�X�ثe���a�{�����Ҧ���
					for(Card c : playercard) {
						c.printCard();
					}
					pp.setOneRoundCard(playercard);//��s���a��oneroundcard
					if(pp.getTotalValue()>21) {//�p�G�{�b���a���I�Ƥw�g�j��21 �N��hit�]��flase �M��L�Xhit is over
						hit = false;
						System.out.println(pp.getName()+" 's hit is over!");
						
					}else if(pp.getTotalValue()<=21&&pp.getTotalValue()>16) {//�p�G���a���I�Ƥ���15��21���� ���N�L�Xpass hit  and hit is over
						System.out.println("pass hit!");
						System.out.println(pp.getName()+" 's hit is over!");
					}
				}
				
			}while(hit == true);//�p�G �~��n�ƴN���� �@��
			//System.out.println(pp.getName()+" 's hit is over!");
			//System.out.println("pass hit!");
			
			
			
		}
		
		
		
		
		

	}

	private void ask_dealer_about_hits() {
		boolean hit2 = false;

		ArrayList<Card> dealercard = new ArrayList<Card>();//�s�W�@�Ӳ��a���d������
		
		do {
			hit2 = thehoster.hit_me(this);//���o�i��l�����a�O�_��ܭn�ƪ����׳]�w��hit2

			if (hit2 == true) {//�p�G���a�n��
				dealercard = thehoster.getOneRoundCard();//��dealercard ���P ���a��oneroundcard
				dealercard.add(atcard.getOneCard(true));// �����a�@�i���}����
				thehoster.setOneRoundCard(dealercard);//�]�wdealercard�����a��oneroundcard 
				
				if(thehoster.getTotalValue() >21) {//�p�G���a�����z���N���n�b�s�P�F
					hit2 = false;

			} 
			}
		} while (hit2 == true);
		System.out.println("dealer's hit is over!");
	}

	private void calculate_chips() {
		System.out.println("dealer's card value is "+thehoster.getTotalValue()+ " , cards : ");//����a���d���I���٦����a���d�����L�X��
		thehoster.printAllCard();
		
		for(int i=0;i<MAXPLAYER;i++) {
		System.out.println(atplayer[i].getName()+" 's cards : ");//�̷Ӷ��� ��U�Ӫ��a���W�r �M�P �٦��I�ƦL�X��
		System.out.print(atplayer[i].getName()+" card value is "+ atplayer[i].getTotalValue());
		
		if(atplayer[i].getTotalValue() <= 21 && thehoster.getTotalValue() <= 21) {//���]���a�M���a���Ƴ��S���z��
		
			if(atplayer[i].getTotalValue()<thehoster.getTotalValue()) {//�p�G���a���Ƥ���a�p
			System.out.print(" , Loss , "+ pos_betArray[i]+" chips");//�L�X��h�ֿ�
				atplayer[i].increaseChips(- pos_betArray[i]);//�ܰ��w�X
			System.out.println(" , the chips now is : "+atplayer[i].getCurrentChips());//�L�X�Ӫ��a�ثe���w�X
			}else if(atplayer[i].getTotalValue()>thehoster.getTotalValue()){//�p�G���a���I�Ƥj����a
			System.out.print(" , Get , "+pos_betArray[i]+" chips");//�L�X�@�h�ֿ�
				atplayer[i].increaseChips(pos_betArray[i]);//����w�X
			System.out.println(" , the chips now is : "+atplayer[i].getCurrentChips());//�L�X�Ӫ��a�{�b���h���w�X
			}else {
			System.out.println(" , chips have no change! the chips now is "+atplayer[i].getCurrentChips());//���]���� �N�L�X�{�b�w�X�S���� �M����I�ƨ̼˦L�X��
			}
		
		}else if(atplayer[i].getTotalValue() > 21 && thehoster.getTotalValue()<=21) {//�p�G���a���P���F
			System.out.print(" , Loss , "+ pos_betArray[i]+" chips");//�L�X���a��h�ֿ�
			atplayer[i].increaseChips(- pos_betArray[i]);//�ܰ��w�X
		System.out.println(" , the chips now is : "+atplayer[i].getCurrentChips());//�L�X�{�b���w�X
		}else if(atplayer[i].getTotalValue() <= 21 && thehoster.getTotalValue() > 21) {//�p�G�O���a�z��
			System.out.print(" , Get , "+ pos_betArray[i]+" chips");//�L�X���aĹ�h�ֿ�
			atplayer[i].increaseChips(+ pos_betArray[i]);//�ܰ��w�X
		System.out.println(" , the chips now is : "+atplayer[i].getCurrentChips());//��{�b�Ӫ��a���I�ƦL�X��
		}else {
			System.out.println(" , chips have no change! the chips now is "+atplayer[i].getCurrentChips());//�_�h �N�L�X�{�b���� �M��A��ثe�Ӫ��a���w�X�L�X��
		}
		
		
		}
		
		
	}
	
	public int[] get_players_bet() {
		return pos_betArray;//�^�ǤU�`�����B
	}
	
	public void play() {
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}
	
}
