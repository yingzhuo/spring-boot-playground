#!/bin/bash

exec java \
  -XX:+PrintCommandLineFlags \
  -XX:InitialHeapSize=4g \
  -XX:MaxHeapSize=4g \
  -XX:ThreadStackSize=1m \
  -Djava.security.egd=file:/dev/./urandom \
  -jar /opt/application.jar \
  "$@"
