import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Meeting implements Comparable<Meeting> {
	public int start;
	public int end;

	public Meeting(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(Meeting o) {
		int r = Integer.compare(end, o.end);
		if (r == 0)
			r = Integer.compare(start, o.start);
		return r;
	}
}

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(bf.readLine());
		Meeting[] m = new Meeting[M];
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			m[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(m);
		int end = -1;
		int result = 0;
		for (int i = 0; i < M; i++) {
			if (m[i].start >= end) {
				result++;
				end = m[i].end;
			}
		}
		System.out.println(result);
	}
}