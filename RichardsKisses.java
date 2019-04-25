import java.util.*;
public class RichardsKisses {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		long x = in.nextInt();
		long y = in.nextInt();
		String XO = in.next();
		System.out.println(result(x, y, XO));
		
		in.close();
	}
	
	public static long result(long x, long y, String XO) {
		char[] c = XO.toCharArray();
		StringBuilder sb = new StringBuilder();
		sb.append(c[0]);
		for(int i = 1; i < c.length; i++)
		{
			while(i < c.length && c[i] == c[i-1]) i++;
			if(i == c.length) break;
			sb.append(c[i]);
		}
		
		XO = sb.toString();
		
		long numX = 0;
		for(int i = 0; i < XO.length(); i++)
			if(XO.charAt(i) == 'X')
				numX++;
		
		if(numX == 0) return 0;
		return y + (numX-1) * Math.min(x, y);
		
	}
}


/*
1 10
XOXXX

11

10 1
XOXXX

2

2 3
OOOOOOO

0
*/