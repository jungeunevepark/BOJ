import java.util.*;
import java.io.*;

public class Main {
	static long[] lan;
	static int K, N;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		lan = new long[K];
		long max = 0;
		for(int i=0; i<K; i++) {
			lan[i] = Integer.parseInt(bf.readLine());
			max = Math.max(max, lan[i]);
		}
		find(0, max);
	}
	static void find(long start, long end) {
		long min = start; long max = end;
		while(min <= max) {
			long mid = (min+max) / 2;
			if(cut(mid)) min = mid+1;
			else max = mid -1;
		}
		System.out.println(min-1);
	}
	static boolean cut(long h) {
		if(h == 0) return true;
		long count = 0;
		for(int i=0; i<K; i++)
			count += (lan[i] / h);
		if(count >= N) return true;
		else return false;
	}
}