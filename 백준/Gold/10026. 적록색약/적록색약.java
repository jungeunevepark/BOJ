import java.io.*;
import java.util.*;

public class Main {
	static int C, N;
	static char[][] Ypicture;
	static char[][] Npicture;
	static boolean[][] v;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		Ypicture = new char[N][N];
		Npicture = new char[N][N];
		for(int i=0; i<N; i++) {
			String s = bf.readLine();
			for(int j=0; j<N; j++) {
				Ypicture[i][j] = s.charAt(j);
				Npicture[i][j] = Ypicture[i][j];
				if(Npicture[i][j] == 'G') Npicture[i][j] = 'R';
			}
		}
		v = new boolean[N][N]; int count = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(v[i][j]) continue;
				count++;
				dfs(i, j, Ypicture);
			}
		}
		System.out.println(count);
		v = new boolean[N][N]; count = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(v[i][j]) continue;
				count++;
				dfs(i, j, Npicture);
			}
		}
		System.out.println(count);
		
	}
	static void dfs(int x, int y, char[][] temp) {
		v[x][y] = true;
		for(int d=0; d<4; d++) {
			int nx = x+dx[d];
			int ny = y+dy[d];
			if(nx<0 || ny<0 || nx >= N || ny>= N || v[nx][ny] || temp[x][y] != temp[nx][ny]) continue;
			dfs(nx, ny, temp);
 		}
	}
}