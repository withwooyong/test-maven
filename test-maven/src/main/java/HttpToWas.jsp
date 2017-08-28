<%--
/*----------------------------------------------------------
/*   시스템명 : ExBill SMS (Service Management System) Ver 1.0
/*   모 듈 명 : CCS 어플리케이션 서버에서 HTTP 송신정보 수신하여 편성정보
/*           호출하는 비즈니스 로직 구현
/*   모듈기호 : CcsToWas.JSP
/*   기능        : CCS가 SOAP 서버인 경우 처리용 (DBS 수신, EPG STEP2 요청, VAS 인증요청)
/*   작 성 자 : 전부일
/*   작 성 일 : 2007/09/19
/*----------------------------------------------------------
/* ( 개발환경 )
/*   운영체제 : Unix, Windows, Linux
/*   DataBase : Oracle
/*   개발도구 : Eclipse 3.2, Editor
/*----------------------------------------------------------
/* ( 변경이력 )
/*
/*   변경일자    변경자       변     경     내      역
/*   --------    ------  -----------------------------------
/*
/*
/*----------------------------------------------------------
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" import="com.imnetpia.exbill.common.*,java.io.*,java.util.Vector, java.sql.*, java.util.*"%>
<%@ page import="com.imnetpia.exbill.service.biz.*, com.imnetpia.exbill.service.entity.*"%>
<%@ page import="javax.xml.parsers.DocumentBuilder, javax.xml.parsers.*, javax.xml.parsers.ParserConfigurationException"%>
<%@ page import="java.net.*"%>


<jsp:useBean id="bts" scope="session" class="com.imnetpia.exbill.EXServer" />
<%!
	//리눅스, 윈도의 exbill 기본 위치 얻기
	public String getPath() {
		String path_head = "";
		if(!IConstant.EXBILL_INSTALL.trim().equals("")) {
			path_head = IConstant.EXBILL_INSTALL;
		} else {
			if(System.getProperty("os.name").substring(0, 5).equals("Windo")){
				path_head = "C:/ExBill/exbill_hanaro";
			} else {
				path_head = "/home/DATA";
			}
		}
		return path_head;
	}


%>
<%
System.out.println("[" + IFuncDate.getSystemTime() + "] HttptoWas 시작 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

Connection gConn = null;
	Vector elem = new Vector();

	BIZ_COMPO biz_compo = null;
	BIZ_CH biz_ch = null;
	BIZ_DBS dbs = null;
	BIZ_VASAUTH vasauth = null;

	File file = null;
	File manualFile = null; // ManualRequest처리를 위한 파일객체

	PrintWriter  pw = null;	//응답결과 송신 라이터

	String ID_CCS_IF = "";//메소드 구분 ID
	String CCS_ID = "";//ccs_id
	String resultFile= "";
	String resultFileUrl= "";
	String[] arr_str = null;
	String[] index_arr = null;
	String result = "";//처리결과
	String Result = "";// 전송결과
	boolean isTF = false;
	BufferedReader in =null;
	//StringBuilder sb = null;


	//20080521
	//set dsname
	try{
			bts.setDS_NAME(BIZ_COMMON.CCS_DS_NAME);
	}catch(Exception e){
		e.printStackTrace();
	}
	/*
	인덱스 구조
	채널정보 : "C(채널)" + "_"  + "P (Process)" + yyyymmddhh24miss
	편성정보 : "S(스케쥴)" + "_" + "R (Request)" + yyyymmddhh24miss + "_" + 스케쥴날짜(yyyymmdd)
	*/
	String index_str = ""; //인덱스 변수
	String[] ID_CCS_IF_arr = null;
	if ( request.getParameter("Index") != null ) {
		
		index_str = IFuncStr.no(request.getParameter("Index").trim());
		index_arr = IFuncStr.tokenAll(index_str, ",");
		
		System.out.println("index_str>>>"+index_str);
		System.out.println("index_arr>>>"+index_arr.length);
		
		if(index_arr.length > 0){
			index_str = index_arr[0];
			System.out.println("index_str>>>"+index_str);
			//S_R20170817044605_20170817
			ID_CCS_IF_arr = IFuncStr.tokenAll(index_arr[1], "=");
			
			CCS_ID = ID_CCS_IF_arr[1];
			ID_CCS_IF = "CCS_IF001";	//CCS_IF001
			System.out.println("CCS_ID>>>"+CCS_ID);
		}
	}
	
	if ( request.getParameter("ID_CCS_IF") != null ) {
		ID_CCS_IF = IFuncStr.no(request.getParameter("ID_CCS_IF").trim());
	}

	String aFilePath = "";
	if ( request.getParameter("aFilePath") != null ) {//수신파일 저장 위치
		aFilePath = IFuncStr.no(request.getParameter("aFilePath").trim());
	}

	if ( request.getParameter("Result") != null ) {
		Result = IFuncStr.no(request.getParameter("Result").trim());
	}

System.out.println("[" + IFuncDate.getSystemTime() + "] HttptoWas CALL ID_CCS_IF>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + ID_CCS_IF);
System.out.println("[" + IFuncDate.getSystemTime() + "] HttptoWas CALL index>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + index_str);
System.out.println("[" + IFuncDate.getSystemTime() + "] HttptoWas CALL Result>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + Result);
	// 기본 컨넥션 구조
	try {
		pw = response.getWriter();	// 응답결과 송신 라이터

		if(!Result.equals("")) {	// 채널/채널편성 전송 결과

			if(!CCS_ID.equals("")) {
				String result_file = "";
				File EPGCH_file = new File(getPath() + "/webapps/dataFile/CCSFolder/SND/EPGCH/");
				File EPGCOMPO_file = new File(getPath() + "/webapps/dataFile/CCSFolder/SND/EPGCOMPO/");

				//채널 파일 이동
				File[] EPGCH_list = EPGCH_file.listFiles();
				for(int i=0;i<EPGCH_list.length;i++) {// 전송파일 찾
					String name = EPGCH_list[i].getName();
					if(name.length() > 10) {

						if(name.substring(name.length() - 10, name.length()).equals(CCS_ID)) {
							result_file = EPGCH_list[i].getPath();
							if(Result.equals("0")) {//전송성공
								BIZ_COMMON.moveFile(result_file, EPGCH_file.getPath()+"/SUCCESS/" +  IFuncDate.getSystemDate().substring(0, 6), EPGCH_list[i].getName());
							}
							else {//전송실패
								BIZ_COMMON.moveFile(result_file, EPGCH_file.getPath()+"/ERROR/" +  IFuncDate.getSystemDate().substring(0, 6), EPGCH_list[i].getName());
								elem.clear();
								elem.add("9999");
								elem.add(CCS_ID);
								elem.add(CCS_ID);

								bts.QueryExecutePS(new IEIF_SVC_IFDET().QueryUpdate2(), elem);
							}
						}
					}
				}
				//채널 편성 파일 이동
				File[] EPGCOMPO_list = EPGCOMPO_file.listFiles();
				for(int j=0;j<EPGCOMPO_list.length;j++) {//전송파일 찾기
					String name = EPGCOMPO_list[j].getName();
					if(name.length() > 10) {
						if(name.substring(name.length() - 10, name.length()).equals(CCS_ID)) {
							result_file = EPGCOMPO_list[j].getPath();
							if(Result.equals("0")) {//전송성공
								BIZ_COMMON.moveFile(result_file, EPGCOMPO_file.getPath()+"/SUCCESS/" +  IFuncDate.getSystemDate().substring(0, 6), EPGCOMPO_list[j].getName());
							}
							else {//전송실패
								BIZ_COMMON.moveFile(result_file, EPGCOMPO_file.getPath()+"/ERROR/" +  IFuncDate.getSystemDate().substring(0, 6), EPGCOMPO_list[j].getName());
								elem.clear();
								elem.add("9999");
								elem.add(CCS_ID);
								elem.add(CCS_ID);

								bts.QueryExecutePS(new IEIF_SVC_IFDET().QueryUpdate2(), elem);
							}
						}
					}
				}
			}
			pw.write("true");

		} else if(ID_CCS_IF.equals("CCS_IF001")) { 		// EPG전송

System.out.println("[" + IFuncDate.getSystemTime() + "] HttptoWas CCS_IF001 1-0 처리 호출 [EPG전송] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
System.out.println("index_str >>>" + index_str);
			if(index_str.substring(0, 1).equals("S")) { // 편성정보
System.out.println("[" + IFuncDate.getSystemTime() + "] HttptoWas CCS_IF001 1-1 편성정보 호출 [EPG전송] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

				biz_compo = new BIZ_COMPO();

				//편성정보 저장 위치
				file = new File(getPath() + "/webapps/dataFile/CCSFolder/SND/EPGCOMPO/");

				//폴더가 없을 경우 생성
				if(!file.exists()) {
					file.mkdirs();
				}


				// 편성정보 저장 파일
				resultFile= file.getPath() + File.separator + IFuncDate.getSystemDateTime() + "-"+ System.currentTimeMillis() + ".xml_" + CCS_ID;
				
				arr_str = IFuncStr.tokenAll(index_str, "_");//인덱스 파싱


				/*
				ManualReqeust시 파일생성 시간이 http 타임아웃을 초과하는 경우가 발생함.(5분)
				ManualRequest는 정해진 시간에 동작하므로 그 이전에 (1시간전)미리 파일을 생성해서 생성된 파일을 전달하도록 기능 추가
				*/

				manualFile = new File(getPath() + "/webapps/dataFile/CCSFolder/SND/EPGCOMPO_Manual/"+arr_str[2]+".xml");

				/*
				서비스ID별로 Request가 올 경우는 신규로 파일을 생성한다.
				*/
				if(CCS_ID.equals("ManualRequest") && manualFile.exists() && (arr_str.length == 3))
				{
System.out.println("[" + IFuncDate.getSystemTime() + "] HttptoWas 요청편성일자["+arr_str[2]+"] 기존파일로 전달 [EPG전송] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					//pw.write("07" + manualFile.toString());
					
					String line ="";
System.out.println("[" + IFuncDate.getSystemTime() + "] HttptoWas pw start >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					try{
						in= new BufferedReader(new InputStreamReader(new FileInputStream(manualFile), "UTF-8"));
						if(in != null){
							
							//sb = new StringBuilder();
							
							while( (line = in.readLine()) != null){
								pw.write(line + "\n");
								//sb.append(line);
							}
							//pw.write(sb.toString());
						}else{
							System.out.println("EPG_COMPO_XML File read error!!");
						}
System.out.println("[" + IFuncDate.getSystemTime() + "] HttptoWas pw end >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					}catch(Exception e){
						e.printStackTrace();
					}finally{
						if(in!=null){in.close();}
					}
					
					
				}else if(arr_str.length>2 && (arr_str[2].equals("20120511") || arr_str[2].equals("20120512") || arr_str[2].equals("20120513") || arr_str[2].equals("20120514")) && arr_str[3].equals("326")){
						System.out.println("temp");
						pw.write("07" + new File(getPath() + "/webapps/dataFile/CCSFolder/SND/EPGCOMPO_Manual/20120511_326.xml").toString());
				}else{
					try {
						gConn = IDBConnection.getConnection(BIZ_COMMON.CCS_DS_NAME);
						
						if(arr_str.length == 4){
							result = biz_compo.bizCompXml(gConn, "EPG", arr_str[3], arr_str[2], resultFile, ""); //arr_str[3]:ID_SVC
						}else{
							result = biz_compo.bizCompXml(gConn, "EPG", "", arr_str[2], resultFile, "");
						}

						System.out.println("result>>>>"+result.substring(0, 2));
						
						if(result.substring(0, 2).equals("07")) { // 편성정보 생성성공
							
							String line ="";

							try{
								in= new BufferedReader(new InputStreamReader(new FileInputStream(resultFile), "UTF-8"));
								if(in != null){
									while( (line = in.readLine()) != null){
										pw.write(line + "\n");
									}
								}else{
									System.out.println("EPG_COMPO_XML File read error!!");
								}
								
							}catch(Exception e){
								e.printStackTrace();
							}finally{
								if(in!=null){in.close();}
							}
							

						}else {
							pw.write("08");
						}
						gConn.commit();

	System.out.println("[" + IFuncDate.getSystemTime() + "] HttptoWas CCS_IF001 1-2 편성정보 처리 [EPG전송] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					} catch (Exception e) {
						try {
						  e.printStackTrace();
						  gConn.rollback();
						}
						catch (Exception e1) {}
					}
					finally {
						IDBConnection.setAutoCommit(gConn, true);
						IDBConnection.close(gConn);
					}
				}


			} else if(index_str.substring(0, 1).equals("C")) { // 채널정보
System.out.println("[" + IFuncDate.getSystemTime() + "] HttptoWas CCS_IF001 1-1 채널정보 호출 [EPG전송] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				biz_ch = new BIZ_CH();

				file = new File(getPath() + "/webapps/dataFile/CCSFolder/SND/EPGCH/");

				if(!file.exists()) {
					file.mkdirs();
				}

				arr_str = IFuncStr.tokenAll(index_str, "_");//인덱스 파싱
				resultFile = file.getPath() + File.separator + IFuncDate.getSystemDateTime()  + "-"+ System.currentTimeMillis() + ".xml_" + CCS_ID;
				if(arr_str.length>2){
					result = biz_ch.bizChXml("EPG", resultFile,arr_str[2]);
				}else{
					result = biz_ch.bizChXml("EPG", resultFile);					
				}
					
				System.out.println("result>>>>"+result.substring(0, 2));

				if(result.substring(0, 2).equals("07")) { // 채널정보 생성성공
					String line ="";

					try{
						in= new BufferedReader(new InputStreamReader(new FileInputStream(resultFile), "UTF-8"));
						if(in != null){
							while( (line = in.readLine()) != null){
								pw.write(line + "\n");
							}
						}else{
							System.out.println("EPG_CH_XML File read error!!");
						}
						
					}catch(Exception e){
						e.printStackTrace();
					}finally{
						if(in!=null){in.close();}
					}
				}
				else {
					pw.write("08");
				}
System.out.println("[" + IFuncDate.getSystemTime() + "] HttptoWas CCS_IF001 1-2 채널정보 처리 [EPG전송] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			}
		} else if(ID_CCS_IF.equals("CCS_IF002")) { // DBS처리
System.out.println("[" + IFuncDate.getSystemTime() + "] HttptoWas CCS_IF002 1-0 처리 호출 [DBS처리] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			aFilePath = "";
			if ( request.getParameter("aFilePath") != null ) {//DBS 수신파일 저장 위치
				aFilePath = IFuncStr.no(request.getParameter("aFilePath").trim());
			}
			dbs = new BIZ_DBS();

			try {
		    	gConn = IDBConnection.getConnection(BIZ_COMMON.CCS_DS_NAME);
System.out.println("[" + IFuncDate.getSystemTime() + "] HttptoWas CCS_IF002 1-1 처리 성공 [DBS처리] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				isTF = dbs.getDbs(gConn, aFilePath);

				if(isTF) {
					pw.write("true");
				}
				else {
					pw.write("false");
				}
				gConn.commit();

			} catch (Exception e) {
System.out.println("[" + IFuncDate.getSystemTime() + "] HttptoWas CCS_IF002 1-2 처리 실패 [DBS처리] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				try {
				  e.printStackTrace();
				  gConn.rollback();
				}
				catch (Exception e1) {}
			}
			finally {
System.out.println("[" + IFuncDate.getSystemTime() + "] HttptoWas CCS_IF002 1-2 처리 성공 [DBS처리] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				IDBConnection.setAutoCommit(gConn, true);
				IDBConnection.close(gConn);
			}

		} else if(ID_CCS_IF.equals("CCS_IF009")) { // 고객인증요청

long howLong= System.currentTimeMillis();

System.out.println("[" + IFuncDate.getSystemTime() + "] HttptoWas STEP [VASAUTH]>>>>>>>>>>>>>>>>>>>>>>>>");

			try {
			    	gConn = IDBConnection.getConnection(BIZ_COMMON.CCS_DS_NAME);
System.out.println("[" + IFuncDate.getSystemTime() + "] HttptoWas CCS_IF009 1-1 DB 연결 성공 [고객인증요청] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				aFilePath = "";
				if ( request.getParameter("aFilePath") != null ) {//DBS 수신파일 저장 위치
					aFilePath = IFuncStr.no(request.getParameter("aFilePath").trim());
				}

				vasauth = new BIZ_VASAUTH();
				resultFile = vasauth.SetVASAuth(gConn, aFilePath);

				if(resultFile.equals("")) {
					pw.write("false");
				}
				else {
					pw.write(resultFile);
				}
			} catch (Exception e) {
System.out.println("[" + IFuncDate.getSystemTime() + "] HttptoWas CCS_IF009 1-2 처리 실패 [고객인증요청] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				try {
				  e.printStackTrace();
				}
				catch (Exception e1) {}
			}
			finally {
				IDBConnection.close(gConn);
long howLong2= System.currentTimeMillis();
System.out.println(">>> VASSTEP 1 : VASAUTH 접수시간[" + howLong + "] : 처리시간["+(howLong2-howLong)+"]");
System.out.println("[" + IFuncDate.getSystemTime() + "] HttptoWas CCS_IF009 1-2 처리 종료 [고객인증요청] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			}
		}
	}
	catch (Exception e) {
		try {
		  e.printStackTrace();
		}
		catch (Exception e1) {}
	}
	finally {
		if(pw != null) {
			pw.close();
			pw = null;
		}
System.out.println("[" + IFuncDate.getSystemTime() + "] HttptoWas 정상 종료>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
%>
<body bgcolor=#D5D5CD marginwidth=0 marginheight=0 leftmargin=0>
</body>
</html>
