package lamda;

import lombok.Data;

@Data
public class Member {
	private String name;
    private String id;
    private Address address;
    
    public Member() {
        System.out.println("Member 실행");
    }
 
    public Member(String id) {
        System.out.println("Member (String id ) 실행");
        this.id = id;
    }
 
    public Member(String name, String id) {
        super();
        System.out.println("Member (String name, String id) 실행");
        this.name = name;
        this.id = id;
    }
 
    public Member(String name, String id, Address address) {
        super();
        this.name = name;
        this.id = id;
        this.address = address;
    }
}
