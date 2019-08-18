package algorithm;
import org.junit.Test;

import java.util.*;

public class Array {
    public List<Integer> spiralOrder(int[][] matrix) {
        if(matrix.length==0){return new ArrayList<>();}
        int imin=0,imax=matrix.length-1;
        int jmin=0,jmax=matrix[0].length-1;
        List<Integer> l=new ArrayList<>((imax+1)*(jmax+1));
        while(imin<imax&&jmin<jmax){
            // up
            for(int j=jmin;j<jmax;j++){
                l.add(matrix[imin][j]);
            }
            // right
            for(int i=imin;i<imax;i++){
                l.add(matrix[i][jmax]);
            }
            // down
            for(int j=jmax;j>jmin;j--){
                l.add(matrix[imax][j]);
            }
            // left
            for(int i=imax;i>imin;i--){
                l.add(matrix[i][jmin]);
            }
            imin++;
            imax--;
            jmin++;
            jmax--;
        }
        if(imin==imax){
            for(int j=jmin;j<=jmax;j++){
                l.add(matrix[imin][j]);
            }
        }else if(jmin==jmax){
            for(int i=imin;i<=imax;i++){
                l.add(matrix[i][jmin]);
            }
        }
        return l;
    }
    @Test
    public void four(){
        int[][] m={{1,2},{3,4}};

        for(int i:spiralOrder(m)){
            System.out.printf("%d ",i);
        }
    }
}
