import java.io.PrintWriter;
import java.util.*;
public class Acdec {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		String g = in.nextLine();
		
		int Q = in.nextInt();
		String[] queries = new String[Q];
		for(int i = 0; i < Q; i++)
			queries[i] = in.next();
		
		HashMap<String, Integer> map = new HashMap<>();
		for(String h : queries)
			map.put(h, 0);
		
		Comparator<String> com = (o1, o2) -> (Integer.compare(o1.length(), o2.length()));
		PriorityQueue<String> pq = new PriorityQueue<>(Q, com);
		
		for(int i = 0; i < Q; i++)
			pq.add(queries[i]);
		
		while(!pq.isEmpty())
		{
			HashSet<String> set = new HashSet<>();
			int num = pq.peek().length();
			while(pq.peek() != null && pq.peek().length() == num)
				set.add(pq.poll());
			
			for(int i = 0; i <= g.length()-num; i++)
			{
				String h = g.substring(i, i+num);
				if(set.contains(h))
					map.put(h, map.get(h)+1);
			}
		}
		
		for(String h : queries)
			out.println(map.get(h));
		
		in.close();
		out.close();
	}

}
/*
hieveryonehowisthecontest
3
on
e
he

2
5
1
*/