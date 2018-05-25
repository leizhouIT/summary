package leetcode.test;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class LC3 {


    public static int lengthOfLongestSubstring(String s){
        if (StringUtils.isEmpty(s)){
            return 0;
        }
        int start=0;
        int result =0;
        Map<Character,Integer> map = new HashMap<>();
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if (map.containsKey(c) && map.get(c)>=start){
                start=map.get(c)+1;
            }else {
                result = Math.max(result,i-start+1);
            }
            map.put(c,i);
        }
        return result;
    }
}
