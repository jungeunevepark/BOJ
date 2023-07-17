import java.io.*;
import java.util.*;

public class Main {
	static int R, C, N;
	static int[][] map;
	static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		for(int i=0; i<R; i++) {
			String s = bf.readLine();
			for(int j=0; j<C; j++) {
				if(s.charAt(j) == 'O') {
					map[i][j] = -1;
				}
			}
		}
		if(N == 1 || N == 0) {
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					System.out.print(map[i][j] != 0 ? 'O' : '.');
				}
				System.out.println();
			}
			return;
		}
		if(N % 2 == 0) {
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					System.out.print('O');
				}
				System.out.println();
			}
			return;
		}
		
		for(int i=0; i<R; i++)
			for(int j=0; j<C; j++)
				if(map[i][j] == 0) map[i][j] = 1;
		// N 이 2이상이고 홀수인 경우들
		N = N % 4;
		int[][] m0 = map;
		int[][] m1 = new int[R][C];
		ArrayDeque<int[]> bomb = new ArrayDeque<>();
		for(int i=0; i<R; i++)
			for(int j=0; j<C; j++)
				if(map[i][j] == -1) bomb.offer(new int[] {i, j});
		
		while(!bomb.isEmpty()) {
			int x = bomb.peek()[0];
			int y = bomb.poll()[1];
			map[x][y] = 0;
			for(int d=0; d<4; d++) {
				int nx = x+dx[d];
				int ny = y+dy[d];
				if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
				map[nx][ny] = 0;
			}
		}
		if(N == 1) {
			for(int i=0; i<R; i++)
				for(int j=0; j<C; j++)
					if(map[i][j] == 0) map[i][j] = -1;
			bomb = new ArrayDeque<>();
			for(int i=0; i<R; i++)
				for(int j=0; j<C; j++)
					if(map[i][j] == 1) bomb.offer(new int[] {i, j});
			
			while(!bomb.isEmpty()) {
				int x = bomb.peek()[0];
				int y = bomb.poll()[1];
				map[x][y] = 0;
				for(int d=0; d<4; d++) {
					int nx = x+dx[d];
					int ny = y+dy[d];
					if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
					map[nx][ny] = 0;
				}
			}
		}
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				System.out.print(map[i][j] != 0 ? 'O' : '.');
			}
			System.out.println();
		}
	}
}