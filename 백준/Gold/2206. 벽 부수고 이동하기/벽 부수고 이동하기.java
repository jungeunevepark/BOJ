import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			String s = bf.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = s.charAt(j)-'0';
			}
		}
		System.out.println(bfs());
		
		
	}
	static int bfs() {
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};
		boolean v[][][] = new boolean[N][M][2];
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {0, 0, 0, 1});
		v[0][0][0] = true;
		while(!q.isEmpty()) {
			int[] n = q.poll();
			if(n[0] == N-1 && n[1] == M-1) return n[3];
			for(int d=0; d<4; d++) {
				int nx = n[0]+dx[d];
				int ny = n[1]+dy[d];
				if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				if(map[nx][ny] == 0 && n[2] == 0 && !v[nx][ny][0]) {
					q.offer(new int[] {nx, ny, n[2], n[3]+1});
					v[nx][ny][0] = true;
				}
				else if(map[nx][ny] == 0 && n[2] == 1 && !v[nx][ny][1]) {
					q.offer(new int[] {nx, ny, n[2], n[3]+1});
					v[nx][ny][1] = true;
				}
				else if(map[nx][ny] == 1 && !v[nx][ny][1] && n[2] == 0) {
					q.offer(new int[] {nx, ny, n[2]+1, n[3]+1});
					v[nx][ny][1] = true;
				}
			}
		}
		return -1;
	}
}