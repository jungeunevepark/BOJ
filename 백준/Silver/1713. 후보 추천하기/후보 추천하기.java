import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
	   BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	   List<Integer> result = new ArrayList<>();
	   int[] like = new int[101];
	   int pictures = Integer.parseInt(bf.readLine());
	   int likes = Integer.parseInt(bf.readLine());
	   StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
	   for(int i=0; i<likes; i++) {
		   int child = Integer.parseInt(st.nextToken());
		   // 이미 게시된 경우
		   if(like[child] > 0) {like[child]++; continue;}
		   // 사진틀이 비어있는 경우
		   if(result.size() < pictures) {
			   result.add(child);
		   }
		   else {
			   // 제거할 학생 고르기
			   int delete = 0; // result의 index
			   for(int k=1; k<result.size(); k++) {
				   int c = result.get(k);
				   if(like[result.get(delete)] > like[c]) delete = k;
			   }
			   like[result.get(delete)] = 0;
			   result.remove(delete);
			   result.add(child);
		   }
		   like[child]++;
	   }
	   Collections.sort(result);
	   for(int u=0; u<result.size(); u++) System.out.print(result.get(u)+" ");
	}
}