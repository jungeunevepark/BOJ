import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[][] W;
	static int[] permutation;
	static boolean[] v;
	static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N= Integer.parseInt(bf.readLine());
        v = new boolean[N];
        permutation = new int[N];
        W = new int[N][N];
        for(int i=0; i<N; i++){
        	StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        	for(int j=0; j<N; j++) {
        		W[i][j] = Integer.parseInt(st.nextToken());
        		if(W[i][j] == 0) W[i][j] = 1000001;
        	}
        }
        perm(1);
        permutation[0] = 0;
        sb.append(result);
        System.out.println(result);
        
    }
    static void perm(int cnt) {
    	if(cnt == N) {
    		int ans = 0;
    		for(int i=1; i<N; i++)
    			ans += W[permutation[i-1]][permutation[i]];
    		ans += W[permutation[N-1]][permutation[0]];
    		if(ans < result) result = ans;
    		return;
    	}
    	for(int i=1; i<N; i++) {
    		if(v[i]) continue;
    		v[i] = true;
    		permutation[cnt] = i;
    		perm(cnt+1);
    		v[i] = false;
    	}
    }
}