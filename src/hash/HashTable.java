package hash;

import java.util.TreeMap;

public class HashTable<K, V> {

    private static final int upperTol = 10;
    private static final int lowerTol = 2;
    private static final int initCapacity = 7;

    private TreeMap<K, V>[] hashtable;
    private int size;
    private int M;

    public HashTable(int M) {
        this.M = M;
        this.size = 0;

        this.hashtable = new TreeMap[M];
        for (int i = 0; i < hashtable.length; i++) {
            hashtable[i] = new TreeMap<>();
        }
    }

    public HashTable(){
        this(initCapacity);
    }

    public int hash(K key){
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void add(K key, V value){
        TreeMap<K, V> map = hashtable[hash(key)];

        if (map.containsKey(key)){
            map.put(key, value);
        }else {
            map.put(key, value);
            size++;

            if(size >= upperTol * M)
                resize(2 * M);
        }
    }

    public V remove(K key){
        V ret = null;
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)){
            size--;
            ret = map.remove(key);

            if(size < lowerTol * M && M / 2 >= initCapacity)
                resize(M / 2);
        }
        return ret;
    }

    public void set(K key, V value){
        TreeMap<K, V> map = hashtable[hash(key)];
        if(!map.containsKey(key))
            throw new IllegalArgumentException(key + " doesn't exist!");

        map.put(key, value);
    }

    public boolean contains(K key){
        return hashtable[hash(key)].containsKey(key);
    }

    public V get(K key){
        return hashtable[hash(key)].get(key);
    }

    private void resize(int newM){
        TreeMap[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newHashTable.length; i++) {
            newHashTable[i] = new TreeMap();
        }

        this.M = newM;
        for (int i = 0; i < hashtable.length; i++) {
            TreeMap<K, V> map = hashtable[i];
            map.forEach((k, v) -> {
                newHashTable[hash(k)].put(k,v);
            });
        }

        this.hashtable = newHashTable;
    }

}
