
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
public class HuffmanList extends LinkedList<HuffmanNode>{
	
	//constructs huffmanlist with ordered byte array.
	public HuffmanList(byte[] bytearray){

		//Creates new a byte counter with input from argument
		ByteCounter bytes = new ByteCounter(bytearray);
		
		//Orders the array in terms of bytes
		bytes.setOrder("countInc");
		
		//Adds each byte to the LinkedList
		for(int i = 0; i < bytes.byteArray.size(); i++){
			add(new HuffmanNode(bytes.byteArray.get(i), bytes.byteCounts.get(i)));
		}	
	}
	
	//calls previous constructor with file read return
	public HuffmanList(String fileName) throws IOException{
		
		//calls previous constructor with file read return
		this(Files.readAllBytes(java.nio.file.Paths.get(fileName)));
		
	}
	
	//creates list from byteArray and intArray
	public HuffmanList(byte[] byteArray, int[] intArray){
		
		//exception handling, throws if count is less than zero and if there are two occurrences of a byte  
		for(int i = 0; i < byteArray.length; i++){
			if(intArray[i] < 0){
				throw new IllegalArgumentException();
			} 
			for(int j = 0; j < byteArray.length; j++){
				if(byteArray[i] ==  byteArray[j] && i != j){
					throw new IllegalArgumentException();
				}
			}
		}
		
		//intial conditions
		int nextSmallestCount = 100000000;
		int nextSmallestIndex = 0;
		//array to see if element has been added
		boolean[] addedIndex = new boolean[intArray.length];

		//continues until arrays are same size, this is when all entries are added
		while(this.size() != intArray.length){
			nextSmallestCount = 100000000;
			for(int i = 0; i < intArray.length; i++){
			
				//checks if element added and if its smaller than previous
				if(intArray[i] <= nextSmallestCount && addedIndex[i] != true){
					nextSmallestCount = intArray[i];
					nextSmallestIndex = i;		
				}		
			}
			//sets index to "added"
			addedIndex[nextSmallestIndex] = true;
			//creates new node with added element
			add(new HuffmanNode(byteArray[nextSmallestIndex], intArray[nextSmallestIndex]));	
		}
	}
}
