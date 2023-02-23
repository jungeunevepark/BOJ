import java.io.*;
import java.util.*;

public class Main {
	static int N, M, V;
	static boolean[] v;
	static List<Integer>[] graph;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		graph = new List[N+1];
		for(int i=1; i<=N; i++)
			graph[i] = new ArrayList<>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			graph[x].add(y);
			graph[y].add(x);
		}
		
		for(int i=1; i<=N; i++)
			Collections.sort(graph[i]);
		v = new boolean[N+1];
		dfs(V);
		System.out.println();
		v = new boolean[N+1];
		bfs(V);
	}
	static void bfs(int x) {
		System.out.print(x+" ");
		ArrayDeque<Integer> q = new ArrayDeque<>();
		v[x] = true;
		q.offer(x);
		while(!q.isEmpty()) {
			int i = q.poll();
			for(int j= 0; j<graph[i].size(); j++) {
				if(v[graph[i].get(j)]) continue;
				v[graph[i].get(j)] = true;
				System.out.print(graph[i].get(j)+" ");
				q.offer(graph[i].get(j));
			}
		}
		
	}
	static void dfs(int i) {
		System.out.print(i+" ");
		v[i] = true;
		for(int j=0; j<graph[i].size(); j++) {
			if(v[graph[i].get(j)]) continue;
			dfs(graph[i].get(j));
		}
	}
}