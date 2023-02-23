import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		boolean[] v = new boolean[100001];
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {N, 0});
		v[N] = true;
		while(!q.isEmpty()) {
			int[] x = q.poll();
			if(x[0] == M) {System.out.println(x[1]); break;}
			if(x[0]-1 >= 0 && !v[x[0]-1]) {v[x[0]-1] = true; q.offer(new int[] {x[0]-1, x[1]+1});}
			if(x[0]+1 < 100001 && !v[x[0]+1]) {v[x[0]+1] = true; q.offer(new int[] {x[0]+1, x[1]+1});}
			if(2*x[0] < 100001 && !v[2*x[0]]) {v[2*x[0]] = true; q.offer(new int[] {2*x[0], x[1]+1});}
		}
	}
}