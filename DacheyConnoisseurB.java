import java.util.*;
import java.io.*;
public class DacheyConnoisseurB {

	public static void main(String[] args) {
		FastScanner in = new FastScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int T = in.nextInt();
		for(int t = 0; t < T; t++)
		{
			long A = in.nextLong();
			long B = in.nextLong();
			int C = in.nextInt();
			
			long result = food(A, B, C);
			out.println(result);
		}
		
		out.close();
	}
	
	public static long food(long A, long B, int C) {
		if(C == 0)
			return 0;
		
		long AA = (A / 9) * 9;
		long BB = (1 + B/9) * 9;
		
		
		long count = BB/9 - AA/9;
		if(C == 9)
		{
			C %= 9;
			count++;
		}
		for(long i = AA; i < A; i++)
			if(i % 9 == C)
				count--;
		
		for(long i = B + 1; i <= BB; i++)
			if(i % 9 == C)
				count--;
		
		return count;
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
4
1 10 1
40 57 1
1 10000000000 0
1 19 3

2
2
0
2

6
7 7 7
1 10 9
2 5 6
2 5 5
2 5 3
2 5 2

1
1
0
1
1
1

*/