import java.util.*;
import java.io.*;

/**
 * 문제 설명: N*N 크기의 격자에 국가가 존재하고 각 칸에는 나라가 존재한다.
 * 나라 간 인구수의 차이가 L이상 R이하라면 국경선을 넘어 인구 이동이 가능하다.
 * 이때 인구의 이동은 (연결된 나라의 총 인구수) / (연결된 나라의 수)가 될때까지 이동한다.
 * 더 이상 이동할 수 없을 때까지 인구 이동은 몇 번 일어나는 지 구하시오.
 */
class Node {
	public int x;
	public int y;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int N, L, R;
	static int[][] A;
	static boolean[][] visited;
	static int[] di = { 0, 0, -1, 1 };
	static int[] dj = { 1, -1, 0, 0 };
	static int sum, cnt;
	static ArrayDeque<int[]> move;
	public static void main(String[] args) throws Exception {
		// N, L, R 입력 받음
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	    String[] line = bf.readLine().split(" ");
	    N = Integer.valueOf(line[0]);
	    L = Integer.valueOf(line[1]);
	    R = Integer.valueOf(line[2]);

		// 나라 배열 A 선언
		// 각 나라의 인구수를 값으로 하는 배열
		A = new int[N][N];

		// A 값 할당: i=행, j=열
		for (int i = 0; i < N; i++) {
			line = bf.readLine().split(" ");
			for (int j = 0; j < N; j++)
				A[i][j] = Integer.valueOf(line[j]);
		}

		int result = 0; // 인구 이동 횟수
		while (true) {
			if (migration() == false)
				break;
			result++;
		}
		System.out.println(result);
		bf.close();
	}
	static void bfs(int i, int j) {
		// 그룹을 찾는데 쓰는 queue
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.add(new int[] {i, j});
		
		move.add(new int[] {i, j});
		visited[i][j] = true;
		boolean flag = false; // return 값 정의
		
		// 더 이상 그룹에 들어올 나라가 없는 경우 while문 종료
		while (!q.isEmpty()) {
			int[] p = q.poll();

			for (int d = 0; d < 4; d++) {
				// 상, 하, 좌, 우 조사
				int ni = p[0] + di[d];
				int nj = p[1] + dj[d];

				// 주어진 땅 크기를 넘어가거나 이미 확인을 마친 나라의 경우 넘어감
				if (ni < 0 || nj < 0 || ni >= N || nj >= N || visited[ni][nj])
					continue;

				// 인구 차이를 조사하여 국경선을 열지 말지 결정
				if (Math.abs(A[p[0]][p[1]] - A[ni][nj]) >= L && Math.abs(A[ni][nj] - A[p[0]][p[1]]) <= R) {
					visited[ni][nj] = true;
					sum += A[ni][nj];
					cnt++;

					// 방문 표시를 하고, 다음 조사할 나라로 queue에 넣어줌
					q.add(new int[] {ni, nj});
					move.add(new int[] {ni, nj});
				}
			}
		}
	}
	static void dfs(int i, int j) {
		visited[i][j] = true;
		move.offer(new int[] {i, j});
		for (int d = 0; d < 4; d++) {
			// 상, 하, 좌, 우 조사
			int ni = i + di[d];
			int nj = j + dj[d];
			
			// 주어진 땅 크기를 넘어가거나 이미 확인을 마친 나라의 경우 넘어감
			if (ni < 0 || nj < 0 || ni >= N || nj >= N || visited[ni][nj])
				continue;
			
			// 인구 차이를 조사하여 국경선을 열지 말지 결정
			if (Math.abs(A[i][j] - A[ni][nj]) >= L && Math.abs(A[ni][nj] - A[i][j]) <= R) {
				visited[ni][nj] = true;
				move.offer(new int[] {ni, nj});
				dfs(ni, nj); sum+= A[ni][nj]; cnt++;
			}
		}
	}
	static boolean migration() {
		/*
		 * 상, 하, 좌, 우 인접한 나라와 인구수를 비교해 국경선이 열려있는 경우 나라 간 인구 이동을 시행하는 함수
		 * 
		 * Return
		 * int : 이주를 하는 경우 true, 안하는 경우 false을 반환
		 */
		boolean flag = false;
		visited = new boolean[N][N]; // 그룹에 속해 인구 이동을 완료한 경우 1로 표시
		move = new ArrayDeque<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// visited가 1이면 이미 그룹에 속해 이동을 마친 나라이므로 넘어간다.
				if (visited[i][j])
					continue;
				sum  = A[i][j]; cnt = 1;
				dfs(i, j);
				if(cnt != 1) flag = true;
				while (!move.isEmpty()) {
					int[] np = move.poll();
					A[np[0]][np[1]] = sum/cnt;
					
				} // 인구 이동
			}
		}
		return flag;
	}
	// migration end
}