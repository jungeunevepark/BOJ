import java.io.*;
import java.util.*;

public class Main {
	static Long[] numbers;
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		numbers = new Long[N];
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		for(int i=0; i<N; i++){
			numbers[i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(numbers);

		int result = 0;
		for(int i=0; i<N; i++){
			if(isGood(i)) result++;
		}
		System.out.println(result);

	}
	static boolean isGood(int index){
		Long number = numbers[index];
		int start = 0; int end = N-1;
		while(start < end){
			if(start == index) start++;
			else if(end == index) end--;
			else if(numbers[start] + numbers[end] < number) start++;
			else if(numbers[start] + numbers[end] == number) return true;
			else end--;

		}
		return false;
	}
}