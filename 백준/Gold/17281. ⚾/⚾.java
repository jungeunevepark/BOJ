import java.io.*;
import java.util.*;

public class Main {
	static int inning = 1;
	static int[][] scores;
	static boolean[] v;
	static int[] p;
	static int result;
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		p = new int[9];
		v = new boolean[9];
		p[3] = 0; v[0] = true;
		scores = new int[N][9];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			for(int j=0; j<9; j++)
				scores[i][j] = Integer.parseInt(st.nextToken());
		}
		perm(0);
		System.out.println(result);
	}
	static void perm(int cnt) {
		if(cnt == 9) {
			game();
			return;
		}
		if(cnt == 3) {perm(4);}
		else {
			for(int i=1; i<9; i++) {
				if(v[i]) continue;
				v[i] = true;
				p[cnt] = i;
				perm(cnt+1);
				v[i] = false;
			}
		}
	}
	static void game() {
		int score = 0; int hit = 0;
		for(int start = 0; start < N; start++) {
			int out = 0;
			int[] run = new int[3];
			while(out < 3) {
				if(scores[start][p[hit % 9]] == 0) {out++;}
				if(scores[start][p[hit % 9]] == 1) {
					score += run[2];
					for(int i=2; i>0; i--)
						run[i] = run[i-1];
					run[0] = 1;
				}
				if(scores[start][p[hit % 9]] == 2) {
					for(int i=1; i<3; i++)
						score += run[i];
					run[2] = run[0];
					run[1] = 1;
					run[0] = 0;
				}
				if(scores[start][p[hit % 9]] == 3) {
					for(int i=0; i<3; i++)
						score += run[i];
					run = new int[3];
					run[2] = 1;
					
				}
				if(scores[start][p[hit % 9]] == 4) {
					for(int i=0; i<3; i++)
						score += run[i];
					score += 1;
					run = new int[4];
				}
				hit++;
			}
		}
		result = Math.max(result, score);
	}
}