import java.util.*;
public class MinimumGroups3 {
	
	public static boolean[] composite;
	public static int[][] all;
	public static int[] starts;
	
	public static long start;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		Set<Integer> set = new HashSet<>();
		
		for(int i = 0; i < N; i++)
			set.add(in.nextInt());
		
		start = System.currentTimeMillis();
		
		int index = 0;
		int[] ar = new int[set.size()];
		for(int i : set)
			ar[index++] = i;
		
		Arrays.sort(ar);
		
		composite = new boolean[100001];
		all = new int[100001][6];
		starts = new int[100001];
		
		for(int i = 2; i < composite.length; i++)
		{
			if(composite[i]) continue;
			
			for(int j = i;j < composite.length; j += i)
			{
				composite[j] = true;
				all[j][starts[j]++] = i;
			}
			
		}
		
//		System.out.println(System.currentTimeMillis() - start + " milliseconds");
		
		N = ar.length;
		
		set = new TreeSet<>();
		for(int i = 0; i < ar.length; i++)
		{
			int num = ar[i];
			for(int j = 0; j < starts[num]; j++)
				set.add(all[num][j]);
		}
		
		int[] primes = new int[set.size()];
		index = 0;
		
		for(int i : set)
			primes[index++] = i;
		
//		System.out.println("primes " + Arrays.toString(primes));
		
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < primes.length; i++)
			map.put(primes[i], i);
		
		WeightedQuickUnionUF ufo = new WeightedQuickUnionUF(primes.length);
		
//		System.out.println("ar " + Arrays.toString(ar));
		
		for(int i = 0; i < ar.length; i++)
		{
			int num = ar[i];
			
			for(int j = 1; j < starts[num]; j++)
			{
//				System.out.println(all[num][0] + " " + all[num][j]);
				
				int p = map.get(all[num][0]);
				int q = map.get(all[num][j]);
				
//				System.out.println("pq: " + p + " " + q);
				if(!ufo.connected(p, q))
				{
					ufo.union(p, q);
				}
			}
		}
		
		System.out.println(ufo.count());
		
		in.close();
	}
	
	
	
	/*  @author Robert Sedgewick
	 *  @author Kevin Wayne
	 */
	public static class WeightedQuickUnionUF {
	    private int[] parent;   // parent[i] = parent of i
	    private int[] size;     // size[i] = number of sites in subtree rooted at i
	    private int count;      // number of components

	    /**
	     * Initializes an empty union–find data structure with {@code n} sites
	     * {@code 0} through {@code n-1}. Each site is initially in its own 
	     * component.
	     *
	     * @param  n the number of sites
	     * @throws IllegalArgumentException if {@code n < 0}
	     */
	    public WeightedQuickUnionUF(int n) {
	        count = n;
	        parent = new int[n];
	        size = new int[n];
	        for (int i = 0; i < n; i++) {
	            parent[i] = i;
	            size[i] = 1;
	        }
	    }

	    /**
	     * Returns the number of components.
	     *
	     * @return the number of components (between {@code 1} and {@code n})
	     */
	    public int count() {
	        return count;
	    }
	  
	    /**
	     * Returns the component identifier for the component containing site {@code p}.
	     *
	     * @param  p the integer representing one object
	     * @return the component identifier for the component containing site {@code p}
	     * @throws IllegalArgumentException unless {@code 0 <= p < n}
	     */
	    public int find(int p) {
	        validate(p);
	        while (p != parent[p])
	            p = parent[p];
	        return p;
	    }

	    // validate that p is a valid index
	    private void validate(int p) {
	        int n = parent.length;
	        if (p < 0 || p >= n) {
	            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));  
	        }
	    }

	    /**
	     * Returns true if the the two sites are in the same component.
	     *
	     * @param  p the integer representing one site
	     * @param  q the integer representing the other site
	     * @return {@code true} if the two sites {@code p} and {@code q} are in the same component;
	     *         {@code false} otherwise
	     * @throws IllegalArgumentException unless
	     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
	     */
	    public boolean connected(int p, int q) {
	        return find(p) == find(q);
	    }

	    /**
	     * Merges the component containing site {@code p} with the 
	     * the component containing site {@code q}.
	     *
	     * @param  p the integer representing one site
	     * @param  q the integer representing the other site
	     * @throws IllegalArgumentException unless
	     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
	     */
	    public void union(int p, int q) {
	        int rootP = find(p);
	        int rootQ = find(q);
	        if (rootP == rootQ) return;

	        // make smaller root point to larger one
	        if (size[rootP] < size[rootQ]) {
	            parent[rootP] = rootQ;
	            size[rootQ] += size[rootP];
	        }
	        else {
	            parent[rootQ] = rootP;
	            size[rootP] += size[rootQ];
	        }
	        count--;
	    }


	    /**
	     * Reads in a sequence of pairs of integers (between 0 and n-1) from standard input, 
	     * where each integer represents some object;
	     * if the sites are in different components, merge the two components
	     * and print the pair to standard output.
	     *
	     * @param args the command-line arguments
	     */
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

5
*/
