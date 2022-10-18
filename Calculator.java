
public class Calculator{

	public static void main(String[] args) {
		System.out.println("Infix: a*b/(c-a)+d*e");
		System.out.println("Postfix: " + convertToPostfix("a*b/(c-a)+d*e"));

		System.out.print("Postfix evaluation: ");
		System.out.println(evaluatePostfix(convertToPostfix("a * b / (c-a) + d * e")));
		
	}


	/**
	 * Converts an infix expression to a postfix expression using a LinkedStack.
	 * Only takes in variables as operands
	 * @param infix infix expression
	 * @return postfix expression
	 */
	public static String convertToPostfix(String infix){
		char[] infixArray = infix.toCharArray();
		String postfix="";
		LinkedStack<Character> stack = new LinkedStack<>();
		for(int i=0; i<infixArray.length; i++){
			if (Character.isLetter(infixArray[i])){								//if it is operand, then just add to postfix
				postfix = postfix + infixArray[i];
			}
			else{																//if it is operator
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
							while(infixPriority <= stackPriority){					// is < instead of <= b/c accounts for priority from left to right of infix
								postfix = postfix + stack.peek();
								stack.pop();
								if(stack.isEmpty()){								//used to avoid EmptyStackException when calling stack.peek() later
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
					default:											//skips any entries in infix that isn't an operator or an operand
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

	/**
	 * Returns the priority level of an operator using PEMDAS.
	 * Does not account for difference in priority from left to right
	 * or right to left. This is accounted already in convertToPostfix().
	 * Higher priority level means higher precedence.
	 * @param operator the math operator
	 * @return priority level of the math operator
	 */
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



	/**
	 * Calculates an answer from a postfix expression, given that it only takes in variables a-e,
	 * or else it will through an IllegalArgumentException when calling convertCharToValue
	 * @param postfix postfix expression you want to calculate
	 * @return the final calculation of postfix
	 */
	public static double evaluatePostfix(String postfix){
		ResizeableArrayStack<Double> stack = new ResizeableArrayStack<>();
		char[] postfixArray = postfix.toCharArray();
		for(int i=0; i<postfixArray.length; i++){
			if(Character.isLetter(postfixArray[i])){						//if is operand just push it to the stack
				stack.push(convertCharToValue(postfixArray[i]));
			}
			else{															//if is an operator, pop two operands from the stack
				double rightOperand = stack.pop();							//and calculate the mini expression accordingly
				double leftOperand = stack.pop();							//then push that final calculation to the stack
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
		return stack.pop();												//returns the final value left over in the stack after all calculations are done
	}

	/**
	 * Converts some variable of a-e its corresponding double value.
	 * Throws IllegalArgumentException if the variable isn't a-e.
	 * @param entry a variable that is from a to e only
	 * @return the value that variable represents
	 */
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
				throw new IllegalArgumentException(entry +" does not have a value assigned to it.");
		}
		return result;
	}

}