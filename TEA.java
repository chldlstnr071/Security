import java.util.*;

public class TEA{

	protected static int delta = 0x9e3779b9;
	protected static int ROUNDS = 32;
	protected int sum;	

	protected int[] key;

	public TEA(){
		key = null;
	}

	public TEA(int[] keyAdd){
		key = new int[4];
		key[0] = keyAdd[0];
		key[1] = keyAdd[1];
		key[2] = keyAdd[2];
		key[3] = keyAdd[3];

	}

	public void addKey(int[] key){
		if(key.length < 4)
			System.out.println("Key<128");
		else if(key.length > 4)
			System.out.println("Key>128");
		else
			this.key = key;			
	}

	public void printKeys(){
		if(key == null){
			System.out.println("no key");
		}
		System.out.println("Keys :\n");
		for(int i=0;i<4;i++){
			System.out.println(key[i]);
		}
	}
	
}