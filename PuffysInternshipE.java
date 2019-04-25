import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
public class PuffysInternshipE {
	
	public static ArrayList<Long> basis = new ArrayList<>();
	
	public static void main(String[] args) {
		FastScanner in = new FastScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int N = in.nextInt();
		for(int i = 0; i < N; i++)
			basis.add(in.nextLong());
		
		gaussianElimination(N);
		
		int Q = in.nextInt();
		for(int i = 0; i < Q; i++)
			out.println(ans(in.nextLong()));
		
		out.close();
	}
	
	public static long ans(long q) {
		q--;
		long value = 0;
		if(q >= (1L << basis.size())) return -1;
		String g = new StringBuilder().append(Long.toBinaryString(q)).reverse().toString();
		for(int i = 0; i < g.length(); i++)
			if(g.charAt(i) == '1')
				value ^= basis.get(i);
		
		return value;
	}
	
	public static void gaussianElimination(int N) {
		int index = 0;
		for(int i = 60; i >= 0; i--)
		{
			int j = index;
			while(j < N && (basis.get(j) >> i & 1) == 0) j++;
			if(j == N) continue;
			
			long t = basis.get(index);
			basis.set(index, basis.get(j));
			basis.set(j, t);
			
//			print(basis);
			
			for(++j; j < N; j++)
				if((basis.get(j) >> i & 1) == 1)
					basis.set(j, basis.get(j) ^ basis.get(index));
//			print(basis);
			index++;
		}
		
		for(int i = N-1; i >= 0; i--)
			if(basis.get(i) == 0)
				basis.remove(i);
		
		for(int i = 0; i < basis.size(); i++)
			for(int j = 0; j < basis.size(); j++)
				if(i != j && (basis.get(i) ^ basis.get(j)) < basis.get(i))
					basis.set(i, basis.get(i) ^ basis.get(j));
		
		Collections.sort(basis);
//		print(basis);
	}
	
	public static void print(ArrayList<Long> list) {
		for(long t : list)
			System.out.printf("%6s\n", Long.toBinaryString(t));
		System.out.println();
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
3
1 64 31
3
1
7
18

0
94
-1

5
3
8
17
25
2
17
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
*/