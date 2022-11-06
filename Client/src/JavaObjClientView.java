import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Image;
import java.awt.Color;

public class JavaObjClientView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String UserName;
	private static final int BUF_LEN = 128; // Windows ó�� BUF_LEN �� ����
	private Socket socket; // �������
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	public JavaObjClientView(String username, String ip_addr, String port_no) {
		/*������ Ŭ���̾�Ʈ ���� ���� �κ�*/
		AppendText("User " + username + " connecting " + ip_addr + " " + port_no);
		UserName = username;

		try {
			socket = new Socket(ip_addr, Integer.parseInt(port_no));

			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(socket.getInputStream());

			ChatMsg obcm = new ChatMsg(UserName, "100", "Hello");
			SendObject(obcm);
			
			ListenNetwork net = new ListenNetwork();
			net.start();

		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			AppendText("connect error");
		}
		/*������ Ŭ���̾�Ʈ ���� ���� �κ�*/
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 630);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 255, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*ȭ��*/
		ImageIcon imgicon1 = new ImageIcon("src/card.png");
		Image img1 = imgicon1.getImage();
		Image img2 = img1.getScaledInstance(100, 140, java.awt.Image.SCALE_SMOOTH);
		ImageIcon imgicon2 = new ImageIcon(img2);
		
		JLabel card;
		JLabel card2;
		
		card = new JLabel("New label");
		card.setBounds(200, 400, 100, 140);
		card.setIcon(imgicon2);
		contentPane.add(card);
		
		card2 = new JLabel("New label");
		card2.setBounds(200, 10, 100, 140);
		card2.setIcon(imgicon2);
		contentPane.add(card2);
		setVisible(true);
		/*ȭ��*/
	}

	class ListenNetwork extends Thread { // Server Message�� �����ؼ� ȭ�鿡 ǥ��
		public void run() {
			while (true) {
				try {
					Object obcm = null;
					String msg = null;
					ChatMsg cm;
					try {
						obcm = ois.readObject();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						break;
					}
					if (obcm == null)
						break;
					if (obcm instanceof ChatMsg) {
						cm = (ChatMsg) obcm;
						msg = String.format("[%s] %s", cm.getId(), cm.getData());
					} else
						continue;
				} catch (IOException e) {
					AppendText("ois.readObject() error");
					try {
						ois.close();
						oos.close();
						socket.close();
						break;
					} catch (Exception ee) {
						break;
					} // catch�� ��
				} // �ٱ� catch����

			}
		}
	}

	public void AppendText(String msg) { // ȭ�鿡 ���
		msg = msg.trim();
	}

	public void SendObject(Object ob) { // ������ �޼����� ������ �޼ҵ�
		try {
			oos.writeObject(ob);
		} catch (IOException e) {
			AppendText("SendObject Error");
		}
	}
}