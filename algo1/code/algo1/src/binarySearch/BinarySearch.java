package binarySearch;

import sort.InsertionSort;

public class BinarySearch {
    private int binarySearch(int[] arr,int value){
        return binarySearch(arr,value,0,arr.length-1);
    }
    private int binarySearch(int[] arr,int value,int l ,int r){
        if(l>r)return -1;
        int mid = l+(r-l)/2;//很重要，防止整形溢出;另外这个是jdk1.6以后的实现(low + high)>>>1无符号右移，记住是>>>而不是>>
        if(arr[mid]==value){
            return mid;
        }else if(arr[mid]>value){
            return binarySearch(arr,value,l,mid-1);
        }else{
            return binarySearch(arr,value,mid+1,r);
        }
    }

    public static void main(String[] args) {

        int[] arr = {1,9,8,0,6,5,4,3,2,1};
        InsertionSort.sort(arr);
        for( int i = 0 ; i < arr.length ; i ++ ){
            System.out.print(arr[i]);
            System.out.print(' ');
        }
        System.out.println();
        System.out.println(new BinarySearch().binarySearch(arr, 3));
    }
}
