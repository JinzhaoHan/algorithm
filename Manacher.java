package manacher;

/**
 * 求一个字符串的最长回文子串
 * 遍历每一个字符为中心位置的情况，得到最大半径
 */
public class Manacher {
    public static void main(String[] args) {
        String s = "babad";
        String result = longestPalindrome(s);
        System.out.println("reuslt is " + result);
    }

    public static String longestPalindrome(String s) {
        char[] data = addSharp(s);
        // 构造回文中心和半径数组
        int len = data.length;
        int[] middles = new int[len];
        int[] radius = new int[len];
        int R = -1;   // 初始右边界设置为-1
        int left, right, count, mid, i2, L;
        int maxRadius = -1;
        int startIdx = 0;
        for (int i = 0; i < len; i++) {
            // 情况一需要暴力扩充边界
            if (i >= R) {
                count = 0;
                left = i - 1;
                right = i + 1;
                while (left >= 0 && right < len && data[left] == data[right]) {
                    count++;
                    left--;
                    right++;
                }
                radius[i] = count;
                if (right - 1 > R) {
                    R = right - 1;
                    middles[R] = i;
                }
                if (radius[i] > maxRadius) {
                    maxRadius = radius[i];
                    startIdx = i;
                }
                continue;
            }
            // i <R 的时候分三种情况讨论
            mid = middles[R];
            L = 2 * mid - R;
            i2 = 2 * mid - i;

            if (i2 - radius[i2] < L) {
                radius[i] = R - i;
            } else if (i2 - radius[i2] > L) {
                radius[i] = radius[i2];
            } else {
                // 从边界处往外扩
                count = radius[i2];
                left = i - count - 1;
                right = i + count + 1;
                while (left >= 0 && right < len && data[left] == data[right]) {
                    count++;
                    left--;
                    right++;
                }
                radius[i] = count;
                if (right - 1 > R) {
                    R = right - 1;
                    middles[R] = i;
                }
            }
            if (radius[i] > maxRadius) {
                maxRadius = radius[i];
                startIdx = i;
            }

        }
        // System.out.println(Arrays.toString(data));
        // System.out.println(Arrays.toString(radius));
        String result = new String(data).substring(startIdx - maxRadius, startIdx + maxRadius + 1);
        return result.replace("#", "");

    }

    public static char[] addSharp(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append("#").append(s.charAt(i));
        }
        sb.append("#");
        return sb.toString().toCharArray();
    }
}
