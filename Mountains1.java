import java.util.*;
public class Mountains1 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		
		int[] height = new int[n];
		
		for(int i = 0; i < n; i++)
			height[i] = in.nextInt();
		
		int k = in.nextInt() - 1;
		
		int left = 0;
		int right = 0;
		
		while(k - left - 1 >= 0 && height[k-left-1] < height[k])
			left++;
		
		while(k + right + 1 < n && height[k+right+1] < height[k])
			right++;
		
		System.out.println(left + " " + right);
		
		in.close();
	}

}
/*
5
1 3 4 2 4
3

2 1
*/