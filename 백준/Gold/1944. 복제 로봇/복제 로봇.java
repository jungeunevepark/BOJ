import java.io.*;
import java.net.Inet4Address;
import java.util.*;

public class Main {
	static int[][] v;
	static char[][] map;
	static boolean[][] keys;
	static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 맵 크기
		int M = Integer.parseInt(st.nextToken()); // Key의 수
		v = new int[N][N];
		map = new char[N][N];
		keys = new boolean[N][N];
		int sx = 0; int sy = 0;
		for(int i=0; i<N; i++){
			String s = bf.readLine();
			for(int j=0; j<N; j++){
				map[i][j] = s.charAt(j);
				if(map[i][j] == 'S'){
					sx = i; sy = j;
				}
			}
		}
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)-> Integer.compare(o1[3], o2[3]));
		pq.offer(new int[]{sx, sy, 0, 0});
		v[sx][sy] = -1;
		int result = 0;
		int key = M;
		// for(int i=0; i<N; i++)
		// 	System.out.println(Arrays.toString(v[i]));
		while(!pq.isEmpty()){
			// if(key == 0) break;
			// System.out.println(pq);
			int[] now = pq.poll(); // 2는 S로부터 떨어진 거리, 3은 복제된 위치로부터 떨어진 거리
			// if(now[0] != sx && now[1] != sy) v[now[0]][now[1]] = now[3];
			if(map[now[0]][now[1]] == 'K'){
				// System.out.println(Arrays.toString(now)+", "+v[now[0]][now[1]]+", "+now[3]);
				if(!keys[now[0]][now[1]]){
					keys[now[0]][now[1]] = true;
					result += now[3];
					key--;
					now[3] = 0;
				}
			}

			for(int d=0; d<4; d++){
				int nx = now[0] + dx[d];
				int ny = now[1] + dy[d];

				if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == '1') continue;
				// System.out.println(v[nx][ny]+", "+now[3]);
				if(v[nx][ny] != 0 && v[nx][ny] <= now[3]+1) continue;
				// System.out.println(nx+", "+ny+", "+now[3]+", "+v[nx][ny]);
				pq.offer(new int[]{nx, ny, now[2]+1, now[3]+1});
				v[nx][ny] = now[3]+1;
			}
		}
		// System.out.println(key);
		// for(int i=0; i<N; i++)
		// 	System.out.println(Arrays.toString(v[i]));
		if(key > 0)
			System.out.println(-1);
		else
			System.out.println(result);


	}

}