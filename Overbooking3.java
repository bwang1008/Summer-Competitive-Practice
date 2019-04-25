import java.util.*;
import java.io.*;
public class Overbooking3 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		int N = Integer.parseInt(in.readLine());
		int M = Integer.parseInt(in.readLine());
		
		int max = 0;
		
		int[][] list = new int[N][2];
		for(int i = 0; i < N; i++)
		{
			StringTokenizer st = new StringTokenizer(in.readLine());
			list[i][0] = Integer.parseInt(st.nextToken());
			list[i][1] = Integer.parseInt(st.nextToken());
			
			max = Math.max(list[i][0], Math.max(list[i][1], max));
		}
		
		int[] arr = new int[max+2];
		
		for(int i = 0; i < N; i++)
		{
			arr[list[i][0]]++;
			arr[list[i][1]+1]--;
		}
		
		int[] ans = new int[arr.length];
		
		for(int i = 1; i < ans.length; i++)
		{
			ans[i] = ans[i-1]+arr[i];
		}
		
//		System.out.println(Arrays.toString(arr));
//		System.out.println(Arrays.toString(ans));
		
		for(int i = 0; i < M; i++)
		{
			int D = Integer.parseInt(in.readLine());
			if(D < ans.length)
				out.println(ans[D]);
			else
				out.println(0);
		}
		
		in.close();
		out.flush();
		out.close();
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