import java.util.*;
import java.io.*;

public class Main {
	static int n, N, Q; // N = 2^n
	static int[][] map;
	static boolean[][] v;
	static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1}; 
    public static void main(String args[]) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        
        N = (int) Math.pow(2, n);
        map = new int[N][N];
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(bf.readLine(), " ");
        	for(int j=0; j<N; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        st = new StringTokenizer(bf.readLine(), " ");
        for(int i=0; i<Q; i++) {
        	int L = Integer.parseInt(st.nextToken());
        	if(L != 0)
        		dfs(0, 0, (int)Math.pow(2, L));
        	int[][] temp = new int[N][N];
        	for(int x=0; x<N; x++) {
        		for(int y=0; y<N; y++) {
        			if(map[x][y] == 0) continue;
        			int cnt = 0;
        			for(int d=0; d<4; d++) {
        				int nx = x+dx[d]; int ny = y+dy[d];
        				if(nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 0) continue;
        				cnt++;
        			}
        			if(cnt < 3) temp[x][y] = map[x][y]-1;
        			else temp[x][y] = map[x][y];
        		}
        	}
        	map = temp;
        }
        int cnt = 0; int max = 0;
        v = new boolean[N][N];
        for(int i=0; i<N; i++)
        	for(int j=0; j<N; j++) {
        		cnt+=map[i][j];
        		if(!v[i][i] && map[i][j] != 0)
        			max = Math.max(max, bfs(i, j));
        }
        System.out.println(cnt);
        System.out.println(max);
        
    }
    static int bfs(int x, int y) {
    	ArrayDeque<int[]> q = new ArrayDeque<>();
    	ArrayDeque<int[]> m = new ArrayDeque<>();
    	v[x][y] = true; int cnt = 1;
    	q.offer(new int[] {x, y});
    	m.offer(new int[] {x, y});
    	while(!q.isEmpty()) {
    		int[] now = q.poll();
    		for(int d=0; d<4; d++) {
    			int nx = now[0] + dx[d];
    			int ny = now[1] + dy[d];
    			if(nx < 0 || ny < 0 || nx >= N || ny >= N || v[nx][ny] || map[nx][ny] == 0) continue;
    			cnt++;
    			v[nx][ny] = true;
    			q.offer(new int[] {nx, ny});
    			m.offer(new int[] {nx, ny});
    		}
    	}
    	return cnt;
    }
    static void dfs(int x, int y, int L) {
    	if(x == N) return;
    	int[][] temp = new int[N][N];
    	for(int i=0; i<L; i++)
    		for(int j=0; j<L; j++)
    			temp[i][j] = map[i+x][j+y];
    	
    	for(int i=0; i<L; i++)
    		for(int j=0; j<L; j++)
    			map[i+x][j+y] = temp[L-j-1][i];
    	
    	if(y+L == N) dfs(x+L, 0, L);
    	else dfs(x, y+L, L);
    }
}