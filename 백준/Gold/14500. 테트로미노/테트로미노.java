import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[][] map;
	static int result;
	static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    public static void main(String args[]) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(bf.readLine(), " ");
        	for(int j=0; j<M; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
//        bfs(0 ,0);
        result = 0;
        for(int i=0; i<N; i++)
        	for(int j=0; j<M; j++)
        		poly(i, j);
        System.out.println(result);
    }
    static void bfs(int x, int y) {
    	boolean[][] v = new boolean[N][M];
    	ArrayDeque<int[]> q = new ArrayDeque<>();
    	q.offer(new int[] {x, y, 1, map[x][y]});
    	int cnt = 0;
    	v[x][y] = true;
    	while(!q.isEmpty()) {
    		int[] now = q.poll();
    		if(now[2] == 4) {
    			result = Math.max(result, now[3]); cnt++; continue;
    		}
    		for(int d=0; d<4; d++) {
    			int nx = now[0] + dx[d];
    			int ny = now[1] + dy[d];
    			if(nx < 0 || ny < 0 || nx >= N || ny >= M || v[nx][ny]) continue;
    			v[nx][ny] = true;
    			q.offer(new int[] {nx, ny, now[2]+1, now[3]+map[nx][ny]});
    		}
    	}
    	System.out.println("cnt = " + cnt);
    }
    static void poly(int x, int y) {
    	int cnt = 0; int count = 0;
    	if(N-x >= 4) // 1번 원형
    		for(int i=x; i<x+4; i++)
    			cnt+= map[i][y];
    	result = Math.max(result, cnt);
    	cnt = 0;
    	if(M-y>=4) // 1번 회전 = 대칭은 동일함
    		for(int j=y; j<y+4; j++)
    			cnt+=map[x][j];
    	result = Math.max(result, cnt);
    	cnt = 0;
    	if(N-x>=2 && M-y>=2) // 2번 원형 = 대칭, 회전 모두 동일
    		for(int i=x; i<x+2; i++)
    			for(int j=y; j<y+2; j++)
    				cnt+=map[i][j];
    	result = Math.max(result, cnt);
    	cnt = 0;
    	if(N-x >= 3 && M-y >= 2) { // 3번 원형
    		for(int i=x; i<x+3; i++)
    			cnt+= map[i][y];
    		cnt += map[x+2][y+1];
    	}
    	result = Math.max(result, cnt);
    	cnt = 0;
    	if(N-x >= 2 && M-y >= 3) { // 3번 시계방향 90도 회전
    		for(int j=y; j<y+3; j++)
    			cnt+= map[x][j];
    		cnt += map[x+1][y];
    	}
    	result = Math.max(result, cnt);
    	cnt = 0;
    	if(N-x >= 3 && M-y >= 2) { // 3번 시계방향 180도 회전
    		for(int i=x; i<x+3; i++)
    			cnt+= map[i][y+1];
    		cnt += map[x][y];
    	}
    	result = Math.max(result, cnt);
    	cnt = 0;
    	if(x >= 1 && N-x >= 1 && M-y >= 3) { // 3번 시계방향 270도 회전
    		for(int j=y; j<y+3; j++)
    			cnt+= map[x][j];
    		cnt += map[x-1][y+2];
    	}
    	result = Math.max(result, cnt);
    	cnt = 0;
    	if(y>=1 && N-x >= 3 && M-y >= 1) { // 3번 대칭
    		for(int i=x; i<x+3; i++)
    			cnt+= map[i][y];
    		cnt += map[x+2][y-1];
    	}
    	result = Math.max(result, cnt);
    	cnt = 0;
    	if(N-x >= 2 && M-y >= 3) { // 3번 대칭 - 시계방향 90도 회전
    		for(int j=y; j<y+3; j++)
    			cnt+= map[x+1][j];
    		cnt += map[x][y];
    	}
    	result = Math.max(result, cnt);
    	cnt = 0;
    	if(N-x >= 3 && M-y >= 2) { // 3번 대칭 - 시계방향 180도 회전
    		for(int i=x; i<x+3; i++)
    			cnt+= map[i][y];
    		cnt += map[x][y+1];
    	}
    	result = Math.max(result, cnt);
    	cnt = 0;
    	if(N-x >= 2 && M-y >= 3) { // 3번 대칭 - 시계방향 270도 회전
    		for(int j=y; j<y+3; j++)
    			cnt+= map[x][j];
    		cnt += map[x+1][y+2];
    	}
    	result = Math.max(result, cnt);
    	cnt = 0;
    	if(N-x >= 3 && M-y>=2) { // 4번 원형 = 180도 회전과 동일
    		cnt += (map[x][y] + map[x+1][y] + map[x+1][y+1] + map[x+2][y+1]);
    	}
    	result = Math.max(result, cnt);
    	cnt = 0;
    	if(x>=1 && N-x >= 1 && M-y>=3) { // 4번 90도 회전 = 270도 회전과 동일
    		cnt += (map[x][y]+map[x][y+1]+map[x-1][y+1] + map[x-1][y+2]);
    	}
    	result = Math.max(result, cnt);
    	cnt = 0;
    	if(x>=1 && N-x >= 2 && M-y>=2) { // 4번 대칭 = 180도와 동일
    		cnt += (map[x][y] + map[x+1][y] + map[x][y+1] + map[x-1][y+1]);
    	}
    	result = Math.max(result, cnt);
    	cnt = 0;
    	if(N-x >= 2 && M-y>=3) { // 4번 대칭 90도 회전 = 270도 회전과 동일
    		cnt += (map[x][y]+map[x][y+1]+map[x+1][y+1] + map[x+1][y+2]);
    	}
    	result = Math.max(result, cnt);
    	cnt = 0;
    	if(N-x >= 2 && M-y >= 3) { // 5번 원형 = 대칭
    		for(int j=y; j<y+3; j++)
    			cnt+=map[x][j];
    		cnt += map[x+1][y+1];
    	}
    	result = Math.max(result, cnt);
    	cnt = 0;
    	if(y>=1 && N-x >= 3 && M-y >= 1) { // 5번 90도 회전
    		for(int i=x; i<x+3; i++)
    			cnt+=map[i][y];
    		cnt+=map[x+1][y-1];
    	}
    	result = Math.max(result, cnt);
    	cnt = 0;
    	if(x>=1 && N-x >= 1 && M-y >= 3) { // 5번 180도 회전
    		for(int j=y; j<y+3; j++)
    			cnt+=map[x][j];
    		cnt += map[x-1][y+1];
    	}
    	result = Math.max(result, cnt);
    	cnt = 0;
    	if(N-x >= 3 && M-y >= 2) { // 5번 270도 회전
    		for(int i=x; i<x+3; i++)
    			cnt+=map[i][y];
    		cnt+=map[x+1][y+1];
    	}
    	result = Math.max(result, cnt);
    }
}