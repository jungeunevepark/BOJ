import java.io.*;
import java.util.*;

public class Main {
	static int[] LIS;
	static int[] numbers;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		LIS = new int[N];
		numbers = new int[N];
		int idx = 0;
		for(int i=0; i<N; i++)
			numbers[i] = Integer.parseInt(st.nextToken());
		LIS[0] = numbers[0];
		for(int i=1; i<N; i++) {
			if(LIS[idx] < numbers[i]) LIS[++idx] = numbers[i];
			else {
				LIS[lower_bound(0, idx, numbers[i])] = numbers[i];
			}
		}
		System.out.println(idx+1);
	}
	static int lower_bound(int start, int end, int target) {
		int min = start; int max = end;
		while(min < max) { // 같으면 while문 종료
			int mid = (min + max) / 2;
			if(LIS[mid] < target) min = mid+1;
			else {
				max = mid;
			}
		}	
		return min;
	}

}