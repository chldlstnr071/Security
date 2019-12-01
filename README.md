# Security

[Alice_bmp.zip](https://github.com/chldlstnr071/Security/files/3907827/Alice_bmp.zip)

# Alice.bmp
![alice](https://user-images.githubusercontent.com/45475182/69909696-76b21200-1442-11ea-9e78-e937c922ad80.png)
위의 사진은 보호하기 전의 Alice 사진이다.

두 가지 모드 ECB와 CBC모드에 대해 분석하기 위해 TEA 알고리즘을 사용하였다. 그 코드는 아래와 같다.
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

# Alice_ECB.bmp
ECB 모드로 암호화했을 경우의 코드 일부분이다.
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

![alice_ECB](https://user-images.githubusercontent.com/45475182/69909695-76b21200-1442-11ea-90ee-4fa8aecdc3bd.png)

# Alice_CBC.bmp
CBC 모드로 암호화했을 경우의 코드 일부분이다.
import java.util.*;

public class CBC extends TEA{


	public CBC(){
		this.key = null;
	}

	public CBC(int[] keyAdd){
		this.key = new int[4];
		key[0] = keyAdd[0];
		key[1] = keyAdd[1];
		key[2] = keyAdd[2];
		key[3] = keyAdd[3];

	}

	public int[] encrypt(int[] plainText, int[] prev){
		
		if(key == null){
			System.out.println("no Key");
			System.exit(0);
		}
		
		int L = plainText[0] ^ prev[0];
		int R = plainText[1] ^ prev[1];
		
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

![alice_CBC](https://user-images.githubusercontent.com/45475182/69909697-774aa880-1442-11ea-8007-d3828b4ed653.png)
