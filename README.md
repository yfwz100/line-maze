Project Line-Maze
=========

Project Line-Maze is initially a game project inspired by a interesting game named [Entanglement][1] which was created by [Gopherwood Studio][2]. However this project does not aim to be a clone of the game. Instead, it tries to be an execise to explore the data structure of programming and tries to be funny enough to learn data structure.

We welcome any advices on this game and willing to hear from you.

Project Structure
=========

We apply the principle "interface to action, callback to reaction" to the design of the project. And this is why interface part and the implementation part are divided clearly in the package hierarchy.

 * Package `com.ziq.linemaze` is the callback-based interface of the game and it's encourage to use in the controller. 

 * Package `com.ziq.linemaze.rect` is one of the implementation of the interface part. It implements a rectangle-based model of the game.

The project adopt the [Gradle][3] build system. You can follow the guide provided by [Gradle][3] to build the project.

Future
=========

If you have any advice, don't hesitate to [add a new issue](https://github.com/yfwz100/line-maze/issues).

[1]: http://entanglement.gopherwoodstudios.com/ "Entanglement Game"
[2]: http://gopherwoodstudios.com "Gopherwood Studio"
[3]: http://www.gradle.org "Gradle build system"

