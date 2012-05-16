grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.tomcat.nio = true

if (appName == 'events-push') {
    grails.plugin.location.'pluginPlatform' = '../../platform-core'
}

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        excludes("xml-apis", "commons-digester")
    }

    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsCentral()
        mavenCentral()
        mavenLocal()
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
        mavenRepo "https://oss.sonatype.org/content/repositories/snapshots"
    }
    dependencies {
        compile('org.atmosphere:atmosphere-runtime:1.0.0-SNAPSHOT') {
            excludes 'slf4j-api', 'atmosphere-ping'
        }
    }

    plugins {
        runtime(":jquery:1.7.1"){
            export = false
        }

        build(":tomcat:$grailsVersion",
                ":release:2.0.1",
                ":hibernate:$grailsVersion"
        ) {
            export = false
        }
        if (appName != 'events-push') {
            compile ':platform-core:1.0.M2d-SNAPSHOT'
        }
    }
}
