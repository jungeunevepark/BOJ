import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] array;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(bf.readLine());
		for(int t=1; t<=T; t++) {
			String s = bf.readLine();
			N = s.length();
			array = new int[N];
			for(int i=0; i<N; i++)
				array[i] = s.charAt(i)-'0';
			if(!nextPermutation()) sb.append("BIGGEST").append("\n");
			else {
				for(int i=0; i<N; i++)
					sb.append(array[i]);
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
	static boolean nextPermutation() {
		int i=N-1;
		while(i-- > 0) if(array[i] < array[i+1]) break;
		if(i < 0) return false;
		
		int k=N-1;
		while(k > i) { 
			if(array[i] < array[k]) break;
			else k--;
		}
		
		int tmp = array[i];
		array[i] = array[k];
		array[k] = tmp;
		int u = 1;
		while(i+u < N-u) {
			tmp = array[i+u];
			array[i+u] = array[N-u];
			array[N-u] = tmp;
			u++;
		}
		
		return true;
	}
}