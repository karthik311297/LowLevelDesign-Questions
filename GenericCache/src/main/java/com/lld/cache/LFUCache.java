package com.lld.cache;

import java.util.HashMap;
import java.util.Map;

public class LFUCache<T> extends InMemoryCache<T>
{
    private Map<String,T> cache = new HashMap<>();
    private Map<String, Integer> keyFrq = new HashMap<>();
    public LFUCache(int size)
    {
        super(size);
    }
    
    @Override
    public T get(String key)
    {
        if(cache.containsKey(key))
        {
            keyFrq.put(key, keyFrq.get(key) + 1);
            return cache.get(key);
        }
        return null;
    }
    
    @Override
    public void put(String key, T value)
    {
        keyFrq.put(key, keyFrq.getOrDefault(key, 0) + 1);
        cache.put(key, value);
        if(cache.size() >= size)
        {
            evict();
        }
    }
    
    private void evict()
    {
        int minFrq = Integer.MAX_VALUE;
        String minFrqKey = "";
        for(Map.Entry<String, Integer> entry : keyFrq.entrySet())
        {
            if(entry.getValue() < minFrq)
            {
                minFrq = entry.getValue();
                minFrqKey = entry.getKey();
            }
        }
        keyFrq.remove(minFrqKey);
        cache.remove(minFrqKey);
    }
}
