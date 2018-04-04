##1.set up database
```
mysql> create database cbio_data
```
##2.grant permissions to a user
```
mysql> grant all privileges on cbio_data.* to 'cbio_user'@'localhost' identified by 'somepassword'
```
##3.build from source
```
mvn -X clean compile install
```
##4.deploy to the site
```
cd /usr/local/tomcat/webapps
mkdir api
cd api
cp ~/myspace/webapi/target/webapi-0.0.1-SNAPSHOT.war ./
mv webapi-0.0.1-SNAPSHOT.war api.war
```
##5.start tomcat
```
/usr/local/tomocat/bin/catalina.sh start
```
##6.view swagger documents
http://localhost:8081/api/swagger-ui.html
