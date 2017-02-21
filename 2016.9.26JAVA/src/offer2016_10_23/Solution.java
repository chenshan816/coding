package offer2016_10_23;


import java.util.LinkedHashMap;


public class Solution {
	//	第一个只出现一次的字符
	 public int FirstNotRepeatingChar(String str) {
		 LinkedHashMap<Character, Integer> map = new LinkedHashMap<Character, Integer>();
	        for(int i=0;i<str.length();i++){
	            if(map.containsKey(str.charAt(i))){
	                int time = map.get(str.charAt(i));
	                map.put(str.charAt(i), ++time);
	            }
	            else {
	                map.put(str.charAt(i), 1);
	            }
	        }
	        int pos = -1;  
	        int i=0;
	        for(;i<str.length();i++){
	            char c = str.charAt(i);
	            if (map.get(c) == 1) {
	                return i;
	            }
	        }
	        return pos;
	    }
}
