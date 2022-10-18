import java.util.EmptyStackException;
import java.util.*;

public class LinkedStack<T> implements StackInterface<T>
{
     private Node topNode;

     public LinkedStack()
     {
          topNode = null;
     }

     private class Node
     {
          private T data;
          private Node next;

          private Node getNextNode()
          {
               return next;
          }

          private void setNextNode(Node next)
          {
               this.next=next;
          }

          private T getData(){
               return data;
           }

          private void setData(T data)
          {
               this.data=data;
          }
           
   
   

     }

     /**
	 * Adds an entry to the top of the stack array.
	 * Checks the integrity of object and if there is space to add beforehand.
	 * @param newEntry entry to add to top of stack
	 */
	public void push(T newEntry)
     {
		Node newNode = new Node(newEntry, topNode);
          topNode = newNode;
	}

	/**
	 * Removes the top entry of the stack and returns it.
	 * @return the removed entry from stack
	 */
	public T pop()
     {
          T top = peek();
          topNode = topNode.getNextNode();

          return top;
	}

	/**
	 * @return top entry of the stack
	 */
	public T peek()
     {
		if(isEmpty())
          {
               throw new EmptyStackException();
               
          }
          else
          {
               return topNode.getData();
          }
	}

	/**
	 * @return if stack array has no entries
	 */
	public boolean isEmpty()
     {
		return topNode == null;
	}

	/**
	 * Sets all indexes in stack array to null
	 */
	public void clear()
     {
          topNode = null;
	}
}
