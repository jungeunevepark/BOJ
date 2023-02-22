import java.util.*;
import java.io.*;

public class Main {
	static int R, C;
	static char[][] bread;
	static int[] dx = { -1, 0, 1 };
	static int[] dy = { 1, 1, 1 };
	static int count;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		bread = new char[R][C];
		for (int i = 0; i < R; i++) {
			String s = bf.readLine();
			for (int j = 0; j < C; j++) {
				bread[i][j] = s.charAt(j);
			}
		}
		visited = new boolean[R][C];
		for(int i=0; i<R; i++) if(bread[i][0] != 'x') dfs(i, 0);
		System.out.println(count);
	}

	static boolean dfs(int x, int y) {
		visited[x][y] = true;
		if(y == C-1) {count++; return true;}
		boolean flag = false;
		d: for (int d = 0; d < 3; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if(nx < 0 || nx >= R || ny >= C || visited[nx][ny] || bread[nx][ny] == 'x') continue;
			if(dfs(nx, ny) == true) {flag = true; break d;}
		}
		return flag;
	}
}