package lamda;

public class MyFunctionalInterfaceExam2 {

	public static void main(String[] args) {
        MyFunctionalInterface2 fi;
 
        fi = (x) -> {
            int result = x * 5;
            System.out.println(result);
        };
        fi.method(2);
 
        fi = x -> System.out.println(x * 5);
        fi.method(2);
    }
}
