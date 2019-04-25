import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.TreeSet;

public class CaptainBlackpuff {
	
	public static void main(String[] args) {
		FastScanner in = new FastScanner(System.in);
		int N = in.nextInt();
		
		@SuppressWarnings("unchecked")
		LinkedList<Integer>[] edges = new LinkedList[N];
		for(int i = 0; i < edges.length; i++)
			edges[i] = new LinkedList<>();
		
		TreeSet<Integer> xs = new TreeSet<>();
		TreeSet<Integer> ys = new TreeSet<>();
		
		for(int i = 0; i < N; i++) {
			int u = in.nextInt();
			int v = in.nextInt();
			edges[u].add(v);
			xs.add(u);
			ys.add(v);
		}
		
		HashMap<Integer, Integer> xmap = new HashMap<>();
		HashMap<Integer, Integer> ymap = new HashMap<>();
		
		int index = 0;
		for(int i : xs)
			xmap.put(index++, i);
		index = 0;
		for(int i : ys)
			ymap.put(index++, i);
		
		
		
		
	}
	
	public static int maxFlow(int start, int stop, LinkedList[] edges, HashMap<Integer, Integer> xmap,
			HashMap<Integer, Integer> ymap) {
		
		return 0;
	}
	
	/**
	 * Source: Matt Fontaine
	 */
	static class FastScanner {
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int chars;

		public FastScanner(InputStream stream) {
			this.stream = stream;
		}

		int read() {
			if (chars == -1)
				throw new InputMismatchException();
			if (curChar >= chars) {
				curChar = 0;
				try {
					chars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (chars <= 0)
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