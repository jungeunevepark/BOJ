import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		N = Integer.parseInt(bf.readLine());
		for(int i=2; i<10; i++)
			dfs(i);
		System.out.println(sb.toString());
	}
	static void dfs(int num) {
		for(int i=2; i<(int)Math.sqrt(num)+1; i++) {
			if(num % i == 0) return;
		}
		if(num >= Math.pow(10, N-1) && num < Math.pow(10, N)) {
			sb.append(num).append("\n");
			return;
		}
		for(int i=0; i<10; i++)
			dfs(num*10+i);
	}
}