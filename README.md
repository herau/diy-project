# DIY Project  [![Build Status](https://travis-ci.org/herau/diy-project.svg)](https://travis-ci.org/herau/diy-project) [![codecov.io](http://codecov.io/github/herau/diy-project/coverage.svg?branch=master)](http://codecov.io/github/herau/diy-project?branch=master) [![Java](https://www.versioneye.com/user/projects/56081ebf5a262f001e000365/badge.svg?style=flat)](https://www.versioneye.com/user/projects/56081ebf5a262f001e000365) [![JS](https://www.versioneye.com/user/projects/56081ea55a262f00220000f9/badge.svg?style=flat)](https://www.versioneye.com/user/projects/56081ea55a262f00220000f9)

## Prerequisites

### Global

* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Git](https://git-scm.com/)
* [Maven](http://maven.apache.org/install.html)
* [NodeJs](https://nodejs.org/en/) (0.12.0+. For Windows < 4.0.0, see [websocket #13](https://github.com/websockets/utf-8-validate/issues/13))
* [Python](https://www.python.org/) (2.7+ and < 3.0, see [node-gyp #193](https://github.com/nodejs/node-gyp/issues/193) )

### Windows specific (see [node-gyp #629](https://github.com/nodejs/node-gyp/issues/629))

 * [Visual Studio](https://www.visualstudio.com/) (2013+ with C++ tools support. You may need to create a new C++ project to trigger such tools installation)
 * [Windows 7 SDK](http://www.microsoft.com/en-us/download/details.aspx?id=8279)

## Quick start

To start, follow those steps:

```bash
$ git clone https://github.com/herau/diy-project.git
$ cd diy-project
$ npm install
$ npm run build
$ npm start
```

> If your network is behind a **proxy**, you may need to config [npm](http://jjasonclark.com/how-to-setup-node-behind-web-proxy/) before run `npm install`:

> ```bash
> $ npm config set proxy http(s)://<user>:<password>@<host>:<port>
> $ npm config set https-proxy http(s)://<user>:<password>@<host>:<port>
> ```

## Under the hood

Powered by [Spring Boot](http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/) and [Angular 2](https://angular.io/)

Built by [Maven](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html),  [NPM](https://www.npmjs.com)

Containerized to [Docker](https://www.docker.com/) Image, thanks to the [Spotify Maven plugin](https://github.com/spotify/docker-maven-plugin)

Full-text search is available on users and tools entities through [Hibernate Search](http://hibernate.org/search/) and [Apache Solr](http://lucene.apache.org/solr/)

## External Documentation

* [Markdown syntax for issues and comments](https://guides.github.com/features/mastering-markdown/)
* [a Git cheat-sheet](https://training.github.com/kit/downloads/fr/github-git-cheat-sheet.pdf)
* [a guide for Git](http://rogerdudler.github.io/git-guide/)
