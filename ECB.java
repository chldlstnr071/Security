import java.util.*;

public class ECB extends TEA{


	public ECB(){
		this.key = null;
	}

	public ECB(int[] keyAdd){
		this.key = new int[4];
		key[0] = keyAdd[0];
		key[1] = keyAdd[1];
		key[2] = keyAdd[2];
		key[3] = keyAdd[3];

	}

	public int[] encrypt(int[] plainText){

		if(key == null){
			System.out.println("no Key");
			System.exit(0);
		}

		int L = plainText[0];	
		int R = plainText[1];
		
		sum = 0;

		for(int i=0; i<32;i++){
			sum += delta;
			L += ((R << 4) + key[0]) ^ (R+sum) ^ ((R >> 5) + key[1]);
			R += ((L << 4) + key[2]) ^ (L+sum) ^ ((L >> 5) + key[3]);

		}
		
		int block[] = new int[2];
		block[0] = L;
		block[1] = R;

		return block;

	}

}