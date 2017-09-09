package edu.geekycamp.datastructures;

public class Queue<E> {
	private class Node {
		public E data;
		public Node next;
		
		public Node(E data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
	
	private Node front, back;
	private int size;
	
	public Queue() {
		front = back = null;
		size = 0;
	}
	
	public boolean isEmpty() {
		return (size == 0);
	}
	
	public int getSize() {
		return size;
	}
	
	public E front() {
		if(isEmpty()) {
			throw new RuntimeException("Cannot get front: queue is empty");
		}
		
		return front.data;
	}
	
	public void push(E value) {
		Node node = new Node(value, null);
		
		if(isEmpty()) {
			size = 1;
			front = back = node;
			return;
		}
		
		size++;

		back.next = node;
		back = node;
	}
	
	public E pop() {
		if(isEmpty()) {
			throw new RuntimeException("Cannot pop: queue is empty");
		}
		
		E value = front.data;
		front = front.next;
		size--;
		
		if(isEmpty()) {
			front = back = null;
		}
		
		return value;
	}
}
