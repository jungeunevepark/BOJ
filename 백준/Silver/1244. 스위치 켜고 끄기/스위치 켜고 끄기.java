import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] onoff;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(bf.readLine());
		onoff = new int[N+1];
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		for(int i=1; i<=N; i++)
			onoff[i] = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(bf.readLine());
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(bf.readLine(), " ");
			if (Integer.parseInt(st.nextToken()) == 1)
				boy(Integer.parseInt(st.nextToken()));
			else
				girl(Integer.parseInt(st.nextToken()));
		}
		for(int i=1; i<=N; i++) {
			sb.append(onoff[i] % 2);
			if(i % 20 == 0) sb.append("\n");
			else if(i != N) sb.append(" ");
		}
		System.out.println(sb.toString());
	}
	static void girl(int idx) {
		int len = 1;
		while(true) {
			if(idx+len >N || idx-len <=0) break;
			if(onoff[idx-len] != onoff[idx+len])
				break;
			len++;
		}
		for(int i = idx-len + 1; i<idx+len; i++)
			onoff[i] = (onoff[i] + 1) %2;
	}
	static void boy(int idx) {
		for(int i=idx; i<=N; i+=idx) {
			onoff[i] = (onoff[i] + 1) %2;
		}
		
	}
}
