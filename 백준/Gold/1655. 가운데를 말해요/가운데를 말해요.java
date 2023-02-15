import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> min = new PriorityQueue<>();
		PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
		int N = Integer.parseInt(bf.readLine());
		for(int i=0; i<N; i++) {
			int tmp = Integer.parseInt(bf.readLine());
			if(i % 2 == 0) {
				if(max.isEmpty() || min.peek() >= tmp) max.add(tmp);
				else {
					max.add(min.poll());
					min.add(tmp);
				}
			}
			else {
				if(max.peek() < tmp) min.add(tmp);
				else {
					min.add(max.poll());
					max.add(tmp);
				}
			}
			sb.append(max.peek()).append("\n");
		}
		System.out.println(sb);
	}
}