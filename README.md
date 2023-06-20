## Blog App

[![DevOps By Rultor.com](https://www.rultor.com/b/hizmailovich/blog-app)](https://www.rultor.com/p/hizmailovich/northview-data-warehouse)
<br>

[![maven](https://github.com/hizmailovich/blog-app/actions/workflows/maven.yml/badge.svg)](https://github.com/hizmailovich/blog-app/actions/workflows/maven.yml)
[![codecov](https://codecov.io/gh/hizmailovich/blog-app/branch/master/graph/badge.svg?token=8NSGE6AGER)](https://codecov.io/gh/hizmailovich/blog-app)

Project architect: [@hizmailovich](https://github.com/hizmailovich)

Simple app with graph data model.

## Glossary
`User` - information about user.

`Post` - information about post.


## Quick Start
Run this script to start containers locally:
```shell
$ sh local.sh
```


## How to Contribute

Fork repository, make changes, send us a [pull request](https://www.yegor256.com/2014/04/15/github-guidelines.html).
We will review your changes and apply them to the `master` branch shortly,
provided they don't violate our quality standards. To avoid frustration,
before sending us your pull request please run full Maven build:

```bash
$ mvn clean install
```

You will need Maven 3.8.7+ and Java 17+.
