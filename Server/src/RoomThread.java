import java.awt.EventQueue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.Random;

public class RoomThread extends Thread {
	private static final long serialVersionUID = 1L;

	private ServerSocket serverSocket; // ��������
	private Socket socket; // accept() ���� ������ client ����
	private Vector UserVec = new Vector(); // ����� ����ڸ� ������ ����
	private static final int BUF_LEN = 128; // Windows ó�� BUF_LEN �� ����
	private String creator;
	private int[] dupCheck = new int[4];

	public int getUserCount() {
		return UserVec.size();
	}

	public int getServerPort() {
		return this.serverSocket.getLocalPort();
	}

	public String getCreator() {
		return this.creator;
	}

	private Random rand = new Random();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaObjServerView frame = new JavaObjServerView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RoomThread(ServerSocket serverSocket, Socket socket, String creator) {
		super();
		this.serverSocket = serverSocket;
		this.socket = socket;
		this.creator = creator;
		AcceptServer accept_server = new AcceptServer();
		accept_server.start();
	}

	// ���ο� ������ accept() �ϰ� user thread�� ���� �����Ѵ�.
	class AcceptServer extends Thread {
		public void run() {
			while (true) { // ����� ������ ����ؼ� �ޱ� ���� while��
				try {
					socket = serverSocket.accept(); // accept�� �Ͼ�� �������� ���� �����
					UserService new_user = new UserService(socket);
					UserVec.add(new_user); // ���ο� ������ �迭�� �߰�
					new_user.start(); // ���� ��ü�� ������ ����
				} catch (IOException e) {
				}
			}
		}
	}

	// User �� �����Ǵ� Thread
	// Read One ���� ��� -> Write All
	class UserService extends Thread {
		private ObjectInputStream ois;
		private ObjectOutputStream oos;
		private Socket client_socket;
		private Vector user_vc;
		public String UserName = "";

		public UserService(Socket client_socket) {
			// �Ű������� �Ѿ�� �ڷ� ����
			this.client_socket = client_socket;
			this.user_vc = UserVec;
			try {
				oos = new ObjectOutputStream(client_socket.getOutputStream());
				oos.flush();
				ois = new ObjectInputStream(client_socket.getInputStream());
			} catch (Exception e) {

			}
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
			}
			for (i = 0; i < bb.length; i++)
				packet[i] = bb[i];
			return packet;
		}

		public void Logout() {
			String msg = "[" + UserName + "]���� ���� �Ͽ����ϴ�.\n";
			UserVec.removeElement(this); // Logout�� ���� ��ü�� ���Ϳ��� �����
			WriteAllObject(msg); // ���� ������ �ٸ� User�鿡�� ����
			if (UserVec.size() == 0) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// ��� User�鿡�� Object�� ���. ä�� message�� image object�� ���� �� �ִ�
		public void WriteAllObject(Object ob) {
			for (int i = 0; i < user_vc.size(); i++) {
				UserService user = (UserService) user_vc.elementAt(i);
				user.WriteOneObject(ob);
			}
		}

		public void WriteOneObject(Object ob) {
			try {
				oos.writeObject(ob);
			} catch (IOException e) {
				try {
					ois.close();
					oos.close();
					client_socket.close();
					client_socket = null;
					ois = null;
					oos = null;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Logout();
			}
		}

		// UserService Thread�� ����ϴ� Client ���� 1:1 ����
		public void WriteOne(String msg) {
			try {
				ChatMsg obcm = new ChatMsg("SERVER", "200", msg);
				oos.writeObject(obcm);
			} catch (IOException e) {
				try {
					ois.close();
					oos.close();
					client_socket.close();
					client_socket = null;
					ois = null;
					oos = null;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Logout(); // �������� ���� ��ü�� ���Ϳ��� �����
			}
		}

		// ���� ������ User�鿡�� ���. ������ UserService Thread�� WriteONe() �� ȣ���Ѵ�.
		public void WriteOthers(String str) {
			for (int i = 0; i < user_vc.size(); i++) {
				UserService user = (UserService) user_vc.elementAt(i);
				if (user != this)
					user.WriteOne(str);
			}
		}

		public void run() {
			while (true) { // ����� ������ ����ؼ� �ޱ� ���� while��
				try {
					Object obcm = null;
					String msg = null;
					ChatMsg cm = null;
					if (socket == null)
						break;
					try {
						obcm = ois.readObject();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
						return;
					}
					if (obcm == null)
						break;
					if (obcm instanceof ChatMsg) {
						cm = (ChatMsg) obcm;
					} else
						continue;
					if (cm.getCode().matches("100")) {
						UserName = cm.getId();
						cm.setData(Integer.toString(user_vc.size()));
						msg = String.format("[%s] %s", cm.getId(), cm.getData());
						WriteOneObject(cm);
						if (UserVec.size() == 2) {
							cm.setCode("104");
							WriteAllObject(cm);
						}
					} else if (cm.getCode().matches("400")) { // logout message ó��
						Logout();
						break;
					} else if (cm.getCode().matches("1")) {
						for (int i = 0; i < 4; i++) {
							dupCheck[i] = rand.nextInt(20);
							for (int j = 0; j < i; j++) {
								if (dupCheck[i] == dupCheck[j])
									i--;
							}
						}
						cm.setData(String.format("%02d %02d %02d %02d", dupCheck[0], dupCheck[1], dupCheck[2],
								dupCheck[3]));
						msg = String.format("[%s] %s", cm.getId(), cm.getData());
						WriteAllObject(cm);
					} else if (cm.getCode().matches("2")) {
						cm.setData(cm.getData());
						msg = String.format("%s", cm.getData());
						WriteAllObject(cm);
					} else if (cm.getCode().matches("3")) {
						cm.setData(cm.getData());
						msg = String.format("%s", cm.getData());
						WriteAllObject(cm);
					} else if (cm.getCode().matches("4")) {
						cm.setData(cm.getData());
						msg = String.format("%s", cm.getData());
						WriteAllObject(cm);
					} else if (cm.getCode().matches("5")) {
						cm.setData(cm.getData());
						msg = String.format("%s", cm.getData());
						WriteAllObject(cm);
					} else if (cm.getCode().matches("6")) {
						cm.setData(cm.getData());
						msg = String.format("%s", cm.getData());
						WriteAllObject(cm);
					} else if (cm.getCode().matches("7")) {
						cm.setData(cm.getData());
						msg = String.format("%s", cm.getData());
						WriteAllObject(cm);
					} else if (cm.getCode().matches("0")) {
						cm.setData(cm.getData());
						msg = String.format("%s", cm.getData());
						WriteAllObject(cm);
					}
				} catch (IOException e) {
					try {
						ois.close();
						oos.close();
						client_socket.close();
						Logout(); // ������ �� ���� ��ü�� ���Ϳ��� �����
						break;
					} catch (Exception ee) {
						break;
					}
				}
			}
		}
	}
}