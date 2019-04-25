import java.util.*;
public class Challenge {

	/* Given an array of N integers, answer Q queries where each query asks for the number of integers 
	 * in the range L <= i <= R (L, R 1-indexed; i.e. 1 <= L <= R <= N) that is less than or equal to X.
	 */
	
	public static int[][] tree;
	public static int[] starts;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		int[] arr = new int[N];
		int[] orig = new int[N];
		
		for(int i = 0; i < N; i++)
			arr[i] = orig[i] = in.nextInt();
		
		int count = 1;
		int temp = N;
		
		while(temp > 1)  //Obtain the modified log2 of N for the length of tree
		{
			temp = (int) Math.ceil(temp / 2.0);
			count++;
		}
		
		tree = new int[count][N];
		starts = new int[count]; //To fill up tree based on tree[row][starts[row]++] = value

		mergeSort(arr, 0); //Obtain Merge Sort Tree
		
		for(int i = 0; i < tree[0].length; i++)
			tree[count-1][i] = orig[i];  //Fill last row with the original array
		
		int Q = in.nextInt();
		for(int t = 0; t < Q; t++)
		{
			int L = in.nextInt()-1; //Turn to 0-indexing
			int R = in.nextInt()-1;
			int X = in.nextInt();
			
			int result = query(0, 0, N-1, L, R, X); //Find query
			System.out.println(result);
		}
		
		in.close();
	}
	
	public static int query(int row, int index1, int index2, int low, int high, int k) {
		//Row = row of tree, index1 through index2 = range of current row (NOT LIKE SUBSTRING; only inclusive);
		// index1 through index2 sorted; L = low, R = high (queries), k = X
		
		if(high < index1 || low > index2) return 0; //Useless to find (left = 2, right = 8) if index1/2 = [10-16]
		if(high > index2) high = index2;  //Turn (2, 8) if index1/2 is [3, 6] to (2, 6)
		if(low < index1) low = index1;    //turn (2, 6) to (3, 6)
		
		if(index1 == low && index2 == high)  //index1-index2 sorted; correct range, don't need to recurse down more
		{
			if(k < tree[row][low]) return 0;  //If [20..., 30,...40] and you are looking for  k == 1 -> ans = 0.
			if(k >= tree[row][high]) return high-low+1; //If k == 100, return the number of elements
			
			int index = binarySearch(tree[row], low, high, k); //Else it's somewhere in between;
															   //binary search for index of highest number <= k
			return index-low+1;
		}
		
		int ans1 = query(row+1, index1, (index1+index2-1)/2, low, high, k);  //Else break up to lower chunk and upper chunk in next row
		int ans2 = query(row+1, (index1+index2-1)/2 + 1, index2, low, high, k);
		
		return ans1+ans2;
	}
	
	public static int binarySearch(int[] arr, int low, int high, int search) {
		while(low <= high)
		{
			int mid = (low+high)/2;
			if(arr[mid] < search) low = mid+1;
			else if(arr[mid] > search) high = mid-1;
			else return mid;
		}
		
		return low-1;  //If not found, then find the lower index:
					   //arr: [2, 4], finding 3: low = 0, high = 1 -> mid == 0; -> low = high = 1;
					   //Return low-1 == 0 (index 0) as arr[0] <(=) 3
	}
	
	public static int[] mergeSort(int[] arr, int level) {
		if(arr.length == 1) //Then already sorted
		{
			tree[level][starts[level]++] = arr[0]; //Fill up tree on the correct level/row
			return arr;
		}
		
		int[] arr1 = new int[arr.length/2];
		int[] arr2 = new int[arr.length-arr.length/2];
		
		for(int i = 0; i < arr1.length; i++)
			arr1[i] = arr[i];
		
		for(int i = 0; i < arr2.length; i++)
			arr2[i] = arr[i+arr1.length];
		
		arr1 = mergeSort(arr1, level+1); //Sort both halves
		arr2 = mergeSort(arr2, level+1);
		
		int i = 0;
		int j = 0;
		int index = 0;
		
		while(i != arr1.length || j != arr2.length)  //Merge both arrays
		{
			if(i == arr1.length)
				arr[index] = arr2[j++];
			else if(j == arr2.length)
				arr[index] = arr1[i++];
			else if(arr1[i] <= arr2[j])  //Choose from first array if less than OR equal in both arrays
				arr[index] = arr1[i++];
			else
				arr[index] = arr2[j++];
			
			
			tree[level][starts[level]++] = arr[index]; //fill up tree
			index++;
		}
		
		return arr;
	}

}
/*
10
2 3 3 9 10 2 3 4 19 2
5
2 4 4
2 8 9
1 3 2
1 5 9
5 9 20

2  [3, 3, 9] : only 2 elements <= 4
6  [3, 3, 9, 10, 2, 3, 4] : only 6 elements <= 9
1  [2, 3, 3] : only 1 element <= 2
4  [2, 3, 3, 9, 10] : 4 elements <= 9
5  [10, 2, 3, 4, 19] : 5 elements <= 20

5
2 4 1 3 5
16
1 2 2
1 2 3
1 2 4
1 5 4
1 5 5
1 4 1
1 4 0
1 4 3
1 4 4
2 2 3
2 2 4
2 2 5
2 4 5
3 5 1
3 5 3
3 5 5

1
1
2
4
5
1
0
3
4
0
1
1
3
1
2
3

*/