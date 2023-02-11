import java.io.*;
import java.util.*;

public class Main {
    static int half;
    static int N;
    static int[] team;
    static int[][] power;
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        power = new int[N][N];
        team = new int[N];
        half = N / 2;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < N; j++)
                power[i][j] = Integer.parseInt(st.nextToken());
        }
        result = Integer.MAX_VALUE;
        dfs(0, 0);
        sb.append(result);
        System.out.println(sb.toString());
    }

    static void dfs(int member, int x) {
        if (member == half) {
            int score1 = 0, score2 = 0;
            for (int k = 0; k < N; k++) {
                for (int u = k + 1; u < N; u++) {
                    if (team[k] == 0 && team[u] == 0)
                        score2 += power[k][u] + power[u][k];
                    if (team[k] == 1 && team[u] == 1)
                        score1 += power[k][u] + power[u][k];
                }
            }
            if (Math.abs(score1 - score2) < result)
                result = Math.abs(score1 - score2);
            return;
        }

        for (int i = x + 1; i < N; i++) {
            team[i] = 1;
            dfs(member + 1, i);
            team[i] = 0;
        }
    }
}
