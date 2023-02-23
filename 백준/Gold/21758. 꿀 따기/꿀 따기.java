import java.io.*;
import java.util.*;

public class Main {
	static int[] honey;
	static int[] d;
	static boolean[] visited;
	static int N, h;
	static int[] comb;
	static int max;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(bf.readLine());
		honey = new int[N];
		visited = new boolean[N];
		d = new int[N];
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
			comb(0, 0);
		}
		System.out.println(max);
	}
	static void comb(int cnt, int start) {
		if(cnt == 2) {
			int sum = 0;
			if(comb[0] < h && h < comb[1]) {
				sum = (d[h] - d[comb[0]]) + (d[comb[1]-1] - d[h] + honey[h]);
			}
			else if(comb[0] > h) {
				sum = (d[comb[0]-1] - d[h] + honey[h]) + (d[comb[1]-1] - d[h] + honey[h] - honey[comb[0]]);		
			}
			else {
				sum = (d[h] - d[comb[0]] - honey[comb[1]]) + (d[h] - d[comb[1]]);								
			}
			max = Math.max(max, sum);
			return;
		}
		for(int i=start; i<N; i++) {
			if(i == h) continue;
			comb[cnt] = i;
			comb(cnt+1, i+1);
		}
	}
}