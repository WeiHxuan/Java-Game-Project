package prop;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

//����ͼƬ
public class picture {
	//����static���õķ���
		public static BufferedImage getImg(String path)
		{
			//����ͼƬ
			try {
				BufferedImage img=ImageIO.read(picture.class.getResource(path));
				return img;//�ҵ�ͼƬ�ͷ���
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
}
