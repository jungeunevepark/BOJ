import java.util.*;
import java.io.*;

public class Main {
	static class Node{
		int to;
		Node link;
		public Node(int to, Node link) {
			super();
			this.to = to;
			this.link = link;
		}
	}
	static int[] v;
	static boolean[] visited;
	static Node[] n;
	static int N;
	public static void main(String[] args) throws Exception {
	   BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	   StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
	   N = Integer.parseInt(st.nextToken());
	   int M = Integer.parseInt(st.nextToken());
	   v = new int[N+1];
	   visited = new boolean[N+1];
	   n = new Node[N+1];
	   for(int i=0; i<M; i++) {
		   st = new StringTokenizer(bf.readLine(), " ");
		   int from = Integer.parseInt(st.nextToken());
		   int to = Integer.parseInt(st.nextToken());
		   n[from] = new Node(to, n[from]);
		   v[to] ++;
	   }
	   ArrayDeque<Integer> q = new ArrayDeque<>();
	   for(int i=1; i<=N; i++) {
		   if(v[i] == 0) {
			   q.offer(i); visited[i] = true; break;}
	   }
	   while(!q.isEmpty()) {
		   int from = q.poll();
		   System.out.print(from+" ");
		   for(Node r = n[from]; r != null; r = r.link) {
			   v[r.to] --;
		   }
		   for(int i=1; i<=N; i++)
			   if(v[i] == 0 && !visited[i])
				   {q.offer(i); visited[i] = true;}
		   
	   }
	}
}