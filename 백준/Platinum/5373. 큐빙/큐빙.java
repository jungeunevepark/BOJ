import java.io.*;
import java.util.*;

public class Main {
	static char[][][] cube;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(bf.readLine());
		for(int t=1; t<=T; t++) {
			cube = new char[][][]{{{'w', 'w', 'w'}, {'w', 'w', 'w'}, {'w', 'w', 'w'}}, {{'r', 'r', 'r'}, {'r', 'r', 'r'}, {'r', 'r', 'r'}}, {{'y', 'y', 'y'}, {'y', 'y', 'y'}, {'y', 'y', 'y'}}, {{'o', 'o', 'o'}, {'o', 'o', 'o'}, {'o', 'o', 'o'}}, {{'g', 'g', 'g'}, {'g', 'g', 'g'}, {'g', 'g', 'g'}}, {{'b', 'b', 'b'}, {'b', 'b', 'b'}, {'b', 'b', 'b'}}};
			int M = Integer.parseInt(bf.readLine());
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			for(int i=0; i<M; i++) {
				String s = st.nextToken();
				cubing(s.charAt(0), s.charAt(1));
			}
			for(int k=0; k<3; k++) {
				for(int j=0; j<3; j++) {
					sb.append(cube[0][k][j]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
	static void cubing(char S, char d) {
		if(S == 'L') { //2 변경 완
			char[] temp = new char[3];
			if(d == '+') {
				for(int i=0; i<3; i++)
					temp[i] = cube[3][i][2];
				// 2->3
				for(int i=0; i<3; i++)
					cube[3][i][2] = cube[2][2-i][0];
				// 1-> 2
				for(int i=0; i<3; i++)
					cube[2][i][0] = cube[1][i][0];
				// 0->1
				for(int i=0; i<3; i++) {
					cube[1][i][0] = cube[0][i][0];
				}
				// 3->0
				for(int i=0; i<3; i++)
					cube[0][2-i][0] = temp[i];
				
				
				char[][] tmp = new char[3][3];
				for(int x=0; x<3; x++)
					for(int y=0; y<3; y++)
						tmp[y][2-x] = cube[4][x][y];
				cube[4] = tmp;
				
			}
			else { // 2변경 완
				for(int i=0; i<3; i++)
					temp[i] = cube[0][i][0];
				// 1->0
				for(int i=0; i<3; i++) {
					cube[0][i][0] = cube[1][i][0];
				}
				// 2->1
				for(int i=0; i<3; i++)
					cube[1][i][0] = cube[2][i][0];
				// 3->2
				for(int i=0; i<3; i++)
					cube[2][i][0] = cube[3][2-i][2];
				// 0->3
				for(int i=0; i<3; i++)
					cube[3][2-i][2] = temp[i];
				
				char[][] tmp = new char[3][3];
				for(int x=0; x<3; x++)
					for(int y=0; y<3; y++)
						tmp[2-y][x] = cube[4][x][y];
				cube[4] = tmp;
			}
		}
		if(S == 'R') { // 2 변경 완
			char[] temp = new char[3];
			if(d == '-') {
				for(int i=0; i<3; i++)
					temp[i] = cube[3][i][0];
				// 2->3
				for(int i=0; i<3; i++)
					cube[3][2-i][0] = cube[2][i][2];
				// 1-> 2
				for(int i=0; i<3; i++)
					cube[2][i][2] = cube[1][i][2];
				// 0->1
				for(int i=0; i<3; i++) {
					cube[1][i][2] = cube[0][i][2];
				}
				// 3->0
				for(int i=0; i<3; i++)
					cube[0][2-i][2] = temp[i];
				
				
				char[][] tmp = new char[3][3];
				for(int x=0; x<3; x++)
					for(int y=0; y<3; y++)
						tmp[2-y][x] = cube[5][x][y];
				cube[5] = tmp;
			}
			else { // 2변경 완
				for(int i=0; i<3; i++)
					temp[i] = cube[0][i][2];
				// 1->0
				for(int i=0; i<3; i++) {
					cube[0][i][2] = cube[1][i][2];
				}
				// 2->1
				for(int i=0; i<3; i++)
					cube[1][i][2] = cube[2][i][2];
				// 3->2
				for(int i=0; i<3; i++)
					cube[2][2-i][2] = cube[3][i][0];
				// 0->3
				for(int i=0; i<3; i++)
					cube[3][2-i][0] = temp[i];
				
				char[][] tmp = new char[3][3];
				for(int x=0; x<3; x++)
					for(int y=0; y<3; y++)
						tmp[y][2-x] = cube[5][x][y];
				cube[5] = tmp;
			}
		}
		if(S == 'U') {
			int[] dir = {1, 4, 3, 5};
			char[] temp = new char[3];
			if(d == '+') {
				for(int i=0; i<3; i++)
					temp[i] = cube[dir[3]][0][i];
				for(int k=3; k>=1; k--) {
					for(int i=0; i<3; i++) {
						cube[dir[k]][0][i] = cube[dir[k-1]][0][i];
					}
				}
				for(int i=0; i<3; i++)
					cube[dir[0]][0][i] = temp[i];
				
				char[][] tmp = new char[3][3];
				for(int x=0; x<3; x++)
					for(int y=0; y<3; y++)
						tmp[y][2-x] = cube[0][x][y];
				cube[0] = tmp;
			}
			else {
				for(int i=0; i<3; i++)
					temp[i] = cube[dir[0]][0][i];
				for(int k=0; k<=2; k++) {
					for(int i=0; i<3; i++) {
						cube[dir[k]][0][i] = cube[dir[k+1]][0][i];
					}
				}
				for(int i=0; i<3; i++)
					cube[dir[3]][0][i] = temp[i];
				
				char[][] tmp = new char[3][3];
				for(int x=0; x<3; x++)
					for(int y=0; y<3; y++)
						tmp[2-y][x] = cube[0][x][y];
				cube[0] = tmp;
			}
		}
		if(S == 'D') {
			int[] dir = {1, 4, 3, 5};
			char[] temp = new char[3];
			if(d == '-') {
				for(int i=0; i<3; i++)
					temp[i] = cube[dir[3]][2][i];
				for(int k=3; k>=1; k--) {
					for(int i=0; i<3; i++) {
						cube[dir[k]][2][i] = cube[dir[k-1]][2][i];
					}
				}
				for(int i=0; i<3; i++)
					cube[dir[0]][2][i] = temp[i];
				
				char[][] tmp = new char[3][3];
				for(int x=0; x<3; x++)
					for(int y=0; y<3; y++)
						tmp[2-y][x] = cube[2][x][y];
				cube[2] = tmp;
			}
			else {
				for(int i=0; i<3; i++)
					temp[i] = cube[dir[0]][2][i];
				for(int k=0; k<=2; k++) {
					for(int i=0; i<3; i++) {
						cube[dir[k]][2][i] = cube[dir[k+1]][2][i];
					}
				}
				for(int i=0; i<3; i++)
					cube[dir[3]][2][i] = temp[i];
				
				char[][] tmp = new char[3][3];
				for(int x=0; x<3; x++)
					for(int y=0; y<3; y++)
						tmp[y][2-x] = cube[2][x][y];
				cube[2] = tmp;
			}
		}
		if(S == 'F') { // 2 변경 완
			char[] temp = new char[3];
			if(d == '+') {
				for(int j=0; j<3; j++)
					temp[j] = cube[4][j][2];
				// 2-> 4
				for(int i=0; i<3; i++)
					cube[4][i][2] = cube[2][0][i];
				// 5-> 2
				for(int i=0; i<3; i++)
					cube[2][0][2-i] = cube[5][i][0];
				// 0-> 5
				for(int i=0; i<3; i++)
					cube[5][i][0] = cube[0][2][i];
				// 4-> 0
				for(int i=0; i<3; i++)
					cube[0][2][2-i] = temp[i];
				
				char[][] tmp = new char[3][3];
				for(int x=0; x<3; x++)
					for(int y=0; y<3; y++)
						tmp[y][2-x] = cube[1][x][y];
				cube[1] = tmp;
				
			}
			else { // 2 변경 완
				for(int j=0; j<3; j++)
					temp[j] = cube[0][2][j];
				// 5-> 0
				for(int i=0; i<3; i++)
					cube[0][2][i] = cube[5][i][0];
				// 2-> 5
				for(int i=0; i<3; i++)
					cube[5][i][0] = cube[2][0][2-i];
				// 4-> 2
				for(int i=0; i<3; i++)
					cube[2][0][i] = cube[4][i][2];
				// 0-> 4
				for(int i=0; i<3; i++)
					cube[4][2-i][2] = temp[i];
				
				char[][] tmp = new char[3][3];
				for(int x=0; x<3; x++)
					for(int y=0; y<3; y++)
						tmp[2-y][x] = cube[1][x][y];
				cube[1] = tmp;
				
			}
		}
		if(S == 'B') { // 2변경 완
			char[] temp = new char[3];
			if(d == '-') {
				for(int j=0; j<3; j++)
					temp[j] = cube[4][j][0];
				// 2-> 4
				for(int i=0; i<3; i++)
					cube[4][i][0] = cube[2][2][i];
				// 5-> 2
				for(int i=0; i<3; i++)
					cube[2][2][2-i] = cube[5][i][2];
				// 0-> 5
				for(int i=0; i<3; i++)
					cube[5][i][2] = cube[0][0][i];
				// 4-> 0
				for(int i=0; i<3; i++)
					cube[0][0][2-i] = temp[i];
				
				char[][] tmp = new char[3][3];
				for(int x=0; x<3; x++)
					for(int y=0; y<3; y++)
						tmp[2-y][x] = cube[3][x][y];
				cube[3] = tmp;
				
			}
			else { // 2 변경 완
				for(int j=0; j<3; j++)
					temp[j] = cube[0][0][j];
				// 5-> 0
				for(int i=0; i<3; i++)
					cube[0][0][i] = cube[5][i][2];
				// 2-> 5
				for(int i=0; i<3; i++)
					cube[5][i][2] = cube[2][2][2-i];
				// 4-> 2
				for(int i=0; i<3; i++)
					cube[2][2][i] = cube[4][i][0];
				// 0-> 4
				for(int i=0; i<3; i++)
					cube[4][2-i][0] = temp[i];
				
				
				char[][] tmp = new char[3][3];
				for(int x=0; x<3; x++)
					for(int y=0; y<3; y++)
						tmp[y][2-x] = cube[3][x][y];
				cube[3] = tmp;
			}
			
		}
	}
}