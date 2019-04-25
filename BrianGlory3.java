import java.util.*;
import java.io.*;
public class BrianGlory3 {
	
	public static int[] one;
	public static int[] two;
	
	public static int[] attack;
	public static int[] gold;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(in.readLine());
		attack = new int[n];
		for(int i = 0; i < n; i++)
			attack[i] = (int) Math.ceil(Integer.parseInt(st.nextToken()) / (double) d);
		
		st = new StringTokenizer(in.readLine());
		gold = new int[n];
		for(int i = 0; i < n; i++)
			gold[i] = Integer.parseInt(st.nextToken());
		
		int ones = 0;
		int twos = 0;
		
		for(int i = 0; i < attack.length; i++)
		{
			if(attack[i] == 1)
				ones++;
			else
				twos++;
		}
		
		one = new int[ones];
		two = new int[twos];
		
		ones = twos = 0;
		
		for(int i = 0; i < n; i++)
		{
			if(attack[i] == 1)
				one[ones++] = gold[i];
			else
				two[twos++] = gold[i];
		}
		
		Arrays.sort(one);
		Arrays.sort(two);
		
//		System.out.println(Arrays.toString(one));
//		System.out.println(Arrays.toString(two));
		
		long result = getWealth(k);
		
		System.out.println(result);
		
		in.close();
	}
	
	public static long getWealth(int k) {
		int temp = 0;
		for(int i = 0; i < attack.length; i++)
			temp += attack[i];
		
		if(temp <= k)
		{
			temp = 0;
			for(int i = 0; i < gold.length; i++)
				temp += gold[i];
			return temp;
		}
		
		int max = 0;
		
		int sword = k;
		int rich = 0;
		int low2 = two.length;
		for(int i = two.length-1; i >= 0 && sword > 1; i--)
		{
			sword -= 2;
			rich += two[i];
			low2 = i;
		}
		
		
		int low1 = one.length;
		if(sword > 0)
		{
			for(int i = one.length-1; i >= 0 && sword > 0; i--)
			{
				sword--;
				rich += one[i];
				low1 = i;
			}
		}
		
		max = rich;
		
		for(int i = low2; i < two.length; i++)
		{
			rich -= two[i];
			
			if(low1 >= 1)
				rich += one[--low1];
			if(low1 >= 1)
				rich += one[--low1];
			
			max = Math.max(rich, max);
		}
		
		return max;
	}

}
/*
/*
10 7 125
200 88 39 188 22 45 56 83 125 126
154 128 128 112 90 2 139 118 55 59

757


5 2 100
56 59 54 32 111
20 64 11 52 110

116

5 3 1
1 1 1 1 1
5 4 3 2 1

12

5 3 1
2 2 2 2 2
5 4 3 2 1

5
*/
