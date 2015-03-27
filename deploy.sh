echo "Once started, application is listening on http://localhost:8090/shop for application requests"
echo "There is some administration too on http://localhost:8444/"
echo
echo
java -jar target/shop-1.0.1.jar server shop.yml
