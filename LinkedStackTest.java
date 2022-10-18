public class LinkedStackTest 
{
public static void main (String[] args) throws Exception
    {
    String infix = "a*b/(c-a)+d*e";
    String postfix = convertToPostfix(infix);
    System.out.println ("Postfix expression: " + postfix);
    }
}
