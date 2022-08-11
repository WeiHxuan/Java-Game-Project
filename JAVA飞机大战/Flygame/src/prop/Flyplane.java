package prop;
public class Flyplane extends UFO {
	public int HP;//主机血量
	public Flyplane()
	{
		img=picture.getImg("/img/zj.png");//调取主机的图片
		//确定横纵坐标确定飞机游戏开始时显示的位置
		x=200;
		y=500;
		//确定图片的大小来作为飞机的大小
		w=img.getWidth();
		h=img.getHeight();
		HP=3;//游戏开始时  血量设置为3
	}
	//主机移动到鼠标的办法   mx横  my纵
	public void moveToMouse(int mx,int my)
	{
		x=mx-w/2;
		y=my-h/2;
	}
	//主机的移动
	public void moveUp()//向上移动
	{
		y-=100;
	}
	public void moveDown()//向下移动
	{
		y+=100;
	}
	public void moveLeft()//向左移动
	{
		x-=100;
	}
	public void moveRight()//向右移动
	{
		x+=100;
	}
}
