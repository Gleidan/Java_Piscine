For compilation:

    javac -sourcepath ./src/java -d target src/java/edu/school21/printer/app/Program.java

Copy resources:

    cp -r src/resources target

For creating jar-archive:

    jar cvfm target/images-to-chars-printer.jar src/manifest.txt -C target/ . -C src/resources/ .

For running jar:

    java -jar target/images-to-chars-printer.jar

