import java.util.*;
import java.io.*;

public class Main {
	static int N, K;
	static int[][] fishes;
	static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    public static void main(String args[]) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        fishes = new int[N][N];
        st = new StringTokenizer(bf.readLine(), " ");
        for(int i=0; i<N; i++)
        	fishes[N-1][i] = Integer.parseInt(st.nextToken());
        
        int count = 0;
        while(true) {
	        // 물고기 수가 가장 적은 어항 찾기, 가장 큰 값도 찾아주기
	        ArrayDeque<Integer> q = new ArrayDeque<>();
	        int small = fishes[N-1][0]; int max = small;
	        q.offer(0);
	        for(int j=1; j<N; j++) {
	        	max = Integer.max(max, fishes[N-1][j]);
	        	if(small == fishes[N-1][j]) q.offer(j);
	        	else if(small > fishes[N-1][j]) {
	        		q = new ArrayDeque<>();
	        		q.offer(j);
	        		small = fishes[N-1][j];
	        	}
	        }
	        // 가장 많이 든 물고기의 수와 적게 든 물고기의 수의 차이가 K이하면 break
	        if(max - small <= K) break;
	        // 가장 적은 어항 물고기 +1 해주기
	        while(!q.isEmpty())
	        	fishes[N-1][q.poll()]++;
	        
	        // 가장 왼쪽 어항 쌓기
	        fishes[N-2][1] = fishes[N-1][0];
	        fishes[N-1][0] = 0;
	        
	        // 어항을 쌓아서 -> 공중 부양 -> 회전 -> 바닥에 올려두기
	        while(rotate1());
	        
	//        for(int[] f : fishes) System.out.println(Arrays.toString(f));
	        // 인접한 두 어항끼리 물고기 수를 조절
	        move_fish();
	        
	        // 한줄 세우기
	        down();
	        
	        // 두번 180도 회전
	        rotate2();
	        
	        move_fish();
	        down();
	        count++;
        }
        System.out.println(count);
    }
    static void move_fish() {
    	int[][] next = new int[N][N];
    	for(int i=0; i<N; i++)
    		for(int j=0; j<N; j++)
    			next[i][j] = fishes[i][j];
    	
    	for(int i=0; i<N; i++) {
    		for(int j=0; j<N; j++) {
    			if(fishes[i][j] == 0) continue;
    			for(int d =0 ; d<4; d++) {
    				int ni = i+dx[d];
    				int nj = j+dy[d];
    				if(ni < 0 || nj < 0 || ni >= N || nj >= N || fishes[ni][nj] == 0) continue;
    				if(fishes[i][j] < fishes[ni][nj]) { // 중복을 막기 위해 작을 때만 연산하게 함
    					next[i][j] += (fishes[ni][nj] - fishes[i][j]) / 5;
    					next[ni][nj] -= (fishes[ni][nj] - fishes[i][j]) / 5;
    				}
    			}
    		}
    	}
    	fishes = next;
    }
    static boolean rotate1() {
    	int sx = N-1, sy = -1, ex = 0, ey = 0;
    	int height = 0; int width = 0;
    	f: for(int j=0; j<N; j++) {
    		if(sy != -1) {
    			if(fishes[ex][j] == 0) {
    				ey = j-1; break;
    			}
    		}
    		else {
	    		if(fishes[sx][j] != 0) {
	    			if(sy == -1) sy = j;
	    			else continue;
	    			
	    			for(int i=N-2; i>=0; i--)
	    				if(fishes[i][j] == 0) {ex = i+1; continue f;}
	    		}
    		}
    	}
    	height=ey-sy+1;
    	width=sx-ex+1;
    	if((ey+1)+width > N || height < 0) return false;
    	int mx = N-2; int my = ey+1;
    	for(int i=0; i<height; i++) {
    		for(int j=0; j<width; j++) {
    			fishes[mx-i][my+j] = fishes[sx-j][ey-i];
    			fishes[sx-j][ey-i] = 0;
    		}
    	}
    	return true;
    }
    static void rotate2() {
    	int cnt = 0;
    	int mx = 2; int my = N/2;
    	int col = N/2;
    	while(++cnt <= 2) {
    		int sy = N - my -1; int sx = N-1;
    		for(int i=0; i<cnt; i++) {
    			for(int j=0; j<col; j++) {
    				fishes[N-mx+i][N - my+j] = fishes[sx-i][sy-j];
    				fishes[sx-i][sy-j] = 0;
    			}
    		}
    		mx*=2; my /=2;
    		col /= 2;
    	}
    }
    static void down() {
    	int[][] next = new int[N][N];
    	int cnt = 0;
    	for(int j=0; j<N; j++) {
    		for(int i=N-1; i>=0; i--) {
    			if(fishes[i][j] != 0) next[N-1][cnt++] = fishes[i][j];
    		}
    	}
    	fishes = next;
    }
}