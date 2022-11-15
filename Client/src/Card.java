import javax.swing.ImageIcon;
import javax.swing.JLabel;

class Card {
	/* Card ��ü�� Swing component */
	private String cardSrc;
	private ImageIcon cardIcon;
	private JLabel card;
	/* Card ��ü�� card.setBounds�� ���� ��ġ, ũ�� ��*/
	private int x;
	private int y;
	private int width;
	private int height;
	
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
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	/*Setter �޼���*/
	
	/*Getter �޼���*/
	public String getCardSrc() {
		return this.cardSrc;
	}
	public JLabel getCard() {
		return this.card;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	/*Getter �޼���*/
	
	public void backside() {
		this.cardSrc = "src/cards/Back.png";
		setCardIcon(cardSrc);
		setCard();
	}
	
	public void drawCard() {
		this.card.setBounds(x, y, width, height);
	}
	
	public Card(int x, int y, int w, int h) { //������
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.card = new JLabel();
		
	}
	
	
}