package other;

import java.util.*;

public class LRU {


    /**
     * 继承linkedhashmap来实现lruCache
     */
    public static class LRU1<K,V> extends LinkedHashMap <K,V> {
        private final int MAX_SIZE;

        public LRU1(int size){
            super((int) Math.ceil(size/0.75)+1,0.75f,true);
            MAX_SIZE = size;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size()>MAX_SIZE;
        }
    }

    //使用双向链表和hashmap来实现lru
    public static class LRUCache<K,V> {
        private final int MAX_CHCHE_SIZE;
        private Entry<K,V> first;
        private Entry<K,V> last;
        private HashMap<K, Entry<K,V>> hashMap;

        private class Entry<K, V> {
            public Entry pre;
            public Entry next;
            public K key;
            public V value;
        }

        public LRUCache(int size){
            MAX_CHCHE_SIZE  = size;
            hashMap = new HashMap<>();
        }
         private Entry<K, V> getEntry(K key) {
            return hashMap.get(key);
        }

        public void put(K key,V value){
            Entry entry = getEntry(key);
            //第一次存入
            if(entry==null){
                if(hashMap.size()>=MAX_CHCHE_SIZE){
                    hashMap.remove(last.key);
                    removeLast();
                }
                entry = new Entry();
                entry.key = key;
            }
            entry.value = value;
            moveToFirst(entry);
            hashMap.put(key,entry);
        }

        public V get(K key){
            Entry<K,V> entry = getEntry(key);
            if(entry==null) return null;
            moveToFirst(entry);
            return entry.value;
        }

        public void remove(K key){
            Entry<K,V> entry = getEntry(key);
            if(entry!=null){
                if(entry.pre!=null) entry.pre.next = entry.next;
                if(entry.next!=null) entry.next.pre = entry.pre;
                if(entry==first) first=entry.next;
                if(entry==last){ last = entry.pre; }
            }
            hashMap.remove(key);
        }

        private void moveToFirst(Entry<K,V> entry){
            if(first==entry) return ;
            if(entry.pre !=null) entry.pre.next = entry.next;
            if(entry.next !=null) entry.next.pre = entry.pre;
            if(entry==last) last = last.pre;
            //无元素
            if(first==null||last==null){
                first=last=entry;
                return;
            }
            entry.next = first;
            first.pre = entry;
            first = entry;
            entry.pre = null;
        }


        private void removeLast(){
            if(last!=null){
                last = last.pre;
                //只有一个元素
                if(last==null) first=null;
                else last.next = null;
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Entry entry = first;
            while (entry != null) {
                sb.append(String.format("%s===%s ", entry.key, entry.value));
                entry = entry.next;
            }
            return sb.toString();
        }


    }


    public static void main(String[] args) {
//        LRU1<Integer,Integer> lru2 = new LRU1(10);
//        LRUCache<Integer,Integer> lru1 = new LRUCache(10);
        LinkedHashMap<Integer, Integer> lru1 = new LinkedHashMap<Integer, Integer>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size()>10;
            }
        };
        for(int i=0;i<21;i++){
            lru1.put(i,i);
        }
        lru1.remove(10);
        System.out.println(lru1.toString());

        String preorder = "1,2,3,4,5,45,#,2";
        String[] strs = preorder.split(",");
        System.out.println(Arrays.toString(strs));



    }
}
