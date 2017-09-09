package edu.geekycamp.datastructures;

// ? iterator

public class LinkedList<E> {
	private class Node {
		public E data;
		public Node prev, next;
		
		public Node(E data, Node prev, Node next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}
	
	private Node front, back;
	private int size;
	
	public LinkedList() {
		front = back = null;
		size = 0;
	}
	
	public boolean isEmpty() {
		return (size == 0);
	}
	
	public int size() {
		return size;
	}
	
	public E get(int index) {
		return getNode(index).data;
	}
	
	public E remove(int index) {
		Node node = getNode(index);
		
		size--;
		
		if(node.prev == null && node.next == null) {
			front = back = null;
			return node.data;
		}
		
		if(node.prev == null) {
			front = front.next;
			return node.data;
		}
		
		if(node.next == null) {
			back = back.prev;
			return node.data;
		}

		node.prev.next = node.next;
		node.next.prev = node.prev;
		
		return node.data;
	}
	
	public void pushBack(E value) {
		Node node = new Node(value, null, null);
		
		if(isEmpty()) {
			front = back = node;
			size++;
			return;
		}
		
		size++;
		node.prev = back;
		back.next = node;
		back = node;
	}
	
	public void pushBefore(int index, E value) {
		if(index == 0) {
			Node node = new Node(value, null, front);
			size++;
			front.prev = node;
			front = node;
			return;
		}
				
		Node pin = getNode(index);
		Node prev = pin.prev;
		Node node = new Node(value, prev, pin);

		pin.prev = node;
		prev.next = node;
		size++;
	}
	
	public void print() {
		Node node = front;
		
		while(node != null) {
			System.out.print(node.data.toString() + " ");
			node = node.next;
		}
		System.out.println();
	}
	
	private Node getNode(int index) {
		if(index < 0 || index >= size) {
			throw new RuntimeException();
		}
		
		Node node = front;
		while(index > 0) {
			node = node.next;
			index--;
		}
		
		return node;
	}
}
