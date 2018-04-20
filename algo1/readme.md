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
        //先把大的排出来（这里可以优化为arr.length-1，因为最后一轮只有一个元素，不用比较了）
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

#### 二分搜索
```java
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
```
#### 构建二分搜索树（不需要是完全二叉树，所以不用数组存储；插入、删除、查找都时间复杂度都为lgN）
- 性质一：左节点<跟节点<右节点
- 性质二：它的左右子树均为二分查找树。
- 性质三：若它的左子树不为空，则左子树上所有结点的值均小于根结点的值；若它的右子树不为空，则右子树上所有结点的值均大于根结点的值；
#### 二分搜索树插入
```java
    public void insert(int key,int value){
        root = insert(root,key,value);
    }
    private Node insert(Node node,int key,int value){
        if(node==null){
            count++;
            return new Node(key,value);
        }
        //如果是当前节点，修改值
        if(key==node.key){
            node.value = value;
        }else if(key>node.key){
            node.right =  insert(node.right,key,value);
        }else{
            node.left =  insert(node.left,key,value);
        }
        return node;
    }
```
#### 二分搜索树查找
```java
    public Node search(int key){
        return search(root,key);
    }
    private Node search(Node node,int key){
        if(node==null)return null;
        if(node.key == key){
            return node;
        }else if (node.key<key){
            return search(node.right,key);
        }else {
            return search(node.left,key);
        }
    }
```
#### 二分搜索树深度优先遍历（前序遍历、中序遍历、后续遍历）
```java
   public void preOrderTravel(){
        preOrderTravel(root);
    }
    private void preOrderTravel(Node node){
        if(node==null) return ;
        System.out.println(node.key);
        preOrderTravel(node.left);
        preOrderTravel(node.right);
    }
    public void inOrderTravel(){
        inOrderTravel(root);
    }
    private void inOrderTravel(Node node){
        if(node==null) return ;
        System.out.println(node.key);
        inOrderTravel(node.left);
        inOrderTravel(node.right);
    }
    public void postOrderTravel(){
        postOrderTravel(root);
    }
    private void postOrderTravel(Node node){
        if(node==null) return ;
        System.out.println(node.key);
        postOrderTravel(node.left);
        postOrderTravel(node.right);
    }
```
#### 二叉树层序遍历
```java
    public void levelOrderTravel(){
        if(root==null)return;
        //使用LinkedList来作为我们的队列
        LinkedList<Node> p = new LinkedList<>();
        p.add(root);
        while(!p.isEmpty()){
            Node node = p.remove();
            if(node.left!=null)p.add(node.left);
            if(node.right!=null)p.add(node.right);
            System.out.println(node.value);
        }
    }
```
#### 二叉搜索树删除节点
- 只有左孩子或者右孩子：删除了直接把左孩子或者右孩子添加到原来的位置
- 既有左孩子又有右孩子：根据二叉搜索树的定义，右子树的最小值既大于左子树最大值，又小于右子树最大值，适合替换当前删除的节点
#### 通过二叉搜索树找出某个值的位置（rank），或者通过某个值的位置找出某个值（select）：构造二叉搜索树的时候，节点增加一个值，这个值就是孩子节点的个数。
#### 平衡二叉树局限性：有可能导致不平衡，所以出现平衡二叉树，例如红黑树，avl树，2-3tree等。
#### 通过前序遍历和中序遍历找出后续遍历的思路：
先根据前序遍历找出根节点，再根据中序遍历和根节点找出左右子树，就可以递归地找到树的结构进而求出后序遍历（也可以不找出树的结构直接输出后续遍历）
#### 并查集
- 思考我们解决一个问题的时候，是不是顺带解决了别的问题，所以算法的性能不能达到最优
- 并查集优化结构：定义一个父节点，默认指向自己，并的时候指向根节点
- 并查集优化一：数量少的集并向数量多的集
- 并查集优化二：层数少的集并向层数多的集合，两个集合层数一样的时候，层数才会加一
- 并查集优化三：路径压缩，时间复杂度可以近乎O(1)
#### 图的深度优先遍历
- 使用邻接表时间复杂度O(V+E)，使用邻接矩阵时间复杂度O(V^2)
#### 图的广度优先遍历
- 使用邻接表时间复杂度O(V+E)，使用邻接矩阵时间复杂度O(V^2)
- 无权图的广度优先遍历可以 找出最短路径
#### 最小生成树
- 有权图的最小生成树，针对连通图
- 切分定理：给定任意切分，横切边中权值最小的边必然属于最小生成树
- lazy prim：用最小堆实现，O(ElogE)
- prim 优化：入队的时候，求出与各个边相邻的最短边。用索引堆实现。堆里面只维护V个元素。时间复杂度O(ElgV)
- Kruskal:每次找出最小边，只要这条边不能构成环，就是最小生成树相应的一条边。如何监控是否形成环：并查集，将边加入最小生成树的时候把这个边union一下；先把所有的边加入最小堆中， 每次取出最小的表。0(ElogE)
#### 单源最短路径：
- 生成最短路径树
- 针对有权图
- 松弛操作
- dijkstra单源最短路径算法：图中不能有负权边，适合有向图和无向图，O(ElgV)。使用最小索引堆。
- bellman-ford:处理负权边，但是不能有负权环，如果有负权环，不存在最短路径。这个算法能够检测出是否有负权环（最后遍历一遍，如果还可以松弛操作，则说明有负权环），如果一个图中没有负权环，从一点到另一点的最短路径，最多经过所有的v个顶点，有v-1条边，否则，存在的顶点经过了两次，即存在负权环。
- 利用拓扑排序：处理有向无权图（DAG）时间复杂度O（V+E）
- floyed：处理无负权环的图的所有最短路径算法，利用了动态规划，时间复杂度O(V3)
- 最长路径:对于有权图，不能使用dijkstra求解最长路径问题，但是可以使用bellman ford求解，取相反数求出最短路径即可。最长路径问题不能有正权环，无权图的最长路径问题是指数级难度的。
#### 算法思想：
- 分治思想：归并排序，快速排序，树结构
- 贪心思想：选择排序，堆，kruskal，prim，dijkstra
- 递归回溯：树的遍历，图的遍历
- 动态规划：prim，dijkstra
