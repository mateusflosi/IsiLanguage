import java.util.Scanner;
public class Loop{ 
	public static void main(String args[]){
 		Scanner _key = new Scanner(System.in);
		int  a;
		int  b;
		a= _key.nextInt();
		b= _key.nextInt();
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

			}

		}

		_key.close();
	}
}