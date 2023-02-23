import java.io.*;
import java.util.*;

public class Main {
	static int R, C, max;
	static char[][] board;
	static boolean[] v;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		v = new boolean[26];
		for(int i=0; i<R; i++) {
			String s = bf.readLine();
			for(int j=0; j<C; j++) {
				board[i][j] = s.charAt(j);
			}
		}
		dfs(0, 0, 1);
		System.out.println(max);
	}
	static void dfs(int x, int y, int cnt) {
		max = Math.max(max, cnt);
		v[board[x][y] - 'A'] = true;
		for(int d= 0; d<4; d++) {
			int nx = x+dx[d];
			int ny = y+dy[d];
			if(nx < 0 || ny < 0 || nx >= R || ny >= C || v[board[nx][ny] - 'A']) continue;
			dfs(nx, ny, cnt+1);
		}		
		v[board[x][y] - 'A'] = false;
	}
}