import java.util.*;
//the time I put on coding  homework4 is less than before , thanks god so much because I am afraid that I can't hang out the homework on time.
public class Player extends  Person {
	private String name;// 玩家名字
	private int chips;// 玩家的總籌碼
	private int bet;//玩家下注的籌碼
	/*private ArrayList<Card> oneRoundCard=new ArrayList<Card>();// 此局的卡*/
	
	
	
	
	
	public Player(String name, int chips) {
		this.name=name;
		this.chips=chips;
	}
	
	public String getName() {
		return name;
	}
	/*public void  setName() {
		
		Scanner keyboard= new Scanner(System.in);
		System.out.println("what's your name?");
		name=keyboard.nextLine();
		
	}*/
	public int makeBet() {
		bet=1;
		if(chips<=0) {
			return 0;
		}
		
		return bet;
	}
	
	
	@Override
	public boolean hit_me(Table tb2) {
		
		if(getTotalValue()<=16) {
			return true;
		}else {
			return false;
		}
		
		
	}
	
	
	public  int getCurrentChips() {
		return chips;
	}
	public void increaseChips(int diff) {
		chips=chips+diff;
	}
	public void sayHello() {
		System.out.println("Hello , I am "+name+" .");
		System.out.println("I have "+chips+" chips");
	}

	/*@Override
	public boolean hit_me(Table table) {
		// TODO Auto-generated method stub
		
		return false;
	}*/
}
