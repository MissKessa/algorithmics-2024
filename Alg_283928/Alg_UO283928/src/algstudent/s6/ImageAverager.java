package algstudent.s6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ImageAverager {
	
	private static final int DEFAULT_SIZE = 256;
	private Image real_img, bad_img; //to store the main good and main bad image
	private Image avg_img, half1_img, half2_img; //to store the final tests to see if we improve the previous results
	private Image[] dataset; //dataset to store all the images (good and bad ones)
	private int[] sol; //to store the partial results (where I am putting the pictures? 0->not assigned, 1->first half, 2->second half
	private int[] bestSol; //to store the best solution
	private int width, height; //to store the width and height of the image
	//backtracking variables
	private int counter; //to store the number of times we assign an image to half1, half2 or no group
	private double max_zncc; //to store the best ZNCC
	private int set2Length = 0;
	private int set1Length = 0;
	
	/** Constructor
	* @real_path  path to the real image (pattern to find) on disk
	* @bad_path  path to the bad image on disk
	* @n_real  number of real images in the dataset (>= 1)
	* @n_bad  number of bad images in the dataset 
	* @s_noise  standard deviation for noise 
	*/
	public ImageAverager(String real_path, String bad_path, int n_real, int n_bad, double s_noise) {
		assert (n_real >= 1) && (n_bad < n_real);//assert at least one reference image
		
		//load reference and bad images
		this.real_img = new Image(real_path);
		this.bad_img = new Image(bad_path);
		this.width = this.real_img.getWidth();
		this.height = this.real_img.getHeight();
		
		//create the dataset as an array of unordered randomly chosen real and bad images
		int total_imgs = n_real + n_bad; //the total number of images are the good + the bad ones
		this.dataset = new Image[total_imgs]; //the data set for the total of images
		this.sol = new int[total_imgs]; //we will use this variable during the process 0->not assigned, 1->first half, 2->second half 
		this.bestSol = new int[total_imgs]; //we will use this variable to store the best results
		int[] rand_index = this.randomIndexes(total_imgs); //random array of positions to mix images
		Image hold_img; //temp images
		int region = 0; // 0-up, 1-down, 2-left, 3-right
		for (int i=0; i<n_real; i++) { //to save good images
			hold_img = new Image(this.width, this.height); //generate image
			hold_img.addSignal(this.real_img); //save the image
			hold_img.suppressRegion(region); //a half part of the image is deleted
			hold_img.addNoise(s_noise); //add some noise 
			this.dataset[rand_index[i]] = hold_img; //save image
			if (region == 3) region = 0;
			else region++;
		}
		region = 0;
		for (int i=n_real; i<n_real+n_bad; i++) { //to save bad images
			hold_img = new Image(this.width, this.height); //generate image
			hold_img.addSignal(this.bad_img); //save the image
			hold_img.invertSignal(); //corrupt the image
			hold_img.suppressRegion(region); //the fourth part of the image is deleted
			hold_img.addNoise(s_noise); //add some noise  
			this.dataset[rand_index[i]] = hold_img; //save image
			if (region == 3) region = 0;
			else region++;
		}
	}
	
	/**
	 * To generate a random array of positions
	 * @param n Length of the array
	 * @return 
	 */
	public int[] randomIndexes(int n) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++)
			list.add(i);
		Collections.shuffle(list);
		int[] array = new int[n];
		for (int i = 0; i < n; i++)
			array[i] = list.get(i);
		return array;
	}
	
	/**
	 * Store resulting images for testing
	 * @out_dir directory save the output images
	 */
	public void saveResults(String out_dir) {
		this.avg_img = half1_img;
		avg_img.addSignal(half2_img);
		this.avg_img.save(out_dir + "/img_avg.png");
		this.half1_img.save(out_dir + "/img_half1_avg.png");
		this.half2_img.save(out_dir + "/img_half2_avg.png");
		for(int i=0; i<this.dataset.length; i++) {
			this.dataset[i].save(out_dir + "/img_" + i + "_klass_" + this.bestSol[i] + ".png");
		}
	}
	
	/**
	 * @return the number of steps carried out by the algorithm to solve the problem
	 */
	public int getCounter() {
		return counter;
	}
	
	/** Computes the ZNCC between both image dataset halves
	 * @return the computed ZNCC
	 */
	public double zncc() {
		return this.half1_img.zncc(this.half2_img);
	}
	
	/**
	 * Greedy algorithm: random instances for each half, the best one is the final solution    
	 * @n_tries number of random tries     
	 */
	public void splitSubsetsGreedy(int n_tries) {	
		// TODO
		int auxZNCC = Integer.MIN_VALUE;
		half1_img = new Image(DEFAULT_SIZE, DEFAULT_SIZE);
		half2_img = new Image(DEFAULT_SIZE, DEFAULT_SIZE);
		for ( int i = 0; i < n_tries; i++ ) {
			for( int j = 0; j < dataset.length; j++ ) {
				sol[j] = new Random().nextInt(3);
				if( sol[j] == 0 ) continue;
				if( sol[j] == 1 ) half1_img.addSignal( dataset[ j ] );
				if( sol[j] == 2 ) half2_img.addSignal( dataset[ j ] );
			}
			
			if( zncc() > auxZNCC ) {
//				auxHalf1 = half1_img;
//				auxHalf2 = half2_img;
				bestSol = sol;
			} 
			half1_img = new Image(DEFAULT_SIZE, DEFAULT_SIZE);
			half2_img = new Image(DEFAULT_SIZE, DEFAULT_SIZE);
			
		}
		
		
		for ( int i = 0; i < bestSol.length; i++ ) {
			if( bestSol[i] == 1 ) half1_img.addSignal(dataset[i]);
			if( bestSol[i] == 2 ) half2_img.addSignal(dataset[i]);
		}
		this.avg_img = new Image( DEFAULT_SIZE, DEFAULT_SIZE );
		this.avg_img.addSignal(half1_img);
		this.avg_img.addSignal(half2_img);
	}
	
	/**
	 * Backtracking algorithm 
	 * @max_unbalancing: (pruning condition) determines the maximum difference between 
	 * the number of images on each half set               
	 */
	public void splitSubsetsBacktracking(int max_unbalancing) {
		// TODO
		half1_img = new Image(DEFAULT_SIZE, DEFAULT_SIZE);
		half2_img = new Image(DEFAULT_SIZE, DEFAULT_SIZE);
		max_zncc = Integer.MIN_VALUE;
		backtracking(0, max_unbalancing);
		
		half1_img = new Image(DEFAULT_SIZE, DEFAULT_SIZE);
		half2_img = new Image(DEFAULT_SIZE, DEFAULT_SIZE);
		
		
		for ( int i = 0; i < bestSol.length; i++ ) {
			if( bestSol[i] == 1 ) half1_img.addSignal(dataset[i]);
			if( bestSol[i] == 2 ) half2_img.addSignal(dataset[i]);
		}
		this.avg_img = new Image( DEFAULT_SIZE, DEFAULT_SIZE );
		this.avg_img.addSignal(half1_img);
		this.avg_img.addSignal(half2_img);
	}

	private void backtracking(int level, int max_unbalancing) {

		if (level == sol.length ) {
			//solution
			//take pics from array
			//compute and see if similar
			half1_img = new Image( DEFAULT_SIZE,DEFAULT_SIZE );
			half2_img = new Image( DEFAULT_SIZE,DEFAULT_SIZE );
			for( int j = 0; j < dataset.length; j++ ) {
				if( sol[j] == 0 ) continue;
				if( sol[j] == 1 ) half1_img.addSignal( dataset[ j ] );
				if( sol[j] == 2 ) half2_img.addSignal( dataset[ j ] );
			}
			
			if( half1_img.zncc(half2_img) > max_zncc ) {
				max_zncc = half1_img.zncc(half2_img);
//				bestSol = sol;
				bestSol = Arrays.copyOf(sol, sol.length);
//				System.out.println( max_zncc );
			}
			
		} else {
			//for this level
			
			// Group 1
			if ( Math.abs(set2Length - set1Length) <= max_unbalancing  ) {
				set1Length++;
				sol[level] = 1;// Inserting the img in set 1
				backtracking(level + 1, max_unbalancing);
				set1Length--;
			}
			
			// Group 2
			if ( Math.abs(set2Length - set1Length) <= max_unbalancing  ) {
				set2Length++;
				sol[level] = 2; // Inserting the img in set 2 and removing from 1
				backtracking(level + 1, max_unbalancing);
				set2Length--;
			}
				
			//group 0
			sol[level] = 0; 
			backtracking(level + 1, max_unbalancing); // Just ignore img if belongs to set 0
			
		}
	}

	/**
	 * Backtracking algorithm without balancing. Using a larger than the number of images in the dataset ensures no prunning          
	 */
	public void splitSubsetsBacktracking() {
		// TODO
		half1_img = new Image(DEFAULT_SIZE, DEFAULT_SIZE);
		half2_img = new Image(DEFAULT_SIZE, DEFAULT_SIZE);
		max_zncc = Integer.MIN_VALUE;
		backtracking(0);
		
		half1_img = new Image(DEFAULT_SIZE, DEFAULT_SIZE);
		half2_img = new Image(DEFAULT_SIZE, DEFAULT_SIZE);
		
		for ( int i = 0; i < bestSol.length; i++ ) {
			if( bestSol[i] == 1 ) half1_img.addSignal(dataset[i]);
			if( bestSol[i] == 2 ) half2_img.addSignal(dataset[i]);
		}
		this.avg_img = new Image( DEFAULT_SIZE, DEFAULT_SIZE );
		this.avg_img.addSignal(half1_img);
		this.avg_img.addSignal(half2_img);
	
	}

	
	private void backtracking(int level) {
		
		if (level == sol.length )
		{
			//solution
			//take pics from array
			//compute and see if similar
			half1_img = new Image( DEFAULT_SIZE,DEFAULT_SIZE );
			half2_img = new Image( DEFAULT_SIZE,DEFAULT_SIZE );
			for( int j = 0; j < dataset.length; j++ ) {
				if( sol[j] == 0 ) continue;
				if( sol[j] == 1 ) half1_img.addSignal( dataset[ j ] );
				if( sol[j] == 2 ) half2_img.addSignal( dataset[ j ] );
			}
			
			if( half1_img.zncc(half2_img) > max_zncc ) {
				max_zncc = half1_img.zncc(half2_img);
//				bestSol = sol;
				bestSol = Arrays.copyOf(sol, sol.length);
//				System.out.println( max_zncc );
			}
			
		} else {
			//for this level
			
			// Group 1
			sol[level] = 1;// Inserting the img in set 1
			backtracking(level + 1);
			
			// Group 2
			sol[level] = 2; // Inserting the img in set 2 and removing from 1
			backtracking(level + 1);
			
			//group 0
			sol[level] = 0; 
			backtracking(level + 1); // Just ignore img if belongs to set 0
			
		}
	}

}
