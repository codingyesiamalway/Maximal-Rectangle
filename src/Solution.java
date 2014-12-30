import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Stack;


public class Solution {
	
	public void initRow(char[][] matrix, int[][] row, int[][] col){
		for(int i = 0; i < row[0].length; i++){
			if(matrix[0][i] == '0'){
				row[0][i] = -1;
				col[0][i] = -1;
			}else if (matrix[0][i] == '1' && i == 0){
				row[0][i] = 0;
				col[0][i] = 0;
			}else if (matrix[0][i] == '1' && matrix[0][i - 1] == '1'){
				row[0][i] = row[0][i-1];
				col[0][i] = col[0][i-1];
			}else{
				row[0][i] = 0;
				col[0][i] = i;
			}
    	}
	}
	
	public void initCol(char[][] matrix, int[][] row, int[][] col){
		for(int i = 0; i < row.length; i++){
			if(matrix[i][0] == '0'){
				row[i][0] = -1;
				col[i][0] = -1;
			}else if (matrix[i][0] == '1' && i == 0){
				row[i][0] = 0;
				col[i][0] = 0;
			}else if (matrix[i][0] == '1' && matrix[i - 1][0] == '1'){
				row[i][0] = row[i - 1][0];
				col[i][0] = col[i - 1][0];
			}else{
				row[i][0] = i;
				col[i][0] = 0;
			}
    	}
	}
	
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
        	return 0;
        }else{
        	int[][] row = new int[matrix.length][matrix[0].length];
        	int[][] col = new int[matrix.length][matrix[0].length];
        	int[][] upRow = new int[matrix.length][matrix[0].length];
        	int[][] upCol = new int[matrix.length][matrix[0].length];
        	// initialize first row and col
        	initRow(matrix, row, col);
        	initCol(matrix, row, col);
        	initRow(matrix, upRow, upCol);
        	initCol(matrix, upRow, upCol);

        	  
        	int maxArea = 0;
        	for(int i = 0; i < matrix.length; i++){
        		if(matrix[i][0] == '1'){
	        		int curArea = i - row[i][0] + 1;
	        		maxArea = maxArea > curArea ? maxArea : curArea;
        		}
        	}
        	
        	for(int i = 0; i < matrix[0].length; i++){
        		if(matrix[0][i] == '1'){
        			int curArea = i - col[0][i] + 1;
        			maxArea = maxArea > curArea ? maxArea : curArea;
        		}
        	}
        	
        	for (int i = 1; i < matrix.length; i++){
        		for (int j = 1; j < matrix[i].length; j++){
        			if(matrix[i][j] != '0'){
        				int leftArea = 0;
        				int upArea = 0;
        				int startRow = i;
        				int startCol = j;
        				// left cell
        				if (matrix[i][j - 1] == '1' && row[i][j - 1] != -1){
	        				for(;startRow > row[i][j - 1] && matrix[startRow - 1][j] == '1'; startRow--){
	        				}
	        				leftArea = (i - startRow + 1) * ( j - col[i][j - 1] + 1);
	        				row[i][j] = startRow;
	        				col[i][j] = col[i][j - 1];
        				}else{
        					row[i][j] = i;
	        				col[i][j] = j;
	        				leftArea = 1;
        				}
        				// up cell
        				if(matrix[i - 1][j] == '1' && upRow[i - 1][j] != -1){
        					for (; startCol > upCol[i - 1][j] && matrix[i][startCol - 1] == '1'; startCol--){
        					}
        					upArea = (j - startCol + 1) * (i - upRow[i - 1][j] + 1);
        					upRow[i][j] = upRow[i-1][j];
	        				upCol[i][j] = startCol;
        				}else{
        					upRow[i][j] = i;
	        				upCol[i][j] = j;
	        				upArea = 1;
        				}
    					maxArea = maxArea > upArea ? maxArea : upArea;
    					maxArea = maxArea > leftArea ? maxArea : leftArea;
        				
        			}else{
        				row[i][j] = -1;
    					col[i][j] = -1;
    					upRow[i][j] = -1;
    					upCol[i][j] = -1;
        			}
        		}
        	}
        	return maxArea;
        }
    }
    
    public static void main(String[] args){
    	Solution s = new Solution();
    
    	TreeNode a1 = new TreeNode(10);
    	TreeNode a2 = new TreeNode(5);
    	TreeNode a3 = new TreeNode(8);
    	TreeNode a4 = new TreeNode(9);
    	TreeNode a5 = new TreeNode(4);

    	a1.right = a2;
    	a2.right = a4;
    	a4.left = a3;
    	a4.right = a5;
    	
    	long st = System.currentTimeMillis();
    	
    	String[] m = {"000","000","111"};
    	char[][]matrix = new char[m.length][m[0].length()];
    	for(int i = 0; i < m.length; i++){
    		matrix[i] = m[i].toCharArray();
    	}
    	
    	int r = s.maximalRectangle(matrix);
    	
    	long end = System.currentTimeMillis();
    	
    	System.out.println(r);
    	System.out.println("time: " +  (end - st));
    }
}
