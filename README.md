[![Build Status](https://travis-ci.org/bootique-examples/bootique-tapestry-demo.svg)](https://travis-ci.org/bootique-examples/bootique-tapestry-demo)
# bootique-tapestry-demo

An example of a simple login page developed with [Tapestry](http://tapestry.apache.org) 
integrated into [Bootique](http://bootique.io).

*For additional help/questions about this example send a message to
[Bootique forum](https://groups.google.com/forum/#!forum/bootique-user).*
   
## Prerequisites
      
    * Java 1.8 or newer.
    * Apache Maven.
      
## Build the Demo
      
Here is how to build it:
        
    git clone git@github.com:bootique-examples/bootique-tapestry-demo.git
    cd bootique-tapestry-demo
    mvn package
      
## Run the Demo

Check the options available in your app:
```bash
java -jar target/bootique-tapestry-demo-2.0.jar
```

```
OPTIONS
      -c yaml_location, --config=yaml_location
           Specifies YAML config location, which can be a file path or a URL.

      -h, --help
           Prints this message.

      -H, --help-config
           Prints information about application modules and their configuration options.

      -s, --server
           Starts Jetty server.
```

Run the demo:
```bash
java -jar target/bootique-tapestry-demo-2.0.jar --config=config.yml --server
```

Open URL:
```
http://localhost:9999/myapp
```
Let's consider some features of development with Tapestry on Bootique.

Since Bootique app is deployed as all-inclusive jar, it doesn't have *web.xml* or 
*src/main/webapp* folders. These restrictions can be bypassed, since Tapestry itself provides 
[Assets](http://tapestry.apache.org/assets.html) facility that supports storing
static content under *META-INF/assets*. E.g. style sheets under *META-INF/assets/bq-demo.css* importing:
```java
@Import(stylesheet = "bq-demo.css")
public class Login {
    //...
}
```
The basic unit in Tapestry IoC is a service. Tapestry is informed about your services and contributions 
via a module class.
```java
public class AppModule {

    public static void bind(ServiceBinder binder) {
        binder.bind(DeferredService.class, DeferredServiceImpl.class);
    }
}
```
The module is to be contributed into Bootique:
```java
TapestryModule.extend(binder)
	//...
	.addTapestryModule(AppModule.class);
```
Another useful feature of Tapestry that helps organize your code better and more logically is
[Components libraries](https://tapestry.apache.org/component-libraries.html).
```java
TapestryModule.extend(binder)
	//additional root package for the application
	.addLibraryMapping(new LibraryMapping("lib", "io.bootique.tapestry.testlib"));
 
```
The last but not least feature is [Symbols](https://tapestry.apache.org/symbols.html) effectively declared via BQ extension point:
```java
TapestryModule.extend(binder)
	.setSymbol(SymbolConstants.SUPPORTED_LOCALES, "en")
	.setSymbol(SymbolConstants.START_PAGE_NAME, "Login");
```





