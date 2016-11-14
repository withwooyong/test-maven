package lamda;

public class UsingLocalVariable {
	void method(int arg) {
        int localVar = 40;
 
        // arg = 31; // final 특성 때문에 수정 불가
        // localVar = 41; // final 특성 때문에 수정 불가
 
        MyFunctionalInterface5 fi = () -> {
            System.out.println("arg: " + arg);
            System.out.println("localVar: " + localVar);
        };
 
        fi.method();
    }
}
