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
          
     }
}
