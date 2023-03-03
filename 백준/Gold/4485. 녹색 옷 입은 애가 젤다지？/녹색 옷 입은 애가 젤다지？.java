import java.util.*;
import java.io.*;

public class Main {
	static int N, M, K;
	static int count;
	static int[][] map;
	static boolean [][][] v;
	static final int INF = Integer.MAX_VALUE;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
	   BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	   sb = new StringBuilder();
	   while((N = Integer.parseInt(bf.readLine()))!=0) {
		   map = new int[N][N];
		   for(int i=0; i<N ;i++) {
			   StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			   for(int j=0; j<N; j++)
				   map[i][j] = Integer.parseInt(st.nextToken());
		   }
		   sb.append("Problem ").append(++count).append(": ");
		   find();
	   }
	   System.out.println(sb);
	}
	static void find() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
		int[][] dist = new int[N][N];
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				dist[i][j] = INF;
		
		dist[0][0] = map[0][0];
		pq.offer(new int[] {0, 0, dist[0][0]});
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			for(int d= 0; d<4; d++) {
				int nx = now[0] + dx[d];
				int ny = now[1] + dy[d];
				if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				if(dist[nx][ny] > dist[now[0]][now[1]] + map[nx][ny]) {
					dist[nx][ny] = dist[now[0]][now[1]] + map[nx][ny];
					pq.offer(new int[] {nx, ny, dist[nx][ny]});
				}
				
			}
		}
		sb.append(dist[N-1][N-1]).append("\n");
	}
}