package sort.problem;

import java.util.Arrays;

public class MergeSolve {
    static int  reversePair = 0;
    //寻找逆序对
    public static int getReversePair(int[] arr){
        mergeSort(arr,0,arr.length-1);
        return reversePair;
    }
    public static void mergeSort(int[] arr,int l,int r){
        if(l>=r)return;//当r-l小于特点值的时候可以用insertion
        int mid = (l+r)/2;
        mergeSort(arr,l,mid);
        mergeSort(arr,mid+1,r);
        merge(arr,l,mid,r);
    }
    public static void merge(int[] arr,int l,int mid,int r){
        int[] arrCopy = Arrays.copyOfRange(arr,l,r+1);
        int i = l;int j = mid+1;
        for(int k = l;k<=r;k++){
            if(i>=mid+1){
                arr[k]=arrCopy[j-l];j++;
            }else if(j>=r+1){
                arr[k]=arrCopy[i-l];i++;
            }else if(arrCopy[i-l]<=arrCopy[j-l]){
                arr[k]=arrCopy[i-l];i++;
            }else if(arrCopy[i-l]>arrCopy[j-l]){
                //关键步骤
                reversePair +=mid-i+1;
                arr[k]=arrCopy[j-l];j++;
            }
        }
    }
}
