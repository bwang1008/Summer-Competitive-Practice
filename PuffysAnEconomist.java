import java.util.*;
public class PuffysAnEconomist {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long P = in.nextLong();
		long N = in.nextLong();
		long M = in.nextLong();
		
		long value = N*M-P;
		if(value >= 0)
			System.out.println(value);
		else
			System.out.println(-1);
		
		in.close();
	}
	
}
/*
100 10 20

100
*/
