import java.util.*;
import java.io.*;
public class MovingDayC {

	public static void main(String[] args) {
		FastScanner in = new FastScanner(System.in);
		int N = in.nextInt();
		in.nextInt();
		
		int[] x = new int[N];
		int[] y = new int[N];
		
		for(int i = 0; i < N; i++)
		{
			x[i] = in.nextInt();
			y[i] = in.nextInt();
		}
		
		Arrays.sort(x);
		Arrays.sort(y);
		
		int a = 0;
		int b = 0;
		
		if(N % 2 == 1)
		{
			a = x[N/2];
			b = y[N/2];
		}
		else
		{
			a = x[(N-1)/2];
			b = y[(N-1)/2];
		}
		
		System.out.println(a + " " + b);
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
3 4
1 1
2 2
4 4

2 2

4 3
1 1
1 3
3 1
3 3

1 1
*/