import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] paper;
	static int blue;
	static int white;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    N = Integer.parseInt(bf.readLine());
	    paper = new int[N][N];
	    for(int i=0; i<N; i++) {
	    	StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
	    	for(int j=0; j<N; j++)
	    		paper[i][j] = Integer.parseInt(st.nextToken());
	    }
	    count(0, 0, N);
	    System.out.println(blue);
	    System.out.println(white);
	}
	static void count(int x, int y, int N) {
		int check = paper[x][y];
		boolean flag = false;
		f: for(int i=x; i<x+N; i++) {
			for(int j=y; j<y+N; j++) {
				if(check != paper[i][j]) {
					flag = true; break f;
				}
			}
		}
		if (flag == false) {
			if(paper[x][y] == 0) blue++;
			else white++;
		}
		else {
			count(x, y, N/2);
			count(x, y+N/2, N/2);
			count(x+N/2, y, N/2);
			count(x+N/2, y+N/2, N/2);
		}
	}
}