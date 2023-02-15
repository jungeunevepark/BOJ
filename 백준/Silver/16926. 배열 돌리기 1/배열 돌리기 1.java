import java.util.*;
import java.io.*;

public class Main {
	static int N, M, R;
	static int[][] A;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		A = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for(int j=0; j<M; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0; i<R; i++)
			rotate(0);
		for(int[] a: A) {for(int b: a) sb.append(b).append(" "); sb.append("\n");}
		System.out.println(sb);
	}
	static void rotate(int k) {
		if(k >= N-1-k || k >= M-1-k) return;
		int tmp = A[k][k];
		for(int j=k; j<M-k-1; j++)
			A[k][j] = A[k][j+1];
		for(int i=k; i<N-k-1; i++)
			A[i][M-1-k] = A[i+1][M-1-k];
		for(int j=M-k-1; j>k; j--)
			A[N-1-k][j] = A[N-1-k][j-1];
		for(int i=N-k-1; i>k+1; i--)
			A[i][k] = A[i-1][k];
		A[k+1][k] = tmp;
		rotate(k+1);
		
	}
}