import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[] guiter;
	static int sum, min;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		guiter = new int[N];
		st = new StringTokenizer(bf.readLine(), " ");
		sum =0; min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			sum += guiter[i] = Integer.parseInt(st.nextToken());
			min = Math.min(min, guiter[i]);
		}
		System.out.println(set());
	}
	static int set() {
		int low = min; int high = sum;
		while(low <= high) {
			int mid = (low+high) / 2;
			if(!blueray(mid)) low = mid+1;
			else {high = mid-1;}
		}
		return low;
	}
	static boolean blueray(int atleast) {
		int sum = 0; int cnt = 1;
		for(int i=0; i<N; i++) {
			if(guiter[i] > atleast) return false;
			sum += guiter[i];
			if(sum > atleast) {sum = guiter[i]; cnt++;}
		}
		if(cnt > M) return false; // 블루레이가 더 커져야 함!
		else return true;
	}
}