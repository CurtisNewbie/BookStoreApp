echo "-----------------------------------------------"
echo
echo "	BookStoreApp - Bundled Build"
echo
echo "	By Yongjie Zhuang (CurtisNewbie)"
echo
echo "-----------------------------------------------"

echo ">>> Building Angular frontend"
(cd ../frontend/frontend; ng build --prod --base-href="/frontend/")
echo

echo ">>> Copying Angular frontend build files to quarkus backend"
cp -r ../frontend/frontend/dist/* ../backend/backend/src/main/resources/META-INF/resources/

echo ">>> Building Angular admin"
(cd ../admin/admin; ng build --prod --base-href="/admin/")
echo

echo ">>> Copying Angular frontend build files to quarkus backend"
cp -r ../admin/admin/dist/* ../backend/backend/src/main/resources/META-INF/resources/

echo ">>> Building quarkus backend"
mvn -f ../backend/backend clean package
echo

echo ">>> Moving bundled build to root dir"
mv ../backend/backend/target/backend-*-runner.jar .

echo ">>> Removing Angular build files in quarkus backend working dir"
rm -rvf ../frontend/frontend/dist/* ../backend/backend/src/main/resources/META-INF/resources/*

echo ">>> Done!"
echo ">>> Created at:"
ls -lth ./*.jar

