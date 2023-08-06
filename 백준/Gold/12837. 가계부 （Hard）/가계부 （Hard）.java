import java.io.*;
import java.util.*;


public class Main {
	static class segmentTree {
		long[] tree;

		void init(int N){
			int n = 1; int t = 1;
			while(n < N){
				n *= 2;
				t += n;
			}
			tree = new long[t+1];
		}


		long get(int start, int end, int node, int vertex){
			if(start == end && start == vertex) return tree[node];

			int mid = (start+end)/2;
			if(start <= vertex && vertex <= end){
				return get(start, mid, node*2, vertex) + get(mid+1, end, 2*node+1, vertex);
			}
			else return 0;
		}

		void update(int start, int end, int vertex, int node, long diff){
			if(start <= vertex && vertex <= end){
				tree[node] += diff;
				if(start != end){
					int mid = (start+end)/2;
					update(start, mid, vertex, 2*node, diff);
					update(mid+1, end, vertex, 2*node+1, diff);
				}
			}
		}

		long print(int start, int end, int left, int right, int node){
			if(right < start || end < left) return 0;
			else if(left <= start && end <= right) return tree[node];

			int mid = (start+end)/2;
			return print(start, mid, left, right, 2*node) + print(mid+1, end, left, right, 2*node+1);
		}
		
	}

	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		segmentTree tree = new segmentTree();
		tree.init(N);

		for(int i=0; i<Q; i++){
			st = new StringTokenizer(bf.readLine(), " ");
			int command = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());

			if(command == 2){
				sb.append(tree.print(1, N, p, q, 1)).append("\n");
			}
			else {
				tree.update(1, N, p, 1, q);
			}
		}
		System.out.println(sb);
	}
}