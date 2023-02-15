import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int result;
	static int[][] apart;
	static boolean[][] v;
	static int account;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(bf.readLine());
		apart = new int[N][N];
		for(int i=0; i<N; i++) {
			String s = bf.readLine();
			for(int j=0; j<N; j++)
				apart[i][j] = s.charAt(j) - '0';
		}
		PriorityQueue<Integer> min = new PriorityQueue<>();
		v = new boolean[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++)
			{if(v[i][j] || apart[i][j] == 0) continue;
			account = 0;
			bfs(i, j);
			min.offer(account);
			}
		}
		sb.append(min.size()).append("\n");
		while(!min.isEmpty()) sb.append(min.poll()).append("\n");
		System.out.println(sb);
	}
	static void bfs(int x, int y) {
		v[x][y] = true;
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		while(!q.isEmpty()) {
			int[] now = q.poll(); account++;
			for(int d =0; d<4; d++) {
				int nx = now[0] + dx[d];
				int ny = now[1] + dy[d];
				if(nx < 0 || ny < 0 || nx >= N || ny >= N || v[nx][ny] || apart[nx][ny] == 0) continue;
				v[nx][ny] = true;
				q.offer(new int[] {nx, ny});
			}
		}
		
	}
	static void dfs(int x, int y) {
		v[x][y] = true; account++;
		for(int d =0; d<4; d++) {
			int nx = x+dx[d];
			int ny = y+dy[d];
			if(nx < 0 || ny < 0 || nx >= N || ny >= N || v[nx][ny] || apart[nx][ny] == 0) continue;
			dfs(nx, ny);
		}
	}
}