import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int count;
	static int[] start;
	static int[][] map;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
	   BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	   StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
	   N = Integer.parseInt(st.nextToken());
	   M = Integer.parseInt(st.nextToken());
	   int T = Integer.parseInt(st.nextToken());
	   sb = new StringBuilder();
	   map = new int[N][M];
	   for(int i=0; i<N; i++) {
		   st = new StringTokenizer(bf.readLine(), " ");
		   for(int j=0; j<M; j++)
			   map[i][j] = Integer.parseInt(st.nextToken());
	   }
	   for(int t=0; t<T; t++) {
		   start = new int[N];
		   st = new StringTokenizer(bf.readLine(), " ");
		   int x = Integer.parseInt(st.nextToken());
		   int d = Integer.parseInt(st.nextToken());
		   int k = Integer.parseInt(st.nextToken());
		   for(int u = x; u<=N; u+=x) {
			   if(d == 0)
				   start[u-1] = start[u-1] - k + M;
			   else
				   start[u-1] = (start[u-1] + k) % M;
		   }
		   rotate();
	   }
	   System.out.println(average()[0]);
	}
	static int[] average() {
		int re = 0; int cnt= 0;
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++) {
				re+= map[i][j];
				if(map[i][j] != 0) cnt++;
			}
		return new int[] {re, cnt};
	}
	static void rotate() {
		int[][] result = new int[N][M];
		for(int i=0; i<N; i++) {
			int tem = start[i];
			for(int j=0; j<M; j++)
				result[i][j] = map[i][(tem++)%M];
		}
		int count = 0;
		for(int i=0; i<N; i++) {
			map[i][0] = result[i][0];
			for(int j=0; j<M-1; j++) {
				if(result[i][j] != 0 && result[i][j] == result[i][j+1]) {
					map[i][j] = 0;
					map[i][j+1] = 0; count++;
				}
				else map[i][j+1] = result[i][j+1];
			}
			if(result[i][M-1] != 0 && result[i][M-1] == result[i][0]) {
				map[i][M-1] = 0;
				map[i][0] = 0; 
			count++;};
		}
		for(int j=0; j<M; j++) {
			for(int i=0; i<N-1; i++) {
				if(result[i][j] != 0 && result[i][j] == result[i+1][j]) {
					map[i][j] = 0; count++;
					map[i+1][j] = 0;
				}
			}
		}
		if(count == 0) {
			int[] r = average();
			double avg = (double)r[0] / r[1];
			for(int i=0; i<N; i++)
				for(int j=0; j<M; j++) {
					if(map[i][j] == 0) continue;
					else if(map[i][j] > avg) map[i][j] --;
					else if(map[i][j] < avg) map[i][j] ++;
				}
		}
	}
}