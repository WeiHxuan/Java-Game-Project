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

//��Ϸ��������
import javax.swing.JPanel;

import prop.Dj;
import prop.Flyplane;
import prop.bullet;
import prop.picture;
public class Gamepanel extends JPanel {
	BufferedImage bg;//����ͼ	
	Flyplane fly=new Flyplane();//ʵ�����ɻ��Ķ���
	//Dj dj=new Dj();//ʵ�����л�����
	//���Ϸ���   �л��Ĵ�Ӫ
	List<Dj>djs =new ArrayList<Dj>();
	
	//������ҩ��
	List<bullet> bus= new ArrayList<bullet>();
	
	//�������
	int score;
	//������Ϸ����
	boolean gameover;//
	
	//��ʼ��Ϸ  ���߳̿��Ʒ������ƶ�(�̶�)
	public void action()
	{
		new Thread(){
			public void run()
			{
				//��ѭ�� ����Ϸһֱ����
				while(true)
				{
					if(!gameover)
					{
						djEnter();
						djMove();
						
						//�����ӵ�
						shoot();
						
						//�ƶ��ӵ�
						buMove();
						
						//�ж��ӵ��Ƿ���ел�
						shootDj();
						
						//�жϵл�ײ�ɻ�
						hit();
					}
					shootDj();
					//�߳̿��� ����
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//ˢ��
					repaint();
				}
			}
		}.start();
	}
	//�л�ײ��
	protected void hit() {
		for(int i=0;i<djs.size();i++)
		{
			Dj j=djs.get(i);
			//����л���������ײ
			if(j.hitBy(fly))
			{
				//1.�л���ʧ
				djs.remove(j);
				//��������Ѫ��
				fly.HP--;
				//3.��������
				score+=2;
				//4.������Ѫ��Ϊ0  ��Ϸ����
				if(fly.HP<=0)
				{
					gameover=true;
					
				}
			}
		}
		
	}
	//�л�������Ϸ������
	int index=0;//��¼ִ�з��� �Ĵ���
	private void djEnter() {
		//��¼ִ�еĴ���
		index++;
		if(index>=20)
		{
			Dj D=new Dj();//�ٴ�ʵ�����л�
			djs.add(D);//���л����뼯��
			//��index��������Ϊ0
			index=0;
		}
	}
	//�л��ƶ����� 
	protected void djMove() {
		for(int i=0;i<djs.size();i++)
		{
			//��ȡ�л�
			Dj D=djs.get(i);
			D.Move();
		}
	}
	//�ӵ���������
	int budex=0;//������ 
	private void shoot() {
		budex++;
		if(budex>=5)
		{
			//�����ӵ�
			bullet bu1=new bullet(fly.x+70,fly.y);
			//���ӵ����뼯�ϵ�ҩ��
			bus.add(bu1);
			bullet bu2=new bullet(fly.x+10,fly.y);
			//���ӵ����뼯�ϵ�ҩ��
			bus.add(bu2);
			budex=0;
		}
	}
	//�ӵ��ƶ�
	private void buMove() {
		//forѭ������
		for(int i=0;i<bus.size();i++)
		{
			bullet b=bus.get(i);
			b.move();
		}	
	}
	//����ӵ��Ƿ����
	private void shootDj() {
		for(int i=0;i<bus.size();i++)
		{
			bullet b=bus.get(i);
			bang(b);
		}
		
	}
	//�ж�һ���ӵ��Ƿ���ел�
	private void bang(bullet b) {
		for(int i=0;i<djs.size();i++)
		{
			//��ȡһ���л�
			Dj j=djs.get(i); 
			//�ж�����ӵ��Ƿ���ел�
			if(j.shootBy(b))
			{
				//1.���ел�  Ѫ������
				j.HP2--;
				if(j.HP2<=0)
				{
					//2.�л����� �ӵ���ʧ  ɾ���л�
					djs.remove(j);
					//3.���ӷ���
					score+=2;
				}
				//4.�ӵ���ʧ
				bus.remove(b);
			}
		}
		
	}
	public Gamepanel(Gameframe frame)
	{
		//���ñ�����ɫ
		setBackground(Color.pink);
		bg=picture.getImg("/img/bg1.jpg");//��ȡͼƬ�ĵ�ַ
		
		//������������
		//����ƶ� mouseMoved()
		MouseAdapter adapter=new MouseAdapter() 
		{
			//��굥�����¿�ʼ��Ϸ
			public void mouseClicked(MouseEvent e)
			{
				if(gameover)
				{
					//�����µķɻ�������
					fly =new Flyplane();
					//������Ϸ����
					gameover=false;
					//�������
					score=0;
					Random rd=new Random();
					int index=rd.nextInt(5)+1;
					bg=picture.getImg("/img/bg"+index+".jpg");
					//ˢ�½���
					repaint();
				}
			}
			//mouseEvent��¼����ƶ�
			@Override
			public void mouseMoved(MouseEvent e) {
				//�ƶ���� ���÷���
				//��������������ƶ�
				int mx=e.getX();//���ĺ�����
				int my=e.getY();//���ĺ�����
				if(!gameover)
				{
					//�������ƶ�������λ��
					fly.moveToMouse(mx,my);
				}
				//ˢ�½���
				repaint();
			}
		};
		//���뵽��������  ���̶��ĸ�ʽ��
		addMouseListener(adapter);
		addMouseMotionListener(adapter);
		
		//���̼���(�̶���ʽ)
		KeyAdapter kd=new KeyAdapter()
		{
			//���Ĵ����¼�
			@Override
			public void keyPressed(KeyEvent e) {
				//�������̵İ���
				int keyCode=e.getKeyCode();
				if(keyCode== KeyEvent.VK_UP)
				{//�����ƶ�	
					fly.moveUp();
				}else if(keyCode==KeyEvent.VK_DOWN)
				{//�����ƶ�	
					fly.moveDown();
				}else if(keyCode==KeyEvent.VK_LEFT)
				{//�����ƶ�		
					fly.moveLeft();
				}else if(keyCode==KeyEvent.VK_RIGHT)
				{//�����ƶ�
					fly.moveRight();
				}else if(keyCode==KeyEvent.VK_W)
				{
					fly.moveUp();//��
				}
				else if(keyCode==KeyEvent.VK_S)
				{
					fly.moveDown();//��
				}
				else if(keyCode==KeyEvent.VK_A)
				{
					fly.moveLeft();//��
				}
				else if(keyCode==KeyEvent.VK_D)
				{
					fly.moveRight();//��
				}
				//ˢ�½���
				repaint();
			}
		};
		//���뵽����������
		frame.addKeyListener(kd);
		
	}
	//Graphics g ����
	public void paint(Graphics g)
	{
		super.paint(g);
		//g.drawImage(ͼƬ��ͼƬ�ĺ����꣬ͼƬ�������꣬null);
		//������
		g.drawImage(bg,0,0,521,768,null);
		//���ɻ�
		//g.drawimage(ͼƬ,ͼƬ�ĺ����꣬ͼƬ�������꣬ͼƬ�Ŀ�ȣ�ͼƬ�ĸ߶ȣ�null)
		g.drawImage(fly.img,fly.x,fly.y,fly.w,fly.h,null);
		//���л�  ��������
		for(int i=0;i<djs.size();i++)
		{
			Dj dj=djs.get(i);
			g.drawImage(dj.img,dj.x,dj.y,dj.w,dj.h,null);
		}
		//���ӵ� �͵л�����һ��
		for(int i=0;i<bus.size();i++)
		{
			bullet bu=bus.get(i);
			g.drawImage(bu.img,bu.x,bu.y,bu.w,bu.h,null);
		}
		//������ɫ����λ������
		g.setColor(Color.red);
		g.setFont(new Font("����",Font.BOLD,20));
		g.drawString("������"+score+"��",10,30);
		//������Ѫ��
		for(int i=0;i<fly.HP;i++)
		{
			g.drawImage(fly.img,400+i*35,5,30,30,null);
		}
		//����Ϸ����  ��GAMEOVER
		if(gameover)
		{
		g.setColor(Color.RED);
		g.setFont(new Font("����",Font.BOLD,100));
		g.drawString("GAMEOVER", 20, 400);
		}
	}
}
