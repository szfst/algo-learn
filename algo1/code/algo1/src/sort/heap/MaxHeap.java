package sort.heap;

import sort.SortUtil;

public class MaxHeap {
    private int count = 0;
    private int capacity ;
    private int[] arr;
    public MaxHeap(int capacity){
        this.capacity = capacity;
        arr = new int[capacity+1];
    }
    //heapify从1开始
    public MaxHeap(int[] arr, int capacity){
        this.capacity = capacity;
        this.arr = new int[capacity+1];
        for(int i = 0;i<arr.length;i++){
            count++;
            this.arr[i+1]=arr[i];
        }
        for(int i = arr.length/2;i>=1;i--){
            shiftDown(i);
        }
    }
    //原地堆排序从0开始
    public void heapSortUsingNoSpace(int[] nums){
         this.count = nums.length;
         arr = nums;
         //heapify
        for(int i = (count-1-1)/2;i>=0;i--){
            shiftDown1(i);
        }
        //sort
        for(int i = arr.length-1;i>=0;i--){
            SortUtil.swap(arr,0,i);
            count--;
            shiftDown1(0);
        }
        for(int i = 0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }
    public int getSize(){
        return count;
    }
    public boolean isEmpty(){
        return count==0;
    }
    public void insert(int value){
        assert count+1<=capacity;
        arr[++count]=value;
        shiftUp(count);
    }
    public int removeMax(){
      if(count<=0)return -1;
      SortUtil.swap(arr,1,count);
      int ret = arr[count--];
      shiftDown(1);
      return ret;
    }
    //索引从0开始的shiftDown
    public void shiftDown(int k){
        int swapK = 2*k;
        if(swapK>count)return ;
        if(swapK+1<=count && arr[swapK]<arr[swapK+1]){
            swapK = swapK+1;
        }
        if(arr[swapK]>arr[k]){
            SortUtil.swap(arr,swapK,k);
            shiftDown(swapK);
        }
    }
    //索引从1开始的shiftDown
    public void shiftDown1(int k){
        int swapK = 2*k+1;
        if(swapK>=count)return ;
        if(swapK+1<count && arr[swapK]<arr[swapK+1]){
            swapK = swapK+1;
        }
        if(arr[swapK]>arr[k]){
            SortUtil.swap(arr,swapK,k);
            shiftDown1(swapK);
        }
    }
    //shiftUp
    private void shiftUp(int k){
        if(k>1 && arr[k]>arr[k/2]){
            SortUtil.swap(arr,k,k/2);
            shiftUp(k/2);
        }
    }
    public void printArr(){
       for(int i = 0;i<=count;i++){
           System.out.println(arr[i]);
       }
    }
    public static void main(String[] args) {
        int[] arr = {1,9,8,0,6,5,4,3,2};
//        MaxHeap h = new MaxHeap(20);
//        MaxHeap h = new MaxHeap(arr,20);
////        System.out.println(h.getSize());
////       for(int i = 0;i<arr.length;i++){
////           h.insert(arr[i]);
////       }
////       h.printArr();
//        for(int i = 0;i<arr.length;i++){
//            System.out.println(h.removeMax());
//        }
        new MaxHeap(20).heapSortUsingNoSpace(arr);
    }

}
