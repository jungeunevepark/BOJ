import java.util.*;
import java.io.*;
class player{
	int x;
	int y;
	int d;
	public player(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}
}

public class Main {
	static int N;
	static int K;
	static int[][] map;
	static List<player> players;
	static ArrayDeque<Integer>[][] p;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		p = new ArrayDeque[N][N];
		players = new ArrayList<>();
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				p[i][j] = new ArrayDeque<>();
			}
		}
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			player temp = new player(x, y, d);
			players.add(temp);
			p[x][y].add(i);
		}
		int round;
		for(round = 1; round<=1000; round++) {
			if(move_player() == 1) break;
		}
		if(round > 1000) System.out.println(-1);
		else			  System.out.println(round);
	}
	static int move_player(){
		int size = players.size();
		for(int i=0; i<K; i++) {
			player u = players.get(i);
			int nx = u.x + dx[u.d];
			int ny = u.y + dy[u.d];
			
			if(nx < 0 || ny <0 || nx >= N || ny >= N || map[nx][ny] == 2) {
				if(u.d % 2 == 0) u.d+=1;
				else u.d -= 1;
				nx = u.x + dx[u.d];
				ny = u.y + dy[u.d];
			}
			if(nx < 0 || ny <0 || nx >= N || ny >= N || map[nx][ny] == 2) {
				nx = u.x; ny = u.y;
			}
			else {
				ArrayDeque<Integer> add =new ArrayDeque<>();
				int flag = 0;
				int sx = u.x; int sy = u.y;
				int s = p[u.x][u.y].size();
				while(s-- >0) {
					int k = p[sx][sy].poll();

					if(k == i) flag = 1;
					if(flag == 0) {
						p[u.x][u.y].add(k);
					}
					else {
						player tem = players.get(k);
						tem.x = nx; tem.y = ny;
						if(map[nx][ny] == 1) {add.add(k);}
						else p[nx][ny].add(k);
					}
				}
				if(map[nx][ny] == 1) {
					while(!add.isEmpty()) {
						int t = add.pollLast();
						p[nx][ny].add(t);
					}
				}
				int check = p[nx][ny].size();
				if(check >= 4) return 1;
			}
		}
		return 0;
	}
}