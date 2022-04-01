### 首先创建一个ca文件夹用来存放私钥跟公钥

mkdir -p /opt/docker/ca cd /opt/docker/ca

### 然后在Docker守护程序的主机上，生成CA私钥和公钥

openssl genrsa -aes256 -out ca-key.pem 4096

### 补全CA证书信息

openssl req -new -x509 -days 365 -key ca-key.pem -sha256 -out ca.pem

### 生成server-key.pem

openssl genrsa -out server-key.pem 4096

### 用CA签署公钥(把下面的IP换成你自己服务器外网的IP或者域名)

openssl req -subj "/CN=[ip]" -sha256 -new -key server-key.pem -out server.csr

### 匹配白名单(这里需要注意，虽然0.0.0.0可以匹配任意，但是仍需要配置你的外网ip和127.0.0.1，否则客户端会连接不上)

echo subjectAltName = IP:0.0.0.0,IP:[ip],IP:127.0.0.1 >> extfile.cnf

### 将Docker守护程序密钥的扩展使用属性设置为仅用于服务器身份验证

echo extendedKeyUsage = serverAuth >> extfile.cnf

### 生成签名整数

openssl x509 -req -days 365 -sha256 -in server.csr -CA ca.pem -CAkey ca-key.pem -CAcreateserial -out server-cert.pem
-extfile extfile.cnf

### 生成客户端的key.pem

openssl genrsa -out key.pem 4096 openssl req -subj '/CN=[ip]' -new -key key.pem -out client.csr

### 要使秘钥适合客户端身份验证

echo extendedKeyUsage = clientAuth >> extfile.cnf echo extendedKeyUsage = clientAuth > extfile-client.cnf

### 生成签名整数

openssl x509 -req -days 365 -sha256 -in client.csr -CA ca.pem -CAkey ca-key.pem -CAcreateserial -out cert.pem -extfile
extfile-client.cnf

### 删除不需要的文件

rm -v client.csr server.csr extfile.cnf extfile-client.cnf

### 为了防止私钥文件被更改以及被其他用户查看，修改其权限为所有者只读

chmod -v 0400 ca-key.pem key.pem server-key.pem

### 为了防止##### 公钥文件被更改，修改其权限为只读

chmod -v 0444 ca.pem server-cert.pem cert.pem

### 修改Docker配置

vi /lib/systemd/system/docker.service

### 修改Docker配置

ExecStart=/opt/bin/dockerd --tlsverify --tlscacert=/opt/docker/ca/ca.pem --tlscert=/opt/docker/ca/server-cert.pem
--tlskey=/opt/docker/ca/server-key.pem -H tcp://0.0.0.0:2375 -H unix:///var/run/docker.sock

### 重新加载daemon并重启docker

systemctl daemon-reload systemctl restart docker systemctl status docker

### 将ca.pem cert.pem key.pem这3个文件拷贝到客户端目录

### 测试用https://101.42.231.254:2375连接 并指定证书目录

https://blog.csdn.net/niceyoo/article/details/107220585?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-1.pc_relevant_paycolumn_v3&spm=1001.2101.3001.4242.2&utm_relevant_index=4

spring:
profiles:
active: dev application:
name: gateway server:
port: 8080 zuul:

# 别名

routes:
provider: /provider/**
consumer: /consumer/**
logging:
level:
org:
springframework:
web:
servlet:

# 链路追踪log

DispatcherServlet: debug

---

# prod

spring:
profiles: prod zipkin:
base-url: http://172.17.0.1:5555

# 禁用链路追踪

enabled: false eureka:
client:
service-url:
defaultZone: http://172.17.0.1:8888/eureka

---

# dev

spring:
profiles: dev zipkin:
base-url: http://localhost:5555
eureka:
client:
service-url:
defaultZone: http://localhost:8888/eureka

# 可以通过zuul.prefix可为所有的映射增加统一的前缀。如: /api。默认情况下，代理会在转发前自动剥离这个前缀。如果需要转发时带上前缀，可以配置: zuul.stripPrefix=false来关闭这个默认行为。例如：

# zuul.routes.users.path=/myusers/**

# zuul.routes.users.stripPrefix=false

# 注意： zuul.stripPrefix只会对zuul.prefix的前缀起作用。对于path指定的前缀不会起作用。

# 同一个系统中各个服务之间通过Headers来共享信息是没啥问题的，但是如果不想Headers中的一些敏感信息随着HTTP转发泄露出去话，需要在路由配置中指定一个忽略Header的清单。

#

# 默认情况下，Zuul在请求路由时，会过滤HTTP请求头信息中的一些敏感信息，默认的敏感头信息通过zuul.sensitiveHeaders定义，包括Cookie、Set-Cookie、Authorization。配置的sensitiveHeaders可以用逗号分割。

#

# 对指定路由的可以用下面进行配置:

#

## 对指定路由开启自定义敏感头

# zuul.routes.[route].customSensitiveHeaders=true

# zuul.routes.[route].sensitiveHeaders=[这里设置要过滤的敏感头]

# 设置全局:

#

#

# zuul.sensitiveHeaders=[这里设置要过滤的敏感头]

#

# 作者：CD826

# 链接：https://www.jianshu.com/p/be5b26a9fa42

# 来源：简书

# 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

# 忽略Header设置

# 如果每一个路由都需要配置一些额外的敏感Header时，那你可以通过zuul.ignoredHeaders来统一设置需要忽略的Header。如:

#

# zuul.ignoredHeaders=[这里设置要忽略的Header]

# 在默认情况下是没有这个配置的，如果项目中引入了Spring Security，那么Spring Security会自动加上这个配置，默认值为: Pragma,Cache-Control,X-Frame-Options,X-Content-Type-Options,X-XSS-Protection,Expries。

#

# 此时，如果还需要使用下游微服务的Spring Security的Header时，可以增加下面的设置:

#

#

# zuul.ignoreSecurityHeaders=false

#

# 作者：CD826

# 链接：https://www.jianshu.com/p/be5b26a9fa42

# 来源：简书

# 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

# Eureka注册服务UI: http://localhost:8888/

# Hystrix监控服务UI: http://localhost:8890/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8890%2Fhystrix.stream

# Zipkin链路追踪服务UI(需要启动单独jar): http://127.0.0.1:9411/
