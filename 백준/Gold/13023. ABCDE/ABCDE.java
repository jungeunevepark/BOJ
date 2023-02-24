import java.io.*;
import java.util.*;

public class Main {
	static int M, N;
	static boolean[] v;
	static Node[] friends;
	static int result;
	
	static class Node{
		int vertex;
		Node link;
		public Node(int vertex, Node link) {
			super();
			this.vertex = vertex;
			this.link = link;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		v = new boolean[N];
		friends = new Node[N];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			friends[x] = new Node(y, friends[x]);
			friends[y] = new Node(x, friends[y]);
		}
		for(int i=0; i<N; i++) {
			if(result == 1) break;
			dfs(i, 0);
		}
		System.out.println(result);
	}
	static void dfs(int x, int cnt) {
		if(result == 1) return;
		if(cnt == 5) {
			result = 1;
			return;
		}
		for(Node n = friends[x]; n != null; n = n.link) {
			if(v[n.vertex]) continue;
			v[n.vertex] = true;
			dfs(n.vertex, cnt+1);
			v[n.vertex] = false;
		}
	}
}