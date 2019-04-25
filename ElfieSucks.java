import java.util.*;
public class ElfieSucks {
	
	public static int p = (int) 1E9 + 7;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		long[] arr = new long[N];
		for(int i = 0; i < N; i++)
			arr[i] = in.nextLong();
		
		System.out.println(result(arr, N));
		in.close();
	}
	
	public static long result(long[] arr, int N) {
		if(N == 1)
			return arr[0];
		long[] sum = new long[N];
		sum[0] = arr[0];
		for(int i = 1; i < N; i++)
			sum[i] = (sum[i-1] + arr[i]) % p;
		
		long[] coef = new long[N];
		long prod = 1L;
		coef[N-1] = 1L;
		coef[N-2] = 2L;
		for(int i = N-3; i >= 0; i--)
		{
			coef[i] = (2*coef[i+1] + prod) % p;
			prod = (2*prod) % p;
		}
		
		long ans = 0L;
		for(int i = 0; i < N; i++)
		{
			ans += ((sum[i] * coef[i]) % p);
			ans %= p;
		}
		
		return ans;
	}

}
/*
2
1 2

5

4
1 3 3 7

60
*/