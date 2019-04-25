import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.io.*;
public class Overbooking2 {
	
	public static int[] temp;
	public static Map<Integer, Integer> map;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int N = Integer.parseInt(in.readLine());
		int M = Integer.parseInt(in.readLine());
		
		int[][] chag = new int[N][2];
		Set<Integer> set = new TreeSet<Integer>();
		
		for(int i = 0; i < N; i++)
		{
			StringTokenizer st = new StringTokenizer(in.readLine());
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken())+1;
			chag[i][0] = L;
			chag[i][1] = R;
			
			set.add(L);
			set.add(R);
		}
		
		map = new HashMap<>();
		
		
		int index = 1;
		temp = new int[set.size()];
		for(int i : set)
		{
			map.put(i, index);
			temp[index-1] = i;
			index++;
		}
		
//		System.out.println(Arrays.toString(temp));
		
		int[] arr = new int[map.size()+1];
		
		for(int i = 0; i < N; i++)
		{
			int L = chag[i][0];
			int R = chag[i][1];
			
			int L2 = map.get(L);
			int R2 = map.get(R);
			
//			System.out.println(L + ", " + R);
//			System.out.println(L2 + ", " + R2);
//			System.out.println();
			
			for(int j = L2; j < R2; j++)
			{
				arr[j]++;
			}
		}
		
		for(int i = 0; i < M; i++)
		{
			int D = Integer.parseInt(in.readLine());
			int num = binary(D);
//			System.out.println("num = " + num);
			out.println(arr[num+1]);
		}
		
		in.close();
		out.close();

	}
	
	public static int binary(int k) {
		
		int low = 0;
		int high = temp.length-1;
		
		while(low <= high)
		{
			int mid = (low + high) / 2;
			
			if(temp[mid] < k)
				low = mid+1;
			else if(temp[mid] > k)
				high = mid-1;
			else
				return mid;
		}
		
		return low-1;
		
	}

}
/*
3
4
1 3
5 7
2 5
2
5
4
9

2
2
1
0

5
5
1 10
2 11
3 12
4 13
10 100
1
5
10
13
101

1
4
5
2
0

5
5
1 10
2 11
3 12
4 13
10 100
4
5
10
11
100

4
4
5
4
1
*/
