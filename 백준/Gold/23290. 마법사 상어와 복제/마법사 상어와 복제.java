import java.util.*;
import java.io.*;

public class Main {
	static int N, M, S, sx, sy, cnt;
	static List<Integer>[][] map; // 물고기의 direction만 저장함
	static int[][] smell;
	static ArrayDeque<int[]> add;
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1, 0}, dy = {-1, -1, 0, 1, 1, 1, 0, -1, 0};
	static int[] sdx = {-1, 0, 1, 0}, sdy = {0, -1, 0, 1}; 
    public static void main(String args[]) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = 4;
        M = Integer.parseInt(st.nextToken()); //물고기 수
        S = Integer.parseInt(st.nextToken()); //라운드 수
        add = new ArrayDeque<>();
        map = new ArrayList[4][4];
        smell = new int[4][4];
        for(int i=0; i<4; i++)
        	for(int j=0; j<4; j++)
        		map[i][j] = new ArrayList<>();
        
        for(int i=0; i<M; i++){
        	st = new StringTokenizer(bf.readLine(), " ");
        	int fx = Integer.parseInt(st.nextToken())-1;
        	int fy = Integer.parseInt(st.nextToken())-1;
        	int fd = Integer.parseInt(st.nextToken())-1;
        	map[fx][fy].add(fd);
        }
        st = new StringTokenizer(bf.readLine(), " ");
        sx = Integer.parseInt(st.nextToken())-1;
        sy = Integer.parseInt(st.nextToken())-1;
        
        cnt = 0;
        while(++cnt <= S) {
        	// 복제마법 시전
        	copy_fish();
        	// add에 따로 저장해두기
        	move_fish();
        	move_shark();
        	// 두번 전 연습에서 생긴 물고기 냄새 사라짐
        	// 복제된 fish 추가하기
        	while(!add.isEmpty()) {
        		int[] now = add.poll();
        		map[now[0]][now[1]].add(now[2]);
        	}
        }
        int k = 0;
        for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
        		k+=map[i][j].size();
        	}
        }
        System.out.println(k);
    }
    static void copy_fish() {
    	// add 에 현재 격자 위의 모든 물고기를 복제한다. x, y, d 순서
    	for(int i=0; i<N; i++) {
    		for(int j=0; j<N; j++) {
    			if(map[i][j].size() != 0) {
    				for(int k=0; k<map[i][j].size(); k++)
    					add.offer(new int[] {i, j, map[i][j].get(k)});
    			}
    		}
    	}
    }
    static void move_fish() {
    	List<Integer>[][] temp = new ArrayList[4][4];
    	for(int i=0; i<4; i++)
        	for(int j=0; j<4; j++)
        		temp[i][j] = new ArrayList<>();
    	
    	for(int i=0; i<N; i++) {
    		for(int j=0; j<N; j++) {
    			if(map[i][j].size()!=0) {
    				f: for(int k=0; k<map[i][j].size(); k++) {
    					int nd = map[i][j].get(k);
    					for(int d=0; d<8; d++) {
    						int ni = i+dx[(8+(nd-d))%8];
    						int nj = j+dy[(8+(nd-d))%8];
    						if(ni < 0 || nj < 0 || ni >= N || nj >= N || smell[ni][nj] >= cnt || (ni == sx && nj == sy)) continue;
    						temp[ni][nj].add((8+(nd-d))%8);
    						continue f;
    					}
    					temp[i][j].add(nd);
    				}
    			}
    		}
    	}
    	map = temp;
    }
    static void move_shark() {
    	ArrayDeque<int[]> move = new ArrayDeque<>();
    	ArrayDeque<int[]> q = new ArrayDeque<>();
    	q.offer(new int[] {sx, sy, 0, 0, 0, 0, 0, 0});
    	int max = -1;
    	while(!q.isEmpty()) {
    		int[] n = q.poll();
    		int x = n[0]; int y = n[1];
    		int time = n[2];
    		int eat = n[7];
    		if(time == 3) {
    			if(eat > max) {
	    			move = new ArrayDeque<>();
	    			move.offer(new int[] {n[3], n[4]});
	    			move.offer(new int[] {n[5], n[6]});
	    			move.offer(new int[] {x, y});
	    			max = eat;
	    			sx = x; sy = y;
    			}
    			continue;
    		}
    		for(int d=0; d<4; d++) {
    			int nx = x+sdx[d];
    			int ny = y+sdy[d];
    			if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
    			int e = map[nx][ny].size();
    			if(time == 0) {
    				q.offer(new int[] {nx, ny, 1, nx, ny, 0, 0, eat+e});
    			}
    			else if(time == 1) {
    				if(nx == n[3] && ny == n[4]) e = 0;
    				q.offer(new int[] {nx, ny, 2, n[3], n[4], nx, ny, eat+e});
    			}
    			else if(time == 2) {
    				if((nx == n[3] && ny == n[4]) || (nx == n[5] && ny == n[6])) e = 0;
    				q.offer(new int[] {nx, ny, 3, n[3], n[4], n[5], n[6], eat+e});
    			}
    		}
    	}
    	while(!move.isEmpty()) {
    		int[] n = move.poll();
    		if(map[n[0]][n[1]].size() == 0) continue;
    		map[n[0]][n[1]] = new ArrayList<>();
    		smell[n[0]][n[1]] = cnt+2;
    	}

    	
    }
}