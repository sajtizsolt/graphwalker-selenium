# Model-based testing on modern web applications

In this project I will try out the model-based testing capabilities
of [GraphWalker](https://graphwalker.github.io/)
and [Selenium](https://www.selenium.dev/) to test modern web applications.

## Generating model

To generate a test model for GraphWalker, you can use the `model-generator`
submodule. After the code is compiled and the `.jar` file is created, you can
use it to place the model at the specified destination.

```shell
$ cd modal-generator
$ mvn clean install
$ cd target
$ java -jar model-generator-<VERSION>.jar --model <PATH>
```

If you not specify the model destination with the `--model <PATH>` arguments,
the generator will place the `model.json` file under
the `graphwalker-selenium/model-runner/src/main/resources/com/company/gs/mbt`
directory. It is recommended to place the generator model file here, if you want
to use the `model-runner` submodule as well.

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
