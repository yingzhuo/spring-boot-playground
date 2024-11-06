FROM registry.cn-shanghai.aliyuncs.com/yingzhuo/openjdk:17-alpine

LABEL maintainer="应卓 'yingzhor@gmail.com'"

USER root:root

WORKDIR /opt/

COPY --chown=root:root *.jar /opt/application.jar
COPY --chown=root:root docker-entrypoint.sh /opt/script/docker-entrypoint.sh

ENV TZ="Asia/Shanghai"
ENV LANG="zh_CN.UTF-8"

EXPOSE 8080
EXPOSE 8443

STOPSIGNAL 15

VOLUME /opt/tmp/
VOLUME /opt/data/
VOLUME /opt/log/

ENTRYPOINT ["sh", "/opt/script/docker-entrypoint.sh"]
