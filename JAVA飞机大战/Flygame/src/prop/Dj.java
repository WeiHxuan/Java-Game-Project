package prop;

import java.util.Random;

public class Dj extends UFO {
	int sp;//�л��ٶ�
	public int HP2;//�л�Ѫ��
	public Dj()
	{
		//�������
		Random rd=new Random();
		//�����������ѡȡͼƬ[1-15]
		int index=rd.nextInt(15)+1;
		//���˷ɻ���ʾ��ͼ
		img=picture.getImg("/img/dj"+index+".png");
		//ȷ���л��Ĵ�С
		w=img.getWidth();
		h=img.getHeight();
		//ȷ���л���λ��
		x=rd.nextInt(512-w);
		y=0;
		//�����ٶ�
		sp=17-index;
		//����Ѫ��
		HP2=rd.nextInt(15);
	}
	//�л��ƶ�����
	public void Move()
	{
		y+=sp;
	}
	//�жϵл��Ƿ��ӵ�����
	public boolean shootBy(bullet b) {
		boolean hit=x<b.x+b.w&&
					x>=b.x-w &&
					y<=b.y+b.h&&
					y>=b.y-h;
		return hit;
	}
	public boolean hitBy(Flyplane b) {
		boolean hit=x<b.x+b.w&&
				x>=b.x-w &&
				y<=b.y+b.h&&
				y>=b.y-h;
		return hit;
	}
}
