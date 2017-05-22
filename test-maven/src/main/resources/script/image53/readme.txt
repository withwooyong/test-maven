https://www.nginx.com/blog/nginx-caching-guide/
$upstream_cache_status다음과 같습니다.

MISS- 캐시에서 응답을 찾을 수 없으므로 원 서버에서 응답을 가져 왔습니다. 그런 다음 응답이 캐시되었을 수 있습니다.
BYPASS- 요청이 proxy_cache_bypass지시문과 일치했기 때문에 캐시에서 제공된 서비스 대신 원본 서버에서 응답을 가져 왔습니다 ( 내 캐시를 통해 구멍을 뚫을 수 있습니까? 참조). 그러면 응답이 캐시되었을 수 있습니다.
EXPIRED- 캐시의 항목이 만료되었습니다. 응답에는 원본 서버의 최신 내용이 포함됩니다.
STALE- 원본 서버가 올바르게 응답하지 않고 구성 되었기 때문에 내용이 오래되었습니다 proxy_cache_use_stale.
UPDATING- 항목이 현재 이전 요청에 대한 응답으로 업데이트되고 구성되어 있으므로 내용이 오래되었습니다 proxy_cache_use_stale updating.
REVALIDATED- proxy_cache_revalidate지시문이 활성화되었으며 NGINX는 현재 캐시 된 내용이 여전히 유효한지 ( If‑Modified‑Since또는 If‑None‑Match) 확인했습니다.
HIT - 응답에는 캐시에서 유효하고 신선한 내용이 직접 포함됩니다.

curl -X GET -I http://1.255.144.53/resize.php?u=http://image.tving.com/upload/cms/caip/CAIP0900/P000363059.jpg
curl -X GET -I http://1.255.144.53/resize.php?u=http://stimage.hanafostv.com:8080/ontv/10250/T30222_src.jpg&w=328

http://nginx.org/en/docs/http/ngx_http_fastcgi_module.html
http://nginx.org/en/docs/http/ngx_http_fastcgi_module.html#fastcgi_cache_valid