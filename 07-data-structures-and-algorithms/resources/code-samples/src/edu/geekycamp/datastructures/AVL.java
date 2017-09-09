package edu.geekycamp.datastructures;

/*
 * Invariant:
 * 		empty BST <=> height == 0 && left == null && right == null
 *  	non-empty BST <=> height != 0 && left != null && right != null
 *
 * Balanced invariant:
 * 		The difference in height of two siblings is <= 1
*/

public class AVL<E extends Comparable<E>> {
	private E data;
	private int height;
	private AVL<E> left, right;

	private AVL(E data, AVL<E> left, AVL<E> right) {
		this.data = data;
		this.left = left;
		this.right = right;
		this.height = Math.max(left.height, right.height) + 1;
	}

	private void balance() {
		// Read about self-balancing and tree rotations and implement this :)
	}

	public AVL() {
		height = 0;
		left = right = null;
	}

	public boolean isEmpty() {
		return (height == 0);
	}

	public boolean contains(E value) {
		if(isEmpty()) {
			return false;
		}

		if(value == data) {
			return true;
		}

		return (value.compareTo(data) < 0 ? left : right).contains(value);
	}

	public void insert(E value) {
		if(isEmpty()) {
			this.data = value;
			height = 1;
			left = new AVL<E>();
			right = new AVL<E>();
			return;
		}

		if(value == data) {
			return;
		}

		(value.compareTo(data) < 0 ? left : right).insert(value);

		balance();

		this.height = Math.max(left.height, right.height) + 1;
	}

	public void remove(E value) { // NOTE: this is not he standard AVL remove
		if(isEmpty()) {
			return;
		}

		if(value.compareTo(data) == 0) {
			AVL<E> higherChild = (left.height > right.height ? left : right);

			data = higherChild.data;

			if(higherChild.height == 1) {
				data = higherChild.data;
				height = 1;
				left = right = null;
				return;
			}

			higherChild.remove(data);
		} else if(value.compareTo(data) < 0){
			left.remove(value);
		} else if(value.compareTo(data) > 0){
			right.remove(value);
		}

		this.height = Math.max(left.height, right.height) + 1;
	}

	public void print() {
		if(isEmpty()) {
			return;
		}

		left.print();

		for(int i = 0; i < height; i++) {
			System.out.print("      ");
		}

		//System.out.print("[" + Integer.toString(height) + ",");
		System.out.println(data);

		right.print();
	}
}
