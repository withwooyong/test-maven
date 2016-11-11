package redisexam;

public class JedisThread {

	public static void main(String args[]) {
		new ThreadTest().start();
	}
}

class ThreadTest extends Thread {
	public ThreadTest() {
		super();
	}

	public void run() {
		TestJedis jedis = new TestJedis();
		while (true) {
			try {
				jedis.push();
			} catch (Exception e) {
				System.out.println("Got exception");
			}
		}

	}
}