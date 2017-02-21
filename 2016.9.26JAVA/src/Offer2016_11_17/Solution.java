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
	//链表中环的入口结点
	//1.找到交汇点
	//2.一个从交汇点走，一个从头走，交汇处就是入口
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
        		//找到交汇点，slow从头开始走
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

	//删除链表中重复的结点
	//排序 递归
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
	//非递归
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

	//二叉树的下一个结点
	//中序遍历  左节点->根节点 -> 右节点
	//1.处理根节点，找右子树的最左子树
	//2.自己是父亲的左子树输出父亲
	//3.自己是父亲的右子树分为两种情况  
	//	3.1 自己有右孩子，输出右孩子  
	//	3.2在自己的父亲及上面找到一个节点是它父亲的左孩子，如果一直找不到说明他是最后的一个节点  
	public TreeLinkNode GetNext(TreeLinkNode pNode)
    {
        if(pNode == null)
        	return pNode;
        if(pNode.next == null)
        {
        	//说明是根节点
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
	        	//表示该点是左子树，则直接输出根节点
	        	return pNode.next;
	        }
	        if(pNode.next.right == pNode){
	        	//表示该点是右子树，进行递归
	        	pNode = pNode.next;
	        }
        }
        return null;
    }

	//对称的二叉树
	//镜像就是根节点的
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

	//按之字形顺序打印二叉树
	//思路肯定是递归 一个队列 一个栈
	public static ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
		ArrayList<ArrayList<Integer>> array = new ArrayList<ArrayList<Integer>>(); 
		if(pRoot == null) return array;
		Stack<TreeNode> s = new Stack<TreeNode>();
		Stack<TreeNode> q = new Stack<TreeNode>();
		TreeNode current;
		ArrayList<Integer> list = null;
		//从左往右 队列
		//从右往左 栈
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
