import java.util.*;
import java.io.*;

public class Main {
	static int N, S, C;
	static int[] numbers;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		numbers = new int[N];
		st = new StringTokenizer(bf.readLine()," ");
		for(int i=0; i<N; i++)
			numbers[i] = Integer.parseInt(st.nextToken());
		C = 0;
		subs(0, 0);
		if(S == 0) C--;
		System.out.println(C);
	}
	static void subs(int cnt, int sum) {
		if(cnt == N) {
			if(sum == S) C++;
			return;
		}
		subs(cnt+1, sum+numbers[cnt]);
		subs(cnt+1, sum);
	}
}