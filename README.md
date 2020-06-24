### Functional Requirements

* **Main Application Requirements (10 points):** The main part of your
  application needs to fulfill the following functional requirements:

  * *Game List (5 points):* Your application should present
    the user with a visual / graphical list of available games. Starting a game should
    either swap the scene in the current stage or create a new application modal stage
    on which to display the game's scene graph.

  * *Multiple Games per Execution (5 points):* Your application should allow
    users to exit one game (without exiting the entire application) and start
    the same game again (with its state reset) or start the other game.

* **Group 1 Game Requirements (45 points):** Your Group 1 game implementation
  needs to fulfill the following functional requirements:

  * *UI, Mechanics, and Scoring (40 points):* The user interface and game mechanics
    must be easily recognizable and consistent with traditional implementations of
    the game you chose (see the Wikipedia links for more information). Your game
    must provide a consistent scoring mechanism and display the score(s) to
    the user somewhere in the user interface. Additionally, games in this group
    must provide a leveling mechanism by which the game's level of difficulty
    increases over time as the "level" increases. A minimum of three levels is
    required, however, there is no maximum level requirement. The first level
    should be easy enough for most players familiar with the game to complete.

  * *Controls (5 points):* You are required to provide keyboard controls
    for a game in this group. If anything is not intuitive, then you need to let
    the user know before the game starts.

* **Group 2 Game Requirements (45 points):** Your Group 2 game implementation
  needs to fulfill the following functional requirements:

  * *UI, Mechanics, and Scoring (40 points):* The user interface and game mechanics
    must be easily recognizable and consistent with traditional implementations of
    the game you chose (see the Wikipedia link for more information). Your game
    must provide a consistent scoring mechanism and display the score or scores to
    the users somewhere in the user interface.

  * *Controls (5 points):* You are required to provide intuitive mouse or
    keyboard controls for a game in this group. If anything is not intuitive,
    then you need to let the user know before the game starts.

  * *Note:* You are only required to provide a
    human player vs. human player mode for for a multiplayer game in this category.
    Feel free to add support for computer players, however, such support will not
    contribute to your grade.

* **Extra Credit 1 (5 points):** Add some kind of animated intro to your application.
  One potential way to accomplish this is by using a separate scene for your intro.
  This intro should include, in addition to some kind of animated element, the
  application title (i.e., `cs1302-arcade`), your team name, and the name of each
  team member. An animated GIF, by itself, does not meet this requirement. We
  want one or more nodes in the scene to move around. If you want the grader to
  check for this requirement, then you must make sure it is included in your
  last update to `REFLECTION.md`.

* **Extra Credit 2 (5 points):** Add a [high score table](https://en.wikipedia.org/wiki/Score_(game)#High_score)
  to your application, accessible via your application's menu (menu bar or otherwise).
  The table needs to actually keep track of the high scores for each game and include
  player initials. This may involve modifications in other areas of your application to
  accommodate this. In order to receive full credit for this extra credit functional
  requirement, the high score table must persist over time and over separate executions
  of your application. This will involve reading and writing to a file.
  If you want the grader to check for this requirement, then you must
  make sure it is included in your last update to `REFLECTION.md`.

### Non-Functional Requirements

A non-functional requirement is *subtracted* from your point total if
not satisfied. In order to emphasize the importance of these requirements,
non-compliance results in the full point amount being subtracted from your
point total. That is, they are all or nothing.

* **User-Friendly Experience (10 points):** Except for reasonable delays resulting
  from X forwarding, your application should not hang/freeze or crash during
  execution.

* **Local Assets / Resources (10 points):** All assets (e.g., images) need to
  be pre-downloaded and placed under `src/main/resources`. You are not allowed
  to load assets directly from the web. This will help make your app faster.

  If you place your assets under `src/main/resources`, then you can refer to
  them in your code directly using a relative path that is assumed to be
  relative to `src/main/resources` itself. For example, you might have the
  following two images:

  * `src/main/resources/brad.jpg`
  * `src/main/resources/frogger/supa.png`

  You can create `Image` objects from these as follows:

  ```java
  Image brad = new Image("brad.jpg");
  Image brad = new Image("frogger/supa.png");
  ```

* **Attribution (10 points):** Proper attribution should be given for all assets (e.g.,
  art, sound, music, etc.) that is not authored by members of your project team.
  You should make an `ATTRIBUTION.md` file that contains this information. For each
  asset, please provide the following information:

  * Asset Name
  * Path within your project
  * Author citation
  * URL
  * License

  Here is an example of an entry in an `ATTRIBUTION.md` file:

  ```
  ## Alien
  * `src/main/resources/alien.png`
  * Carlos Alface. "Alien" November 15, 2014
  * https://opengameart.org/content/alien-1
  * Creative Commons Attribution 3.0 Unported (CC BY 3.0)
  ```

* **(40 points; 10 points each) Reflection Updates:** Before each submission
  deadline mentioned towards the beginning of this document, you
  will need to update your project's `REFLECTION.md` file to include a new
  section describing:

  1. That work that has been done since the last update (you may summarize your Git log);
  2. The work you plan to complete before the next deadline (except for the last submission); and
  3. What problems, if any, you have encountered related to this project.

  These changes, including your work in progress, must be committed to your
  repository. The commit should also be tagged using Git. For example:

  ```
  $ git tag -am "Partial Submission 1" deadline1
  $ git push origin --tags
  ```

  For each reflection update, you will be graded on the existence of the related
  Git tag and the germaneness of the content included in your `REFLECTION.md`
  file.

### Absolute Requirements

An absolute requirement is similar to a non-functional requirement, except that violating
it will result in an immediate zero for the assignment. In many cases, a violation
will prevent the graders from evaluating your functional requirements. No attempts will be
made to modify your submission to evaluate other requirements.

* **Project Structure:** The location of the default
  package for the source code should be a direct subdirectory called `src/main/java`.
  When the project is compiled using Maven, the the default package for compiled
  code should be `target/classes`.

  If you follow this structure, then you might type the following to clean, compile,
  and run your code using Maven, assuming you are in the top-level project directory:

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
  easily clean, compile, and run your code using the
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

  Your main application class should be `cs1302.arcade.ArcadeApp`.
  The driver class should be `cs1302.arcade.ArcadeDriver`.
  Any additional `.java` files in your project are expected to fulfill all
  non-functional and absolute requirements, even if the main parts of the
  project do not use them. You may assume graders will compile your source
  code in an order that satisfies compilation dependencies.
  You should remove any `.java` files that you do not need before submission.

* **Team Membership:** Formalized team membership via a team application
  submission made before the team application deadline is required. Furthermore,
  you must adhere to the policies outlined in the syllabus and the team application.
  Deviations will likely result in zero on the assignment for both team members.
  Except for extenuating circumstances, such as family or medical emergencies, that are
  approved by the instructor, team issues such as scheduling and coordination are the
  responsibility of each team member. If you identify a team issue early on (first week),
  then we encourage you to contact your instructor by sending a private post to
  "Instructors" via the course Piazza as soon as possible.

* **GitHub Collaborators:** Teams are required to add the following users as collaborators
  to the private GitHub-hosted Git repository that they setup for their project:

  * [`mepcotterell`](https://github.com/mepcotterell) -- Dr. Cotterell
  * [`bjb211`](https://github.com/bjb211) -- Dr. Barnes

  Your instructors will have access to your private repository's history. This
  means that they can see who did what and when based on the log.

* **Development Environment:** This project must be implemented
  in Java 8, and it *must compile and run* correctly on Nike using the specific
  version of Java 8 that is setup according to the instructions provided
  by your instructor. For Spring 2019, these instructions were posted on
  Piazza [@29](https://piazza.com/class/jpupoaxnvvs497?cid=29).

* **No FXML or use of Scene Builder:** FXML and SceneBuilder
  are advanced tools that are not currently covered in this course. Use of
  either for this project is prohibited. Please note that the project is
  not easier with these tools. In most cases, they actually make the
  project harder, especially since those topics have not been covered.

### Grading

The graders will compile and run your code on Nike using Maven (likely via
the provided `Makefile`). They will test each of the requirements and total up the points
earned. This project is worth 100 points. Students have an opportunity to earn
an additional 10 points via extra credit.

## How to Download the Project

These instructions assume that both team members have
[setup their SSH keys](https://git.io/fjLzB#setting-up-ssh-keys)
on Nike and GitHub.

1. If you have not done so already, you or your partner should create a
   free GitHub-hosted private Git repository for your project under one of your GitHub
   accounts called `cs1302-arcade` and add `mepcotterell`, `bjb211`, and the second team
   member as collaborators to that private repository. **In the remaining instructions,**
   **`TEAM_REPO_SSH` will refer to the SSH URL for that repository as provided by GitHub.**

1. One team member should clone their empty team repository to their Nike account and
   setup a link to the remote skeleton repository provided by your instructor. A sequence
   of commands is provided below. You should make every effort to understand what
   each command is doing *before* you execute the command.

   ```
   $ git clone TEAM_REPO_SSH cs1302-arcade
   $ cd cs1302-arcade
   $ git remote add skeleton https://github.com/cs1302uga/cs1302-arcade.git
   $ git pull skeleton master
   $ git push origin master
   ```

   This team member should now be good to go. A pair programming workflow is provided
   in the [Appendix - Workflow](#appendix---workflow) section, which provides an
   overview of one way to send / receive changes from team member to team member
   while minimizing basic merge conflicts.

1. The other team member should now be able to perform the same sequence of steps,
   omitting the last two steps:

   ```
   $ git clone TEAM_REPO_SSH cs1302-arcade
   $ cd cs1302-arcade
   $ git remote add skeleton https://github.com/cs1302uga/cs1302-arcade.git
   ```

   This team member should now be good to go. A pair programming workflow is provided
   in the [Appendix - Workflow](#appendix---workflow) section, which provides an
   overview of one way to send / receive changes from team member to team member
   while minimizing basic merge conflicts.

If you followed these instructions correctly, then the steps above should have
created a directory called `cs1302-arcade` in your present working directory that
contains the project files as well as a local copy of the repository.
If you have any problems with any of these procedures, then please
ask about them on Piazza.

## Submission Instructions

You will be submitting your project via Nike before the final deadline.
**Both team members are required to submit according to these instructions.**

1. **CRITICAL:** Make sure that all tracked files are staged and committed with
   a good log message and that the `master` branch is checked out. Take special
   care to ensure that your fully-committed `master` branch reflects the project
   you wish to submit. Compare your log to the log on GitHub. If you do not have
   the most recent version of your team's project, then you may need to do a
   `git pull`. Finally, Using `git status` should not indicate any untracked files.

1. Change into the parent directory of your local copy of the repository and
   submit it using the `submit` command:

   ```
   $ submit cs1302-arcade cs1302a
   ```

If you have any problems submitting your project, then please contact the CSCI
1302 Support Team by sending a private post to "Instructors" via the course
Piazza as soon as possible.

# Appendix - Useful Links

## JavaFX 8

* [Package: `javafx`](https://docs.oracle.com/javase/8/javafx/api/toc.htm)
* [JavaFX CSS Reference Guide](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/doc-files/cssref.html)
* [Styling UI Controls with CSS](https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/apply-css.htm)

## Resources

* [Reference Manual](https://git-scm.com/docs) - alternative to the manual pages for `git`
* [Pro Git (Book)](https://git-scm.com/book/en/v2) - contains a lot of good examples
* [Version Control by Example (Book)](https://ericsink.com/vcbe/index.html) - excellent introduction to version control by Eric Sink

# Appendix - Workflow

**NOTE:** We recommend that you read
[Pro Git Ch. 3.4](https://git-scm.com/book/en/v2/Git-Branching-Branching-Workflows)
before reading our suggested pair programming workflow below.

For this group-based project, you and your partner are required to do
**pair programming**. According to [Williams & Kessler](https://t.uga.edu/4S0):

> Pair programming is a practice in which two programmers work side-by-side
> at one computer, continuously collaborating on the same design, algorithm,
> code, or test. [...] one person drives (has control of the keyboard or is
> recording design ideas) while the other is continuously reviewing the work.

If you've followed the instructions included
[earlier in this project description](#how-to-download-the-project),
then you and your partner already have a GitHub-hosted private Git repository
as well as local working copies of that repository on your respective Nike
accounts. The challenge now is how to effectively collaborate using these
three distributed copies of the repository. That is where the workflow
below comes into play. In this context, a workflow describes a sequence of
steps that you and your partner can take to effectively collaborate on the
project and avoid simple merge conflicts.

**The steps we provide in this workflow are suggested, however, they are not required.**
If you and your partner decide on a better workflow, then feel free to
use that instead. If it works really well, then I encourage you to describe it
in a Piazza post to the rest of the class.

1. Decide whose turn it is to **drive**. That person should login to
   their Nike account and change into the local working copy of their
   project's repository. The rest of these steps assume this person
   is executing the commands. When it comes time to change who is
   driving, that person should follow these steps from the beginning.

1. Use `git status` to see the state of the current branch.

   1. Regardless of what branch you are on, stage and commit all tracked files.
      If you manually added any new files, then you should add them and
      commit them.

      ```
      $ git status
      ```

   1. If the branch that you are currently on is not `master`, then
      checkout `master` and repeat the previous sub-step to make
      sure everything is committed in `master`.

      ```
      $ git checkout master
      ```

      If you want / need to, merge the changes from the other branch
      into `master`, then delete the other branch.

      ```
      $ git merge branch_name
      $ git branch -d branch_name
      ```

      **MERGE CONFLICT?** If you merge, then you may encounter a merge conflict.
      In this scenario, Git's output will tell you what files are affected. You
      must then edit those files using your text editor of choice to
      manually resolve the conflict. Git will mark lines in the affected files
      with a special `diff` syntax so that you can easily identify them, then make
      edits to either pick one version or combine them in a desired way.
      The source code should be clean and free of special `diff` syntax
      before you save. Once an affected file is saved, you should
      stage and commit it. The merge conflict is not technically resolved
      until all affected files are fixed, staged, and committed -- these
      can be individual commits or one single commit with all affected files
      on the stage.

1. Now that your local working copy is cleaned up, you should pull
   changes from your team repository into your local working copy.

   ```
   $ git pull origin master
   ```

   If you encounter a merge conflict, then see the **"MERGE CONFLICT?"**
   note provided earlier in this workflow.

1. Instead of working on your project directly in the `master` branch,
   create a branch for this programming session based on the most
   recent commit:

   ```
   $ git branch driver
   $ git checkout driver
   ```

   Alternatively, you can use:

   ```
   $ git checkout -b driver
   ```

1. Work on the project. As often as is reasonable, you should stage and
   commit tracked files in this branch. If you add new source code files
   or visual assets (e.g., images in `src/main/resources`), then be
   sure to add and commit them. Liberal use of `git status` is highly
   recommended.

1. Once you are done with this programming session, perhaps for the day
   or because you want to switch who is driving, you should do the
   following:

   1. Stage and commit tracked files. Also ensure that new files that
      you added recently are staged and committed. Remember, you can use
      `git status` to see what's going on.

   1. Checkout the `master` branch, merge `driver` into `master`, resolve
      conflicts if necessary, then delete the `driver` branch.

      ```
      $ git checkout master
      $ git merge driver
      $ git branch -f driver
      ```

      If you encounter a merge conflict, then see the **"MERGE CONFLICT?"**
      note provided earlier in this workflow.


   1. Finally, push your changes up to your team's GitHub repository.

      ```
      $ git push origin master
      ```
      If your partner worked on the project while you were also working
      on the project, then it's possible that you don't have the most
      recent changes. In this scenario, return to the first step and
      quickly repeat -- much of the work is usually minimal in this
      scenario, assuming you both espouse the same workflow.

# Appendix - FAQ

Below are some frequently asked questions related to this project.

1. **Maven doesn't work!?**

   You need to add the executable for Maven to your `PATH` environmental variable.
   Add the following lines to the end of your `~/.bash_profile` file on Nike, then logout and login:
   ```
   export MAVEN_HOME=/home/grads/mec/maven
   export PATH=$MAVEN_HOME/bin:$PATH
   ```
   If done correctly, these changes should take effect every time you login to Nike.

1. **What does "local variables referenced from a lambda expression must**
   **be final or effectively final" and how do I fix it?**

   Like local and anonymous classes, a lambda expression can only access local
   variables of the enclosing block that are `final` or effectively `final`.
   That is, a variable local to method can only be involved in the body of
   a lambda expression if it is either explicitly declared as `final` or if
   its value does not change after initialization over the entire body of
   the method. A variable is local to a method (i.e., it's a local variable)
   if it's declared inside of the method or if it's a parameter to the method.
   Please note that this restriction applies to the variables themselves and
   presents an interesting scenario in the case of local reference variables.
   A local reference variable may remain effectively `final` even if the
   internal state of the object being referenced is changed so long as the
   variable itself (i.e., the reference value) does not change.

   This problem can be usually be fixed by effectively making use of
   instance variables and/or writing methods that return an instance
   of the interface being implemented via the lambda. For example,
   consider the following scenario that results in the compile-time
   error message "local variables referenced from a lambda expression must
   be final or effectively final":

   ```java
   void someMethod() {
       for (int i = 0; i < 10; ++i) {
           EventHandler<ActionEvent> handler = e -> {
               // something involving i
               System.out.println(i);
           };
       } // for
   } // someMethod
   ```

   The variable `i` is local to `someMethod` and neither `final` nor
   effectively `final` because its value changes after each iteration
   of the for-loop. In this scenario, an instance variable is unlikely
   to be appropriate as the value of `i` itself does not need to be
   accessible to the rest of the methods in the class. A suggested way
   to fix this is to create a method that returns an object of the
   interface being implemented by the lambda expression, ensuring that
   `i` is effectively final in that method. Then, we can call that
   method in `someMethod` instead of creating the lambda there directly.
   For example:

   ```java
   EventHandler<ActionEvent> createHandler(int i) {
       EventHandler<ActionEvent> handler = e -> {
           // something involving i
           System.out.println(i);
       };
       return handler;
   } // createHandler
   ```

   ```java
   void someMethod() {
       for (int i = 0; i < 10; ++i) {
           EventHandler<ActionEvent> handler = createHandler(i);
       } // for
   } // someMethod
   ```

   In this new scenario, the variable `i` is an effectively final local variable
   in the block enclosing the lambda in `createHandler`, thus avoiding the
   problem described by the compiler.

   Why is this an issue? Well, the big reason is that the language does not support it.
   Why doesn't the language support it? I speculate that the reason has to do with
   how local variables are managed internally in memory. As methods get called and
   return they occupy and free up a region of memory called the program stack. It is
   very likely that the region of memory used by the method that created the lambda
   is freed up before the object created by the lambda is used. If the body of the
   lambda expression attempts to change the value of the variable, then what does
   that mean if the variable is not longer there!?

1. **How do I make my application not freeze/hang when executing long running event handlers?**

   For the most part, your GUI application is just like any other
   Java application you have ever written. If a line of code takes a long time to
   execute, then there is a delay before the next line of code is executed.
   This can be problematic in GUI applications since the underlying GUI
   framework, essentially, pauses what it is doing in order to do what you
   ask it to do. This can cause your GUI to hang/freeze (i.e., become unresponsive)
   when you have code blocks that take more than a few milliseconds.

   The way to solve this problem is through a basic use of threads.
   The term *thread* refers to a single thread of execution, in which
   code is executed in sequential order. When you start a Java program,
   you usually start with one thread that starts executing the `main`
   method. This thread is usually called the "main" thread. When you launch
   your JavaFX application using the `Application.launch` method, part of
   the application life-cycle is the creation of a thread for your GUI
   called the "JavaFX Event Dispatch" thread. By default, any code
   executed by or in response to your GUI components (e.g., the code for an
   event handler) takes place in the JavaFX Event Dispatch Thread. If you
   do not want your program to hang, then you will need to create a
   separate thread for your problematic code snippets. This works because
   a Java program can have multiple threads executing concurrently.

   Although we have been using "problematic code" to describe the code
   snippet causing the problem, such a code snippet really represents some
   "task" that you want your application to perform without hanging.
   Therefore, I will try to use "task" throughout the remainder of this
   response.

   To create a new thread, you need to instantiate a
   [`Thread`](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html) object
   with a [`Runnable`](https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html)
   implementation for your task. Since `Runnable` is a functional interface,
   this process is simplified using a lambda expression or method reference.
   Here is an example idiom of how to create and start a new thread for a task:
   ```java
   Runnable r = () -> {
       /* task code here */
   };
   Thread t = new Thread(r);
   t.setDaemon(true);
   t.start();
   ```
   The call to `t.setDaemon(true)` prevents this newly created thread from
   delaying program termination in the case where either the main thread
   or the JavaFX Event Dispatch thread terminate first. After the call to
   `t.start()`, both the JavaFX Event Dispatch Thread and the newly created
   thread are executing concurrently. You cannot assume that statements in
   either thread execute in any predetermined order. When writing an event
   handler that executes a task, you might do something like the following:
   ```java
   EventHandler<ActionEvent> handler = event -> {
       Runnable r = () -> {
           /* task code here */
       };
       Thread t = new Thread(r);
       t.setDaemon(true);
       t.start();
   };
   button.setOnAction(handler);
   ```
   If you understand the code snippet above, then you might instead write it
   more concisely as follows:
   ```java
   button.setOnAction(event -> {
       Thread t = new Thread(() -> {
           /* task code here */
       });
       t.setDaemon(true);
       t.start();
   });
   ```

   **Advanced:** The solution presented above is probably the simplest.
   Alternatively, you can make use of some of the classes in the
   [`javafx.concurrent`](https://docs.oracle.com/javase/8/javafx/api/javafx/concurrent/package-summary.html) package,
   which provide, among other things, the ability to control the execution and
   track the progress of code that is deferred to another thread. For more information,
   please see the [Concurrency in JavaFX](https://docs.oracle.com/javase/8/javafx/interoperability-tutorial/fx_concurrency.htm)
   tutorial.

1. **What does "Not on FX application thread" mean and how do I fix it?**

   Usually an `IllegalStateException` with the message "Not on FX application thread"
   means that you are trying to access or modify some node (i.e., a component
   or container) in the scene graph from a code snippet that is not executing
   in the JavaFX Event Dispatch thread (see Q4 in this FAQ). If you want to fix this, then
   the code snippet that interacts with the scene graph needs to be wrapped
   in a [`Runnable`](https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html)
   implementation and passed to the static `runLater` method in
   [`Platform`](https://docs.oracle.com/javase/8/javafx/api/javafx/application/Platform.html).
   Since `Runnable` is a functional interface, this process is simplified using
   a lambda expression or method reference. Here is a basic example:
   ```java
   Runnable r = () -> {
       /* place code interacting with scene graph here */
   };
   Platform.runLater(r);
   ```
   The `runLater` method ensures that the code in your `Runnable` implementation
   executes in the JavaFX Event Dispatch thread. Here is a more complete example
   that combines this scenario with the one described in Q4 of this FAQ:
   ```java
   EventHandler<ActionEvent> handler = event -> {
       Runnable r = () -> {
           /* some task code here */
           Platform.runLater(() -> { /* interact with scene graph */ });
           /* perhaps more task code here */
           Platform.runLater(() -> { /* interact with scene graph again */ });
           /* perhaps even more task code here */
       };
       Thread t = new Thread(r);
       t.setDaemon(true);
       t.start();
   };
   button.setOnAction(handler);
   ```
   If you understand the code snippet above, then you might instead write it
   more concisely as follows:
   ```java
   button.setOnAction(event -> {
       Thread t = new Thread(() -> {
           /* some task code here */
           Platform.runLater(() -> { /* interact with scene graph */ });
           /* perhaps more task code here */
           Platform.runLater(() -> { /* interact with scene graph again */ });
           /* perhaps even more task code here */
       });
       t.setDaemon(true);
       t.start();
   });
   ```
   While it might be tempting to place all of your task code in the
   `Runnable` implementation provided to `runLater`, that is not a good idea
   because it will be executed on the JavaFX Event Dispatch thread. If you
   already writing code for another thread, it was likely to avoid having it
   run on the JavaFX Event Dispatch Thread. Multiple calls to the `runLater`
   method can be used, as needed, to ensure only the code that interacts with
   the scene graph is executed in the JavaFX Event Dispatch thread.

1. **How do I make a code snippet execute repeatedly with a delay between executions?**

   The easiest way to accomplish this in a JavaFX application is using the
   [`Timeline`](https://docs.oracle.com/javase/8/javafx/api/javafx/animation/Timeline.html)
   and [`KeyFrame`](https://docs.oracle.com/javase/8/javafx/api/javafx/animation/KeyFrame.html)
   classes. Here is an example that prints the current time (using
   [`LocalTime`](https://docs.oracle.com/javase/8/docs/api/java/time/LocalTime.html)) to
   standard output every two (2) seconds (specified using
   [`Duration`](https://docs.oracle.com/javase/8/javafx/api/javafx/util/Duration.html)), indefinitely:
   ```java
   EventHandler<ActionEvent> handler = event -> System.out.println(LocalTime.now());
   KeyFrame keyFrame = new KeyFrame(Duration.seconds(2), handler);
   Timeline timeline = new Timeline();
   timeline.setCycleCount(Timeline.INDEFINITE);
   timeline.getKeyFrames().add(keyFrame);
   timeline.play();
   ```
   The `Timeline` object also has a `pause` method to pause the execution of the timeline.

1. **How do I pass around objects effectively?**

   From time to time, you may need to access one part of your app from another part of your app.
   You used a good design (e.g., classes and inheritance), but you find that you're passing a lot
   of reference variables around, perhaps through constructors. If it were one or two variables,
   then it would not be a big deal. However, you are likely reading this question because you
   are passing a lot of variables around. Your first thought might be to make those variables
   static, but that is not good for a couple different reasons--recall what it means for
   a variable to be static as well as the non-functional requirements for this project.
   Using static variables as a "go to" solution is an example of an anti-pattern, i.e.,  a
   common response to a recurring problem that is usually ineffective and risks being highly
   counterproductive.

   The recommended strategy is to add all those variables to your `Application` subclass as
   instance variables, then create getters / setters and higher level methods that interact
   with groups of variables in a controlled way. This will make the application object easier
   to work with, promote encapsulation, and help enforce self-governance of the object.
   When employing this strategy, you can then pass a reference to your application object around as needed.
   This way, you are only passing around one variable instead of many! In the other parts of your
   app (e.g., in custom components), simply call on the application object's methods to access and
   change the things you were previously passing around.

   Remember, if you are currently in your application class's `start` method, then you can pass
   a reference to the current application object into a method using the `this` reference
   variable.

1. **How do I make my game run at *x* frames per second (e.g., 60 FPS)?**

   If you are using a single `Timeline` object for your main game loop, then duration of all
   key frames in the main game loop should sum to exactly `1000 / x` ms (e.g.,
   `Duration.ofMillis(1000 / 60)`).
   The easiest way to ensure this is to have only one key frame with that duration.

1. **What is a good GUI container for games?**

   You already know that you can setup your scene graph however you want by using and nesting
   different types of layout panes. If you need explicit control over the `x` and `y` positions
   of some nodes, then I recommend using the
   [`Group`](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Group.html) class.
   Since `Group` is a sub-class of `Node`, you should be able to add a `Group` object anywhere in
   your scene graph just like any other node.

1. **How do I setup event handlers related to mouse and keyboard events?**

   Look at the Javadoc documentation for the
   [`Node`](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html) class,
   specifically the methods that start with `setOnMouse` and `setOnKey`, to see the
   different types of mouse and keyboard events you can handle. Here is a simple example
   that prints the current time (using
   [`LocalTime`](https://docs.oracle.com/javase/8/docs/api/java/time/LocalTime.html)) to
   standard output when a user clicks on an `ImageView` object (referenced by `iv`) that
   is assumed to be visible within the current scene graph:
   ```java
   iv.setOnMouseClicked(event -> System.out.println(LocalTime.now()));
   ```
   Remember, you can setup these event handlers for any node in the scene graph, including
   the containers.

1. **How can I detect if two nodes in my scene collide?**

   Look at the "Bounding Rectangles" section in the Javadoc documentation for the
   [`Node`](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html) class.
   The getter methods described in that section return references to
   [`Bounds`](https://docs.oracle.com/javase/8/javafx/api/javafx/geometry/Bounds.html)
   objects, which likely contain useful information regarding the whereabouts of
   node. I would recommend taking a very close look at the methods in that class.

1. **How do I add sound?**

   While JavaFX does support audio playback of various formats, this feature is not
   currently available over X11 forwarding from Nike. **If you incorporate audio into**
   **your application, then take measures to properly deal with the unchecked exceptions**
   **that might get thrown if audio playback is not available.**

1. **How do I make basic shapes appear?**

   Look at the classes in the
   [`javafx.scene.shape`](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/shape/package-summary.html)
   package.

1. **Git won't let me `git pull` my team's repository!?**

   When you tried to `git push`, you likely received some output similar to following

   ```
   To git@github.com:username/cs1302-arcade.git
   ! [rejected]        master -> master (non-fast-forward)
   error: failed to push some refs to 'git@github.com:username/cs1302-arcade.git'
   To prevent you from losing history, non-fast-forward updates were rejected
   Merge the remote changes before pushing again.  See the 'Note about
   fast-forwards' section of 'git push --help' for details.
   ```

   Obviously, you already read the "Note about fast-forwards", as suggested by the
   program output, by referring to:

   ```
   $ git push --help
   ```

   To summarize, this error is likely happening because your team partner has
   recently pushed some commits to your team repository on GitHub. The solution
   is contained right there in the "Note about fast-forwards":

   > If you do not want to lose your work [..] nor the work by the other person [..],
   > you would need to first fetch the history from the repository, create a history
   > that contains changes done by both parties, and push the result back.

   The easiest way to do that is to pull changes from the team repository, resolve
   merge conflicts if necessary, then push.

Have a question? Please post it on the course Piazza.

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
