import java.io.*;
import java.util.*;
class step{
	char[][] temp;
	int time;
	int N, M;
	public step(char[][] temp, int time, int N, int M) {
		super();
		this.temp = temp;
		this.time = time;
		this.N = N;
		this.M = M;
	}
}
public class Main {
	static char[][] board;
	static int result = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		board = new char[10][10];
		for(int i=0; i<N; i++) {
			String s = bf.readLine();
			for(int j=0; j<M; j++)
				board[i][j] = s.charAt(j);
		}
		perm(0, board, N, M);
		if(result == Integer.MAX_VALUE) result = -1;
		System.out.println(result);
	}
	static void perm(int cnt, char[][] temp, int N, int M) {// bfs로 해야됨!!!!
		ArrayDeque<step> dir = new ArrayDeque<>();
		dir.offer(new step(temp, 1, N, M));
		while(!dir.isEmpty()) {
			step now = dir.poll();
			if(now.time > 10 || result !=Integer.MAX_VALUE) break;
			for(int i=0; i<4; i++) {
				char[][] n = new char[10][10];
				for(int x=0; x<10; x++) {
					for(int y=0; y<10; y++)
						n[x][y] = now.temp[x][y];
				}
				if(move(n, now.time, i, now.N, now.M)) {
					if(i<2)
						dir.offer(new step(n, now.time+1, now.M, now.N));
					else
						dir.offer(new step(n, now.time+1, now.N, now.M));
				}
			}
		}
	}
	static boolean move(char[][] temp, int time, int dir, int N, int M) {
		int re = 0;
		if(dir == 0) re = move_left(temp, N, M);
		if(dir == 1) re = move_right(temp, N, M);
		if(dir == 2) re = move_up(temp, N, M);
		if(dir == 3) re = move_down(temp, N, M);
		
		if(re == 1) result = time;
		if(re != -1) return true;
		
		return false;
	}
	static int move_left(char[][] temp, int N, int M) {
		boolean flag = false;
		char[][] n = new char[M][N];
		int rx = 0, ry = 0; int bx = 0, by = 0;
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				n[i][j] = temp[j][M-i-1];
				if(n[i][j] == 'R') {
					rx = i; ry = j;
				}
				if(n[i][j] == 'B') {
					bx = i; by = j;
				}
			}
		}
		if(ry == by && rx < bx) {
			int i = bx+1;
			n[bx][by] = '.';
			for(i=bx+1; i<M; i++) {
				if(n[i][by] == '#') {
					break;
				}
				else if(n[i][by] == 'O') {
					return -1;
				}
			}
			n[i-1][by] = 'B';

			i = rx+1;
			n[rx][ry] = '.';
			for(i=rx+1; i<M; i++) {
				if(n[i][ry] == '#' || n[i][ry] == 'B') {
					break;
				}
				else if(n[i][ry] == 'O') {
					return 1;
				}
			}
			n[i-1][ry] = 'R';
		}
		else {
			int i = rx+1;
			n[rx][ry] = '.';
			for(i=rx+1; i<M; i++) {
				if(n[i][ry] == '#') {
					break;
				}
				else if(n[i][ry] == 'O') {
					flag = true;
					break;
				}
			}
			if(!flag) n[i-1][ry] = 'R';
			
			i = bx+1;
			n[bx][by] = '.';
			for(i=bx+1; i<M; i++) {
				if(n[i][by] == '#' || n[i][by] == 'R') {
					break;
				}
				else if(n[i][by] == 'O') 
					return - 1;
			}
			n[i-1][by] = 'B';
		}
		for(int i=0; i<M; i++)
			for(int j=0; j<N; j++)
				temp[i][j] = n[i][j];
		if(flag) return 1;
		else return 0;
	}
	static int move_right(char[][] temp, int N, int M) {
		boolean flag = false;
		char[][] n = new char[M][N];
		int rx = 0, ry = 0; int bx = 0, by = 0;
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				n[i][j] = temp[N-1-j][i];
				if(n[i][j] == 'R') {
					rx = i; ry = j;
				}
				if(n[i][j] == 'B') {
					bx = i; by = j;
				}
			}
		}
		if(ry == by && rx < bx) {
			int i = bx+1;
			n[bx][by] = '.';
			for(i=bx+1; i<M; i++) {
				if(n[i][by] == '#') {
					break;
				}
				else if(n[i][by] == 'O') {
					return -1;
				}
			}
			n[i-1][by] = 'B';
			
			i = rx+1;
			n[rx][ry] = '.';
			for(i=rx+1; i<M; i++) {
				if(n[i][ry] == '#' || n[i][ry] == 'B') {
					break;
				}
				else if(n[i][ry] == 'O') {
					return 1;
				}
			}
			n[i-1][ry] = 'R';
		}
		else {
			int i = rx+1;
			n[rx][ry] = '.';
			for(i=rx+1; i<M; i++) {
				if(n[i][ry] == '#') {
					break;
				}
				else if(n[i][ry] == 'O') {
					flag = true;
					break;
				}
			}
			if(!flag) n[i-1][ry] = 'R';
			
			i = bx+1;
			n[bx][by] = '.';
			for(i=bx+1; i<M; i++) {
				if(n[i][by] == '#' || n[i][by] == 'R') {
					break;
				}
				else if(n[i][by] == 'O') {
					return - 1;
				}
			}
			n[i-1][by] = 'B';
		}
		for(int i=0; i<M; i++)
			for(int j=0; j<N; j++)
				temp[i][j] = n[i][j];
		if(flag) return 1;
		else return 0;
	}
	static int move_up(char[][] temp, int N, int M) {
		boolean flag = false;
		char[][] n = new char[N][M];
		int rx = 0, ry = 0; int bx = 0, by = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				n[i][j] = temp[N-1-i][M-j-1];
				if(n[i][j] == 'R') {
					rx = i; ry = j;
				}
				if(n[i][j] == 'B') {
					bx = i; by = j;
				}
			}
		}
		if(ry == by && rx < bx) {
			int i = bx+1;
			n[bx][by] = '.';
			for(i=bx+1; i<N; i++) {
				if(n[i][by] == '#') {
					break;
				}
				else if(n[i][by] == 'O') {
					return -1;
				}
			}
			n[i-1][by] = 'B';
			
			i = rx+1;
			n[rx][ry] = '.';
			for(i=rx+1; i<N; i++) {
				if(n[i][ry] == '#' || n[i][ry] == 'B') {
					break;
				}
				else if(n[i][ry] == 'O') {
					return 1;
				}
			}
			n[i-1][ry] = 'R';
		}
		else {
			int i = rx+1;
			n[rx][ry] = '.';
			for(i=rx+1; i<N; i++) {
				if(n[i][ry] == '#') {
					break;
				}
				else if(n[i][ry] == 'O') {
					flag = true;
					break;
				}
			}
			if(!flag) n[i-1][ry] = 'R';
			
			i = bx+1;
			n[bx][by] = '.';
			for(i=bx+1; i<N; i++) {
				if(n[i][by] == '#'|| n[i][by] == 'R') {
					break;
				}
				else if(n[i][by] == 'O') {
					return - 1;
				}
			}
			n[i-1][by] = 'B';
		}
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++)
				temp[i][j] = n[i][j];
		if(flag) return 1;
		else return 0;
	}
	static int move_down(char[][] temp, int N, int M) {
		boolean flag = false;
		int rx = 0, ry = 0; int bx = 0, by = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(temp[i][j] == 'R') {
					rx = i; ry = j;
				}
				if(temp[i][j] == 'B') {
					bx = i; by = j;
				}
			}
		}
		if(ry == by && rx < bx) {
			int i = bx+1;
			temp[bx][by] = '.';
			for(i=bx+1; i<N; i++) {
				if(temp[i][by] == '#') {
					break;
				}
				else if(temp[i][by] == 'O') {
					return -1;
				}
			}
			temp[i-1][by] = 'B';
			
			i = bx+1;
			temp[rx][ry] = '.';
			for(i=rx+1; i<N; i++) {
				if(temp[i][ry] == '#' || temp[i][ry] == 'B') {
					break;
				}
				else if(temp[i][ry] == 'O') {
					return 1;
				}
			}
			temp[i-1][ry] = 'R';
		}
		else {
			int i = rx+1;
			temp[rx][ry] = '.';
			for(i=rx+1; i<N; i++) {
				if(temp[i][ry] == '#') {
					break;
				}
				else if(temp[i][ry] == 'O') {
					flag = true;
					break;
				}
			}
			if(!flag) temp[i-1][ry] = 'R';
			
			i = bx+1;
			temp[bx][by] = '.';
			for(i=bx+1; i<N; i++) {
				if(temp[i][by] == '#'|| temp[i][by] == 'R') {
					break;
				}
				else if(temp[i][by] == 'O') {
					return - 1;
				}
			}
			temp[i-1][by] = 'B';
		}
		if(flag) return 1;
		else return 0;
	}
}