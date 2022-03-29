# Model-based testing on modern web applications

In this project I will try out the model-based testing capabilities
of [GraphWalker](https://graphwalker.github.io/)
and [Selenium](https://www.selenium.dev/) to test modern web applications.

## Executing tests

There are several ways of executing tests.

### Executing with Maven

```shell
$ cd modal-runner
$ mvn clean graphwalker:test
```

### Executing from your IDE with JUnit 5

There is a [JUnit 5](https://junit.org/junit5/) compatible test executor for the
model, so you can run the tests from your IDE as well.
