package compiler.util;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Chrono implements AutoCloseable {
	public final long start;
	private final BiConsumer<Long, Long> consumer;

	public Chrono() {
		this((start, end) -> 
				System.out.printf("Operation finished in %dms"+System.lineSeparator(), end - start));
	}

	public Chrono(Consumer<Long> consumer) {
		this((start, end) -> consumer.accept(end-start));
	}

	public Chrono(BiConsumer<Long, Long> consumer) {
		start = System.currentTimeMillis();
		this.consumer = consumer;
	}

	@Override
	public void close() {
		consumer.accept(start, System.currentTimeMillis());
	}

}
