package offer2016_10_17;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class Solution {

	public static void main(String[] args) {
		
//		int[] data = {-2,-8,-1,-5,-9};
//		System.out.println(FindGreatestSumOfSubArray(data));
		System.out.println(GetUglyNumber_Solution(3)); 
	}
	/**
	 * :����һ����A������A������ۼ����Ǹ�����ô����A��ʹ��ֵ��С��A����Ϊ�ۼ�ֵ��
                        ��������й��׵ġ����ǰ�����ۼ�ֵ����������Ϊ�к����ܺͣ�total��¼��ǰֵ��
    	��ʱ ���ʹ���maxSum ����maxSum��¼����
	 */
	public static int FindGreatestSumOfSubArray(int[] array) {
        int size = array.length;
        if(array ==null || size<=0) return 0;
        int sum=array[0];
        int max=array[0];
        //����ұߵ��ۺϷǸ�ֵ������Խ����ۼ�
        for(int i =1;i<size;i++){
        	if(sum>=0)
        		sum += array[i];
        	else
        		sum=array[i];
        	if(sum>max)
        		max=sum;
        }
        return max;
    }
	//������1���ֵĴΣ���1��n������1���ֵĴ�����
	public static int NumberOf1Between1AndN_Solution(int n) {
		int num=0;
		for(int i=1;i<=n;i++){
			String s = Integer.toString(i);
			char[] cs=s.toCharArray();
			for(char c:cs){
				if(c=='1')
					num++;
			}
		}
		return num;
    }
	//�������ų���С����������ʱ��Ҫ�뵽�ַ���������һλһλ���бȽ�
	public String PrintMinNumber(int [] numbers) {
		int n = numbers.length;
		String s="";
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<n;i++){
			list.add(numbers[i]);
		}
		Collections.sort(list, new Comparator<Integer>(){

			@Override
			public int compare(Integer o1, Integer o2) {
				String s1 = o1+""+o2;
				String s2 = o2+""+o1;
				return s1.compareTo(s2);
			}
		});
		for(int j:list){
			s+=j;
		}
		return s;
    }
	//����=����ֻ��������2��3��5������������(Ugly Number)
	public static int GetUglyNumber_Solution(int index) {
        //��������2��������1���ʾ��ֻ��2�������
//		if(index <=0)
//			return 0;
//		int number =0;
//		int uglyFound =0;
//		while(uglyFound < index){
//			++number;
//			if(IsUgly(number)){
//				++uglyFound;
//			}
//		}
//		return number;
		
		//����ĳ�������ǰһ����������2��3��5�е�һ����������˿����ö�̬�滮ȥ��
		if(index <=0) return 0;
		if(index == 1)return 1;
		int[] k = new int[index];
		k[0]=1;
		int t2=0,t3=0,t5=0;
		for(int i=1;i<index;i++){
			k[i] =Math.min(k[t2]*2, Math.min(k[t3]*3, k[t5]*5));
			if(k[i]==k[t2]*2) t2++;
			if(k[i]==k[t3]*3) t3++;
			if(k[i]==k[t5]*5) t5++;
		}
		return k[index-1];
    }
	static Boolean IsUgly(int number){
		while(number %2==0)
			number /=2;
		while(number %3==0)
			number /=3;
		while(number %5==0)
			number /=5;
		return (number == 1)?true:false;
	}
}
