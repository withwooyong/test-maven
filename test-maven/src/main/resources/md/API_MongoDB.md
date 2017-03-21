[toc]
## 통합 API MongoDB 서버군 구성
### 버전
mongodb-linux-x86_64-2.4.10

### 서버구성
MongoDB는 master-slave-arbiter 로 형태의 replication으로 구성이 되어 있으며, arbiter를 제외한 나머지 7대의 서버에서 투표에 의하여 자동으로 master가 변경이 되고, 구성은 아래와 같다. 

|no|ip|port|web console port|Master/Slave/Arbiter|vote
|-----|-----|-----|-----|-----|-----|
|01|172.18.147.12|25017|26017|slave|true|
|02|172.18.147.13|25017|26017|slave|true|
|03|172.18.147.14|25017|26017|master|true|
|04|172.18.147.15|25017|26017|slave|true|
|05|172.18.147.16|25017|26017|slave|true|
|06|172.18.147.17|25017|26017|slave|false|
|07|172.18.147.18|25017|26017|slave|false|
|08|172.18.147.9|25017|26017|arbiter|-|

### DataBase 구성
|no|DataBase|Collection|Storage Size|Data Size|Total Size|Index|
|-----|-----|-----|-----|-----|-----|-----|
|01|im_service|16|33.23g|8.23g|59.92g|110|
|02|im_community|11|6.99g|6.37g|13.95g|40|
|03|im_manager|7|15.43m|4.8m|208m|12|
|04|im_micro|13|524.37m|389.59m|1.95g|33|
|05|im_operator|4|1.34m|1.22m|208m|7|

### Collection 구성
#### im_service
|no|collection|용도|index 구성|capped type|
|-----|-----|-----|-----|-----|
|01|cms_ad|광고 메타 데이타|cms_ad_idx1 {"ad_code":1}|false|
|02|cms_channel|채널 메타 데이타|cms_channel_idx_1 {"code":1}<br>cms_channel_idx_2 {"no":1}<br> cms_channel_idx_3 {"sort_no":1}<br>cms_channel_idx_4 {"name.ko":1}<br>cms_channel_idx_5 {"support_info":1}|false|
|03|cms_clip|클립 데이타|cms_clip_idx_1{"clip_code":1}<br>cms_clip_idx_2 {"vod_code":1}<br>cms_clip_idx_3 {"channel.code":1}<br>cms_clip_idx_5 {"episode.code":1}<br>cms_clip_idx_6 {"movie.code":1}<br>cms_clip_idx_7 {"insert_date":1}<br>cms_clip_idx_8 {"episode.broadcast_date":1}<br>cms_clip_idx_9 {"view_count.total":-1}<br>cms_clip_idx_10 {"view_count.day":1}<br>cms_clip_idx_11 {"view_count.week":1}<br>cms_clip_idx_12 {"clip_name.ko":1}<br>cms_clip_idx_13 {"broadcast_url.service_open_date":1}<br>cms_clip_idx_14 {"broadcast_url.service_open_date":1,"clip_code":1}<br>cms_clip_idx_15 {"category1_code":1}<br>cms_clip_idx_16{ "program.category1_code": 1}<br>cms_clip_idx_17 {"movie.category1_code": 1} <br>cms_clip_idx_18 {"asp_info.code": 1} <br>cms_clip_idx_19 {"clip_group": 1} <br>cms_clip_idx_20 {"initial_consonant": 1, "clip_name.ko": 1} <br>cms_clip_idx_24 {"program.display_category1": 1,"service_open_date": 1}<br>cms_clip_idx_21 {"service_open_date": -1, "program.category1_code": -1} <br>cms_clip_idx_22 {"program.code": 1, "clip_name.ko": 1} |X|
|04|cms_episode|에피소드 메타 데이타|cms_episode_idx_01{"code":-1}|false|
|05|cms_live|시청률 데이타|cms_live_idx_1 {"live_code": 1, "use_yn": 1 <br>cms_live_idx_2 {"use_yn": 1, "live_rating.realtime": -1 <br>cms_live_idx_3 {"use_yn": 1, "live_rating.day": -1 <br>cms_live_idx_4 {"use_yn": 1, "live_rating.week": -1 <br>cms_live_idx_5 {"use_yn": 1, "channel.name.ko": 1 <br>cms_live_idx_6 {"use_yn": 1, "schedule.channel.adult_yn": 1, "live_rating.realtime": -1 <br>cms_live_idx_7 {"use_yn": 1, "schedule.channel.category_code": 1, "live_rating.realtime": -1 |true|
|06|cms_log|vod 변경 이력|cms_log_idx01 {"date": 1,"media_type": 1}|true|
|07|cms_movie|영화 메타 데이타|cms_movie_idx_1{"code":1}|false|
|08|cms_program|프로그램 메타 데이터|cms_program_idx_1 {"code": 1}<br>cms_program_idx_2 {"insert_date": -1}<br>cms_program_idx_3 {"name.ko": -1}<br>cms_program_idx_4 {"channel_code": 1}<br>cms_program_idx_5 {"category1_code": 1}|false|
|09|cms_schedule|방송스켈줄 데이터|cms_schedule_idx_1 {"schedule_code": 1}<br>cms_schedule_idx_2 {"channel.code": 1}<br>cms_schedule_idx_3 {"program.code": 1}<br>cms_schedule_idx_4 {"episode.code": 1}<br>cms_schedule_idx_5 {"movie.code": 1}<br>cms_schedule_idx_6 {"schedule_code": 1,"broadcast_start_time": 1,"broadcast_end_time": 1}<br>cms_schedule_idx_7 {"broadcast_start_time": -1,"broadcast_date": -1,"schedule_code": 1}<br>cms_schedule_idx_8 {"broadcast_date": -1,"schedule_code": -1, "broadcast_start_time": 1}<br>cms_schedule_idx_11 {"ad.ad_code": 1}|false|
|10|cms_vod|프로그램, 영화 VOD 데이터|cms_vod_idx_01 {"vod_code": -1 }<br>cms_vod_idx_02 {"channel.code": 1 }<br>cms_vod_idx_03 {"program.code": 1 }<br>cms_vod_idx_04 {"program.category1_code": 1, "last_frequency_yn": -1 }<br>cms_vod_idx_21 {"vod_type": 1, "service_open_date": -1, "movie.release_date": -1 }<br>cms_vod_idx_05 {"episode.code": 1 }<br>cms_vod_idx_06 {"billing_package_id": 1 }<br>cms_vod_idx_07 {"last_frequency_yn": -1, "movie.scp_code": 1, "episode.scp_code": 1, "service_open_date": -1 }<br>cms_vod_idx_08 {"service_open_date": -1 }|false|
|11|cms_vod_episode|프로그램 VOD 데이터|cms_vod_episode_idx_01 {"vod_code": -1 }<br>cms_vod_episode_idx_02 {"channel.code": 1 }<br>cms_vod_episode_idx_03 {"program.code": 1 }<br>cms_vod_episode_idx_06 {"program.name.ko": 1 }<br>cms_vod_episode_idx_07 {"last_frequency_yn": -1 }<br>cms_vod_episode_idx_08 {"program.display_category1": 1, "episode.free_yn": -1 }<br>cms_vod_episode_idx_09 {"episode.code": 1 }<br>cms_vod_episode_idx_05 {"service_open_date": -1, "episode.broadcast_date": -1 }<br>cms_vod_episode_idx_10 {"view_count.day": -1, "service_open_date": -1 }<br>cms_vod_episode_idx_11 {"episode.name.ko": 1, "service_open_date": -1 }<br>cms_vod_episode_idx_12 {"free_last_frequency_yn": 1, "service_open_date": -1, "episode.broadcast_date": -1 }<br>cms_vod_episode_idx_14 {"last_frequency_yn": 1, "channel.code": 1 }<br>cms_vod_episode_idx_15 {"last_frequency_yn": 1, "program.display_category1": 1 }<br>cms_vod_episode_idx_13 {"last_frequency_yn": 1, "program.category1_code": 1, "episode.free_yn": 1 }|false|
|12|cms_vod_movie|영화 VOD 데이터|cms_vod_movie_idx_01 {"vod_code": -1 }<br>cms_vod_movie_idx_02 {"movie.category1_code": 1, "view_count.week": -1 }<br>cms_vod_movie_idx_03 {"movie.free_yn": 1, "service_open_date": -1, "movie.release_date": -1 }<br>cms_vod_movie_idx_04 {"movie.free_yn": 1, "movie.category1_code": 1, "sale_count.week": -1 }<br>cms_vod_movie_idx_05 {"asp_info.code": 1, "asp_info.mapping_code": 1 }<br>cms_vod_movie_idx_06 {"view_count.week": -1 }<br>cms_vod_movie_idx_07 {"billing_package_id": 1 }<br>cms_vod_movie_idx_08 {"movie.initial_consonant": 1, "movie.name.ko": 1 }<br>cms_vod_movie_idx_09 {"movie.price.krw": 1 }<br>cms_vod_movie_idx_10 {"movie.display_category1": 1, "service_open_date": -1, "movie.release_date": -1 }<br>cms_vod_movie_idx_11 {"movie.type": 1, "service_open_date": -1, "movie.release_date": -1 }|false|
|13|member_favorite|광고 데이터|member_favorite_idx_1 {"user_no": 1 }<br>member_favorite_idx_2 {"user_no": 1, "code": 1 |false|
|14|member_foreign_ip|해외IP 데이터|-|false|
|15|member_internal_ip|국내IP 데이터|member_internal_ip_idx01 {"begin_ip": 1, "end_ip": 1}|false|
|16|member_like|채널, 프로그램 즐겨찾기 데이터|member_like_idx_1 {"user_no": 1 }<br>member_like_idx_2 {"program_code": 1, "channel_code": 1 }<br>member_like_idx_3 {"user_no": 1, "program_code": 1, "channel_code": 1 }|false|

#### im_community
|no|collection|용도|index 구성|capped type|
|-----|-----|-----|-----|-----|
|01|board_comment|게시판 댓글|board_comment_idx1 {"comment_seq":1}<br>board_comment_idx2 {"post_seq":1}<br>board_comment_idx3 {"histor.insert_date":-1}<br>board_comment_idx4 {"post_seq":-1, "histor.insert_date":-1}|false|
|02|board_post|게시판 본문|board_post_idx1 {"post_seq":1}<br>board_post_idx2 {"board_id":-1, "histor.insert_date":-1}<br>board_post_idx3 {"board_id":-1}<br>board_post_idx4 {"board_id":1, "recommend_count.like":-1, "categor":1}<br>board_post_idx5 {"histor.insert_date":-1, "recommend_count.like":-1, "board_id":1}|false|
|03|board_recommend|게시판 추천|board_recommend_idx1 {"seq":-1}<br>board_recommend_idx2 {"report_user.user_no":-1}<br>board_recommend_idx3 {"report_user.user_no":-1, "content_seq":-1, "content_tpe":-1, "recommend_tpe":-1}<br>board_recommend_idx4 {"content_seq":-1, "content_tpe":-1}|false|
|04|fan|fan|fan_idx_01 {"code":-1}<br>fan_idx_02 {"user_no":1, "code":1}|false|
|05|fan_activity||fan_activit_idx_01 {"user_no":-1}|false|
|06|fan_info||fan_info_idx_01 {"code":-1}|false|
|07|push_notice|push 메세지|push_notice_idx1 {"seq":1}<br>push_notice_idx2 {"user_no":1}<br>push_notice_idx3 {"user_no":1, "notice_date":-1}|false|
|08|sequence|통합게시판 고유값||false|
|09|talk|티빙 톡데이타|talk_idx_6 {"talk_seq":1}<br>talk_idx_7 {"user_no":1, "program_code":1}<br>talk_idx_10 {"talkHistor.insert_id":1}<br>talk_idx_01 {"program_code":-1, "talkHistor.insert_date":-1}<br>talk_idx_02 {"program_code":-1, "episode_code":-1, "talkHistor.insert_date":-1}<br>talk_idx_03 {"talkHistor.insert_id":1, "program_code":1, "channel_code":1, "talkHistor.insert_date":-1}<br>talk_idx_04 {"shortclip_code":1, "status":1}|false|
|10|talk_comment|티빙톡 댓글||false|
|11|talk_image|티빙톡 이미지||false|


#### im_manager
|no|collection|용도|index 구성|capped type|
|-----|-----|-----|-----|-----|
|01|api_call_count|api 호출수 - Deprecated||false|
|02|api_json_ignore|api 결과 데이타 필터|api_json_ignore_idx01{"ignore_id": 1}|false|
|03|api_status|api 서버 URI 목록 데이타||false|
|04|api_uri_call_count|api uri별 호출 데이타 - Deprecated||false|
|05|api_uri_call_count.api_uri_status_count|api별 uri 카운트 데이타 - Deprecated||false|
|06|api_uri_status_count|api uri 별 상태값 호출수 데이타 - Deprecated||false|
|07|certification|api key 관리 데이타|certification_idx01{"app_info.api_key": 1}<br>certification_idx02{"client_id": 1}|false|



#### im_micro
|no|collection|용도|index 구성|capped type|
|-----|-----|-----|-----|-----|
|01|lol_community_board|lol 게시판|community_board_idx1 {"category": 1}<br>bbs_no_idx {"bbs_no": 1}|false|
|02|lol_community_comments|lol 댓글 게시판|bbs_no {"cmt_no": -1}<br>bbs_no2 {"bbs_no": -1}<br>bbs_no3 {"url": 1}<br>community_comments_idx1 {"bbs_no": 1,"del_yn": 1,"yellow_card_count": 1}<br>community_comments_idx_4 {"bbs_no": 1,"yellow_card_count": 1,"del_yn": 1}|false|
|03|lol_community_yellow_card|lol 게시판 신고|contents_id_report_idx {"contents_id": 1,"report_user_no": 1}|false|
|04|lol_league_match_history|lol 경기 history|kda_idx {"kda": -1}<br>win_rate_idx {"win_rate": -1}|false|
|05|lol_match_player|lol 대전선수||false|
|06|lol_match_result|lol 경기결과||false|
|07|lol_match_team|lol 대전팀|lol_match_team_idx_01 {"match_date": -1|false|
|08|sequence|lol 게시판 고유값||false|
|09|shopping_cart|쇼핑 카드 데이타|shopping_cart_idx_01 {"user_no": -1}|false|
|10|shopping_epg|쇼핑 편성표|shopping_epg_idx_02 {"broad_name": 1}<br>shopping_epg_idx_03 {"broad_name": 1,"broad_start_date": 1}<br>shopping_epg_idx_01 {"broad_start_date": 1,"broad_end_date": 1}<br>shopping_epg_idx_04 {"broad_date": 1,"broad_start_date": 1, "broad_end_date": 1, "broad_name": 1}|false|
|11|shopping_item|쇼핑 상품|shopping_item_idx_01 {"item_id": -1}|false|
|12|shopping_statistics|상품 클릭수||false|
|13|shopping_vod|쇼핑 VOD|shopping_vod_idx_01 {"broad_name": 1, "video_id": 1}|false|

#### im_operator
|no|collection|용도|index 구성|capped type|
|-----|-----|-----|-----|-----|
|01|asp_cgv_movie_mapping|cgv 영화 맵핑코드값 데이타||false|
|02|code_management|공통 코드값 데이타|code_managerment_idx_01{"code":1}|false|
|03|multiview_mapping|멀티View 채널 데이타 값||false|
|04|player_redirect|채널 전용관 주소 데이타 ||false|




## API 분석 MongoDB 서버군 구성
### 버전
mongodb-linux-x86_64-2.6.6

### 서버구성
MongoDB는 master-slave-arbiter 로 형태의 replication으로 구성이 되어 있으며, arbiter를 포함한 2대의 서버에서 투표에 의하여 자동으로 master가 변경이 되고, 구성은 아래와 같다. 

|no|ip|port|web console port|Master/Slave/Arbiter|vote
|-----|-----|-----|-----|-----|-----|
|01|119.149.188.229|25017|26017|master|true|
|02|119.149.188.229|25018|26018|slave|true|
|03|119.149.188.230|25017|26017|slave|true|
|04|119.149.188.230|25018|26018|arbiter||

### DataBase 구성
|no|DataBase|Collection|Storage Size|Data Size|Total Size|Index|
|-----|-----|-----|-----|-----|-----|-----|
|01|im_logs|7|11.12g|8.65g|21.94g|18|

### Collection 구성
#### im_logs
|no|collection|용도|index 구성|capped type|
|-----|-----|-----|-----|-----|
|01|log_datetime|api 분당 호출수 데이타|log_datetime_idx01{"key_data":1}<br>log_datetime_idx02{"year":1,"month":1,"day":1,"hour":1,"min":1,"server":1}|false|
|02|datetime_key|api key별 분당 호출수 데이타|datetime_key_idx01{"key_data":1,"count":-1}|false|
|03|datetime_uri|api uri 10분당 호출수 데이타|datetime_uri_idx01{"key_data":1}|false|
|04|log_etc|api uri 제외 호출수||false|
|05|log_key|api key 날짜별|log_key_idx01{"log_date":1,"key_data":1}|false|
|06|log_uri_idx|api uri 날짜별|log_uri_idx01{"log_date":1,"key_data":1}|false|
|07|templog|api log 1차 가공 데이타|templog_idx01{"log_date":1,"server_name":1,"trans_yn":1}|true|





