import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> plus = new PriorityQueue<>(Comparator.reverseOrder());
		PriorityQueue<Integer> minus = new PriorityQueue<>();

		int N = Integer.parseInt(bf.readLine());
		for(int i=0; i<N; i++){
			int number = Integer.parseInt(bf.readLine());
			if(number > 0)
				plus.offer(number);
			else minus.offer(number);
		}

		int result = 0;
		while(!plus.isEmpty()){
			int n1 = plus.poll();
			if(!plus.isEmpty() && plus.peek() != 1){
				result += n1*plus.poll();
			} else result += n1;
		}

		while(!minus.isEmpty()){
			int n1 = minus.poll();
			if(!minus.isEmpty()) result += n1 * minus.poll();
			else result += n1;
		}
		System.out.println(result);
	}
}