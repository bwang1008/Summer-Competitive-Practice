import java.util.*;
public class MinimumGroups {
	
	public static boolean[] composite;
	public static int[] primes;
	
	public static int[] arr;
	
	public static long start;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		
		HashSet<Integer> set = new HashSet<>();
		for(int i = 0; i < N; i++)
			set.add(in.nextInt());
		
		int index = 0;
		arr = new int[set.size()];
		for(int i : set)
			arr[index++] = i;
		
		start = System.currentTimeMillis();
		
		Arrays.sort(arr);
		
/*		int[] a = new int[N];
		for(int i = 0; i < a.length; i++)
			a[i] = arr[arr.length-i-1];
	*/	
		composite = new boolean[100001];
		
		for(int i = 2; i < 350; i++)
		{
			if(composite[i]) continue;
			for(int j = i*i; j < composite.length; j += i)
				composite[j] = true;
		}
		
		int count = 0;
		for(int i = 2; i < composite.length; i++)
			if(!composite[i])
				count++;
		
		primes = new int[count];
		count = 0;
		
		for(int i = 2; i < composite.length; i++)
			if(!composite[i])
				primes[count++] = i;
		
		int groups = getGroups(arr);
		
		System.out.println(groups);
		System.out.println(System.currentTimeMillis() - start + " milliseconds");
		
		in.close();
	}
	
	public static int getGroups(int[] a) {
		if(allPrime(a))
			return 1;
		
		boolean[] marked = new boolean[a.length];
		ArrayList<Integer> list = new ArrayList<>();
		
		list.add(a[0]);
		marked[0] = true;
		
		
		
		
		return 0;
	}
	
	public static boolean allPrime(int[] a) {
		for(int i = 0; i < a.length; i++)
			if(composite[i])
				return false;
		
		return true;
	}
	
	public static int[] factor(int num) {
		if(!composite[num])
			return new int[] {num};
		
		ArrayList<Integer> list = new ArrayList<>();
		
		for(int i = 0; i < primes.length; i++)
		{
			if(num % primes[i] == 0)
			{
				list.add(primes[i]);
				while(num % primes[i] == 0)
					num /= primes[i];
			}
		}
		
		int[] result = new int[list.size()];
		for(int i = 0; i < result.length; i++)
			result[i] = list.get(i);
		
		return result;
	}

}
/*
5
10 2 5 6 7

2
*/
