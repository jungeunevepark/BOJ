import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] alpha = new int[26];

        int N = Integer.parseInt(bf.readLine());
        String cat = bf.readLine();
        alpha[cat.charAt(0) - 'a']++;
        int result = 0; //정답
        int start = 0; //투포인터 시작값
        int end = 1; //투포인터 끝값
        int now = 1; // start~end의 차이
        int count = 1;  //현재 알파벳 종류의 개수

        while(end < cat.length()){
//            System.out.println(start+", "+(end-1)+", "+Arrays.toString(alpha));
            if(count < N){
//                System.out.println("111, "+end+", "+count+", "+Arrays.toString(alpha));
                if(alpha[cat.charAt(end)-'a'] > 0){
                    alpha[cat.charAt(end)-'a']++;
                }else{
                    alpha[cat.charAt(end)-'a']++;
                    count++;
                }
                result = Math.max(result, end-start+1);
                end++;
            }
            else{
//                System.out.println("222, "+end+", "+count+", "+Arrays.toString(alpha));
                if(alpha[cat.charAt(end)-'a'] > 0){
                    alpha[cat.charAt(end)-'a']++;
                    result = Math.max(result, end-start+1);
                    end++;
                }else{
                    //이 로직은 종류를 잃거나 그대로기 때문에 result를 업데이트할 필요가 없다.
                    if(alpha[cat.charAt(start)-'a'] > 1){
                        alpha[cat.charAt(start)-'a']--;
                    }else{
                        alpha[cat.charAt(start)-'a']--;
                        count--; // start와 같은 값을 가진 것은 start 뿐이었으므로 종류가 1개 줄어들어야 한다.
                    }
                    start++;
                }
            }
//            System.out.println(start+", "+end+", "+count);
        }
        System.out.println(result);


    }
}