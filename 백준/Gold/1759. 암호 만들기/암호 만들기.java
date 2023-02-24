import java.io.*;
import java.util.*;


public class Main {
	static int L, C;
	static int mom, son;
	static List<Character> momdict;
	static List<Character> sondict;
	static List<String> result;
	static char[] answer;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		answer = new char[L];
		momdict = new ArrayList<>();
		sondict = new ArrayList<>();
		result = new ArrayList<>();
		st = new StringTokenizer(bf.readLine(), " ");
		for(int i=0; i<C; i++) {
			char c = st.nextToken().charAt(0);
			if(c == 'a' || c == 'u' || c == 'o' || c == 'i' || c == 'e') 
				{momdict.add(c); mom++;}
			else {sondict.add(c); son++;}
		}
		Collections.sort(sondict);
		Collections.sort(momdict);
		for(int i = 2; i<=son; i++) {
			for(int j=1; j<=mom; j++) {
				if(i+j == L) { // 자음은 i개 모음은 j개 합쳐서 L개
					//System.out.println(i+", "+j);
					comb_son(0, 0, i);
				}
			}
		}
		Collections.sort(result);
		for(int i=0; i<result.size(); i++)
			System.out.println(result.get(i));
	}
	static void comb_son(int start, int cnt, int check) {
		if(cnt == check) {
			comb_mom(0, check, L);
			return;
		}
		for(int i=start; i<son; i++) {
			answer[cnt] = sondict.get(i);
			comb_son(i+1, cnt+1, check);
		}
	}
	static void comb_mom(int start, int cnt, int check) {
		//System.out.println(cnt);
		if(cnt == check) {
			char[] temp = Arrays.copyOf(answer, answer.length);
			Arrays.sort(temp);
			String s = "";
			for(int i=0; i<L; i++)
				s+=temp[i];
			result.add(s);
			return;
		}
		
		for(int i=start; i<mom; i++) {
			answer[cnt] = momdict.get(i);
			comb_mom(i+1, cnt+1, check);
		}
	}
}