import java.util.*;
import java.io.*;

public class Main {
	static int N, K;
	static int[] numbers;
	static int max;
    public static void main(String[] args) throws Exception {
       BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
       StringBuilder sb = new StringBuilder();
       StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
       N = Integer.parseInt(st.nextToken());
       K = Integer.parseInt(st.nextToken());
       
       // 투 포인터 체크하고 있는 곳, 수열의 시작 부분
       numbers = new int[N];
       st = new StringTokenizer(bf.readLine()," ");
       for(int i=0; i<N ;i++)
    	   numbers[i] = Integer.parseInt(st.nextToken());
       
       int max = 0;
       HashMap<Integer, Integer> set = new HashMap<>();
       int count = 0; int idx = 0;
       for(int i=0; i<N; i++) {
    	   if(set.get(numbers[i]) == null) { set.put(numbers[i], 1); count++;}
    	   else {
    		   if(set.get(numbers[i]) < K) {
    			   // 더 넣어도 됨.
    			   set.replace(numbers[i], set.get(numbers[i])+1); count++;
    		   }
    		   else {
	    		   for(int j=idx; j<i; j++) {
	    			   if(numbers[i] == numbers[j]) {idx = j+1; break;}
	    			   set.replace(numbers[j], set.get(numbers[j])-1); count--;
	    		   }
    		   }
    	   }
    	   if(max < count) max = count;
       }
       
       System.out.println(max);
    }
}