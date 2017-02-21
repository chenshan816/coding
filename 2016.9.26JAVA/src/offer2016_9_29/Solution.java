package offer2016_9_29;

import java.util.ArrayList;

public class Solution {
	//˳ʱ���ӡ����
	public ArrayList<Integer> printMatrix(int [][] matrix) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		int rows = matrix.length;
		int cols = matrix[0].length;
		int start = 0;
		if(matrix == null || rows <=0||cols <=0) return null;
		while(cols > (start*2) && rows > (start*2)){
			PrintMatrixIncircle(matrix,rows,cols,start,result);
			start++;
		}
		return result;
	}
	public ArrayList<Integer> PrintMatrixIncircle(int[][] matrix,int rows,int cols,int start,ArrayList<Integer> result){
		int endX = cols-1-start;
		int endY = rows-1-start;
		if(endX == 0){
			for(int i =0;i<=endY;i++){
				result.add(matrix[i][endX]);
			}
			return result;
		}
		//������
		for(int i = start;i<=endX;i++){
			result.add(matrix[start][i]);
		}
		//���ϵ���
		if(endX > start){
			for(int i =start+1;i<=endY;i++){
				result.add(matrix[i][endX]);
			}
		}
		//���ҵ���
		if(endY > start && endX > start){
			for(int i =endX-1;i>=start;i--){
				result.add(matrix[endY][i]);
			}
		}
		//��������
		if(endY-1 > start && endX > start){
			for(int i =endY-1;i>=start+1;i--){
				result.add(matrix[i][start]);
			}
		}
		return result;
	}
	public ArrayList<Integer> printMatrix1(int [][] array) {
	   ArrayList<Integer> result = new ArrayList<Integer> ();
	   if(array.length==0) return result;
	   int n = array.length,m = array[0].length;
	   if(m==0) return result;
	   int layers = (Math.min(n,m)-1)/2+1;//����ǲ���
	   for(int i=0;i<layers;i++){
	       for(int k = i;k<m-i;k++) result.add(array[i][k]);//������
	       for(int j=i+1;j<n-i;j++) result.add(array[j][m-i-1]);//����������
	       for(int k=m-i-2;(k>=i)&&(n-i-1!=i);k--) result.add(array[n-i-1][k]);//������
	       for(int j=n-i-2;(j>i)&&(m-i-1!=i);j--) result.add(array[j][i]);//����������
	   }
	   return result;       
	}
}
