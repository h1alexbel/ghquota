<img alt="logo" src="https://www.objectionary.com/cactus.svg" height="100px" />

[![EO principles respected here](https://www.elegantobjects.org/badge.svg)](https://www.elegantobjects.org)
[![DevOps By Rultor.com](https://www.rultor.com/b/h1alexbel/ghquota)](https://www.rultor.com/p/h1alexbel/ghquota)
[![We recommend IntelliJ IDEA](https://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)
<br>

[![mvn](https://github.com/h1alexbel/ghquota/actions/workflows/mvn.yml/badge.svg)](https://github.com/h1alexbel/ghquota/actions/workflows/mvn.yml)
[![maven central](http://maven-badges.herokuapp.com/maven-central/io.github.h1alexbel/ghquota/badge.svg)](https://search.maven.org/artifact/io.github.h1alexbel/ghquota)
[![javadoc](https://javadoc.io/badge2/io.github.h1alexbel/ghquota/javadoc.svg)](https://javadoc.io/doc/io.github.h1alexbel/ghquota)

[![Hits-of-Code](https://hitsofcode.com/github/h1alexbel/ghquota)](https://hitsofcode.com/view/github/h1alexbel/ghquota)
[![Lines-of-Code](https://tokei.rs/b1/github/h1alexbel/ghquota)](https://github.com/h1alexbel/ghquota)
[![PDD status](http://www.0pdd.com/svg?name=h1alexbel/ghquota)](http://www.0pdd.com/p?name=h1alexbel/ghquota)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/h1alexbel/ghquota/blob/master/LICENSE.txt)

JUnit extension that won't run your tests if request quota in GitHub is exceeded.

**How to use**. All you need is this (get the latest version [here](https://search.maven.org/artifact/io.github.h1alexbel/ghquota)):

Maven:
```xml
<dependency>
  <groupId>io.github.h1alexbel</groupId>
  <artifactId>ghquota</artifactId>
  <scope>test</scope>
</dependency>
```

Gradle:
```groovy
dependencies {
    testCompile 'io.github.h1alexbel:ghquota:<version>'
}
```

```java
import java.net.URL;

@Test
@ExtendWith(Quota.class)
void readsContentInRealGitHub() {
    new URL("api.github.com/repos/h1alexbel/ghquota").getContent();
}
```

We don't want this unit test to be executed when [GitHub Quota API](https://docs.github.com/en/rest/using-the-rest-api/rate-limits-for-the-rest-api?apiVersion=2022-11-28) is exceeded.
The Quota execution condition will prevent JUnit5 from executing the test when you are out of requests.

## How to Contribute

It's a Java project.
You will need to install Maven 3.8.7+ Java 17+.

Fork repository, make changes, send us a [pull request](https://www.yegor256.com/2014/04/15/github-guidelines.html).
We will review your changes and apply them to the `master` branch shortly,
provided they don't violate our quality standards. To avoid frustration,
before sending us your pull request please run full Maven build:

```bash
$ mvn clean install
```
