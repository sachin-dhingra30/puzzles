/**
 * 
 */
package com.sachin.puzzles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Sachin Dhingra
 *
 */
public class SkiingProblem {

	// Check if the new problem started.
	private boolean isTokenStartWithString(String[] tokens) {
		try{
			Integer.valueOf(tokens[0]);
			return false;
		}catch(NumberFormatException e) {
			return true;
		}
	}

	// Traverse all nodes in matrix and find the longest ski path
	private int findLongestRoute(Integer[][] matrix)
	{
		String longestSkiPath = "";

		//rows
		for(int i = 0; i<matrix.length; i++)
		{   //cols
			for(int j = 0; j<matrix[i].length; j++)
			{
				String returnSkiPath = findLongestRouteOfNode(matrix, i, j);

				if(returnSkiPath.length() > longestSkiPath.length()) {
					longestSkiPath = returnSkiPath;
				}
			}
		}

		//	        System.out.println(longestSkiPath.toString());
		return longestSkiPath.split(" ").length;	        

	}

	// Check the left, right, upper and down longest ski path and return the max of them.
	private String findLongestRouteOfNode(Integer[][] matrix, int row, int col)
	{
		String maxPath = getLeftPath(matrix, row, col);

		String rightRun = getRightPath(matrix, row, col);

		if(rightRun.length() > maxPath.length())
			maxPath = rightRun;

		String upperRun = getUpperPath(matrix, row, col);

		if(upperRun.length() > maxPath.length())
			maxPath = upperRun;

		String downRun = getDownPath(matrix, row, col);

		if(downRun.length() > maxPath.length())
			maxPath = downRun;


		if (!maxPath.isEmpty())
			maxPath += " " + matrix[row][col];
		else
			maxPath += matrix[row][col];
		return maxPath;
	}

	// Get longest ski path for left index.
	private String getLeftPath(Integer[][] matrix, int row, int col)
	{
		String leftRun = "";
		int left = col-1;

		// check if left exist and its value is less than current index.
		if(left >= 0 && matrix[row][left] < matrix[row][col]) {	            
			leftRun = findLongestRouteOfNode(matrix, row, left);
		}
		return leftRun;
	}

	// Get longest run for right index.
	private String getRightPath(Integer[][] matrix, int row, int col)
	{
		String rightRun = "";
		int right = col+1;

		// check if right exist and its value is less than current index.
		if(right < matrix[0].length && matrix[row][right] < matrix[row][col]) {
			rightRun = findLongestRouteOfNode(matrix, row, right);
		}

		return rightRun;
	}

	// Get longest run for upper index.
	private String getUpperPath(Integer[][] matrix, int row, int col)
	{
		String upperRun = "";
		int up = row-1;

		// check if upper index exist and its value is less than current index.
		if(up >= 0 && matrix[up][col] < matrix[row][col]) {
			upperRun =  findLongestRouteOfNode(matrix, up, col);
		}

		return upperRun;
	}

	// Get longest run for down index.
	private String getDownPath(Integer[][] matrix, int row, int col)
	{
		String downRun = "";
		int down = row+1;

		// check if down index exist and its value is less than current index.
		if(down < matrix.length && matrix[down][col] < matrix[row][col]) {
			downRun = findLongestRouteOfNode(matrix, down, col);
		}

		return downRun;
	}	    


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Output:");
		BufferedReader br = null;
		SkiingProblem skiingObj = new SkiingProblem();
		Integer[][] matrix = null;
		String currentLine = null;
		int rowCount = 0;
		String name = null;
		int numOfProbs;

		// Read file and create 2D array
		try {

			br = new BufferedReader(new FileReader("src/test/resources/skiingInput.txt"));
			currentLine = br.readLine();
			numOfProbs = Integer.parseInt(currentLine);
			currentLine = br.readLine();

			while (currentLine  != null && numOfProbs>=0) {
				String[] tokens =  currentLine.split(" ");

				if(skiingObj.isTokenStartWithString(tokens))
				{
					if(matrix != null)
					{
						System.out.println(name + ": "+ skiingObj.findLongestRoute(matrix));
						matrix = null;
					}
					name = tokens[0];
					rowCount = 0;
					Integer row =  Integer.valueOf(tokens[1]);
					Integer col =  Integer.valueOf(tokens[2]);
					matrix = new Integer[row][col];
					numOfProbs--;
				} else
				{
					for(int i = 0; i < tokens.length; i++)
					{
						matrix[rowCount][i] = Integer.valueOf(tokens[i]);
					}
					rowCount++;
				}
				currentLine = br.readLine();
			}

			//For the last problem
			if(matrix != null && numOfProbs>=0)
			{
				System.out.println(name + ": "+ skiingObj.findLongestRoute(matrix));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
