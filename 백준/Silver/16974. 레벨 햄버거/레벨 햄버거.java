import java.io.*;
import java.util.*;

public class Main {
	static long[][] d; // 패티 개수
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		d = new long[51][2];
		int level = Integer.parseInt(st.nextToken());
		long X = Long.parseLong(st.nextToken());
		d[0][0] = 1; d[0][1] = 1;
		for(int i=1; i<=level; i++) {
			d[i][0] = 2*d[i-1][0] + 1; // 패티 수
			d[i][1] = 2*d[i-1][1] + 3; // 총 번과 패티 수
		}
		System.out.println(find(X, level));
	}
	static long find(long cnt, int level) {
		if(cnt == 0) return 0;
		if(d[level][1] <= cnt) return d[level][0];
		else if(d[level-1][1] < cnt - 2) return d[level-1][0] + 1 +find(cnt-d[level-1][1]-2, level-1);
		else if(d[level-1][1] == cnt - 2) return d[level-1][0] + 1;
		else if (d[level-1][1] == cnt -1) return d[level-1][0];
		else return find(cnt-1, level-1);
	}
}