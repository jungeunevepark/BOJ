import java.util.*;
import java.io.*;

public class Main {
	static int C, N;
	static long[] wifi;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		wifi = new long[N];
		for(int i=0; i<N; i++)
			wifi[i] = Long.parseLong(bf.readLine());
		Arrays.sort(wifi);
		find(wifi[N-1] - wifi[0]);
	}
	static void find(long x) {
		long start = 0; long end = x;
		while(start <= end) {// 두 값이 같아지는 경우 끝나게 됨
			long mid = (start + end) / 2; // 공유기 사이의 가능한 거리
			if(find_dist(mid)) start = mid+1;
			else end = mid - 1;
		}
		System.out.println(start-1);
	}
	static boolean find_dist(long m) {
		int start = 0; int cnt = 1;
		for(int i=0; i<N; i++) {
			if(wifi[i] - wifi[start] >= m) {start = i; cnt++;}
		}
		if(cnt < C) return false;
		else return true;
	}
}