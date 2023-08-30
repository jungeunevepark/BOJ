import java.io.*;
import java.util.*;


public class Main {
	static int[] before; // 만드는 데에 필요한 부품의 "종류"의 개수
	static List<int[]>[] needParts;
	static List<int[]>[] wantParts;

	static boolean[] v;
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine()); // 완제품의 번호.

		before = new int[N+1];
		wantParts = new List[N+1];
		needParts = new List[N+1];
		v = new boolean[N+1];

		for(int i=1; i<=N; i++) needParts[i] = new ArrayList<>();
		for(int i=1; i<=N; i++) wantParts[i] = new ArrayList<>();
		int M = Integer.parseInt(bf.readLine());

		for(int i=0; i<M; i++){
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			wantParts[b].add(new int[]{a, c});
			before[a]++;
		}
		
		ArrayDeque<Integer> q = new ArrayDeque<>();

		for(int i=1; i<=N; i++){
			if(before[i] == 0) {
				v[i] = true;
				q.offer(i);
			}
		}
		while(!q.isEmpty()){
			int n = q.poll();

			for(int[] w: wantParts[n]){
				int[] parts = new int[N+1];
				before[w[0]]--;
				if(needParts[n].size() == 0) parts[n]+=w[1];
				else {
					for(int[] k: needParts[n])
						parts[k[0]] += k[1]*w[1];
				}
				addParts(parts, needParts[w[0]]);
			}

			for(int i=1; i<=N; i++){
				if(!v[i] && before[i] == 0) {
					v[i] = true;
					q.offer(i);
				}
			}
		}
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->Integer.compare(o1[0], o2[0]));

		for(int[] n: needParts[N]){
			pq.offer(n);
		}
		while(!pq.isEmpty()){
			int[] now = pq.poll();
			System.out.println(now[0] +" "+now[1]);
		}

	}
	static void addParts(int[] P, List<int[]> n){
		w: for(int i=1; i<=N; i++){
			if(P[i] == 0) continue;
			if(n.size() > 0){
				for(int[] need: n){
					if(need[0] == i) {
						need[1] += P[i];
						 continue w;
					}
				}
			}
			n.add(new int[]{i, P[i]});
		}
	}
}