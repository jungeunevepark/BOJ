import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] di = { 1, 0, -1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static int[][] map;
	static int n;
	static int m;
	static int k;
	static int[][] ways;
	static int[] res;
	static boolean[] v;
	static int MIN;

	static void cal(int[][] arr) {
		for (int i = 0; i < n; i++) {
			int cnt = 0;
			for (int j = 0; j < m; j++) {
				cnt += arr[i][j];
			}
			MIN = Math.min(MIN, cnt);
		}
	}

	static void rot(int[][] arr, int s_x, int s_y, int val) {
		if (val == 1)
			return;
		int tmp = arr[s_x][s_y];
		int x = s_x;
		int y = s_y;
		int dir = 0;
		while (dir < 4) {
			int nx = x + di[dir];
			int ny = y + dj[dir];
			if (nx < s_x || s_x + val <= nx || n <= nx | ny < s_y || s_y + val <= ny || m <= ny) {
				dir++;
				continue;
			}
			arr[x][y] = arr[nx][ny];
			x = nx;
			y = ny;

		}
		arr[s_x][s_y + 1] = tmp;
		rot(arr, s_x + 1, s_y + 1, val - 2);
	}

	static void perm(int cnt) {
		if (cnt == k) {
			int[][] tmp = new int[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					tmp[i][j] = map[i][j];
				}
			}
			for (int i = 0; i < k; i++) {
				rot(tmp, ways[res[i]][0], ways[res[i]][1], ways[res[i]][2]);
//				for (int[] t : tmp)
//					System.out.println(Arrays.toString(t));
//				System.out.println();
			}
			cal(tmp);
			return;
		}
		for (int i = 0; i < k; i++) {
			if (v[i])
				continue;
			v[i] = true;
			res[cnt] = i;
			perm(cnt + 1);
			v[i] = false;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		ways = new int[k][3];
		res = new int[k];
		v = new boolean[k];
		MIN = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			ways[i] = new int[] { r - s - 1, c - s - 1, 2 * s + 1 };
		}
		perm(0);
		System.out.println(MIN);
	}
}