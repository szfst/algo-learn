package sort;

public class BubbleSort {
    public static void sort(int[] arr){
        //先把大的排出来
        for(int i = 0;i<arr.length;i++){
            for(int j = 0;j<arr.length-1-i;j++){
                if(arr[j]>arr[j+1]){
                    SortUtil.swap(arr,j,j+1);
                }
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
