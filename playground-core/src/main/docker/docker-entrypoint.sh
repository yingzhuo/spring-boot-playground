#!/bin/bash

exec java \
  -XX:+PrintCommandLineFlags \
  -Djava.security.egd=file:/dev/./urandom \
  -jar /opt/application.jar \
  "$@"
