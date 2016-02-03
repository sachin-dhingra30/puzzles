package com.sachin.puzzles;

/**
 * @author Sachin Dhingra
 * 
 * Find Square root of a number
 * 
 * Algo:
 * Use binary search to find the square root.
 *   1. Initialize, start = 0, end = number, mid = (start+end)/2.
 *   2. Set prevMid = 0, as the previous mid value.
 *   3. Find diff = absolute difference between prevMid and mid.
 *   4. While mid is not the square root of number (i.e. mid*mid == number) or difference diff is greater than 0.0005, repeat the following steps:
 *      a. If mid*mid > number, then the square root will be less than mid. So, set end = mid.
 *      b. Else, the square root will be greater than mid. So, set start = mid.
 *      c. Set prevMid = mid
 *      d. Re-evaluate mid  = (start+end)/2.
 *      e. Re-evaluate diff from prevMid and mid.
 *
 *  If the given number is negative, then square root of the number will be square root of positive part of the number * i (iota).
 *  For example:
 *  sqRoot(-100) = sqRoot(100) * sqRoot(-1) = 10i
 */
public class SquareRoot {
	
	public static void main(String[] args) {
		System.out.println("Output:" + findSqRoot(Integer.parseInt(args[0])));
	}
	
	public static String findSqRoot(int num) {
		
		double start = 0;
		double end = num;
		boolean isNumNegative = false;
		if (num < 0) {
			num = -num;
			isNumNegative = true;
		}
		
		if(num ==1) {
			return num + (isNumNegative ? "i"  : "" ); 
		}
		
		double prevMid = 0;
		double mid = (start+end)/2;
		double diff = Math.abs(prevMid-mid);
		double precision = 0.0005;
		
		while(num != (mid*mid) && diff > precision ) {
			if (mid*mid > num) {
				end = mid;
			} else {
				start = mid;
			}
			prevMid = mid;
			mid = (start+end)/2;
			diff = Math.abs(prevMid-mid);
		}
		
		return mid + (isNumNegative ? "i"  : "" );
	}

}
