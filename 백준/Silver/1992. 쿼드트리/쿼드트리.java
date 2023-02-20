import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] tree;
	static String result;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    //StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
	    N = Integer.parseInt(bf.readLine());
	    tree = new int[N][N];
	    for(int i=0; i<N; i++) {
	    	String s = bf.readLine();
	    	for(int j=0; j<N; j++)
	    		tree[i][j] = s.charAt(j) - '0';
	    }
	    result = find(0, 0, N);
	    System.out.println(result);
	    
	}
	static String find(int x, int y, int N) {
		int check = tree[x][y];
		boolean flag = false;
		f: for(int i=x; i<x+N; i++) {
			for(int j=y; j<y+N; j++) {
				if(check != tree[i][j]) {
					flag = true; break f;
				}
			}
		}
		if (flag == false) return check+"";
		else {
			return "("+find(x, y, N/2)+find(x, y+N/2, N/2)+find(x+N/2, y, N/2)+find(x+N/2, y+N/2, N/2)+")";
		}
	}
}