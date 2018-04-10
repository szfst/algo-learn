package sort.heap;

import java.util.LinkedList;

public class BinarySearchTree {
    // 树中的节点为私有的类, 外界不需要了解二分搜索树节点的具体实现
    private class Node {
        private int key;
        private int value;
        private Node left, right;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            left = right = null;
        }
    }
    private Node root;  // 根节点
    private int count;  // 树种的节点个数

    // 构造函数, 默认构造一棵空二分搜索树
    public BinarySearchTree() {
        root = null;
        count = 0;
    }

    // 返回二分搜索树的节点个数
    public int size() {
        return count;
    }

    // 返回二分搜索树是否为空
    public boolean isEmpty() {
        return count == 0;
    }
    public void insert(int key,int value){
        root = insert(root,key,value);
    }
    public boolean contains(int key){
        return contains(root,key);
    }
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

    private boolean contains(Node node,int key){
        if(node==null)return false;
        if(node.key == key)return true;
        if(node.key<key){
            return contains(node.right,key);
        }else{
            return contains(node.left,key);
        }
    }
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
    public Node getMax(){
        return getMax(root);
    }
    public Node getMin(){
        return getMin(root);
    }
    private Node getMax(Node node){
        if(node==null)return null;
        if(node.right==null)return node;
        return getMax(node.right);
    }
    private Node getMin(Node node){
        if(node==null)return null ;
        if(node.left==null)return node;
        return getMin(node.left);
    }


}
