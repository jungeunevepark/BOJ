import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] permutation;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		permutation = new int[M];
		perm(0);
		System.out.println(sb.toString());
	}
	static void perm(int count) {
		if(count == M) {
			for(int i=0; i<M; i++) {
				sb.append(permutation[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		for(int i=0; i<N; i++) {
			permutation[count] = i+1;
			perm(count+1);
		}
	}
}