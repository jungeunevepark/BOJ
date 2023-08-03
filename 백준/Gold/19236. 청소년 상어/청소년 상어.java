import java.util.*;
import java.io.*;

public class Main {
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	static int result;
	public static void main(String args[]) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[][] map = new int[4][4];
        int[] fd = new int[17];
        for(int i = 0; i<4; i++) {
        	StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        	for(int j = 0; j<4; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        		fd[map[i][j]] = Integer.parseInt(st.nextToken())-1;
        	}
        }
        int eat = map[0][0];
        int sd = fd[eat];
        map[0][0] = 'S';
        dfs(0, 0, eat, sd, map, fd);
        System.out.println(result);
	}
	static void dfs(int x, int y, int eat, int sd, int[][] map, int[] fd) {
		result = Math.max(result, eat);
		int[] p = new int[17];
		f: for(int f = 1; f<=16; f++) {
			for(int i = 0; i<4; i++) {
				for(int j=0; j<4; j++) {
					if(map[i][j] == f) {
						for(int d=0; d<8; d++) {
							int ni = i+dx[(d+fd[f])%8];
							int nj = j+dy[(d+fd[f])%8];
							if(ni < 0 || nj < 0 || ni >= 4 || nj >=4 || map[ni][nj] == 'S') continue;
							map[i][j] = map[ni][nj]; // 0이든 물고기든 넣어준다.
							map[ni][nj] = f;
							p[f] = (fd[f] + d) % 8; // 방향이 갱신되었을 수 있음.
							continue f; // 다음 물고기 조사하러 ㄱ
						}
						p[f] = (7+fd[f]) % 8; //8방향 모두 불가능한 경우.
						continue f;
					}
				}
			}
		}
		map[x][y] = 0; // 상어가 이동할 것이기 때문에 0으로 초기화.
		for(int d =1; d<=3; d++) { // 행, 열, 대각선의 크기가 4칸이기 때문에 최대 4칸까지 뛰어 넘어 갈 수 있음.
			int nx = x + d*dx[sd];
			int ny = y + d*dy[sd];
			if(nx < 0 || ny < 0 || nx >= 4 || ny >= 4) break;
			if(map[nx][ny] == 0) continue;
			
			int[][] pass = new int[4][4];
			for(int i=0; i<4; i++)
				for(int j=0; j<4; j++)
					pass[i][j] = map[i][j];
			
			int tmp = pass[nx][ny];
			pass[nx][ny] = 'S';
			dfs(nx, ny, eat + tmp, p[tmp], pass, p);
		}

	}
}