import java.io.*;
import java.util.*;

public class Main {
	static int[] numbers;
	static int[] options = new int[4];
	static int N;
	static int min, max;
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
    	N= Integer.parseInt(bf.readLine());
    	StringTokenizer st = new StringTokenizer(bf.readLine(), " "); 
    	options = new int[4];
    	numbers = new int[N];
    	for(int i=0; i<N; i++)
    		numbers[i] = Integer.parseInt(st.nextToken());
    	st = new StringTokenizer(bf.readLine(), " "); 
    	for(int i=0; i<4; i++)
    		options[i] = Integer.parseInt(st.nextToken());
    	min = Integer.MAX_VALUE; max = Integer.MIN_VALUE;
    	backtracking(1, numbers[0]);
        System.out.println(max);
        System.out.println(min);
    }
    static void backtracking(int idx, int sum) {
    	if(idx == N) {
    		if(max < sum) max = sum;
    		if(min > sum) min = sum;
    		return;
    	}
    	for(int i=0; i<4; i++) {
    		if(options[i] == 0) continue;
    		options[i]--;
    		if(i==0)
    			backtracking(idx+1, sum+numbers[idx]);
    		if(i==1)
    			backtracking(idx+1, sum-numbers[idx]);
    		if(i==2)
    			backtracking(idx+1, sum*numbers[idx]);
    		if(i== 3)
    			backtracking(idx+1, sum/numbers[idx]);
    		options[i]++;
    	}
    }
}