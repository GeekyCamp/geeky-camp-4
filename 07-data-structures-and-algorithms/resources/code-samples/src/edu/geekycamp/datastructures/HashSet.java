package edu.geekycamp.datastructures;

import java.util.ArrayList;

/*
 * Hash set which resolves collisions with separate chaining with LinkedLists
 */

public class HashSet<E> {	
	private static final int MOD = 101;
	private int size;

	private ArrayList<LinkedList<E>> map = new ArrayList<LinkedList<E>>();

	public HashSet() {
		for(int i = 0; i < MOD; i++) {
			map.add(new LinkedList<E>());
		}
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public void insert(E element) {
		int key = Math.abs(element.hashCode()) % MOD;
		LinkedList<E> bucket = map.get(key);
		int bucketSize = bucket.size();
		
		for(int i = 0; i < bucketSize; i++) {
			if(element.equals(bucket.get(i))) {
				return;
			}
		}
		
		bucket.pushBack(element);
		size++;
	}
	
	public boolean contains(E element) {
		int key = Math.abs(element.hashCode()) % MOD;
		LinkedList<E> bucket = map.get(key);
		int bucketSize = bucket.size();
		
		for(int i = 0; i < bucketSize; i++) {
			if(element.equals(bucket.get(i))) {
				return true;
			}
		}

		return false;
	}
	
	public void remove(E element) {
		int key = Math.abs(element.hashCode()) % MOD;
		LinkedList<E> bucket = map.get(key);
		int bucketSize = bucket.size();
		
		for(int i = 0; i < bucketSize; i++) {
			if(element.equals(bucket.get(i))) {
				bucket.remove(i);
				size--;
				return;
			}
		}
	}
}