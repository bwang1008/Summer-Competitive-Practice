import java.util.*;
import java.io.*;
import java.math.BigInteger;
public class DacheyList {
	
	public static int p = 1_000_000_007;
	public static HashMap<Integer, Long> map = new HashMap<>();
	public static Set<Integer> isPrime = new HashSet<>();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int Q = in.nextInt();
		int[] q = new int[Q];
		for(int i = 0; i < Q; i++)
			q[i] = in.nextInt();
		
		boolean[] comp = new boolean[1000001];
		for(int i = 2; i <= 1000; i++)
		{
			if(comp[i]) continue;
			for(int j = i*i; j < comp.length; j += i)
				comp[j] = true;
		}
		
		for(int i = 2; i < comp.length; i++)
			if(!comp[i])
				isPrime.add(i);
		
		map.put(1, 1L);
		map.put(2, 1L);
		
		for(int i = 0; i < q.length; i++)
		{
			out.println(get(q[i]));
		}
		
		in.close();
		out.close();
	}
	
	public static long get(int N) {
		if(map.containsKey(N))
			return map.get(N);
		
		long value = (BigInteger.valueOf(2).modPow(BigInteger.valueOf(N-1), BigInteger.valueOf(p)).longValue()+p-1) % p;
		if(isPrime.contains(N))
		{
			map.put(N, value);
			return value;
		}
		
		for(int i = 2; i <= Math.sqrt(N); i++)
		{
			if(N % i == 0)
			{
				value = (value + 2*p - get(i)) % p;
				if(i != N/i)
					value = (value + 2*p - get(N/i)) % p;
			}
		}
		
		map.put(N, value);
		return value;
	}

}
/*
2
1
4

1
6
*/