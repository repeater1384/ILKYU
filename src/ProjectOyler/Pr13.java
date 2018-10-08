package ProjectOyler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;

public class Pr13 {

	public static void main(String[] args) throws Exception {
		FileReader fr = new FileReader("C:\\temp\\Pr13.txt");
		BufferedReader br = new BufferedReader(fr);
		
		String str;
		
		String a[] = new String[100];
		for (int j = 0; j < 100; j++) {
			a[j] = "";
		}
		int i = 0;
		while ((str = br.readLine()) != null) {
			a[i] = str;
			i++;
		}

		BigInteger sum = BigInteger.ZERO;		//50자리수<<biginteger 이용;(long불가)
		for (i = 0; i < a.length; i++) {
			BigInteger bigInteger = new BigInteger(a[i]);
			sum = sum.add(bigInteger);
		}
		
		String result = String.valueOf(sum);		//첫 열글자
		System.out.println(result.substring(0,10));
		
		
//		for (String string : a) {
//			System.out.println(string);
//		}

	}

}
