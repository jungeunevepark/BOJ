import java.util.*;
import java.io.*;

public class Main {
	static int N, M, K;
	static int[][] map;
	static boolean [][][] v;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	  public static void main(String[] args) throws Exception {
	        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
	        N = Integer.parseInt(st.nextToken());
	        M = Integer.parseInt(st.nextToken());
	        K = Integer.parseInt(st.nextToken());
	        map = new int[N][M]; 
	        for(int i=0; i<N; i++) {
	        	String s = bf.readLine();
	        	for(int j=0; j<M; j++) {
	        		map[i][j] = s.charAt(j) - '0';
	        	}
	        }
	        System.out.println(bfs(0, 0));
	  }
	  static int bfs(int x, int y) {
		  ArrayDeque<int[]> q = new ArrayDeque<>();
		  v = new boolean[K+1][N][M];
		  q.offer(new int[] {x, y, 0, 1});
		  v[0][0][0] = true;
		  while(!q.isEmpty()) {
			  int[] now = q.poll();
			  if(now[0] == N-1 && now[1] == M-1) return now[3];
			  for(int d =0; d<4; d++) {
				  int nx = now[0] + dx[d];
				  int ny = now[1] + dy[d];
				  if(nx<0 || ny<0 || nx>=N || ny>=M) continue;
				  if(now[2] >= K && map[nx][ny] > 0) continue;
				  if(map[nx][ny] > 0 && !v[now[2]+1][nx][ny]) {
					  v[now[2]+1][nx][ny] = true;
					  q.offer(new int[] {nx, ny, now[2]+1, now[3]+1});
				  }
				  else if(map[nx][ny] == 0 && !v[now[2]][nx][ny]) {
					  v[now[2]][nx][ny] = true;
					  q.offer(new int[] {nx, ny, now[2], now[3]+1});
				}
			  }
				  
		  }
		  return -1;
	  }
}