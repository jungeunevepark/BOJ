import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[][] game;
	static boolean [][] v;
	static int[] gone = new int[2];
	static int result;
	static int count, count_rain;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	  public static void main(String[] args) throws Exception {
	        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			game = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(bf.readLine(), " ");
			        	for(int j=0; j<N; j++) {
			        		game[i][j] = Integer.parseInt(st.nextToken());
			        		if(game[i][j] == 0) game[i][j] = 10;
			        	}
			        }
			while(true) {
		        score();
		        if(count < 2) break;
		        pop();
		        down();
		        sqare();
		        down();
	        }
			System.out.println(result);
	  }
	  static void sqare() {
		  int[][] temp = new int[N][N];
		  for(int i = 0; i<N; i++)
			  for(int j=0; j<N; j++)
				  temp[N-j-1][i] = game[i][j];
		  game = temp;
	  }
	  static void down() {
		  ArrayDeque<Integer> stack = new ArrayDeque<>();
		  for(int j=0; j<N; j++) {
			  for(int i=0; i<N; i++) {
				  if(game[i][j] == -1) {
					  for(int k=i-1; k>=0; k--) {
						  if(stack.isEmpty()) break;
						  game[k][j] = stack.pop();
					  }
				  }
				  else if(game[i][j] != 0) {
					  stack.push(game[i][j]);
					  game[i][j] = 0;
				}
			  }
			  for(int i=N-1; i>=0; i--) {
				  if(stack.isEmpty()) break;
				  game[i][j] = stack.pop();
			  }
		  }
	  }
	  static void pop() {
		  ArrayDeque<int[]> q = new ArrayDeque<>();
		  v= new boolean[N][N];
		  int block = game[gone[0]][gone[1]];
		  v[gone[0]][gone[1]] = true;
		  q.offer(new int[] {gone[0], gone[1]});
		  int cnt = 1;
		  while(!q.isEmpty()) {
			  int[] now = q.poll();
			  game[now[0]][now[1]] = 0;
			  for(int d=0; d<4; d++) {
				  int nx = now[0] + dx[d];
				  int ny = now[1] + dy[d];
				  if(nx < 0 || ny < 0 || nx >= N || ny >= N || v[nx][ny]) continue;
				  if(game[nx][ny] != block && game[nx][ny] != 10) continue;
				  v[nx][ny] = true;
				  q.offer(new int[] {nx, ny}); game[nx][ny] = 0;
			  }
		  }
		  result += count*count;
	  }
	  static void score() {
		  v = new boolean[N][N]; count = 0; count_rain = 0;
		  for(int i=0; i<N; i++) {
			  for(int j=0; j<N; j++) {
				  if(!v[i][j] && game[i][j] > 0 && game[i][j] != 10)
					  bfs(i, j);
			  }
		  }
	  }
	  static void bfs(int x, int y) {
		  ArrayDeque<int[]> q = new ArrayDeque<>();
		  v[x][y] = true;
		  boolean[][] visited = new boolean[N][N];
		  visited[x][y] = true;
		  q.offer(new int[] {x, y});
		  int cnt = 1; int cnt_rain = 0;
		  while(!q.isEmpty()) {
			  int[] now = q.poll();
			  for(int d=0; d<4; d++) {
				  int nx = now[0] + dx[d];
				  int ny = now[1] + dy[d];
				  if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;
				  if(game[nx][ny] != game[x][y] && game[nx][ny] != 10) continue;
				  v[nx][ny] = true; visited[nx][ny] = true;
				  q.offer(new int[] {nx, ny}); cnt++;
				  if(game[nx][ny] == 10) cnt_rain++;
			  }
		  }
		  if(cnt > count) {
			  count = cnt; gone[0] = x; gone[1] = y; count_rain = cnt_rain;
		  }
		  else if(cnt == count && count_rain < cnt_rain) {
			  count = cnt; gone[0] = x; gone[1] = y; count_rain = cnt_rain;
		  }
		  else if(cnt == count && count_rain == cnt_rain && gone[0] < x) {
			  count = cnt; gone[0] = x; gone[1] = y; count_rain = cnt_rain;
		  }
		  else if(cnt == count && count_rain == cnt_rain && gone[0] == x && gone[1] < y) {
			  count = cnt; gone[0] = x; gone[1] = y; count_rain = cnt_rain;
		  }
	  }
}