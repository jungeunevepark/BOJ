import java.io.*;
import java.net.Inet4Address;
import java.util.*;

public class Main {
	static boolean[] power;
	static int[] parent;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 도시의 수
		int M = Integer.parseInt(st.nextToken()); // 간선 수
		int K = Integer.parseInt(st.nextToken()); // 발전소 수
		power = new boolean[N+1];
		parent = new int[N+1];

		st = new StringTokenizer(bf.readLine(), " ");

		for(int i=1; i<=N; i++) parent[i] = i;
		for(int i=1; i<=K; i++){
			power[Integer.parseInt(st.nextToken())] = true; // 발전소 true
		}
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)-> Integer.compare(o1[2], o2[2]));

		for(int i=0; i<M; i++){
			st = new StringTokenizer(bf.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			pq.offer(new int[]{a, b, dist});
		}
		int result = 0;

		while(!pq.isEmpty()){
			int[] now = pq.poll();

			if(parent(now[0]) != parent(now[1])) {
				if(combine(now[0], now[1]))
					result += now[2];
			}
		}

		System.out.println(result);

	}
	static boolean combine(int a, int b){
		int A = parent(a);
		int B = parent(b);
		if(power[A] && power[B]) return false;

		if(power[A]) parent[B] = A;
		else if(power[B]) parent[A] = B;
		else if(A > B) parent[A] = B;
		else parent[B] = A;
		return true;
	}
	static int parent(int a){
		if(a == parent[a]) return a;
		else return parent[a] = parent(parent[a]);
	}


}