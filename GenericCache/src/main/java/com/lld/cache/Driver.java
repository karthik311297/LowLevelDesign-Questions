package com.lld.cache;

public class Driver
{
    public static void main(String[] args)
    {
        InMemoryCache<Integer> inMemoryCache = new Cache.CacheBuilder<Integer>()
                .withSize(10)
                .withEvictionPolicy(EvictionPolicy.LFU)
                .build();
        InMemoryCache<String> inMemoryCache2 = new Cache.CacheBuilder<String>()
                .withSize(10)
                .withEvictionPolicy(EvictionPolicy.LFU)
                .build();
        inMemoryCache2.put("hello", "bye");
        inMemoryCache.put("hello", 1);
        System.out.println(inMemoryCache2.get("hello"));
        System.out.println(inMemoryCache.get("hello"));
    }
}
