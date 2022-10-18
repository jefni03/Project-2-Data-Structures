
public class Calculator{

	public static void main(String[] args) {
		System.out.println(evaluatePostfix(convertToPostfix("a * b / (c-a) + d * e")));
		
	}


	//		!!!NEED TO CHANGE STACK OBJECT TO ONE OF LINKEDCLASS, USED RESIZARRAY AS TEST!!!
	public static String convertToPostfix(String infix){
		char[] infixArray = infix.toCharArray();
		String postfix="";
		ResizeableArrayStack<Character> stack = new ResizeableArrayStack<>();
		for(int i=0; i<infixArray.length; i++){
			if (Character.isLetter(infixArray[i])){								//if it is operand, then just add to postfix
				postfix = postfix + infixArray[i];
			}
			else{
				switch (infixArray[i]){
					case '^':														//separate from other operators because priority of '^' is from right to left 
						stack.push(infixArray[i]);									//and b/c infix read from left to right
						break;														//so multiple '^' can be next to each other in the stack b/c it doesn't violate priority
					case '+','-','*','/':
						if (stack.isEmpty()){										//if the stack is empty, no need to check priority of operator in stack, so just push onto stack
							stack.push(infixArray[i]);
						}
						else{														//makes sure that the infix operator only pops to stack if its priority is higher than
							int infixPriority = getPriority(infixArray[i]);			//the priority of the operator(s) already in the stack
							int stackPriority = getPriority(stack.peek());			//if it isn't, then need to pop stack until no more priority conflict, or until stack is empty
							while(infixPriority <= stackPriority){
								postfix = postfix + stack.peek();
								stack.pop();
								if(stack.isEmpty()){
									break;
								}
								stackPriority = getPriority(stack.peek());
							}
							stack.push(infixArray[i]);
						}
						break;
					case '(':
						stack.push(infixArray[i]);
						break;
					case ')':										
						while (!stack.isEmpty()){						//goes through stack until it is empty or until it reaches '('
							if(stack.peek().equals('(')){			//and pops the elements to postfix
								break;
							}
							else{
								postfix = postfix + stack.peek();
								stack.pop();
							}
						}
						stack.pop();									//a final pop to remove '(' from the stack
						break;
					default:											//skips any entries in infix that isn't an operator or an operand (a letter)
						break;
				}
			}
		}
		while(!stack.isEmpty()){										//once the entire process of reading infix is done,
			postfix = postfix + stack.peek();							//pop any remaining operators in stack to the postfix
			stack.pop();
		}
		return postfix;
	}

	public static int getPriority(Character operator){
		int priority = -1;
		switch (operator){
			case '+':
				priority = 1;
				break;
			case '-':
				priority = 1;
				break;
			case '*':
				priority = 2;
				break;
			case '/':
				priority = 2;
				break;
			case '^':
				priority = 3;
				break;
		}
		return priority;
	}

	public static double evaluatePostfix(String postfix){
		ResizeableArrayStack<Double> stack = new ResizeableArrayStack<>();
		char[] postfixArray = postfix.toCharArray();
		for(int i=0; i<postfixArray.length; i++){
			if(Character.isLetter(postfixArray[i])){
				stack.push(convertCharToValue(postfixArray[i]));
			}
			else{
				double rightOperand = stack.pop();
				double leftOperand = stack.pop();
				double result = 0;
				switch (postfixArray[i]){
					case '+':
						result = leftOperand + rightOperand;
						stack.push(result);
						break;
					case '-':
						result = leftOperand - rightOperand;
						stack.push(result);
						break;
					case '*':
						result = leftOperand * rightOperand;
						stack.push(result);
						break;
					case '/':
						result = leftOperand / rightOperand;
						stack.push(result);
						break;
					case '^':
						result = Math.pow(leftOperand,rightOperand);
						stack.push(result);
						break;
				}
			}
		}
		return stack.pop();
	}

	public static double convertCharToValue(Character entry){
		double result=-1;
		switch (entry){
			case 'a':
				result = 2;
				break;
			case 'b':
				result = 3;
				break;
			case 'c':
				result = 4;
				break;
			case 'd':
				result = 5;
				break;
			case 'e':
				result = 6;
				break;
			default:
				throw new IllegalArgumentException("cannot convert " + entry + " to a double value");
				break;
		}
		return result;
	}

}