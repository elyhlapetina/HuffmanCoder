import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

public class HuffmanCode {

	//boolean array to be returned after search
	private boolean[] currentBoolChain =null;
	
	//bytecounter to get bytes from
	private ByteCounter byteCounter;

	//head of tree after hlist transformation
	HuffmanNode nodeHead = null;
	
	public HuffmanCode(byte[] byteArray){
		
		//calls constructors for counting bytes
		byteCounter = new ByteCounter(byteArray);
		HuffmanList hlist = new HuffmanList(byteArray);
		
		
		//runs until hlist is reduced to head
		while(hlist.size() > 1){
			
			//removes first two nodes and stores them in pointers
			HuffmanNode node1 = hlist.removeFirst();
			HuffmanNode node2 = hlist.removeFirst();
			
			//creates new node with count of left and right nodes
			HuffmanNode newNode = new HuffmanNode((byte)-1, node1.count + node2.count);
			
			
			//places nodes in appropriate place in tree
			if(node1.count <= node2.count){
				newNode.left = node1;
				newNode.right = node2;
			} else {
				newNode.right = node1;
				newNode.left = node2;
			}
		
			
			//inserts node into correct place in tree, based on count
			boolean isInserted = false;
			for(int i = 0; i < hlist.size(); i++){
				if(newNode.count <= hlist.get(i).count && isInserted == false){
					hlist.add(i, newNode);
					isInserted = true;
				}
			}
			if(isInserted == false){
				hlist.add(newNode);
			}
		}		
		
		//sets node to first element of hlist
		nodeHead = hlist.getFirst();
		
		//calls generate code to create code for each node
		generateCode(nodeHead, null);
	}
	
	
	//creates new huffmancode from input file
	public HuffmanCode(String fileName) throws IOException{
		
		//calls constructors for counting bytes
		HuffmanList hlist = new HuffmanList(fileName);
		byteCounter = new ByteCounter(fileName);

		//runs untill hlist is reduced to 1 node
		while(hlist.size() > 1){
			
			//removes first two nodes and stores them in pointers
			HuffmanNode node1 = hlist.removeFirst();
			HuffmanNode node2 = hlist.removeFirst();
			
			//gives count for node
			HuffmanNode newNode = new HuffmanNode((byte)-1, node1.count + node2.count);
			
			//places each node in appropriate place
			if(node1.count <= node2.count){
				newNode.left = node1;
				newNode.right = node2;
			} else {
				newNode.right = node1;
				newNode.left = node2;
			}
		
			//places new node in appropriate place based on count.
			boolean isInserted = false;
			for(int i = 0; i < hlist.size(); i++){
				if(newNode.count <= hlist.get(i).count && isInserted == false){
					hlist.add(i, newNode);
					isInserted = true;
				}
			}
			if(isInserted == false){
				hlist.add(newNode);
			}
		}		
		
		//sets hlist first node to tree
		nodeHead = hlist.getFirst();
		//calls generate code to create code for each node
		generateCode(nodeHead, null);
	}
	
	
	//creates new huffman code from two arrays
	public HuffmanCode(byte[] byteArray, int[] intArray){

		//calls constructors for counting bytes
		HuffmanList hlist = new HuffmanList(byteArray, intArray);
		byteCounter = new ByteCounter(byteArray, intArray);

		//iterates till hlist is reduced to one
		while(hlist.size() > 1){
			
			//removes first two nodes and stores in pointers
			HuffmanNode node1 = hlist.removeFirst();
			HuffmanNode node2 = hlist.removeFirst();
			
			//determines count
			HuffmanNode newNode = new HuffmanNode((byte)-1, node1.count + node2.count);
			
			//places each node in appropriate place
			if(node1.count <= node2.count){
				newNode.left = node1;
				newNode.right = node2;
			} else {
				newNode.right = node1;
				newNode.left = node2;
			}
		
			//places new node in appropriate place based on count.
			boolean isInserted = false;
			for(int i = 0; i < hlist.size(); i++){
				if(newNode.count <= hlist.get(i).count && isInserted == false){
					hlist.add(i, newNode);
					isInserted = true;
				}
			}
			if(isInserted == false){
				hlist.add(newNode);
			}
		}		
		
		//sets hlist first node to tree
		nodeHead = hlist.getFirst();
		//calls generate code to create code for each node
		generateCode(nodeHead, null);
	}
	
	
	public static void generateCode(HuffmanNode node, HuffmanNode prevNode){
		//generates code for EVERY piece of tree. Its children get another part of the code depending if they are on the left or the right.
		
		//base case
		if(node == null){
			return;
		}
		
		
		if(prevNode != null){
			//checks if it is left or right node then creates new array with appropriate values
			if(node == prevNode.left){
				//the case if it is at a depth of 1
				if(prevNode.code == null){
					node.code = new boolean[1];
					node.code[0] = false;
				} else {
					//copies previous code into new node, sets next value to correct value.
					node.code = new boolean[prevNode.code.length + 1];
					for(int i = 0; i < prevNode.code.length; i++){
						node.code[i] = prevNode.code[i];
					}
					node.code[prevNode.code.length] = false;
				}
			}
			if(node == prevNode.right){
				//the case if it is at a depth of 1
				if(prevNode.code == null){
					node.code = new boolean[1];
					node.code[0] = true;
				} else {
					//copies previous code into new node, sets next value to correct value.
					node.code = new boolean[prevNode.code.length + 1];
					for(int i = 0; i < prevNode.code.length; i++){
						node.code[i] = prevNode.code[i];
					}
					node.code[prevNode.code.length] = true;
				}
			}
		}
		
		//recursive calls
		generateCode(node.left, node);
		generateCode(node.right, node);
		
	}
	
	//added method for search functionality.
	public void search(HuffmanNode node, byte b){
		
		if(node.getByte() == b && node.left == null && node.right == null){
			currentBoolChain = node.code;
		}
		
		if(node.left!= null){
			search(node.left,b);
		}
		if(node.right != null){
			search(node.right,b);
		}
		
	}
	
		
	//returns code for specific byte
	public boolean[] code(byte b){
		
		//returns code from search method.
		search(nodeHead, b);
		if(currentBoolChain == null){
			throw new IllegalArgumentException();
		}
		return currentBoolChain;	
		
	}
	
	//calls code then turns it into string
	public String codeString(byte b){
		StringBuilder builder = new StringBuilder();
		boolean[] workingArray = code(b);
		
		//appends 0 or 1 depending on true or false respectfully
		for(int i = 0; i < workingArray.length; i++){
			if(workingArray[i] == false){
				builder.append("0");
			} else {
				builder.append("1");
			}
		}
		
		return builder.toString();
	}
	
	//iterates through every present byte and then prints each one out on a new line.
	public String toString(){
		
		//orders bytes in correct order
		byteCounter.setOrder("countDec");
		
		
		byte[] elements = byteCounter.getElements();
		StringBuilder sb = new StringBuilder();
		
		//appends each formatted string line
		for(int i = 0; i < elements.length; i++){
			sb.append(elements[i] + ": " +codeString(elements[i])+ '\n');
			
		}
		//returns string without last new line
		return sb.toString().trim();
	}
}	
