# A Scala RSS Reader Library

The purpose of this project is to provide a little library for reading RSS feeds
from Scala applications. Because an RSS feed is just XML, this library uses Scala’s
XML parsing library (which is now a standalone library). It also uses the ScalaJ
library if you want to use it to read an RSS feed URL.

Of course if you don’t like the code as is, feel free to just use this library as
a “demo” that shows what you have to do to read an RSS feed.

See the “Driver” classes for examples of how to use the methods in the library.


## Building a Jar file

To build a single Jar file that includes the XML and ScalaJ dependencies,
use this command:

````
sbt assembly
````

If you don’t want to do that, you can include those dependencies manually 
in your project, and use this command to create a Jar file that only contains
the classes from this project:

````
sbt package
````

The output of those two commands will give you the name of the Jar file
that was built.


## Created by

Alvin Alexander    
https://alvinalexander.com

