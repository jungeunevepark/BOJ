import java.util.*;
import java.io.*;
public class Main {
	static int[][] sdoku;
	static boolean[][][] v;
	static boolean flag;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		sdoku = new int[9][9];
		v = new boolean[3][10][10];
		for(int i=0; i<9; i++) {
			String s = bf.readLine();
			for(int j=0; j<9; j++) {
				int num = s.charAt(j) - '0';
				sdoku[i][j] = num;
				v[0][i][num] = true;
				v[1][j][num] = true;
				v[2][3*(i/3)+(j/3)+1][num] = true;
			}
		}
		backtracking(0, 0);
	}
	static void backtracking(int x, int y) {
		if(flag) return;
		else if(x == 8 && y == 9) {flag = true; 
		for(int i=0; i<9; i++) {
			for(int j=0; j<9;j ++) {
				System.out.print(sdoku[i][j]);
			}
			System.out.println();
		}
		return;}
		else if(y == 9) backtracking(x+1, 0);
		else if(sdoku[x][y] != 0) backtracking(x, y+1);
		else {
			int group = 3*(x/3) + (y/3) + 1;
			for(int i=1; i<=9; i++) {
				if(!v[0][x][i] && !v[1][y][i] && !v[2][group][i]) {
					sdoku[x][y] = i;
					v[0][x][i] = true;
					v[1][y][i] = true;
					v[2][group][i] = true;
					backtracking(x, y+1);
					v[0][x][i] = false;
					v[1][y][i] = false;
					v[2][group][i] = false;
					sdoku[x][y] = 0;
				}
			}
		}
	}
}