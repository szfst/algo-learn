package sort;

import java.util.Arrays;

public class MergeSort {
    public static void sort(int[] arr){
        mergeSort(arr,0,arr.length-1);
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
            }else if(arrCopy[i-l]<arrCopy[j-l]){
                arr[k]=arrCopy[i-l];i++;
            }else if(arrCopy[i-l]>=arrCopy[j-l]){
                arr[k]=arrCopy[j-l];j++;
            }
        }
    }
    public static void main(String[] args) {

        int[] arr = {1,9,8,0,6,4,3,2,1};
        sort(arr);
        for( int i = 0 ; i < arr.length ; i ++ ){
            System.out.print(arr[i]);
            System.out.print(' ');
        }
        System.out.println();
    }

}
