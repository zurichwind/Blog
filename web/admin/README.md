# admins

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Run your tests
```
npm run test
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).



    server {
        listen       8000;
        server_name  localhost;

        #charset koi8-r;
        #
        #       #access_log  logs/host.access.log  main;

              location / {
                     root   C:/A/Web/dist;
                     index  index.html ;
                     try_files $uri $uri/ /index.html;
                    # error_page 405 =200  $request_uri;
                   }
              location ^/api/ {
              		proxy_pass http://localhost:8090;
              		proxy_set_header HOST $host;  # 不改变源请求头的值
              		proxy_pass_request_body on;  #开启获取请求体
              		proxy_pass_request_headers on;  #开启获取请求头
              		proxy_set_header X-Real-IP $remote_addr;   # 记录真实发出请求的客户端IP
              		proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;  #记录代理信息
              }
               location @405 {
                           proxy_set_header Host $host;
                           proxy_set_header X-Real-IP $remote_addr;
                           proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
                           #ip为后端服务地址
                          # proxy_pass http://192.168.3.129:8081$request_uri ;
                            proxy_pass http://localhost:8090;
                }



        }