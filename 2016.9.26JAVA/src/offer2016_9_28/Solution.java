package offer2016_9_28;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		
	}
	
	 public boolean HasSubtree(TreeNode root1,TreeNode root2) {
	        if(root2 == null || root1 == null){
	        	return false;
	        }
	        //�������ṹ���ҵ�root2�ĸ��ڵ�--ǰ�����
	        return isSubtree(root1,root2) || HasSubtree(root1.left,root2) || HasSubtree(root1.right,root2);
	    }
	 boolean isSubtree(TreeNode pA,TreeNode pB){
		 if(pB == null) return true;
		 if(pA == null) return false;
		 if(pA.val == pB.val){
			 return isSubtree(pA.left,pB.left) && isSubtree(pA.right,pB.right);
		 }else{
			 return false;
		 }
	 }
	 
	 //��������� �ݹ�
	 public void Mirror(TreeNode root) {
		 	TreeNode temp;
	        if(root !=null){
	        	temp = root.left;
	        	root.left = root.right;
	        	root.right = temp;
	        	if(root.left!=null)
	        		Mirror(root.left);
	        	if(root.right!=null)
	        	Mirror(root.right);
	        }
	    }
	 
	//��������� �ݹ�
	 public void Mirror1(TreeNode root){
		 if(root == null) return;
		 Stack<TreeNode> s = new Stack<TreeNode>();
		 s.push(root);
		 while(s.size() != 0){
			 TreeNode node = s.pop();
			 if(node.left != null||node.right != null){
	                TreeNode temp = node.left;
	                node.left = node.right;
	                node.right = temp;
	            }
	            if(node.left!=null){
	                s.push(node.left);
	            }
	            if(node.right!=null){
	                s.push(node.right);
	            }
		 }
	 }
	 
	
	//���ṹ
	public class TreeNode {
	    int val = 0;
	    TreeNode left = null;
	    TreeNode right = null;

	    public TreeNode(int val) {
	        this.val = val;
	    }
	}
}
