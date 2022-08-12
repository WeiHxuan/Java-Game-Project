package com.cxd.threadGame210124.V3;

public class Vector {
	
	public int x,y;
	
	public Vector(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	public void add(Vector v){
		this.x+=v.x;
		this.y+=v.y;
	}
	
	public void sub(Vector v){
		this.x-=v.x;
		this.y-=v.y;
	}

}
