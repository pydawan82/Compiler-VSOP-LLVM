package compiler.util;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * A class that is useful to measure time of a given action.
 * This class is {@link AutoCloseable}. This means it should be used
 * in a try block.
 * Time stamps are generated using {@link System#currentTimeMillis}
 */
public class Chrono implements AutoCloseable {
	private final long start;
	private long end = -1L;
	private boolean closed = false;

	private final BiConsumer<Long, Long> consumer;

	/**
	 * Creates a new {@link Chrono} that will print the duration of the underlying operations
	 * when closed.
	 */
	public Chrono() {
		this((start, end) -> 
				System.out.printf("Operation finished in %dms"+System.lineSeparator(), end - start));
	}

	/**
	 * Creates a new {@link Chrono} that will execute the given action when closed.
	 * The consumer fed with the duration this {@link Chrono} was open.
	 * @param consumer - The consumer that will be called when this object is closed.
	 */
	public Chrono(Consumer<Long> consumer) {
		this((start, end) -> consumer.accept(end-start));
	}

	/**
	 * Creates a new {@link Chrono} that will execute the given action when closed.
	 * The consumer is fed with the start and end which correspond to the open and close time
	 * of this object.
	 * @param consumer - The consumer that will be called when this object is closed.
	 */
	public Chrono(BiConsumer<Long, Long> consumer) {
		start = System.currentTimeMillis();
		this.consumer = consumer;
	}

	/**
	 * 
	 * @return the time stamp when this object was opened.
	 */
	public long start() {
		return start;
	}

	/**
	 * 
	 * @return the time stamp when this object was closed. If this object
	 * has not been closed yet, it returns -1L otherwise.
	 */
	public long end() {
		return end;
	}

	/**
	 * Stops this chrono and execute the predefined action.
	 */
	@Override
	public void close() {
		if(closed)
			return;
		
		end = System.currentTimeMillis();
		consumer.accept(start, end);
	}

}
