import java.util.*;
import java.math.BigInteger;
public class IdealSituationsA {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		
		long[] a = new long[N];
		for(int i = 0; i < N; i++)
			a[i] = in.nextLong();
		
		BigInteger[] arr = new BigInteger[N];
		for(int i = 0; i < N; i++)
			arr[i] = BigInteger.valueOf(a[i]);
		
		BigInteger ans = arr[0];
		for(int i = 1; i < arr.length; i++)
		{
			ans = ans.gcd(arr[i]);
		}
		
		long max = a[0];
		for(int i = 0; i < N; i++)
			max = Math.max(a[i], max);
		
		long gcd = ans.longValue();
		
		System.out.println(max/gcd + 1);
		in.close();
	}

}
/*
3
2 6 14

8

2
3 6

3
*/