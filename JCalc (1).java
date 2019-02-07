/* ================================
 * Student: Max Brodeur-Urbas
 * Student ID: 260806240
 * Date: Tuesday November 7th 2017
 * ===============================
 */

import java.util.Scanner;
import java.util.StringTokenizer;

public class JCalc {

	//BONUS: program works with all cases of parentheses and unary + and -.

	public static int Precedence (String value){ //used to assign a precedence value to strings that match ()+-x/

		if (value.equals("("))					//precedence values () < +- < x / < t
			return 0;

		if (value.equals(")"))
			return 0;

		if (value.equals("+"))
			return 1;

		if (value.equals("-"))
			return 1;

		if (value.equals("x")) 							
			return 2;

		if (value.equals("/"))
			return 2;

		if (value.equals("t"))
			return 3;

		System.out.println("Error in Precedence"); 		// if Precedence is called by the string matches none of the operators an error message is printed and it returns 0
		return 0;
	}

	public static String main (String args) {

		
		args = args.replace(" ","");  					//removing all the spaces from the input to remove error

		int a;
		
		StringBuilder unary = new StringBuilder(args);  //converting our input string to StringBuilder because strings are immutable.

		if (unary.charAt(0) == '+')						//if the first character is a unary + delete it
			unary.deleteCharAt(0);

		if (unary.charAt(0) == '-')						//if the first character is a unary - replace it with temp varaible t.
			unary.setCharAt(0, 't');

		for(a = 0; a < unary.length(); a++){			//iterating through each character in the string.
			char val = unary.charAt(a);

			if (val == '(')								//if the character is a (, check if there is a unary + to the right and delete it.
				if (unary.charAt(a+1) == '+')
					unary.deleteCharAt( a+1);

			if (val == '(')
				if (unary.charAt(a+1) == '-')			//if the character is a (, check if there is a unary - to the right and replace it with t.
					unary.setCharAt( a+1, 't');

			if (val == '+' || val == '/' || val == 'x' || val == '-')		// if a - comes after any operator its unary so replace it with t.
				if (unary.charAt(a+1) == '-')
					unary.setCharAt( a+1, 't');

		}				

		String a1 = unary.toString();

		StringTokenizer tokenizer = new StringTokenizer(a1,"+-x/()t",true); 	//breaking input up into pieces by splitting at +-*/()

		Queue input_Queue = new Queue();				 //creating input Queue to hold input values.
		Stack Op_stack = new Stack (); 					//creating stack to hold operators
		Queue Out_Queue = new Queue (); 				// Creating output queue

		while (tokenizer.hasMoreTokens()) {  			//while their is more input
			input_Queue.Enqueue(tokenizer.nextToken()); //Filling our input queue with tokens
		}

		while (input_Queue.front != null) { 			//while input queue is not empty

			String token = input_Queue.Dequeue(); 		//makes the token = the first value in the queue, as while loop executes it will continue popping off values the input will be iterated through.

			if ( token.equals("x") || token.equals ("/") || token.equals("+") || token.equals("-") || token.equals("(") || token.equals(")") || token.equals("t")) //if the token during this iteration is an operator or bracket.
			{				
				if (Op_stack.top == null){ 				// if the operator stack is empty, push token in
					Op_stack.push(token);
					continue; 							//return to the outer if statement
				}

				else if  (token.equals("(")){ 			// if the token is a left parentheses, push it into the operator stack
					Op_stack.push(token);

				}

				else if (token.equals(")")){ 			 // if the token is a RIGHT parentheses, pop items from operator stack into output stack until left parentheses is reached.
					while(!	Op_stack.top.data.equals("("))
						Out_Queue.Enqueue(Op_stack.pop()); //moving operators from stack to output queue

					Op_stack.pop(); 					//deleting the remaining left parentheses from the operator stack.
				}

				else{ 									//if the token is not a parentheses and the top of the stack isn't null this executes. 

					while(Op_stack.top!=null && Precedence (Op_stack.top.data) >= Precedence(token)) //while stack not empty and precedence of the top is greater than or equal to token precedence
						Out_Queue.Enqueue(Op_stack.pop()); 	//pop items off top of Operator stack and enqueue them on output queue

					Op_stack.push(token);				 //push token onto stack once no operators of greater precedence remain in the stack.
				}
			}

			else										 //if token is not one of the listed operators
				Out_Queue.Enqueue(token);				 //Enqueue the token to the final output queue. All numbers should make it to this point
		}

		while (Op_stack.top != null){ 					//move all remaining operators from stack to output queue
			Out_Queue.Enqueue(Op_stack.pop());
		}

		Stack Ans_Stack = new Stack();					// creating a stack that will be used to hold values as the operators are being applied.
		while (Out_Queue.front != null){				//while there are move values in our output queue.

			String popper = Out_Queue.Dequeue();		//in each iteration we pop another value off the output queue

			if (!popper.equals("x") && !popper.equals ("/") && !popper.equals("+") && !popper.equals("-") && !popper.equals("t")) {		
				Ans_Stack.push(popper);					//if popped value isn't an operator or temp variable push it to answer stack to be operated upon later.
				continue;								//go back to beginning of while loop to get new popped value.
			}
			if (popper.equals("t")){					// if  popped value is temporary unary - variable, pop  off answer stack, negate it and push it back in.
				double num = Double.parseDouble(Ans_Stack.pop());
				num = num*(-1);
				Ans_Stack.push(Double.toString(num));
				continue;
			}
			double val1 = Double.parseDouble(Ans_Stack.pop());	//pop two values off answer stack for operators to act upon.
			double val2 = Double.parseDouble(Ans_Stack.pop());

			if (popper.equals("+")){					//implementing + operator on val1 and val2 and pushing the answer back into stack
				double val3 = val2 + val1;
				Ans_Stack.push(Double.toString(val3));
				continue;
			}
			if (popper.equals("-")){					//implementing - operator on val1 and val2 and pushing the answer back into stack
				double val3 = val2 - val1;
				Ans_Stack.push(Double.toString(val3));
				continue;
			}
			if (popper.equals("x")){					//implementing * operator on val1 and val2 and pushing the answer back into stack
				double val3 = val2 * val1;
				Ans_Stack.push(Double.toString(val3));
				continue;
			}
			if (popper.equals("/")){					//implementing / operator on val1 and val2 and pushing the answer back into stack
				double val3 = val2 / val1;
				Ans_Stack.push(Double.toString(val3));
				continue;
			}
			
			

		}
		
		String FINAL = Ans_Stack.pop() + "";
		return FINAL;

	}

}


