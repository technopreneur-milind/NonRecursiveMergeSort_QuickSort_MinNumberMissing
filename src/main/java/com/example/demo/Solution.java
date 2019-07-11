package com.example.demo;

import java.util.Stack;


class Solution {
	public int solution(int[] A) {
		if (A == null || A.length <= 0)
			return 1;
		int result = 1;
		if (A.length > 1) {
			//mergeSort(A, A.length);
			quickSort(A,A.length);
		}
		for (int i : A) {
			if (i > result) {
				break;
			}
			if (i == result)
				result++;
		}
		return result;

	}
	
	void quickSort(int arr[], int n) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(0);
		stack.push(n-1);
		while(!stack.isEmpty())
		{
			int end = stack.pop();
			int start = stack.pop();
			if(end -start <= 1)
				continue;
			int pivot = start + ((end -start)/2);
			pivot = partition(arr,pivot, start, end);
			stack.push(pivot+1);
			stack.push(end);
			stack.push(start);
			stack.push(pivot);
			
			
		}
	}

	private int partition(int[] arr, int pivot, int start, int end) {
		int left = start;
		int right = end -2;
		int pivotElement = arr[pivot];
		swap(arr,pivot,end-1);
		while(left < right)
		{
			if(arr[left] <pivotElement)
				left++;
			else if (arr[right] >= pivotElement)
			{
				right--;
			}
			else
			{
				swap(arr,left, right);
			}
		}
		int index = right;
		if(arr[right] < pivotElement)
			index++;
		swap(arr,end-1,index);
		return index; 
	}

	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i]= arr[j];
		arr[j] = temp;
		
	}

	void mergeSort(int arr[], int n) {

		int currentSize;
		int left;
		for (currentSize = 1; currentSize <= n - 1; currentSize = 2 * currentSize) {

			for (left = 0; left < n - 1; left += 2 * currentSize) {
				int mid = left + currentSize - 1;

				int right = Math.min(left + 2 * currentSize - 1, n - 1);
				merge(arr, left, mid, right);
			}
		}
	}

	void merge(int array[], int left, int mid, int right) {
		int leftBlockIndex, rightBlockIndex, arrayIndex;
		int leftBlockSize = mid - left + 1;
		int rightBlockSize = right - mid;

		if (leftBlockSize > 0 && rightBlockSize > 0) {

			/* create temp arrays */
			int leftBlock[] = new int[leftBlockSize];
			int rightBlock[] = new int[rightBlockSize];

			/*
			 * Copy data to temp arrays L[] and R[]
			 */
			for (leftBlockIndex = 0; leftBlockIndex < leftBlockSize; leftBlockIndex++)
				leftBlock[leftBlockIndex] = array[left + leftBlockIndex];
			for (rightBlockIndex = 0; rightBlockIndex < rightBlockSize; rightBlockIndex++)
				rightBlock[rightBlockIndex] = array[mid + 1 + rightBlockIndex];

			/*
			 * Merge the temp arrays back into arr[l..r]
			 */
			leftBlockIndex = 0;
			rightBlockIndex = 0;
			arrayIndex = left;
			while (leftBlockIndex < leftBlockSize && rightBlockIndex < rightBlockSize) {
				if (leftBlock[leftBlockIndex] <= rightBlock[rightBlockIndex]) {
					array[arrayIndex] = leftBlock[leftBlockIndex];
					leftBlockIndex++;
				} else {
					array[arrayIndex] = rightBlock[rightBlockIndex];
					rightBlockIndex++;
				}
				arrayIndex++;
			}
			while (leftBlockIndex < leftBlockSize) {
				array[arrayIndex] = leftBlock[leftBlockIndex];
				leftBlockIndex++;
				arrayIndex++;
			}
			while (rightBlockIndex < rightBlockSize) {
				array[arrayIndex] = rightBlock[rightBlockIndex];
				rightBlockIndex++;
				arrayIndex++;
			}
		}
	}
}
