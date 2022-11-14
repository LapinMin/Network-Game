import javax.swing.ImageIcon;
import javax.swing.JLabel;

class Card {
	private String cardSrc;
	private String backSrc = "src/cards/Back.png";
	private ImageIcon cardIcon;
	private JLabel card;
	
	/*Setter �޼���*/
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
	
	public Card() { //������
		this.card = new JLabel();
		setCardIcon(backSrc);
		setCard();
	}
}