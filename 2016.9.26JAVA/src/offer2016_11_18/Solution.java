package offer2016_11_18;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import CommandClass.TreeNode;

public class Solution {
	
	
	public static void main(String[] args) {
		String str="5,4,#,3,#,2";
		TreeNode root = Deserialize(str);
		System.out.println(Serialize(root));
	}
	//把二叉树打印成多行
	//层次遍历 使用队列
	 public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
		 ArrayList<ArrayList<Integer>> array = new ArrayList<ArrayList<Integer>>();
		 if(pRoot == null)
		    return array;
		 Queue<TreeNode> q = new LinkedList<TreeNode>();
		 Queue<TreeNode> q1 = new LinkedList<TreeNode>();
		 TreeNode current;
		 ArrayList<Integer> list;
		 q.add(pRoot);
		 while(!q.isEmpty()){
			 list = new ArrayList<Integer>();
			 while(!q.isEmpty()){
				 current = q.poll();
				 if(current.left != null) q1.add(current.left);
				 if(current.right != null) q1.add(current.right);
				 list.add(current.val);
			 }
			 if(!list.isEmpty()){
				 array.add(list);
			 }
			 list = new ArrayList<Integer>();
			 while(!q1.isEmpty()){
				 current = q1.poll();
				 if(current.left != null) q.add(current.left);
				 if(current.right != null) q.add(current.right);
				 list.add(current.val);
			 }
			 if(!list.isEmpty()){
				 array.add(list);
			 }
		 }
		 return array;
	 }
	 //不用双队列，记个数就好了
	 public ArrayList<ArrayList<Integer> > Print1(TreeNode pRoot){
		 ArrayList<ArrayList<Integer>> array = new ArrayList<ArrayList<Integer>>();
		 if(pRoot == null)
		    return array;
		 Queue<TreeNode> q = new LinkedList<TreeNode>();
		 TreeNode current;
		 q.add(pRoot);
		 int i=0,size =0;
		 while(!q.isEmpty()){
			 size = q.size();
			 ArrayList<Integer> list=null;
			 while(i++<size){
				 current = q.remove();
				 list = new ArrayList<Integer>();
				 list.add(current.val);
				 if(current.left != null) q.add(current.left);
				 if(current.right != null) q.add(current.right);
			 }
			 if(list !=null)
				 array.add(list);
		 }
		 return array;
	 }

	 //序列化二叉树：层次遍历，在从字符串还原出二叉树
	 public static int index = -1;
	    static String Serialize(TreeNode root) {
	        StringBuffer sb = new StringBuffer();
	        if(root == null){
	            sb.append("#,");
	            return sb.toString();
	        }
	        sb.append(root.val + ",");
	        sb.append(Serialize(root.left));
	        sb.append(Serialize(root.right));
	        return sb.toString();
	  }
	    static TreeNode Deserialize(String str) {
	        index++;
	       int len = str.length();
	        if(index >= len){
	            return null;
	        }
	        String[] strr = str.split(",");
	        TreeNode node = null;
	        if(!strr[index].equals("#")){
	            node = new TreeNode(Integer.valueOf(strr[index]));
	            node.left = Deserialize(str);
	            node.right = Deserialize(str);
	        }
	        return node;
	  }
}
