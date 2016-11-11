package blocking.queue;

public class Document implements Comparable<Document> {
	private int docId;
	private String docName;

	public Document(int bookId, String bookName) {
		this.docId = bookId;
		this.docName = bookName;
	}

	public int getDocId() {
		return docId;
	}

	public String getDocName() {
		return docName;
	}

	@Override
	public int compareTo(Document ob) {
		return docName.compareTo(ob.getDocName());
	}
}