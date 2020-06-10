

import java.util.Arrays;

public class KMP {
    public static void main(String[] args) {
        String str1 = "abcaaabcaaaabcaaxabcaaay";
        String str2 = "abcaax";
        System.out.println("next arr");
        System.out.println(Arrays.toString(generateNext(str2)));
        System.out.println("kmp methos");
        System.out.println(indexOf(str1, str2));
        System.out.println("java method");
        System.out.println(str1.indexOf(str2));
    }

    public static int indexOf(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int i = 0, j = 0;
        int[] next = generateNext(str2);
        while (i < len1 && j < len2) {
            if (str1.charAt(i) == str2.charAt(j)) {
                i++;
                j++;
            } else if(next[j]==-1){
                i++;
            }else{
                j = next[j];
            }
        }
        return j == len2 ? i - j : -1;
    }

    public static int[] generateNext(String s) {
        int len = s.length();
        if (len == 1) {
            return new int[-1];
        }
        int[] next = new int[len];
        // next数组前两个的值时认为规定的
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cur = 0;  // cur=next[i-1] 需要一直维持其等于所要判断的子串的长度
        while (i < len) {
            if (s.charAt(i - 1) == s.charAt(cur)) {
                next[i] = ++cur;
                i++;
            } else if(cur>0){  // 如果此时子串依旧存在前后缀长度相等
                cur = next[cur];
            }else{
                next[i] = 0;
                i++;
            }
        }
        return next;

    }
}
