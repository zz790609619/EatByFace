package com.hiqiblog.util;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @Description: 带有效期map 简单实现 实现了基本的方法
 * @author ww
 * @date: 2019-07-20
 * @param <K>
 * @param <V>
 */
public class ExpiryMap<K, V> extends HashMap<K, V> {

    private static final long serialVersionUID = 1L;

    /**
     * default expiry time 5分钟
     */
    private long EXPIRY = 1000 ;

    private HashMap<K, Long> expiryMap = new HashMap<K, Long>();

    public ExpiryMap(){
        super();
    }
    public ExpiryMap(long defaultExpiryTime){
        this(1 << 4, defaultExpiryTime);
    }
    public ExpiryMap(int initialCapacity, long defaultExpiryTime){
        super(initialCapacity);
        this.EXPIRY = defaultExpiryTime;
    }
    @Override
    public V put(K key, V value) {
        expiryMap.put(key, System.currentTimeMillis() + EXPIRY);
        return super.put(key, value);
    }
    @Override
    public boolean containsKey(Object key) {
        return !checkExpiry(key, true) && super.containsKey(key);
    }
    /**
     * @param key
     * @param value
     * @param expiryTime 键值对有效期 毫秒
     * @return
     */
    public V put(K key, V value, long expiryTime) {
        expiryMap.put(key, System.currentTimeMillis() + expiryTime);
        return super.put(key, value);
    }
    @Override
    public int size() {
        return entrySet().size();
    }
    @Override
    public boolean isEmpty() {
        return entrySet().size() == 0;
    }
    @Override
    public boolean containsValue(Object value) {
        if (value == null){
            return Boolean.FALSE;
        }
        Set<Entry<K, V>> set = super.entrySet();
        Iterator<Entry<K, V>> iterator = set.iterator();
        while (iterator.hasNext()) {
            java.util.Map.Entry<K, V> entry = iterator.next();
            if(value.equals(entry.getValue())){
                if(checkExpiry(entry.getKey(), false)) {
                    iterator.remove();
                    return Boolean.FALSE;
                }else{
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }
    @Override
    public Collection<V> values() {

        Collection<V> values = super.values();

        if(values == null || values.size() < 1){
            return values;
        }

        Iterator<V> iterator = values.iterator();

        while (iterator.hasNext()) {
            V next = iterator.next();
            if(!containsValue(next)) {
                iterator.remove();
            }
        }
        return values;
    }
    @Override
    public V get(Object key) {
        if (key == null){
            return null;
        }

        if(checkExpiry(key, true)){
            return null;
        }
        return super.get(key);
    }
    /**
     * @Description: 是否过期
     * @return null:不存在或key为null -1:过期  存在且没过期返回value 因为过期的不是实时删除，所以稍微有点作用
     * @param key
     * @return
     */
    public Object isInvalid(Object key) {
        if (key == null){
            return null;
        }

        if(!expiryMap.containsKey(key)){
            return null;
        }
        long expiryTime = expiryMap.get(key);

        boolean flag = System.currentTimeMillis() > expiryTime;

        if(flag){
            super.remove(key);
            expiryMap.remove(key);
            return -1;
        }
        return super.get(key);
    }
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()){
            expiryMap.put(e.getKey(), System.currentTimeMillis() + EXPIRY);
        }
        super.putAll(m);
    }
    @Override
    public Set<Map.Entry<K,V>> entrySet() {
        Set<java.util.Map.Entry<K, V>> set = super.entrySet();
        Iterator<java.util.Map.Entry<K, V>> iterator = set.iterator();
        while (iterator.hasNext()) {
            java.util.Map.Entry<K, V> entry = iterator.next();
            if(checkExpiry(entry.getKey(), false)) {
                iterator.remove();
            }
        }

        return set;
    }
    /**
     * @Description: 是否过期
     **/
    private boolean checkExpiry(Object key, boolean isRemoveSuper){

        if(!expiryMap.containsKey(key)){
            return Boolean.FALSE;
        }
        long expiryTime = expiryMap.get(key);

        boolean flag = System.currentTimeMillis() > expiryTime;

        if(flag){
            if(isRemoveSuper){
                super.remove(key);
            }
            expiryMap.remove(key);
        }
        return flag;
    }

}

