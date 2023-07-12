import java.io.*;
import java.util.*;

public class Main {
	static long[] building;
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		building = new long[N];
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for(int i=0; i<N; i++)
			building[i] = Integer.parseInt(st.nextToken());

		boolean[][] isOkay = new boolean[N][N];
		for(int i=0; i<N; i++){
			for(int j=i+1; j<N; j++){
				if(look(i, j)){
					// System.out.println(i+", "+j);
					// if(building[i] > building[j]) isOkay[i][j] = true;
					isOkay[i][j] = true; isOkay[j][i] = true;
					// else isOkay[j][i] = true;
					// System.out.println(building[i]+", "+building[j]+", " +isOkay[14][11]);
				}
			}
		}
		int max = 0;
		for(int i=0; i<N; i++){
			int now = 0;
			for(int j=0; j<N; j++){
				if(isOkay[i][j]) now++;
			}
			// System.out.println(now);
			max = Math.max(max, now);
		}
		System.out.println(max);
	}
	static boolean look(int a, int b){
		double m = (double)(building[a]-building[b])/(a-b);
		for(int i=a+1; i<b; i++){
			if(building[i] >= m*(i-a)+building[a]) return false;
		}

		return true;
	}
}