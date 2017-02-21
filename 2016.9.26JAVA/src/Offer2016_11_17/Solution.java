package Offer2016_11_17;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import CommandClass.ListNode;
import CommandClass.TreeLinkNode;
import CommandClass.TreeNode;

public class Solution {
	
	
	public static void main(String[] args) {
		TreeNode p1 = new TreeNode(8);
		TreeNode p2 = new TreeNode(6);
		TreeNode p3 = new TreeNode(10);
		TreeNode p4 = new TreeNode(5);
		TreeNode p5 = new TreeNode(7);
		TreeNode p6 = new TreeNode(9);
		TreeNode p7 = new TreeNode(11);
		p1.left = p2;p1.right=p3;
		p2.left = p4;p2.right=p5;
		p3.left = p6;p3.right=p7;
		ArrayList<ArrayList<Integer>> array =Print(p1);
		System.out.println(array);
	}
	//�����л�����ڽ��
	//1.�ҵ������
	//2.һ���ӽ�����ߣ�һ����ͷ�ߣ����㴦�������
	public ListNode EntryNodeOfLoop(ListNode pHead)
    {
        if(pHead == null || pHead.next == null)
        	return null;
        ListNode slow = pHead;
        ListNode quick = pHead;
        while(slow!=null && quick.next !=null){
        	slow = slow.next;
        	quick = quick.next.next;
        	if(slow == quick){
        		//�ҵ�����㣬slow��ͷ��ʼ��
        		slow = pHead;
        		while(slow != quick){
        			slow = slow.next;
        			quick = quick.next;
        		}
        		if(slow == quick)
        			return slow;
        	}
        }
        return null;
    }

	//ɾ���������ظ��Ľ��
	//���� �ݹ�
	public ListNode deleteDuplication(ListNode pHead)
    {
		if(pHead ==null || pHead.next == null)
			return pHead;
		ListNode current=null;
		if(pHead.next.val == pHead.val){
			current = pHead.next.next;
			while(current != null && current.val == pHead.val)
				current = current.next;
			return deleteDuplication(current);
		}else{
			current = pHead.next;
			pHead.next = deleteDuplication(current);
			return pHead;
		}
    }
	//�ǵݹ�
	public ListNode deleteDuplication2(ListNode pHead){
		ListNode first = new ListNode(-1);
		first.next = pHead;
		ListNode p = pHead;
        ListNode last = first;
		while(p!=null &p.next !=null){
			if(p.val == p.next.val){
				int val = p.val;
				while(p!=null &p.val == val)
					p=p.next;
				last.next = p;
			}else{
				last = p;
				p=p.next;
			}
		}
		return first.next;
	}

	//����������һ�����
	//�������  ��ڵ�->���ڵ� -> �ҽڵ�
	//1.������ڵ㣬������������������
	//2.�Լ��Ǹ��׵��������������
	//3.�Լ��Ǹ��׵���������Ϊ�������  
	//	3.1 �Լ����Һ��ӣ�����Һ���  
	//	3.2���Լ��ĸ��׼������ҵ�һ���ڵ��������׵����ӣ����һֱ�Ҳ���˵����������һ���ڵ�  
	public TreeLinkNode GetNext(TreeLinkNode pNode)
    {
        if(pNode == null)
        	return pNode;
        if(pNode.next == null)
        {
        	//˵���Ǹ��ڵ�
        	if(pNode.right != null)
        	{
        		pNode = pNode.right;
        		while(pNode.left != null){
        			pNode = pNode.left;
        		}
        	}else{
        		pNode = null;
        	}
        	return pNode;
        	
        }
        if(pNode == null)
        	return pNode;
        if(pNode.right != null)
        	return pNode.right;
        while(pNode.next != null){
	        if(pNode.next.left == pNode){
	        	//��ʾ�õ�������������ֱ��������ڵ�
	        	return pNode.next;
	        }
	        if(pNode.next.right == pNode){
	        	//��ʾ�õ��������������еݹ�
	        	pNode = pNode.next;
	        }
        }
        return null;
    }

	//�ԳƵĶ�����
	//������Ǹ��ڵ��
	boolean isSymmetrical(TreeNode pRoot)
    {
        if(pRoot == null)
        	return true;
        return comRoot(pRoot.left,pRoot.right);
    }
	private boolean comRoot(TreeNode left, TreeNode right) {
		if(left == null) return right==null;
		if(right == null) return false;
		if(left.val != right.val) return false;
		return comRoot(left.right, right.left) && comRoot(left.left,right.right);
	}

	//��֮����˳���ӡ������
	//˼·�϶��ǵݹ� һ������ һ��ջ
	public static ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
		ArrayList<ArrayList<Integer>> array = new ArrayList<ArrayList<Integer>>(); 
		if(pRoot == null) return array;
		Stack<TreeNode> s = new Stack<TreeNode>();
		Stack<TreeNode> q = new Stack<TreeNode>();
		TreeNode current;
		ArrayList<Integer> list = null;
		//�������� ����
		//�������� ջ
		q.add(pRoot);
		while(!q.isEmpty()){
			list=new ArrayList<Integer>();
			while(!q.isEmpty()){
				current = q.pop();
				list.add(current.val);
				if(current.left!=null) s.add(current.left);
				if(current.right !=null) s.add(current.right);
			}
			if(!list.isEmpty())
				array.add(list);
			list=new ArrayList<Integer>();
			while(!s.empty()){
				current = s.pop();
				list.add(current.val);
				if(current.right!=null) q.add(current.right);
				if(current.left !=null) q.add(current.left);
			}
			if(!list.isEmpty())
				array.add(list);
		}
		return array;
    }
}
