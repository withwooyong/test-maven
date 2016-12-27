package algorithm.hanbit.hanbit09;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code9_01 {
	
	private static Logger log = LoggerFactory.getLogger(code9_01.class);
	
	public static void main(String[] args) {
		
		ArrayList<Student> students = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			Student student = new Student();
			student.setNumber(i);
			student.setName("A_" + i);
			students.add(student);
		}
		
		log.debug("{}", students.get(4).getNumber());
		log.debug("{}", students.get(4).getName());
	}

}
