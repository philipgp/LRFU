package com.cache;

import static org.junit.Assert.*;

import org.junit.Test;

public class LrfuCacheTest {

	/**
	 * didn't insert any value. should give -1
	 */
	@Test
	public void get1() {
		LrfuCache cache =  new LrfuCache();
		assertEquals(-1,cache.get("key").intValue());
	}

	
	/**
	 * first insert
	 */
	@Test
	public void get2() {
		LrfuCache cache =  new LrfuCache();
		cache.put("key", 1);
		assertEquals(1,cache.get("key").intValue());
	}
	
	/**
	 * two inserts . getting by first key
	 * no collision as of now
	 */
	@Test
	public void get3() {
		LrfuCache cache =  new LrfuCache();
		cache.put("key", 1);
		cache.put("key1", 2);
		assertEquals(2,cache.get("key1").intValue());
		assertEquals(1,cache.get("key").intValue());
	}
	
	/**
	 * two inserts . getting by first key
	 * one collision as of now
	 */
	@Test
	public void get4() {
		LrfuCache cache =  new LrfuCache();
		cache.put("key", 1);
		cache.put("key1", 2);
		cache.put("key2", 3);
		assertEquals(2,cache.get("key1").intValue());
		assertEquals(3,cache.get("key2").intValue());
		assertEquals(1,cache.get("key").intValue());
	}
	
	/**
	 * 5 inserts 
	 * now the list is full.
	 */
	@Test
	public void get5() {
		LrfuCache cache =  new LrfuCache();
		cache.put("key", 1);
		cache.put("key1", 2);
		cache.put("key2", 3);
		cache.put("key3", 4);
		cache.put("key4", 5);
		assertEquals(2,cache.get("key1").intValue());
		assertEquals(3,cache.get("key2").intValue());
		assertEquals(1,cache.get("key").intValue());
		assertEquals(4,cache.get("key3").intValue());
		assertEquals(5,cache.get("key4").intValue());
	}
	
	@Test
	public void get6() {
		LrfuCache cache =  new LrfuCache();
		cache.put("key", 1);
		cache.put("key1", 2);
		cache.get("key1");
		cache.put("key2", 3);
		cache.get("key2");
		cache.put("key3", 4);
		cache.get("key3");
		cache.put("key4", 5);
		cache.get("key4");
		cache.put("key5", 6);
		assertEquals(2,cache.get("key1").intValue());
		assertEquals(3,cache.get("key2").intValue());
		assertEquals(-1,cache.get("key").intValue());
		assertEquals(4,cache.get("key3").intValue());
		assertEquals(5,cache.get("key4").intValue());
		assertEquals(6,cache.get("key5").intValue());
	}

}
