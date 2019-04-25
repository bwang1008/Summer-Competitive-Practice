import java.util.*;
public class JettVSJeff3 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		
		int[] primes = genPrimes();
		int[] count = new int[primes.length];
		
		for(int i = 0; i < primes.length; i++)
		{
			if(N == 1) break;
			while(N % primes[i] == 0)
			{
				N /= primes[i];
				count[i]++;
			}
		}
		
		long ans = 1;
		if(N != 1)
			ans = 2;
		
		for(int i = 0; i < count.length; i++)
			ans *= (count[i] + 1);
		
		System.out.println(ans);
		
		in.close();
	}
	
	public static int[] genPrimes() {
		boolean[] comp = new boolean[31700];
		for(int i = 2; i < 179; i++)
		{
			if(comp[i]) continue;
			for(int j = i*i; j < comp.length; j += i)
				comp[j] = true;
		}
		int count = 0;
		for(int i = 2; i < comp.length; i++)
			if(!comp[i])
				count++;
		
		int[] primes = new int[count];
		int index = 0;
		for(int i = 2; i < comp.length; i++)
			if(!comp[i])
				primes[index++] = i;
		
		return primes;
	}		

}
/*
6

4

4

3
*/