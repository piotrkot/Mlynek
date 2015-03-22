echo "Once started, application is listening on http://localhost:8090/shop for application requests"
echo "There is some administration too on http://localhost:8081/"
echo
echo
java -jar target/shop-1.0-SNAPSHOT.jar server shop.yml
