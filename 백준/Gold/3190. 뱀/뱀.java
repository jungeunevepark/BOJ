import java.io.*;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static class timer{
        char dir;
        int time;
        timer(char dir, int time){
            this.dir = dir;
            this.time = time;
        }
    }
    static boolean[][] map;
    static boolean[][] snake;
    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        map = new boolean[N][N];
        snake = new boolean[N][N];
        int K = Integer.parseInt(bf.readLine());
        for(int i=0; i<K; i++){
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x-1][y-1] = true;
        }
        ArrayDeque<timer> q = new ArrayDeque<>();
        int L = Integer.parseInt(bf.readLine());
        for(int i=0; i<L; i++){
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            q.offer(new timer(c, x));
        }
        int time = 1;
        int hx = 0; int hy = 0;
        int tx = 0; int ty = 0;
        int d = 0;
        ArrayDeque<int[]> road = new ArrayDeque<>();
        while(true){
            if(!q.isEmpty() && q.peek().time < time){
                timer t = q.poll();
                if(t.dir == 'L')
                    d = (d+3) % 4;
                else if(t.dir == 'D')
                    d = (d+1) % 4;
            }
            hx += dx[d]; hy += dy[d];

            if(hx < 0 || hx >= N || hy < 0 || hy >= N) break; // 벽에 부딪히거나
            if(snake[hx][hy]) break; // 자기자신의 몸과 부딪힘
            snake[hx][hy] = true;
            road.offer(new int[]{hx, hy});

            if(map[hx][hy]) map[hx][hy] = false;
            else {
                snake[tx][ty] = false;
                tx = road.peek()[0];
                ty = road.poll()[1];
            }
//            System.out.println(hx+", " +hy)
            time++;
        }
        System.out.println(time);
    }
}