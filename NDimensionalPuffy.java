import java.util.*;
import java.io.*;
public class NDimensionalPuffy {
	
	public static int[][] board;
	public static int[][] count;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int N = in.nextInt();
		int M = in.nextInt();
		int Q = in.nextInt();
		
		board = new int[N][M];
		count = new int[N][M];
		
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++)
				board[i][j] = j;
		
		for(int q = 0; q < Q; q++) {
			String g = in.next();
			
			if(g.equals("swap")) {
				int dimension = in.nextInt();
				int swapA = in.nextInt();
				int swapB = in.nextInt();
				
				int t = board[dimension][swapA];
				board[dimension][swapA] = board[dimension][swapB];
				board[dimension][swapB] = t;
				
				count[dimension][swapA]++;
				count[dimension][swapB]++;
			}
			else if(g.equals("get")) {
				int[] start = new int[N];
				for(int i = 0; i < N; i++)
					start[i] = in.nextInt();
				
				int[] result = new int[N];
				
				for(int i = 0; i < N; i++)
				{
					int j, c;
					for(j = start[i], c = 0; c < count[i][start[i]]; j = board[i][j], c++);
						result[i] = j;
				}
				
				out.println(Arrays.toString(result));
			}
		}
		in.close();
		out.close();
	}

}
