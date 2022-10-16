import java.util.Arrays;
import java.util.EmptyStackException;

public class ResizeableArrayStack<T> implements StackInterface<T>{
	private T[] stack;
	private int topIndex;
	private boolean integrityOK=false;
	private static final int DEFAULT_CAPACITY=10;
	private static final int MAX_CAPACITY=20000;

	/**
	 * Constructor that makes a resizeable array stack
	 * with default capacity of 10
	 */
	public ResizeableArrayStack(){
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Constructor that makes a resizeable array stack
	 * with a specified capacity
	 * @param initialCapacity capacity of resizeable array stack you want to make
	 */
	public ResizeableArrayStack(int initialCapacity){
		integrityOK = false;
		checkCapacity(initialCapacity);

		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[initialCapacity];
		stack = temp;
		topIndex = -1;
		integrityOK = true;
	}

	/**
	 * Checks if the capacity of the stack array is larger than the maximum allowed capacity.
	 * If it is larger, then it throws an error.
	 * @param initialCapacity capacity of the stack array
	 */
	public void checkCapacity(int initialCapacity){
		if (initialCapacity > MAX_CAPACITY){
			throw new IllegalStateException("Trying to make resizeable array stack with a capacity larger than the allowed max");
		}
	}

	/**
	 * Makes sure there is space in the stack array, if there isn't
	 * then double the size of the array.
	 * Uses checkCapacity() to then make sure new array size isn't larger than the maximum allowed.
	 */
	public void ensureCapacity(){
		int capacity = stack.length-1;
		if (topIndex>=capacity){
			int newCapacity = 2*capacity;
			checkCapacity(newCapacity);
			stack = Arrays.copyOf(stack, newCapacity+1);
		}
		
	}

	/**
	 * Makes sure the ResizeableArrayStack object is initialized completely or else it throws an error
	 */
	public void checkIntegrity(){
		if (integrityOK == false){
			throw new SecurityException("ResizeableArrayObject is corrupt");
		}
	}

	/**
	 * Adds an entry to the top of the stack array.
	 * Checks the integrity of object and if there is space to add beforehand.
	 * @param newEntry entry to add to top of stack
	 */
	public void push(T newEntry){
		checkIntegrity();
		ensureCapacity();
		stack[topIndex+1] = newEntry;
		topIndex++;
	}

	/**
	 * Removes the top entry of the stack and returns it.
	 * @return the removed entry from stack
	 */
	public T pop(){
		checkIntegrity();
		if (isEmpty()){
			throw new EmptyStackException();
		}
		else{
			T top = stack[topIndex];
			stack[topIndex] = null;
			topIndex--;
			return top;
		}
	}

	/**
	 * @return top entry of the stack
	 */
	public T peek(){
		checkIntegrity();
		if (isEmpty()){
			throw new EmptyStackException();
		}
		else{
			return stack[topIndex];
		}
	}

	/**
	 * @return if stack array has no entries
	 */
	public boolean isEmpty(){
		return topIndex < 0;
	}

	/**
	 * Sets all indexes in stack array to null
	 */
	public void clear(){
		checkIntegrity();
		while (topIndex > -1){
			stack[topIndex] = null;
			topIndex--;
		}
	}
}
