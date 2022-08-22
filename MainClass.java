import java.util.Scanner;
public class MainClass{ 
	public static void main(String args[]){
 		Scanner _key = new Scanner(System.in);
		int  a;
		int  b;
		int  i;
		String  t1;
		a= _key.nextInt();
		b= _key.nextInt();
		a = 1+2*3/b;
		if (a>b) {
			System.out.println(a);
			while (a<b) {
				System.out.println(a);
				System.out.println(b);
			}

			if (a==b) {
				System.out.println(a);
			}
			else {
				System.out.println(b);
			}
		}
		else {
			System.out.println(b);
			System.out.println(a);
			System.out.println(a);
		}
		if (a==4) {
			System.out.println(b);
			System.out.println(a);
		}
		else {
			System.out.println(b);
			System.out.println(a);
			System.out.println(a);
		}
		while (a>b) {
			System.out.println(a);
			while (b<a) {
				System.out.println(b);
				while (a==1) {
					System.out.println(a);
					System.out.println(b);
					System.out.println(a);
				}

				if (b==2) {
					System.out.println(b);
					System.out.println(a);
					System.out.println(b);
				}
				else {
					System.out.println(b);
					System.out.println(a);
					System.out.println(a);
				}
			}

		}

		_key.close();
	}
}