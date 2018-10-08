package ProjectOyler;

public class pr53 {
	static int result = 0;

//	private static int factorial(int n) {
//		if (n <= 1)
//			return n;
//
//		else
//			return n * factorial(n - 1);
//	}

	public static long combination(int n, int r) {
		if (n == r || r == 0)
			return 1;
		else
			return combination(n - 1, r - 1) + combination(n - 1, r);
	}

	public static void main(String[] args) {
		for (int i = 1; i <= 100; i++) {
			for (int r = 0; r <= i; r++) {
				if(combination(i, r)>=1000000)
					result++;
			}
		}

		System.out.println(combination(23, 10));
		System.out.println(result);

	}

}
