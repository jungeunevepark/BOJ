import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static char[][] map;
	static int[][] height;

	static int sx, sy, visit, result = Integer.MAX_VALUE;

	static int[] dx = {0, -1, 0, 1, -1, -1, 1, 1}, dy = {-1, 0, 1, 0, 1, -1, -1, 1};

	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		int min = Integer.MIN_VALUE; int max = 0;

		map = new char[N][N]; height = new int[N][N];
		for(int i=0; i<N; i++){
			String s = bf.readLine();
			for(int j=0; j<N; j++){
				map[i][j] = s.charAt(j);
				if(map[i][j] == 'P'){sx = i; sy = j;}
				else if(map[i][j] == 'K') visit++;
			}
		}

		List<Integer> mapHeight = new ArrayList<>();
		for(int i=0; i<N; i++){
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for(int j=0; j<N; j++){
				height[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != '.'){
					min = Math.min(min, height[i][j]);
					max = Math.max(max, height[i][j]); // 방문해야하는 곳들 중 가장 낮은, 높은 고도
				}
				mapHeight.add(height[i][j]);
			}
		}

		Collections.sort(mapHeight);

		int minIndex = 0; int maxIndex = 0;
		for(int i=0; i<N*N; i++){
			if(max == mapHeight.get(i)){
				maxIndex = i; // 방문 해야 할 집중 가장 높은 고도를 가진 집 index
			}
		}

		while(minIndex <= maxIndex && maxIndex < N*N){
			if(isDelivery(mapHeight.get(minIndex), mapHeight.get(maxIndex)) == visit){ // 모든 곳을 방문할수 있다면 고도를 줄인다.
				result = Math.min(result, mapHeight.get(maxIndex) - mapHeight.get(minIndex));
				minIndex++;
			}
			else
				maxIndex++;
		}
		System.out.println(result);

	}
	static int isDelivery(int low, int high){
		if(height[sx][sy] < low || height[sx][sy] > high) return -1;

		int r = 0;
		boolean[][] visited = new boolean[N][N];
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{sx, sy});
		visited[sx][sy] = true;

		while(!q.isEmpty()){
			int[] now = q.poll();
			for(int d=0; d < 8; d++){
				int nx = now[0] + dx[d];
				int ny = now[1] + dy[d];
				if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;
				if(height[nx][ny] < low || height[nx][ny] > high) continue;

				q.offer(new int[]{nx, ny});
				visited[nx][ny] = true;
				if(map[nx][ny] == 'K') r++;
			}

		}
		return r;
	}
}