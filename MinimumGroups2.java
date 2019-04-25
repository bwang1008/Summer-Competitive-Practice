import java.util.*;
public class MinimumGroups2 {

	public static boolean[] composite;
	public static int[] primes;
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		HashSet<Integer> set = new HashSet<>();
		
		for(int i = 0; i < N; i++)
			set.add(in.nextInt());
		
		int index = 0;
		int[] ar = new int[set.size()];
		for(int i : set)
			ar[index++] = i;
		
		Arrays.sort(ar);
		
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
		
		LinkedList<Integer> list = new LinkedList<Integer>();
		for(int i : ar)
			list.add(i);
		
		long result = getGroups(list);
		System.out.println(result);
		
		in.close();
	}
	
	public static long getGroups(LinkedList<Integer> list) {
		if(allPrime(list))
			return list.size();
		
		if(list.size() == 0 || list.size() == 1)
			return list.size();
		
		int first = list.removeFirst();
		int[] temp = factor(first);
		
		HashSet<Integer> set = new HashSet<>();
		for(int i : temp)
			set.add(i);
		
		Queue<Integer> q = new LinkedList<>();
		for(int i : temp)
			q.add(i);
		
		while(!q.isEmpty())
		{
//			System.out.println("list = " + list);
//			System.out.println("q = " + q);
			int num = q.poll();
			
			for(int j = list.size()-1; j >= 0; j--)
			{
				int def = list.get(j);
				if(def % num == 0)
				{
					list.remove(j);
					int[] asd = factor(def);
					
					for(int k : asd)
					{
						if(!set.contains(k))
						{
							set.add(k);
							q.add(k);
						}
					}
				}
			}
		}
		
//		System.out.println("gotta + 1" );
		return 1 + getGroups(list);
		
	}
	
	public static boolean allPrime(LinkedList<Integer> list) {
		for(int i : list)
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

	public static int gcd2(int p, int q) {  //GCD
        while (q != 0) {
            int temp = q;
            q = p % q;
            p = temp;
        }
        return p;
    }

}
/*
5
10 2 5 6 7

2

6
14 10 2 5 6 7

1

6
19 18 17 16 15 14

3

21
22 21 20 19 18 17 16 15 14 13 12 11 10
9 8 7 6 5 4 3 2

4

20
21 20 19 18 17 16 15 14 13 12 11 10
9 8 7 6 5 4 3 2
*/
