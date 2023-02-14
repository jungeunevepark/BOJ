import java.util.*;
import java.io.*;

class dot{
	public int x;
	public int y;
	public int d;
	public dot(int x, int y, int d) {
		super();
		this.x = x;
		this.y = y;
		this.d = d;
	}
}

class bridge{
	public int len;
	public int start_island;
	public int end_island;
	public bridge(int len, int start_island, int end_island) {
		this.len = len;
		this.start_island = start_island;
		this.end_island = end_island;
	}
	@Override
	public String toString() {
		return "bridge [len=" + len + ", start_island=" + start_island + ", end_island=" + end_island + "]";
	}
	
}

public class Main {
	static int N, M, isnum = 1, result = Integer.MAX_VALUE, brnum;
	static int[][] island;
	static int parent[];
	static boolean visited[];
	static List<bridge> b;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		island = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for(int j=0; j<M; j++) {
				island[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		boolean[][] v = new boolean[N][M];
		b = new ArrayList<>();
		ArrayDeque<dot> dots = new ArrayDeque<>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(v[i][j] || island[i][j] == 0) continue;
				ArrayDeque<int[]> temp = new ArrayDeque<>();
				temp.offer(new int[] {i, j});
				v[i][j] = true; island[i][j] = isnum;
				while(!temp.isEmpty()) {
					int[] t = temp.poll();
					f: for(int d =0 ; d<4; d++) {
						int nx = t[0] + dx[d];
						int ny = t[1] + dy[d];
						if(nx<0 || ny<0 || nx>=N || ny>=M || v[nx][ny]) continue f;
						if(island[nx][ny] == 0) {
							dots.offer(new dot(t[0], t[1], d)); continue f;
						}
						v[nx][ny] = true;
						island[nx][ny] = isnum;
						temp.offer(new int[] {nx, ny});
					}
				}
				isnum++;
			}
		}
		while(!dots.isEmpty()) {
			dot d = dots.poll();
			int x = d.x; int y = d.y; int dir = d.d;
			int len = 0; int nx = x; int ny =y;
			w: while(true) {
				nx += dx[dir];
				ny += dy[dir];
				if(nx< 0 || ny < 0 || nx>=N || ny >= M || island[nx][ny] != 0) break w;
				len++;
			}
			if(ny >= 0 && nx>=0 && nx<N && ny<M && len >= 2 && island[x][y]<island[nx][ny]) {
				b.add(new bridge(len, island[x][y], island[nx][ny]));
				brnum++;
			}
		}
		parent = new int[isnum];
		visited = new boolean[brnum];
		isnum--;
		subset(0);
		if(result == Integer.MAX_VALUE)
			result = -1;
		System.out.println(result);
		
	}
	static int find_parent(int x) {
		if(parent[x] != x)
			return find_parent(parent[x]);
		return x;
	}
	
	static void union_parent(int a, int b) {
		a = find_parent(a);
		b = find_parent(b);
		if (a < b)
			parent[b] =  a;
		else parent[a] = b;
	}
	static void subset(int cnt) {
		if(cnt == brnum) {
			int sum =0;
			for(int i=1; i<=isnum; i++) {
				parent[i] = i;
			}
			for(int i=0 ; i<brnum; i++) {
				if(!visited[i]) continue;
				bridge bd = b.get(i);
				union_parent(bd.start_island, bd.end_island);
				sum += bd.len;
			}
			int flag = 0;
			for(int i=1; i<=isnum; i++)
				if(find_parent(i) != 1) return;
			if(result > sum) result = sum;
			return;
		}
		visited[cnt] = false;
		subset(cnt+1);
		visited[cnt] = true;
		subset(cnt+1);
		
	}
}