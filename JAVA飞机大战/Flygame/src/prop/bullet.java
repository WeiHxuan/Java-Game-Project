package prop;
public class bullet extends UFO {
		//���췽��
		//hx��hy�����ĺ�������
		public bullet(int hx,int hy)
		{
			//��ȡ�ӵ���ͼƬ
			img=picture.getImg("/img/zd.png");
			//ȷ���ӵ��Ĵ�Сb               
			w=img.getWidth()/3;
			h=img.getHeight()/3;
			//�ӵ���λ��  ���������������Ϸ���
			x=hx;
			y=hy;
		}
		//�ӵ��ƶ�����
		public void move() {
			y-=10;
		}
}
