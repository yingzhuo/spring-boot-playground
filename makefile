usage:
	@echo "========================================================================================="
	@echo "usage (default)      : 显示本菜单"
	@echo "wrapper              : 初始化GradleWrapper"
	@echo "clean                : 清理项目"
	@echo "compile              : 编译项目"
	@echo "build                : 构建项目"
	@echo "run                  : 运行项目"
	@echo "github               : 推送文件到Github"
	@echo "========================================================================================="

wrapper:
	@gradle wrapper \
		--gradle-distribution-url 'https://mirrors.cloud.tencent.com/gradle/gradle-8.12-bin.zip'

clean:
	@find $(CURDIR) -name '.DS_Store' -delete
	@rm -rf $(CURDIR)/logs/*.log*
	@rm -rf $(CURDIR)/build/
	@gradlew clean -q

compile:
	@gradlew classes

build:
	@gradlew build -P"build-for-prod" -x"test"

run:
	@gradlew :playground-core:bootRun

github: clean
	@git status
	@git add .
	@git commit -m "$(shell /bin/date "+%F %T")"
	@git push

.PHONY: usage wrapper clean compile build run github