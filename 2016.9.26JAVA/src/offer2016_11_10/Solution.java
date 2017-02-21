package offer2016_11_10;

import java.util.HashMap;

public class Solution {
	
	//正则表达式匹配
	public boolean match(char[] str, char[] pattern){
		if (str == null || pattern == null) {
	        return false;
	    }
	    int strIndex = 0;
	    int patternIndex = 0;
	    return matchCore(str, strIndex, pattern, patternIndex);
	}
	public boolean matchCore(char[] str, int strIndex, char[] pattern, int patternIndex) {
	    //有效性检验：str到尾，pattern到尾，匹配成功
	    if (strIndex == str.length && patternIndex == pattern.length) {
	        return true;
	    }
	    //pattern先到尾，匹配失败
	    if (strIndex != str.length && patternIndex == pattern.length) {
	        return false;
	    }
	    //模式第2个是*，且字符串第1个跟模式第1个匹配,分3种匹配模式；如不匹配，模式后移2位
	    if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
	        if (strIndex != str.length && (pattern[patternIndex] == str[strIndex] || pattern[patternIndex] == '.' )) {
	            return matchCore(str, strIndex, pattern, patternIndex + 2)//模式后移2，视为x*匹配0个字符
	                    || matchCore(str, strIndex + 1, pattern, patternIndex + 2)//视为模式匹配1个字符
	                    || matchCore(str, strIndex + 1, pattern, patternIndex);//*匹配1个，再匹配str中的下一个
	        } else {
	            return matchCore(str, strIndex, pattern, patternIndex + 2);
	        }
	    }
	    //模式第2个不是*，且字符串第1个跟模式第1个匹配，则都后移1位，否则直接返回false
	    if (strIndex != str.length && (pattern[patternIndex] == str[strIndex] || pattern[patternIndex] == '.')) {
	        return matchCore(str, strIndex + 1, pattern, patternIndex + 1);
	    }
	    return false;
	    }

	//表示数值的字符串 -- 正则表达式
	public boolean isNumeric(char[] str) {
		  String string = String.valueOf(str);
		  //[\\+-]？：匹配所包含的任意一个字符 0次或1一次	[0-9]：任意次匹配0-9之间的数	(\\.[0-9]*)?：（任意匹配.0-9任意次）没有或一次
	      return string.matches("[\\+-]?[0-9]*(\\.[0-9]*)?([eE][\\+-]?[0-9]+)?");
    }

	//字符流中第一个不重复的字符
	//Insert one char from stringstream
	StringBuilder sb = new StringBuilder();
    public void Insert(char ch)
    {
        sb.append(ch);
    }
    //return the first appearence once char in current stringstream
    //1.还是Map
    //2.还是bit查重复
    public char FirstAppearingOnce()
    {
    	if(sb.length() <=0)
    		return '#';
    	HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    	char[] ch = sb.toString().toCharArray();
    	for(int i=0;i<ch.length;i++){
    		if(map.containsKey(ch[i])){
    			int v = map.get(ch[i]);
    			map.put(ch[i], v+1);
    		}else{
    			map.put(ch[i], 1);
    		}
    	}
    	for(int i=0;i<ch.length;i++){
    		if(map.get(ch[i]) == 1)
    			return ch[i];
    	}
    	return '#';
    }
    public char FirstAppearingOnce1(){
    	int[] bitMap = new int[256];
    	if(sb.length() <=0)
    		return '#';
    	char[] ch = sb.toString().toCharArray();
    	for(int i=0;i<ch.length;i++){
    		int index = bitMap[ch[i]];
    		if(index == 0)
    			bitMap[ch[i]] =1;
    		else{
    			bitMap[ch[i]] = index +1;
    		}
    	}
    	for(int i=0;i<ch.length;i++){
    		if(bitMap[ch[i]] == 1)
    			return ch[i];
    	}
    	return '#';
    }


}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
