import java.util.*;
import java.io.*;

public class Main {
	static boolean v[][][];
	static int[][] sdoku = new int[9][9];
	static boolean flag;
	public static void main(String args[]) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        v = new boolean[3][9][10];
        for(int i=0; i<9; i++) {
        	StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        	for(int j=0; j<9; j++) {
        		sdoku[i][j] = Integer.parseInt(st.nextToken());
        		v[0][i][sdoku[i][j]] = true;
        		v[1][j][sdoku[i][j]] = true;
        		v[2][3*(i/3)+j/3][sdoku[i][j]] = true;
        	}
        }
        backtracking(0, 0);
    }
	static void backtracking(int x, int y) {
		if(flag) return;
		if(x == 8 && y == 9) {
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++)
					System.out.print(sdoku[i][j]+" ");
				System.out.println();
			}
			flag = true;
			return;
		}
		if(y == 9) {backtracking(x+1, 0); return;}
		if(sdoku[x][y] != 0) {backtracking(x, y+1); return;}
		for(int i=1; i<=9; i++) {
			if(v[0][x][i] || v[1][y][i] || v[2][3*(x/3)+y/3][i]) continue;
			sdoku[x][y] = i;
			v[0][x][i] = true;
			v[1][y][i] = true;
			v[2][3*(x/3)+y/3][i] = true;
			backtracking(x, y+1);
			v[0][x][i] = false;
			v[1][y][i] = false;
			v[2][3*(x/3)+y/3][i] = false;
			sdoku[x][y] = 0;
		}
	}
}