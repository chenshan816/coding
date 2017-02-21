package offer2016_11_4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;


public class Solution {
	//判断该二叉树是否是平衡二叉树
	private boolean isBalanced = true;
	public boolean IsBalanced_Solution(TreeNode root) {
        if(root == null)
        	return true;
        TreeNode t = root;
        TreeHigh(t);
        return isBalanced;
    }
	int TreeHigh(TreeNode t){
		//1.生成栈
		if(t == null)
			return 0;
        int left = TreeHigh(t.left);
        int right=TreeHigh(t.right);
        if(Math.abs(left-right)>1){
        	isBalanced = false;
        }
        return right>left?right+1:left+1;
	}
	
	public static void main(String[] args) {
		int[] array = {1,2,4,7,11,16};
//		int[] num1=new int[1],num2 = new int[1];
//		FindNumsAppearOnce1(array,num1 ,num2);
		int sum = 17;
		ArrayList<Integer> list = FindNumbersWithSum1(array,sum);
		System.out.println(list.get(0)+","+list.get(1));
	}
	//数组中只出现一次的数字
	//num1,num2分别为长度为1的数组。传出参数
	//将num1[0],num2[0]设置为返回结果
	public static void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        //放到字典中遍历一次，如果存在则不是想要的结果
		if(array == null || array.length <=0){
			return;
		}
		else{
			HashMap map = new HashMap();
			int num =0;
			boolean first=true;
			for(int i=0;i<array.length;i++){
				if(map.containsKey(array[i]) == false){
					map.put(array[i], 1);
				}else{
					map.put(array[i], 2);
				}
			}
			for(int i=0;i<array.length;i++){
				num =(int) map.get(array[i]);
				if(num == 1 && first){
					num1[0] =array[i];
					first = false;
				}else if(num == 1){
					num2[0] =array[i];
				}
			}
		}
    }

	//另一种想法，异或——任何数字异或自己都等于0
	public static void FindNumsAppearOnce1(int [] array,int num1[] , int num2[]){
		if(array.length < 2) return;
		int myxor =0;
		int flag =1;
		for(int i=0;i<array.length;i++){
			myxor ^=array[i];
		}
		while((myxor & flag)==0) flag <<= 1; //判断第一个等于1的位置,说明出现一次的两个数这一位相同
		num1[0] = myxor;
		num2[0] = myxor;
		for(int i =0;i<array.length;i++){
			if((flag &array[i]) == 0) num2[0] ^=array[i];//再一起与一次，则其他还是0，可以获得不同的数
			else num1[0] ^= array[i];
		}
	}

	//和为S的连续正数序列
	//(an+a1)(an-a1+1)=2s=k*l ==> an=(k+l-1)/2 a1 = (k-l+1)/2
	public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
	       ArrayList<ArrayList<Integer>> arrayList = new ArrayList<ArrayList<Integer>>();
	       if(sum <3) return arrayList;
	       int s = (int)Math.sqrt(sum*2);
	       for(int i=s;i>=2;i--){
	    	   if(2*sum%i==0){
	    		   int d = 2*sum/i;
	    		   if(d%2==0 && i%2!=0 || d%2!=0&&i%2==0){
	    			   int an = (d+i-1)/2;
	    			   int a1 =(d-i+1)/2;
	    			   ArrayList<Integer> list = new ArrayList<Integer>();
	    			   for(int j = a1;j<=an;j++)
	    				   list.add(j);
	    			   arrayList.add(list);
	    		   }
	    	   }
	       }
	       return arrayList;
	}
	
	//和为S的两个数字
	//array是递增的因此可以使用二分法进行查找 可以有负数？o(nlogn)
	public static ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(array == null || array.length < 2)
        	return list;
        int min=65536;
        int num1=0;
        int num2=0;
        for(int i=0;i<array.length;i++){
        	if(array[i] > sum/2)
        		break;
        	int d=sum-array[i];
        	if((d*array[i]) < min){
        		if(divisionSort(array,i,array.length,d)){
        			min = d*array[i];
        			num1 =array[i];
        			num2 = d;
        		}
        	}
        }
        if(min==65536)
        	return list;
        list.add(num1);
        list.add(num2);
        return list;
    }
	static boolean divisionSort(int[] array,int start,int end,int sum){
		if(start > end)
			return false;
		int mid = (start + end)/2;
		if(array[mid] == sum)
			return true;
		else if(array[mid] > sum){
			end = mid-1;
			return divisionSort(array,start,end,sum);
		}else{
			start = mid +1;
			return divisionSort(array,start,end,sum);
		}
	}
	//o(n)
	/**
	 * 数列满足递增，设两个头尾两个指针i和j，
	 * 若ai + aj == sum，就是答案（相差越远乘积越小）
	 * 若ai + aj > sum，aj肯定不是答案之一（前面已得出 i 前面的数已是不可能），j -= 1
	 * 若ai + aj < sum，ai肯定不是答案之一（前面已得出 j 后面的数已是不可能），i += 1
	 */
	public static ArrayList<Integer> FindNumbersWithSum1(int [] array,int sum){
		 ArrayList<Integer> list = new ArrayList<Integer>();
	        if(array == null || array.length < 2)
	        	return list;
	        int i=0;
	        int j=array.length-1;
	        while(i<j){
	        	if(array[i] + array[j]==sum)
	        	{	
	        		list.add(array[i]);
	        		list.add(array[j]);
	        		break;
	        	}else if(array[i] + array[j] > sum){
	        		j--;
	        	}else{
	        		i++;
	        	}
	        }
	        return list;
	}
	
}

	class TreeNode {
		int val = 0;
		TreeNode left = null;
		TreeNode right = null;
		public TreeNode(int val) {
        this.val = val;
    }
}