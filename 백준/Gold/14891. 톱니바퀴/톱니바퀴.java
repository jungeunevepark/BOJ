import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String[] settings = new String[4];
        for (int i = 0; i < 4; i++)
            settings[i] = sc.next();
        int k = sc.nextInt();
        for (int i = 0; i < k; i++) {
            int num = sc.nextInt();
            int vector = sc.nextInt();
            int[] result = new int[4];
            num--;
            result[num] = vector;
            for (int j = num - 1; j >= 0; j--) {
                if (settings[j].charAt(2) != settings[j + 1].charAt(6))
                    result[j] = (-1) * result[j + 1];
                else
                    result[j] = 0;
            }
            for (int j = num + 1; j < 4; j++) {
                if (settings[j].charAt(6) != settings[j - 1].charAt(2))
                    result[j] = (-1) * result[j - 1];
                else
                    result[j] = 0;
            }
            for (int j = 0; j < 4; j++)
                change_setting(settings, j, result[j]);
        }
        int result = 0;
        for(int i=0; i<4; i++) {
        	result += (settings[i].charAt(0) - '0') * Math.pow(2, i);
        }
        System.out.println(result);
    }

    static void change_setting(String[] s, int num, int vector) {
        String news = "";
        if (vector == 0)
            return;
        else if (vector == -1) {
            news = s[num].substring(1, 8);
            news += String.valueOf(s[num].charAt(0));
        } else if (vector == 1) {
            news = String.valueOf(s[num].charAt(7));
            news += s[num].substring(0, 7);
        }
        s[num] = news;
    }
}