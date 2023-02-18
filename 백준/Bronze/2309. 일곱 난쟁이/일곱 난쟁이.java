import java.io.*;
import java.util.*;

public class Main {
	static int[] mini;
	static int[] result;
	static StringBuilder sb;
	static int flag;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		mini = new int[9];
		result = new int[7]; // 9C7 = 9C2 조합
		for(int i=0; i<9; i++) {
			mini[i] = Integer.parseInt(bf.readLine());
		}
		comb(0, 0, 0);
		System.out.println(sb.toString());
		bf.close();
	}
	static void comb(int cnt, int start, int sum) {
		if(sum > 100) return;
		if(cnt == 7) {
			if(sum == 100 && flag == 0) {
				Arrays.sort(result);
				for(int i=0; i<7; i++)
					sb.append(result[i]).append("\n");
				flag = 1;
			}
			return;
		}
		for(int i=start; i<9; i++) {
			result[cnt] = mini[i];
			comb(cnt+1, i+1, sum+mini[i]);
		}
	}
}