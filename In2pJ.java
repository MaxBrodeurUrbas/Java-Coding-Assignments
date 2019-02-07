/* ================================
 * Student: Max Brodeur-Urbas
 * Student ID: 260806240
 * Date: Tuesday November 7th 2017
 * ===============================
 */

import java.util.Scanner;
import java.util.StringTokenizer;

public class In2pJ {
	
	//BONUS: program works with all cases of parentheses.
	
	public static int Precedence (String value){ //used to assign a precedence value to strings that match ()+-*/

		if (value.equals("("))					//precedence values () < +- < */
			return 0;
		
		if (value.equals(")"))
			return 0;
		
		if (value.equals("+"))
			return 1;
		
		if (value.equals("-"))
			return 1;
		
		if (value.equals("*")) 							
			return 2;
		
		if (value.equals("/"))
			return 2;
		
		System.out.println("Error in Precedence"); 		// if Precedence is called by the string matches none of the operators an error message is printed and it returns 0
		return 0;
	}

	public static void main (String[] args) {

		Scanner reader = new Scanner (System.in); 		//creating a scanner that scans the user input
		String line;
		
		System.out.print("Enter string: "); 			//prompting the user
		
		line = reader.nextLine();   					//making the line variable hold the user input
		line = line.replace(" ","");  					//removing all the spaces from the input to remove error
		
		StringTokenizer tokenizer = new StringTokenizer(line,"+-*/()",true); 	//breaking input up into pieces by splitting at +-*/()
		
		Queue input_Queue = new Queue();				 //creating input Queue to hold input values.
		Stack Op_stack = new Stack (); 					//creating stack to hold operators
		Queue Out_Queue = new Queue (); 				// Creating output queue
			
		while (tokenizer.hasMoreTokens()) {  			//while their is more input
			input_Queue.Enqueue(tokenizer.nextToken()); //Filling our input queue with tokens
		}
		
		while (input_Queue.front != null) { 			//while input queue is not empty
			
			String token = input_Queue.Dequeue(); 		//makes the token = the first value in the queue, as while loop executes it will continue popping off values the input will be iterated through.

			if ( token.equals("*") || token.equals ("/") || token.equals("+") || token.equals("-")|| token.equals("(")|| token.equals(")")) //if the token during this iteration is an operator or bracket
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

					Op_stack.push(token);				 //push token onto stack once above while loop condition is no longer upheld, ie once no operators of greater precedence remain in the stack.
			}
			}
			
			else										 //if token is not one of the listed operators
				Out_Queue.Enqueue(token);				 //Enqueue the token to the final output queue. All numbers should make it to this point
		}

		while (Op_stack.top != null){ 					//move all remaining operators from stack to output queue
			Out_Queue.Enqueue(Op_stack.pop());
		}
		System.out.print("Postfix: ");					//printing "Postfix" before returning the output queue
		
		while(Out_Queue.front != null){					//while output queue is not empty, dequeue all values and put spaces between them.
			String output = Out_Queue.Dequeue();
			System.out.print( output+" ");
		}

	}
	
}

