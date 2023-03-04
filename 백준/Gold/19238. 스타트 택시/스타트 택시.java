import java.io.*;
import java.util.*;

public class Main {
	static int[][] map;
	static int N, M, engine, add;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int tx, ty;
	static int result;
	static ArrayList<int[]> custom;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		engine = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for(int j=0; j<N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(bf.readLine(), " ");
		tx = Integer.parseInt(st.nextToken()) -1;
		ty = Integer.parseInt(st.nextToken()) -1;
		custom = new ArrayList<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			map[x][y] = 'C';
			custom.add(new int[] {x, y, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1});
			
		}
		while(true) {
			if(custom.isEmpty()) {System.out.println(engine); break;}
			if(!find_customer()) {System.out.println(-1); break;}
			if(!move_customer()) {System.out.println(-1); break;}
		}
	}
	static boolean move_customer() {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		boolean[][] v = new boolean[N][N];
		v[tx][ty] = true;
		int mx = 0; int my = 0;
		for(int i=0; i<custom.size(); i++) {
			int[] now = custom.get(i);
			if(now[0] == tx && now[1] == ty) {
				mx = now[2]; my = now[3]; 
				custom.remove(i);
				break;
			}
		}
		q.offer(new int[] {tx, ty, 0});
		while(!q.isEmpty()) {
			int[] now = q.poll();
			if(now[2] > engine) return false;
			if(now[0] == mx && now[1] == my) {engine += now[2]; tx = mx; ty = my; return true;}
			for(int d=0; d<4; d++) {
				int nx = now[0] + dx[d];
				int ny = now[1] + dy[d];
				if(nx < 0 || ny < 0 || nx >=N || ny >= N || v[nx][ny] || map[nx][ny] == 1) continue;
				v[nx][ny] = true;
				q.offer(new int[] {nx, ny, now[2] + 1});
			}
		}
		return false;
	}
	static boolean find_customer() {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		boolean[][] v = new boolean[N][N];
		v[tx][ty] = true;
		int result = Integer.MAX_VALUE; int x = N; int y = N;
		q.offer(new int[] {tx, ty, 0});
		while(!q.isEmpty()) {
			int[] now = q.poll();
			if(now[2] > result || now[2] > engine) break;
			if(map[now[0]][now[1]] == 'C') {
				if(x > now[0] || (x == now[0] && y > now[1])) {
					result = now[2]; x = now[0]; y = now[1];
				}
			}
			for(int d=0; d<4; d++) {
				int nx = now[0] + dx[d];
				int ny = now[1] + dy[d];
				if(nx < 0 || ny < 0 || nx >=N || ny >= N || v[nx][ny] || map[nx][ny] == 1) continue;
				v[nx][ny] = true;
				q.offer(new int[] {nx, ny, now[2] + 1});
 			}
		}
		if(x == N) return false;
		tx = x; ty = y; engine -= result; map[tx][ty] = 0;
		return true;
	}
}