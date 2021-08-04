# arcade-app

### How to run

  Type the following to clean, compile, and run this code using Maven, assuming you are in the top-level project directory:

  ```
  $ mvn clean
  ```

  ```
  $ mvn -e compile
  ```

  ```
  $ export MAVEN_OPTS=-Dprism.order=sw;
  $ mvn -e exec:java -Dexec.mainClass="cs1302.arcade.ArcadeDriver"
  ```

  If you get a `mvn: command not found` error when attempting to execute
  the `mvn` command, then please see the first question in the
  [FAQ](#appendix---faq).

  Additionally, a `Makefile` is provided so that you can type the following to
  easily clean, compile, and run this code using the
  [Make utility](https://www.gnu.org/software/make/):

  ```
  $ make clean
  ```

  ```
  $ make compile
  ```

  ```
  $ make run
  ```

  Using `make run` will automatically add the `-Dprism.order=sw` when running
  `mvn -e exec:java`.
