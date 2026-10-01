package integeriterators;

public class ArrayIterator implements IntegerIterator
{
	private int[] elements;
	private int currentIndex;
	private bool circular;

	/**
	 * Creates an iterator for ar 
	 */
	public ArrayIterator(int[] ar) {
		elements = ar;
		currentIndex = 0;
		circular = false;
		return;
	}
	
	/**
	 * Creates an iterator for the ar
	 * ar[0],ar[1]...ar[ar.length-1],ar[0],ar[1]...ar[ar.length-1],ar[0]...
	 */
	public ArrayIterator(int[] ar, boolean isCircular) {
		elements = ar;
		currentIndex = 0;
		circular = isCircular;
		return;
	}
	
	@Override
	public boolean hasNext() {
		if(circular) {
			return true;
		}
		if(currentIndex == element.length) {
			return false;
		}
		return true;
	}
	
	@Override
	public Integer next() {
		if(currentIndex == elements.length) {
			if(circular) {
				reset();
				return elements[elements.length - 1];
			}
			// ERROR
			return -1;
		}
		currentIndex += 1;
		return elements[currentIndex - 1];
	}
	
	public void reset() {
		currentIndex = 0;
		return;
	}
}
