#Overview

This is the server module for environmental monitoring product.

#Environment Setup

##Software/Tools 
Need to install the following software

* Oracle JDK 8 (not OpenJDK)
* Mysql
* Maven 3.0.4 and above
* Git

## Local Environment

We need to setup couple of environment variables for maven to work:

* `CHASERVER_TCP_HOME`
This is the working directory for chaserver-tcp. Server will write logs etc into that directory. On windows, it could be "c:\chahome". For Unix, it should be a directory where chaserver-tcp has write permission.

* `M2_HOME=c:\maven3.0.4`  
This is the home directory Maven 2. 

* `MAVEN_OPTS=-Xms256m -Xmx512m`  
This is to set maven options. 

* `CHADA_REPO_BASE_URL`
This is the chada internal repository

## IDEA Plugin Install
* lombok

## Quick Start
`
java -jar chaserver-tcp-1.0.3-SNAPSHOT-exec.jar
`
