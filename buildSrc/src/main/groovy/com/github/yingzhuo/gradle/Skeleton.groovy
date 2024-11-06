package com.github.yingzhuo.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class Skeleton implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.task("skeleton", group: 'example') {
            doLast {
                println('-' * 10)
                println '这是一个Gradle插件的骨架 无实际功能'
            }
        }
    }

}
