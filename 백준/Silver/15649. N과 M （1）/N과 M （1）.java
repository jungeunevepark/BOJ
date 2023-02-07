import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] visited;
	static int[] permutation;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new int[N];
		permutation = new int[M];
		pem(0);
	}
	static void pem(int count) {
		if(count == M) {
			for(int i=0; i<M; i++)
				System.out.print(permutation[i] + " ");
			System.out.println();
			return;
		}
		for(int i=0; i<N; i++) {
			if(visited[i] != 0) continue;
			visited[i] = 1;
			permutation[count] = i+1;
			pem(count+1);
			visited[i] = 0;
			
		}
	}

}