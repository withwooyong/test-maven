package threadtest.washroom;

public class SyncThreadTest {

	public static void main(String[] args) throws InterruptedException {

		Washroom wr = new Washroom();
		FamilyThread father = new FamilyThread("father", wr);
		FamilyThread mother = new FamilyThread("mother", wr);
		FamilyThread sister = new FamilyThread("sister", wr);
		FamilyThread brother = new FamilyThread("brother", wr);
		FamilyThread me = new FamilyThread("me", wr);

		father.start(); Thread.sleep(10);
		mother.start(); Thread.sleep(10);
		sister.start(); Thread.sleep(10);
		brother.start(); Thread.sleep(10);
		me.start();

	}

}
