import edu.geekycamp.datastructures.Stack;
import edu.geekycamp.datastructures.Queue;
import edu.geekycamp.datastructures.LinkedList;
import edu.geekycamp.datastructures.AVL;
import edu.geekycamp.datastructures.HashSet;
import edu.geekycamp.datastructures.HashTable;

import edu.geekycamp.algorithms.Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	static void testStack() {
		Stack<Integer> s = new Stack<Integer>();

		System.out.println(s.isEmpty());

		for(int i = 1; i < 10; i++) {
			s.push(i);
		}

		while(!s.isEmpty()) {
			System.out.println(s.pop());
		}

		s.push(10);

		try {
			System.out.println(s.pop());
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}

	static void testQueue() {
		Queue<String> q = new Queue<String>();

		System.out.println(q.isEmpty());

		for(int i = 1; i < 10; i++) {
			q.push(Integer.toString(i));
		}

		while(!q.isEmpty()) {
			System.out.println(q.pop());
		}

		q.push("10");

		try {
			System.out.println(q.pop());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	static void testLinkedList() {
		LinkedList<Double> l = new LinkedList<Double>();

		for(double i = 0.5; i < 10; i++) {
			l.pushBack(i);
		}

		l.print();
		l.pushBefore(0, new Double(0));
		l.pushBefore(6, new Double(5));
		l.print();
		l.remove(7);
		l.print();
	}

	static void testAVL() {
		AVL<Character> bst = new AVL<Character>();

		List<Character> helloWorld = Arrays.asList('h','e','l','l','o','w','o','r','l','d');
		List<Character> geekyCamp = Arrays.asList('g','e','e','k','y','c','a','m','p');
		List<Character> alphabet = Arrays.asList('a','b','c','d','e','f','g','h','i','j','k','l','m');

		for(int i = 0; i < alphabet.size(); i++) {
			bst.insert(alphabet.get(i));
		}

		bst.print();
	}

	static void testHashSet() {
		HashSet<Book> set = new HashSet<Book>();

		Book hamlet = new Book("Hamlet", "William Shakespeare", 1987);
		Book tales = new Book("Fairy Tales", "Grimm Brothers", 2007);
		Book robinson = new Book("Robinson Crusoe", "Daniel Defoe", 1993);
		Book robinsonCopy = new Book("Robinson Crusoe", "Daniel Defoe", 1993);

		set.insert(hamlet);
		set.insert(tales);
		set.insert(robinson);

		System.out.println("Map size: " + Integer.toString(set.size()));
		System.out.println(set.contains(robinsonCopy));
	}

	static void testHashTable() {
		HashTable<String, Book> map = new HashTable<String, Book>();

		map.insert("favourite", new Book("Hamlet", "William Shakespeare", 1967));
		map.insert("borrowed", new Book("Hamlet", "William Shakespeare", 1967));
		map.insert("robinson", new Book("Robinson Crusoe", "Daniel Defoe", 1991));

		System.out.println(map.size());

		map.insert("favourite", new Book("Animal Farm", "George Orwell", 2003));

		System.out.println(map.size());

		System.out.println(map.get("favourite").toString());
	}

	static void testSorting() {
		ArrayList<Integer> list = new ArrayList<Integer>();

		list.add(5);
		list.add(3);
		list.add(2);
		list.add(1);
		list.add(4);
		list.add(8);
		list.add(6);
		list.add(9);
		list.add(7);

		Sorting.print(list);

//		Sorting.selectionSort(list);
//		Sorting.mergeSort(list, 0, list.size());
		Sorting.quickSort(list, 0, list.size());
		Sorting.print(list);

//		System.out.println(Sorting.binarySearch(list, 0, list.size(), 10));
	}

	public static void main(String[] args) {
//		testStack();
//		testQueue();
//		testLinkedList();
//		testAVL();
//		testHashSet();
//		testHashTable();

//		testSorting();
	}
}
