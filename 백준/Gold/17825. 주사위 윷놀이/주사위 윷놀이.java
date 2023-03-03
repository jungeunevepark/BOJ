import java.util.*;
import java.io.*;

public class Main {
	static int[] dice;
	static int[][] hores;
	static int result;
	static int[][] map = new int[5][];
	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};
	public static void main(String[] args) throws Exception {
	   BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	   dice = new int[10];
	   hores = new int[4][2];
	   map[0] = new int[] {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40};
	   map[1] = new int[] {0, 13, 16, 19};
	   map[2] = new int[] {0, 22, 24};
	   map[3] = new int[] {0, 28, 27, 26};
	   map[4] = new int[] {0, 25, 30, 35};
	   StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
	   for(int i=0; i<10; i++)
		   dice[i] = Integer.parseInt(st.nextToken());
	   move(0, 0);
	   System.out.println(result);
	}
	static void move(int cnt, int sum) {
		if(cnt == 10) {
			if(result < sum) result = sum;
			return;
		}
		c: for(int i=0; i<4; i++) { // 위치 인덱스, 맵 선택 인덱스
			if(hores[i][0] == 'e') continue;
			int x = hores[i][0]; int m = hores[i][1];
			if(hores[i][1] == 0 && hores[i][0] % 5 == 0 && hores[i][0] < 20) {
				hores[i][1] = hores[i][0] / 5; hores[i][0] = 0;
			}
			hores[i][0] += dice[cnt];
			if(hores[i][1] == 0 && hores[i][0] >= map[0].length)
				hores[i][0] = 'e';
			if(hores[i][1] > 0 && hores[i][1] < 4 && hores[i][0] >= map[hores[i][1]].length) {
				hores[i][0] = hores[i][0] - map[hores[i][1]].length + 1;
				hores[i][1] = 4;
			}
			if(hores[i][1] == 4 && hores[i][0] >= map[hores[i][1]].length) {
				hores[i][0] = hores[i][0] - map[hores[i][1]].length;
				hores[i][1] = 0;
				if(hores[i][0] > 0) hores[i][0] = 'e';
				else hores[i][0] = 20;
			}
			if(hores[i][0] != 'e') {
				for(int k=0; k<4; k++)
					if(k == i) continue;
					else if(hores[k][0] == hores[i][0] && hores[k][1] == hores[i][1]) {
						hores[i][0] = x; hores[i][1] = m;
						continue c;
				}
			}
			if(hores[i][0] == 'e')
				move(cnt+1, sum);
			else
				move(cnt+1, sum+map[hores[i][1]][hores[i][0]]);
			hores[i][0] = x; hores[i][1] = m;
		}
	}
}