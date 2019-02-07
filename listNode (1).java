/* ================================
 * Student: Max Brodeur-Urbas
 * Student ID: 260806240
 * Date: Tuesday November 7th 2017
 * ===============================
 */

public class listNode { 		//defining our listNode class that will be used in stacks and queues later
		public String data;		//each listNode has a data String header and contains two other listNodes that are the next and back listNodes in linked lists.
		public listNode next;
		public listNode back;
	


public listNode (String value) 	//adding in a constructor to initialize a listNode
{
		data = value;			//creating a listNode with string argument will assign this argument to the data value in the listNode
		next = null;			//next and back listNodes within listNode are initialized to null and need to be assigned.
		back = null;
		
	}
}
