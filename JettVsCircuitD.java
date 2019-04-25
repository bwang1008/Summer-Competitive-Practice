import java.util.*;
import java.io.*;
public class JettVsCircuitD {

	public static void main(String[] args) {
		FastScanner in = new FastScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int N = in.nextInt();
		int M = in.nextInt();
		
		int[][] arr = new int[N][M];
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++)
				arr[i][j] = in.nextInt();
		
		int[] row = new int[N];
		int[] col = new int[M];
		int[] c = new int[N];
		int[] c2 = new int[M];
		
		for(int i = 0; i < N; i++)
			row[i] = i;
		for(int i = 0; i < M; i++)
			col[i] = i;
		
		int Q = in.nextInt();
		for(int q = 0; q < Q; q++)
		{
			String g = in.next();
			int A = in.nextInt() - 1;
			int B = in.nextInt() - 1;
			
			if(g.equals("swaprow"))
			{
				int t = row[A];
				row[A] = row[B];
				row[B] = t;
				c[A]++;
				c[B]++;
				if(c[A] > 1 || c[B] > 1) System.out.println("WHAT");
			}
			else if(g.equals("swapcol"))
			{
				int t = col[A];
				col[A] = col[B];
				col[B] = t;
				c2[A]++;
				c2[B]++;
			}
			else
			{
				int i;
				int count = 0;
				for(i = A; count < c[A]; i = row[i], count++);
				
				int j;
				count = 0;
				for(j = B; count < c2[B]; j = col[j], count++);
				
//				out.println("i = " + i + " j = " + j + " arr[i][j] = " + arr[i][j]);
				out.println(arr[i][j]);
			}
		}
		
		out.close();
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
3 3
1 2 3
4 5 6
7 8 9
5
swaprow 1 2
swapcol 1 2
get 1 1
swaprow 3 2
get 3 3

5
3
*/