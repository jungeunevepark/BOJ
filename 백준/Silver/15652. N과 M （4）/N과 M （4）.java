import java.io.*;
import java.util.*;

public class Main {
	static int N, R;
	static int[] comb;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		comb = new int[R];
		comb(0, 0);
	}
	static void comb(int cnt, int start) {
		if(cnt == R) {
			for(int p : comb) System.out.print(p+" ");
			System.out.println();
			return;
			
			}
		for(int i=start; i<N; i++) {
			comb[cnt] = i+1;
			comb(cnt+1, i);
		}
		
	}
}