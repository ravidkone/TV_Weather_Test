# TV_Weather_Test
## How to Run the project using terminal/cmd

## Pre-required
```
Install JDK 1.8 
Maven 3.6.3 [add maven installation path to PATH env variable] 
```

Check if both installed properly in cmd/terminal by entering below commands
```
java -version  		[it should show the installed version]
mvn -version 		[it should show the installed version]
```

Git Repository URL: https://github.com/ravidkone/TV_Weather_Test

## Steps to execute:
Step 1: Go to your cmd/terminal and and create a folder
        inside that folder clone the git repository and move to the new folder
```
  $ mkdir “run_test” 
  $ cd run_test
  $ git clone https://github.com/ravidkone/TV_Weather_Test
``` 

 cd “project folder name” [change directory to project folder]

Once we are inside the project folder enter below maven commands to execute the project
```
$ mvn clean     // [it will scan the project and do a clean install]
$ mvn test      // [this will start executing the test cases]


Note: for the first time it will download the all the required jars(it may take little time) 
and then execute the test cases, from second time onward it will be faster.
```
To change the city name, enter below cmd from project folder which will redirect to config folder and open config file
Change the city name to check details in web UI.

##### In MAC

Once you opened a file with vim you can insert text by typing i, for instance.
If you want to save your file use :w (write) or :q (quit) or :wq (for write and quit) or :q! (quit and do not save). 
Sometimes you need to hit the ESC key to be able to type the commands.
```

cd TV_Weather_Test\src\main\java\com\tv\properties\config.properties
vi config.properties
```

##### In Windows
Open the file by choosing appropriate app(note pad) and change city value to other city name and save it.
```
cd TV_Weather_Test\src\main\java\com\tv\properties\config.properties
```




Move to back to Project folder and run again below cmd, now Firefox browser will be triggered
```
mvn clean
mvn test
```

## How to open the project in Eclipse IDE
Step 1: Open Eclipse IDE, click on File Tab and select "Open Projects from File System.." 
#
Step 2: It will open dialogue box, click on "directory" and choose the project folder and click on Finish

## Project Details
    - Under "src/main/java" we can find base,config,report and utility packages
    - Under "src/main/test" we can find the testcases and POMs.
    - Under "TestReport" folder, we can find the test reports for each execution
            its a customer report created, which will have logs.
    - Under "screenshots", we can find the screenshot of error occured during the excution.
    - Under "test-output"/index.html will provide the testNG html report
    - Under "pom.xml" we can find all the dependedancies and plugins
