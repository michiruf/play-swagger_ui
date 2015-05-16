# play-swagger-ui
Playframework 1.3 module to integrate swagger ui in your application.

## Add it to your project
Put the dependency in your <pre>dependencies.yml</pre> file:
```lang
require:
    - boblakk -> swagger_ui 0.0.1
```

Define the repository to get the module from github:
```lang
repositories:
   - boblakk-play-github:
       type:       http
       artifact:   "https://github.com/[organization]/play-[module]/raw/master/release/[module]-[revision].zip"
       contains:
           - boblakk -> *
```

## Add routes to your application
In your <pre>conf/routs</pre> file of your application put something like this:
```
# import these routes in the main app like this:
*     /api-doc                module:swagger_ui
```
You can change the path as you want.

## Configure the module
Create the file <pre>swagger_ui.yml</pre> in your applications <pre>conf</pre> directory:
```lang
showUrlInput: true
scheme: https
### If the next value is present, the host is static and not resolved by the request
host: example.com
basePath: /api
swaggerFilePath: swagston.json
```
Values passed here are used to provide information for the swagger ui to set the correct path to the
<pre>swagger.json</pre> resource. All values and this file are optional.

## Version table
Play Swagger module version     | Swagger UI version   | Play version
------------------------------- | -------------------- | ------------
0.0.1                           | 2.1.8-M1             | 1.3.1
