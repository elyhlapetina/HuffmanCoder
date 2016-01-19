import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
import java.nio.file.Files;
public class ByteCounter {
	
	/*Fields */
	ArrayList<Byte> byteArray = new ArrayList<Byte>();
	ArrayList<Integer> byteCounts = new ArrayList<Integer>();  

	
	//constructor for byte array
	public ByteCounter(byte[] inputBytes){
		
		//Creates array with first occurrence of all bytes
		//Creates a new array with single byte occurrences
		// 
		for(int i = 0; i < inputBytes.length; i++){
			if(!byteArray.contains(inputBytes[i])){
				byteArray.add(inputBytes[i]);
			}
		}
		
		//Counts each byte from byteArray based on inputBytes
		for(int i = 0; i < byteArray.size(); i++){
			int count = 0;
			for(int j = 0; j < inputBytes.length; j++){
				if(inputBytes[j] == (Byte)byteArray.get(i)){
					count++;
				}
			}
			byteCounts.add(count);
		}

	}
		
	
	//constructor for file type, call previous constructor with return
	public ByteCounter(String fileName) throws IOException{
		this(Files.readAllBytes(java.nio.file.Paths.get(fileName)));
	}
	
	//constructor for byte array
	public ByteCounter(byte[] inputBytes, int[] inputInts){
		
		for(int i = 0; i < inputBytes.length; i++){
			byteCounts.add(inputInts[i]);
			byteArray.add(inputBytes[i]);
		}

	}
	
	//returns number of occurrences of input byte
	public int getCount(byte b){
		return (Integer)byteCounts.get(byteArray.indexOf(b));
	}
	
	
	//returns array of int corresponding to occurrences of bytes
	public int[] getCount(byte[] b){
		int[] bCount = new int[b.length];
		
		for(int i = 0; i < b.length; i++){
			bCount[i] = (Integer)byteCounts.get(byteArray.indexOf(b[i]));
		}
		
		return bCount;
	}
	
	
	//sets order of string by rearranging the arrays
	public void setOrder(String order){
		
		//two new temporary arrays
		ArrayList<Byte> tempBytes = new ArrayList<Byte>();
		ArrayList<Integer> tempCounts = new ArrayList<Integer>();
		
		
		if(order.compareTo("byte") == 0){
			
			//intial conditions so that it will always select next smallest
			byte nextSmallestByte = 127;
			int nextSmallestIndex = 0;
			
			
			//runs until all elements have been removed and reogranzied
			while(!byteArray.isEmpty())	{
				//checks to see if the next value is actually the smallest
				nextSmallestByte = 127;
				for(int i = 0; i < byteArray.size(); i++){
					
					if(byteArray.get(i) <= nextSmallestByte){
						nextSmallestByte = byteArray.get(i);
						nextSmallestIndex = i;
					}
				}
				//reassigns the reordered array
				tempBytes.add(byteArray.remove(nextSmallestIndex));
				tempCounts.add(byteCounts.remove(nextSmallestIndex));
			}
			
			byteArray = tempBytes;
			byteCounts = tempCounts;
			
			
		} else if(order.compareTo("countInc") == 0){
			
			//initial conditions to match sure it selects next smallest
			int nextSmallestCount = byteCounts.get(0);
			int nextSmallestIndex = 0;
			
			//continues untill all nodes have been removed
			while(!byteCounts.isEmpty())	{
				
				//reset initial conditions
				nextSmallestCount = 100000000;
				nextSmallestIndex = 0;
				
				//iterates through nodes that are left
				for(int i = 0; i < byteCounts.size(); i++){
					if(byteCounts.get(i) <= nextSmallestCount){
						nextSmallestCount = byteCounts.get(i);
						nextSmallestIndex = i;
					}
					

				}
				tempCounts.add(byteCounts.remove(nextSmallestIndex));
				tempBytes.add(byteArray.remove(nextSmallestIndex));
			}
			
			//point var names towards new arraylists
			byteArray = tempBytes;
			byteCounts = tempCounts;
			
			
			
			
		} else if(order.compareTo("countDec") == 0) {
			
			//initial conditions
			int nextLargestCount = 0;
			int nextLargestIndex = 0;
			
			//continues until all nodes are removed, this makes sure all nodes are ordered
			while(!byteCounts.isEmpty()){
				nextLargestIndex = 0;
				nextLargestCount = 0;
				
				//iterates through remaining nodes
				for(int i = 0; i < byteCounts.size(); i++){
					if(byteCounts.get(i) >= nextLargestCount){
						nextLargestCount = byteCounts.get(i);
						nextLargestIndex = i;
					}
					

				}
				tempCounts.add(byteCounts.remove(nextLargestIndex));
				tempBytes.add(byteArray.remove(nextLargestIndex));
			}
			
			//sets fields to new arrays
			byteArray = tempBytes;
			byteCounts = tempCounts;
			
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	
	//returns elements in array form
	public byte[] getElements(){
		
		byte[] returnArray = new byte[byteArray.size()];
		
		//iterates through each array element
		for(int i = 0; i < byteArray.size(); i++){
			returnArray[i] = (Byte) byteArray.get(i);
		}
		
		return returnArray;
	}
	
	//prints out bytes and counts
	public String toString() {
		
		StringBuilder b = new StringBuilder();
		
		//first character
		b.append(byteArray.get(0));
		b.append(":");
		b.append(byteCounts.get(0));
		
		//2nd through last characters
		for(int i = 1; i < byteArray.size(); i++){
			b.append(" ");
			b.append(byteArray.get(i));
			b.append(":");
			b.append(byteCounts.get(i));
		}
		
		return b.toString();
	}
	
	
	//prints out chars and counts
	public String toString(String format){
		
		//first character
		StringBuilder b = new StringBuilder();
		b.append((char)(byteArray.get(0).byteValue()));
		b.append(":");
		b.append(byteCounts.get(0));
		
		//second through last characters
		for(int i = 1; i < byteArray.size(); i++){
			b.append(" ");
			b.append((char)(byteArray.get(i).byteValue()));
			b.append(":");
			b.append(byteCounts.get(i));
		}
		
		return b.toString();
	}
}


