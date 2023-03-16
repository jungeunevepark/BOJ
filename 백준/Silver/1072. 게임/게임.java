import java.util.*;
import java.io.*;

public class Main {
	static long x;
	static long y;
	public static void main(String[] args) throws Exception {
	   BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	   StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
	   x = Long.parseLong(st.nextToken());
	   y = Long.parseLong(st.nextToken());
	   long k = 0;
	   System.out.println((k = find(x))==x+1?-1:k);
	}
	static long find(long cnt) {
		long start = 0; long end = cnt;
		while(start<=end) {
			long mid = (start+end)/2;
			if(check(mid)) end = mid - 1;
			else start = mid+1;
		}
		return start;
	}
	static boolean check(long mid) {
		if(account(x, y) != account(x+mid, y+mid)) return true;
		return false;
	}
	static long account(long x, long y) {
		double c = (double)y*100/x;
		return (long)c;
	}
}