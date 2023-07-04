import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.util.Vector;

public class JavaObjClientLobby extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int BUF_LEN = 128; // Windows ó�� BUF_LEN �� ����
	private JPanel contentPane;
	private JTextField txtInput;
	private String UserName;
	private JButton btnSend;
	private Socket socket; // �������
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private JLabel lblUserName;
	private JTextPane textArea;

	private String uID;
	private String backSrc;
	private ImageIcon backIcon;

	private JScrollPane lobbyPane;
	DefaultListModel model;
	private JList lobbyList;
	private String username;
	private String ip_addr;
	private String port_no;
	private Vector room;
	int currentRoomSize;
	Object obcm = null;
	String msg = null;
	ChatMsg cm;

	/* ��ư �ҽ� */
	private String roomMakeSrc = "src/buttons/RoomMake";
	private String refreshSrc = "src/buttons/Refresh";
	private String exitSrc = "src/buttons/Power";
	private String sendSrc = "src/buttons/Send";

	private JLabel roomMakeBtn;
	private JLabel refreshBtn;
	private JLabel exitBtn;

	public JavaObjClientLobby(String username, String ip_addr, String port_no) {
		this.username = username;
		this.ip_addr = ip_addr;
		this.port_no = port_no;
		contentPane = new JPanel();
		setBounds(100, 100, 900, 630);
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(494, 100, 382, 430);
		contentPane.add(scrollPane);
		textArea = new JTextPane();
		textArea.setEditable(true);
		textArea.setFont(new Font("����ü", Font.PLAIN, 14));
		scrollPane.setViewportView(textArea);

		txtInput = new JTextField();
		txtInput.setBounds(584, 541, 219, 40);
		contentPane.add(txtInput);
		txtInput.setColumns(10);

		lblUserName = new JLabel("Name");
		lblUserName.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblUserName.setBackground(Color.WHITE);
		lblUserName.setFont(new Font("����", Font.BOLD, 14));
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setBounds(494, 541, 87, 40);
		contentPane.add(lblUserName);
		setVisible(true);

		AppendText(username + "�Բ��� �����ϼ̽��ϴ�.");
		UserName = username;
		lblUserName.setText(username);
		backSrc = "src/images/background.jpg";
		backIcon = new ImageIcon(backSrc);

		/* �κ� ��ũ���� ���� */
		lobbyPane = new JScrollPane();
		lobbyPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lobbyPane.setBounds(26, 100, 456, 481);
		contentPane.add(lobbyPane);

		lobbyList = new JList();
		lobbyList.setFont(new Font("���� ��� Semilight", Font.BOLD, 18));
		lobbyPane.setViewportView(lobbyList);
		lobbyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lobbyList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String port_number = lobbyList.getSelectedValue().toString().split(" ")[0];
				AppendText(String.format("%s�� �濡 �����մϴ�.", port_number));
				ChatMsg msg = new ChatMsg(UserName, "102", port_number);
				SendObject(msg);
			}
		});

		ImageIcon RoomMake1 = new ImageIcon(new ImageIcon(roomMakeSrc + "1.png").getImage().getScaledInstance(140, 69,
				java.awt.Image.SCALE_SMOOTH));
		ImageIcon RoomMake2 = new ImageIcon(new ImageIcon(roomMakeSrc + "2.png").getImage().getScaledInstance(140, 69,
				java.awt.Image.SCALE_SMOOTH));
		ImageIcon Refresh1 = new ImageIcon(
				new ImageIcon(refreshSrc + "1.png").getImage().getScaledInstance(140, 69, java.awt.Image.SCALE_SMOOTH));
		ImageIcon Refresh2 = new ImageIcon(
				new ImageIcon(refreshSrc + "2.png").getImage().getScaledInstance(140, 69, java.awt.Image.SCALE_SMOOTH));
		ImageIcon Exit1 = new ImageIcon(
				new ImageIcon(exitSrc + "1.png").getImage().getScaledInstance(140, 69, java.awt.Image.SCALE_SMOOTH));
		ImageIcon Exit2 = new ImageIcon(
				new ImageIcon(exitSrc + "2.png").getImage().getScaledInstance(140, 69, java.awt.Image.SCALE_SMOOTH));
		ImageIcon Send1 = new ImageIcon(
				new ImageIcon(sendSrc + "1.png").getImage().getScaledInstance(69, 40, java.awt.Image.SCALE_SMOOTH));
		ImageIcon Send2 = new ImageIcon(
				new ImageIcon(sendSrc + "2.png").getImage().getScaledInstance(69, 40, java.awt.Image.SCALE_SMOOTH));

		roomMakeBtn = new JLabel("New label");
		roomMakeBtn.setIcon(RoomMake1);
		roomMakeBtn.setBounds(342, 21, 140, 69);
		roomMakeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChatMsg msg = new ChatMsg(UserName, "999", "�� ����� ��ư Ŭ��");
				AppendText(msg.getData());
				SendObject(msg);
			}

			public void mouseEntered(MouseEvent e) {
				roomMakeBtn.setIcon(RoomMake2);
			}

			public void mouseExited(MouseEvent e) {
				roomMakeBtn.setIcon(RoomMake1);
			}
		});
		contentPane.add(roomMakeBtn);

		/* ���ΰ�ħ ��ư */
		refreshBtn = new JLabel("New label");
		refreshBtn.setIcon(Refresh1);
		refreshBtn.setBounds(494, 21, 140, 69);
		refreshBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChatMsg msg = new ChatMsg(UserName, "998", "���ΰ�ħ");
				AppendText(msg.getData());
				SendObject(msg);
			}

			public void mouseEntered(MouseEvent e) {
				refreshBtn.setIcon(Refresh2);
			}

			public void mouseExited(MouseEvent e) {
				refreshBtn.setIcon(Refresh1);
			}
		});
		contentPane.add(refreshBtn);

		/* ���� ��ư */
		exitBtn = new JLabel("New label");
		exitBtn.setBounds(736, 21, 140, 69);
		exitBtn.setIcon(Exit1);
		exitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChatMsg msg = new ChatMsg(UserName, "400", "Bye");
				SendObject(msg);
				System.exit(0);
			}

			public void mouseEntered(MouseEvent e) {
				exitBtn.setIcon(Exit2);
			}

			public void mouseExited(MouseEvent e) {
				exitBtn.setIcon(Exit1);
			}
		});
		contentPane.add(exitBtn);

		/* ���� ��ư */
		btnSend = new JButton(Send1);
		btnSend.setBounds(807, 541, 69, 40);
		btnSend.setRolloverIcon(Send2);
		btnSend.setFocusPainted(false);
		btnSend.setBorderPainted(false);
		contentPane.add(btnSend);

		JLabel Logo = new JLabel("New label");
		Logo.setBounds(26, 21, 69, 69);
		Logo.setIcon(new ImageIcon(new ImageIcon("src/images/start.png").getImage().getScaledInstance(69, 69,
				java.awt.Image.SCALE_SMOOTH)));
		contentPane.add(Logo);

		room = new Vector();

		try {
			socket = new Socket(ip_addr, Integer.parseInt(port_no));

			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(socket.getInputStream());

			ChatMsg obcm = new ChatMsg(UserName, "101", "Lobby");
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
						msg = String.format("[%s] %s %s", cm.getId(), cm.getCode(), cm.getData());
					} else
						continue;
					switch (cm.getCode()) {
					case "100":
						uID = cm.getData();
						break;
					case "101": // �κ� ���� ��������
						currentRoomSize = Integer.parseInt(cm.getData());
						if (currentRoomSize > 0) {
							cm.setCode("103");
							SendObject(cm);
						}
						break;
					case "102": // �� ���� ��������
						String[] code = cm.getData().split(" ");
						if (code[1].equals("true")) {
							setVisible(false);
							JavaObjClientGame game = new JavaObjClientGame(username, ip_addr, code[0]);
						} else if (code[1].equals("full"))
							AppendText("���� ���� : �ִ� �ο���");
						else if (code[1].equals("empty"))
							AppendText("���� ���� : ����� ��");
						else
							AppendText("���� ����");
						break;
					case "103":
						String[] data = cm.getData().split(" ");
						String roomData = String.format("%s   ||    %s���� ���Դϴ�.                            ||   %s/2",
								data[0], data[2], data[3]);
						room.addElement(roomData);
						if (data[1].matches("last")) {
							lobbyList.setListData(room);
							lobbyList.setCellRenderer(getRenderer());
						} else
							SendObject(cm);
						break;
					case "200":
						AppendText(msg);
						break;
					case "999":
						AppendText(msg);
						setVisible(false);
						JavaObjClientGame game = new JavaObjClientGame(username, ip_addr, cm.getData());
						break;
					case "998":
						int currentRoomSize = Integer.parseInt(cm.getData());
						room.clear();
						if (currentRoomSize > 0) {
							cm.setCode("101");
							SendObject(cm);
						}
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

	private ListCellRenderer<? super String> getRenderer() {
		return new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean CellHasFocus) {
				JLabel listCellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index,
						isSelected, CellHasFocus);
				listCellRendererComponent.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
				return listCellRendererComponent;
			}
		};
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
			ChatMsg msg = (ChatMsg) ob;
			oos.writeObject(ob);
		} catch (IOException e) {
			AppendText("SendObj ect Error");
		}
	}
}