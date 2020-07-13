package org.jdamico.xorgraphicsymmetry;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jdamico.xorgraphicsymmetry.components.Controller;
import org.jdamico.xorgraphicsymmetry.dataobjects.Binary;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	
	private static final Color BLACK = Color.BLACK;
	private static final Color WHITE = Color.WHITE;
	private static final Color DARK_RED = new Color(145,8,8); 
	
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
    	super(testName);
    	
    	Graphics2D g2dFS;
		BufferedImage bufferedImage = null;

		int l = 120;
		
		int frame = l;

		int w = frame;
		int h = frame;

		BufferedImage buffGlobal = new BufferedImage(w*16, h*16, BufferedImage.TYPE_INT_RGB);

		int x = 0;

		for(int i=0; i<16; i++){

			for(int n=0; n<16; n++){
				
				bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
				g2dFS = bufferedImage.createGraphics();
				g2dFS.setColor(DARK_RED);
				g2dFS.fillRect(0, 0, w*16, h*16);
				g2dFS.setColor(BLACK);
				
				Binary a = Controller.getInstance().intToBinary(i);
				Binary b = Controller.getInstance().intToBinary(n);

				int[] xor = new int[4];

				for(int k=0; k<4; k++){

					xor[k] = Controller.getInstance().xor(a.getBitArray()[k], b.getBitArray()[k]);

					if(xor[k]==1 && k == 3) g2dFS = representBinary(g2dFS,x,l,20,BLACK);
					else if(xor[k]==0 && k == 3) g2dFS = representBinary(g2dFS,x,l,20,WHITE);
					if(xor[k]==1 && k == 2) g2dFS = representBinary(g2dFS,x,l,40,BLACK); 
					else if(xor[k]==0 && k == 2) g2dFS = representBinary(g2dFS,x,l,40,WHITE); 
					if(xor[k]==1 && k == 1) g2dFS = representBinary(g2dFS,x,l,60,BLACK);
					else if(xor[k]==0 && k == 1) g2dFS = representBinary(g2dFS,x,l,60,WHITE);
					if(xor[k]==1 && k == 0) g2dFS = representBinary(g2dFS,x,l,80,BLACK);
					else if(xor[k]==0 && k == 0) g2dFS = representBinary(g2dFS,x,l,80,WHITE);
					

				}
				
				//g2dFS.drawString(Controller.getInstance().intArrayToString(xor), 2, l-10);
				Graphics2D globalG2D = buffGlobal.createGraphics();
				globalG2D.setColor(new Color(145,8,8));
				globalG2D.drawImage(bufferedImage, i*l, n*l, null);
				System.out.println("["+i+","+n+"] "+Controller.getInstance().intToBinary(i).getStrBitArray()+","+Controller.getInstance().intToBinary(n).getStrBitArray()+","+Controller.getInstance().intArrayToString(xor));
				
				
			}	


		}

		
		String filename = "/tmp/test.png";
		try {
			File file = new File(filename);
			ImageIO.write(buffGlobal, "png", file);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    private Graphics2D representBinary(Graphics2D g2dFS, int x, int l, int il, Color c) {
		g2dFS.setColor(Color.BLACK);
		g2dFS.setColor(c);
		g2dFS.fillOval(((x+l)/2)-(il/2), (l/2)-(il/2), il, il); 
		g2dFS.setColor(Color.WHITE);
		return g2dFS;
	}
}
