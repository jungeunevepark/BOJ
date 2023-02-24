import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] maze;
	static int[][] result;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static boolean[][] v;
	static List<Integer> group;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maze = new int[N][M];
		result = new int[N][M];
		group = new ArrayList<>();
		for(int i=0; i<N; i++) {
			String s = bf.readLine();
			for(int j=0; j<M; j++)
				maze[i][j] = s.charAt(j) - '0';
		}
		v = new boolean[N][M];
		int count = 0;
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++) {
				if(v[i][j] || maze[i][j] == 1) continue;
				bfs(i, j, count++);
			}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(maze[i][j] == 0) sb.append(0);
				else {
					boolean[] visited = new boolean[count];
					for(int d=0; d<4; d++) {
						int ni = i+dx[d];
						int nj = j+dy[d];
						if(ni < 0 || nj <0 || ni >= N || nj >= M || maze[ni][nj] == 1 || visited[result[ni][nj]]) continue;
						visited[result[ni][nj]] = true;
						result[i][j] += group.get(result[ni][nj]);
					}
					sb.append((result[i][j]+1) % 10);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	static void bfs(int x, int y, int num) {
		int count = 1;
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		v[x][y] = true;
		while(!q.isEmpty()) {
			int[] now = q.poll();
			result[now[0]][now[1]] = num;
			for(int d =0; d<4; d++) {
				int nx = now[0] + dx[d];
				int ny = now[1] + dy[d];
				if(nx < 0 || ny < 0 || nx >= N || ny >= M || v[nx][ny] || maze[nx][ny] == 1) continue;
				v[nx][ny] = true; count++;
				q.offer(new int[] {nx, ny});
			}
		}
		group.add(count);
	}

}