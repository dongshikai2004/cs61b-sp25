package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    private int initialCapacity;
    private double loadFactor;
    private int size;
    private Collection<Node>[] buckets;

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
        @Override
        public int hashCode(){
            return key.hashCode();
        }
    }

    /* Instance Variables */
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        this(16,0.75);
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity,0.75);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        this.size=0;
        this.initialCapacity=initialCapacity;
        this.loadFactor=loadFactor;
        this.buckets=new Collection[initialCapacity];
        for(int i=0;i<initialCapacity;++i){
            buckets[i]=createBucket();
        }
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        // TODO: Fill in this method.
        return new ArrayList<Node>();
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!
    @Override
    public void put(K key, V value) {
        if(loadFactor*buckets.length<size){
            resize();
        }
        int index=Math.floorMod(key.hashCode(),buckets.length);
        for(Node a:buckets[index]){
            if(a.key.equals(key)){
                a.value=value;
                return;
            }
        }
        buckets[index].add(new Node(key,value));
        size++;
    }

    @Override
    public V get(K key) {
        int index=Math.floorMod(key.hashCode(),buckets.length);
        for(Node a:buckets[index]){
            if(a.key.equals(key)){
                return a.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int index=Math.floorMod(key.hashCode(),buckets.length);
        for(Node a:buckets[index]){
            if(a.key.equals(key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        buckets=new Collection[initialCapacity];
        for(int i=0;i<initialCapacity;++i){
            buckets[i]=createBucket();
        }
        size=0;
    }

    @Override
    public Set<K> keySet() {
        HashSet<K> keySet=new HashSet<>();
        for (Collection<Node> bucket : buckets) {
            for (Node a : bucket) {
                keySet.add(a.key);
            }
        }
        return keySet;
    }

    @Override
    public V remove(K key) {
        V res=null;
        Node remove=null;
        int index=Math.floorMod(key.hashCode(),buckets.length);
        for(Node a:buckets[index]){
            if(a.key.equals(key)){
                res=a.value;
                remove=a;
                break;
            }
        }
        buckets[index].remove(remove);
        return res;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int bucketIndex = 0;
            private Iterator<Node> currentBucketIterator = null;
            private boolean hasFoundNext = false;
            private K nextKey = null;

            private void findNextBucket() {
                while (bucketIndex < buckets.length) {
                    if (!buckets[bucketIndex].isEmpty()) {
                        currentBucketIterator = buckets[bucketIndex].iterator();
                        return;
                    }
                    bucketIndex++;
                }
                currentBucketIterator = null;
            }
            private void findNext() {
                if (hasFoundNext) return;
                while (true) {
                    if (currentBucketIterator == null) {
                        findNextBucket();
                        if (currentBucketIterator == null) {
                            nextKey = null;
                            hasFoundNext = true;
                            return;
                        }
                    }
                    if (currentBucketIterator.hasNext()) {
                        nextKey = currentBucketIterator.next().key;
                        hasFoundNext = true;
                        return;
                    } else {
                        bucketIndex++;
                        currentBucketIterator = null;
                    }
                }
            }
            @Override
            public boolean hasNext() {
                findNext();
                return nextKey != null;
            }
            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                K result = nextKey;
                hasFoundNext = false;
                nextKey = null;
                return result;
            }
        };
    }

    private void resize(){
        Collection<Node>[] newbuckets = new Collection[buckets.length * 2];
        for(int i=0;i<newbuckets.length;++i){
            newbuckets[i]=createBucket();
        }
        for (Collection<Node> bucket : buckets) {
            for (Node a : bucket) {
                int index = Math.floorMod(a.key.hashCode(), newbuckets.length);
                newbuckets[index].add(a);
            }
        }
        buckets=newbuckets;
    }
}
