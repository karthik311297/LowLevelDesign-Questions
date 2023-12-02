package com.lld.cache;

public abstract class InMemoryCache<T>
{
    protected int size;
    
    protected InMemoryCache(int size)
    {
        this.size = size;
    }
    abstract T get(String key);
    abstract void put(String key, T value);
}
