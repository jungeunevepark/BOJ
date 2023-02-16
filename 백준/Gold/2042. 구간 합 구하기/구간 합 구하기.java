import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SegmentTree {
	long[] tree;
	int treeSize;

	public SegmentTree(int n) {
		int treeHeight = (int) Math.ceil(Math.log(n) / Math.log(2));
		this.treeSize = (int) Math.pow(2, treeHeight + 1);
		tree = new long[treeSize];
	}

	public long init(long[] arr, int node, int start, int end) {
		if (start == end) {
			return tree[node] = arr[start];
		}
		return tree[node] = init(arr, node * 2, start, (start + end) / 2)
				+ init(arr, node * 2 + 1, (start + end) / 2 + 1, end);
	}

	public void update(int node, int start, int end, int idx, long diff) {
		if (start > idx || end < idx)
			return;

		tree[node] += diff;

		if (start != end) {
			update(node * 2, start, (start + end) / 2, idx, diff);
			update(node * 2 + 1, (start + end) / 2 + 1, end, idx, diff);
		}
	}

	public long get(int node, int starti, int endi, int start, int end) {
		if (starti > end || endi < start)
			return 0;

		if (starti <= start && end <= endi)
			return tree[node];
		else
			return get(node * 2, starti, endi, start, (start + end) / 2)
					+ get(node * 2 + 1, starti, endi, (start + end) / 2 + 1, end);
	}
}

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		long[] numbers = new long[N];
		for (int i = 0; i < N; i++)
			numbers[i] = Long.parseLong(bf.readLine());
		SegmentTree sg = new SegmentTree(N);
		sg.init(numbers, 1, 0, N - 1);
		for (int i = 0; i < K + M; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			if (a == 1) {
				sg.update(1, 0, N - 1, b - 1, c - numbers[b - 1]);
				numbers[b - 1] = c;
			} else if (a == 2) {
				// System.out.println(b + ", " + c);
				sb.append(sg.get(1, b - 1, (int) c - 1, 0, N - 1)).append("\n");
			}
		}
		System.out.println(sb);
	}
}