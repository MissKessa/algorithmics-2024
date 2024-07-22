package algstudent.s6;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Image {
	
	private float[][] img;
	private int width, height;
	
	//constructor to open a picture
	public Image(String img_path) {
		try 
		{
		    BufferedImage buff_img = ImageIO.read(new File(img_path)); //read image 		    
		    //store data into a float array
		    this.width = buff_img.getWidth();
		    this.height = buff_img.getHeight();
		    Raster raster = buff_img.getData();
		    this.img = new float[width][height];
		    for (int i = 0; i < width; i++)
		        for (int j = 0; j < height; j++)
		        	this.img[i][j] = raster.getSample(i, j, 0);		    		    
		    this.zeroNormalization(); //zero-Normalization
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
	}
	
	//constructor to generate an empty (zero-valued) image
	public Image(int width, int height) {
		this.width = width;
		this.height = height;
		this.img = new float[this.width][this.height];
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public float[][] getSignal() {
		return this.img;
	}
	
	//allows to add the signal (array values) from another image
	public void addSignal(Image img) {
		for (int i = 0; i < width; i++)
	        for (int j = 0; j < height; j++)
	            this.img[i][j] += img.img[i][j];
	}
	
	//adds additive Gaussian noise with some standard deviation
	public void addNoise(double noise_std) {
		Random rand = new Random();
	    for (int i = 0; i < width; i++)
	        for (int j = 0; j < height; j++)
	            this.img[i][j] += rand.nextGaussian() * noise_std;
	}
	
	//inverts pixel values
	public void invertSignal() {
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				this.img[i][j] *= -1;
	}
	
	//inverts pixel values
	public Image getInvertedImage() {
		invertSignal();
		return this;
	}
	
	/**
	 *  @region indicates the region to suppress: 0-up, 1-bottom, 2-left and 3-right
	 */
	public void suppressRegion(int region) {
		//suppression loop
		switch (region) {
			case 0: // remove up half
				for (int i=0; i<this.width; i++)
					for (int j=0; j<this.height/2; j++)
						this.img[i][j] = 0;
				break;
			case 1: // remove bottom half
				for (int i=0; i<this.width; i++)
					for (int j=this.height/2; j<this.height; j++)
						this.img[i][j] = 0;
				break;
			case 2: // remove left half
				for (int i=0; i<this.width/2; i++)
					for (int j=0; j<this.height; j++)
						this.img[i][j] = 0;
				break;
			default: // remove right half
				for (int i=this.width/2; i<this.width; i++)
					for (int j=0; j<this.height; j++)
						this.img[i][j] = 0;
		}
		
		//zero mean normalization
		this.zeroNormalization();
	}
	
	//computes Zero Mean Normalized Cross-Correlation (ZNCC) with respect to another image
	//zero normalization is already ensured during construction
	public float zncc(Image img) {
		float cc = 0;
		float std_i = 0;
		float mean_1 = this.computeMean();
		float mean_2 = img.computeMean();
		float std_1 = this.computeStd();
		float std_2 = img.computeStd();
		if ((std_1 == 0) || (std_2 == 0))
			return 0;
		std_i = (float)(1.0 / (std_1 * std_2));
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				cc += (this.img[i][j]-mean_1) * (img.img[i][j]-mean_2);
		return (cc * std_i) / (this.width * this.height);
	}
	
	//writes image data on disk (PNG format)
	public void save(String file_path) {
		int[] p = new int[1];
		int[][] img_ubyte = this.toUnsignedByte();
		BufferedImage buff_img = new BufferedImage(this.width, this.height, BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster raster = buff_img.getRaster();
		for(int i = 0; i < this.width; i++) {
	        for(int j = 0; j < this.height; j++) {
	        	p[0] = img_ubyte[i][j];
	            raster.setPixel(i, j, p);
	        }
	    }
		try {
			ImageIO.write(buff_img, "png", new File(file_path));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	//represents image values in unsigned byte range [0. 255] 
	public int[][] toUnsignedByte(){
		int[][] hold_img = new int[this.width][this.height];
		// Computes maximum and minimum values
		float min = Float.MAX_VALUE;
		float max = Float.MIN_VALUE;
		for(int i = 0; i < this.width; i++)
	        for(int j = 0; j < this.height; j++) {
	            if (this.img[i][j] > max)
	            	max = this.img[i][j];
	            if (this.img[i][j] < min)
	            	min = this.img[i][j];
	        }
		// Linear map to fit 0-255 byte range
		float a = (float) (255.0 / (max - min));
		float b = (float) (-1.0 * a * min); 
		for(int i = 0; i < this.width; i++)
	        for(int j = 0; j < this.height; j++) 
	        	hold_img[i][j] = (int)(a * this.img[i][j] + b);
	    return hold_img;
	}
	
	//zero-Normalization for computing properly cross-correlation (ZNCC)
	public void zeroNormalization() {
		float mean = this.computeMean();
		float std = this.computeStd();
		for (int i=0; i<this.img.length; i++) 
			for (int j=0; j<this.img[i].length; j++)
				this.img[i][j] = (this.img[i][j] - mean) / std;
	}
	
	//computes image gray values mean
	private float computeMean() {
		float total = 0;
		for (int i=0; i<this.width; i++) 
			for (int j=0; j<this.height; j++)
				total += this.img[i][j];
		return total / (this.width * this.height);
	}
	
	//computes image gray values standard deviation
	private float computeStd() {
		float hold;
		float total = 0;
		float mean = this.computeMean();
		for (int i=0; i<this.width; i++) 
			for (int j=0; j<this.height; j++) {
				hold = (this.img[i][j] - mean);
				total += hold * hold;
			}
		return (float) Math.sqrt(total / (this.width * this.height));
	}
	
	//returns a copy of this image
	public Image copy() {
		Image hold_img = new Image(this.width, this.height);
		hold_img.addSignal(this);
		return hold_img;
	}

}
