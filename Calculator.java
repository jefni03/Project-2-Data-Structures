import java.security.spec.RSAKeyGenParameterSpec;

public class Calculator{

	public static void main(String[] args) {
		
	}

	/*
	 * a * b / (c-a) + d * e
	 */

	/*
	 * if operand
	 * 	add to postfix
	 * if ^
	 * 		add operator to stack 		//accounts for ^ priority being from right to left
	 * if operator, and operator.priority > stack.top.priority, and not ^
	 * 		while(operator.priotiy > stack.top.priority)
	 * 			pop stack to postfix
	 * else
	 * 		add operator to stack
	 * if (
	 * 		add to stack
	 * if )
	 * 		pop from stack until reach ) and add to psotfix but odn't include ( or )
	 */

	 // !!!NOT DONE!!!
	public static String convertToPostfix(String infix){
		char[] infixArray = infix.toCharArray();
		String postfix="";
		ResizeableArrayStack<Character> stack = new ResizeableArrayStack<>();	//need To change to linkedlist, using resiz array since the methods are done
		for(int i=0; i<infixArray.length; i++){
			switch (infixArray[i]){
				case 'a','b','c','d','e':
					postfix = postfix + infixArray[i];
					break;
				case '^':
					stack.push(infixArray[i]);
					break;
				case '+','-','*','/':
					while (){

					}
				

			}
		}
		return postfix;
	}

	public static void evaluatePostfix(){
		//use resiz array
	}

}