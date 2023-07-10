import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] ices;
	static boolean[][] visited;
	static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ices = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < M; j++) {
				ices[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int time = 0;
		while (true) {
			if (check())
				break;
			remove();
			// for(int[] i: ices)
			// 	System.out.println(Arrays.toString(i));
			// System.out.println();
			time++;
		}
		int ice = 0;
		for(int i=0; i<N; i++){
			for(int j=0; j<M; j++){
				if(ices[i][j] > 0) ice++;
			}
		}
		System.out.println(ice == 0 ? 0 : time);
	}
	static void remove(){
		int[][] minus = new int[N][M];
		for(int i=0; i<N; i++){
			for(int j=0; j<M; j++){
				if(ices[i][j] > 0){
					for(int d=0; d<4; d++){
						int ni = i+dx[d]; int nj = j+dy[d];
						if(ni < 0 || nj < 0 || ni >= N || nj >= M) continue;
						if(ices[ni][nj] == 0) minus[i][j]++;
					}
				}
			}
		}
		for(int i=0; i<N; i++) {
			for (int j = 0; j < M; j++) {
				if (minus[i][j] > 0) {
					if (ices[i][j] >= minus[i][j])
						ices[i][j] -= minus[i][j];
					else
						ices[i][j] = 0;
				}
			}
		}
	}
	static void bfs(int x, int y){
		visited[x][y] = true;
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{x, y});
		while(!q.isEmpty()){
			int[] now = q.poll();
			for(int d=0; d<4; d++){
				int nx = now[0]+dx[d];
				int ny = now[1]+dy[d];
				if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				if(!visited[nx][ny] && ices[nx][ny] > 0){
					visited[nx][ny] = true;
					q.offer(new int[]{nx, ny});
				}
			}
		}
	}
	static boolean check(){
		visited = new boolean[N][M];
		int fishes = 0;
		for(int i=0; i<N; i++){
			for(int j=0; j<M; j++){
				if(!visited[i][j] && ices[i][j] != 0){
					fishes++;
					bfs(i, j);
				}
			}
		}
		if(fishes >= 2 || fishes == 0) return true;
		return false;
	}
}