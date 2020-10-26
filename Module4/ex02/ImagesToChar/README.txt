Links for downloading library:

    JCommander https://mvnrepository.com/artifact/com.beust/jcommander/1.78
    JColor https://mvnrepository.com/artifact/com.diogonunes/JColor/5.0.1

Add library to directory *lib*:

    mkdir lib
    cp path/to/lib lib

For compilation:

    javac -sourcepath ./src/java -d target -classpath ./lib/*:./lib/* src/java/edu/school21/printer/app/Program.java

Copy resources:

    cp -r src/resources target


Compiling library: 

    cd target/
    jar xvf ../lib/jcommander-1.78.jar com/
    jar xvf ../lib/JColor-5.0.1.jar com/
    cd ..

For creating jar-archive:

    jar cvfm target/images-to-chars-printer.jar src/manifest.txt -C target/ . -C src/resources/ .

For running jar:

    java -jar target/images-to-chars-printer.jar

