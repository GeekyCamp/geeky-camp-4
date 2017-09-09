package edu.geekycamp.datastructures;

import java.util.ArrayList;

/*
 * Hash table which resolves collisions with separate chaining with LinkedLists
 * Read about other collision resolution strategies (Hash table's Wikipedia page) and implement one of them
 */

public class HashTable<K, V> {
	private class HashTableEntry {
		public K key;
		public V value;

		public HashTableEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	private static final int MOD = 101;
	private int size;

	private ArrayList<LinkedList<HashTableEntry>> map = new ArrayList<LinkedList<HashTableEntry>>();

	public HashTable() {
		for(int i = 0; i < MOD; i++) {
			map.add(new LinkedList<HashTableEntry>());
		}
		size = 0;
	}

	public int size() {
		return size;
	}

	public void insert(K key, V value) {
		int hashCode = Math.abs(key.hashCode()) % MOD;
		LinkedList<HashTableEntry> bucket = map.get(hashCode);
		int bucketSize = bucket.size();

		for(int i = 0; i < bucketSize; i++) {
			if(key.equals(bucket.get(i).key)) {
				bucket.get(i).value = value;
				return;
			}
		}

		bucket.pushBack(new HashTableEntry(key, value));
		size++;
	}

	public V get(K key) {
		int hashCode = Math.abs(key.hashCode()) % MOD;
		LinkedList<HashTableEntry> bucket = map.get(hashCode);
		int bucketSize = bucket.size();

		for(int i = 0; i < bucketSize; i++) {
			if(key.equals(bucket.get(i).key)) {
				return bucket.get(i).value;
			}
		}

		return null;
	}

	public void remove(K key) {
		int hashCode = Math.abs(key.hashCode()) % MOD;
		LinkedList<HashTableEntry> bucket = map.get(hashCode);
		int bucketSize = bucket.size();

		for(int i = 0; i < bucketSize; i++) {
			if(key.equals(bucket.get(i).key)) {
				bucket.remove(i);
				size--;
				return;
			}
		}
	}
}
