package proccesingproject;
import processing.core.PApplet;
public class ProccesingProject extends PApplet {
	public static void main(String[] args) {
        PApplet.main("proccesingproject.ProccesingProject");
	}
	public void settings() {
		size(1000,700,P3D);
	}

	public void setup() {
		rectMode(CENTER);
	}
int zrot=0;
int xrot=0;
int yrot=0;
int spd=5;
	public void draw() {
		frameRate(1);
		background(255,255,255);
		fill(40,40,250);
		if ((keyPressed == true) && (key == 'd')) {
			zrot=zrot+spd;	
		}
		if ((keyPressed == true) && (key == 'w')) {
			xrot=xrot+spd;
		}
		if ((keyPressed == true) && (key == 'q')) {
			yrot=yrot+spd;	
		}
		if ((keyPressed == true) && (key == 'a')) {
			zrot=zrot-spd;	
		}
		if ((keyPressed == true) && (key == 's')) {
			xrot=xrot-spd;	
		}
		if ((keyPressed == true) && (key == 'e')) {
			yrot=yrot-spd;
		}
		pushMatrix();
		translate(500,350,-300);
		rotateZ(radians(zrot));
		rotateX(radians(xrot));
		rotateY(radians(yrot));
		sphere(200);
		popMatrix();
	}
}
