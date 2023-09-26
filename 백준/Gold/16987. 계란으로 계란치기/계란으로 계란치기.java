import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static boolean[] v;
	static int[] S;
	static int[] W;
	static int result;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		S = new int[N];
		W = new int[N];
		v = new boolean[N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			S[i] = Integer.parseInt(st.nextToken());
			W[i] = Integer.parseInt(st.nextToken());
		}
		breaking(0);
		System.out.println(result);
		
	}
	static void breaking(int x) {
		if(x == N) {
			int cnt = 0;
			for(int i=0; i<N; i++)
				if(v[i]) cnt++;
			result = Math.max(result, cnt);
			return;
		}
		if(v[x]) {breaking(x+1); return;}
		int cnt = 0;
		for(int i=0; i<N; i++) {
			if(v[i] || x == i) continue;
			cnt++;
			S[i] -= W[x];
			S[x] -= W[i];
			if(S[i] <= 0) v[i] = true;
			if(S[x] <= 0) v[x] = true;
			breaking(x+1);
			v[i] = false;
			v[x] = false;
			S[i] += W[x];
			S[x] += W[i];
		}
		if(cnt == 0)
			breaking(x+1);
	}
}