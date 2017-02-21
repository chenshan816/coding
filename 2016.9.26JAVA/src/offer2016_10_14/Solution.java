package offer2016_10_14;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

	public static void main(String[] args) {
		int[] data = {1,3,4,5,2,2,2,2,2};
	}
	//数组中出现次数超过一半的数字
	public int MoreThanHalfNum_Solution(int [] array) {
		int size;
        if((size=array.length) <=0 || array ==null) return 0;
        int num = array[0],count=1;
        for(int i=1;i<size;i++){
        	if(array[i]==num){
        		count++;
        	}else{
        		count--;
        	}
        	if(count == 0){
        		num = array[i];
        		count =1;
        	}
        }
        count =0;
        for(int i=0;i<size;i++){
        	if(array[i]==num) count++;
        }
        if(count*2>size) return num;
        return 0;
    }
	
	public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if(input ==null || input.length ==0 || k>(input.length-1) || k<0) return result;
		quickSort(input,k,0,input.length-1);
		for(int i=0;i<k;i++)
			result.add(input[i]);
		return result;
    }
	//最小堆
	public static  ArrayList<Integer> maxTopN(int[] array,int k){
		ArrayList<Integer> result = new ArrayList<Integer>();
		if(array.length ==0 || array.length <k) return result;
		int len = array.length;
		//初始化最大堆
		for(int j=k/2-1;j>=0;j--){
			fitToMaxHeap(array,j,k);
		}
		for(int n=k;n<len;n++){
			if(array[n] <array[0]){
				int temp = array[n];
				array[n] =array[0];
				array[0] =temp;
				fitToMaxHeap(array, 0, k);
			}
		}
		for(int i=0;i<k;i++)
			result.add(array[i]);
		return result;
	}
	private static void fitToMaxHeap(int[] array, int top, int tail) {
		int i =top;
		//获得第一个结点
		int j =2*i+1;//子节点
		int temp = array[i];
		//不断的循环
		while(j<tail){
			//有右孩子，且右子数大于左子树,使j指向右孩子
			if(j+1 < tail && array[j+1] > array[j]){
				j++;
			}
			if(array[j] >temp){
				array[i] = array[j];
			}else{
				break;
			}
			i=j;
			j=2*i+1;
		}
		array[i]=temp;
	}
	//数组的快速排序
	public static void quickSort(int[] array,int k, int low,int high){
		int i=low,j=high;
		int key = array[low];
		while(i < j){
			while(array[j] > key){
				j--;
			}
			swap(array,i,j);
			while(array[i] <key){
				i++;
			}
			swap(array,i,j);
		}
		if(i>low) quickSort(array,k,0,i-1);
		if(j<high) quickSort(array,k,i+1,high);
	}
	public static void swap(int[] array,int i,int j){
		int temp = array[i];
		array[i] =array[j];
		array[j] = temp;
	}
}
