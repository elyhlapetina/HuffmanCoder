
public class HuffmanNode {
	
	//byte that node represents
	public byte b;
	
	//count of byte
	public int count;
	
	//code of byte based on tree
	public boolean[] code;
	
	//left child in tree
	public HuffmanNode left;
	
	//right child in tree
	public HuffmanNode right;
	
	
	//constructor for node
	public HuffmanNode(byte b, int c){
		this.count = c;
		this.b = b;
		this.left = null;
		this.right = null;
	}
	
	//returns count
	public int getCount(){
		return this.count;
	}
	
	
	//sets count
	public void setCount(int count){
		this.count = count;
	}
	
	//returns byte
	public byte getByte(){
		return this.b;
	}
}
