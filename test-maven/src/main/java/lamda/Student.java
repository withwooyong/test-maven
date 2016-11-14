package lamda;

import lombok.Data;

@Data
public class Student {

	private String name;
    private int englishScore;
    private int mathScore;
    private String sex;
    
    public Student(String name, int englishScore, int mathScore, String sex) {
        super();
        this.name = name;
        this.englishScore = englishScore;
        this.mathScore = mathScore;
        this.sex = sex;
    }
    
}
