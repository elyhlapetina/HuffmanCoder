import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;

public class HuffmanCodeFundTest {

	/* Other JUnit tests.
     *
     * Write your own JUnit tests below to check the correctness of your implementation.
     * Leave the above methods intact and add your own tests below this comment so it's
     * easier for the graders to see what you've added.
     *
     * When you turn in your drafts we will run our own test suite on your code and provide
     * you with the feedback to help you debug an issues.
     *
     * Your draft code needs to contain a complete set of methods as specified in the assignment.
     * Otherwise, it won't compile with our test suite.  To avoid this, you should first write
     * a complete set of "skeleton" methods, i.e. methods with the correct names and
     * the correct return and argument types.
     *
     * To help ensure your code will compile when you submit it for testing, below we have
	 * included a set of skeleton tests. You should replace these with more meaningful tests
	 * as you write your methods.
     */

	/*This is a comprehensive test. It covers every method in HuffmanCode, the rest of the tests are just for double safety. By its nature, this also tests almost every other method + edge cases for them!*/
	@Test
	public void testByteAndCountArraysConstructorAndCode2() {
		HuffmanCode hc = new HuffmanCode(new byte[] {(byte)'a', (byte)'c', (byte)'b', (byte)'d', (byte)'e' }, new int [] {2, 4, 3, 5, 6});
		
		boolean[] code = hc.code((byte)'a');
    	boolean[] codeCheck = new boolean[] {false, true, false};
    	for(int i = 0; i < code.length; i++){
    		if(codeCheck[i] == code[i])
    			assertTrue(true);
    		else
    			fail("For A. On element i: "+ i + " It was: " + ((Boolean)code[i]).toString() + " but should have been: " +  ((Boolean)codeCheck[i]).toString());
    	}
    	
    	code = hc.code((byte)'b');
    	codeCheck = new boolean[] {false, true, true};
    	for(int i = 0; i < code.length; i++){
    		if(codeCheck[i] == code[i])
    			assertTrue(true);
    		else
    			fail("For B. "+ i +" It was: " + ((Boolean)code[i]).toString() + " but should have been: " +  ((Boolean)codeCheck[i]).toString());
    	}
    	code = hc.code((byte)'c');
    	codeCheck = new boolean[] {false, false};
    	for(int i = 0; i < code.length; i++){
    		if(codeCheck[i] == code[i])
    			assertTrue(true);
    		else
    			fail("For C. "+ i +" It was: " + ((Boolean)code[i]).toString() + " but should have been: " +  ((Boolean)codeCheck[i]).toString());
    	}
    	code = hc.code((byte)'d');
    	codeCheck = new boolean[] {true, false};
    	for(int i = 0; i < code.length; i++){
    		if(codeCheck[i] == code[i])
    			assertTrue(true);
    		else
    			fail("For D. " + i +" It was: " + ((Boolean)code[i]).toString() + " but should have been: " +  ((Boolean)codeCheck[i]).toString());
    	}
    	code = hc.code((byte)'e');
    	codeCheck = new boolean[] {true, true};
    	for(int i = 0; i < code.length; i++){
    		if(codeCheck[i] == code[i])
    			assertTrue(true);
    		else
    			fail("For E. " + i + " It was: " + ((Boolean)code[i]).toString() + " but should have been: " +  ((Boolean)codeCheck[i]).toString());
    	}
    	
	}
	
	
	
	@Test
	public void testByteArrayArgumentConstructor() {
    	HuffmanCode hc = new HuffmanCode(new byte[] {(byte)'a', (byte)'b', (byte)'b'});
    	if(hc.nodeHead.left.b != (byte)'a'){
    		fail();
    	}
    	if(hc.nodeHead.right.b != (byte)'b'){
    		fail();
    	}
	}

	@Test
	public void testStringArgumentConstructorAndCode() throws IOException {
		//aaaabbbcc \n
    	HuffmanCode hc = new HuffmanCode("file.txt");
    	boolean[] code = hc.code((byte)'b');
    	boolean[] codeCheck = new boolean[] {true, true};
    	for(int i = 0; i < code.length; i++){
    		if(codeCheck[i] == code[i])
    			assertTrue(true);
    		else
    			fail();
    	}
    	
    	
    	code = hc.code((byte)'c');
    	codeCheck = new boolean[] {true, false, true};
    	
    	for(int i = 0; i < code.length; i++){
    		if(codeCheck[i] == code[i])
    			assertTrue(true);
    		else
    			fail("At i ="+ i + "It was: " + ((Boolean)code[i]).toString() + " but should have been: " +  ((Boolean)codeCheck[i]).toString());
    	}
    	
	}

	
	@Test
	public void testByteAndCountArraysConstructor() {
    	HuffmanCode hc = new HuffmanCode(new byte[] {(byte)'a', (byte)'b'}, new int [] {2, 3});
    	if(hc.nodeHead.left.b != (byte)'a'){
    		fail();
    	}
    	if(hc.nodeHead.right.b != (byte)'b'){
    		fail();
    	}
	}
	
	
	
	//builds from file.txt ... output written by hand...
	@Test
	public void testToStringMethod2() throws IOException {
		
		HuffmanCode hc = new HuffmanCode("file.txt");
    	String s = hc.toString();
    	assertEquals("97: 0\n98: 11\n99: 101\n10: 100", s);
	}
	
	
}