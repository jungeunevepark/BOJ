import java.io.*;
import java.util.*;

public class Main {
	static ArrayDeque<int[]> q;
	static int[][] map;
	static int[][] turn;
	static int N;
	static int[] dx = { 0, -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 0, -1, 1 };
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		q = new ArrayDeque<>(); // 별로야 배열로 합시다.
		turn = new int[N*N - 1][2];
		int[] dx = { 0, 1, 0, -1 };
		int[] dy = { 1, 0, -1, 0 };
		boolean[][] v = new boolean[N][N];
		int x = 0;
		int y = 0;
		int d = 0;
		v[x][y] = true;
		int count = 0;
		while (x != N / 2 || y != N / 2) {
			turn[count][0] = x;
			turn[count++][1] = y;
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx < 0 || ny < 0 || nx >= N || ny >= N || v[nx][ny]) {
				d = (d + 1) % 4;
				nx = x + dx[d];
				ny = y + dy[d];
			}
			x = nx;
			y = ny;
			v[x][y] = true;
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			blizzard(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		System.out.println(result);
	}

	static void blizzard(int d, int s) {
		int x = N / 2;
		int y = N / 2;
		for (int k = 1; k <= s; k++) {
			int nx = x + k*dx[d];
			int ny = y + k*dy[d];
			map[nx][ny] = 0;
		}
		push();
		while (true) {
			int count = 0; // 추가되는 점수
			int num = 0, cnt = 1; // 연속되는 숫자, 연속된 개수
			for (int i = N*N - 2; i >= 0; i--) {
				x = turn[i][0];
				y = turn[i][1];
				if (num != map[x][y]) {
					if (cnt >= 4) {
						for (int k = i + 1; k <= i + cnt; k++)
							map[turn[k][0]][turn[k][1]] = 0;
						for (int k = i + 1; k <= i + cnt; k++)
							map[turn[k][0]][turn[k][1]] = 0;
						count += num * cnt;
					}
					num = map[x][y];
					cnt = 1;
				} else
					cnt++;
			}
			if(cnt >= 4 && num != 0) {
				for (int k = 0; k < cnt; k++)
					map[turn[k][0]][turn[k][1]] = 0;
				count += num * cnt;
			}
			result += count;
			if (count == 0)
				break;
			push();
		}
		divide();
	}

	static void push() {
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		for (int k = 0; k < N*N - 1; k++) {
			int x = turn[k][0];
			int y = turn[k][1];
			if (map[x][y] == 0)
				continue;
			stack.push(map[x][y]);
		}
		for (int k = N*N - 2; k >= 0; k--) {
			int x = turn[k][0];
			int y = turn[k][1];
			if (stack.isEmpty())
				map[x][y] = 0;
			else
				map[x][y] = stack.pop();
		}
	}

	static void divide() {
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		int num = 0, cnt = 1; // 연속되는 숫자, 연속된 개수
		for (int i = 0; i < N*N - 1; i++) {
			int x = turn[i][0];
			int y = turn[i][1];
			if (map[x][y] == 0)
				continue;
			if (num != map[x][y]) {
				if (num != 0) {
					stack.push(num);
					stack.push(cnt);
				}
				num = map[x][y];
				cnt = 1;
			} else
				cnt++;
		}
		if(num != 0) {
			stack.push(num);
			stack.push(cnt);
		}
		for (int k = N*N - 2; k >= 0; k--) {
			int x = turn[k][0];
			int y = turn[k][1];
			if (stack.isEmpty())
				map[x][y] = 0;
			else
				map[x][y] = stack.pop();
		}
	}
}