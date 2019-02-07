/* ================================
 * Student: Max Brodeur-Urbas
 * Student ID: 260806240
 * Date: Tuesday November 7th 2017
 * ===============================
 */

public class Queue {
	
	public listNode front; //defining front and rear listNodes
	public listNode rear;
		
	public void Enqueue ( String newStr ) //creating the Enqueue function which will add a listNode to the back of the Queue
	{
		listNode input = new listNode (newStr); //new listNode containing the string we are tryin to Enqueue
		
		if (rear == null){	//if queue is empty then new listNode will become front and rear.
			front = input;
			rear = input;
		}
		else{
			input.next = rear; //if queue is not empty, the next listNode of the new listNode will be assigned as the rear of the queue.
			rear.back = input; //the back listNode of the rear listNode in the queue will be assigned to be out input listNode
			rear = input;	//finally the rear listNode will be reassigned as out new listNode making our new listNode the rear of the queue officially
		}
			
	}
	
	public String Dequeue() { //removes the first item in the queue and returns its string data value.
		
		if (front == null){ //if queue is empty return warning statement and exit.
			System.out.println("Queue is empty.");
			return null;
		}
		
		String dequeue_result = front.data; // saving the data value of the front listNode in Queue before it is overwritten

		if ( front.back == null) { //if there is only one listNode in the queue make the queue empty.
			front = null;
			rear = null;
		}
		
		else {
		
		listNode temp = front.back; // temp holds second listNode in queue
		temp.next = null; //make next value of second listNode in queue null meaning it is now the first value in queue
		front = temp; //second value in queue officially becomes front value
		
		}
	
		return dequeue_result; //return data value of the front value that is dequeued.

		
	}
}
