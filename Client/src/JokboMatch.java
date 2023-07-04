public class JokboMatch {
	private String AJokbo; // ���� A �� ����
	private String BJokbo; // ���� B �� ����

	public JokboMatch(String str) {
		String array[] = str.split(" ");
		this.AJokbo = array[0];
		this.BJokbo = array[1];
	}

	public String selectWinner() {
		String winner = "";
		if (AJokbo.equals("���ȱ���")) {
			return "A";
		} else if (BJokbo.equals("���ȱ���")) {
			return "B";
		}

		int Alevel = 0;
		int Blevel = 0;

		if (AJokbo.equals("���ȱ���") || AJokbo.equals("�ϻﱤ��")) {
			Alevel = 50;
		}

		else if (AJokbo.charAt(1) == '��') {
			switch (AJokbo.substring(0, 1)) {
			case "��":
				Alevel = 49;
				break;
			case "��":
				Alevel = 48;
				break;
			case "��":
				Alevel = 47;
				break;
			case "ĥ":
				Alevel = 46;
				break;
			case "��":
				Alevel = 45;
				break;
			case "��":
				Alevel = 44;
				break;
			case "��":
				Alevel = 43;
				break;
			case "��":
				Alevel = 42;
				break;
			case "��":
				Alevel = 41;
				break;
			case "��":
				Alevel = 40;
				break;
			}
		} else {
			switch (AJokbo) {
			case "�˸�": // 1�� + 2�� ����
				Alevel = 39;
				break;
			case "����": // 1�� + 4�� ����
				Alevel = 38;
				break;
			case "����": // 1�� + 9�� ����
				Alevel = 37;
				break;
			case "���": // 1�� + 10�� ����
				Alevel = 36;
				break;
			case "���": // 4�� + 10�� ����
				Alevel = 35;
				break;
			case "����": // 4�� + 6�� ����
				Alevel = 34;
				break;
			case "����": // �� ���� ���� 1�� �ڸ� 9
				Alevel = 33;
				break;
			case "������": // �� ���� ���� 1�� �ڸ� 8
				Alevel = 32;
				break;
			case "�ϰ���": // �� ���� ���� 1�� �ڸ� 7
				Alevel = 31;
				break;
			case "������": // �� ���� ���� 1�� �ڸ� 6
				Alevel = 30;
				break;
			case "�ټ���": // �� ���� ���� 1�� �ڸ� 5
				Alevel = 29;
				break;
			case "�ײ�": // �� ���� ���� 1�� �ڸ� 4
				Alevel = 28;
				break;
			case "����": // �� ���� ���� 1�� �ڸ� 3
				Alevel = 27;
				break;
			case "�β�": // �� ���� ���� 1�� �ڸ� 2
				Alevel = 26;
				break;
			case "�Ѳ�": // �� ���� ���� 1�� �ڸ� 1
				Alevel = 25;
				break;
			case "����": // �� ���� ���� 1�� �ڸ� 0
				Alevel = 24;
				break;
			}
		}

		if (BJokbo.equals("���ȱ���") || BJokbo.equals("�ϻﱤ��")) {
			Blevel = 50;
		} else if (BJokbo.charAt(1) == '��') {
			switch (BJokbo.substring(0, 1)) {
			case "��":
				Blevel = 49;
				break;
			case "��":
				Blevel = 48;
				break;
			case "��":
				Blevel = 47;
				break;
			case "ĥ":
				Blevel = 46;
				break;
			case "��":
				Blevel = 45;
				break;
			case "��":
				Blevel = 44;
				break;
			case "��":
				Blevel = 43;
				break;
			case "��":
				Blevel = 42;
				break;
			case "��":
				Blevel = 41;
				break;
			case "��":
				Blevel = 40;
				break;
			}
		} else {
			switch (BJokbo) {
			case "�˸�":
				Blevel = 39;
				break;
			case "����":
				Blevel = 38;
				break;
			case "����":
				Blevel = 37;
				break;
			case "���":
				Blevel = 36;
				break;
			case "���":
				Blevel = 35;
				break;
			case "����":
				Blevel = 34;
				break;
			case "����":
				Blevel = 33;
				break;
			case "������":
				Blevel = 32;
				break;
			case "�ϰ���":
				Blevel = 31;
				break;
			case "������":
				Blevel = 30;
				break;
			case "�ټ���":
				Blevel = 29;
				break;
			case "�ײ�":
				Blevel = 28;
				break;
			case "����":
				Blevel = 27;
				break;
			case "�β�":
				Blevel = 26;
				break;
			case "�Ѳ�":
				Blevel = 25;
				break;
			case "����":
				Blevel = 24;
				break;
			}
		}

		if (AJokbo.equals("������")) {
			switch (BJokbo) {
			case "����":
			case "�ȶ�":
			case "ĥ��":
			case "����":
			case "����":
			case "�綯":
			case "�ﶯ":
			case "�̶�":
			case "�涯":
				return "A";
			case "����":
				return "���º�";
			default:
				return "B";
			}
		}

		if (BJokbo.equals("������")) {
			switch (AJokbo) {
			case "����":
			case "�ȶ�":
			case "ĥ��":
			case "����":
			case "����":
			case "�綯":
			case "�ﶯ":
			case "�̶�":
			case "�涯":
				return "B";
			case "����":
				return "���º�";
			default:
				return "A";
			}
		}

		if (AJokbo.equals("����")) {
			if (Blevel <= 39)
				Alevel = Blevel;
			else
				Alevel = 39;
		} else if (AJokbo.equals("���ֱ�������")) {
			if (Blevel <= 48)
				Alevel = Blevel;
			else
				Alevel = 48;
		} else if (AJokbo.equals("������")) {
			if (Blevel == 50)
				Alevel = 51;
			else
				Alevel = 25;
		}

		if (BJokbo.equals("����")) {
			if (Alevel <= 39)
				Blevel = Alevel;
			else
				Blevel = 39;
		} else if (BJokbo.equals("���ֱ�������")) {
			if (Alevel <= 48)
				Blevel = Alevel;
			else
				Blevel = 48;
		} else if (BJokbo.equals("������")) {
			if (Alevel == 50)
				Blevel = 51;
			else
				Blevel = 25;
		}

		if (Alevel == Blevel)
			return "���º�";
		else
			return Alevel > Blevel ? "A" : "B";
	}
}