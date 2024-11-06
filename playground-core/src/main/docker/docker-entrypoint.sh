#!/bin/bash

exec java \
  -Djava.security.egd=file:/dev/./urandom \
  -jar /opt/application.jar \
  "$@"
