import java.util.*;
import java.io.*;
public class JettVSFJeffF {
	
	public static final int len = 50001;
	public static long[] sum;
	public static int[] primes;
	public static int[] tot;
	public static long[] S;
	public static HashSet<Integer> isPrime = new HashSet<Integer>();
	
	public static long start;
	
	public static void main(String[] args) {
		FastScanner in = new FastScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		initialize();
		
		int T = in.nextInt();
		for(int t = 0; t < T; t++)
			out.println(sum[in.nextInt()]);
		
		out.close();
	}
	
	public static void initialize() {
		long[] arr = new long[len];
		sum = new long[len];
		tot = new int[len];
		S = new long[len];
		
		boolean[] comp = new boolean[len];
		for(int i = 2; i < 225; i++)
			for(int j = i*i; j < comp.length; j += i)
				comp[j] = true;
		
		int count = 0;
		for(int i = 2; i < comp.length; i++)
			if(!comp[i])
				count++;
		
		primes = new int[count];
		count = 0;
		for(int i = 2; i < comp.length; i++)
		{
			if(!comp[i])
			{
				primes[count++] = i;
				isPrime.add(i);
			}
		}
		
		tot[1] = 1;
		S[1] = 1;
		sum[1] = 1;
		
		for(int i = 2; i < len; i++)
			totient(i);
		
		for(int i = 2; i < len; i++)
			S(i);
		
		for(int i = 1; i < len; i++)
			arr[i] = i*S[i];
		
		for(int i = 2; i < len; i++)
			sum[i] = sum[i-1] + arr[i];
	}
	
	public static long S(int n) {
		long N = (long) n;
		
		if(S[n] != 0) return S[n];
		if(isPrime.contains(n)) return S[n] = N*(N-1)+1;
		
		long ans = 0;
		int index = 0;
		while(N % primes[index] != 0) index++;
		
		long a = 1;
		int aa = 1;
		int ab = 1;
		while(N % primes[index] == 0)
		{
			N /= primes[index];
			aa *= primes[index];
			ab *= (aa == primes[index]) ? primes[index]-1 : primes[index];
			a += aa*ab;
		}
		
		ans = a * S((int) N);
		return S[n] = ans;
	}
	
	public static int totient(int N) {
		if(tot[N] != 0) return tot[N];
		if(isPrime.contains(N)) return tot[N] = N-1;
		
		int M = N;
		long ans = 0;
		int index = 0;
		
		while(N % primes[index] != 0) index++;
		
		int a = 1;
		while(N % primes[index] == 0)
		{
			N /= primes[index];
			a *= primes[index];
		}
		
		ans = (N == 1) ? (primes[index]-1) * (a/primes[index]) : totient(a) * totient(N);
		return tot[M] = (int) ans;
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
5
3
1
2
10
100

28
1
7
2127
18446224
*/