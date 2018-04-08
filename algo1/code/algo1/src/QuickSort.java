import sort.SortUtil;

public class QuickSort {
    public static void sort(int[] arr){
        quickSort(arr,0,arr.length-1);
    }
    public static void quickSort(int[] arr,int l,int r){
        if(l>=r)return;//可以优化为当r-l小于某个值的时候使用插入排序
        int p = partition(arr, l, r);
        quickSort(arr,l,p-1);
        quickSort(arr,p+1,r);
    }
    private static int partition(int[] arr, int l, int r) {
        int pivot = arr[l];
        int j = l;
        int i = l+1;
        while(i<=r){
            if(arr[i]>pivot){
                i++;
            }else{
                SortUtil.swap(arr,++j,i);
                i++;
            }
        }
        SortUtil.swap(arr,l,j);
        return j;
    }

    public static void main(String[] args) {

        int[] arr = {1,9,8,6,5,4,3,2,1,10,0};
        sort(arr);
        for( int i = 0 ; i < arr.length ; i ++ ){
            System.out.print(arr[i]);
            System.out.print(' ');
        }
        System.out.println();
    }
}
