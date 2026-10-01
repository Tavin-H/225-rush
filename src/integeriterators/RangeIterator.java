package integeriterators;

public class RangeIterator implements IntegerIterator
{
	private int start;
	private int end;
	private int current;

	/**
	 * Creates an iterator for the infinite sequence 0,1,2,...
	 */
	public RangeIterator() {
		start = 0;
		end = -1;
	}
	
	/**
	 * Creates an iterator for the infinite sequence s,s+1,s+2...
	 */
	public RangeIterator(int s) {
		start = s;
		end = -1;
	}
	
	/**
	 * Creates an iterator for the finite sequence [s,s+1,s+2...t-1]
	 * @throws IllegalArgumentException if t<s
	 */
	public RangeIterator(int s, int t) {
		start = s;
		end = t;
	}
	
	@Override
	public boolean hasNext() {
		if(current == end) {
			return false;
		}
		return true;
	}
	
	@Override
	public Integer next() {
		current += 1;
		return current - 1;
	}
	
	public void reset() {
		current = start;
		return;
	}
}
