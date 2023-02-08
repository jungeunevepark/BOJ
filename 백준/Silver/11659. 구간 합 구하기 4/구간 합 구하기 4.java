import java.util.*;
import java.io.*;

public class Main {
	static int[] d;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		d = new int[N+1];
		st = new StringTokenizer(bf.readLine(), " ");
		d[0] = 0;
		for(int i=1; i<N+1; i++) {
			d[i] = d[i-1] + Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			sb.append(-d[Integer.parseInt(st.nextToken())-1] + d[Integer.parseInt(st.nextToken())]).append("\n");
		}
		System.out.println(sb.toString());
	}
}