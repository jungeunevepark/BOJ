import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int result;
	static int[][] queen;
	static int[] dx = {1, 1, -1, -1};
	static int[] dy = {-1, 1, -1, 1};
	static boolean[] v;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N= Integer.parseInt(bf.readLine());
        queen = new int[N][N];
        v = new boolean[N];
        dfs(0, 0);
        System.out.println(result);
    }
    static void dfs(int cnt, int x) {
    	if(cnt == N) {
    		result++;
    		return;
    	}
    	f: for(int j=0; j<N; j++) {
    		if(v[j]) continue;
    		int flag = 0;
    		for(int d=0; d<4; d++) {
    			if(flag == 1) continue;
    			int ni = x; int nj = j;
    			while(true) {
    				ni += dx[d];
    				nj += dy[d];
    				if(ni<0 || ni >= N || nj < 0 || nj >= N) {break;}
    				if(queen[ni][nj] == 1)  {flag = 1; break;}
    			}	
    		}// end for
    		
    		if(flag == 0) {
	    		queen[x][j] = 1;
	    		v[j] = true;
	    		dfs(cnt+1, x+1);
	    		queen[x][j] = 0;
	    		v[j] = false;
    		}// end if
    		
    	}// end for
    }  
}