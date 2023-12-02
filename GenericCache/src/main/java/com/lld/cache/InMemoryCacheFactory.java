package com.lld.cache;

public class InMemoryCacheFactory
{
    public static InMemoryCache getInMemoryCache(int size, EvictionPolicy evictionPolicy)
    {
        switch(evictionPolicy)
        {
            case LFU:
                return new LFUCache<>(size);
            case LRU:
            default:
                return new LRUCache<>(size);
        }
    }
}
