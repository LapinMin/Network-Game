public class JokboMatch {
	private String user1;
	private String user2;
	private String user1Jokbo;
	private String user2Jokbo;
	
	public JokboMatch(String data1, String data2) {
		String user1 = data1.split(" ")[0];
		String user1Jokbo = data1.split(" ")[1];
		String user2 = data2.split(" ")[0];
		String user2Jokbo = data2.split(" ")[1];
	}
	
	public String selectWinner() {
		/* �� �� �ϳ��� ���ȱ����� ��*/
		String winner = "";
		if (user1Jokbo.equals("���ȱ���")) {
			return user1;
		}
		else if (user2Jokbo.equals("���ȱ���")) {
			return user2;
		}
		
		/* �� �� ���ȱ����� �ƴ� �� */
		
		int user1level = 0;
		int user2level = 0;
		
		/* user1level ���ϱ� */
		if (user1Jokbo.substring(2,4).equals("����")) {
			user1level = 10;
		}
		else if (user1Jokbo.substring(1,2).equals("��")) {
			
		}
		return user1level > user2level ? user1 : user2;
	}
}