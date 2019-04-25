import java.util.*;
import java.io.*;
public class GarrettCoolDiary {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int N = in.nextInt();
		int[] arr = new int[N];
		for(int i = 0; i < N; i++)
			arr[i] = in.nextInt();
		
		int min = arr[0];
		
		LinkedList<Integer> list = new LinkedList<>();
		list.add(min);
		for(int i = 1; i < arr.length; i++)
		{
			list.add(min);
			list.add(arr[i]);
		}
		
		out.println(list.size());
		StringBuilder sb = new StringBuilder();
		for(int i : list)
			sb.append(i + " ");
		
		out.println(sb.toString().trim());
		
		in.close();
		out.close();
	}
	
	public static int gcd(int a, int b) {
		while(b != 0)
		{
			int t = b;
			b = a % b;
			a = t;
		}
		return a;
	}

}
