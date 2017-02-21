package offer2016_11_5;

import java.util.ArrayList;


public class Solution {

	public static void main(String[] args) {
//		String str = "abcXYZdef";
//		System.out.println(LeftRotateString2(str, 10));
		System.out.println(Add(5,3)); 
	}

	// 左旋转字符串
	public static String LeftRotateString(String str, int n) {
		if (str == null || str.length() <= 0)
			return "";
		char[] chars = str.toCharArray();
		int size = chars.length;
		int num = n % size;
		String result = "";
		char[] copyChars = new char[size];
		for (int i = num; i < size; i++) {
			copyChars[i - num] = chars[i];
		}
		for (int i = 0; i < num; i++) {
			copyChars[size - n + i] = chars[i];
		}
		for (int i = 0; i < size; i++) {
			result += copyChars[i];
		}
		return result;
	}
	// 利用String的子串的方法
	public static String LeftRotateString1(String str, int n) {
		int length = str.length();
		if (length <= 0)
			return "";
		str += str;
		return str.substring(n, n + length);
	}
	// 利用reverse操作
	// A=(abc)，B=(defg)，原序列为AB，我们要的结果为BA，则可以这么做：(ArBr)r = ((B)r)r((A)r)r=BA
	public static String LeftRotateString2(String str, int n) {
		int length = str.length();
		if (length <= 0)
			return "";
		char[] chars = str.toCharArray();
		n %= length;
		reverse(chars, 0, n - 1);
		reverse(chars, n, length - 1);
		reverse(chars, 0, length - 1);
		return String.valueOf(chars);
	}
	private static void reverse(char[] chars, int start, int end) {
		char temp;
		while (start < end) {
			temp = chars[start];
			chars[start] = chars[end];
			chars[end] = temp;
			start++;
			end--;
		}
	}

	//翻转单词顺序列
	//1.使用split进行分割，再重新组合
	public static String ReverseSentence(String str) {
        String reStr="";
		if(str == null || str.length()<=0 )
        	return reStr;
		String[] temp = str.split(" ");
        for(int i=temp.length-1;i>0;i++){
        	reStr += temp[i];
        	reStr += " ";
        }
        reStr += temp[0];
        return reStr;
    }

	//扑克牌顺子
	//1.除了0以外没有其他重复的数
	//2.max-min<=5
	public static boolean isContinuous(int [] numbers) {
		if(numbers.length != 5)
			return false;
		int max=-1;
		int min=14;
		//1.使用数组
//		ArrayList<Integer> list = new ArrayList<Integer>();
		//2.使用标志
		int flag =0;//bitMap--开辟1-13大小的空间，如果数等于3则第三位等于1
		for(int i=0;i<5;i++){
			if(numbers[i] !=0){
//				if(list.contains(numbers[i]))
//					return false;
//				list.add(numbers[i]);
				if(((flag >> numbers[i])&1) == 1) return false;//说明之前同样的位置已经有该数
				flag |= (1<<numbers[i]);//将该位标志为1
				if(numbers[i]<min)
					min=numbers[i];
				else if(numbers[i] >max)
					max=numbers[i];
			}
		}
		if((max-min)<5)
			return true;
		return false;
    }
	
	//孩子们的游戏(圆圈中最后剩下的数)
	//总共有n个小朋友，编号为0~n-1，编号为m-1的小朋友出列
	public int LastRemaining_Solution(int n, int m) {
        if(n==0) return -1;
        if(n==1) return 0;
        else 
        	return LastRemaining_Solution(n-1,m)+m%n;
    }
	public int LastRemaining_Solution1(int n, int m){
		 if(n<1||m<1) return -1;
	     int[] array = new int[n];
	     int i=-1,step=0,count =n;
	     while(count>0){
	    	 i++;
	    	 if(i>=n) i=0;
	    	 if(array[i] == -1) continue;
	    	 step++;
	    	 if(step == m){
	    		 array[i] = -1;
	    		 step=0;
	    		 count--;
	    	 }
	     }
	     return i;
	}

	//求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）
	//短路求值原理
	public int Sum_Solution(int n) {
        int sum =n;
        boolean ans = (n>0) &&((sum += Sum_Solution(n-1))>0);//如果n<=0时,后半段不再计算，递归就停止了
        return sum;
    }

	//不用加减乘除做加法 利用逻辑运算
	//按位异或得到获得没有进位的和
	//按位与得到每一位的进位 左移移位再和异或的结果相加，最终条件就是进位没有1
	public static int Add(int num1,int num2) {
        while(num2 !=0){
        	num1 ^= num2;
        	num2 = ((num1^num2)&num2) <<1;
        }
        return num1;
    }

	//把字符串转换成整数
	
}
