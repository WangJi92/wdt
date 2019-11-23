#!/bin/sh -X
curl -sk https://arthas.gitee.io/arthas-boot.jar -o ~/.arthas-boot.jar  && echo "alias as.sh='java -jar ~/.arthas-boot.jar --repo-mirror aliyun --use-http'" >> ~/.bashrc && source ~/.bashrc
