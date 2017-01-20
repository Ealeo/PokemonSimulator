import javax.swing.*;
import java.io.*;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class PokeSim
{
	//Path for music (Constant variable).
	final static String url = "music/pokemon.wav";
	
	//Thread	
	static Thread mainThread;

	//Images
	BufferedImage oakImg = null,
				  oakWithPbImg = null,
				  oakWithNFImg = null,
				  rivalImg = null,
				  playerImg = null;
		//Not sure if this is the best way to display them in a GUI, but best I could find.	  

	//GUI Variables
	JFrame frame;
	JPanel panel;
	
	//Reference to other program
	static Pokemon intro = new Pokemon();
	
	//User input variables
	static int response;
	static String holder;
	static String[] options = {"Boy", "Girl"};
			
	public PokeSim()
	{
		try
		{
			oakImg = ImageIO.read(new File("images/oak.jpg"));
			oakWithPbImg = ImageIO.read(new File("images/oakwithpokeball.jpg"));
			oakWithNFImg = ImageIO.read(new File("images/oakwithnidoranf.jpg"));
			rivalImg = ImageIO.read(new File("images/rival.jpg"));
		}
		catch (IOException e)
		{
		}
		
		JLabel oak = new JLabel(new ImageIcon(oakImg));
		JLabel oakWithPb = new JLabel(new ImageIcon(oakWithPbImg));
		JLabel oakWithNF = new JLabel(new ImageIcon(oakWithNFImg));
		JLabel rival = new JLabel(new ImageIcon(rivalImg));
		
		
		frame = new JFrame("Pokemon SLUH Blue Version");
		panel = new JPanel();
		
		frame.add(panel);
		
		panel.add(oak);
		panel.repaint();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setVisible(true);
		
	}
	  
	public static void main(String [] args)
	{
		mainThread = new Thread(() -> //the () -> this is a lambda expression. Thread knows it needs runnable --> Runnable only has one method, and the lambda expression takes its place.
		{
			try 
			{
			      Clip clip = AudioSystem.getClip();
			      AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(url));
			      PokeSim control = new PokeSim();
			      clip.open(inputStream);
			      while(true)
			      {
			     	 clip.start();
			      }
			}
			catch (Exception e) 
			{
				System.err.println(e.getMessage());
			}
		});
		
		mainThread.start();
	}
}