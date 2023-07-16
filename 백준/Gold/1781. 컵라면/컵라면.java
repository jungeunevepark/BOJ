import java.util.*;
import java.io.*;

public class Main {
    public static void main(String args[]) throws Exception{
    	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(bf.readLine());
    	PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
    		int r = Integer.compare(o1[0], o2[0]);
    		return r;
    	});
    	int max = 0;
    	for(int i=0; i<N; i++) {
    		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
    		int day = Integer.parseInt(st.nextToken());
    		int cup = Integer.parseInt(st.nextToken());
    		pq.offer(new int[] {day, cup});
    		max = Integer.max(day, max);
    	}
    	PriorityQueue<Integer> days = new PriorityQueue<>();
    	while(!pq.isEmpty()) {
    		int[] now = pq.poll();
    		days.offer(now[1]);
    		if(now[0] < days.size()) days.poll();
    	}
    	long cupramen = 0;
    	while(!days.isEmpty()) cupramen+=days.poll();
    	System.out.println(cupramen);
    }
}