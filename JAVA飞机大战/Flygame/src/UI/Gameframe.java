package UI;

//�Զ��崰��
//������ �̳�JFrame
//�������췽�������ô���
import javax.swing.JFrame;

public class Gameframe extends JFrame
{
	//���췽��
	public Gameframe()
	{	
		//���ñ���
		setTitle("ȫ��ɻ���ս    By��Ŀ31802��");
		//���ô�С
		setSize(512,768);
		//����λ�þ�����ʾ  null��ʾ�������Ļ���ϽǾ���
		setLocationRelativeTo(null);
		//���ý�ֹ���ý����С
		setResizable(false);
		//�رմ�����˳�����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String []args)
	{
		//ʵ��������
		Gameframe frame=new Gameframe();
		//ʵ�������
		Gamepanel panel=new Gamepanel(frame);//����������м�������¼�
		//������Ϸ
		panel.action();
		//�������봰��
		frame.add(panel);
		//��ʾ����  trueΪ��ʾ  falseΪ����
		frame.setVisible(true);
	}
}