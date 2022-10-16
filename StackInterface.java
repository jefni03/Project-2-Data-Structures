public interface StackInterface<T>{

	/**
	 * Adds a new entry to the top of the stack.
	 * @param newEntry
	 */
	public void push(T newEntry);

	/**
	 * Removes an entry from the top of the stack and returns it.
	 * @return entry from the top of stack
	 */
	public T pop();

	/**
	 * @return entry from the top of stack
	 */
	public T peek();

	/**
	 * @return if stack is empty
	 */
	public boolean isEmpty();

	/**
	 * Removes all entries from the stack.
	 */
	public void clear();
}
