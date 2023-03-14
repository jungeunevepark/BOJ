import java.util.*;
import java.io.*;

public class Main {
	static int[][] d;
	public static void main(String[] args) throws Exception {
	   BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	   int N = Integer.parseInt(bf.readLine());
	   d = new int[N+1][3];
	   for(int i=1; i<=N; i++) {
		   StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		   d[i] = new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
		   for(int k=0; k<3; k++) {
			   for(int u=0; u<3; u++)
				   if(u == k) continue;
				   else d[i][k] = Math.min(d[i-1][u], d[i][k]);
			   d[i][k] += Integer.parseInt(st.nextToken());
 		   }
	   }
	   int result = Integer.MAX_VALUE;
	   for(int i=0; i<3; i++)
		   result = Math.min(result, d[N][i]);
	   System.out.println(result);
	}
}