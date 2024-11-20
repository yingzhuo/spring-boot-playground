usage:
	@echo "========================================================================================="
	@echo "usage (default)      : 显示本菜单"
	@echo "wrapper              : 初始化GradleWrapper"
	@echo "clean                : 清理项目"
	@echo "compile              : 编译项目"
	@echo "build                : 构建项目"
	@echo "docker               : 构建Docker镜像"
	@echo "github               : 推送文件到Github"
	@echo "========================================================================================="

wrapper:
	@gradle wrapper \
		--gradle-distribution-url 'https://mirrors.cloud.tencent.com/gradle/gradle-8.11-bin.zip'

clean:
	@gradlew clean
	@rm -rf $(CURDIR)/logs/*.log*

compile:
	@gradlew classes

build:
	@gradlew build

docker:
	@gradlew dockerBuildImage

github: clean
	@git status
	@git add .
	@git commit -m "$(shell /bin/date "+%F %T")"
	@git push

.PHONY: usage wrapper clean compile build docker github