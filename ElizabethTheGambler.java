import java.io.*;
import java.util.Scanner;
public class ElizabethTheGambler {
	
	public static void main3(String[] args) {
		Scanner in = new Scanner(System.in);
		while(true)
		{
			int N = in.nextInt();
			
			if(N == 0) break;
			
			int M = in.nextInt();
			
			
			long result = minv(N, M);
			System.out.println(result);
		}
		
		in.close();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		long[] ans = new long[10];
		
		ans[0] = ans[1] = 1;
		for(long i = 1; i < ans.length-1; i++)
			ans[(int) (i+1)] = (ans[(int) i] * 2 * (2*i+1) * minv((i+2), 1000_000_007)) % 1000_000_007;
		
		int T = Integer.parseInt(in.readLine());
		
		for(int i = 0; i < T; i++)
			out.println(ans[Integer.parseInt(in.readLine())]);
		
		in.close();
		out.close();
	}
	
	public static long minv(long m, long mod) {
		int[] a = {(int) m, 1, 0};
		int[] b = {(int) mod, 0, 1};
		int[] c = new int[3];
		
		while(b[0] != 0)
		{
			int q = a[0]/b[0];
			for(int i = 0; i < 3; i++)
				c[i] = a[i] - b[i] * q;
			a = b;
			b = c;
			c = new int[3];
		}
		
		System.out.println(a[1] + " " + a[2]);
		
		if(a[1] < 0) a[1] += mod; 
		return a[1];
	}

}
/*
3
0
2
5

1
2
42
*/