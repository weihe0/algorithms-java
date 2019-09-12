package algorithm;
import org.junit.Test;

import java.util.*;

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
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        TreeMap<Integer,Integer> m=new TreeMap<>();
        for(int i=0;i<nums1.length;i++){
            m.put(nums1[i],i);
        }
        int[] nexts=new int[nums1.length];
        for(int i=0;i<nums1.length;i++){
            nexts[i]=-1;
        }
        int[] s=new int[nums2.length];
        int t=-1;
        for(int n:nums2){
            while(t>=0&&s[t]<n){
                if(m.containsKey(s[t])){
                    int i=m.get(s[t]);
                    nexts[i]=n;
                }
                --t;
            }
            s[++t]=n;
        }
        return nexts;
    }
    @Test
    public void testIncStack(){
        int n1[]={4,1,2},n2[]={1,3,4,2};
        nextGreaterElement(n1,n2);
    }
}
