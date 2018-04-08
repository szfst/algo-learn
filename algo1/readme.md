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