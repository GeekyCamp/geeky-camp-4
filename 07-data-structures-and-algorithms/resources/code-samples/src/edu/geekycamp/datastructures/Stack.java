package edu.geekycamp.datastructures;

public class Stack<E> {
	private class Node {
		public E data;
		public Node next;
		
		public Node(E data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
	
	private int size;
	private Node top;
	
	public Stack() {
		top = null;
		size = 0;
	}

	public boolean isEmpty() {
		return (size == 0);
	}
	
	public int getSize() {
		return size;
	}
	
	public E top() {
		if(isEmpty()) {
			throw new RuntimeException("Cannot get top: stack is empty");
		}
		
		return top.data;
	}
	
	public void push(E value) {
		Node node = new Node(value, top);
		top = node;
		size++;
	}
	
	public E pop() {
		if(isEmpty()) {
			throw new RuntimeException("Cannot pop: stack is empty");
		}
		E value = top.data;
		top = top.next;
		size--;
		return value;
	}
}
