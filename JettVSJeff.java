import java.util.*;
public class JettVSJeff {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		
		boolean[] win = new boolean[15];
		win[1] = true;
		win[2] = true;
		win[3] = true;
		
		for(int i = 4; i < win.length; i++)
		{
			if(!win[i-2] || !win[i-3])
			{
				win[i] = true;
				continue;
			}
			
			for(int j = 1; j <= (i-1)/2; j++)
			{
				boolean left = win[j-1];
				boolean right = win[i-j-2];
				
				if(j == 1 && !right)
				{
					win[i] = true;
					break;
				}
				
				if(j != 1 && !(left ^ right))
				{
					win[i] = true;
					break;
				}
			}
		}
		
		print(win);
		
		for(int t = 0; t < T; t++)
		{
			int N = in.nextInt();
			System.out.println((win[N]) ? "JETT" : "JEFF");
		}
		in.close();
	}
	
	public static void print(boolean[] a) {
		for(int i = 0; i < a.length; i++)
		{
			if(a[i])
				System.out.print(1);
			else
				System.out.print(0);
		}
		System.out.println();
	}

}
/*
2
1
4

JETT
JEFF
*/