package prop;

import java.util.Random;

public class Dj extends UFO {
	int sp;//敌机速度
	public int HP2;//敌机血量
	public Dj()
	{
		//随机数类
		Random rd=new Random();
		//生成随机数，选取图片[1-15]
		int index=rd.nextInt(15)+1;
		//敌人飞机显示的图
		img=picture.getImg("/img/dj"+index+".png");
		//确定敌机的大小
		w=img.getWidth();
		h=img.getHeight();
		//确定敌机的位置
		x=rd.nextInt(512-w);
		y=0;
		//设置速度
		sp=17-index;
		//设置血量
		HP2=rd.nextInt(15);
	}
	//敌机移动方法
	public void Move()
	{
		y+=sp;
	}
	//判断敌机是否被子弹击中
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
