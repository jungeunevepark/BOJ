import java.util.*;
import java.io.*;

class Fireball{
	public int m, s, d;

	public Fireball(int m, int s, int d) {
		super();
		this.m = m;
		this.s = s;
		this.d = d;
	}
}

public class Main {
	static int N, M, K;
	static int result;
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	static ArrayDeque<Fireball>[][] balls;
	
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        balls = new ArrayDeque[N][N];
        for(int i=0; i<N; i++)
        	for(int j=0; j<N; j++)
        		balls[i][j] = new ArrayDeque<>();
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(bf.readLine(), " ");
        	int r = Integer.parseInt(st.nextToken())-1;
        	int c = Integer.parseInt(st.nextToken())-1;
        	balls[r][c].offer(new Fireball(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        for(int i=0; i<K; i++) {
        	move();
        }
        int result = 0;
        for(int i=0; i<N; i++)
    		for(int j=0; j<N; j++) {
    			if(balls[i][j].size() == 0) continue;
    			while(!balls[i][j].isEmpty()) {
    				result += balls[i][j].poll().m;
    			}
    		}
        System.out.println(result);
    }
    
    static void move() {
    	ArrayDeque<Fireball>[][] tmp = new ArrayDeque[N][N];
    	for(int i=0; i<N; i++)
    		for(int j=0; j<N; j++)
    			tmp[i][j] = new ArrayDeque<>();
    	
    	// 파이어볼 이동
    	for(int i=0; i<N; i++) {
    		for(int j=0; j<N; j++) {
    			if(balls[i][j].size() == 0) continue;
    			while(!balls[i][j].isEmpty()) {
    				Fireball t = balls[i][j].poll();
    				int ni = i; int nj = j;
    				int s = t.s;
    				while(s-- > 0) {
    					ni += dx[t.d]; nj += dy[t.d];
    					if(ni == -1) ni = N-1;
    					if(nj == -1) nj = N-1;
    					if(ni == N) ni = 0;
    					if(nj == N) nj = 0;
    				}
    				tmp[ni][nj].offer(t);
    			}
    		}
    	}
    	
    	// 파이어볼 결합 및 소멸
    	for(int i=0; i<N; i++) {
    		for(int j=0; j<N; j++) {
    			if(tmp[i][j].size() <= 1) continue;
    			int sum_m = 0; int sum_s = 0; int size = tmp[i][j].size();
    			int flag = 0;
    			while(!tmp[i][j].isEmpty()) {
    				Fireball t = tmp[i][j].poll();
    				sum_m += t.m; sum_s += t.s;
    				if(t.d % 2 == 0) flag++;
    			}
    			sum_m /= 5;
    			if(sum_m == 0) continue;
    			sum_s /= size;
    			if(flag == 0 || flag == size) {
    				for(int d=0; d<8; d+=2)
    					tmp[i][j].offer(new Fireball(sum_m, sum_s, d));
    			}
    			else {
    				for(int d=1; d<8; d+=2)
    					tmp[i][j].offer(new Fireball(sum_m, sum_s, d));
    			}
    		}
   		}
    	balls = tmp;
    }
}