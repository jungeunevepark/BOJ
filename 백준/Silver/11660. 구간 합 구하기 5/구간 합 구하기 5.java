import java.util.*;
import java.io.*;

public class Main {
	static int[][] d;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		d = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for(int j=1; j<=N; j++)
				d[i][j] = d[i-1][j] + d[i][j-1] - d[i-1][j-1] + Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			int sx, sy, ex, ey;
			sx = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());
			ex = Integer.parseInt(st.nextToken());
			ey = Integer.parseInt(st.nextToken());
			sb.append(d[ex][ey] - d[ex][sy-1] - d[sx-1][ey] + d[sx-1][sy-1]).append("\n");
		}
		System.out.println(sb.toString());
	}
}