package com.cxd.catchballgame210126.V3;

public class Vector {
	public int x,y;
	
	public Vector(int x,int y){
		this.x=x;
		this.y=y;
	}
	public void add(Vector vec){
		this.x+=vec.x;
		this.y+=vec.y;
	}
	public void sub(Vector vec){
		this.x-=vec.x;
		this.y-=vec.y;
	}

}
