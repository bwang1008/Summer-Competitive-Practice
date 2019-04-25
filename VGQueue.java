import java.util.*;
public class VGQueue {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int[] arr = new int[N];
		for(int i = 0; i < N; i++)
			arr[i] = in.nextInt();
		
		long ans = 0;
		while(true)
		{
			for(int i = 0; i < N; i++)
			{
				arr[i] = Math.max(arr[i]-i,	0);
				if(arr[i] == 0)
				{
					ans = i + 1;
					break;
				}
			}
			if(ans != 0)
				break;
			for(int i = 0; i < N; i++)
				arr[i] = Math.max(arr[i] - N + i, 0);
		}
		
		System.out.println(ans);
		in.close();
	}

}
/*
6
2
3
4
3
5
1

4
*/