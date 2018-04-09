package sort.problem;

import sort.SortUtil;

public class QuickSortSolve {
    //寻找第k的值，索引从0开始
    public int findKMaxProblem(int[] arr,int k){
        //如果索引从1开始，则k=arr.length-k
        k = arr.length-1-k;
        return findKMaxProblem(arr,k,0,arr.length-1);
    }
    private int findKMaxProblem(int[] arr,int k,int l,int r){
        if(r==l)return arr[l];
        int p = partition(arr,l,r);
        if(k==p){
            return arr[p];
        }else if(k>p){
           return findKMaxProblem(arr,k,p+1,r);
        }else{
           return findKMaxProblem(arr,k,l,p-1);
        }
    }
    private int partition(int[] arr,int l,int r){
        int compare = arr[l];
        int j = l;
        for(int i = l+1;i<=r;i++){
            if(arr[i]<compare)SortUtil.swap(arr,++j,i);
        }
        SortUtil.swap(arr,j,l);
        return j;
    }
    public static void main(String[] args) {
        int[] arr = {1,9,8,0,6,5,4,3,2,1};
        System.out.println(new QuickSortSolve().findKMaxProblem(arr,1));
    }
}
