package UI;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//游戏的面板设计
import javax.swing.JPanel;

import prop.Dj;
import prop.Flyplane;
import prop.bullet;
import prop.picture;
public class Gamepanel extends JPanel {
	BufferedImage bg;//背景图	
	Flyplane fly=new Flyplane();//实例化飞机的对象
	//Dj dj=new Dj();//实例化敌机对象
	//集合泛型   敌机的大本营
	List<Dj>djs =new ArrayList<Dj>();
	
	//创建弹药库
	List<bullet> bus= new ArrayList<bullet>();
	
	//定义分数
	int score;
	//设置游戏开关
	boolean gameover;//
	
	//开始游戏  用线程控制飞行物移动(固定)
	public void action()
	{
		new Thread(){
			public void run()
			{
				//死循环 让游戏一直运行
				while(true)
				{
					if(!gameover)
					{
						djEnter();
						djMove();
						
						//发射子弹
						shoot();
						
						//移动子弹
						buMove();
						
						//判断子弹是否击中敌机
						shootDj();
						
						//判断敌机撞飞机
						hit();
					}
					shootDj();
					//线程控制 休眠
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//刷新
					repaint();
				}
			}
		}.start();
	}
	//敌机撞我
	protected void hit() {
		for(int i=0;i<djs.size();i++)
		{
			Dj j=djs.get(i);
			//如果敌机和主机相撞
			if(j.hitBy(fly))
			{
				//1.敌机消失
				djs.remove(j);
				//主机减少血量
				fly.HP--;
				//3.分数增加
				score+=2;
				//4.当主机血量为0  游戏结束
				if(fly.HP<=0)
				{
					gameover=true;
					
				}
			}
		}
		
	}
	//敌机进入游戏的数量
	int index=0;//记录执行方法 的次数
	private void djEnter() {
		//记录执行的次数
		index++;
		if(index>=20)
		{
			Dj D=new Dj();//再次实例化敌机
			djs.add(D);//将敌机加入集合
			//将index重新设置为0
			index=0;
		}
	}
	//敌机移动方法 
	protected void djMove() {
		for(int i=0;i<djs.size();i++)
		{
			//获取敌机
			Dj D=djs.get(i);
			D.Move();
		}
	}
	//子弹发射数量
	int budex=0;//计数器 
	private void shoot() {
		budex++;
		if(budex>=5)
		{
			//创建子弹
			bullet bu1=new bullet(fly.x+70,fly.y);
			//将子弹加入集合弹药库
			bus.add(bu1);
			bullet bu2=new bullet(fly.x+10,fly.y);
			//将子弹加入集合弹药库
			bus.add(bu2);
			budex=0;
		}
	}
	//子弹移动
	private void buMove() {
		//for循环遍历
		for(int i=0;i<bus.size();i++)
		{
			bullet b=bus.get(i);
			b.move();
		}	
	}
	//检测子弹是否击中
	private void shootDj() {
		for(int i=0;i<bus.size();i++)
		{
			bullet b=bus.get(i);
			bang(b);
		}
		
	}
	//判断一颗子弹是否击中敌机
	private void bang(bullet b) {
		for(int i=0;i<djs.size();i++)
		{
			//获取一个敌机
			Dj j=djs.get(i); 
			//判断这个子弹是否击中敌机
			if(j.shootBy(b))
			{
				//1.击中敌机  血量减少
				j.HP2--;
				if(j.HP2<=0)
				{
					//2.敌机击中 子弹消失  删除敌机
					djs.remove(j);
					//3.增加分数
					score+=2;
				}
				//4.子弹消失
				bus.remove(b);
			}
		}
		
	}
	public Gamepanel(Gameframe frame)
	{
		//设置背景颜色
		setBackground(Color.pink);
		bg=picture.getImg("/img/bg1.jpg");//获取图片的地址
		
		//创建鼠标监听器
		//鼠标移动 mouseMoved()
		MouseAdapter adapter=new MouseAdapter() 
		{
			//鼠标单击重新开始游戏
			public void mouseClicked(MouseEvent e)
			{
				if(gameover)
				{
					//生成新的飞机和生命
					fly =new Flyplane();
					//重置游戏开关
					gameover=false;
					//清零分数
					score=0;
					Random rd=new Random();
					int index=rd.nextInt(5)+1;
					bg=picture.getImg("/img/bg"+index+".jpg");
					//刷新界面
					repaint();
				}
			}
			//mouseEvent记录鼠标移动
			@Override
			public void mouseMoved(MouseEvent e) {
				//移动鼠标 调用方法
				//让主机跟着鼠标移动
				int mx=e.getX();//鼠标的横坐标
				int my=e.getY();//鼠标的横坐标
				if(!gameover)
				{
					//让主机移动到鼠标的位置
					fly.moveToMouse(mx,my);
				}
				//刷新界面
				repaint();
			}
		};
		//加入到监听里面  （固定的格式）
		addMouseListener(adapter);
		addMouseMotionListener(adapter);
		
		//键盘监听(固定格式)
		KeyAdapter kd=new KeyAdapter()
		{
			//鼠标的触发事件
			@Override
			public void keyPressed(KeyEvent e) {
				//监听键盘的按键
				int keyCode=e.getKeyCode();
				if(keyCode== KeyEvent.VK_UP)
				{//向上移动	
					fly.moveUp();
				}else if(keyCode==KeyEvent.VK_DOWN)
				{//向下移动	
					fly.moveDown();
				}else if(keyCode==KeyEvent.VK_LEFT)
				{//向左移动		
					fly.moveLeft();
				}else if(keyCode==KeyEvent.VK_RIGHT)
				{//向右移动
					fly.moveRight();
				}else if(keyCode==KeyEvent.VK_W)
				{
					fly.moveUp();//上
				}
				else if(keyCode==KeyEvent.VK_S)
				{
					fly.moveDown();//下
				}
				else if(keyCode==KeyEvent.VK_A)
				{
					fly.moveLeft();//左
				}
				else if(keyCode==KeyEvent.VK_D)
				{
					fly.moveRight();//右
				}
				//刷新界面
				repaint();
			}
		};
		//加入到监听器里面
		frame.addKeyListener(kd);
		
	}
	//Graphics g 画笔
	public void paint(Graphics g)
	{
		super.paint(g);
		//g.drawImage(图片，图片的横坐标，图片的纵坐标，null);
		//画背景
		g.drawImage(bg,0,0,521,768,null);
		//画飞机
		//g.drawimage(图片,图片的横坐标，图片的纵坐标，图片的宽度，图片的高度，null)
		g.drawImage(fly.img,fly.x,fly.y,fly.w,fly.h,null);
		//画敌机  遍历集合
		for(int i=0;i<djs.size();i++)
		{
			Dj dj=djs.get(i);
			g.drawImage(dj.img,dj.x,dj.y,dj.w,dj.h,null);
		}
		//画子弹 和敌机方法一样
		for(int i=0;i<bus.size();i++)
		{
			bullet bu=bus.get(i);
			g.drawImage(bu.img,bu.x,bu.y,bu.w,bu.h,null);
		}
		//分数颜色字体位置设置
		g.setColor(Color.red);
		g.setFont(new Font("楷体",Font.BOLD,20));
		g.drawString("分数【"+score+"】",10,30);
		//画主机血量
		for(int i=0;i<fly.HP;i++)
		{
			g.drawImage(fly.img,400+i*35,5,30,30,null);
		}
		//当游戏结束  画GAMEOVER
		if(gameover)
		{
		g.setColor(Color.RED);
		g.setFont(new Font("楷体",Font.BOLD,100));
		g.drawString("GAMEOVER", 20, 400);
		}
	}
}
