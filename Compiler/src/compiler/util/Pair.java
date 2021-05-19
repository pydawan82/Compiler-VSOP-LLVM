package compiler.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * A tuple class that can group two objects.
 */
public record Pair<A, B>(A first, B second) {

	/**
	 * Creates a {@link List} of {@link Pair} by grouping the elements of both {@link List}s.
	 * Elements are grouped in the same order as they appear in their parent list. 
	 * @param <A> - the first type
	 * @param <B> - the second type
	 * @param first - the first list
	 * @param second - the second list
	 * @return a {@link List} of {@link Pair}s from the given lists
	 * @throws IllegalArgumentException if the lists are not the same size.
	 */
	public static <A,B> List<Pair<A, B>> zip(List<A> first, List<B> second) {
		int size = first.size();
		if(size != second.size())
			throw new IllegalArgumentException("Lists must be of the same size");
		
		List<Pair<A, B>> pairs = new ArrayList<>(size);
		
		Iterator<A> it1 = first.iterator();
		Iterator<B> it2 = second.iterator();

		for(int i=0; i<size; i++)
			pairs.add(new Pair<A, B>(it1.next(), it2.next()));
		
		return pairs;
	}

	/**
	 * Creates a consumer of {@link Pair} from a {@link BiConsumer}
	 * @param biConsumer - the biconsumer
	 * @return the consumer
	 */
	public Consumer<Pair<A,B>> consumer(BiConsumer<A,B> biConsumer) {
		return pair -> biConsumer.accept(first(), second());
	}

	@Override
	public String toString() {
		return String.format("[%s, %s]", first, second);
	}
}
