package prop;
public class bullet extends UFO {
		//构造方法
		//hx，hy主机的横纵坐标
		public bullet(int hx,int hy)
		{
			//获取子弹的图片
			img=picture.getImg("/img/zd.png");
			//确定子弹的大小b               
			w=img.getWidth()/3;
			h=img.getHeight()/3;
			//子弹的位置  （必须在主机的上方）
			x=hx;
			y=hy;
		}
		//子弹移动方法
		public void move() {
			y-=10;
		}
}
