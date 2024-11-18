#!/bin/bash

exec java \
  -server \
  -Xmixed \
  -XX:+PrintCommandLineFlags \
  -XX:-PrintFlagsInitial \
  -XX:+PrintFlagsFinal \
  -XX:InitialHeapSize=4g \
  -XX:MaxHeapSize=4g \
  -XX:ThreadStackSize=1m \
  -Djava.security.egd=file:/dev/./urandom \
  -jar /opt/application.jar \
  "$@"
