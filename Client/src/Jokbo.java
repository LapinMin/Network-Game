public class Jokbo {
	private String card1;
	private String card2;
	private int level;
	private int sum;
	
	public Jokbo(Card card1, Card card2) {
		this.card1 = card1.getCardSrc().substring(10,12);
		this.card2 = card2.getCardSrc().substring(10,12);
	}
	
	//00 : 1 02 : 2 04 : 3 06 : 4 08 : 5 10 : 6 12 : 7 14 : 8 16 : 9 18 : 10
	//00 : 1�� 04 : 3�� 14 : 8��
	//00(1), 02(2), 06(4), 12(7), 15(8), 16(9), 18(10) -> ���� ��
	public String calculateJokbo() {
		String x = "";
		switch(card1) {
		case "00" : // 1��
			switch(card2) {
			case "01": x = "�涯"; // 1��
			break;
			case "02": x = "�˸�"; // 2��
			break;
			case "03": x = "�˸�"; // 2��
			break;
			case "04": x = "�ϻﱤ��"; // 3��
			break;
			case "05": x = "�ײ�"; // 3��
			break;
			case "06": x = "����"; // 4��
			break;
			case "07": x = "����"; // 4��
			break;
			case "08": x = "������"; // 5��
			break;
			case "09": x = "������"; // 5��
			break;
			case "10": x = "�ϰ���"; // 6��
			break;
			case "11": x = "�ϰ���"; // 6��
			break;
			case "12": x = "������"; // 7��
			break;
			case "13": x = "������"; // 7��
			break;
			case "14": x = "���ȱ���"; // 8��
			break;
			case "15": x = "����"; // 8��
			break;
			case "16": x = "����"; // 9��
			break;
			case "17": x = "����"; // 9��
			break;
			case "18": x = "���"; // 10��
			break;
			case "19": x = "���"; // 10��
			break;
			}
		break;
		case "01" : // 1��
			switch(card2) {
			case "00": x = "�涯"; // 1��
			break;
			case "02": x = "�˸�"; // 2��
			break;
			case "03": x = "�˸�"; // 2��
			break;
			case "04": x = "�ײ�"; // 3��
			break;
			case "05": x = "�ײ�"; // 3��
			break;
			case "06": x = "����"; // 4��
			break;
			case "07": x = "����"; // 4��
			break;
			case "08": x = "������"; // 5��
			break;
			case "09": x = "������"; // 5��
			break;
			case "10": x = "�ϰ���"; // 6��
			break;
			case "11": x = "�ϰ���"; // 6��
			break;
			case "12": x = "������"; // 7��
			break;
			case "13": x = "������"; // 7��
			break;
			case "14": x = "����"; // 8��
			break;
			case "15": x = "����"; // 8��
			break;
			case "16": x = "����"; // 9��
			break;
			case "17": x = "����"; // 9��
			break;
			case "18": x = "���"; // 10��
			break;
			case "19": x = "���"; // 10��
			break;
			}
		break;
		case "02" : // 2��
			switch(card2) {
			case "00": x = "�˸�"; // 1��
			break;
			case "01": x = "�˸�"; // 1��
			break;
			case "03": x = "�̶�"; // 2��
			break;
			case "04": x = "�ټ���"; // 3��
			break;
			case "05": x = "�ټ���"; // 3��
			break;
			case "06": x = "������"; // 4��
			break;
			case "07": x = "������"; // 4��
			break;
			case "08": x = "�ϰ���"; // 5��
			break;
			case "09": x = "�ϰ���"; // 5��
			break;
			case "10": x = "������"; // 6��
			break;
			case "11": x = "������"; // 6��
			break;
			case "12": x = "����"; // 7��
			break;
			case "13": x = "����"; // 7��
			break;
			case "14": x = "����"; // 8��
			break;
			case "15": x = "����"; // 8��
			break;
			case "16": x = "�Ѳ�"; // 9��
			break;
			case "17": x = "�Ѳ�"; // 9��
			break;
			case "18": x = "�β�"; // 10��
			break;
			case "19": x = "�β�"; // 10��
			break;
			}
		break;
		case "03" : // 2��
			switch(card2) {
			case "00": x = "�˸�"; // 1��
			break;
			case "01": x = "�˸�"; // 1��
			break;
			case "02": x = "�̶�"; // 2��
			break;
			case "04": x = "�ټ���"; // 3��
			break;
			case "05": x = "�ټ���"; // 3��
			break;
			case "06": x = "������"; // 4��
			break;
			case "07": x = "������"; // 4��
			break;
			case "08": x = "�ϰ���"; // 5��
			break;
			case "09": x = "�ϰ���"; // 5��
			break;
			case "10": x = "������"; // 6��
			break;
			case "11": x = "������"; // 6��
			break;
			case "12": x = "����"; // 7��
			break;
			case "13": x = "����"; // 7��
			break;
			case "14": x = "����"; // 8��
			break;
			case "15": x = "����"; // 8��
			break;
			case "16": x = "�Ѳ�"; // 9��
			break;
			case "17": x = "�Ѳ�"; // 9��
			break;
			case "18": x = "�β�"; // 10��
			break;
			case "19": x = "�β�"; // 10��
			break;
			}
		break;
		case "04" : // 3��
			switch(card2) {
			case "00": x = "�ϻﱤ��"; // 1��
			break;
			case "01": x = "�ײ�"; // 1��
			break;
			case "02": x = "�ټ���"; // 2��
			break;
			case "03": x = "�ټ���"; // 2��
			break;
			case "05": x = "�ﶯ"; // 3��
			break;
			case "06": x = "�ϰ���"; // 4��
			break;
			case "07": x = "�ϰ���"; // 4��
			break;
			case "08": x = "������"; // 5��
			break;
			case "09": x = "������"; // 5��
			break;
			case "10": x = "����"; // 6��
			break;
			case "11": x = "����"; // 6��
			break;
			case "12": x = "������"; // 7��
			break;
			case "13": x = "����"; // 7��
			break;
			case "14": x = "�Ѳ�"; // 8��
			break;
			case "15": x = "�Ѳ�"; // 8��
			break;
			case "16": x = "�β�"; // 9��
			break;
			case "17": x = "�β�"; // 9��
			break;
			case "18": x = "����"; // 10��
			break;
			case "19": x = "����"; // 10��
			break;
			}
		break;
		case "05" : // 3��
			switch(card2) {
			case "00": x = "�ײ�"; // 1��
			break;
			case "01": x = "�ײ�"; // 1��
			break;
			case "02": x = "�ټ���"; // 2��
			break;
			case "03": x = "�ټ���"; // 2��
			break;
			case "04": x = "�ﶯ"; // 3��
			break;
			case "06": x = "�ϰ���"; // 4��
			break;
			case "07": x = "�ϰ���"; // 4��
			break;
			case "08": x = "������"; // 5��
			break;
			case "09": x = "������"; // 5��
			break;
			case "10": x = "����"; // 6��
			break;
			case "11": x = "����"; // 6��
			break;
			case "12": x = "����"; // 7��
			break;
			case "13": x = "����"; // 7��
			break;
			case "14": x = "�Ѳ�"; // 8��
			break;
			case "15": x = "�Ѳ�"; // 8��
			break;
			case "16": x = "�β�"; // 9��
			break;
			case "17": x = "�β�"; // 9��
			break;
			case "18": x = "����"; // 10��
			break;
			case "19": x = "����"; // 10��
			break;
			}
		break;
		case "06" : // 4��
			switch(card2) {
			case "00": x = "����"; // 1��
			break;
			case "01": x = "����"; // 1��
			break;
			case "02": x = "������"; // 2��
			break;
			case "03": x = "������"; // 2��
			break;
			case "04": x = "�ϰ���"; // 3��
			break;
			case "05": x = "�ϰ���"; // 3��
			break;
			case "07": x = "�綯"; // 4��
			break;
			case "08": x = "����"; // 5��
			break;
			case "09": x = "����"; // 5��
			break;
			case "10": x = "����"; // 6��
			break;
			case "11": x = "����"; // 6��
			break;
			case "12": x = "������"; // 7��
			break;
			case "13": x = "�Ѳ�"; // 7��
			break;
			case "14": x = "�β�"; // 8��
			break;
			case "15": x = "�β�"; // 8��
			break;
			case "16": x = "���ֱ�������"; // 9��
			break;
			case "17": x = "����"; // 9��
			break;
			case "18": x = "���"; // 10��
			break;
			case "19": x = "���"; // 10��
			break;
			}
		break;
		case "07" : // 4��
			switch(card2) {
			case "00": x = "����"; // 1��
			break;
			case "01": x = "����"; // 1��
			break;
			case "02": x = "������"; // 2��
			break;
			case "03": x = "������"; // 2��
			break;
			case "04": x = "�ϰ���"; // 3��
			break;
			case "05": x = "�ϰ���"; // 3��
			break;
			case "06": x = "�綯"; // 4��
			break;
			case "08": x = "����"; // 5��
			break;
			case "09": x = "����"; // 5��
			break;
			case "10": x = "����"; // 6��
			break;
			case "11": x = "����"; // 6��
			break;
			case "12": x = "�Ѳ�"; // 7��
			break;
			case "13": x = "�Ѳ�"; // 7��
			break;
			case "14": x = "�β�"; // 8��
			break;
			case "15": x = "�β�"; // 8��
			break;
			case "16": x = "����"; // 9��
			break;
			case "17": x = "����"; // 9��
			break;
			case "18": x = "���"; // 10��
			break;
			case "19": x = "���"; // 10��
			break;
			}
		break;
		case "08" : // 5��
			switch(card2) {
			case "00": x = "������"; // 1��
			break;
			case "01": x = "������"; // 1��
			break;
			case "02": x = "�ϰ���"; // 2��
			break;
			case "03": x = "�ϰ���"; // 2��
			break;
			case "04": x = "������"; // 3��
			break;
			case "05": x = "������"; // 3��
			break;
			case "06": x = "����"; // 4��
			break;
			case "07": x = "����"; // 4��
			break;
			case "09": x = "����"; // 5��
			break;
			case "10": x = "�Ѳ�"; // 6��
			break;
			case "11": x = "�Ѳ�"; // 6��
			break;
			case "12": x = "�β�"; // 7��
			break;
			case "13": x = "�β�"; // 7��
			break;
			case "14": x = "����"; // 8��
			break;
			case "15": x = "����"; // 8��
			break;
			case "16": x = "�ײ�"; // 9��
			break;
			case "17": x = "�ײ�"; // 9��
			break;
			case "18": x = "�ټ���"; // 10��
			break;
			case "19": x = "�ټ���"; // 10��
			break;
			}
		break;
		case "09" : // 5��
			switch(card2) {
			case "00": x = "������"; // 1��
			break;
			case "01": x = "������"; // 1��
			break;
			case "02": x = "�ϰ���"; // 2��
			break;
			case "03": x = "�ϰ���"; // 2��
			break;
			case "04": x = "������"; // 3��
			break;
			case "05": x = "������"; // 3��
			break;
			case "06": x = "����"; // 4��
			break;
			case "07": x = "����"; // 4��
			break;
			case "08": x = "����"; // 5��
			break;
			case "10": x = "�Ѳ�"; // 6��
			break;
			case "11": x = "�Ѳ�"; // 6��
			break;
			case "12": x = "�β�"; // 7��
			break;
			case "13": x = "�β�"; // 7��
			break;
			case "14": x = "����"; // 8��
			break;
			case "15": x = "����"; // 8��
			break;
			case "16": x = "�ײ�"; // 9��
			break;
			case "17": x = "�ײ�"; // 9��
			break;
			case "18": x = "�ټ���"; // 10��
			break;
			case "19": x = "�ټ���"; // 10��
			break;
			}
		break;
		case "10" : // 6��
			switch(card2) {
			case "00": x = "�ϰ���"; // 1��
			break;
			case "01": x = "�ϰ���"; // 1��
			break;
			case "02": x = "������"; // 2��
			break;
			case "03": x = "������"; // 2��
			break;
			case "04": x = "����"; // 3��
			break;
			case "05": x = "����"; // 3��
			break;
			case "06": x = "����"; // 4��
			break;
			case "07": x = "����"; // 4��
			break;
			case "08": x = "�Ѳ�"; // 5��	
			break;
			case "09": x = "�Ѳ�"; // 5��
			break;
			case "11": x = "����"; // 6��
			break;
			case "12": x = "����"; // 7��
			break;
			case "13": x = "����"; // 7��
			break;
			case "14": x = "�ײ�"; // 8��
			break;
			case "15": x = "�ײ�"; // 8��
			break;
			case "16": x = "�ټ���"; // 9��
			break;
			case "17": x = "�ټ���"; // 9��
			break;
			case "18": x = "������"; // 10��
			break;
			case "19": x = "������"; // 10��
			break;
			}
		break;
		case "11" : // 6��
			switch(card2) {
			case "00": x = "�ϰ���"; // 1��
			break;
			case "01": x = "�ϰ���"; // 1��
			break;
			case "02": x = "������"; // 2��
			break;
			case "03": x = "������"; // 2��
			break;
			case "04": x = "����"; // 3��
			break;
			case "05": x = "����"; // 3��
			break;
			case "06": x = "����"; // 4��
			break;
			case "07": x = "����"; // 4��
			break;
			case "08": x = "�Ѳ�"; // 5��	
			break;
			case "09": x = "�Ѳ�"; // 5��
			break;
			case "10": x = "����"; // 6��
			break;
			case "12": x = "����"; // 7��
			break;
			case "13": x = "����"; // 7��
			break;
			case "14": x = "�ײ�"; // 8��
			break;
			case "15": x = "�ײ�"; // 8��
			break;
			case "16": x = "�ټ���"; // 9��
			break;
			case "17": x = "�ټ���"; // 9��
			break;
			case "18": x = "������"; // 10��
			break;
			case "19": x = "������"; // 10��
			break;
			}
		break;
		case "12" : // 7��
			switch(card2) {
			case "00": x = "������"; // 1��
			break;
			case "01": x = "������"; // 1��
			break;
			case "02": x = "����"; // 2��
			break;
			case "03": x = "����"; // 2��
			break;
			case "04": x = "������"; // 3��
			break;
			case "05": x = "����"; // 3��
			break;
			case "06": x = "������"; // 4��
			break;
			case "07": x = "�Ѳ�"; // 4��
			break;
			case "08": x = "�β�"; // 5��	
			break;
			case "09": x = "�β�"; // 5��
			break;
			case "10": x = "����"; // 6��
			break;
			case "11": x = "����"; // 6��
			break;
			case "13": x = "ĥ��"; // 7��
			break;
			case "14": x = "�ټ���"; // 8��
			break;
			case "15": x = "�ټ���"; // 8��
			break;
			case "16": x = "������"; // 9��
			break;
			case "17": x = "������"; // 9��
			break;
			case "18": x = "�ϰ���"; // 10��
			break;
			case "19": x = "�ϰ���"; // 10��
			break;
			}
		break;
		case "13" : // 7��
			switch(card2) {
			case "00": x = "������"; // 1��
			break;
			case "01": x = "������"; // 1��
			break;
			case "02": x = "����"; // 2��
			break;
			case "03": x = "����"; // 2��
			break;
			case "04": x = "����"; // 3��
			break;
			case "05": x = "����"; // 3��
			break;
			case "06": x = "�Ѳ�"; // 4��
			break;
			case "07": x = "�Ѳ�"; // 4��
			break;
			case "08": x = "�β�"; // 5��	
			break;
			case "09": x = "�β�"; // 5��
			break;
			case "10": x = "����"; // 6��
			break;
			case "11": x = "����"; // 6��
			break;
			case "12": x = "ĥ��"; // 7��
			break;
			case "14": x = "�ټ���"; // 8��
			break;
			case "15": x = "�ټ���"; // 8��
			break;
			case "16": x = "������"; // 9��
			break;
			case "17": x = "������"; // 9��
			break;
			case "18": x = "�ϰ���"; // 10��
			break;
			case "19": x = "�ϰ���"; // 10��
			break;
			}
		break;
		case "14" : // 8��
			switch(card2) {
			case "00": x = "���ȱ���"; // 1��
			break;
			case "01": x = "����"; // 1��
			break;
			case "02": x = "����"; // 2��
			break;
			case "03": x = "����"; // 2��
			break;
			case "04": x = "���ȱ���"; // 3��
			break;
			case "05": x = "�Ѳ�"; // 3��
			break;
			case "06": x = "�β�"; // 4��
			break;
			case "07": x = "�β�"; // 4��
			break;
			case "08": x = "����"; // 5��	
			break;
			case "09": x = "����"; // 5��
			break;
			case "10": x = "�ײ�"; // 6��
			break;
			case "11": x = "�ײ�"; // 6��
			break;
			case "12": x = "�ټ���"; // 7��
			break;
			case "13": x = "�ټ���"; // 7��
			break;
			case "15": x = "�ȶ�"; // 8��
			break;
			case "16": x = "�ϰ���"; // 9��
			break;
			case "17": x = "�ϰ���"; // 9��
			break;
			case "18": x = "������"; // 10��
			break;
			case "19": x = "������"; // 10��
			break;
			}
		break;
		case "15" : // 8��
			switch(card2) {
			case "00": x = "����"; // 1��
			break;
			case "01": x = "����"; // 1��
			break;
			case "02": x = "����"; // 2��
			break;
			case "03": x = "����"; // 2��
			break;
			case "04": x = "�Ѳ�"; // 3��
			break;
			case "05": x = "�Ѳ�"; // 3��
			break;
			case "06": x = "�β�"; // 4��
			break;
			case "07": x = "�β�"; // 4��
			break;
			case "08": x = "����"; // 5��	
			break;
			case "09": x = "����"; // 5��
			break;
			case "10": x = "�ײ�"; // 6��
			break;
			case "11": x = "�ײ�"; // 6��
			break;
			case "12": x = "�ټ���"; // 7��
			break;
			case "13": x = "�ټ���"; // 7��
			break;
			case "14": x = "�ȶ�"; // 8��
			break;
			case "16": x = "�ϰ���"; // 9��
			break;
			case "17": x = "�ϰ���"; // 9��
			break;
			case "18": x = "������"; // 10��
			break;
			case "19": x = "������"; // 10��
			break;
			}
		break;
		case "16" : // 9��
			switch(card2) {
			case "00": x = "����"; // 1��
			break;
			case "01": x = "����"; // 1��
			break;
			case "02": x = "�Ѳ�"; // 2��
			break;	
			case "03": x = "�Ѳ�"; // 2��
			break;
			case "04": x = "�β�"; // 3��
			break;
			case "05": x = "�β�"; // 3��
			break;
			case "06": x = "���ֱ�������"; // 4��
			break;
			case "07": x = "����"; // 4��
			break;
			case "08": x = "�ײ�"; // 5��	
			break;
			case "09": x = "�ײ�"; // 5��
			break;
			case "10": x = "�ټ���"; // 6��
			break;
			case "11": x = "�ټ���"; // 6��
			break;
			case "12": x = "������"; // 7��
			break;
			case "13": x = "������"; // 7��
			break;
			case "14": x = "�ϰ���"; // 8��
			break;
			case "15": x = "�ϰ���"; // 8��
			break;
			case "17": x = "����"; // 9��
			break;
			case "18": x = "��ȩ��"; // 10��
			break;
			case "19": x = "��ȩ��"; // 10��
			break;
			}
		break;
		case "17" : // 9��
			switch(card2) {
			case "00": x = "����"; // 1��
			break;
			case "01": x = "����"; // 1��
			break;
			case "02": x = "�Ѳ�"; // 2��
			break;	
			case "03": x = "�Ѳ�"; // 2��
			break;
			case "04": x = "�β�"; // 3��
			break;
			case "05": x = "�β�"; // 3��
			break;
			case "06": x = "����"; // 4��
			break;
			case "07": x = "����"; // 4��
			break;
			case "08": x = "�ײ�"; // 5��	
			break;
			case "09": x = "�ײ�"; // 5��
			break;
			case "10": x = "�ټ���"; // 6��
			break;
			case "11": x = "�ټ���"; // 6��
			break;
			case "12": x = "������"; // 7��
			break;
			case "13": x = "������"; // 7��
			break;
			case "14": x = "�ϰ���"; // 8��
			break;
			case "15": x = "�ϰ���"; // 8��
			break;
			case "16": x = "����"; // 9��
			break;
			case "18": x = "��ȩ��"; // 10��
			break;
			case "19": x = "��ȩ��"; // 10��
			break;
			}
		break;
		case "18" : // 10��
			switch(card2) {
			case "00": x = "���"; // 1��
			break;
			case "01": x = "���"; // 1��
			break;
			case "02": x = "�Ѳ�"; // 2��
			break;	
			case "03": x = "�Ѳ�"; // 2��
			break;
			case "04": x = "�β�"; // 3��
			break;
			case "05": x = "�β�"; // 3��
			break;
			case "06": x = "����"; // 4��
			break;
			case "07": x = "����"; // 4��
			break;
			case "08": x = "�ײ�"; // 5��		
			break;
			case "09": x = "�ײ�"; // 5��
			break;
			case "10": x = "�ټ���"; // 6��
			break;
			case "11": x = "�ټ���"; // 6��
			break;
			case "12": x = "������"; // 7��
			break;
			case "13": x = "������"; // 7��
			break;
			case "14": x = "�ϰ���"; // 8��
			break;
			case "15": x = "�ϰ���"; // 8��
			break;
			case "16": x = "��ȩ��"; // 9��
			break;
			case "17": x = "��ȩ��"; // 9��
			break;
			case "19": x = "�嶯"; // 10��
			break;
			}
		break;
		case "19" : // 10��
			switch(card2) {
			case "00": x = "���"; // 1��
			break;
			case "01": x = "���"; // 1��
			break;
			case "02": x = "�Ѳ�"; // 2��
			break;	
			case "03": x = "�Ѳ�"; // 2��
			break;
			case "04": x = "�β�"; // 3��
			break;
			case "05": x = "�β�"; // 3��
			break;
			case "06": x = "����"; // 4��
			break;
			case "07": x = "����"; // 4��
			break;
			case "08": x = "�ײ�"; // 5��	
			break;
			case "09": x = "�ײ�"; // 5��
			break;
			case "10": x = "�ټ���"; // 6��
			break;
			case "11": x = "�ټ���"; // 6��
			break;
			case "12": x = "������"; // 7��
			break;
			case "13": x = "������"; // 7��
			break;
			case "14": x = "�ϰ���"; // 8��
			break;
			case "15": x = "�ϰ���"; // 8��
			break;
			case "16": x = "��ȩ��"; // 9��
			break;
			case "17": x = "��ȩ��"; // 9��
			break;
			case "18": x = "�嶯"; // 10��
			break;
			}
		break;
		}
		return x;
	} // calculateJokbo ��
}	