import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
public class PuffyPizza {

	public static int p = 1_000_000_007;
	public static int[] mu;
	@SuppressWarnings("unchecked")
	public static ArrayList<Integer>[] factors = new ArrayList[100001];
	
	public static void main(String[] args) {
		FastScanner in = new FastScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		initialize();
		
		int T = in.nextInt();
		while(T --> 0) {
			int N = in.nextInt();
			int K = in.nextInt();
			System.out.println(ans(N, K));
		}
		
		out.close();
	}
	
	public static long ans(int N, int K) {
		long ans = 0;
		for(int div : factors[N]) 
			ans += g(K, div);
		
		return ans % p;
	}
	
	public static long g(int K, int N) {
		long sum = 0;
		for(int div : factors[N])
			sum += modPow(K, div) * mu[N/div];
		
		long inv = modInv(N);
		sum = inv * sum % p;
		if(sum < 0) sum += p;
		
		return sum;
	}
	
	public static long modInv(int k) {
		int[] a = {k, 1, 0};
		int[] b = {p, 0, 1};
		while(b[0] != 0) {
			int[] c = new int[3];
			int q = a[0] / b[0];
			c[0] = a[0] - q*b[0];
			c[1] = a[1] - q*b[1];
			c[2] = a[2] - q*b[2];
			a = b; b = c;
		}
		int ans = a[1];
		if(ans < 0) ans += p;
		return ans;
	}
	
	public static long modPow(int a, int b) {
		char[] bin = Integer.toBinaryString(b).toCharArray();
		long[] base = new long[bin.length];
		
		base[base.length - 1] = a;
		for(int i = base.length-2; i >= 0; i--)
			base[i] = (base[i+1] * base[i+1]) % p;
		
		long ans = 1;
		for(int i = 0; i < base.length; i++)
			if(bin[i] == '1') ans = base[i] * ans % p;
		
		return ans;
	}
	
	public static void initialize() {
		mu = new int[100001];
		boolean[] odd = new boolean[100001];
		boolean[] comp = new boolean[100001];
		ArrayList<Integer> list = new ArrayList<>();
		
		for(int i = 2; i < comp.length; i++) {
			if(comp[i]) continue;
			list.add(i);
			odd[i] = !odd[i];
			for(int j = 2*i; j < comp.length; j += i) {
				comp[j] = true;
				odd[j] = !odd[j];
			}
		}
		
		int index = 0;
		int[] primes = new int[list.size()];
		for(int p : list)
			primes[index++] = p;
		
		Arrays.fill(mu, Integer.MAX_VALUE);
		mu[1] = 1;
		for(int i = 2; i <= Math.sqrt(mu.length); i++)
			for(int j = i*i; j < mu.length; j += i*i)
				mu[j] = 0;
		
		for(int i = 2; i < mu.length; i++)
			if(mu[i] != 0)
				mu[i] = odd[i] ? -1 : 1;
		
		for(int i = 0; i < factors.length; i++) factors[i] = new ArrayList<>();
		for(int i = 1; i < factors.length; i++) 
			for(int j = i; j < factors.length; j += i)
				factors[j].add(i);
	}
	
	/**
	 * Source: Matt Fontaine
	 */
	public static class FastScanner {
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
/*
3
3 1
3 2
4 6

1
4
336
*/