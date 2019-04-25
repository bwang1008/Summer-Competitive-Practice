import java.util.*;
import java.math.BigInteger;
public class DacheysStudy {
	
	public static long[] primes;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		boolean[] list = new boolean[110];
		for(int i = 2; i < 12; i++)
		{
			if(list[i]) continue;
			for(int j = i*i; j < list.length; j += i)
				list[j] = true;
		}
		
		primes = new long[29];
		int index = 0;
		for(int i = 2; i < list.length; i++)
			if(!list[i])
				primes[index++] = i;
		
//		System.out.println(Arrays.toString(primes));
		
		int N = in.nextInt();
		
		for(int i = 0; i < N; i++)
		{
			BigInteger result = answer(in.nextBigInteger());
			System.out.println(result);
		}
		
		in.close();
	}
	
	public static BigInteger answer(BigInteger b) {
		if(b.equals(BigInteger.ONE))
			return BigInteger.ONE;
		
		BigInteger prod = BigInteger.valueOf(2);
		BigInteger prev = BigInteger.valueOf(2);
		
		int index = 1;
		while(true)
		{
			if(prod.compareTo(b) == 1)
				return prev;
			
			prev = prod;
			prod = prod.multiply(BigInteger.valueOf(primes[index++]));
		}
	}

}
/*
3
8
2
43

6
2
30
*/