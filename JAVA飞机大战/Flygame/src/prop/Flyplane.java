package prop;
public class Flyplane extends UFO {
	public int HP;//����Ѫ��
	public Flyplane()
	{
		img=picture.getImg("/img/zj.png");//��ȡ������ͼƬ
		//ȷ����������ȷ���ɻ���Ϸ��ʼʱ��ʾ��λ��
		x=200;
		y=500;
		//ȷ��ͼƬ�Ĵ�С����Ϊ�ɻ��Ĵ�С
		w=img.getWidth();
		h=img.getHeight();
		HP=3;//��Ϸ��ʼʱ  Ѫ������Ϊ3
	}
	//�����ƶ������İ취   mx��  my��
	public void moveToMouse(int mx,int my)
	{
		x=mx-w/2;
		y=my-h/2;
	}
	//�������ƶ�
	public void moveUp()//�����ƶ�
	{
		y-=100;
	}
	public void moveDown()//�����ƶ�
	{
		y+=100;
	}
	public void moveLeft()//�����ƶ�
	{
		x-=100;
	}
	public void moveRight()//�����ƶ�
	{
		x+=100;
	}
}
