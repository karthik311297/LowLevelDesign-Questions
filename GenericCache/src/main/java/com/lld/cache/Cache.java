package com.lld.cache;

public class Cache
{
    public static class CacheBuilder<T>
    {
        private int size;
        private EvictionPolicy evictionPolicy;
    
        public CacheBuilder<T> withSize(int size)
        {
            this.size = size;
            return this;
        }
        
        public CacheBuilder<T> withEvictionPolicy(EvictionPolicy evictionPolicy)
        {
            this.evictionPolicy = evictionPolicy;
            return this;
        }
        
        public InMemoryCache<T> build()
        {
            return InMemoryCacheFactory.getInMemoryCache(this.size, this.evictionPolicy);
        }
    }
}
