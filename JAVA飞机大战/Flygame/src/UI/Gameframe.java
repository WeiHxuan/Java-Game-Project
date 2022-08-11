package UI;

//自定义窗体
//创建类 继承JFrame
//创建构造方法，设置窗体
import javax.swing.JFrame;

public class Gameframe extends JFrame
{
	//构造方法
	public Gameframe()
	{	
		//设置标题
		setTitle("全民飞机大战    By项目31802班");
		//设置大小
		setSize(512,768);
		//设置位置居中显示  null表示相对于屏幕左上角居中
		setLocationRelativeTo(null);
		//设置禁止设置界面大小
		setResizable(false);
		//关闭窗体就退出程序
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String []args)
	{
		//实例化窗体
		Gameframe frame=new Gameframe();
		//实例化面板
		Gamepanel panel=new Gamepanel(frame);//方便在面板中加入监听事件
		//启动游戏
		panel.action();
		//将面板加入窗体
		frame.add(panel);
		//显示窗体  true为显示  false为隐藏
		frame.setVisible(true);
	}
}