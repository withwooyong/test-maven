<%--
/*----------------------------------------------------------
/*   �ý��۸� : ExBill SMS (Service Management System) Ver 1.0
/*   �� �� �� : CCS ���ø����̼� �������� HTTP �۽����� �����Ͽ� ������
/*           ȣ���ϴ� ����Ͻ� ���� ����
/*   ����ȣ : CcsToWas.JSP
/*   ���        : CCS�� SOAP ������ ��� ó���� (DBS ����, EPG STEP2 ��û, VAS ������û)
/*   �� �� �� : ������
/*   �� �� �� : 2007/09/19
/*----------------------------------------------------------
/* ( ����ȯ�� )
/*   �ü�� : Unix, Windows, Linux
/*   DataBase : Oracle
/*   ���ߵ��� : Eclipse 3.2, Editor
/*----------------------------------------------------------
/* ( �����̷� )
/*
/*   ��������    ������       ��     ��     ��      ��
/*   --------    ------  -----------------------------------
/*
/*
/*----------------------------------------------------------
--%>
<%@ page contentType="text/html; charset=euc-kr" %>
<%@ page language="java" import="com.imnetpia.exbill.common.*,java.io.*,java.util.Vector, java.sql.*, java.util.*"%>
<%@ page import="com.imnetpia.exbill.service.biz.*, com.imnetpia.exbill.service.entity.*"%>
<jsp:useBean id="bts" scope="session" class="com.imnetpia.exbill.EXServer" />
<%!
	//������, ������ exbill �⺻ ��ġ ���
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
System.out.println("[" + IFuncDate.getSystemTime() + "] SoapToWas ���� >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

Connection gConn = null;
	Vector elem = new Vector();

	BIZ_COMPO biz_compo = null;
	BIZ_CH biz_ch = null;
	BIZ_DBS dbs = null;
	BIZ_VASAUTH vasauth = null;

	File file = null;
	File manualFile = null; //20110221 cjyu ManualRequestó���� ���� ���ϰ�ü

	PrintWriter  pw = null;	//������ �۽� ������

	String ID_CCS_IF = "";//�޼ҵ� ���� ID
	String CCS_ID = "";//ccs_id
	String resultFile= "";
	String[] arr_str = null;
	String result = "";//ó�����
	String Result = "";// ���۰��
	boolean isTF = false;


	//20080521
	//set dsname
	try{
			bts.setDS_NAME(BIZ_COMMON.CCS_DS_NAME);
	}catch(Exception e){
		e.printStackTrace();
	}
	/*
	�ε��� ����
	ä������ : "C(ä��)" + "_"  + "P (Process)" + yyyymmddhh24miss
	������ : "S(������)" + "_" + "R (Request)" + yyyymmddhh24miss + "_" + �����쳯¥(yyyymmdd)
	*/
	String index_str = ""; //�ε��� ����
	if ( request.getParameter("index") != null ) {
		index_str = IFuncStr.no(request.getParameter("index").trim());
	}

	String aFilePath = "";
	if ( request.getParameter("aFilePath") != null ) {//�������� ���� ��ġ
		aFilePath = IFuncStr.no(request.getParameter("aFilePath").trim());
	}
	if ( request.getParameter("ID_CCS_IF") != null ) {
		ID_CCS_IF = IFuncStr.no(request.getParameter("ID_CCS_IF").trim());
	}
	if ( request.getParameter("CCS_ID") != null ) {
		CCS_ID = IFuncStr.no(request.getParameter("CCS_ID").trim());
	}
	if ( request.getParameter("Result") != null ) {
		Result = IFuncStr.no(request.getParameter("Result").trim());
	}

System.out.println("[" + IFuncDate.getSystemTime() + "] SoapToWas CALL >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + ID_CCS_IF);

	// �⺻ ���ؼ� ����
	try {
		pw = response.getWriter();	// ������ �۽� ������

		if(!Result.equals("")) {	// ä��/ä���� ���� ���

			if(!CCS_ID.equals("")) {
				String result_file = "";
				File EPGCH_file = new File(getPath() + "/webapps/dataFile/CCSFolder/SND/EPGCH/");
				File EPGCOMPO_file = new File(getPath() + "/webapps/dataFile/CCSFolder/SND/EPGCOMPO/");

				//ä�� ���� �̵�
				File[] EPGCH_list = EPGCH_file.listFiles();
				for(int i=0;i<EPGCH_list.length;i++) {// �������� ã
					String name = EPGCH_list[i].getName();
					if(name.length() > 10) {

						if(name.substring(name.length() - 10, name.length()).equals(CCS_ID)) {
							result_file = EPGCH_list[i].getPath();
							if(Result.equals("0")) {//���ۼ���
								BIZ_COMMON.moveFile(result_file, EPGCH_file.getPath()+"/SUCCESS/" +  IFuncDate.getSystemDate().substring(0, 6), EPGCH_list[i].getName());
							}
							else {//���۽���
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
				//ä�� �� ���� �̵�
				File[] EPGCOMPO_list = EPGCOMPO_file.listFiles();
				for(int j=0;j<EPGCOMPO_list.length;j++) {//�������� ã��
					String name = EPGCOMPO_list[j].getName();
					if(name.length() > 10) {
						if(name.substring(name.length() - 10, name.length()).equals(CCS_ID)) {
							result_file = EPGCOMPO_list[j].getPath();
							if(Result.equals("0")) {//���ۼ���
								BIZ_COMMON.moveFile(result_file, EPGCOMPO_file.getPath()+"/SUCCESS/" +  IFuncDate.getSystemDate().substring(0, 6), EPGCOMPO_list[j].getName());
							}
							else {//���۽���
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

		} else if(ID_CCS_IF.equals("CCS_IF001")) { 		// EPG����

System.out.println("[" + IFuncDate.getSystemTime() + "] SoapToWas CCS_IF001 1-0 ó�� ȣ�� [EPG����] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
System.out.println("index_str >>>" + index_str);
			if(index_str.substring(0, 1).equals("S")) { // ������
System.out.println("[" + IFuncDate.getSystemTime() + "] SoapToWas CCS_IF001 1-1 ������ ȣ�� [EPG����] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

				biz_compo = new BIZ_COMPO();

				//������ ���� ��ġ
				file = new File(getPath() + "/webapps/dataFile/CCSFolder/SND/EPGCOMPO/");

				//������ ���� ��� ����
				if(!file.exists()) {
					file.mkdirs();
				}


				// ������ ���� ����
				resultFile= file.getPath() + File.separator + IFuncDate.getSystemDateTime() + "-"+ System.currentTimeMillis() + ".xml_" + CCS_ID;
				
				arr_str = IFuncStr.tokenAll(index_str, "_");//�ε��� �Ľ�


				/*
				20110221 cjyu
				ManualReqeust�� ���ϻ��� �ð��� http Ÿ�Ӿƿ��� �ʰ��ϴ� ��찡 �߻���.(5��)
				ManualRequest�� ������ �ð��� �����ϹǷ� �� ������ (1�ð���)�̸� ������ �����ؼ� ������ ������ �����ϵ��� ��� �߰�
				*/

				manualFile = new File(getPath() + "/webapps/dataFile/CCSFolder/SND/EPGCOMPO_Manual/"+arr_str[2]+".xml");

				/*
				20111014 cjyu
				����ID���� Request�� �� ���� �űԷ� ������ �����Ѵ�.
				*/
				if(CCS_ID.equals("ManualRequest") && manualFile.exists() && (arr_str.length == 3))
				{
System.out.println("[" + IFuncDate.getSystemTime() + "] SoapToWas ��û������["+arr_str[2]+"] �������Ϸ� ���� [EPG����] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					pw.write("07" + manualFile.toString());
				}else if(arr_str.length>2 && (arr_str[2].equals("20120511") || arr_str[2].equals("20120512") || arr_str[2].equals("20120513") || arr_str[2].equals("20120514")) && arr_str[3].equals("326")){
						System.out.println("temp");
						pw.write("07" + new File(getPath() + "/webapps/dataFile/CCSFolder/SND/EPGCOMPO_Manual/20120511_326.xml").toString());
				}else{
					try {
//				    	gConn = IDBConnection.getConnection();
						gConn = IDBConnection.getConnection(BIZ_COMMON.CCS_DS_NAME);
						
						if(arr_str.length == 4){
							result = biz_compo.bizCompXml(gConn, "EPG", arr_str[3], arr_str[2], resultFile, ""); //arr_str[3]:ID_SVC
						}else{
							result = biz_compo.bizCompXml(gConn, "EPG", "", arr_str[2], resultFile, "");
						}

						if(result.substring(0, 2).equals("07")) { // ������ ��������
							pw.write("07" + resultFile);
						}
						else {
							pw.write("08");
						}
						gConn.commit();

	System.out.println("[" + IFuncDate.getSystemTime() + "] SoapToWas CCS_IF001 1-2 ������ ó�� [EPG����] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
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


			} else if(index_str.substring(0, 1).equals("C")) { // ä������
System.out.println("[" + IFuncDate.getSystemTime() + "] SoapToWas CCS_IF001 1-1 ä������ ȣ�� [EPG����] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				biz_ch = new BIZ_CH();

				file = new File(getPath() + "/webapps/dataFile/CCSFolder/SND/EPGCH/");

				if(!file.exists()) {
					file.mkdirs();
				}

				arr_str = IFuncStr.tokenAll(index_str, "_");//�ε��� �Ľ�
				resultFile = file.getPath() + File.separator + IFuncDate.getSystemDateTime()  + "-"+ System.currentTimeMillis() + ".xml_" + CCS_ID;
				if(arr_str.length>2){
					result = biz_ch.bizChXml("EPG", resultFile,arr_str[2]);
				}else{
					result = biz_ch.bizChXml("EPG", resultFile);					
				}
					


				if(result.substring(0, 2).equals("07")) { // ä������ ��������
					pw.write("07" + resultFile);
				}
				else {
					pw.write("08");
				}
System.out.println("[" + IFuncDate.getSystemTime() + "] SoapToWas CCS_IF001 1-2 ä������ ó�� [EPG����] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			}
		} else if(ID_CCS_IF.equals("CCS_IF002")) { // DBSó��
System.out.println("[" + IFuncDate.getSystemTime() + "] SoapToWas CCS_IF002 1-0 ó�� ȣ�� [DBSó��] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			aFilePath = "";
			if ( request.getParameter("aFilePath") != null ) {//DBS �������� ���� ��ġ
				aFilePath = IFuncStr.no(request.getParameter("aFilePath").trim());
			}
			dbs = new BIZ_DBS();

			try {

//		    	gConn = IDBConnection.getConnection();
		    	gConn = IDBConnection.getConnection(BIZ_COMMON.CCS_DS_NAME);
System.out.println("[" + IFuncDate.getSystemTime() + "] SoapToWas CCS_IF002 1-1 ó�� ���� [DBSó��] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				isTF = dbs.getDbs(gConn, aFilePath);

				if(isTF) {
					pw.write("true");
				}
				else {
					pw.write("false");
				}
				gConn.commit();

			} catch (Exception e) {
System.out.println("[" + IFuncDate.getSystemTime() + "] SoapToWas CCS_IF002 1-2 ó�� ���� [DBSó��] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				try {
				  e.printStackTrace();
				  gConn.rollback();
				}
				catch (Exception e1) {}
			}
			finally {
System.out.println("[" + IFuncDate.getSystemTime() + "] SoapToWas CCS_IF002 1-2 ó�� ���� [DBSó��] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				IDBConnection.setAutoCommit(gConn, true);
				IDBConnection.close(gConn);
			}

		} else if(ID_CCS_IF.equals("CCS_IF009")) { // ��������û

long howLong= System.currentTimeMillis();

System.out.println("[" + IFuncDate.getSystemTime() + "] SoapToWas STEP [VASAUTH]>>>>>>>>>>>>>>>>>>>>>>>>");

			try {
//				gConn = IDBConnection.getConnection();
			    	gConn = IDBConnection.getConnection(BIZ_COMMON.CCS_DS_NAME);
System.out.println("[" + IFuncDate.getSystemTime() + "] SoapToWas CCS_IF009 1-1 DB ���� ���� [��������û] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				aFilePath = "";
				if ( request.getParameter("aFilePath") != null ) {//DBS �������� ���� ��ġ
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
System.out.println("[" + IFuncDate.getSystemTime() + "] SoapToWas CCS_IF009 1-2 ó�� ���� [��������û] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				try {
				  e.printStackTrace();
				}
				catch (Exception e1) {}
			}
			finally {
				IDBConnection.close(gConn);
long howLong2= System.currentTimeMillis();
System.out.println(">>> VASSTEP 1 : VASAUTH �����ð�[" + howLong + "] : ó���ð�["+(howLong2-howLong)+"]");
System.out.println("[" + IFuncDate.getSystemTime() + "] SoapToWas CCS_IF009 1-2 ó�� ���� [��������û] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
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
System.out.println("[" + IFuncDate.getSystemTime() + "] SoapToWas ���� ����>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
%>
<body bgcolor=#D5D5CD marginwidth=0 marginheight=0 leftmargin=0>
</body>
</html>
