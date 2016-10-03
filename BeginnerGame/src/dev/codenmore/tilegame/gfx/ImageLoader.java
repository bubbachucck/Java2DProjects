package dev.codenmore.tilegame.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	//Buffered Image objects are where images are stored
	//path is location of image
	//this is how we load in image in java
	public static BufferedImage loadImage(String path){
		try {	//just in case error occur
			return ImageIO.read(ImageLoader.class.getResource(path));	//returns buffered image object of loaded image
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}	
		return null;	//gets rid of error if everthing goes awry
		}

}
