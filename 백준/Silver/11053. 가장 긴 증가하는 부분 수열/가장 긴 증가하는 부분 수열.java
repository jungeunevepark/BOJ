import java.util.*;
import java.io.*;

public class Main {
	static int C, N;
	static int[] numbers;
	static int[] d;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		numbers = new int[N];
		d = new int[N];
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
			d[i] = 1;
		}
		for(int i=1; i<N; i++) {
			for(int j=0; j<i; j++) {
				if(numbers[j] < numbers[i]) d[i] = Math.max(d[i], d[j]+1);
			}
		}
		int max = 0;
		for(int i=0; i<N; i++) {
			max = Math.max(max, d[i]);
		}
		System.out.println(max);
	}
}