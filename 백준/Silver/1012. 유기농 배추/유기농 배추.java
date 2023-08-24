import java.util.*;
import java.io.*;

public class Main {
	static int M, N;
	static int[][] map;
	static boolean[][] v;
	static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1}; 
    public static void main(String args[]) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(bf.readLine());
        for(int t=1; t<=T; t++) {
        	StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        	M = Integer.parseInt(st.nextToken());
        	N = Integer.parseInt(st.nextToken());
        	int K = Integer.parseInt(st.nextToken());
        	map = new int[M][N];
        	v = new boolean[M][N];
        	for(int i=0; i<K; i++) {
        		st = new StringTokenizer(bf.readLine(), " ");
        		int x = Integer.parseInt(st.nextToken());
        		int y = Integer.parseInt(st.nextToken());
        		map[x][y] = 1;
        	}
        	int cnt = 0;
        	for(int i=0; i<M; i++) {
        		for(int j=0; j<N; j++) {
        			if(v[i][j] || map[i][j] == 0) continue;
        			v[i][j] = true;
        			bfs(i, j); cnt++;
        		}
        	}
        	sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }
    static void bfs(int x, int y) {
    	ArrayDeque<int[]> q = new ArrayDeque<>();
    	q.offer(new int[] {x, y});
    	while(!q.isEmpty()) {
    		int[] n = q.poll();
    		for(int d=0; d<4; d++) {
    			int nx = n[0] + dx[d];
    			int ny = n[1] + dy[d];
    			if(nx < 0 || ny < 0 || nx >= M || ny >= N || v[nx][ny] || map[nx][ny] == 0) continue;
    			v[nx][ny] = true;
    			q.offer(new int[] {nx, ny});
    		}
    	}
    }
}