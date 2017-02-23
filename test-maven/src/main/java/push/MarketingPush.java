package push;

import java.util.List;

public class MarketingPush extends ListObject {

	private static final long serialVersionUID = 5633004958149873146L;
	
	private int push_seq; // push 일련번호(SQ_TSM_MOBILE_MARKETING_PUSH.NEXTVAL)
	private String title; // push 메세지 내용
	private String content; // push 메세지 내용
	private String gcm_message; // GCM_MESSAGE 메세지 내용
	private String push_os; // OS - CSOD0100:IOS, CSOD0200:안드로이드
	private String push_target; // push 발송 대상
	private String push_target_tester; // push 발송 대상 중 테스터
	private List<String> push_target_tester_arr; // push 발송 대상 중 테스터 배열 처리
	private String push_target_sql; // 발송 대상 회원번호 추출 SQL
	private String target_sql_desc; // 발송 대상 회원번호 추출 SQL 설명
	private String start_dt; // push 발송 시작시간
	private String end_dt; // push 발송 종료시간

	private String ins_id; // 등록 ID
	private String ins_dt; // 등록일
	private String ins_nm; // 등록자
	private String upd_id; // 수정 ID
	private String upd_nm; // 수정자
	private String upd_dt; // 수정일
	private String del_yn; // 삭제 여부(Y:삭제, N:삭제안함)
	
	private String adr_status; // Android 발송 상태 1:대기, 2:대상수집, 3:발송대기 4:발송중,5:완료, 6:실패, 7:실패(타임아웃)
	private String elasped_time; // 발송 소요 시간
	private int adr_estimation; // GCM 요청 수
	private int adr_send_count; // 안드로이드 푸시 수신자 수
	private int adr_delivery; // 안드로이드 푸시 액션 실행자 수

    // ios
    private String ios_status;      // IOS 발송 상태 1:대기, 2:대상수집, 3:발송대기, 4:발송중, 5:완료, 6:실패, 7:실패(타임아웃)
    private int ios_estimation;     // IOS 푸쉬 발송량
    private int ios_send_count;     // IOS 푸시 수신자 수
    private int ios_delivery;       // IOS 푸시 액션 실행자 수

	public int getPush_seq() {
		return push_seq;
	}

	public void setPush_seq(int push_seq) {
		this.push_seq = push_seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getGcm_message() {
		return gcm_message;
	}

	public void setGcm_message(String gcm_message) {
		this.gcm_message = gcm_message;
	}

	public String getPush_os() {
		return push_os;
	}

	public void setPush_os(String push_os) {
		this.push_os = push_os;
	}

	public String getPush_target() {
		return push_target;
	}

	public void setPush_target(String push_target) {
		this.push_target = push_target;
	}

	public String getPush_target_tester() {
		return push_target_tester;
	}

	public void setPush_target_tester(String push_target_tester) {
		this.push_target_tester = push_target_tester;
	}

	public List<String> getPush_target_tester_arr() {
		return push_target_tester_arr;
	}

	public void setPush_target_tester_arr(List<String> push_target_tester_arr) {
		this.push_target_tester_arr = push_target_tester_arr;
	}

	public String getPush_target_sql() {
		return push_target_sql;
	}

	public void setPush_target_sql(String push_target_sql) {
		this.push_target_sql = push_target_sql;
	}

	public String getTarget_sql_desc() {
		return target_sql_desc;
	}

	public void setTarget_sql_desc(String target_sql_desc) {
		this.target_sql_desc = target_sql_desc;
	}

	public String getStart_dt() {
		return start_dt;
	}

	public void setStart_dt(String start_dt) {
		this.start_dt = start_dt;
	}

	public String getEnd_dt() {
		return end_dt;
	}

	public void setEnd_dt(String end_dt) {
		this.end_dt = end_dt;
	}

	public String getElasped_time() {
		return elasped_time;
	}

	public void setElasped_time(String elasped_time) {
		this.elasped_time = elasped_time;
	}

	public String getIns_id() {
		return ins_id;
	}

	public void setIns_id(String ins_id) {
		this.ins_id = ins_id;
	}

	public String getIns_dt() {
		return ins_dt;
	}

	public void setIns_dt(String ins_dt) {
		this.ins_dt = ins_dt;
	}

	public String getIns_nm() {
		return ins_nm;
	}

	public void setIns_nm(String ins_nm) {
		this.ins_nm = ins_nm;
	}

	public String getUpd_id() {
		return upd_id;
	}

	public void setUpd_id(String upd_id) {
		this.upd_id = upd_id;
	}

	public String getUpd_nm() {
		return upd_nm;
	}

	public void setUpd_nm(String upd_nm) {
		this.upd_nm = upd_nm;
	}

	public String getUpd_dt() {
		return upd_dt;
	}

	public void setUpd_dt(String upd_dt) {
		this.upd_dt = upd_dt;
	}

	public String getDel_yn() {
		return del_yn;
	}

	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}

	public String getAdr_status() {
		return adr_status;
	}

	public void setAdr_status(String adr_status) {
		this.adr_status = adr_status;
	}

	public int getAdr_estimation() {
		return adr_estimation;
	}

	public void setAdr_estimation(int adr_estimation) {
		this.adr_estimation = adr_estimation;
	}

	public int getAdr_send_count() {
		return adr_send_count;
	}

	public void setAdr_send_count(int adr_send_count) {
		this.adr_send_count = adr_send_count;
	}

	public int getAdr_delivery() {
		return adr_delivery;
	}

	public void setAdr_delivery(int adr_delivery) {
		this.adr_delivery = adr_delivery;
	}

    public String getIos_status() {
        return ios_status;
    }

    public void setIos_status(String ios_status) {
        this.ios_status = ios_status;
    }

    public int getIos_estimation() {
        return ios_estimation;
    }

    public void setIos_estimation(int ios_estimation) {
        this.ios_estimation = ios_estimation;
    }

    public int getIos_send_count() {
        return ios_send_count;
    }

    public void setIos_send_count(int ios_send_count) {
        this.ios_send_count = ios_send_count;
    }

    public int getIos_delivery() {
        return ios_delivery;
    }

    public void setIos_delivery(int ios_delivery) {
        this.ios_delivery = ios_delivery;
    }
}
