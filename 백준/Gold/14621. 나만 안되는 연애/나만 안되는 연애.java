import java.io.*;
import java.util.*;

public class Main {
	static boolean[] uni;
	static int[] parent;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		uni = new boolean[N+1];
		parent = new int[N+1];

		st = new StringTokenizer(bf.readLine(), " ");

		for(int i=1; i<=N; i++) parent[i] = i;
		for(int i=1; i<=N; i++){
			if(st.nextToken().charAt(0) == 'M') uni[i] = true; // 남자 학교 true, 여자 학교 false
		}
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)-> Integer.compare(o1[2], o2[2]));

		for(int i=0; i<M; i++){
			st = new StringTokenizer(bf.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());

			if(uni[a] != uni[b]) pq.offer(new int[]{a, b, dist});
		}
		int result = 0;
		while(!pq.isEmpty()){
			int[] now = pq.poll();

			if(parent(now[0]) != parent(now[1])) {
				result += now[2];
				combine(now[0], now[1]);}
		}

		for(int i=2; i<=N; i++){
			if(parent(i-1) != parent(i)){
				System.out.println(-1);
				return;
			}
		}
		System.out.println(result);

	}
	static void combine(int a, int b){
		int A = parent(a);
		int B = parent(b);

		if(A > B) parent[A] = B;
		else parent[B] = A;
	}
	static int parent(int a){
		if(a == parent[a]) return a;
		else return parent[a] = parent(parent[a]);
	}


}