import java.io.*;
import java.util.*;

public class Main {
	static int[] honey;
	static long[] d;
	static boolean[] visited;
	static int N, h;
	static int[] comb;
	static long max;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(bf.readLine());
		honey = new int[N];
		visited = new boolean[N];
		d = new long[N];
		comb = new int[2];
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		honey[0] = Integer.parseInt(st.nextToken());
		d[0] = honey[0];
		for(int i=1; i<N; i++) {
			honey[i] = Integer.parseInt(st.nextToken());
			d[i] = d[i-1]+honey[i];
		}
		for(int i=0; i<N; i++) {
			h = i;
			// 양 끝
			if(h != 0 && h != N-1) {
				comb[0] = 0; comb[1] = N-1;
				curc1();
				// 계산
			}
			// 가장 왼쪽 + 왼쪽 부근
			if(h != 0 && h != 1) {
				comb[0] = 0;
				for(int k=1; k<h; k++) {
					comb[1] = k;
					curc3();
				}
			}
			// 가장 오른쪽 + 오른쪽 부근
			if(h != N-1 && h != N-2) {
				comb[1] = N-1;
				for(int k = h+1; k<N-1; k++) {
					comb[0] = k;
					curc2();
				}
			}
		}
		System.out.println(max);
	}
	static void curc1() {
		long sum = (d[h] - d[comb[0]]) + (d[comb[1]-1] - d[h] + honey[h]);
		max = Math.max(max, sum);
	}
	static void curc2() {
		long sum = (d[comb[0]-1] - d[h] + honey[h]) + (d[comb[1]-1] - d[h] + honey[h] - honey[comb[0]]);		
		max = Math.max(max, sum);
	}
	static void curc3() {
		long sum = (d[h] - d[comb[0]] - honey[comb[1]]) + (d[h] - d[comb[1]]);
		max = Math.max(max, sum);
	}
}