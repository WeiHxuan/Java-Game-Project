package prop;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

//处理图片
public class picture {
	//定义static公用的方法
		public static BufferedImage getImg(String path)
		{
			//加载图片
			try {
				BufferedImage img=ImageIO.read(picture.class.getResource(path));
				return img;//找到图片就返回
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
}
