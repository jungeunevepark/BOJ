import java.io.*;
import java.util.*;

public class Main {
	static int N, M ;
	static int t, k;
	static int[][] water;
	static int[][][] dir;
	static int[][][] smell;
	static int[] D;
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		water = new int[N][N];
		smell = new int[N][N][2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for(int j=0; j<N; j++) {
				water[i][j] = Integer.parseInt(st.nextToken());
				if(water[i][j] > 0) {smell[i][j][0] = water[i][j]; smell[i][j][1] = k;}
			}
		}
		D = new int[M+1];
		st = new StringTokenizer(bf.readLine(), " ");
		for(int i = 1; i<=M; i++)
			D[i] = Integer.parseInt(st.nextToken());
		
		dir = new int[M+1][5][5];
		for(int i=1; i<=M; i++) {
			for(int d = 1; d<=4; d++) {
				st = new StringTokenizer(bf.readLine(), " ");
				for(int u=1; u<=4; u++)
					dir[i][d][u] = Integer.parseInt(st.nextToken());
			}
		}
		for(t=1; t<=1001; t++) {
			if(t == 1001) break;
			move();
			if(M == 1) break;
		}
		if(t == 1001) t = -1;
		System.out.println(t);
	}
	static void move() {
		int[][] tmp = new int[N][N];
		ArrayDeque<int[]> newsmell = new ArrayDeque<>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(water[i][j] == 0) continue;
				int s = water[i][j]; int mx = N; int my = N; int md = 0;
				for(int d = 1; d<=4; d++) {
					int ni = i + dx[dir[s][D[s]][d]];
					int nj = j + dy[dir[s][D[s]][d]];
					if(ni < 0 || nj < 0 || ni >= N || nj >= N) continue;
					if(smell[ni][nj][0] != s && smell[ni][nj][1] >= t) continue;
					if(smell[ni][nj][1] < t) {mx = ni; my = nj; md = dir[s][D[s]][d]; break;} // 최적을 찾았으므로 나가도 됨
					else if(smell[ni][nj][0] == s && mx == N) // mx가 아무 선택도 안된 경우 -> 가장 처음에 선택된 방향이 됨
						{mx = ni; my = nj; md = dir[s][D[s]][d];}
				}
				if(tmp[mx][my] == 0) {tmp[mx][my] = water[i][j]; newsmell.offer(new int[] {mx, my, s, t+k});}
				else if(tmp[mx][my] > water[i][j]) {tmp[mx][my] = water[i][j]; M--; newsmell.offer(new int[] {mx, my, s, t+k});}
				else {M--;}
				D[s] = md;
			}
		}
		while(!newsmell.isEmpty()) {
			int[] n = newsmell.poll();
			smell[n[0]][n[1]][0] = n[2];
			smell[n[0]][n[1]][1] = n[3];
		}
		water = tmp;
	}
}