package push;

/**
 * GCM 발송 전문
 * 
 * @author mayona
 * 
 */
public class ApnsMessage extends BaseObject {

    private static final long serialVersionUID = 4010796816649561850L;

    private String alert;
    private String seq;
    private String scheme_url;

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getScheme_url() {
        return scheme_url;
    }

    public void setScheme_url(String scheme_url) {
        this.scheme_url = scheme_url;
    }
}
