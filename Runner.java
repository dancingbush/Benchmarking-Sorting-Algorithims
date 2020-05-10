/**
 * 
 */
package ie.gmit.dip;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

/**
 * @author Ciaran Mooney 14/04/2020
 *
 *         This class will perform all the benchmarking on the 7 sorting
 *         algorithms.
 * 
 *         The following resources have been used to help build source code: 
 *         1.
 *         Article title: Java - Benchmarks various sorting algorithms Website
 *         title: Gist URL: https://gist.github.com/AnthonyFermin/10925101 
 *         2.
 *         Author Nataraja Gootooru Article title: Java sorting algorithms -
 *         Java sorting algorithm programs Website title: Java2novice.com URL:
 *         https://www.java2novice.com/java-sorting-algorithms/ 
 *         3. 
 *         Article title: Sorting Algorithms in Java Website title: Stack Abuse URL:
 *         https://stackabuse.com/sorting-algorithms-in-java/ 
 *         4. 
 *         Article title: Measuring code execution time - The wrong way - Java Developer
 *         Central Website title: Java Developer Central URL:
 *         https://javadevcentral.com/measuring-execution-time-the-wrong-way 
 *         5.
 *         Article title: Analysis of Algorithms Website title: Cs.cmu.edu URL:
 *         https://www.cs.cmu.edu/afs/cs/Web/People/pattis/15-1XX/15-200/lectures/aa/index.html
 *         6.
 *          Article title: Efficiently Merge Sorted Java Sequences | Baeldung
 *         Website title: Baeldung URL:
 *         https://www.baeldung.com/java-merge-sorted-sequences
 */


public class Runner {

	/**
	 * Class level variable producing random ints for Testing arrays. and defining
	 * output directories for benchmark 'csv' files.
	 */
	private static Random randomInteger = new Random();
	private final static String average10000 = "./averageBM1_500_10000.csv";
	private final static String average1000000 = "./averageBM1_10000_10000000.csv";
	// private static String avergaeCase_1Million = "./average_1Million.csv";
	private static String sorted10000 = "./sortedBM1_500_to_10000.csv";
	private static String sorted1000000 = "./sortedBM2_10000_1Million.csv";
	// private static String sorted_1Million = "./sorted_1Million.csv";
	private static String worseCase10000 = "./worstCase_500_10000.csv";
	private static String worseCase1000000 = "./worstCase_1000000.csv";
	// private static String worseCase_1Million = "./worsstCase_1Million.csv";

	public static void main(String[] args) throws IOException {
		/*
		 * Benchmark algorithms program entry point.
		 */

		long start = System.nanoTime();
		System.out.println("******BENCHMARKING ALGORTHIMS******\n");

		System.out.println("Avergae case randomised arrays of size 500-10000 benchmarking output to " + average10000);
		benchmarkAverageCase(avergaeCaseArray, average10000, 0, 10000, 500);
		System.out.println(
				"Avergae case randomised arrays of size 10000-100000 benchmarking output to " + average1000000);
		benchmarkAverageCase(avergaeCaseArray, average1000000, 0, 100000, 10000);
//		System.out.println(
//				"Avergae case randomised arrays of size 100k to 1 million benchmarking output to " + avergaeCase_1Million);
//		benchmarkAverageCase(avergaeCaseArray, avergaeCase_1Million, 0, 10000000, 1000000);

		System.out.println("Best case sorted arrays of size 500-10000 benchmarking output to " + sorted10000);
		benchmarkSorted(sortedArray, sorted10000, 0, 10000, 500);
		System.out.println("Best case sorted arrays benchmarking of array size 10k to 100k output to " + sorted1000000);
		benchmarkSorted(sortedArray, sorted1000000, 0, 100000, 10000);
		// System.out.println(
		// "Best case sorted arrays of size 1 Million to 10 million benchmarking output
		// to " + sorted_1Million);
		// benchmarkSorted(sortedArray, sorted_1Million, 0, 10000000, 1000000);

		System.out.println("Worst case reverse sorted array of size 500 to 10k, output to " + worseCase10000);
		benchmarkWorstCase(reverseSortedArray, worseCase10000, 0, 10000, 500);
		System.out.println("Worst case reverse sorted array of size 10k to 100k, output to " + worseCase1000000);
		benchmarkWorstCase(reverseSortedArray, worseCase1000000, 0, 100000, 10000);
		// System.out.println(
		// "Worst case sorted arrays of size 1 Million to 10 million benchmarking output
		// to " + worseCase_1Million);
		// benchmarkWorstCase(reverseSortedArray, worseCase_1Million, 0, 10000000,
		// 1000000);

		long totalTime = (System.nanoTime() - start) / 1000000 / 1000;
		System.out.println("\n\nTotal Program Execution time (seconds): " + totalTime);

	}

	/*
	 * Measure the time required to execute the algorithm in milliseconds.
	 * Functional interface that accepts the test array and passes it to the
	 * relevant sorting algorithm. Ensure we copy the arr so as not to
	 * pass sorted array to an algorithm.
	 */
	public static long timeBenchMark(Consumer<int[]> sortingFunction, int[] arr) {
		
		arr = copyArray(arr);
		final long startTime = System.nanoTime();
		sortingFunction.accept(arr);
		final long totalTime = (System.nanoTime() - startTime) / 1000000;

		return totalTime;

	}

	private static int[] copyArray(int[] arr) {
		/*
		 * Copy the array before it is passed to the sorting algorithm
		 */
		int[] copyArray = new int[arr.length];
		System.arraycopy(arr, 0, copyArray, 0, arr.length);
		return copyArray;

	}
	
	

	/**
	 * A functional interface to apply to Lambda equivalent generate data function
	 * The arrayDataGenertor type will construct a random, sorted or reverse sorted
	 * array depending on the benchmarking required. n is the size of the array to
	 * be built.
	 */
	@FunctionalInterface
	public interface ArrayDataGenerator {
		
		int[] generate(int n);
	}

	
	
	/**
	 * Takes an argument n defining the size of the array. Randomly assigns integers
	 * in the range 0 to 99 to the array producing a (n choose 2)/2 inversions, This
	 * gives us a demonstration of the average case.
	 */
	public static ArrayDataGenerator avergaeCaseArray = (n) -> {
		

		int[] random = new int[n];
		for (int i = 0; i < n; i++) {
			random[i] = randomInteger.nextInt(100);
			// random[i] = randomInteger.nextInt();
		}

		return random;

	};

	
	
	/**
	 * Takes n as array size and returns a sorted array for best case for 
	 * benchmarking all algorithms
	 */
	public static ArrayDataGenerator sortedArray = (n) -> {
		

		int[] sorted = new int[n];
		for (int i = 0; i < n; i++) {
			sorted[i] = i;
		}

		return sorted;
	};

	/**
	 * Returns an array sorted in reverse order of what would be sorted. Provides
	 * n/2 inversions for worst case time complexity benchmarking.
	 */
	public static ArrayDataGenerator reverseSortedArray = (n) -> {
		

		int[] reversed = new int[n];
		for (int x = 0; x < n; x++) {
			reversed[x] = -x;
		}
		return reversed;

	};

	
	/**
	 * Benchmarks all the sorting algorithms with randomized average case array.
	 * Each algorithm will be tested against 30 arrays in total varying form 500 to
	 * 10k and 10k to 100k with increments of 500 and 10k respectively. Takes the
	 * functional interface to generate the test array, a string to define the
	 * output csv file with results, a start and end index and a increment (fixed).
	 * First create a Buffered writer and output stream to handle the 'csv' file
	 * creation and output.
	 */
	public static void benchmarkAverageCase(ArrayDataGenerator testArray, String fileOutput, int start, int end,
			int increment) throws IOException {
		

		BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileOutput)));
		bf.write(
				"Size,Insertion Sort,Mege Sort,Quick Sort,Selection Sort,Bubble Sort, Bubble Optimal,Counting sort, Javas native sort\n");

		System.out.print("***Executing Avergae case benchmarking***\n\n");

		// Generate 20 / 10 average case random arrays, ranging form size 500 to 1k, and
		// 10k to 100k
		for (int i = start; i <= end; i += increment) {
			int[] arr = testArray.generate(i);
			System.out.println("Randomised average case array of size  " + arr.length);

			long insertionsort = timeBenchMark(InsertionSort::insertionSort, arr);
			long mergesort = timeBenchMark(MergeSort::mergeSort, arr);
			long quicksort = timeBenchMark(Quicksort::quickSort, arr);
			long selctionsort = timeBenchMark(SelectionSort::selectionSort, arr);
			long nativeJaveSort = timeBenchMark(Arrays::sort, arr);
			long bubbleSort = timeBenchMark(BubbleSort::bubbleSort, arr);
			long bubbleSortOptimal = timeBenchMark(BubbleSort::optimalBubbleSort, arr);

			// Counting sort we must first find the highest number in he array (k)
			int k = getHighestNUmber(arr);
			long countingSort = timeBenchMarkCountingSort(arr, k);

			System.out.println("Assigning K ~ " + k + " to counting sort.");

			// Get the average running time form 10 runs
			for (int x = 1; x < 10; x++) {
				System.out.println("Getting average of " + arr.length + " for average case run number : " + x + "/10.");
				insertionsort += timeBenchMark(InsertionSort::insertionSort, arr);
				mergesort += timeBenchMark(MergeSort::mergeSort, arr);
				quicksort += timeBenchMark(Quicksort::quickSort, arr);
				selctionsort += timeBenchMark(SelectionSort::selectionSort, arr);
				nativeJaveSort += timeBenchMark(Arrays::sort, arr);
				countingSort += timeBenchMarkCountingSort(arr, k);
				bubbleSort = timeBenchMark(BubbleSort::bubbleSort, arr);
				bubbleSortOptimal = timeBenchMark(BubbleSort::optimalBubbleSort, arr);

			}

			// Average of 10 runs
			insertionsort = (insertionsort / 10);
			mergesort = (mergesort / 10);
			quicksort = (quicksort / 10);
			selctionsort = (selctionsort / 10);
			nativeJaveSort = (nativeJaveSort / 10);
			countingSort = (countingSort / 10);
			bubbleSort = (bubbleSort / 10);
			bubbleSortOptimal = (bubbleSortOptimal / 10);

			System.out.println("\n\nWriting to average case randomised array file: \nSize- " + i + " Insertion- "
					+ insertionsort + ": Merge- " + mergesort + " : Quick sort- " + quicksort + " : Selection sort- "
					+ selctionsort + " : Java Arrays Sort - " + nativeJaveSort + " : Bubble sort - " + bubbleSort
					+ " : Optimal Bubblesort - " + bubbleSortOptimal + " : Counting : " + countingSort);
			bf.write(i + "," + insertionsort + "," + mergesort + "," + quicksort + "," + selctionsort + "," + bubbleSort
					+ "," + bubbleSortOptimal + "," + countingSort + "," + nativeJaveSort + "\n");

		}

		bf.close();
		System.out.println("***Average case benchmarking successfully executed***\n\n");

	}

	
	/**
	 * We must handle counting sort separately in order to assign K, the highest
	 * value in the array, and send it to the count sort algorithm.
	 */
	private static long timeBenchMarkCountingSort(int[] arr, int k) {
		
		arr = copyArray(arr);
		final long startTime = System.nanoTime();
		CountingSort.countSort(arr, k);
		
		//test the optimized in place derivative 
		//CountingSort.counSortInPlace(arr, k);
		
		final long totalTime = (System.nanoTime() - startTime) / 1000000;
		
		//Time in nanoseconds
		//final long totalTime = (System.nanoTime() - startTime);
		return totalTime;
	}

	
	/**
	 * Find the maximum value in our list and assign to k for counting sort
	 * integer.MINVALE will return -2~ if arr is empty
	 */
	private static int getHighestNUmber(int[] arr) {
		

		if (arr.length == 0) {
			return 0;
		}
		int m = Integer.MIN_VALUE;
		for (int i : arr) {
			m = Math.max(i, m);
		}
		return m;
	}

	/**
	 * Test Merge and Quick sort algorithms with O(nlog n) average time with sorted
	 * arrays. Including other algorithms for discussion.
	 * We need to use optimisedAquickSort to handle stackOverFlow
	 */
	public static void benchmarkSorted(ArrayDataGenerator testDataGenerator, String output, int start, int end,
			int increment) throws IOException {
		

		BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output)));
		bf.write(
				"Size,Insertion Sort,Mege Sort,OPtimal Quick Sort,Selection Sort,Counting Sort,Javas native sort,Bubble Sort, Bubble Optimal\n");

		System.out.println("***Executing Sorted Array Benchmarking for O(n log n) algorithms***");

		for (int i = start; i <= end; i += increment) {
			int[] arr = testDataGenerator.generate(i);
			System.out.println("Building sorted array of size " + arr.length);
			long insertionsort = timeBenchMark(InsertionSort::insertionSort, arr);
			long mergesort = timeBenchMark(MergeSort::mergeSort, arr);
			long quicksort = timeBenchMark(Quicksort::optimalQuickSort, arr);
			long selctionsort = timeBenchMark(SelectionSort::selectionSort, arr);
			long nativeJaveSort = timeBenchMark(Arrays::sort, arr);

			// Counting sort we must first find the highest number in he array (k)
			int k = getHighestNUmber(arr);
			long countingSort = timeBenchMarkCountingSort(arr, k);
			System.out.println("Assigning K ~ " + k + " to counting sort.");

			long bubbleSort = timeBenchMark(BubbleSort::bubbleSort, arr);
			long bubbleSortOptimal = timeBenchMark(BubbleSort::optimalBubbleSort, arr);

			// Get the average running time form 10 runs
			for (int x = 1; x < 10; x++) {
				System.out.println(
						"Getting average of " + arr.length + " for best sorted  case run number : " + x + "/10.");
				insertionsort += timeBenchMark(InsertionSort::insertionSort, arr);
				mergesort += timeBenchMark(MergeSort::mergeSort, arr);
				quicksort += timeBenchMark(Quicksort::optimalQuickSort, arr);
				selctionsort += timeBenchMark(SelectionSort::selectionSort, arr);
				nativeJaveSort += timeBenchMark(Arrays::sort, arr);
				countingSort += timeBenchMarkCountingSort(arr, k);
				bubbleSort = timeBenchMark(BubbleSort::bubbleSort, arr);
				bubbleSortOptimal = timeBenchMark(BubbleSort::optimalBubbleSort, arr);

			}

			// Average of 10 runs
			insertionsort = (insertionsort / 10);
			mergesort = (mergesort / 10);
			quicksort = (quicksort / 10);
			selctionsort = (selctionsort / 10);
			nativeJaveSort = (nativeJaveSort / 10);
			countingSort = (countingSort / 10);
			bubbleSort = (bubbleSort / 10);
			bubbleSortOptimal = (bubbleSortOptimal / 10);

			System.out.println("\n\nWriting to best case sorted array file: \nSize- " + i + " Insertion- "
					+ insertionsort + ": Merge- " + mergesort + " : OPtQuick sort- " + quicksort + " : Selection sort- "
					+ selctionsort + " : Counting - " + countingSort + " : Java Arrays Sort - " + nativeJaveSort
					+ " : Bubble sort - " + bubbleSort + " : Optimal Bubblesort - " + bubbleSortOptimal);
			bf.write(i + "," + insertionsort + "," + mergesort + "," + quicksort + "," + selctionsort + ","
					+ countingSort + "," + nativeJaveSort + "," + bubbleSort + "," + "," + bubbleSortOptimal + "\n");

		}
		bf.close();
		System.out.println("***Sorted benchmark successfully executed***\n\n");
	}

	
	/**
	 * Test algorithms with worst case O(n log n) worst time by passing arrays
	 * sorted in reverse. Include all algorithms for comparison.
	 * use optimisedQuicksort and Modified Countsort for handling
	 * negative integers.
	 */
	public static void benchmarkWorstCase(ArrayDataGenerator arrayGenerator, String output, int start, int end,
			int increment) throws IOException {
		
		BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output)));
		bf.write(
				"Size,Insertion Sort,Mege Sort,OPtimal Quick Sort,Selection Sort,Modfied Counting Sort,Javas native sort,Bubble Sort, Bubble Optimal\n");

		System.out
				.println("\n\n***Executing Reverse Sorted Worst acse Array Benchmarking for O(n log n) algorithms***");

		for (int i = start; i <= end; i += increment) {

			int[] arr = arrayGenerator.generate(i);
			System.out.println("Building reverse sorted array of size " + arr.length);
			long insertionsort = timeBenchMark(InsertionSort::insertionSort, arr);
			long mergesort = timeBenchMark(MergeSort::mergeSort, arr);
			long quicksort = timeBenchMark(Quicksort::optimalQuickSort, arr);
			long selctionsort = timeBenchMark(SelectionSort::selectionSort, arr);
			long nativeJaveSort = timeBenchMark(Arrays::sort, arr);

			// We must use a modified counting sort to handle negative numbers
			int k = getHighestNUmber(arr);
			long countingSort = timeBenchMark_NegativeCounting(arr, k);
			System.out.println("Assigning K ~ " + k + " to counting sort.");

			long bubbleSort = timeBenchMark(BubbleSort::bubbleSort, arr);
			long bubbleSortOptimal = timeBenchMark(BubbleSort::optimalBubbleSort, arr);

			// Get the average running time form 10 runs
			for (int x = 1; x < 10; x++) {
				System.out.println(
						"Getting average of " + arr.length + " for worst case case run number : " + x + "/10.");
				insertionsort += timeBenchMark(InsertionSort::insertionSort, arr);
				mergesort += timeBenchMark(MergeSort::mergeSort, arr);
				quicksort += timeBenchMark(Quicksort::optimalQuickSort, arr);
				selctionsort += timeBenchMark(SelectionSort::selectionSort, arr);
				nativeJaveSort += timeBenchMark(Arrays::sort, arr);
				countingSort += timeBenchMark_NegativeCounting(arr, k);
				bubbleSort = timeBenchMark(BubbleSort::bubbleSort, arr);
				bubbleSortOptimal = timeBenchMark(BubbleSort::optimalBubbleSort, arr);

			}

			// Average of 10 runs
			insertionsort = (insertionsort / 10);
			mergesort = (mergesort / 10);
			quicksort = (quicksort / 10);
			selctionsort = (selctionsort / 10);
			nativeJaveSort = (nativeJaveSort / 10);
			countingSort = (countingSort / 10);
			bubbleSort = (bubbleSort / 10);
			bubbleSortOptimal = (bubbleSortOptimal / 10);

			System.out.println("\n\nWriting to worst reverse sort case randomised array file: \nSize- " + i
					+ " Insertion- " + insertionsort + ": Merge- " + mergesort + " : OPtQuick sort- " + quicksort
					+ " : Selection sort- " + selctionsort + " : Counting - " + countingSort + " : Java Arrays Sort - "
					+ nativeJaveSort + " : Bubble sort - " + bubbleSort + " : Optimal Bubblesort - "
					+ bubbleSortOptimal);
			bf.write(i + "," + insertionsort + "," + mergesort + "," + quicksort + "," + selctionsort + ","
					+ countingSort + "," + nativeJaveSort + "," + bubbleSort + "," + "," + bubbleSortOptimal + "\n");
		}
		bf.close();
		System.out.println("\n\n***Reverse Sorted worst case benchmark successfully executed***\n\n");
	}
	
	
	/**
	 * Use a modified method of counting sort to handle negative integers -n to K
	 */
	private static long timeBenchMark_NegativeCounting(int[] arr, int k) {
		
		arr = copyArray(arr);
		final long startTime = System.nanoTime();
		CountingSort.negativeCountingSort(arr);
		final long totalTime = (System.nanoTime() - startTime) / 1000000;
		// final long totalTime = (System.nanoTime() - startTime);
		return totalTime;

	}

}
