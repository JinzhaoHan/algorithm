// 经典双指针 固定窗口解法
 public int numKLenSubstrNoRepeats(String s, int k) {
        int len = s.length();
        if(k>len ||k>26) return 0;

        int[] pos = new int[26];
        Arrays.fill(pos, -1);  
        // -1表示不存在该字母， >=0表示存储的该字母的上一个位置
        char[] arr= s.toCharArray();
        int count = 0;
        int left=0;
        int right=0;
        while(right<len){
            char c = arr[right];           
            
            if(pos[c-'a']!=-1){
                // 找出该位置的上一次出现位置
                left = Math.max(left, pos[c-'a']+1);
            }
            if(right-left+1==k){
                count++;
                left++;
            }
            pos[c-'a'] = right;
            right++;
        }
        
        return count;

    }
