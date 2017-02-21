package offer2016_11_4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;


public class Solution {
	//�жϸö������Ƿ���ƽ�������
	private boolean isBalanced = true;
	public boolean IsBalanced_Solution(TreeNode root) {
        if(root == null)
        	return true;
        TreeNode t = root;
        TreeHigh(t);
        return isBalanced;
    }
	int TreeHigh(TreeNode t){
		//1.����ջ
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
	//������ֻ����һ�ε�����
	//num1,num2�ֱ�Ϊ����Ϊ1�����顣��������
	//��num1[0],num2[0]����Ϊ���ؽ��
	public static void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        //�ŵ��ֵ��б���һ�Σ��������������Ҫ�Ľ��
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

	//��һ���뷨����򡪡��κ���������Լ�������0
	public static void FindNumsAppearOnce1(int [] array,int num1[] , int num2[]){
		if(array.length < 2) return;
		int myxor =0;
		int flag =1;
		for(int i=0;i<array.length;i++){
			myxor ^=array[i];
		}
		while((myxor & flag)==0) flag <<= 1; //�жϵ�һ������1��λ��,˵������һ�ε���������һλ��ͬ
		num1[0] = myxor;
		num2[0] = myxor;
		for(int i =0;i<array.length;i++){
			if((flag &array[i]) == 0) num2[0] ^=array[i];//��һ����һ�Σ�����������0�����Ի�ò�ͬ����
			else num1[0] ^= array[i];
		}
	}

	//��ΪS��������������
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
	
	//��ΪS����������
	//array�ǵ�������˿���ʹ�ö��ַ����в��� �����и�����o(nlogn)
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
	 * �������������������ͷβ����ָ��i��j��
	 * ��ai + aj == sum�����Ǵ𰸣����ԽԶ�˻�ԽС��
	 * ��ai + aj > sum��aj�϶����Ǵ�֮һ��ǰ���ѵó� i ǰ��������ǲ����ܣ���j -= 1
	 * ��ai + aj < sum��ai�϶����Ǵ�֮һ��ǰ���ѵó� j ����������ǲ����ܣ���i += 1
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