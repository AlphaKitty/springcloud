#FROM frolvlad/alpine-oraclejdk8:slim

#拉取基础镜像位置
FROM openjdk:15
VOLUME /tmp

#拷贝jar到基础镜像容器  需要根据 pom 中的 artifactId jar.version 做变更
ADD target/provider-1.0.0-exec.jar /app.jar

EXPOSE 8888

#RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom"]

#容器运行默认启动命令
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Djava.awt.headless=true"]
