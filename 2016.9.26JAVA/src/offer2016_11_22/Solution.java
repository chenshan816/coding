package offer2016_11_22;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

import CommandClass.TreeNode;

public class Solution {
	
	
	public static void main(String[] args) {
		System.out.println(movingCount(5,10,5));
	}
	//�����������ĵ�k����� ��С��������
	//������
	TreeNode KthNode(TreeNode pRoot, int k)
    {
        if(pRoot == null)
        	return null;
        int i =0;
        TreeNode p = pRoot;
        TreeNode returnP = null;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(p!=null || !stack.isEmpty()){
        	while(p!=null){
        		stack.push(p);
        		p=p.left;
        	}
        	TreeNode node = stack.pop();
        	i++;
        	if(i==k){
        		returnP = node;
        	}
        	p = node.right;
        }
        return returnP;
    }

	//�������е���λ��
	//���ȶ��У���Ȼ�����С����
	PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>();
	PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(15,new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o2-o1;
		}
	});
	public void Insert(Integer num) {
	   if(minHeap.isEmpty() || num <= minHeap.peek()) minHeap.add(num);
	   else maxHeap.add(num);
	   if(minHeap.size() == maxHeap.size() + 2){
		   maxHeap.add(minHeap.peek());
		   minHeap.poll();
	   }
	   if(minHeap.size() + 1 == maxHeap.size()){
		   minHeap.add(maxHeap.peek());
		   maxHeap.poll();
	   }
    }
    public Double GetMedian() {
        return maxHeap.size() == minHeap.size()?(minHeap.peek()+maxHeap.peek())/2.0:minHeap.peek();
    }

    //�������ڵ����ֵ
    //ʹ�ö���---��һ�����м�¼���ֵ���±꣬�ٽ��бȽϣ���������ֵ���ڶ����е�ֵ��ֱ��ȥ��
    public ArrayList<Integer> maxInWindows(int [] num, int size)
    {
    	 ArrayList<Integer> maxList = new ArrayList<Integer>();
         if(size <= 0) return maxList;
         //����һ����˫�˶��С�����ÿ���������ڵġ����ֵ���±꡿
         ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
         //����һ������max���ڼ�¼��ǰ�������ڵ����ֵ���±�
         int max = 0;
         for (int i = 0; i < num.length; i++){
        	 max = i+1-size;
        	 if(queue.isEmpty())
        		 queue.add(i);
        	 //�����������ֵ�Ѿ����Ƴ��˻�������ɾ��
        	 else if(max > queue.peekFirst()){
        		 queue.pollFirst();
        	 }
        	 while(!queue.isEmpty() && num[queue.peekLast()] <= num[i]){
        		//���������ʾ�����ж�βλ�ö�Ӧ��Ԫ�رȵ�ǰԪ�ظ�С�����Ƴ�������Ϊ��Ҫ�õ����Ǵ������ֵ
        		 queue.pollLast();
        	 }
        	 queue.add(i);
        	 if(max >= 0){
        		 maxList.add(num[queue.peekFirst()]);
        	 }
         }
         return maxList;
    }

    //�����е�·��
    //��������  �����߽�ֱ���ж���Ч�������ľ͵ݹ�ʵ��
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        int flag[] = new int[matrix.length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (helper(matrix, rows, cols, i, j, str, 0, flag))
                    return true;
            }
        }
        return false;
    }
    private boolean helper(char[] matrix, int rows, int cols, int i, int j, char[] str, int k, int[] flag) {
        int index = i * cols + j;
        if (i < 0 || i >= rows || j < 0 || j >= cols || matrix[index] != str[k] || flag[index] == 1)
            return false;
        if(k == str.length - 1) return true;
        flag[index] = 1;
        if (helper(matrix, rows, cols, i - 1, j, str, k + 1, flag)
                || helper(matrix, rows, cols, i + 1, j, str, k + 1, flag)
                || helper(matrix, rows, cols, i, j - 1, str, k + 1, flag)
                || helper(matrix, rows, cols, i, j + 1, str, k + 1, flag)) {
            return true;
        }
        flag[index] = 0;
        return false;
    }

    //�����˵��˶���Χ
    //Ҳ��һ�����ݵ�����
    public static int movingCount(int threshold, int rows, int cols)
    {
    	boolean flag[][] = new boolean[rows][cols];
    	return help(threshold,rows,cols,0,0,flag);

    }
    private static int help(int threshold,int rows,int cols,int row,int col,boolean[][] flag){
    	if (row < 0 || row >= rows || col < 0 || col >= cols || numSum(row) + numSum(col)  > threshold || flag[row][col] == true) 
    		return 0;
    	flag[row][col] = true;
    	return help(threshold,rows,cols,row+1,col,flag)
    			+ help(threshold,rows,cols,row-1,col,flag)
    			+help(threshold,rows,cols,row,col-1,flag)
    			+help(threshold,rows,cols,row,col+1,flag)+1;
    }
	private static int numSum(int num) {
		int sum=0;
		do{
			sum+=num%10;
		}while((num=num/10)>0);
		return sum;
	}
    
}
