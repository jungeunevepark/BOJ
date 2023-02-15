import java.util.*;
import java.io.*;
class node implements Comparable<node>{
	public int x;
	public int abs;
	public node(int x, int abs) {
		super();
		this.x = x;
		this.abs = abs;
	}
	@Override
	public int compareTo(node o) {
		int r = Integer.compare(abs, o.abs);
		if(r == 0) r = Integer.compare(x, o.x);
		return r;
	}
}
public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		PriorityQueue<node> min = new PriorityQueue<>();
		int N = Integer.parseInt(bf.readLine());
		for(int i=0; i<N; i++) {
			int tmp = Integer.parseInt(bf.readLine());
			if(tmp != 0)
				min.offer(new node(tmp, Math.abs(tmp)));
			else {
				if(min.isEmpty()) sb.append(0).append("\n");
				else sb.append(min.poll().x).append("\n");
			}
		}
		System.out.println(sb);
	}
}