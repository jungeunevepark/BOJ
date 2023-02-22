import java.io.*;
import java.util.*;

public class Main {
	static int w, h;
	static int[][] map;
	static boolean[][] visited;
	static int dx[] = {0, 1, 0, -1, 1, 1, -1, -1};
	static int dy[] = {1, 0, -1, 0, 1, -1, 1, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if(w == 0 && h == 0) break;
			map = new int[h][w];
			for(int i=0; i<h; i++) {
				st = new StringTokenizer(bf.readLine()," ");
				for(int j=0; j<w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			visited = new boolean[h][w];
			int count =0;
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					if(visited[i][j]) continue;
					if(map[i][j] == 1) {
						count++;
						dfs(i, j); 
					}
				}
			}
			sb.append(count).append("\n");
		}
		System.out.println(sb);
	}
	static void dfs(int x, int y) {
		visited[x][y] = true;
		for(int d=0; d<8; d++) {
			int nx = x+dx[d];
			int ny = y+dy[d];
			if(nx < 0 || ny < 0 || nx >= h || ny >= w || visited[nx][ny] || map[nx][ny] == 0) continue;
			dfs(nx, ny);
		}
	}
}