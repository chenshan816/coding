package Offer;

import java.util.ArrayList;

import org.junit.Test;

public class Solution {

	public static void main(String[] args){
		Solution s = new Solution();
		int num = s.NumberOf1(-1);
		System.out.println(num);
	}
	@Test
	public int JumpFloor(int target){
		//只能倒着推导
		//1.只有一节或者两节
		if(target == 1 || target == 2){
			return target;
		}
		//2.计算n-1和n-2的总数则F(n)=F(n-1)+F(n-2)
		int jumpNum =0;//总共的跳法
		int jumpSumBack1=2;
		int jumpSumBack2 =1;
		for(int i =3;i<=target;i++){
			jumpNum = jumpSumBack1+jumpSumBack2;
			jumpSumBack2 = jumpSumBack1;
			jumpSumBack1 = jumpNum;
		}
		return jumpNum;
	}

	@Test
	public int JumpFloorII(int target) {
		int a=1;
		return a<<(target-1);
    }
	
	public int NumberOf1(int n) {
		int numOf1 =0;
		String n_B= Integer.toBinaryString(n);
		char[] n_BC = n_B.toCharArray();
		for(int i =0;i<n_BC.length;i++){
			if(n_BC[i]=='1'){
				numOf1 += 1;
			}
		}
		return numOf1;
    }
	
	public double Power(double base, int exponent) {
        return Math.pow(base,exponent);
	  }
	

	public void reOrderArray(int [] array) {
        int size = array.length;
        ArrayList<Integer> odd = new ArrayList<Integer>();
        ArrayList<Integer> even = new ArrayList<Integer>();
        for(int i =0;i<size;i++){
        	if(array[i]%2==0){
        		even.add(array[i]);
        	}
        	else{
        		odd.add(array[i]);
        	}
        }
        int j=0;
	    for(int i=0;i<odd.size();i++){
	       array[j] = odd.get(i);
	       j++;
	   }
	    for(int i=0;i<even.size();i++){
		       array[j] = even.get(i);
		       j++;
		   }
    }
	
	public ListNode FindKthToTail(ListNode head,int k) {
		//先走k个长度，剩下的长度就是length-k，因此第二个指针走了length-k个
		if(head==null||k<=0){
            return null;
        }
        ListNode pre=head;
        ListNode last=head;       
        for(int i=1;i<k;i++){
            if(pre.next!=null){
                pre=pre.next;
            }else{
                return null;
            }
        }
        while(pre.next!=null){
            pre = pre.next;
            last=last.next;
        }
        return last;
    }
	
	public ListNode ReverseList(ListNode head) {
		if(head == null)
			return null;
		ListNode reverseHead =null;
		ListNode current = head;
		ListNode pre = null;
		ListNode temp =null;
		while(current != null)
		{
			temp = current.next;
			current.next = pre;
			reverseHead = current;
			pre = current;
			current = temp;
		}
		return reverseHead;
    }
	
/*	public ListNode Merge(ListNode list1,ListNode list2) {
		if(list1==null)
			 return list2;
		else if(list2==null)
			 return list1;
		ListNode mergeHead=null;
		if(list1.val<list2.val){
			 mergeHead=list1;
			 mergeHead.next=Merge(list1.next, list2);
		}
		else{
			 mergeHead=list2;
			 mergeHead.next=Merge(list1, list2.next);
		}
			 return mergeHead;
    }*/
	
	public ListNode Merge(ListNode list1,ListNode list2){
		ListNode current=new ListNode(-1);
        current.next=null;
        ListNode head=current;
		while(list1 !=null && list2!=null){
			if(list1.val < list2.val){
				current.next = list1;
				current = list1;
				list1 = list1.next;
			}
			else{
				current.next = list2;
				current = list2;
				list2 = list2.next;
			}
		}
		if(list1 != null){
			current.next = list1;
		}
		if(list2 != null){
			current.next = list2;
		}
		return head.next;
	}
	
	//链表
	public class ListNode {
	    int val;
	    ListNode next = null;

	    ListNode(int val) {
	        this.val = val;
	    }
	}
}

