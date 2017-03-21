[toc]
### Oracle To MonogDB Interface
#### 채널메타 등록 프로세스  
 - 채널메타 등록 시 호출되는 Interface

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|insertChannel|getChannelCategoryInfo|channelMongoDAO.insertChannel|TCM_CHANNEL<br>TCM_CATE_CMS<br>TCM_CHANNEL_SCR|cms_channel|-|

#### 채널메타 수정 프로세스
 - 채널메타 등록 시 호출되는 Interface

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|updateChannel|getChannelCategoryImageInfo|channelMongoDAO.updateChannel<br>|TCM_CHANNEL<br>TCM_CATE_CMS<br>TCM_CHANNEL_SCR<br>TCM_IMG IMG|cms_channel<br>cms_schedule<br>cms_vod<br>cms_vod_episode<br>cms_clip|채널메타 변경 시 cms_channel 컬렉션 업데이트 후 embedded 컬렉션을 업데이트 한다.|


#### 채널메타 삭제 프로세스
 - 채널메타 삭제 시 호출되는 Interface

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|deleteChannel|-|channelMongoDAO.deleteChannel|-|cms_channel|-|

#### EPG 채널 batch  프로세스
 - epg으로부터 신규채널 등록 시 호출되는 Interface

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|insertBatchChannelFromEpg|getChannelCategoryListFromEpg|channelMongoDAO.insertChannelBatch|TCM_CHANNEL<br>TCM_CATE_CMS<br>TCM_CHANNEL_SCR<br>TCM_IMG|cms_channel|-|

#### EPG 편성 스케줄 batch 프로세스
 - epg으로부터 편성 스케줄 정보를 수신 후 호출되는 Interface  

~~~~
 - 정기편성은 00~01시, 12~13시 두번이며, 긴급편성은 수시로 발생한다.  
 - Oracle Query 상의 정렬은 삭제, 수정, 등록 목록을 쉽게 추출하기 위하여 추가하였고, 해당 row Data는 MongoDB 처리시 삭제, 수정, 등록순으로 처리를 해야 한다.   
 - 편성삭제는 Oralce 상에서는 필드 업데이트이나 MongoDB는 row data를 삭제함에 따라 삭제목록은 채널코드, 방송날짜, 방송시작 시간이 일치하는 경우만 삭제를 진행한다.  
 - 편성수정은 채널코드, 방송날짜가 일치하고 해당 조건 ( 유입 방송시작 시간 <= 기존 데이터 방송시작 시간 < 유입 방송종료시간  OR 유입 방송시작시간 < 기존 데이터 방송종료 시간 <= 유입 방송종료 시간)에 따라 이전 편성정보를 삭제하고 새롭게 추가한다.  
 - 편성등록은 기존 데이터를 채널코드, 방송날짜, 방송시작시간, 방송종료시간이 일치하지 않으면 등록처리하고 존재하는 경우 삭제 후 재등록 처리한다.  
~~~~

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|insertBatchScheduleFromEpg|getEpgScheduleListAll|scheduleMongoDAO.deleteBatchSchedule<br>scheduleMongoDAO.updateBatchSchedule<br>scheduleMongoDAO.insertBatchScheduleEpg|TCM_CHANNEL<br>TCM_SCHEDULE<br>TCM_CHANNEL_URL<br>TCM_CHANNEL_SCR<br>TCM_SERVICE_SITE|cms_schedule|MongoDB 처리순서 삭제, 수정, 등록 목록순으로 진행.|



#### 프로그램메타 등록 프로세스
- 프로그램메타 등록 시 호출되는 Interface

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|insertProgram|getProgramImageList|programMongoDAO.insertProgram|TCM_PROGRAM<br>TCM_IMG<br>TCM_PEOPLE<br>TCM_MULTI_CATE_MGMT<br>TCM_CATE_CMS|cms_program|-|

#### 프로그램메타 수정 프로세스
- 프로그램메타 수정, 이미지 변경 시 호출되는 Interface
: 프로그램메타 변경은 해당 프로그램 정보가 embeded 되어 있는 컬렉션을 조회 후, cms_schedule, cms_vod, cms_vod_episode, cms_clip을 수정한다.

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|updateProgram|getProgramImageList|programMongoDAO.updateProgram|TCM_PROGRAM<br>TCM_IMG<br>TCM_PEOPLE<br>TCM_MULTI_CATE_MGMT<br>TCM_CATE_CMS|cms_program<br>cms_schedule<br>cms_vod<br>cms_vod_episode<br>cms_clip|-|

#### 프로그램메타 삭제 프로세스
- 프로그램메타 삭제 시 호출되는 Interface

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|deleteProgram|-|programMongoDAO.deleteProgram|-|cms_program|-|

#### 에피소드메타 등록 프로세스
- 에피소드메타 등록 시 호출되는 Interface

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|updateEpisode|getEpisodeImageList|episodeMongoDAO.insertEpisode|TCM_EPISODE<br>TCM_IMG<br>TCM_VOD_FILE<br>TCM_VOD_FILE_SCR<br>TCM_PEOPLE<br>TCM_CATE_CMS|cms_episode|-|

#### 에피소드메타 수정 프로세스
- 에피소드메타 수정, 이미지 변경 시 호출되는 Interface
: 에피소드메타 변경은 해당 에피소드 정보가 embeded 되어 있는 컬렉션을 조회 후, cms_schedule, cms_vod, cms_vod_episode, cms_clip을 수정한다.

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|insertEpisode|getEpisodeImageList|episodeMongoDAO.updateEpisode|TCM_EPISODE<br>TCM_IMG<br>TCM_VOD_FILE<br>TCM_VOD_FILE_SCR<br>TCM_PEOPLE<br>TCM_CATE_CMS|cms_episode<br>cms_schedule<br>cms_vod<br>cms_vod_episode<br>cms_clip|-|

#### 에피소드메타 삭제 프로세스
- 에피소드메타 삭제 시 호출되는 Interface

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|deleteEpisode|-|episodeMongoDAO.deleteEpisode|-|cms_episode|-|

#### 영화메타 등록 프로세스
- 영화메타 등록 시 호출되는 Interface

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|insertMovie|getMovieImageList|movieMongoDAO.insertMovie|TCM_MOVIE<br>TCM_IMG<br>TCM_PEOPLE<br>TCM_VOD_FILE<br>TCM_CATE_CMS<br>TCM_MULTI_CATE_MGMT|cms_movie|-|


#### 영화메타 수정 프로세스
- 영화메타 수정, 이미지 변경 시 호출되는 Interface
: 영화메타 변경은 해당 에피소드 정보가 embeded 되어 있는 컬렉션을 조회 후, cms_schedule, cms_vod, cms_vod_movie, cms_clip을 수정한다.

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|updateMovie|getMovieImageList|movieMongoDAO.updateMovie|TCM_MOVIE<br>TCM_IMG<br>TCM_PEOPLE<br>TCM_VOD_FILE<br>TCM_CATE_CMS<br>TCM_MULTI_CATE_MGMT|cms_movie<br>cms_schedule<br>cms_vod<br>cms_clip|-|


#### 영화메타 삭제 프로세스
- 영화메타 삭제 시 호출되는 Interface

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|deleteMovie|-|movieMongoDAO.deleteMovie|-|cms_movie|-|

#### 스케줄 편성 등록 프로세스
- 스케줄 편성 시 호출되는 Interface
 : 관리자가 임의의 편성 시 이용되는 Interface


|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|insertSchedule|getEpgScheduleListAll|scheduleMongoDAO.insertSchedule<br>channelMongoDAO.getChannelInfo<br>movieMongoDAO.getMovieInfo<br>clipMongoDAO.getClipInfo<br>advertisingMongoDAO.getAdvertisingInfo<br>programMongoDAO.getProgramInfo<br>episodeMongoDAO.getEpisodeInfo|TCM_CHANNEL<br>TCM_SCHEDULE<br>TCM_CHANNEL_URL<br>TCM_CHANNEL_SCR<br>TCM_SERVICE_SITE|cms_schedule|-|

#### 스케줄 편성 수정 프로세스
- 스케줄 편성 수정(편성 시간 조정, 스크린별 노출, 채널 블럭 등) 시 호출되는 Interface

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|updateSchedule|getEpgScheduleListAllBySEQ|scheduleMongoDAO.updateSchedule<br>channelMongoDAO.getChannelInfo<br>movieMongoDAO.getMovieInfo<br>clipMongoDAO.getClipInfo<br>advertisingMongoDAO.getAdvertisingInfo<br>programMongoDAO.getProgramInfo<br>episodeMongoDAO.getEpisodeInfo|TCM_CHANNEL<br>TCM_SCHEDULE<br>TCM_CHANNEL_URL<br>TCM_CHANNEL_SCR<br>TCM_SERVICE_SITE|cms_schedule|-|

#### 스케줄 편성 삭제 프로세스
- 스케줄 삭제 시 호출되는 Interface

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|deleteSchedule|getEpgScheduleListAll|scheduleMongoDAO.deleteSchedule|TCM_CHANNEL<br>TCM_SCHEDULE<br>TCM_CHANNEL_URL<br>TCM_CHANNEL_SCR<br>TCM_SERVICE_SITE|cms_schedule|-|

#### 프로그램/영화 VOD 노출관리 프로세스
- 프로그램/영화 VOD 노출관리 시 호출되는 Interface
: Oracle 의 VOD 정보와 MongoDB의 노출에 따라 등록, 수정, 삭제가 이루어지며 TCM_SERVICE_SITE 테이블 정보에 컨텐츠 코드값의 존재에 따라 MongoDB의 해당 정보를 삭제 또는 수정 프로세스를 진행한다. 

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|upsertVod|getProgramVodScreenList<br>getMovieVodScreenList|vodMongoDAO.insertVod<br>vodMongoDAO.updateVod<br>vodMongoDAO.deleteVod|-|cms_vod<br>cms_vod_episode<br>cms_vod_movie|-|

#### CLIP 노출관리 프로세스
- 프로그램/영화 CLIP 노출관리 시 호출되는 Interface
: Oracle 의 CLIP 정보와 MongoDB의 노출에 따라 등록, 수정, 삭제가 이루어지며 TCM_SERVICE_SITE 테이블 정보에 컨텐츠 코드값의 존재에 따라 MongoDB의 해당 정보를 삭제 또는 수정 프로세스를 진행한다. 

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|upsertClip|ironman.getClipServiceCount<br>getClipScreenList|clipMongoDAO.insertClip<br>clipMongoDAO.updateClip<br>clipMongoDAO.deleteClip|TCM_SHORTCLIP<br>TCM_VOD_FILE<br>TCM_VOD_FILE_SCR<br>TCM_VOD_FILE_ORIGIN<br>TCM_SERVICE_SITE<br>TCM_VOD_FILE_MTS<br>TCM_VOD_FILE_DRM<br>TLG_RANK_SHORTCLIP<br>TLG_SHORTCLIP_TOTAL_VIEW<br>TCM_CATE_CMS|cms_clip|-|


#### 파일 LIVE 편성 스케줄 관리 프로세스
- 파일 라이브 편성 등록, 수정 시 호출되는 Interface
: 파일 라이브는 원본 테이블이 없고, 서비스 테이블(TCM_SERV_SCHEDULE)에만 존재한다. 또한, 스크린별 노출정책에 따라 스크린별 편성시작 시간, 종료시간 파일의 특성상 약간의 차이가 있다. 기존의 EPG 등록, 수정 로직과는 달리 이전의 항목을 모두 삭제하고 새롭게 등록처리하는 프로세스로 처리해야 정확한 편성이 가능하다.

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|upsertFileLiveSchedule|getFileLiveList|scheduleMongoDAO.insertFileLiveSchedule<br>scheduleMongoDAO.updateScheduleScreen|TCM_SERV_SCHEDULE|cms_schedule|-|

#### 파일 LIVE 편성 삭제 프로세스
- 파일 라이브 편성 삭제 시 호출되는 Interface
: 파일 라이브는 Oracle DB 상에서 서비스 테이블에만 존재함에 따라 원본 테이블이 없어서 삭제 시 미리 해당 항목을 조회 후 Oracle 성공 이후에 MongoDB에서 삭제 목록을 전달 받아 삭제 처리하는 프로세스를 취한다. 스크린별 삭제 이후에 편성 스크린이 없는 경우 편성 자체를 삭제 처리한다.

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|deleteFileLive|getFileLiveList|scheduleMongoDAO.deleteScheduleScreen|TCM_SERV_SCHEDULE|cms_schedule|-|

#### 편성스케줄 프로그램/시간대 BLOCK 프로세스
- 편성스케줄 프로그램, 시간대 BLOCK 시 호출되는 Interface
: 파일라이브와 일반 편성의 조정 두곳에서 모두 발생되므로, 해당 Interface는 서비스 테이블에서 조회 후 처리한다.

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|updateScheduleBlock|getScheduleListByBroadcastTime<br>getScheduleListByBlockSeq|scheduleMongoDAO.updateBatchSchedule|TCM_CHANNEL<br>TCM_SERV_SCHEDULE<br>TCM_CHANNEL_URL|cms_schedule|-|

#### 카테고리 수정 프로세스
- 카테고리 수정 시 호출되는 Interface
: CMS 상에서 공통으로 사용하는 코드체계에서 명칭변경 시 호출되는 Interface이다. 해당 메소드는 해당 모든 컬렉션의 변경이 발생하므로 위험요소가 존재한다. 이에 따라 신규 코드를 할당받아 컨텐츠에 맵핑하는 것을 권고한다. DB Ref 방식은 정렬 조건이 아닌 검색 조건상에서는 처리가 가능하기 때문에 현재의 category embeded 방식에서 DB Ref 방식으로 변경하는 것을 권고한다. <b>*매우 위험한 Interface*</b>

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|updateCmsCategory|-|channelMongoDAO.updateCmsCategory|-|cms_channel<br>cms_episode<br>cms_movie<br>cms_vod<br>cms_vod_episode<br>cms_vod_movie<br>cms_clip|-|


#### 에피소드 프로그램 변경 프로세스
- 에피소드의 프로그램을 변경 시 호출되는 Interface
: 에피소드의 프로그램을 변경 후 cms_schedule, cms_vod, cms_vod_episode, cms_clip순으로 프로그램을 수정한다.

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|updateEpisodeChangeProgram|-|episodeMongoDAO.updateEpisode<br>episodeMongoDAO.updateEpisodeChangeProgram|-|cms_episode<br>cms_schedule<br>cms_vod<br>cms_vod_episode<br>cms_clip|-|

#### EPG 프로그램/에피소드 관리 프로세스
- EPG에서 유입되는 프로그램 생성 시 호출되는 Interface
: 신규프로그램 생성 시에는 기본으로 에피소드는 빈 메타 형식으로 생성을 한다.

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|insertBatchProgramFromEpg|getProgramImageList<br>getEpisodeImageList|programMongoDAO.insertEpgProgram<br>episodeMongoDAO.insertEpgEpisode|TCM_PROGRAM<br>TCM_IMG<br>TCM_PEOPLE<br>TCM_MULTI_CATE_MGMT<br>TCM_CATE_CMS<br>TCM_EPISODE<br>TCM_VOD_FILE<br>TCM_VOD_FILE_SCR|cms_program<br>cms_episode|-|

#### EPG 에피소스 관리 프로세스
- EPG에서 유입되는 에피소는 생성 시 호출되는 Interface

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|insertBatchEpisodeFromEpg|getEpisodeImageList|episodeMongoDAO.insertEpgEpisode|TCM_EPISODE<br>TCM_IMG<br>TCM_VOD_FILE<br>TCM_VOD_FILE_SCR<br>TCM_PEOPLE<br>TCM_CATE_CMS|cms_episode|-|


#### 방송주소 변경 프로세스
- 편성 스케줄 stream url 변경 시 호출되는 Interface

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|updateChannelBroadUrl|getChannelBroadUrlList<br>getChannelSupportInfoList|channelMongoDAO.updateChannelBroadUrl|TCM_CHANNEL<br>|cms_schedule<br>TCM_CHANNEL_URL<br>TCM_CHANNEL_SCR|-|

#### 광고 메타 관리 프로세스
- 광고 메타 등록, 수정, 삭제 시 호출되는 Interface
: 광고가 스케줄에 편성이 된 경우 해당 데이터를 업데이트 처리한다.

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|upsertAdvertising|getAdScreenListCount<br>getAdScreenList|advertisingMongoDAO.insertAdvertising<br>advertisingMongoDAO.updateAdvertising<br>advertisingMongoDAO.deleteAdvertising|TCM_AD<br>TCM_VOD_FILE<br>TCM_VOD_FILE_SCR<br>TCM_VOD_FILE_ORIGIN<br>TCM_SERIES_DETAIL<br>TCM_PEOPLE|cms_ad<br>cms_schedule|-|

#### 국내허용 IP 등록 프로세스
- 국내 허용 IP 등록 시 호출되는 Interface
: 국내 허용 IP는 수정 프로세스가 없고, 삭제 후 재등록 처리한다.

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|insertInternalIP|getInternalIp|ipMongoDAO.insertInternalIp|TMB_INTERNAL_IP|member_internal_ip|-|

#### 국내허용 IP 삭제 프로세스
- 국내 허용 IP 삭제 시 호출되는 Interface
: 국내 허용 IP는 수정 프로세스가 없고, 삭제 후 재등록 처리한다.

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|deleteInternalIP|-|ipMongoDAO.deleteInternalIp|-|member_internal_ip|-|

#### VOD/CLIP 이력관리 프로세스
- 프로그램/영화 VOD, clip 등록, 수정, 삭제 시 로그를 기록한다.

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|insertDataLog|-|vodMongoDAO.insertMap|-|cms_log|-|

#### 플레이어 전용관주소 등록 프로세스
- 플레이어 전용관주소 등록 시 호출되는 Interface

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|insertPlayerRedirect|getPlayerRedirectInfo|playerRedirectMongoDAO.insertPlayerRedirectInfo|TSM_MICRO_REDIRECT|player_redirect|-|


#### 플레이어 전용관주소 수정 프로세스
- 플레이어 전용관주소 수정 시 호출되는 Interface
: MongoDB상에서 존재하면 업데이트 처리하고 없으면, 등록 처리하는 프로세스이다.

|method|sql_id|DAO|Oracle|MongoDB|etc|
|-----|-----|-----|-----|-----|
|updatePlayerRedirect|getPlayerRedirectInfo|playerRedirectMongoDAO.updatePlayerRedirectInfo|TSM_MICRO_REDIRECT|player_redirect|-|
