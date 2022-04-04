package max.leetcode.concurrent;

// Java program to control the Main Thread

import java.util.concurrent.atomic.AtomicInteger;

// Main class extending thread class
public class Multithreading extends Thread {

	private final int count;

	Multithreading(int count) {
		this.count = count;
	}

	private Integer counter1 = 0;  // unsafe
	private volatile Integer counter2 = 0;
	private Integer counter3 = 0;  // syncronized
	private AtomicInteger counter4 = new AtomicInteger(0); // extends volatile

	public void run() {

		printout("Initial state", 0);

		// Getting reference to Main thread
		Thread t = Thread.currentThread();

		// Getting name of Main thread
		System.out.println("Current thread: " + t.getName());

		// Changing the name of Main thread
		t.setName("Geeks");
		System.out.println("After name change: " + t.getName());

		// Getting priority of Main thread
		System.out.println("SET Main thread priority: " + t.getPriority());

		// Setting priority of Main thread to MAX(10)
		t.setPriority(MIN_PRIORITY);

		// Print and display the main thread priority
		System.out.println("Main thread new priority: " + t.getPriority());

		for (int i = 0; i < 5; i++) {
			System.out.println("Main thread");
		}

		// Main thread creating child threads
		Thread ct1 = new ChildThread("Child 1", 1);
		Thread ct2 = new ChildThread("Child 2", 1);
		Thread ct3 = new ChildThread("Child 3", 2);
		Thread ct4 = new ChildThread("Child 4", 1);
		Thread ct5 = new ChildThread("Child 5", 1);

		// Setting priority of Main thread to MAX(10)
		ct3.setPriority(MAX_PRIORITY);

		// Starting child threads
		ct1.start();

		try {
			// Joining the current thread
			ct1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ct2.start();
		ct3.start();
		ct4.start();
		ct5.start();

		try {
			// Joining the current thread
			ct3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		printout("Final state", 0);
	}

	void increment3() {
		synchronized(this) {
			counter3++;
		}
	}

	void printout(String name, int index) {
		System.out.println(String.format("%s: (%s) -> %s, %s, %s, %s", name, index, counter1, counter2, counter3, counter4.get()));
	}

	// Helper class extending Thread class, Child Thread class
	class ChildThread extends Thread {

		final String name;
		final long sleep;

		ChildThread(String name, long sleep) {
			this.name = name;
			this.sleep = sleep;
		}

		// run() method of a thread
		public void run()
		{
			for (int i = 0; i < count; i++) {
				try {
					this.sleep(sleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				counter1++;
				counter2++;
				increment3();
				counter4.incrementAndGet();

				printout(name, i);
			}
		}
	}

	// Main driver method
	public static void main(String[] args)
	{
		Thread main = new Multithreading(200);
		main.setName("Main");
		main.start();
	}
}
