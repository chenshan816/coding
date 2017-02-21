package offer2016_11_9;

import java.util.HashMap;

public class Solution {
	//1.把字符串转换成整数
	//2.sum*10+ch[i]-48;0--Ascii48
	public int StrToInt(String str) {
        if(str == null || str.length() <=0)
        	return 0;
        char[] ch = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        if(ch.length == 1){
        	if(ch[0]=='+' || ch[0] == '-')
        		return 0;
        }
        for(int i=0;i<ch.length;i++){
        	if(i==0 && (ch[0] == '+' ||ch[0] =='-' )){
        		if(ch[0] =='-'){
        			sb.append(ch[0]);
        		}
        	}else{
        		if(ch[i] >= '0' && ch[i] <='9'){
        			sb.append(ch[i]);
        		}else{
        			return 0;
        		}
        	}
        }
        return Integer.parseInt(sb.toString());
    }
	
	//数组中重复的数字
	/**
	 * 
	 * @param numbers: 		an array of integers
	 * @param length:  		the length of array numbers
	 * @param duplication:	(Output) the duplicated number in the array number,length of duplication array is 1
	 * @return
	 */
	//1.最简单的是hashmap
	 public boolean duplicate(int numbers[],int length,int [] duplication) {
		    if(numbers == null || length <=0)
		    	return false;
		    HashMap<Integer, Integer> map = new HashMap<Integer,Integer>();
		    for(int i=0;i<length;i++){
		    	if(map.containsKey(numbers[i])){
		    		duplication[0] = numbers[i];
		    		return true;
		    	}else{
		    		map.put(numbers[i], 1);
		    	}
		    }
		    return false;
	 }
	 
	//2.bit排序，因为数组总所有数字的范围是知道的 --占用空间更少
	 public boolean duplicate1(int numbers[],int length,int [] duplication){
		 if(numbers == null || length <=0)
		    	return false;
		 boolean[] bit = new boolean[length];
		 for(int i=0;i<length;i++){
			 if(bit[numbers[i]]==true){
				 duplication[0] = numbers[i];
				 return true;
			 }else{
				 bit[numbers[i]] =true;
			 }
		 }
		 return false;
	 }
	 
	//3.不使用额外的空间
	 public boolean duplicate2(int numbers[],int length,int [] duplication){
		 for(int i=0;i< length;i++){
			 int index = numbers[i];
			 if(index >= length)
				 index -= length;
			 if(numbers[index] >= length){
				 duplication[0] = index;
				 return true;
			 }
			 numbers[index] = numbers[index] + length;
		 }
		 return false;
	 }
	 
	 //构建乘积数组
	 //1.用一个数组保存i前面的数
	 //2.用一个数组保存i后面的数的乘积  时间复杂度：o(n)	空间复杂度：o(n)
	 public int[] multiply(int[] A) {
		 if(A == null || A.length <=0)
			 return null;
		 int length = A.length;
		 int[] B = new int[length];
		 int[] head = new int[length];
		 int[] tail = new int[length];
		 head[0] =1;
		 tail[length-1] =1;
		 for(int i =1;i<length;i++){
			 head[i] = head[i-1]*A[i-1];
		 }
		 for(int i=length-2;i>=0;i--){
			 tail[i] = tail[i+1]*A[i+1];
		 }
		 for(int i=0;i<length;i++){
			 B[i] = head[i]*tail[i];
		 }
		 return B;
	 }
}
