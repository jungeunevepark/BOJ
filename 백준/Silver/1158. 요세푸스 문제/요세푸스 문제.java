import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayDeque<Integer> q = new ArrayDeque<>();
		for(int i=1; i<=N; i++)
			q.offer(i);
		int count = 1;
		sb.append("<");
		while(!q.isEmpty()) {
			count = count % M;
			int size = q.size();
			if(size == 1) sb.append(q.poll()).append(">");
			else {
				if(count == 0) sb.append(q.poll()).append(", ");
				else q.offer(q.poll());
			}
			count++;
		}
		System.out.println(sb);
	}
}