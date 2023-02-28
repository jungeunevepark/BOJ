import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static boolean[][] v;
	static boolean[][][] visited;
	static int[][] island;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int result;
	static ArrayDeque<int[]> is;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		is = new ArrayDeque<>();
		v = new boolean[N][N];
		island = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			for(int j=0; j<N; j++)
				island[i][j] = Integer.parseInt(st.nextToken());
		}
		int count = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!v[i][j] && island[i][j] == 1) {
					bfs(i, j, ++count);
				}
			}
		}
		result = Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(j<N-1 && island[i][j] != island[i][j+1]) {
					if(island[i][j] == 0)
						is.offer(new int[] {i, j+1, -1, island[i][j+1]});
					else
						is.offer(new int[] {i, j, -1, island[i][j]});
				}
				if(i<N-1 && island[i][j] != island[i+1][j]) {
					if(island[i][j] == 0)
						is.offer(new int[] {i+1, j, -1, island[i+1][j]});
					else 
						is.offer(new int[] {i, j, -1, island[i][j]});
				}
			}
		}
		visited = new boolean[count+1][N][N];
		System.out.println(find());
	}
	static int find() {
		while(!is.isEmpty()) {
			int[] now = is.poll();
			visited[now[3]][now[0]][now[1]] = true;
			if(island[now[0]][now[1]] != 0 && now[2] >= 0 && island[now[0]][now[1]] != now[3]) return now[2];
			for(int d=0; d<4; d++) { 
				int nx = now[0] + dx[d];
				int ny = now[1] + dy[d];
				if(nx<0 || ny<0 || nx >= N || ny >= N || visited[now[3]][nx][ny] || island[nx][ny] == now[3]) continue;
				visited[now[3]][nx][ny] = true;
				is.offer(new int[] {nx, ny, now[2]+1, now[3]});
			}
		}
		return -1;
	}
	static void bfs(int x, int y, int d) {
		v[x][y] = true;
		island[x][y] = d;
		ArrayDeque<int[]> q= new ArrayDeque<>();
		q.offer(new int[] {x, y});
		while(!q.isEmpty()) {
			int[] now = q.poll();
			for(int u = 0; u<4; u++) {
				int nx = now[0] + dx[u];
				int ny = now[1] + dy[u];
				if(nx < 0 || ny < 0 || nx >= N || ny >= N || v[nx][ny] || island[nx][ny] == 0) continue;
				v[nx][ny] = true;
				island[nx][ny] = d;
				q.offer(new int[] {nx, ny});
			}
		}
	}
}