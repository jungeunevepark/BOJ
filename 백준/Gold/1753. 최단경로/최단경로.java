import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		int to;
		int weak;
		Node link;
		public Node(int to, int weak, Node link) {
			super();
			this.to = to;
			this.weak = weak;
			this.link = link;
		}
	}
	static Node[] v;
	static long[] W;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		PriorityQueue<long[]> q = new PriorityQueue<>((o1, o2) -> Long.compare(o1[1], o2[1]));
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(bf.readLine());
		W = new long[V+1];
		for(int i=0; i<=V; i++)
			W[i] = Integer.MAX_VALUE;
		
		v = new Node[V+1];
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			int u =Integer.parseInt(st.nextToken());
			int to =Integer.parseInt(st.nextToken());
			int w =Integer.parseInt(st.nextToken());
			
			v[u] = new Node(to, w, v[u]);
			
		}
		
		W[start] = 0;
		q.offer(new long[] {start, 0});
		while(!q.isEmpty()) {
			long[] now = q.poll();
			for(Node n = v[(int)now[0]]; n!= null; n = n.link) {
				long dist = n.weak + now[1];
				if(W[n.to] > dist) {
					W[n.to] = dist;
					q.offer(new long[] {n.to, dist});
				}
			}
			
		}
		for(int i=1; i<=V; i++)
			System.out.println(W[i] == Integer.MAX_VALUE ? "INF" : W[i]);
	}
}