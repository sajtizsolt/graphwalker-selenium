# Model-based testing on modern web applications

In this project I will try out the model-based testing capabilities of [GraphWalker](https://graphwalker.github.io/) and [Selenium](https://www.selenium.dev/) to test modern web applications.

## Generating model

To generate a test model for GraphWalker, you can use the `model-generator` submodule. After the code is compiled and the `.jar` file is created, you can use it to place the model at the specified destination.

```shell
$ cd modal-generator
$ mvn clean install
$ cd target
$ java -jar model-generator-<VERSION>.jar --model <PATH>
```

If you not specify the model destination with the `--model <PATH>` arguments, the generator will place the `model.json` file under the `graphwalker-selenium/model-runner/src/main/resources/com/company/gs/mbt` directory. It is recommended to place the generator model file here, if you want to use the `model-runner` submodule as well.

## Executing tests

There are several ways of executing tests.

Before executing tests, you have to place a property file under `model-runner/src/main/resources` directory, named `gs.properties` - otherwise, the software will not work properly.

These properties are important, because with the help of these, Selenium will find the web elements to run the tests.

Here is a list of properties you have to define:

```properties
# HTML class names
className.modal.close=
className.modal.root=
className.tab.active=

# HTML element IDs
id.logo=
id.modal.forgottenPassword.email=
id.modal.forgottenPassword.submit=
id.modal.notifyUser.checkbox=
id.modal.notifyUser.submit=
id.page.file.new.container=
id.page.login.forgottenPassword=
id.page.login.email=
id.page.login.password=
id.page.login.submit=
id.page.settings.back=
id.page.signingMultipleFiles.back=
id.page.signingProcesses.back=
id.menu.user=
id.menu.user.logout=
id.menu.user.settings=
id.menu.user.signingMultipleFiles=
id.menu.user.signingProcesses=

# XPath routes
xpath.modal.close=
xpath.page.login.loginTab=

# URLs
url.file=
url.login=
url.settings=
url.signingMultipleFiles=
url.signingProcesses=

# Data
data.user.email=
data.user.password=
```

### Executing with Maven

```shell
$ cd modal-runner
$ mvn clean graphwalker:test
```

### Executing from your IDE with JUnit 5

There is a [JUnit 5](https://junit.org/junit5/) compatible test executor for the model, so you can run the tests from your IDE as well.
