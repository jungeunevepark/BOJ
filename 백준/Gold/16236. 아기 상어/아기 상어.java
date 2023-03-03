import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int sx, sy, size = 2, cnt;
	static int[][] map;
	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};
	public static void main(String[] args) throws Exception {
	   BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	   StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
	   N = Integer.parseInt(st.nextToken());
	   size = 2;
	   map = new int[N][N];
	   for(int i=0; i<N; i++) {
		   st = new StringTokenizer(bf.readLine(), " ");
		   for(int j=0; j<N; j++) {
			   map[i][j] = Integer.parseInt(st.nextToken());
			   if(map[i][j] == 9) {sx = i; sy = j; map[i][j] = 0;}
		   }
	   }
	   int result = 0;
	   while(true) {
		   int c = bfs();
		   if(c==0) break;
		   result += c;
	   }
	   System.out.println(result);
	}
	static int bfs() {
		boolean[][] v = new boolean[N][N];
		v[sx][sy] = true;
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {sx, sy, 0});

		int result = Integer.MAX_VALUE; int x = N+1; int y = N+1;
		while(!q.isEmpty()) {
			int[] now = q.poll();
			if(now[2] > result) break;
			if(map[now[0]][now[1]] > 0 && map[now[0]][now[1]] < size) { // 먹을 수 있는 물고기 등장
				if(x > now[0] || (x == now[0] && y > now[1])) {
					x = now[0]; y = now[1]; result = now[2];
				}
			}
			for(int d = 0; d<4; d++) {
				int nx = now[0] + dx[d];
				int ny = now[1] + dy[d];
				if(nx < 0 || ny < 0 || nx >= N || ny >= N || v[nx][ny] || map[nx][ny] > size) continue;
				v[nx][ny] = true;
				q.offer(new int[] {nx, ny, now[2]+1});
			}
		}
		if(x != N+1) {
			cnt++; map[x][y] = 0;
			sx = x; sy = y;
			if(cnt == size) {size++; cnt = 0;}
			return result;			
		}	
		return 0;
	}
}