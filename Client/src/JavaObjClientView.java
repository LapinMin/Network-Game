import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JavaObjClientView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtInput;
	private String UserName;
	private JButton btnSend;
	private static final int BUF_LEN = 128; // Windows ó�� BUF_LEN �� ����
	private Socket socket; // �������
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private JLabel lblUserName;
	private JTextPane textArea;
	
	/*�츮�� ���� JavaObjectClientView Ŭ���� �� �������� �����ϴ� ����*/
	private JLabel Background;
	private JLabel card;
	private String imgSrc;
	private String backSrc;
	private ImageIcon backIcon;
	private ImageIcon imgIcon1;
	private JLabel myRight;
	private JLabel otherLeft;
	private JLabel otherRight;
	/*�츮�� ���� JavaObjectClientView Ŭ���� �� �������� �����ϴ� ����*/

	public JavaObjClientView(String username, String ip_addr, String port_no) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setBounds(100, 100, 900, 630);
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		otherRight = new JLabel("otherRight");
		otherRight.setBounds(300, 10, 75, 100);
		contentPane.add(otherRight);
		
		otherLeft = new JLabel("otherLeft");
		otherLeft.setBounds(150, 10, 75, 100);
		contentPane.add(otherLeft);
		
		myRight = new JLabel("myRight");
		myRight.setBounds(300, 460, 75, 100);
		contentPane.add(myRight);
		
		JLabel myLeft = new JLabel("myLeft");
		myLeft.setBounds(150, 460, 75, 100);
		contentPane.add(myLeft);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(524, 10, 352, 471);
		contentPane.add(scrollPane);
		textArea = new JTextPane();
		textArea.setEditable(true);
		textArea.setFont(new Font("����ü", Font.PLAIN, 14));
		scrollPane.setViewportView(textArea);

		txtInput = new JTextField();
		txtInput.setBounds(586, 491, 209, 40);
		contentPane.add(txtInput);
		txtInput.setColumns(10);

		btnSend = new JButton("Send");
		btnSend.setFont(new Font("����", Font.PLAIN, 14));
		btnSend.setBounds(807, 491, 69, 40);
		contentPane.add(btnSend);

		lblUserName = new JLabel("Name");
		lblUserName.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblUserName.setBackground(Color.WHITE);
		lblUserName.setFont(new Font("����", Font.BOLD, 14));
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setBounds(524, 545, 62, 40);
		contentPane.add(lblUserName);
		setVisible(true);

		AppendText("User " + username + " connecting " + ip_addr + " " + port_no);
		UserName = username;
		lblUserName.setText(username);
		
		JButton btnNewButton = new JButton("�� ��");
		btnNewButton.setFont(new Font("����", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChatMsg msg = new ChatMsg(UserName, "400", "Bye");
				SendObject(msg);
				System.exit(0);
			}
		});
		btnNewButton.setBounds(807, 545, 69, 40);
		contentPane.add(btnNewButton);
		
		card = new JLabel("New label");
		imgSrc = "src/cards/0.png";
		imgIcon1 = new ImageIcon(imgSrc);
		card.setIcon(imgIcon1);
		card.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChatMsg msg = new ChatMsg(UserName, "777", "�̹����� �����غ���!");
				SendObject(msg);
			}
		});
		card.setBounds(524, 503, 20, 15);
		contentPane.add(card);
		
		Background = new JLabel("");
		backSrc = "src/images/background.jpg";
		backIcon = new ImageIcon(backSrc);
		Background.setIcon(backIcon);
		Background.setBounds(0, 0, 900, 595);
		contentPane.add(Background);
		
		try {
			socket = new Socket(ip_addr, Integer.parseInt(port_no));

			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(socket.getInputStream());

			ChatMsg obcm = new ChatMsg(UserName, "100", "Hello");
			SendObject(obcm);
			
			ListenNetwork net = new ListenNetwork();
			net.start();
			TextSendAction action = new TextSendAction();
			btnSend.addActionListener(action);
			txtInput.addActionListener(action);
			txtInput.requestFocus();

		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
			AppendText("connect error");
		}
	}

	// Server Message�� �����ؼ� ȭ�鿡 ǥ��
	class ListenNetwork extends Thread {
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
					switch (cm.getCode()) {
					case "200": // chat message
						AppendText(msg);
						break;
					case "777":
						AppendText(msg);
						imgSrc = ChangeImgSrc(imgSrc);
						imgIcon1 = new ImageIcon(imgSrc);
						card.setIcon(imgIcon1);
						break;
					}
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

	// keyboard enter key ġ�� ������ ����
	class TextSendAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Send button�� �����ų� �޽��� �Է��ϰ� Enter key ġ��
			if (e.getSource() == btnSend || e.getSource() == txtInput) {
				String msg = null;
				msg = txtInput.getText();
				SendMessage(msg);
				txtInput.setText(""); // �޼����� ������ ���� �޼��� ����â�� ����.
				txtInput.requestFocus(); // �޼����� ������ Ŀ���� �ٽ� �ؽ�Ʈ �ʵ�� ��ġ��Ų��
				if (msg.contains("/exit")) // ���� ó��
					System.exit(0);
			}
		}
	}

	// ȭ�鿡 ���
	public void AppendText(String msg) {
		msg = msg.trim(); // �յ� blank�� \n�� �����Ѵ�.
		int len = textArea.getDocument().getLength();
		// ������ �̵�
		textArea.setCaretPosition(len);
		textArea.replaceSelection(msg + "\n");
	}

	// Windows ó�� message ������ ������ �κ��� NULL �� ����� ���� �Լ�
	public byte[] MakePacket(String msg) {
		byte[] packet = new byte[BUF_LEN];
		byte[] bb = null;
		int i;
		for (i = 0; i < BUF_LEN; i++)
			packet[i] = 0;
		try {
			bb = msg.getBytes("euc-kr");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.exit(0);
		}
		for (i = 0; i < bb.length; i++)
			packet[i] = bb[i];
		return packet;
	}

	// Server���� network���� ����
	public void SendMessage(String msg) {
		try {
			ChatMsg obcm = new ChatMsg(UserName, "200", msg);
			oos.writeObject(obcm);
		} catch (IOException e) {
			AppendText("oos.writeObject() error");
			try {
				ois.close();
				oos.close();
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
				System.exit(0);
			}
		}
	}

	// Server���� �޼����� ������ �޼ҵ�
	public void SendObject(Object ob) {
		try {
			oos.writeObject(ob);
		} catch (IOException e) {
			AppendText("SendObject Error");
		}
	}
	
	public String ChangeImgSrc(String str) {
		if(str.equals("src/cards/0.png"))
			return "src/cards/1.png";
		else
			return "src/cards/0.png";
	}
}