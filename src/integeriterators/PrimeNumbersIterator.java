package integeriterators;
import java.math.BigInteger;

public class PrimeNumbersIterator implements IntegerIterator {
	private BigInteger current;
	private BigInteger start;
	
	public PrimeNumbersIterator() {
		current = 0;
	}
	
	public PrimeNumbersIterator(int n) {
		BigInteger cast = BigInteger.valueOf(n);
		if (cast.isProbablePrime(100000)) {
			start = cast;
			current = start;
			return;
		}

		start = cast.nextProbablePrime();
		current = start;
	}
	
	@Override
	public boolean hasNext() {
		return true;
	}
	
	@Override
	public Integer next() {
		temp = current;
		current = current.nextProbablePrime();
		return temp;
	}
	
	public void reset() {
		current = start;
	}
}
