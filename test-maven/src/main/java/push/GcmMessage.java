package push;


import java.util.List;

/**
 * GCM 발송 전문
 */
public class GcmMessage extends BaseObject {	
	
	private static final long serialVersionUID = 2438252228443661769L;
	
	private String notice_type;
	private String skin_type;
	private String title;
	private String contents;
	private String image;
	private String bar_image;
	private String sub_title;
	private String sub_contents;
	private String arrive_log_url;
	private String error_log_url;
	private List<GcmMessageButton> button;
	
	// 버젼 호환성 문제로 추가
	private String app_url_scheme;
	private String accept_log_url;
	
	public String getNotice_type() {
		return notice_type;
	}

	public void setNotice_type(String notice_type) {
		this.notice_type = notice_type;
	}

	public String getSkin_type() {
		return skin_type;
	}

	public void setSkin_type(String skin_type) {
		this.skin_type = skin_type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBar_image() {
		return bar_image;
	}

	public void setBar_image(String bar_image) {
		this.bar_image = bar_image;
	}

	public String getSub_title() {
		return sub_title;
	}

	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}

	public String getSub_contents() {
		return sub_contents;
	}

	public void setSub_contents(String sub_contents) {
		this.sub_contents = sub_contents;
	}

	public String getArrive_log_url() {
		return arrive_log_url;
	}

	public void setArrive_log_url(String arrive_log_url) {
		this.arrive_log_url = arrive_log_url;
	}

	public String getError_log_url() {
		return error_log_url;
	}

	public void setError_log_url(String error_log_url) {
		this.error_log_url = error_log_url;
	}

	public List<GcmMessageButton> getButton() {
		return button;
	}

	public void setButton(List<GcmMessageButton> button) {
		this.button = button;
	}

	public String getApp_url_scheme() {
		return app_url_scheme;
	}

	public void setApp_url_scheme(String app_url_scheme) {
		this.app_url_scheme = app_url_scheme;
	}

	public String getAccept_log_url() {
		return accept_log_url;
	}

	public void setAccept_log_url(String accept_log_url) {
		this.accept_log_url = accept_log_url;
	}
	
}
