import java.io.*;
import java.util.*;

public class Main {
	static int[][] scaf; // 스카프의 유무를 표현할 배열
	static int result; // 결과값을 담을 변수
	static boolean[][] v; // bfs에 사용되는 visited 배열
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0}; // 4방 탐색을 위한 배열
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(bf.readLine());
		scaf = new int[101][101]; // 가로 세로가 100인 흰천위에 스카프를 놓기 때문에 크기를 101으로 설정한다.
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			make(x, y);
		} // x, y를 입력받아서 스카프가 놓이는 곳은 1로 표현한다.
		
		/*
		 * 로직은 다음과 같다.
		 * 먼저 스카프가 놓여있는 곳을 확인한다.(확인되지 않은 곳을 체크할것이므로 v를 통해 check한다.)
		 * bfs를 통해 상하좌우 인접된 곳에 놓인 스카프까지 확인해준다.(check 함수)
		 * 
		 * */
		v= new boolean[101][101];
		for(int i=0; i<101; i++) {
			for(int j=0; j<101; j++) {
				if(scaf[i][j] == 1 && !v[i][j]) { // 스카프가 놓여있고, 아직 확인하지 않은 곳만
					count(i, j); // BFS로 검사해준다.
				}
			}
		}
		sb.append(result); // 출력
		System.out.println(sb);
		bf.close();
	}
	static void count(int i, int j) {
		/*
		 * BFS로 (i, j)와 연결되어 있고 스카프가 놓여있는 부분을 전부 탐색한다.
		 * 이때 둘레는 스카프의 가장 자리 칸에서 0과 연결된 방향의 개수와 동일하다.(가장 자리 칸의 개수가 아님!)
		 * 그러므로 스카프의 둘레는 0과 연결된 방향의 개수와 동일하다.
		 * 함수를 통해 개수를 구해서 result를 갱신해주자.
		 * 
		 * */
		ArrayDeque<int[]> q = new ArrayDeque<>();
		v[i][j] = true;
		q.offer(new int[] {i, j});
		while(!q.isEmpty()) { // q에 들어오는 것은 스카프가 놓여있는 영역 뿐이다.
			int[] now = q.poll(); // 검사할 곳을 Deque에서 꺼낸다.
			int x = now[0];
			int y = now[1];
			for(int d = 0; d<4; d++) { // 우리는 연결된 스카프의 둘레만 측정할 것이다.
				int nx = x+dx[d];
				int ny = y+dy[d]; // 검사할 방향을 nx, ny에 담는다.
				if(v[nx][ny]) continue;
				if(scaf[nx][ny] == 0) {result++; continue;} // 상하좌우를 탐색하여 스카프가 놓여있지 않은 부분과 연결되는 곳의 길이를 측정해야 된다.
				// v를 통해서 겹치는 부분은 탐색하지 않기 때문에 중복될 경우는 없다.
				v[nx][ny] = true;
				q.offer(new int[] {nx, ny});
			}
		}
	}
	static void make(int x, int y) {
		/*
		 * 스카프의 위치를 받아서 놓여있는 곳의 배열 값을 1로 바꿔주는 함수
		 * */
		for(int i=x; i<x+10; i++)
			for(int j=y; j<y+10; j++)
				scaf[i][j] = 1;
	}
}