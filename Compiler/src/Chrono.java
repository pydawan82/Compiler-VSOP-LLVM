
public class Chrono implements AutoCloseable {
	public final long start;
	
	public Chrono() {
		start = System.currentTimeMillis();
	}
	
	@Override
	public void close(){
		System.out.printf("Operation finished in %dms", System.currentTimeMillis()-start);
		System.out.println();
	}
	
}
