package offer2016_10_12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Stack;

public class Solution {
	
	public static void main(String[] args) {
		//System.out.println(VerifySquenceOfBST(date));
	}
	
	public static boolean VerifySquenceOfBST(int[] sequence) {
		int size;
		if(sequence == null ||(size = sequence.length)<=0)
        	return false;
        return fun(0,size-1,sequence);
        
    }
	public static boolean fun(int low,int high,int[] sequence){
		int temp_index=0,i=0;
		if(low >=high){
			return true;
		}
        for(i=low;i<high;i++){
        	//当数据的值大于根节点的值，表明已经是右子树
        	if(sequence[i] >sequence[high]){
        		break;
        	}
        }
		temp_index =i;
        for(i=temp_index;i<high;i++){
        	if(sequence[i]<sequence[high]){
        		return false;
        	}
        }
        if(fun(low,temp_index-1,sequence)==false){return false;}
        if(fun(temp_index+1,high,sequence)==false){return false;}
        return true;
	}
	//遍历树和查找路径
	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	ArrayList<Integer> list = new ArrayList<Integer>();
	public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
		if(root == null)
			return result;
		list.add(root.val);
		target -= root.val;
		if(target == 0 && root.left==null&& root.right ==null){
			result.add(new ArrayList<Integer>(list));
		}
		FindPath(root.left,target);
		FindPath(root.right,target);
		list.remove(list.size()-1);
		return result;
    }
	//复杂链表的复制
	//1.先进行主元素的复制
	//2.利用哈希表存储，用于记录Random指针
	public RandomListNode Clone(RandomListNode pHead)
    {
        if(pHead == null)
        	return null;
       RandomListNode pNode = pHead;
       //复制节点A得到A1，将A1插到A后面
       while(pNode != null){
    	   RandomListNode pCloneNode = new RandomListNode(pNode.label);
    	   pCloneNode.next = pNode.next;
    	   pNode.next = pCloneNode;
    	   pNode =pCloneNode.next;
       }
       pNode = pHead;
       //遍历节点使A1.random = A.random.next=A1
       while(pNode !=null){
    	   RandomListNode pCloneNode = pNode.next;
    	   if(pNode.random !=null){
    		   pCloneNode.random = pNode.random.next;
    	   }
    	   pNode = pCloneNode.next;
       }
       //拆分
       RandomListNode pCloneHead = pHead.next;
       RandomListNode temp;
       pNode = pHead;
       while(pNode.next != null){
    	   temp = pNode.next;//A1
    	   pNode.next = temp.next;
    	   pNode = temp;
       }
       return pCloneHead;
    }
	//二叉搜索树与双向链表
	public TreeNode Convert(TreeNode root) {
        //1.中序遍历
		if(root == null)
			return null;
		//栈存放根节点
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode p = root;
		TreeNode pre = null;
		boolean isFirst = true;
		while(p !=null || !stack.isEmpty()){
			while(p !=null){
				stack.push(p);
				p=p.left;
			}
			p=stack.pop();
			if(isFirst){
				root = p;
				pre = root;
				isFirst = false;
			}else{
				pre.right = p;
				p.left = pre;
				pre = p;
			}
			p=p.right;
		}
		return root;
    }
	//递归型
	protected TreeNode leftLast = null;
	public TreeNode Convert1(TreeNode root){
		if(root == null) return null;
		if(root.left == null && root.right == null) 
			{
				leftLast = root;
				return root;
			}
		//1.将左子树构成双链表
		TreeNode left = Convert1(root.left);
		TreeNode p = left;
		//2.定位到左子树双链表的最后一个节点
		while(p!=null&&p.right !=null){
			p=p.right;
		}
		//3.如果左子树链表不为空的话，将当前的root追加到左子树表
		if(left !=null){
			p.right = root;
			root.left = leftLast;
		}
		//1.将右子树构成双链表
		TreeNode right = Convert1(root.right);
		if(right != null){
			right.left = root;
			root.right = right;
		}
		return left !=null?left:root;
	}
	//字符串的排列
	char[] seqs;
	Integer[] visited;
	HashSet<String> dict = new HashSet<String>();
	 public ArrayList<String> Permutation(String str) {
		 ArrayList<String> strList = new ArrayList<String>();
		 if(str == null || str.isEmpty()) return strList;
		 //1.需要去重，可以利用字典
		 char[] strs = str.toCharArray();
		 seqs = new char[strs.length];
		 visited = new Integer[strs.length];
		 for(int i=0;i<visited.length;i++){
			 visited[i] =0;
		 }
		 dfs(strs,0);
		 strList.addAll(dict);
		 Collections.sort(strList);
		 return strList;
	 }
	void dfs(char[] arrs,int step){
		if(arrs.length==step){
			String str ="";
			for(int i=0;i<seqs.length;i++){
				str += seqs[i];
			}
			dict.add(str);
			return;
		}
		for(int i =0;i<arrs.length;i++){
			if(visited[i]==0){
				seqs[step] = arrs[i];
				visited[i] =1;
				dfs(arrs,step+1);
				visited[i]=0;
			}
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
	public class RandomListNode {
	    int label;
	    RandomListNode next = null;
	    RandomListNode random = null;

	    RandomListNode(int label) {
	        this.label = label;
	    }
	}
}
