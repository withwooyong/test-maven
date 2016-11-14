package lamda;

public class UsingThis {

	public int outterField = 10;
	 
    class Inner {
        int innerField = 20;
 
        void method() {
            MyFunctionalInterface4 fi = () -> {
                System.out.println("Outter Field: " + outterField);
                System.out.println("Outter Field: " + UsingThis.this.outterField + "\n");
 
                System.out.println("Inner Field: " + innerField);
                System.out.println("Inner Field: " + this.innerField + "\n");
            };
            
            fi.method();
        }
    }
}
