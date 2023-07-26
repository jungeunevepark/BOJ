import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	static class OE{
		int odd;
		int even;
	}
	static class SegmentTree{

		OE[] tree;
		SegmentTree(int N){
			int k = 1; int n = 1;
			while(k < N){
				k *= 2;
				n += k;
			}
			tree = new OE[n+1];
		}

		void init(int start, int end, int node){
			tree[node] = new OE();
			if(start == end){
				if(numbers[start] % 2 == 0) tree[node].even++;
				else tree[node].odd++;
				return;
			}
			int mid = (start + end) / 2;
			init(start, mid, node*2);
			init(mid+1, end, node*2+1);
			tree[node].even = tree[node*2+1].even+tree[node*2].even;
			tree[node].odd = tree[node*2+1].odd+tree[node*2].odd;
		}
		void update(int start, int end, int vertex, int node, boolean odd){
			if(start <= vertex && vertex <= end){
				if(odd) {
					tree[node].odd++;
					tree[node].even--;
				}
				else {
					tree[node].odd--;
					tree[node].even++;
				}
				if(start == end && start == vertex) return;
				int mid = (start + end) / 2;
				update(start, mid, vertex,node*2, odd);
				update(mid+1, end, vertex,node*2+1, odd);
			}
		}

		int print(int start, int end, int left, int right, int node, boolean odd){
			if(right < start || end < left) return 0;
			if(left <= start && end <= right){
				if(odd) return tree[node].odd;
				else return tree[node].even;
			}else{
				return print(start, (start+end)/2, left, right, node*2, odd) +
					print((start+end)/2+1, end, left, right, node*2+1, odd);
			}

		}

	}
	static int[] numbers;

	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(bf.readLine());
		numbers = new int[N+1];
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		for(int i=1; i<=N; i++) numbers[i] = Integer.parseInt(st.nextToken());

		SegmentTree tree = new SegmentTree(N);
		tree.init(1, N, 1);

		int K = Integer.parseInt(bf.readLine());
		for(int i=0; i<K; i++){
			st = new StringTokenizer(bf.readLine(), " ");
			int command = Integer.parseInt(st.nextToken());
			if(command == 1){
				int I = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());

				if((x % 2) != (numbers[I] % 2)){
					if(x % 2 == 0)
						tree.update(1, N, I, 1, false);
					else
						tree.update(1, N, I, 1, true);
				}
				numbers[I] = x;
			}
			else if(command == 2){
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				sb.append(tree.print(1, N, l, r, 1,false)).append("\n");

			}
			else{
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				sb.append(tree.print(1, N, l, r, 1,true)).append("\n");

			}
		}
		System.out.print(sb);
	}
}