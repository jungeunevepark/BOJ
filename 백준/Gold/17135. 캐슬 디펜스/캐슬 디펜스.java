import java.util.*;
import java.io.*;

class Archer{
	public int x;
	public int y;
	public Archer(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}
public class Main {
	static int[][] map;
	static int N, M, D;
	static int result;
	static ArrayDeque<Archer> archers;
	static int[] dx = {0, -1, 1};
	static int[] dy = {-1, 1, 1};
	static void check() {
		int[][] temp = new int[N][M];
		for(int i = 0; i<N; i++)
			for(int j=0; j<M; j++)
				temp[i][j] = map[i][j];
		
		int count = 0; int kill = 0;
		while(true) {
			if(count == 3) {
				int t = 0;
				for(int i=0; i<N; i++)
					for(int j=0; j<M; j++) {
						if(temp[i][j] > 1) {
							kill++; 
							temp[i][j] = 0;
							}
						if(temp[i][j] == 1 && i < N-1) t++;
					}
				for(int i=N-1; i>0; i--)
					temp[i] = temp[i-1];
				temp[0] = new int[M];
				count = 0;
				if(t == 0) break;
			}
			Archer a = archers.poll();
			int x = a.x; int y = a.y;
			f: for(int d = 1; d<=D; d++) {
				int k = 1; boolean flag = false;
				int nx = x+d*dx[0];
				int ny = y+d*dy[0];
				while(true) {
					if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
						if(temp[nx][ny] >= 1) {
							temp[nx][ny]++; break f;
						}
					}
					
					if(flag == false) {
						nx += dx[1]; ny += dy[1];}
					else { 
						nx += dx[2]; ny += dy[2];}
					if(flag && k>d) break;
					else if(flag == false && k>=d) {k= 0; flag = true;}
					k++;
				}
				
			}
			count++;
			archers.offer(a);
		}
		if(kill > result) result = kill;
	}
	static void dfs(int cnt, int y) {
		if(cnt == 3) {
			check();
			return;
		}
		for(int j=y; j<M; j++) {
			Archer a = new Archer(N, j);
			archers.offer(a);
			dfs(cnt+1, j+1);
			archers.remove(a);
		}
	
	}
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N+1][M];
		archers = new ArrayDeque<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for(int j=0; j<M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		dfs(0,0);
		sb.append(result).append("\n");
		System.out.println(sb.toString());
	}
}