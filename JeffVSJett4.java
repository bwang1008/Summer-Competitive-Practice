import java.util.*;
import java.io.*;
public class JeffVSJett4 {
	
	public static long[][] stirling1;
	public static int p = 1_000_000_007;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int Q = in.nextInt();
		int[][] q = new int[Q][2];
		
		for(int i = 0; i < q.length; i++)
			q[i] = new int[] {in.nextInt(), in.nextInt()};
		
		initialize();
		for(int i = 0; i < q.length; i++)
			out.println(stirling1[q[i][0]][q[i][1]]);
		
		in.close();
		out.close();
	}
	
	public static void initialize() {
		stirling1 = new long[1001][1001];
		stirling1[0][0] = 1;
		for(int i = 0; i < stirling1.length-1; i++)
			for(int j = 1; j <= i+1; j++)
				stirling1[i+1][j] = (i*stirling1[i][j] + stirling1[i][j-1]) % p;
	}
	
}
/*
4
2 2
3 2
5 3
10 4

1
3
35
723680
*/