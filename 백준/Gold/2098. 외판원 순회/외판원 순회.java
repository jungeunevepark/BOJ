import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[][] W;
	static int[][] d;
	static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N= Integer.parseInt(bf.readLine());
        int flag = 1<<N-1;
        W = new int[N][N];
        d = new int[1<<N][N];
        for(int i=0; i<N; i++){
        	StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        	for(int j=0; j<N; j++) {
        		W[i][j] = Integer.parseInt(st.nextToken());
        		if(W[i][j] == 0) W[i][j] = 1000001;
        	}
        }
        d[0][N-1] = dfs(flag, N-1); //int t = 0;
        sb.append(d[0][N-1]);
        System.out.println(sb);
        
    }
    static int dfs(int flag, int x) {
    	for(int i=0; i<N-1; i++) {
    		if((flag & 1<<i) != 0) continue;
    		if(d[flag][i] == 0) { // 아직 계산을 하지 않았다면
	    		d[flag][i] = dfs(flag|1<<i, i);
	    		if(d[flag][i] == Integer.MAX_VALUE) d[flag][i] = W[i][N-1]; // 순열의 마지막 값
    		}
    	}
    	int min = Integer.MAX_VALUE;
    	for(int i=0; i<N-1; i++) {
    		if((flag & 1<<i) != 0) continue;
    		if(min > d[flag][i]+W[x][i]) {
    			min = d[flag][i] + W[x][i];
    		}
    	}
    	return min;
    }  
}