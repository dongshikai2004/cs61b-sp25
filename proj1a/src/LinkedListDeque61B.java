import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T>{
    public class Node {
        public Node prev;
        public T item;
        public Node next;
        public Node(T item,Node prev,Node next){
            this.prev=prev;
            this.next=next;
            this.item=item;
        }
    }
    private Node sentinel;
    private int size;
    public LinkedListDeque61B(){
        this.sentinel=new Node(null,null,null);
        this.sentinel.prev=sentinel;
        this.sentinel.next=sentinel;
        this.size=0;
    }
    @Override
    public void addFirst(T x) {
        Node newNode=new Node(x,sentinel,sentinel.next);
        sentinel.next.prev=newNode;
        sentinel.next=newNode;
        size++;
    }

    @Override
    public void addLast(T x) {
        Node newNode=new Node(x,sentinel.prev,sentinel);
        sentinel.prev.next=newNode;
        sentinel.prev=newNode;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList=new ArrayList<>();
        Node point=sentinel.next;
        while(point!=sentinel){
            returnList.add(point.item);
            point=point.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if(size==0) return null;
        size--;
        Node First=sentinel.next;
        sentinel.next=First.next;
        First.next.prev=sentinel;
        return First.item;
    }

    @Override
    public T removeLast() {
        if(size==0) return null;
        size--;
        Node First=sentinel.prev;
        sentinel.prev=First.prev;
        First.prev.next=sentinel;
        return First.item;
    }

    @Override
    public T get(int index) {
        if(index>=size||index<0)return null;
        Node point=sentinel.next;
        for(int i=0;i<index;++i){
            point=point.next;
        }
        return point.item;
    }

    @Override
    public T getRecursive(int index) {
        if(index>=size||index<0)return null;
        return getRecursive_t(sentinel.next,index);
    }
    public T getRecursive_t(Node point,int index){
        if(index==0)return point.item;
        return getRecursive_t(point.next,index-1);
    }
}
