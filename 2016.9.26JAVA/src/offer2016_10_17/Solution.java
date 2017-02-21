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
	 * :对于一个数A，若是A的左边累计数非负，那么加上A能使得值不小于A，认为累计值对
                        整体和是有贡献的。如果前几项累计值负数，则认为有害于总和，total记录当前值。
    	此时 若和大于maxSum 则用maxSum记录下来
	 */
	public static int FindGreatestSumOfSubArray(int[] array) {
        int size = array.length;
        if(array ==null || size<=0) return 0;
        int sum=array[0];
        int max=array[0];
        //如果右边的综合非负值，则可以进行累加
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
	//整数中1出现的次（从1到n整数中1出现的次数）
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
	//把数组排成最小的数，这种时候要想到字符串的排序，一位一位进行比较
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
	//丑数=》把只包含因子2、3和5的数称作丑数(Ugly Number)
	public static int GetUglyNumber_Solution(int index) {
        //连续除以2，最终是1则表示它只有2这个因子
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
		
		//后面的丑数是有前一个丑数乘以2，3，5中的一个得来。因此可以用动态规划去解
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
