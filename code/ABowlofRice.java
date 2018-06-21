package abowlofrice;
import java.util.ArrayList;

import processing.core.PApplet;
public class ABowlofRice extends PApplet {
	ArrayList<Rice> grains = new ArrayList<Rice>();
	float repelForce=-10;
	float grainRepel=1;
	int xsiz=800;
	int ysiz=800;
	int msiz=100;
	public static void main(String[] args) {
        PApplet.main("abowlofrice.ABowlofRice");
	}
	public void settings() {
		size(xsiz,ysiz);
	}
	public void setup() {
		rectMode(CENTER);
		for(int i=0; i<1000; i++) {
			grains.add(new Rice());
		}
	}
	public class Rice{
		float xpos=(float)Math.random()*(xsiz-10)+5;
		float ypos=(float)Math.random()*(ysiz-10)+5;
		float xvel=(float)Math.random()*4-2;
		float yvel=(float)Math.random()*4-2;
		float res=0.9f;
		float rot=(float)Math.random()*360;
		float rspd=3;
		public boolean collidesWith(Rice other) {
			float xdst = this.xpos - other.xpos;
			float ydst = this.ypos - other.ypos;
			float dst = xdst*xdst+ydst*ydst;
			return dst < 100;
		}
	}
	public void draw() {
		background(200);
		for(Rice grain : grains) {
			pushMatrix();
			translate(grain.xpos,grain.ypos);
			if((grain.xpos>=xsiz-10)||(grain.xpos<=10)) {
				grain.xvel=-grain.xvel*0.9f;
			}
			if((grain.ypos>=ysiz-10)||(grain.ypos<=10)) {
				grain.yvel=-grain.yvel*0.9f;
			}
			if(grain.xvel>10) {
				grain.xvel=10;
			}
			if(grain.yvel>10) {
				grain.yvel=10;
			}
			grain.xpos=grain.xpos+grain.xvel;
			grain.ypos=grain.ypos+grain.yvel;
			if(mousePressed == true) {
				float xdist=mouseX-grain.xpos;
				float ydist=mouseY-grain.ypos;
				double r= Math.sqrt((xdist*xdist)+(ydist*ydist));
				if((r<=msiz)&&(r>=0)) {
					double frc=(msiz-Math.abs(r))/30;
					xdist /= r;
					ydist /= r;
					grain.xvel -= xdist*repelForce*frc;
					grain.yvel -= ydist*repelForce*frc;
				}
			}/*
			for(Rice grain2 : grains) {
				if(grain != grain2) {
					if(grain.collidesWith(grain2)) {
						float xdst = grain.xpos - grain2.xpos;
						float ydst = grain.ypos - grain2.ypos;
						float dst = xdst*xdst+ydst*ydst;
						double gfrc=(100-Math.abs(dst))/300;
						xdst /= dst;
						ydst /= dst;
						grain.xvel -= xdst*grainRepel*gfrc;
						grain.yvel -= ydst*grainRepel*gfrc;
					}
				}
			}//*/
			grain.xvel=grain.xvel*grain.res;
			grain.yvel=grain.yvel*grain.res;
			rotate(radians(grain.rot));
			grain.rspd=(grain.xvel+grain.yvel)/4;
			grain.rot=grain.rot+grain.rspd;
			fill(255);
			ellipse(0,0,6,30);
			popMatrix();
			/* The circle causes huge performance issues
			if(mousePressed == true) {
				fill(200,200,200,0);
				ellipse(mouseX,mouseY,msiz*2,msiz*2);
			}*/
		}
	}
}