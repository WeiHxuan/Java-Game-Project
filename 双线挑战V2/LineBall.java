package com.cxd.linecargame210127.V2;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LineBall {
	public volatile Vector location;
	public volatile Vector speed;
	public int len=0;
	public String imgName;
	public String fileAddress="image_lineballgame/";
	public Boolean gameOver=false;
	
	public static int[][] chessBoard=new int [70][70];
	
	public LineBall(Vector location,Vector speed){
		this.location=location;
		this.speed=speed;
	}
	
	public void move(){
		len++;
		location.add(speed);
	}
	
	public void drawLB(Graphics g){
		if(imgName==null){
			chessBoard[location.x][location.y]=1;
			g.fillRect(location.x*10+50, location.y*10+50, 10,10);
		}else{
			chessBoard[location.x][location.y]=1;
			ImageIcon imgic=new ImageIcon(fileAddress+imgName);
			Image img=imgic.getImage();
			g.drawImage(img,location.x*10+50, location.y*10+70, null);
		}
	}
	
	public Boolean judge_gameover(){
		if(location.x>69|location.y>69|location.x<0|location.y<0){
			gameOver=true;
			return true;
		}else if(chessBoard[location.x][location.y]==1){
			gameOver=true;
			return true;
		}else{
			gameOver=false;
			return false;
		}
	}

}
