import java.io.*;
import java.util.*;
public class Main {
	static class star{
		double x, y;
		public star(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
	static class comp implements Comparable<comp>{
		int i, j;
		double l;

		public comp(int i, int j, double l) {
			this.i = i;
			this.j = j;
			this.l = l;
		}
		@Override
		public int compareTo(comp o) {
			if (l < o.l) {
				return -1;
			}
			return 1;
		}
	}
	static int[] parent;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		star[] stars = new star[N];
		for(int i=0; i<N; i++){
			StringTokenizer st = new StringTokenizer(bf.readLine());
			double a = Double.parseDouble(st.nextToken());
			double b = Double.parseDouble(st.nextToken());
			stars[i] = new star(a, b);
		}

		parent = new int[N];
		for(int i=0; i<N; i++) parent[i] = i;
		PriorityQueue<comp> pq = new PriorityQueue<>();
		for(int i=0; i<N-1; i++){
			for(int j=i+1; j<N; j++){
				star a = stars[i];
				star b = stars[j];
				double l = Math.sqrt(Math.pow((a.x-b.x), 2)+Math.pow((a.y-b.y), 2));
				pq.offer(new comp(i, j, l));
			}
		}
		double result = 0;
		while(!pq.isEmpty()){
			comp d = pq.poll();
			if(parent(d.i) != parent(d.j)){
				result += d.l;
				union(d.i, d.j);
			}
		}
		System.out.println(result);

	}
	static int parent(int a){
		if(a == parent[a]) return a;
		else return parent[a] = parent(parent[a]);
	}
	static void union(int a, int b){
		int A = parent(a), B = parent(b);
		if(A > B) parent[A] = parent[B];
		else parent[B] = parent[A];
	}
}