#####  选择排序
``` java
    public static void sort(int[] arr){
        for(int i = 0;i<arr.length;i++){
            for(int j = i+1;j<arr.length;j++){
                if(arr[j]<arr[i])
                    SortUtil.swap(arr,i,j);
            }
        }
    }
```
#### 插入排序
``` java
    public static void sort(int[] arr){
        for(int i = 1;i<arr.length;i++){
            for(int j = i;j>0 && arr[j]<arr[j-1];j--){
                    SortUtil.swap(arr,j-1,j);
            }
        }
    }
```
#### 插入排序优化（不交换元素）
#### 冒泡排序
```java
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
```
#### 归并排序
``` java
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
```
#### 快速排序
```java
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
```
#### 随机快速排序
```java
    public static void sort(int[] arr){
        quickSort(arr,0,arr.length-1);
    }
    public static void quickSort(int[] arr,int l,int r){
        if(l>=r)return;
        int p = partition(arr,l,r);
        quickSort(arr,l,p-1);
        quickSort(arr,p+1,r);
    }
    public static int partition(int[] arr,int l,int r){
        SortUtil.swap(arr,l,SortUtil.randomIntBetween(l, r));
        int compare = arr[l];
        int j = l;
        for(int i = l+1;i<=r;i++){
            if(arr[i]<compare)SortUtil.swap(arr,i,++j);
        }
        SortUtil.swap(arr,l,j);
        return j;
    }
```
#### 三路快排
```java
public static void sort(int[] arr){
        quickSort(arr,0,arr.length-1);
    }
    private static void quickSort(int[] arr,int l,int r){
        if(l>=r)return;
        int[] partition = partition(arr, l, r);
        quickSort(arr,l,partition[0]);
        quickSort(arr,partition[1],r);
    }
    private static int[] partition(int[] arr,int l,int r){
        SortUtil.swap(arr,l,SortUtil.randomIntBetween(l, r));
        int compare = arr[l];
        int lt = l;
        int gt = r+1;
        int i = l+1;
        while(i<gt){
            if(arr[i]==compare){
                i++;
            }else if(arr[i]>compare){
                SortUtil.swap(arr,i,--gt);
            }else {
                SortUtil.swap(arr,i++,++lt);
            }
        }
        SortUtil.swap(arr,lt,l);
        return new int[]{lt-1,gt};
    }
```
#### 利用归并排序归并的思路寻找逆序对
```java
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
```
#### 利用快速排序思路解决：求一个数组中第k大的值，用O(n)的时间复杂度。
```java
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
        SortUtil.swap(arr,l,SortUtil.randomIntBetween(l, r));
        int compare = arr[l];
        int j = l;
        for(int i = l+1;i<=r;i++){
            if(arr[i]<compare)SortUtil.swap(arr,++j,i);
        }
        SortUtil.swap(arr,j,l);
        return j;
    }
```
#### 构建堆
#### 堆插入（shiftUp）
```java
    public void insert(int value){
        assert count+1<=capacity;
        arr[++count]=value;
        shiftUp(count);
    }
    private void shiftUp(int k){
        if(k>1 && arr[k]>arr[k/2]){
            SortUtil.swap(arr,k,k/2);
            shiftUp(k/2);
        }
    }
``` 
#### 堆移除元素(最大值和最后一个元素交换位置之后shiftDown)
```java
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
``` 
#### heapify
    public MaxHeap(int[] arr,int capacity){
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
#### 原地堆排序，索引从0开始
```java
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
```
#### 排序算法的稳定性
插入排序、归并排序稳定；快速排序和堆排序不稳定
#### 索引堆
#### 用堆（优先队列）实现n路归并排序
- https://leetcode.com/submissions/detail/146156487/
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
    PriorityQueue<ListNode> p = new PriorityQueue<>((o1, o2) -> o1.val<o2.val?-1:1);
       for(ListNode item:lists){
           if(item!=null){
               p.add(item);
           }
       }
       ListNode head = new ListNode(0);
       ListNode cur = head;
       while(!p.isEmpty()){
           ListNode peek = p.remove();
           cur.next = peek;
           if(peek.next!=null){
               p.add(peek.next);
           }
           cur=cur.next;
       }
       return head.next;
    }
}
```