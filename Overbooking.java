import java.util.*;
import java.io.*;
public class Overbooking {
	
	public static int[] arr;
	public static long start;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		int N = Integer.parseInt(in.readLine());
		int M = Integer.parseInt(in.readLine());
		
		int[][] chag = new int[N][2];
		
		int max = 0;
		
		start = System.currentTimeMillis();
		
		for(int i = 0; i < N; i++)
		{
			StringTokenizer st = new StringTokenizer(in.readLine());
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			chag[i][0] = L;
			chag[i][1] = R;
			
			max = Math.max(L, Math.max(R, max));
		}
		
		arr = new int[max+1];
		Tree tree = new Tree(max+1);
		
//		System.out.println(System.currentTimeMillis() - start + " milliseconds");
//		System.out.println("KHEFL");
		
		for(int i = 0; i < N; i++)
		{
			int L = chag[i][0];
			int R = chag[i][1];
			tree.change(L, R, 1, tree.root);
//			System.out.println("DONE");
		}
		
		for(int i = 0; i < M; i++)
		{
			int D = Integer.parseInt(in.readLine());
//			start = System.currentTimeMillis();
			long result = tree.sum(D, D, tree.root);
			out.println(result);
//			System.out.println(System.currentTimeMillis() - start + " millseconds");
		}
		
//		System.out.println(System.currentTimeMillis() - start + " milliseconds");

		in.close();
		out.close();
	}
	
	public static class Tree {
		
		Node root;
		
		public Tree(int N) {
			root = new Node(0, N-1);
		}
		
		public void change(int a, int b, int k, Node node) {
			if(node.index1 > b || node.index2 < a) return;
			if(node.index2 < b) b = node.index2;
			if(node.index1 > a) a = node.index1;
			
//			System.out.println("going to node " + node.index1 + ", " + node.index2 + "a = " + a + " b = " + b);
			
//			node.change += (b-a+1) * k;
		
			
			if(a == node.index1 && b == node.index2)
			{
//				System.out.println("changing here to += " + k);
				node.value += k;
			}
			else
			{
				change(a, b, k, node.left);
				change(a, b, k, node.right);
			}
		}
		
		public long sum(int a, int b, Node node) {
			if(node.index1 > b || node.index2 < a) return 0;
			if(node.index2 < b) b = node.index2;
			if(node.index1 > a) a = node.index1;
			
//			System.out.println("SUM going to node " + node.index1 + ", " + node.index2 + "a = " + a + " b = " + b);
			
			
			if(a == node.index1 && b == node.index2)
			{
//				System.out.println("Returning " + node.value);
				return node.value;
			}
			
			return sum(a, b, node.left) + sum(a, b, node.right) + node.value;
		}
		
	}
	
	public static class Node {
		long value;
		int index1;
		int index2;
		Node left;
		Node right;
		
		public Node(int x, int y) {
			index1 = x;
			index2 = y;
			if(x == y)
			{
				value = arr[x];
			}
			else
			{
				left = new Node(x, (x+y-1)/2);
				right = new Node((x+y+1)/2, y);
				value = left.value + right.value;
			}
		}
		
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
