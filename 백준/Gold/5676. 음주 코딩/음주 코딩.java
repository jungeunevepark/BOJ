import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class segmentTree{
		long[] tree;
		public segmentTree(int N){
			int k=1; int n=1;
			while(k < N){
				k *= 2;
				n+=k;
			}
			tree = new long[n+1];
		}

		long init(int start, int end, int node){
			if(start == end) {
				if(numbers[start] < 0)
					return tree[node] = -1;
				else if(numbers[start] > 0)
					return tree[node] = 1;
				return 0;
			}
			int mid = (start + end) / 2;
			return tree[node] = init(start, mid, 2*node)*init(mid+1, end, 2*node+1);
		}

		void update(int start, int end, int node, int vertex, double diff){
			if(start <= vertex && vertex <= end){
				tree[node] *= diff;
				int mid = (start+end)/2;
				if(start != end){
					update(start, mid, node*2, vertex, diff);
					update(mid+1, end,node*2+1, vertex, diff);
				}
			}
		}

		long print(int start, int end, int left, int right, int node){ // 현재 node가 가진 범위 (start~end), 출력해야할 범위 (left~right)
			if(left <= start && end <= right) return tree[node];
			else if(end < left || right < start) return 1;
			else return print(start, (start+end)/2, left, right, 2*node) * print((start+end)/2+1, end, left, right, 2*node+1);
		}

	}
	static int[] numbers;

	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String k = "";
		while((k = bf.readLine())!=null) {
			StringTokenizer st = new StringTokenizer(k, " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			numbers = new int[N+1];
			st = new StringTokenizer(bf.readLine(), " ");
			for(int i=1; i<=N; i++)
				numbers[i] = Integer.parseInt(st.nextToken());

			segmentTree trees = new segmentTree(N);
			trees.init(1, N, 1);

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(bf.readLine(), " ");
				char type = st.nextToken().charAt(0);
				if (type == 'C') {
					// 변경
					int I = Integer.parseInt(st.nextToken());
					int V = Integer.parseInt(st.nextToken());
					if (numbers[I] == 0) {
						trees = new segmentTree(N);
						numbers[I] = V;
						trees.init(1, N, 1);
					} else if(numbers[I] * V < 0){
						trees.update(1, N, 1, I, -1);
						numbers[I] = V;
					}else if(V == 0){
						trees.update(1, N, 1, I, 0);
						numbers[I] = V;
					}
				} else {
					int I = Integer.parseInt(st.nextToken());
					int J = Integer.parseInt(st.nextToken());
					long result = trees.print(1, N, I, J, 1);
					if (result == 0)
						sb.append(0);
					else if (result < 0)
						sb.append("-");
					else
						sb.append("+");
				}
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());

	}
}