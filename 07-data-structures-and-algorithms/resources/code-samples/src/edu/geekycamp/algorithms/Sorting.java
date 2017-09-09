package edu.geekycamp.algorithms;

import java.util.ArrayList;
import java.util.Collections;

public class Sorting {
	public static void log(Object o) {
		System.out.println(o);
	}

	public static <E> void print(ArrayList<E> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println();
	}

	public static <E extends Comparable<E>> void selectionSort(ArrayList<E> list) {
		for (int i = 0; i < list.size(); i++) {
			int minIndex = i;
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(j).compareTo(list.get(minIndex)) < 0) {
					minIndex = j;
				}
			}

			E minValue = list.get(minIndex);
			list.set(minIndex, list.get(i));
			list.set(i, minValue);
		}
	}

	public static <E extends Comparable<E>> void mergeSort(ArrayList<E> list, int from, int to) {
		if (to - from <= 1) {
			return;
		}

		if (to - from == 2) {
			if (list.get(from).compareTo(list.get(to - 1)) > 0) {
				E swap = list.get(from);
				list.set(from, list.get(to - 1));
				list.set(to - 1, swap);
			}
			return;
		}

		int mid = (from + to) / 2;

		mergeSort(list, from, mid);
		mergeSort(list, mid, to);
		merge(list, from, mid, to);
	}

	private static <E extends Comparable<E>> void merge(ArrayList<E> list, int from, int mid, int to) {
		ArrayList<E> merged = new ArrayList<E>();

		int pin1 = from;
		int pin2 = mid;

		while (pin1 < mid && pin2 < to) {
			if (list.get(pin1).compareTo(list.get(pin2)) < 0) {
				merged.add(list.get(pin1));
				pin1++;
			} else {
				merged.add(list.get(pin2));
				pin2++;
			}
		}

		while (pin1 < mid) {
			merged.add(list.get(pin1));
			pin1++;
		}

		while (pin2 < to) {
			merged.add(list.get(pin2));
			pin2++;
		}

		for (int i = from; i < to; i++) {
			list.set(i, merged.get(i - from));
		}
	}

	public static <E extends Comparable<E>> void quickSort(ArrayList<E> list, int from, int to) { // sort the sequence [from; to)
		if (to - from < 2) {
			return;
		}
		
        int pivot = from;
        E pivotValue = list.get(pivot);
        
        int left = from + 1;
        int right = to - 1;
        
        while (left <= right) {
            while (left < to && list.get(left).compareTo(pivotValue) < 0) {
                left++;
            }

            while (right > from && list.get(right).compareTo(pivotValue) >= 0) {
                right--;
            }
            
            if (left < right) {
                Collections.swap(list, left, right);
            }
        }
        
        Collections.swap(list, pivot, left - 1);
        
        quickSort(list, from, right);
        quickSort(list, right + 1, to);
	}

	public static <E extends Comparable<E>> boolean binarySearch(ArrayList<E> list, int from, int to, E value) { // [from; to)
		if (to - from == 0) {
			return false;
		}

		if (to - from == 1) {
			return (list.get(from).equals(value));
		}

		E mid = list.get((from + to) / 2);

		if (value.equals(mid)) {
			return true;
		}

		if (value.compareTo(mid) < 0) {
			return binarySearch(list, from, (from + to) / 2, value);
		} else {
			return binarySearch(list, (from + to) / 2, to, value);
		}
	}
}
