import java.util.*;
import java.io.*;

public class Main {
	static int result;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N= Integer.parseInt(bf.readLine());
        for(int i=0; i<N; i++) {
        	String s = bf.readLine();
        	int[] nums = new int[s.length()];
        	for(int k=0; k<s.length(); k++)
        		nums[k] = s.charAt(k) - '0';
        	if(np(nums) == false) sb.append("USELESS").append("\n");
        	else sb.append(result).append("\n");
        }
        System.out.println(sb);
    }
    static boolean np(int[] numbers) {
    	int N = numbers.length;
    	int i = N-1;
    	while(i >0 && numbers[i-1] >= numbers[i]) i--;
    	if(i == 0) return false;
    	
    	int j = N-1;
    	while(numbers[i-1] >= numbers[j]) j--;
    	
    	swap(i-1, j, numbers);
    	int k = N-1;
    	while(i<k) swap(i++, k--, numbers);
    	
    	result = numbers[0];
    	for(int u=1; u<N; u++) {
    		result = result*10 + numbers[u];
    	}
    	
    	return true;
    }
    static void swap(int i, int j, int[] numbers) {
    	int tmp = numbers[i];
    	numbers[i] = numbers[j];
    	numbers[j] = tmp;
    }
}