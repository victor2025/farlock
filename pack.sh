# osname=$(uname -s|tr A-Z a-z)
# 处理win下的osname
result=$(echo $osname|grep "mingw")
if [ "$result" != "" ]
then
	osname="win"
fi
# 处理其他名称
# archname=$(uname -m|tr A-Z a-z)
version=$(git describe --tags `git rev-list --tags --max-count=1`)
# 处理版本
version=${version#v}
# 设置文件夹
dirname=farlock-spring-boot-starter-$version
# 编译打包
# 移动文件
mkdir $dirname
cp out/artifacts/farlock_spring_boot_starter_jar/farlock-spring-boot-starter.jar $dirname/$dirname.jar
# 创建release文件夹
mkdir releases
mv $dirname releases/$dirname