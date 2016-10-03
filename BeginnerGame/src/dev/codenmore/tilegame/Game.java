package dev.codenmore.tilegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import dev.codenmore.tilegame.display.Display;
import dev.codenmore.tilegame.gfx.ImageLoader;
import dev.codenmore.tilegame.gfx.SpriteSheet;

public class Game implements Runnable{
	private Display display;

	public int width, height;
	public String title;
	
	//Threads allows a mini program to run seperate of big program. Runnable is implemented 
	// to allow game class to run on its own thread.
	//running variable used in run class while loop 
	private boolean running = false;
	private Thread thread;
	
	//bufferstrategy are ways and how computer draws things to screen
	//buffers are hidden computer screen within computer (actually data)
	//buffers are useful to prevent flickering in game (old games used to draw directly to screen which caused buffering
	private BufferStrategy bs;
	private Graphics g;
	
	
	
	public Game(String title, int width, int height){
		this.width=width;
		this.height=height;
		this.title=title;
		
		
		
	}
	
	//initialize graphics for game
	private void init(){
		display = new Display(title, width, height);
		
		
	}
	
	//update everything in game
	//render actually means drawing things to the screen
	private void tick(){
		
	}
	
	//render everything in game
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){	//checks if there is a bufferstrategy, if not, create one
			display.getCanvas().createBufferStrategy(3);	//3 is maximum amount of buffers??
			return;
		}
		g = bs.getDrawGraphics();
		//Clear screen
		g.clearRect(0, 0, width, height);
		//Draw here!
		
		
		bs.show();	//always use these 2 lines of code to end drawing
		g.dispose();//buffer.show() works buffer magic while g.dispose() finalizes drawing
		
		
		
		
		
		
		
	}
	
	//Run method is mandatory because it is an abstract method of the Runnable class, which in turn implements thread
	//runs the game loop!
	//Game Loop: Updates all variables, positions of objects, etc, Renders(draws) everything to the screen, repeat over and over
	public void run(){
		
		init();
		while(running){
			tick();
			render();
		}
		stop();
		
	}
	
	
	//start method starts thread
	//synchronized is used when working with threads directly
	//thread.start calls the run method from above
	//Thread(this) allows this class to be run on its own thread, thread constructor takes in class that you want to run on thread
	public synchronized void start(){
		if(running){	//prevents from starting a program again if true, prevents errors
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	//stop method stops thread
	//.join should must be surrounded by try catch statement
	//a try catch statement is used to catch exceptions. Always use try catch statement to avoid program crash in case exception is thrown
	public synchronized void stop(){
		if(!running){	//prevents from stopping program again, prevents errors
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

}
