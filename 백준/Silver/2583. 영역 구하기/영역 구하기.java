import java.util.*;
import java.io.*;

public class Main {
	static int M, N, K;
	static boolean[][] map, v;
	static PriorityQueue<Integer> pq;
	static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
    public static void main(String args[]) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new boolean[M][N];
        pq = new PriorityQueue<>();
        for(int i=0; i<K; i++) {
        	st = new StringTokenizer(bf.readLine(), " ");
        	int sy = Integer.parseInt(st.nextToken());
        	int sx = Integer.parseInt(st.nextToken());
        	int ey = Integer.parseInt(st.nextToken())-1;
        	int ex = Integer.parseInt(st.nextToken())-1;
        	cover(sx, sy, ex, ey);
        }
        v = new boolean[M][N];
        for(int i=0; i<M; i++) {
        	for(int j=0; j<N; j++) {
        		if(v[i][j] || map[i][j]) continue;
        		bfs(i, j);
        	}
        }
        System.out.println(pq.size());
        while(!pq.isEmpty()) {
        	System.out.print(pq.poll()+" ");
        }
    }
    static void cover(int sx, int sy, int ex, int ey) {
    	for(int i=sx; i<=ex; i++)
    		for(int j=sy; j<=ey; j++)
    			map[i][j] = true;
    }
    static void bfs(int i, int j) {
    	ArrayDeque<int[]> q = new ArrayDeque<>();
    	q.offer(new int[] {i, j});
    	v[i][j] = true;
    	int cnt = 1;
    	while(!q.isEmpty()) {
    		int[] now = q.poll();
    		for(int d=0 ; d<4; d++) {
    			int nx = now[0] + dx[d];
    			int ny = now[1] + dy[d];
    			if(nx < 0 || ny < 0 || nx >= M || ny >= N || v[nx][ny] || map[nx][ny]) continue;
    			v[nx][ny] = true;
    			q.offer(new int[] {nx, ny}); cnt++;
    		}
    	}
    	pq.offer(cnt);
    }
}