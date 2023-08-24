import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] dx, dy;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] cheese = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for(int j=0; j<M; j++) {
				cheese[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dx = new int[]{0, 1, 0, -1};
		dy = new int[]{1, 0, -1, 0};
		int result = 0; int count = 0; int cnt = 0;
		while((cnt = hole(cheese)) != 0) {
			result++;
			count = cnt;
		}
		System.out.println(result);
		System.out.println(count);
	}
	static int hole(int[][] cheese) {
		
		int[][] temp = new int[N][M];
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++)
				temp[i][j] = cheese[i][j];
		
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {0, 0});
		boolean[][] v = new boolean[N][M];
		while(!q.isEmpty()) {
			int[] now = q.poll();
			for(int d=0; d<4; d++) {
				int ni = now[0]+dx[d];
				int nj = now[1]+dy[d];
				if(ni < 0 || nj < 0 || ni >= N || nj >= M || v[ni][nj]) continue;
				v[ni][nj] = true;
				if(cheese[ni][nj] == 0) {
					q.offer(new int[] {ni, nj});
					temp[ni][nj] = 'H';
				}
			}
		}
		
		int cnt = 0;
		for(int i=0; i<N; i++)
			f: for(int j=0; j<M; j++)
				if(cheese[i][j] == 1) {
					cnt++;
					for(int d=0; d<4; d++) {
						int ni = i+dx[d];
						int nj = j+dy[d];
						if(ni < 0 || nj < 0 || ni >= N || nj >= M || temp[ni][nj] != 'H') continue;
						cheese[i][j] = 0; continue f;
					}
				}
		
		return cnt;
	}
}