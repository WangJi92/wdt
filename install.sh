#!/bin/sh -x
dir=$(cd `dirname $0`;pwd)
echo $dir
sed -i '' '/^alias wdt=/d' ~/.bash_profile
echo "alias wdt='cd $dir && java -jar $dir/target/wdt.jar'" >> ~/.bash_profile
echo "source ~/.bash_profile"
exec ./mvnw clean install -DskipTests
