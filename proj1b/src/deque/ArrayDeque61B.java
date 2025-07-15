package deque;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T>{
    private int size;
    private T[] back;
    private int first;
    public ArrayDeque61B() {
        this.size=0;
        this.back=(T[]) new Object[8];
        this.first=0;
    }

    @Override
    public void addFirst(T x) {
        resize();
        first=Math.floorMod(first-1,back.length);
        back[first]=x;
        size++;

    }

    @Override
    public void addLast(T x) {
        resize();
        back[getLoc(size)]=x;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> ans= new ArrayList<>();
        for(int i=0;i<size;++i){
            ans.add(get(i));
        }
        return ans;
    }

    @Override
    public boolean isEmpty() {
        return (size==0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if(size==0)return null;
        resize();
        T res=back[first];
        first=Math.floorMod(first+1,back.length);
        size--;
        return res;
    }

    @Override
    public T removeLast() {
        if(size==0)return null;
        resize();
        T res=get(size-1);
        size--;
        return res;
    }

    @Override
    public T get(int index) {
        if(index>=size)return null;
        return back[getLoc(index)];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    private int getLoc(int p){
        return Math.floorMod(p+first,back.length);
    }

    private void resize(){
        if(size==back.length){
            resizeUp();
        }else if(size*4<back.length && back.length>8){
            resizeDown();
        }
    }
    private void resizeUp(){
        T[] newBack=(T[]) new Object[back.length*2];
        for(int i=0;i<size;++i){
            newBack[i]=get(i);
        }
        back=newBack;
        first=0;
    }
    private void resizeDown(){
        T[] newBack=(T[]) new Object[back.length/2];
        for(int i=0;i<size;++i){
            newBack[i]=get(i);
        }
        back=newBack;
        first=0;
    }
    @Override
    public Iterator<T> iterator(){
        return new Iterator<T>(){
            private int p=0;
            @Override
            public boolean hasNext() {
                return p!=size;
            }

            @Override
            public T next() {
                if(!hasNext())return null;
                p++;
                return get(p-1);
            }
        };
    }
    @Override
    public boolean equals(Object obj){
        if(obj instanceof ArrayDeque61B<?> a){
            if(this.size!=a.size)return false;
            for(int i=0;i<size;++i){
                if(!this.get(i).equals(a.get(i)))return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        StringBuilder res= new StringBuilder("[");
        for(int i=0;i<size-1;++i){
            res.append(get(i));
            res.append(", ");
        }
        res.append(get(size-1));
        res.append("]");
        return res.toString();
    }
}
