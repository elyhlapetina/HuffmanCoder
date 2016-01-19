import java.io.IOException;
import java.nio.file.*;


public class HuffmanCoder {
	
	//input and output files
	private String inputFile;
	private String outputFile;
	
	//constructor to specific fileinput and output names
	public HuffmanCoder(String inputName, String outputName){
		this.inputFile = inputName;
		this.outputFile = outputName;
	}
	
	//compresses into binary
	public void compress(){
		try{
			
			//reads in binary file
			BinaryWriter writer = new BinaryWriter(outputFile);
			
			//gets path of input file
			Path path = Paths.get(inputFile);
			
			//reads bytes from input
			byte[] byteArray = Files.readAllBytes(path);
			
			//creates new huffman code
			HuffmanCode hc = new HuffmanCode(this.inputFile);
			
			//for each byte, it generates the code and closes it
			for(int i = 0; i < byteArray.length; i++){
				writer.writeBinaryArray(hc.code(byteArray[i]));
				
			}
			
			//closes
			writer.close();
		} catch(Exception e) {
			System.out.println(e);
		} finally{
		
		}
	}
}
