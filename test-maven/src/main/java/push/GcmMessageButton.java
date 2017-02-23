package push;

/**
 * GCM 발송 전문
 */
public class GcmMessageButton extends BaseObject {
	
	private static final long serialVersionUID = 7256086392702681327L;
	
	private String text;
	private String mapping_type;
	private String app_url_scheme;
	private String media_info;
	private String accept_log_url;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getMapping_type() {
		return mapping_type;
	}

	public void setMapping_type(String mapping_type) {
		this.mapping_type = mapping_type;
	}

	public String getApp_url_scheme() {
		return app_url_scheme;
	}

	public void setApp_url_scheme(String app_url_scheme) {
		this.app_url_scheme = app_url_scheme;
	}

	public String getMedia_info() {
		return media_info;
	}

	public void setMedia_info(String media_info) {
		this.media_info = media_info;
	}

	public String getAccept_log_url() {
		return accept_log_url;
	}

	public void setAccept_log_url(String accept_log_url) {
		this.accept_log_url = accept_log_url;
	}

}
