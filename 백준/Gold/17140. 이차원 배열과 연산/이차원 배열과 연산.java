import java.util.*;
import java.io.*;

public class Main {
	static int r;
	static int c;
	static int k;
	static int[][] A;
	static int R;
	static int C;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        A = new int[100][100];
        for(int i=0; i<3; i++) {
        	st = new StringTokenizer(bf.readLine(), " ");
        	for(int j=0; j<3; j++)
        		A[i][j] = Integer.parseInt(st.nextToken());	
        }
        int i = 0; R = 3; C = 3;
        for(i=0; i<=100; i++) {
        	if(A[r-1][c-1] == k) break;
        	sorting();
        }
        if(i > 100) i = -1;
        sb.append(i).append("\n");
        System.out.println(sb.toString());
        
    }
    
    static void sorting() {
    	int maxr = 0; int maxc = 0; int[] temp = null;
    	if (R >= C) {
    		for(int i=0; i<R; i++) {
    			int now = 0; temp = new int[100];
    			for(int j=0; j<C; j++){
    				if(A[i][j] == 0) continue;
    				int flag = 0;
    				for(int k =0; k<now*2; k+= 2) {
    					if(temp[k] == A[i][j]) {
    						temp[k+1] ++; flag = 1;
    						int g = k; int c; int h;
    						for(int u=k+2; u<now*2; u+= 2) {
    							if(temp[g+1] > temp[u+1] || (temp[g+1] == temp[u+1] && temp[g] > temp[u])) {
    								c = temp[g]; h = temp[g+1];
        							temp[g] = temp[u]; temp[g+1] = temp[u+1];
        							temp[u] = c; temp[u+1]= h;
        							g = u;
    							}
    						}
    					}
    					if(flag == 1) break;
    				}
    				if (flag == 0) {
    					temp[2*now] = A[i][j];
    					temp[2*now+1] ++;
    					int change = now; int c; int h;
    					for(int k =now*2-1; k>=0; k-= 2) {
    						if(temp[k] > temp[change*2+1] || (temp[k] == temp[change*2+1] && temp[k-1] > temp[change*2])) {
    							c = temp[k-1]; h = temp[k];
    							temp[k-1] = temp[change*2]; temp[k] = temp[change*2+1];
    							temp[change*2] = c; temp[change*2+1]= h;
    							change = k/2;
    							
    						}
    					}
    					now ++;
    				}
    			}
    			if(maxc < now*2) maxc = now*2;
    			A[i] = temp;
    		}
    		C= maxc;
    		
    	}
    	else {
    		for(int j=0; j<C; j++) {
			int now = 0; temp = new int[100];
			for(int i=0; i<R; i++){
				int flag = 0;
				if(A[i][j] == 0) continue;
				for(int k =0; k<now*2; k+= 2) {
					if(temp[k] == A[i][j]) {
						temp[k+1] ++; flag = 1;
						int g = k; int c; int h;
						for(int u=k+2; u<now*2; u+= 2) {
							if(temp[g+1] > temp[u+1] || (temp[g+1] == temp[u+1] && temp[g] > temp[u])) {
								c = temp[g]; h = temp[g+1];
    							temp[g] = temp[u]; temp[g+1] = temp[u+1];
    							temp[u] = c; temp[u+1]= h;
    							g = u;
							}
						}
					}
					if(flag == 1) break;
				}
				if (flag == 0) {
					temp[2*now] = A[i][j];
					temp[2*now+1] ++;
					int change = now; int c; int h;
					for(int k =now*2-1; k>=0; k-= 2) {
						if(temp[k] > temp[change*2+1] || (temp[k] == temp[change*2+1] && temp[k-1] > temp[change*2])) {
							c = temp[k-1]; h = temp[k];
							temp[k-1] = temp[change*2]; temp[k] = temp[change*2+1];
							temp[change*2] = c; temp[change*2+1]= h;
							change = k/2;
							
						}
					}
					now ++;
				}
			}
			if(maxr < now*2) maxr = now*2;
			for(int u =0; u<100; u++) {
				A[u][j] = temp[u];
			}
			}
		R= maxr;
		
    	}
    }
   
}