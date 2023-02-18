import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(bf.readLine());
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		int max = Integer.MIN_VALUE; int min = Integer.MAX_VALUE;
		for(int t=0; t< N; t++) {
			int n = Integer.parseInt(st.nextToken());
			if(max < n) max = n;
			if(min > n) min = n;
		}
		System.out.println(min+" "+max);
	}
}