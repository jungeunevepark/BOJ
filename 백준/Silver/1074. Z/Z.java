import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int r, c;
	static int cnt = -1;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		find(0, 0, N);
	}
	static void find(int x, int y, int N) {
		if(N == 0) {
			cnt++;
			if(x == r && y == c) System.out.println(cnt);
			return;
		}
		if(r < x+(int)Math.pow(2, N-1) && c < y+(int)Math.pow(2, N-1)) {find(x, y, N-1);}
		else if(r < x+(int)Math.pow(2, N-1) && c >= y+(int)Math.pow(2, N-1)) {
			cnt += (int)Math.pow(4, N-1); find(x, y+(int)Math.pow(2, N-1), N-1);}
		else if(r >= x+(int)Math.pow(2, N-1) && c < y+(int)Math.pow(2, N-1)) {
			cnt += 2*(int)Math.pow(4, N-1);
			find(x+(int)Math.pow(2, N-1), y, N-1);}
		else {
			cnt += 3*(int)Math.pow(4, N-1);
			find(x+(int)Math.pow(2, N-1), y+(int)Math.pow(2, N-1), N-1);}
	}
}