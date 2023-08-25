import java.io.*;
import java.util.*;


public class Main {
	static int[] parent;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		parent = new int[N+1];
		for(int i=1; i<=N; i++)
			parent[i] = i;

		int group = N;

		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)-> Integer.compare(o1[2], o2[2]));

		for(int i=0; i<M; i++){
			st = new StringTokenizer(bf.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			pq.offer(new int[]{a, b, c});
		}
		int result = 0;
		while(!pq.isEmpty()){
			int[] now = pq.poll();
			if(group == 2) break;

			if(parent(now[0]) != parent(now[1])){
				combine(now[0], now[1]);
				result += now[2];
				group--;
			}
			// else result += now[2];
		}
		System.out.println(result);
	}
	static int parent(int a){
		if(parent[a] == a) return a;
		else return parent[a] = parent(parent[a]);
	}

	static void combine(int a, int b){
		int A = parent(a);
		int B = parent(b);
		if(A > B)
			parent[B] = A;
		else parent[A] = B;
	}

}