import java.io.*;
import java.util.*;

public class Main {
	static int result;
	static int numbers[];
	static int pick[] = new int[3];
	static int N, M;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numbers = new int[N];
		st = new StringTokenizer(bf.readLine(), " ");
		for(int i=0; i<N; i++)
			numbers[i] = Integer.parseInt(st.nextToken());
		pick(0, 0);
		System.out.println(result);
	}
	static void pick(int cnt, int x) {
		if(cnt == 3) {
			int sum = 0;
			for(int p: pick) sum+=p;
			if(sum <= M && result < sum) result = sum;
			return;
		}
		for(int i = x; i<N; i++) {
			pick[cnt] = numbers[i];
			pick(cnt+1, i+1);
		}
	}
}