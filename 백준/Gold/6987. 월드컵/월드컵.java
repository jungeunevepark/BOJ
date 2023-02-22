import java.util.*;
import java.io.*;

public class Main {
	static int N, K;
	static int[][] score;
	static int[][] check;
	static int[][] WDL = {{1, 0, 0, 0, 0, 1}, {0, 1, 0, 0, 1, 0}, {0, 0, 1, 1, 0, 0}};
	static int[] first = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
	static int[] second = {1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5};
	static int max;
    public static void main(String[] args) throws Exception {
       BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
       StringBuilder sb = new StringBuilder();
       for(int t=0; t<4; t++) {
    	   score = new int[6][3];
    	   check = new int[6][3];
    	   int lose = 0; int win = 0; int draw = 0;
    	   StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
    	   for(int i=0; i<6; i++) {
    		   win += score[i][0] = Integer.parseInt(st.nextToken());
    		   draw += score[i][1] = Integer.parseInt(st.nextToken());
    		   lose += score[i][2] = Integer.parseInt(st.nextToken());
    	   }
    	   if(win != lose || draw % 2 != 0 || win+lose+draw != 30) sb.append("0").append(" ");
    	   else sb.append(dfs(0)? "1" : "0").append(" ");
        }
       System.out.println(sb);
    }
    static boolean dfs(int cnt) {
    	if(cnt == 15) {
    		return true;
    	}
    	boolean re = false;

    	int x = first[cnt]; int i = second[cnt];
		for(int j=0; j<3; j++) {
			if(check[x][0]+WDL[j][0] > score[x][0] || check[x][1]+WDL[j][1] > score[x][1] || check[x][2]+WDL[j][2] > score[x][2]) continue;
			if(check[i][0]+WDL[j][3] > score[i][0] || check[i][1]+WDL[j][4] > score[i][1] || check[i][2]+WDL[j][5] > score[i][2]) continue;
			check[x][0] += WDL[j][0]; check[x][1] += WDL[j][1]; check[x][2] += WDL[j][2];
			check[i][0] += WDL[j][3]; check[i][1] += WDL[j][4]; check[i][2] += WDL[j][5];
			re |= dfs(cnt+1);
			check[x][0] -= WDL[j][0]; check[x][1] -= WDL[j][1]; check[x][2] -= WDL[j][2];
			check[i][0] -= WDL[j][3]; check[i][1] -= WDL[j][4]; check[i][2] -= WDL[j][5];
		}


    	return re;
    }
}