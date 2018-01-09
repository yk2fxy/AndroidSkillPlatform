##tinker 接入

1. 项目build.gradle的配置
    在dependencies添加classpath('com.tencent.tinker:tinker-patch-gradle-plugin:1.9.1')
2. app目录下build.gradle的配置
    这个需要配置的地方比较多，主要参考tinker 的GitHub上的build.gradle
3. 创建一个application继承DefaultApplicationLike
4. 在需要patch的地方调用TinkerInstaller.onReceiveUpgradePatch(context,patchPkgPath+"xxx.apk")

##问题分析
    出现code=-2 则检查热更新的路径，获取检查热更新文件是否有权限
    tinker_id出错，把tinker_id写成一个默认值
    