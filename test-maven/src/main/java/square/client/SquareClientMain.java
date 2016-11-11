package square.client;

import java.io.IOException;

import square.server.SquareServer;

public class SquareClientMain {

	private static final int N = 100;
	
	/** Use a SquareServer to square all the integers from 1 to N. */
	public static void main(String[] args) throws IOException {
		SquareClient client = new SquareClient("localhost", SquareServer.SQUARE_PORT);
		// send the requests to square 1...N
		for (int x = 1; x <= N; ++x) {
			client.sendRequest(x);
			System.out.println(x + "^2 = ?");
		}
		// collect the replies
		for (int x = 1; x <= N; ++x) {
			int y = client.getReply();
			System.out.println(x + "^2 = " + y);
		}
		client.close();
	}

}
