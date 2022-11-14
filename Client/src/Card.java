import javax.swing.ImageIcon;
import javax.swing.JLabel;

class Card {
	private int cardNum;
	private String cardSrc;
	private String backSrc = "src/cards/Back.png";
	private ImageIcon cardIcon;
	private JLabel card;
	
	/*Setter �޼���*/
	public void setCardNum(int cardNum) {
		this.cardNum = cardNum;
	}
	public void setCardSrc(int srcNum) {
		this.cardSrc = "src/cards/" + srcNum + ".png";
	}
	public void setCardIcon(String src) {
		this.cardIcon = new ImageIcon(src);
	}
	public void setCard() {
		this.card.setIcon(this.cardIcon);
	}
	/*Setter �޼���*/
	
	/*Getter �޼���*/
	public int getCardNum() {
		return this.cardNum;
	}
	public String getCardSrc() {
		return this.cardSrc;
	}
	public String getBackSrc() {
		return this.backSrc;
	}
	public JLabel getCard() { //
		return this.card;
	}
	/*Getter �޼���*/
	
	public Card(int num) { //������
		this.card = new JLabel(Integer.toString(cardNum));
		setCardNum(num);
		setCardIcon(backSrc);
		setCard();
	}
}