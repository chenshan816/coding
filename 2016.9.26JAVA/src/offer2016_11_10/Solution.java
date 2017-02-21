package offer2016_11_10;

import java.util.HashMap;

public class Solution {
	
	//������ʽƥ��
	public boolean match(char[] str, char[] pattern){
		if (str == null || pattern == null) {
	        return false;
	    }
	    int strIndex = 0;
	    int patternIndex = 0;
	    return matchCore(str, strIndex, pattern, patternIndex);
	}
	public boolean matchCore(char[] str, int strIndex, char[] pattern, int patternIndex) {
	    //��Ч�Լ��飺str��β��pattern��β��ƥ��ɹ�
	    if (strIndex == str.length && patternIndex == pattern.length) {
	        return true;
	    }
	    //pattern�ȵ�β��ƥ��ʧ��
	    if (strIndex != str.length && patternIndex == pattern.length) {
	        return false;
	    }
	    //ģʽ��2����*�����ַ�����1����ģʽ��1��ƥ��,��3��ƥ��ģʽ���粻ƥ�䣬ģʽ����2λ
	    if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
	        if (strIndex != str.length && (pattern[patternIndex] == str[strIndex] || pattern[patternIndex] == '.' )) {
	            return matchCore(str, strIndex, pattern, patternIndex + 2)//ģʽ����2����Ϊx*ƥ��0���ַ�
	                    || matchCore(str, strIndex + 1, pattern, patternIndex + 2)//��Ϊģʽƥ��1���ַ�
	                    || matchCore(str, strIndex + 1, pattern, patternIndex);//*ƥ��1������ƥ��str�е���һ��
	        } else {
	            return matchCore(str, strIndex, pattern, patternIndex + 2);
	        }
	    }
	    //ģʽ��2������*�����ַ�����1����ģʽ��1��ƥ�䣬�򶼺���1λ������ֱ�ӷ���false
	    if (strIndex != str.length && (pattern[patternIndex] == str[strIndex] || pattern[patternIndex] == '.')) {
	        return matchCore(str, strIndex + 1, pattern, patternIndex + 1);
	    }
	    return false;
	    }

	//��ʾ��ֵ���ַ��� -- ������ʽ
	public boolean isNumeric(char[] str) {
		  String string = String.valueOf(str);
		  //[\\+-]����ƥ��������������һ���ַ� 0�λ�1һ��	[0-9]�������ƥ��0-9֮�����	(\\.[0-9]*)?��������ƥ��.0-9����Σ�û�л�һ��
	      return string.matches("[\\+-]?[0-9]*(\\.[0-9]*)?([eE][\\+-]?[0-9]+)?");
    }

	//�ַ����е�һ�����ظ����ַ�
	//Insert one char from stringstream
	StringBuilder sb = new StringBuilder();
    public void Insert(char ch)
    {
        sb.append(ch);
    }
    //return the first appearence once char in current stringstream
    //1.����Map
    //2.����bit���ظ�
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
