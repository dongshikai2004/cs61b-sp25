import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V>{
    private class Node{
        private K key;
        private V val;
        private Node left;
        private Node right;
        private Node parent;
        public Node(K key,V val,Node left,Node right,Node parent){
            this.key=key;
            this.val=val;
            this.left=left;
            this.right=right;
            this.parent=parent;
        }
        public Node get(K k){
            if(k==null)return null;
            if(this.key.compareTo(k)==0){
                return this;
            }else if(this.key.compareTo(k)>0){
                if(this.left==null){
                    return this;
                }else{
                    return this.left.get(k);
                }
            }else{
                if(this.right==null){
                    return this;
                }else{
                    return this.right.get(k);
                }
            }
        }
    }
    private Node root;
    private int size;
    private Set<K> keyset;
    public BSTMap(){
        this.root=null;
        this.size=0;
        this.keyset=null;
    }

    @Override
    public void put(K key, V value) {
        if(root==null){
            root=new Node(key,value,null,null,null);
            size++;
        }else{
            Node leaf=root.get(key);
            if(leaf.key.compareTo(key)==0){
                leaf.val=value;
            }else if(leaf.key.compareTo(key)>0){
                leaf.left=new Node(key,value,null,null,leaf);
                size++;
            }else{
                leaf.right=new Node(key,value,null,null,leaf);
                size++;
            }
        }
    }

    @Override
    public V get(K key) {
        if(root==null)return null;
        Node f=root.get(key);
        if(f.key.equals(key))return f.val;
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        if(root==null)return false;
        Node f=root.get(key);
        return f.key.equals(key);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root=null;
        size=0;
    }
    private void putKeySet(Node t){
        if(t==null)return;
        putKeySet(t.left);
        keyset.add(t.key);
        putKeySet(t.right);
    }
    @Override
    public Set<K> keySet() {
        this.keyset=new TreeSet<>();
        putKeySet(root);
        return keyset;
    }

    @Override
    public V remove(K key) {
        if(root==null)return null;
        Node f=root.get(key);
        if(f.key.compareTo(key)==0){
            --size;
            if(f.left==null&&f.right==null){
                if(f==root){
                    root=null;
                }else if(f==f.parent.left){
                    f.parent.left=null;
                }else{
                    f.parent.right=null;
                }
            }else if(f.left == null){
                if(f==root){
                    root=f.right;
                }else if(f==f.parent.left){
                    f.right.parent=f.parent;
                    f.parent.left=f.right;
                }else{
                    f.right.parent=f.parent;
                    f.parent.right=f.right;
                }
            }else if(f.right == null){
                if(f==root){
                    root=f.left;
                }else if(f==f.parent.left){
                    f.left.parent=f.parent;
                    f.parent.left=f.left;
                }else{
                    f.left.parent=f.parent;
                    f.parent.right=f.left;
                }
            }else{
                //不优化了,把右边接到左子树最右端
                if(f==root){
                    root=f.left;
                }
                Node fl=f.left;
                while(fl.right!=null){
                    fl=fl.right;
                }
                f.right.parent=fl;
                fl.right=f.right;
            }
            return f.val;
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private java.util.Stack<Node> stack = new java.util.Stack<>();
            private Node current = root;
            {
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
            }
            @Override
            public boolean hasNext() {
                return !stack.isEmpty();
            }
            @Override
            public K next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                Node node = stack.pop();
                K result = node.key;
                if (node.right != null) {
                    current = node.right;
                    while (current != null) {
                        stack.push(current);
                        current = current.left;
                    }
                }
                return result;
            }
        };
    }
}
