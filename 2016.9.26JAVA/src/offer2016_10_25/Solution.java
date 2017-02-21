package offer2016_10_25;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution {
	//�����е������
	int cnt;
	public int InversePairs(int [] array) {
        //�鲢����
		cnt=0;
		if(array != null)
			mergeSortUp2Down(array,0,array.length-1);
		return cnt;
    }
	void mergeSortUp2Down(int[] array,int start,int end){
		if(start >=end)
			return;
		int mid = (start+end)>>1;
		mergeSortUp2Down(array,start,mid);//���
		mergeSortUp2Down(array,mid+1,end);
		
		merge(array,start,mid,end);
	}
	/**
	 * 
	 * @param array :��������
	 * @param start:��һ����������ʼλ��
	 * @param mid:�ڶ�����������ʼλ��
	 * @param end:�ڶ��������Ľ���λ��
	 */
	void merge(int[] array,int start,int mid,int end){
		int[] temp = new int[end-start +1];
		int i=start,j=mid+1,k=0;
		while(i<=mid && j <= end){
			if(array[i] <= array[j]){
				temp[k++] = array[i++];
			}else{
				temp[k++] = array[j++];
				cnt += mid-i+1;//������һ����С��ǰ��ε���������Ϊǰ��ε���������ģ����mid-i+1�����������
			}
		}
		while(i<=mid){
			temp[k++] = array[i++];
		}
		while(j <= end){
			temp[k++] = array[j++];
		}
		for (k = 0; k < temp.length; k++)
            array[start + k] = temp[k];
	}

	//��������ĵ�һ���������
	//����������ڵ�֮��������������ͬ
	public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
		 if(pHead1 == null || pHead2 == null)
			 return null;
		 int len1=0,len2=0,size=0;
		 while(pHead1 != null)
		 {
			 len1++;
			 pHead1 = pHead1.next;
		 }
		 while(pHead2 != null)
		 {
			 len2++;
			 pHead2 = pHead2.next;
		 }
		 if(len1 >= len2){
			 size = len1-len2;
			 while(size > 0){
				 pHead1 = pHead1.next;
				 size--;
			 }
		 }
		 else {
			 size = len2-len1;
			 while(size > 0){
				 pHead2 = pHead2.next;
				 size--;
			 }
		 }
		 //ͬ�ȳ���֮��ʼ�Ա�
		 while(pHead1 != pHead2){
			 pHead1 = pHead1.next;
			 pHead2 = pHead2.next;
		 }
		 return pHead1;
    }
	
	//������*(��������)�г��ֵĴ��� => ֱ�ӽ��бȽ� 39ms 629k
	public int GetNumberOfK(int [] array , int k) {
		if(array == null || array.length <=0)
			return 0;
		int num =0;
		boolean flag =false;
		for(int i=0;i < array.length;i++){
			if(array[i] == k){
				num++;
				flag =true;
			}
			if(array[i] != k && flag)
				break;
		}
		return num;
    }
	//������*(��������)�г��ֵĴ��� => ����ʹ�ö��ַ� 29ms 629k
	public int GetNumberOfK2(int [] array , int k){
		if(array == null || array.length <=0)
			return 0;
		int first = getFirstK(array,k,0,array.length-1);
		int last = getLastK(array,k,0,array.length-1);
		if(first == -1 || last == -1)
			return 0;
		return last-first+1;
	}
	public int getFirstK(int[] array,int k,int start,int end){
		while(start <=end){
			int mid = (start + end)>>1;
			if(k < array[mid]){
				end = mid-1;
			}else if(k > array[mid]){
				start = mid +1;
			}else{
				if(mid >0 &&array[mid-1] !=k || mid==0)
					return mid;
				else{
					end =mid-1;
				}
			}
		}
		return -1;
	}
	public int getLastK(int[] array,int k,int start,int end){
		while(start <=end){
			int mid = (start + end)>>1;
			if(k < array[mid]){
				end = mid-1;
			}else if(k > array[mid]){
				start = mid +1;
			}else{
				if(mid <end &&array[mid+1] !=k || mid==end)
					return mid;
				else{
					start =mid+1;
				}
			}
		}
		return -1;
	}
	
	//�����������  �ݹ� 34ms 654k
	public int TreeDepth(TreeNode pRoot)
    {
		if(pRoot == null) return 0;
		int left =1;
		int right =1;
		left += TreeDepth(pRoot.left);
		right += TreeDepth(pRoot.right);
		return left>right? left:right;
    }
	//��������� �ǵݹ� ��α��� 33ms 636k
	public int TreeDepth2(TreeNode pRoot){
		if(pRoot == null) return 0;
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(pRoot);
		int count =0,depth=0;;
		int nextCount =1;
		while(q.size() !=0){
			TreeNode top = q.poll();
			count++;
			if(top.left != null) q.add(top.left);
			if(top.right != null) q.add(top.right);
			if(count == nextCount){
				nextCount = q.size();
				count =0;
				depth++;
			}
		}
		return depth;
		
	}

	
	
	
	
	 class ListNode {
	    int val;
	    ListNode next = null;

	    ListNode(int val) {
	        this.val = val;
	    }
	}
	 public class TreeNode {
		int val = 0;
		TreeNode left = null;
		TreeNode right = null;
		public TreeNode(int val) {
	        this.val = val;
	    }
	}
}
