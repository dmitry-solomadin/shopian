#!/bin/bash
#echo "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
#echo "!!!!!!   NOT FORGET run: Menu -> Build -> Make Grail Web Archive   !!!!!!!!!!"
#echo "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"

echo "upload to server??? [y,n]"
read -n 1 doIt
case $doIt in
    y|Y)
        echo "Uploading WAR..."
        rsync -r -v --progress -e "ssh -p2210" target/shopian3-spring.war lafox@lafox.net:/var/lib/tomcat7/webapps/ROOT.war
        ;;
    n|N)
        echo "Canceled!!!"
        ;;
    *)
        echo dont know
        ;;
esac