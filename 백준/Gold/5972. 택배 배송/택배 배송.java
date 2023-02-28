import java.util.*;
import java.io.*;
public class Main {
	static int N;
	static int[] array;
	static int[] W;
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
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		W = new int[N+1];
		v = new Node[N+1];
		for(int i=1; i<=N; i++) {
			W[i] = 987654321;
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weak = Integer.parseInt(st.nextToken());
			v[from] = new Node(to, weak, v[from]);
			v[to] = new Node(from, weak, v[to]);
		}
		W[1] = 0;
		q.offer(new int[] {1, 0});
		while(!q.isEmpty()) {
			int[] now = q.poll();
			for(Node n = v[now[0]]; n!= null; n = n.link) {
				int dist = n.weak + now[1];
				if(W[n.to] > dist) {
					W[n.to] = dist;
					q.offer(new int[] {n.to, dist});
				}
			}
			
		}
		System.out.println(W[N]);
		
	}
}