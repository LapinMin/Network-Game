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
   private static final int BUF_LEN = 128; // Windows 처럼 BUF_LEN 을 정의
   private Socket socket; // 연결소켓
   private ObjectInputStream ois;
   private ObjectOutputStream oos;
   private JLabel lblUserName;
   private JTextPane textArea;
   
   /*우리가 만든 JavaObjectClientView 클래스 내 지역변수 선언하는 공간*/
   private JLabel Background;
   private String backSrc;
   private ImageIcon backIcon;
   private Card myLeft;
   private Card myRight;
   private Card otherLeft;
   private Card otherRight;
   private JLabel shuffle;
   /*우리가 만든 JavaObjectClientView 클래스 내 지역변수 선언하는 공간*/

   public JavaObjClientView(String username, String ip_addr, String port_no) {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      contentPane = new JPanel();
      setBounds(100, 100, 900, 630);
      contentPane.setBackground(new Color(255, 255, 255));
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      myLeft = new Card(200, 250, 75, 125);
      myLeft.setCardBounds();
      contentPane.add(myLeft.getCard());
      
      myRight = new Card(200, 250, 75, 125);
      myRight.setCardBounds();
      contentPane.add(myRight.getCard());
      
      otherLeft = new Card(200, 250, 75, 125);
      otherLeft.setCardBounds();
      contentPane.add(otherLeft.getCard());
      
      otherRight = new Card(200, 250, 75, 125);
      otherRight.setCardBounds();
      contentPane.add(otherRight.getCard());
      
      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(524, 10, 352, 471);
      contentPane.add(scrollPane);
      textArea = new JTextPane();
      textArea.setEditable(true);
      textArea.setFont(new Font("굴림체", Font.PLAIN, 14));
      scrollPane.setViewportView(textArea);

      txtInput = new JTextField();
      txtInput.setBounds(586, 491, 209, 40);
      contentPane.add(txtInput);
      txtInput.setColumns(10);

      btnSend = new JButton("Send");
      btnSend.setFont(new Font("굴림", Font.PLAIN, 14));
      btnSend.setBounds(807, 491, 69, 40);
      contentPane.add(btnSend);

      lblUserName = new JLabel("Name");
      lblUserName.setBorder(new LineBorder(new Color(0, 0, 0)));
      lblUserName.setBackground(Color.WHITE);
      lblUserName.setFont(new Font("굴림", Font.BOLD, 14));
      lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
      lblUserName.setBounds(524, 545, 62, 40);
      contentPane.add(lblUserName);
      setVisible(true);

      AppendText("User " + username + " connecting " + ip_addr + " " + port_no);
      UserName = username;
      lblUserName.setText(username);
      
      JButton btnNewButton = new JButton("종 료");
      btnNewButton.setFont(new Font("굴림", Font.PLAIN, 14));
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ChatMsg msg = new ChatMsg(UserName, "400", "Bye");
            SendObject(msg);
            System.exit(0);
         }
      });
      btnNewButton.setBounds(807, 545, 69, 40);
      contentPane.add(btnNewButton);
      
      shuffle = new JLabel("shuffle");
      shuffle.setFont(new Font("굴림", Font.PLAIN, 28));
      shuffle.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
        	  ChatMsg msg = new ChatMsg(UserName, "1", "덱을 섞어보자!");
              SendObject(msg);
          }
       });
      shuffle.setBounds(200, 300, 100, 50);
      contentPane.add(shuffle);
      
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

   // Server Message를 수신해서 화면에 표시
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
               case "1":
            	   AppendText(msg);
            	   String[] array = cm.getData().split(" ");
                   shuffle.setVisible(false);
            	   myLeft.backside();
        		   myRight.backside();
        		   otherLeft.backside();
        		   otherRight.backside();
        		   Thread.sleep(1500);
            	   for(int i=0;i<4;i++) {
            		   switch(i) {
                	   case 0 :
                		   myLeft.setCardSrc(Integer.parseInt(array[i]));
                		   myLeft.setCardIcon(myLeft.getCardSrc());
                		   while(myLeft.getX() > 100 || myLeft.getY() < 450 ) {
                			   if (myLeft.getX() > 100) myLeft.setX(myLeft.getX() - 5);
                			   if (myLeft.getY() < 450) myLeft.setY(myLeft.getY() + 12);
                			   myLeft.setCardBounds();
                			   repaint();
                			   Thread.sleep(10);
                		   }
                	   case 1 :
                		   myRight.setCardSrc(Integer.parseInt(array[i]));
                		   myRight.setCardIcon(myRight.getCardSrc());
                		   while(myRight.getX() < 300 || myRight.getY() < 450 ) {
                			   if (myRight.getX() < 300) myRight.setX(myRight.getX() + 5);
                			   if (myRight.getY() < 450) myRight.setY(myRight.getY() + 12);
                			   myRight.setCardBounds();
                			   repaint();
                			   Thread.sleep(10);
                		   }
                	   case 2 :
                		   otherLeft.setCardSrc(Integer.parseInt(array[i]));
                		   otherLeft.setCardIcon(otherLeft.getCardSrc());
                		   while(otherLeft.getX() > 100 || otherLeft.getY() > 10 ) {
                			   if (otherLeft.getX() > 100) otherLeft.setX(otherLeft.getX() - 5);
                			   if (otherLeft.getY() > 10) otherLeft.setY(otherLeft.getY() - 12);
                			   otherLeft.setCardBounds();
                			   repaint();
                			   Thread.sleep(10);
                		   }
                	   case 3 :
                		   otherRight.setCardSrc(Integer.parseInt(array[i]));
                		   otherRight.setCardIcon(otherRight.getCardSrc());
                		   while(otherRight.getX() < 300 || otherRight.getY() > 10 ) {
                			   if (otherRight.getX() < 300) otherRight.setX(otherRight.getX() + 5);
                			   if (otherRight.getY() > 10) otherRight.setY(otherRight.getY() - 12);
                			   otherRight.setCardBounds();
                			   repaint();
                			   Thread.sleep(10);
                		   }
                	   } 
            	   }
            	   myLeft.flip();
            	   myRight.flip();
            	   otherLeft.flip();
            	   otherRight.flip();
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
               } // catch문 끝
            } // 바깥 catch문끝
 catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         }
      }
   }

   // keyboard enter key 치면 서버로 전송
   class TextSendAction implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
         // Send button을 누르거나 메시지 입력하고 Enter key 치면
         if (e.getSource() == btnSend || e.getSource() == txtInput) {
            String msg = null;
            msg = txtInput.getText();
            SendMessage(msg);
            txtInput.setText(""); // 메세지를 보내고 나면 메세지 쓰는창을 비운다.
            txtInput.requestFocus(); // 메세지를 보내고 커서를 다시 텍스트 필드로 위치시킨다
            if (msg.contains("/exit")) // 종료 처리
               System.exit(0);
         }
      }
   }

   // 화면에 출력
   public void AppendText(String msg) {
      msg = msg.trim(); // 앞뒤 blank와 \n을 제거한다.
      int len = textArea.getDocument().getLength();
      // 끝으로 이동
      textArea.setCaretPosition(len);
      textArea.replaceSelection(msg + "\n");
   }

   // Windows 처럼 message 제외한 나머지 부분은 NULL 로 만들기 위한 함수
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

   // Server에게 network으로 전송
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

   // Server에게 메세지를 보내는 메소드
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