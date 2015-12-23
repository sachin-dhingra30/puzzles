/**
 * 
 */
package com.sachin.puzzles;

/**
 * @author Sachin Dhingra
 *
 */
public class StringCharReverse {

	// Reverse characters of words in a sentence
	public String reverse(String str) {
		String output = "";
		int pre = 0;
		int curr=0;
		char[] arr = str.toCharArray();
		for(int i=0; i<arr.length; i++) 
		{	
			if(arr[i] == ' ' || i==arr.length-1) {
				// To handle last word in sentence
				if (i==arr.length-1) {
					curr = i;
				} else {
					curr = i-1;
				}
				while(curr >= pre) {
					output+=arr[curr];
					curr--;
				}
				output+=" ";
				pre=i;				
			}
		}
		return output;
	}
	
	public static void main(String[] args) {
		StringCharReverse str = new StringCharReverse();
		System.out.println(str.reverse("Hello world"));
	}

}
