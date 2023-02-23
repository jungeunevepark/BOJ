import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static boolean[] v;
	static boolean[] subs;
	static int[] population;
	static boolean[][] graph;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(bf.readLine());
		population = new int[N+1];
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		
		for(int i=1; i<=N; i++)
			population[i] = Integer.parseInt(st.nextToken());
		
		subs = new boolean[N+1];
		graph = new boolean[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			int M = Integer.parseInt(st.nextToken());
			for(int k=0; k<M; k++) {
				int j = Integer.parseInt(st.nextToken());
				graph[i][j] = true;
				graph[j][i] = true;
			}
		}
		subs[1] = true;
		subs(2);
		if(min == Integer.MAX_VALUE) min = -1;
		System.out.println(min);
	}
	static void dfs(int i) {
		v[i] = true;
		for(int j=1; j<=N; j++) {
			if(graph[i][j] && subs[j] && !v[j])
				dfs(j);
		}
	}
	static void dfs2(int i) {
		v[i] = true;
		for(int j=1; j<=N; j++) {
			if(graph[i][j] && !subs[j] && !v[j])
				dfs2(j);
		}
	}
	static void subs(int cnt) {
		if(cnt == N+1) {
			boolean flag = true;
			for(int i=2; i<=N; i++) {
				flag &= subs[i];
				
			}
			if(flag == true) return;
			
			v = new boolean[N+1];
			dfs(1);
			for(int i=1; i<=N; i++) {
				if(subs[i] && !v[i]) return;
			}
			v = new boolean[N+1];
			for(int i=1; i<=N; i++) {
				if(!subs[i]) {dfs2(i); break;}
			}
			for(int i=1; i<=N; i++) {
				if(!subs[i] && !v[i]) return;
			}
			int sum1 = 0; int sum2 = 0;
			for(int i=1; i<=N; i++) {
				if(subs[i]) sum1+=population[i];
				else sum2+=population[i];
			}
			min = Math.min(min, Math.abs(sum1-sum2));
			return;
		}
		subs[cnt] = true;
		subs(cnt+1);
		subs[cnt] = false;
		subs(cnt+1);
	}
}