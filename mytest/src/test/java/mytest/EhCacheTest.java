package mytest;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

public class EhCacheTest {

	public static void main(String[] args) {
		CacheManager cacheManager = CacheManager.getInstance();

		CacheConfiguration conf = new CacheConfiguration();
		conf.name("cache_name_default");// 设置名字
		conf.maxEntriesLocalHeap(1000);// 最大的缓存数量
		conf.memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU);// 设置失效策略

		// 创建一个缓存对象，并把设置的信息传入进去
		Cache localCache = new Cache(conf);
		//将缓存对象添加到管理器中
        cacheManager.addCache(localCache);
        
        localCache.put(new Element("wanggg", 123456));
        
        System.out.println(localCache.getSize());
        System.out.println(localCache.getStatistics().toString());
        System.out.println(localCache.getName());
        System.out.println(localCache.get("wanggg").toString());
        System.out.println(localCache.get("wanggg").getObjectValue()); 
        
        localCache.removeAll();
        //System.out.println(localCache.getSize());
		

	}

}
