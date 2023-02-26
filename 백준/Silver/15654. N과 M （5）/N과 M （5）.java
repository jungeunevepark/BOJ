import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] result;
	static int[] numbers;
	static boolean[] v;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numbers = new int[N];
		result = new int[M];
		v = new boolean[N];
		st = new StringTokenizer(bf.readLine(), " ");
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(numbers);
		perm(0);
	}
	static void perm(int cnt) {
		if(cnt == M) {
			for(int r : result) System.out.print(r+" ");
			System.out.println();
			return;
		}
		for(int i=0; i<N; i++) {
			if(v[i]) continue;
			v[i] = true;
			result[cnt] = numbers[i];
			perm(cnt+1);
			v[i] = false;
		}
	}
}