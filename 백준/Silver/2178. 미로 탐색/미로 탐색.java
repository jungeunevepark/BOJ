import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] maze;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maze = new int[N][M];
		for(int i=0; i<N; i++) {
			String s = bf.readLine();
			for(int j=0; j<M; j++)
				maze[i][j] = s.charAt(j) - '0';
		}
		boolean[][] v = new boolean[N][M];
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {0, 0, 1});
		v[0][0] = true;
		while(!q.isEmpty()) {
			int[] now = q.poll();
			if(now[0] == N-1 && now[1] == M-1) {System.out.println(now[2]); break;}
			for(int d =0; d<4; d++) {
				int nx = now[0] + dx[d];
				int ny = now[1] + dy[d];
				if(nx < 0 || ny < 0 || nx >= N || ny >= M || v[nx][ny] || maze[nx][ny] == 0) continue;
				v[nx][ny] = true;
				q.offer(new int[] {nx, ny, now[2]+1});
			}
		}
	}

}