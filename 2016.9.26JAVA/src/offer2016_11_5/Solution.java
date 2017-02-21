package offer2016_11_5;

import java.util.ArrayList;


public class Solution {

	public static void main(String[] args) {
//		String str = "abcXYZdef";
//		System.out.println(LeftRotateString2(str, 10));
		System.out.println(Add(5,3)); 
	}

	// ����ת�ַ���
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
	// ����String���Ӵ��ķ���
	public static String LeftRotateString1(String str, int n) {
		int length = str.length();
		if (length <= 0)
			return "";
		str += str;
		return str.substring(n, n + length);
	}
	// ����reverse����
	// A=(abc)��B=(defg)��ԭ����ΪAB������Ҫ�Ľ��ΪBA���������ô����(ArBr)r = ((B)r)r((A)r)r=BA
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

	//��ת����˳����
	//1.ʹ��split���зָ���������
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

	//�˿���˳��
	//1.����0����û�������ظ�����
	//2.max-min<=5
	public static boolean isContinuous(int [] numbers) {
		if(numbers.length != 5)
			return false;
		int max=-1;
		int min=14;
		//1.ʹ������
//		ArrayList<Integer> list = new ArrayList<Integer>();
		//2.ʹ�ñ�־
		int flag =0;//bitMap--����1-13��С�Ŀռ䣬���������3�����λ����1
		for(int i=0;i<5;i++){
			if(numbers[i] !=0){
//				if(list.contains(numbers[i]))
//					return false;
//				list.add(numbers[i]);
				if(((flag >> numbers[i])&1) == 1) return false;//˵��֮ǰͬ����λ���Ѿ��и���
				flag |= (1<<numbers[i]);//����λ��־Ϊ1
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
	
	//�����ǵ���Ϸ(ԲȦ�����ʣ�µ���)
	//�ܹ���n��С���ѣ����Ϊ0~n-1�����Ϊm-1��С���ѳ���
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

	//��1+2+3+...+n��Ҫ����ʹ�ó˳�����for��while��if��else��switch��case�ȹؼ��ּ������ж���䣨A?B:C��
	//��·��ֵԭ��
	public int Sum_Solution(int n) {
        int sum =n;
        boolean ans = (n>0) &&((sum += Sum_Solution(n-1))>0);//���n<=0ʱ,���β��ټ��㣬�ݹ��ֹͣ��
        return sum;
    }

	//���üӼ��˳����ӷ� �����߼�����
	//��λ���õ����û�н�λ�ĺ�
	//��λ��õ�ÿһλ�Ľ�λ ������λ�ٺ����Ľ����ӣ������������ǽ�λû��1
	public static int Add(int num1,int num2) {
        while(num2 !=0){
        	num1 ^= num2;
        	num2 = ((num1^num2)&num2) <<1;
        }
        return num1;
    }

	//���ַ���ת��������
	
}
