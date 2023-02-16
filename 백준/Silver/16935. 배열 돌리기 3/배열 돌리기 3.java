import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] A;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		StringBuilder sb= new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int max = Math.max(N, M);
		A = new int[max][max];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for(int j=0; j<M; j++)
				A[i][j] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(bf.readLine(), " ");
		for(int i=0; i<R; i++) {
			rotate(Integer.parseInt(st.nextToken()));
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++)
				sb.append(A[i][j]).append(" ");
			sb.append("\n");
		}
		System.out.println(sb);
	}
	private static void rotate(int num) {
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		if(num == 1) {
			for(int j=0; j<M; j++) {
				for(int i=0; i<N; i++)
					stack.push(A[i][j]);
				for(int i=0; i<N; i++)
					A[i][j] = stack.pop();
			}
		}
		else if(num == 2) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++)
					stack.push(A[i][j]);
				for(int j=0; j<M; j++)
					A[i][j] = stack.pop();
			}
		}
		else if(num == 3) {
			int[][] temp = new int[M][N];
			for(int i=0; i<N; i++)
				for(int j=0; j<M; j++)
					temp[j][N-i-1] = A[i][j];
			int tmp = N;
			N = M;
			M = tmp;
			for(int i=0; i<N; i++)
				for(int j=0; j<M; j++)
					A[i][j] = temp[i][j];
			
		}
		else if(num == 4) {
			int[][] temp = new int[M][N];
			for(int i=0; i<N; i++)
				for(int j=0; j<M; j++)
					temp[M-j-1][i] = A[i][j];
			int tmp = N;
			N = M;
			M = tmp;
			for(int i=0; i<N; i++)
				for(int j=0; j<M; j++)
					A[i][j] = temp[i][j];
		}
		else if(num == 5) {
			int[][] temp = new int[N/2][M/2];
			for(int i=0; i<N/2; i++)
				for(int j=0; j<M/2; j++)
					temp[i][j] = A[i][j];
			move(N/2, 0, 0, 0);
			move(N/2, M/2, N/2, 0);
			move(0, M/2, N/2, M/2);
			for(int i=0; i<N/2; i++)
				for(int j=0; j<M/2; j++)
					A[i][j+M/2] = temp[i][j];
		}
		else if(num == 6) {
			int[][] temp = new int[N/2][M/2];
			for(int i=0; i<N/2; i++)
				for(int j=0; j<M/2; j++)
					temp[i][j] = A[i][j];
			move(0, M/2, 0, 0);
			move(N/2, M/2, 0, M/2);
			move(N/2, 0, N/2, M/2);
			for(int i=0; i<N/2; i++)
				for(int j=0; j<M/2; j++)
					A[i+N/2][j] = temp[i][j];
		}
	}
	static void move(int x, int y, int nx, int ny) {
		int[][] temp = new int[N/2][M/2];
		for(int i=0; i<N/2; i++)
			for(int j=0; j<M/2; j++)
				temp[i][j] = A[i+x][j+y];
		for(int i=0; i<N/2; i++)
			for(int j=0; j<M/2; j++)
				A[i+nx][j+ny] = temp[i][j];
	}
}