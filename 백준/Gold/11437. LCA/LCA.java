import java.io.*;
import java.util.*;

public class Main {
	static int[] p;
	static int[] depth;
	static boolean[] v;
	static class Node{
		int vertex;
		Node link;
		public Node(int vertex, Node link) {
			super();
			this.vertex = vertex;
			this.link = link;
		}
		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", link=" + link + "]";
		}
	}
	static Node[] tree;
	static void union(int a, int dept) {
		v[a] = true;
		depth[a] = dept;
		for(Node n = tree[a]; n != null; n = n.link) {
			if(v[n.vertex]) continue;
			p[n.vertex] = a;
			union(n.vertex, dept+1);
		}
	}
	static int find(int a, int b) {
		if(a == 1 || b == 1) return 1;
		int x = a; int y = b;
		while(depth[x] !=1 && depth[y] !=1) {
			if(depth[x] == depth[y]) {
				if(x==y) return x;
				else {
					x = p[x]; y = p[y];
				}
			}
			else if(depth[x] > depth[y])
				x = p[x];
			else y = p[y];
		}
		return 1;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb= new StringBuilder();
		int N = Integer.parseInt(bf.readLine());
		p = new int[N+1];
		depth = new int[N+1];
		tree = new Node[N+1];
		v = new boolean[N+1];
		for(int i=1; i<=N; i++){
			p[i] = i; depth[i] = i;
		}
		for(int i=1; i<N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			tree[a] = new Node(b, tree[a]);
			tree[b] = new Node(a, tree[b]);
		}
		union(1, 1);
		int M = Integer.parseInt(bf.readLine());
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			int t = find(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			sb.append(t).append("\n");
		}
		System.out.println(sb);
	}
}