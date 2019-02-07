/* ================================
 * Student: Max Brodeur-Urbas
 * Student ID: 260806240
 * Date: Tuesday November 7th 2017
 * ===============================
 */

public class Stack {
	
		public listNode top; 						//defining the top listNode that will be used to refer to the most recently pushed listNode in stack.
				
		public void push ( String newStr ) 			//defining our push function that will add listNodes to stack
		{
			listNode input = new listNode (newStr);	//creating a new listNode who's data is the String we are trying to push in.
			
			if(top != null) 						//if Stack not empty make back of the new listNode = old top listNode and next of old top = new listNode.
			{
				input.back = top;
				top.next = input;

			}
			top = input; 							//if stack is empty make new listNode the top listNode in stack.
				
		}
		
		public String pop() {						//defining a pop function that removes the most recent addition to the stack and returns its data value
			
			String popresult = top.data;			//saving data value of top listNode before the top is changed.
		
			if (top == null){						//if the stack is empty print warning message and exit.
				System.out.println("Stack is empty");	
				return null;
			}
			if (top.back == null){					// if there is only one item in stack, delete top list Node.
				top = null;
			}
			else {
				listNode temp = top.back;			// temp holds second listNode in stack
				temp.next = null;					//make second listNode in stack have no next pointer meaning there is no listNode ahead of it in the stack.
				top = temp;							//making the top listNode = the old second topmost listNode.
			}
			
			return popresult;						//returning the string data value of the listNode that was popped out.
			
		}
	}


