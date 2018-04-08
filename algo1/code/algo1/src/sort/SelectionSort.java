package sort;

public class SelectionSort {
    public static void sort(int[] arr){
        for(int i = 0;i<arr.length;i++){
            for(int j = i+1;j<arr.length;j++){
                if(arr[j]<arr[i])SortUtil.swap(arr,i,j);
            }
        }
    }
    public static void main(String[] args) {

        int[] arr = {1,9,8,0,6,5,4,3,2,1};
        sort(arr);
        for( int i = 0 ; i < arr.length ; i ++ ){
            System.out.print(arr[i]);
            System.out.print(' ');
        }
        System.out.println();
    }

}
