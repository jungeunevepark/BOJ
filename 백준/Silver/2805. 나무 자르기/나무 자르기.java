import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static long M;
	static long[] tree;
	static long result = 0;
    public static void main(String[] args) throws Exception {
       BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
       StringBuilder sb = new StringBuilder();
       StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
       N = Integer.parseInt(st.nextToken());
       M = Long.parseLong(st.nextToken());
       tree = new long[N]; long max = 0; long min = Long.MAX_VALUE;
       st = new StringTokenizer(bf.readLine(), " ");
       for(int i =0 ;i<N; i++) {
    	   tree[i] = Long.parseLong(st.nextToken());
    	   max = Math.max(max, tree[i]);
    	   min = Math.min(min, tree[i]);
       }
       cut(min, max);
       
    }
    static void cut(long start, long end) {
    	long min = 0; long max = end;
		while(min <= max) {
			long mid = (min + max) / 2;
			long sum = 0;
			for(long treeHeight : tree) {
				if(treeHeight - mid > 0) { 
					sum += (treeHeight - mid);
				}
			}
			if(sum < M) {
				max = mid-1;
			}
			else {
				min = mid + 1;
			}
		}
		System.out.println(max);
	}
}