package mines;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Landmines {
	int[][] landmines;
	
	int shortestLength=Integer.MAX_VALUE;
	int[][] shortestSolution;
	
	int length=1;
	int[][]solution;
	boolean solutionFound;

	public Landmines(String path) throws IOException {
		FileReader f=null;
		BufferedReader b=null;
		ArrayList<String> lines;
		String line;
		try {
			f= new FileReader(path);
			b= new BufferedReader(f);
			lines = new ArrayList<String>();
			while(true) {
				line=b.readLine();
				if(line==null) {
					break;
				}
				lines.add(line);
			}
		} finally {
			b.close();
			f.close();
		}
		
		for(int i=0; i< lines.size();i++) {
			line=lines.get(i);
			String[] row=line.split(" ");
			if(i==0) {
				landmines = new int[lines.size()][row.length];
				solution= new int[lines.size()][row.length];
				shortestSolution= new int[lines.size()][row.length];
				shortestLength=lines.size()*row.length;
			}
			
			for(int j=0; j<row.length;j++) {
				int value=Integer.parseInt(row[j]);
				landmines[i][j]=value;
				solution[i][j]=value;
			}
		}
		
	}
	
	public Landmines(int[][] landmines) {
		this.landmines = landmines;
		solution=landmines.clone();
	}
	
	public void backtracking() {
		for(int i=0; i<landmines.length;i++) {
			if(isSafeCell(i, 0)) {
				solution[i][0]=2;
				backtracking(i,0);
				solution[i][0]=1;
			}
		}
	}
	
	public void printSolution() {
		for(int i=0; i<landmines.length;i++) {
			for (int j=0; j<landmines[i].length;j++) {
				System.out.print(landmines[i][j]+ " ");
			}
			System.out.println();
		}
		
		if(solutionFound) {
			System.out.println("LENGTH: "+shortestLength);
			for(int i=0; i<shortestSolution.length;i++) {
				for (int j=0; j<shortestSolution[i].length;j++) {
					System.out.print(shortestSolution[i][j]+ " ");
				}
				System.out.println();
			}
		} else {
			System.out.println("SOLUTION NOT FOUND");
		}
		
	}

//	int[] movementsX= {0,1,0,-1}; //down,right,up,left
//	int[] movementsY= {1,0,-1,0};
	
	int[] movementsX= {1,0,0,-1}; //right,down,up,left
	int[] movementsY= {0,1,-1,0};
	
	private void backtracking(int i, int j) {
		if(j==0 && length>1) {
			return;
		}
		if(j==landmines[i].length-1) {
			if(length<shortestLength) {
				//shortestSolution=solution.clone();
				for(int y=0; y<solution.length;y++) {
					for (int x=0; x<solution[y].length;x++) {
						shortestSolution[y][x]=solution[y][x];
					}
				}
				shortestLength=length;
				solutionFound=true;
			}
		} else {
			for(int k=0; k<movementsX.length && length<shortestLength;k++) {
				if(j==0 && !(movementsX[k]==1 && movementsY[k]==0)) {
					continue;
				}
				int newX=movementsX[k]+j;
				int newY=movementsY[k]+i;
				
				if(isSafeCell(newY,newX) && solution[newY][newX]!=2) {
					solution[newY][newX]=2;
					length++;
					backtracking(newY,newX);
					solution[newY][newX]=1;
					length--;
				}
			}
		}
	}
	
	private boolean isSafeCell(int i, int j) {
		if(i<0 || i>=landmines.length || j<0 || j>=landmines[i].length) {
			return false;
		}
		if (landmines[i][j]==0) //is a mine
			return false;
		
		if(i+1<landmines.length && landmines[i+1][j]==0) { //mine down
			return false;
		}
		if(i-1>=0 && landmines[i-1][j]==0) { //mine up
			return false;
		}
		
		if(j+1<landmines[i].length && landmines[i][j+1]==0) { //mine right
			return false;
		}
		if(j-1>=0 && landmines[i][j-1]==0) { //mine left
			return false;
		}
		return true;
	}
}
