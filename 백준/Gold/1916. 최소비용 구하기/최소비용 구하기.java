import java.util.*;
import java.io.*;
public class Main {
	static int N;
	static int[] array;
	static long[] W;
	static Node[] v;
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
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<long[]> q = new PriorityQueue<>((o1, o2) -> Long.compare(o1[1], o2[1]));
		N = Integer.parseInt(bf.readLine());
		int M = Integer.parseInt(bf.readLine());
		W = new long[N+1];
		v = new Node[N+1];
		for(int i=1; i<=N; i++) {
			W[i] = Long.MAX_VALUE;
		}
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weak = Integer.parseInt(st.nextToken());
			v[from] = new Node(to, weak, v[from]);
		}
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
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
		System.out.println(W[end]);
		
	}
}