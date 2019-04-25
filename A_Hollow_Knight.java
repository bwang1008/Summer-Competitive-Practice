import java.util.*;
import java.io.*;
public class A_Hollow_Knight {
	
	public static boolean[] shaded;
	public static int[][] roads;

	public static void main(String[] args) {
		FastScanner in = new FastScanner(System.in);
		int N = in.nextInt();
		String bin = in.next();
		shaded = new boolean[N+1];
		for(int i = 0; i < bin.length(); i++)
			if(bin.charAt(i) == '1')
				shaded[i+1] = true;
		
		boolean[][] adj = new boolean[N+1][N+1];
		for(int i = 0; i < N-1; i++)
		{
			int a = in.nextInt();
			int b = in.nextInt();
			adj[a][b] = adj[b][a] = true;
		}
		
		roads = new int[N+1][N+1];
		for(int i = 0; i < adj.length; i++)
		{
			int temp = 0;
			for(int j = 0; j < adj[i].length; j++)
				if(adj[i][j])
					temp++;
			roads[i] = new int[temp];
			temp = 0;
			for(int j = 0; j < adj[i].length; j++)
				if(adj[i][j])
					roads[i][temp++] = j;
		}
		
		int ans = 0;
		for(int i = 1; i <= N; i++)
		{
			if(shaded[i]) continue;
			ans++;
			dfs(i);
		}
		
		System.out.println(ans);
	}
	
	public static void dfs(int curr) {
		if(shaded[curr]) return;
		shaded[curr] = true;
		for(int i : roads[curr])
			dfs(i);
	}
	
	/**
	 * Source: Matt Fontaine
	 */
	public static class FastScanner {
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;

		public FastScanner(InputStream stream) {
			this.stream = stream;
		}

		int read() {
			if (numChars == -1)
				throw new InputMismatchException();
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (numChars <= 0)
					return -1;
			}
			return buf[curChar++];
		}

		boolean isSpaceChar(int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		boolean isEndline(int c) {
			return c == '\n' || c == '\r' || c == -1;
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public String next() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isSpaceChar(c));
			return res.toString();
		}

		public String nextLine() {
			int c = read();
			while (isEndline(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isEndline(c));
			return res.toString();
		}
	}

}
/*
7
0000001
1 2
2 4
2 7
7 6
6 3
7 5

3

7
1111111
1 2
2 4
2 7
7 6
6 3
7 5

0

1
0

1

1
1

0
*/