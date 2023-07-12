import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] W = new int[N+1]; // W[i]는 i시간에 들을 수 있는 최대 중요도
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(bf.readLine());
			int I = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			for(int j=N; j>=T; j--)
				W[j] = Math.max(W[j], W[j-T]+I);
		}
		System.out.println(W[N]);
	}
}