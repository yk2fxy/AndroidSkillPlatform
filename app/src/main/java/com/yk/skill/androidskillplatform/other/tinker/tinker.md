##tinker 接入[tinker](https://github.com/Tencent/tinker)

1. 项目build.gradle的配置
    在dependencies添加
    ```
     classpath('com.tencent.tinker:tinker-patch-gradle-plugin:1.9.1')
    ```
2. app目录下build.gradle的配置
    这个需要配置的地方比较多，主要参考tinker 的GitHub上的build.gradle
    ```
    apply plugin: 'com.android.application'
    apply plugin: 'com.tencent.tinker.patch'
    android {
        signingConfigs {
            release {
                keyAlias 'key0'
                keyPassword '123456'
                storeFile file('../keystore/skill.jks')
                storePassword '123456'
            }
        }
        compileSdkVersion 25
        buildToolsVersion '26.0.2'
        defaultConfig {
            applicationId "com.yk.skill.androidskillplatform"
            minSdkVersion 17
            targetSdkVersion 25
            versionCode 1
            versionName "1.0"
            testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
            multiDexEnabled true
            buildConfigField "String", "MESSAGE", "\"I am the base apk\""
    
            buildConfigField "String", "TINKER_ID", "\"1.0\""
            buildConfigField "String", "PLATFORM", "\"all\""
        }
        dexOptions {
            jumboMode = true
        }
        buildTypes {
            release {
                minifyEnabled true
                proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
                signingConfig signingConfigs.release
            }
        }
        dependencies {
            compile fileTree(dir: 'libs', include: ['*.jar'])
            androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
                exclude group: 'com.android.support', module: 'support-annotations'
            })
            
            annotationProcessor 'com.tencent.tinker:tinker-android-anno:1.9.1'
            provided 'com.tencent.tinker:tinker-android-anno:1.9.1'
            compile 'com.tencent.tinker:tinker-android-lib:1.9.1'
            compile 'com.android.support:multidex:1.0.1'
            
    // Because RxAndroid releases are few and far between, it is recommended you also
    // explicitly depend on RxJava's latest version for bug fixes and new features.
            compile 'io.reactivex.rxjava2:rxjava:2.1.7'
            compile 'com.squareup.retrofit2:retrofit:2.3.0'
        }
        compileOptions {
            targetCompatibility 1.8
            sourceCompatibility 1.8
        }
    }
    repositories {
        mavenCentral()
        google()
    }
    
    def gitSha() {
        try {
            String gitRev = 'git rev-parse --short HEAD'.execute(null, project.rootDir).text.trim()
            if (gitRev == null) {
                throw new GradleException("can't get git rev, you should add git to system path or just input test value, such as 'testTinkerId'")
            }
            return gitRev
        } catch (Exception e) {
            throw new GradleException("can't get git rev, you should add git to system path or just input test value, such as 'testTinkerId'")
        }
    }
    
    def javaVersion = JavaVersion.VERSION_1_7
    def bakPath = file("${buildDir}/bakApk/")
    
    /**
     * you can use assembleRelease to build you base apk
     * use tinkerPatchRelease -POLD_APK=  -PAPPLY_MAPPING=  -PAPPLY_RESOURCE= to build patch
     * add apk from the build/bakApk
     */
    ext {
        //for some reason, you may want to ignore tinkerBuild, such as instant run debug build?
        tinkerEnabled = true
    
        //for normal build
        //old apk file to build patch apk
        tinkerOldApkPath = "${bakPath}/app-release-0109-10-01-47.apk"
        //proguard mapping file to build patch apk
        tinkerApplyMappingPath = "${bakPath}/app-release-0109-10-01-47-mapping.txt"
        //resource R.txt to build patch apk, must input if there is resource changed
        tinkerApplyResourcePath = "${bakPath}/app-release-0109-10-01-47-R.txt"
    
        //only use for build all flavor, if not, just ignore this field
        tinkerBuildFlavorDirectory = "${bakPath}/app-1018-17-32-47"
    }
    
    
    def getOldApkPath() {
        return hasProperty("OLD_APK") ? OLD_APK : ext.tinkerOldApkPath
    }
    
    def getApplyMappingPath() {
        return hasProperty("APPLY_MAPPING") ? APPLY_MAPPING : ext.tinkerApplyMappingPath
    }
    
    def getApplyResourceMappingPath() {
        return hasProperty("APPLY_RESOURCE") ? APPLY_RESOURCE : ext.tinkerApplyResourcePath
    }
    
    def getTinkerIdValue() {
        return hasProperty("TINKER_ID") ? TINKER_ID : gitSha()
    }
    
    def buildWithTinker() {
        return hasProperty("TINKER_ENABLE") ? TINKER_ENABLE : ext.tinkerEnabled
    }
    
    def getTinkerBuildFlavorDirectory() {
        return ext.tinkerBuildFlavorDirectory
    }
    
    if (buildWithTinker()) {
        apply plugin: 'com.tencent.tinker.patch'
    
        tinkerPatch {
            /**
             * necessary，default 'null'
             * the old apk path, use to diff with the new apk to build
             * add apk from the build/bakApk
             */
            oldApk = getOldApkPath()
            /**
             * optional，default 'false'
             * there are some cases we may get some warnings
             * if ignoreWarning is true, we would just assert the patch process
             * case 1: minSdkVersion is below 14, but you are using dexMode with raw.
             *         it must be crash when load.
             * case 2: newly added Android Component in AndroidManifest.xml,
             *         it must be crash when load.
             * case 3: loader classes in dex.loader{} are not keep in the main dex,
             *         it must be let tinker not work.
             * case 4: loader classes in dex.loader{} changes,
             *         loader classes is ues to load patch dex. it is useless to change them.
             *         it won't crash, but these changes can't effect. you may ignore it
             * case 5: resources.arsc has changed, but we don't use applyResourceMapping to build
             */
            ignoreWarning = false
    
            /**
             * optional，default 'true'
             * whether sign the patch file
             * if not, you must do yourself. otherwise it can't check success during the patch loading
             * we will use the sign config with your build type
             */
            useSign = true
    
            /**
             * optional，default 'true'
             * whether use tinker to build
             */
            tinkerEnable = buildWithTinker()
    
            /**
             * Warning, applyMapping will affect the normal android build!
             */
            buildConfig {
                /**
                 * optional，default 'null'
                 * if we use tinkerPatch to build the patch apk, you'd better to apply the old
                 * apk mapping file if minifyEnabled is enable!
                 * Warning:
                 * you must be careful that it will affect the normal assemble build!
                 */
                applyMapping = getApplyMappingPath()
                /**
                 * optional，default 'null'
                 * It is nice to keep the resource id from R.txt file to reduce java changes
                 */
                applyResourceMapping = getApplyResourceMappingPath()
    
                /**
                 * necessary，default 'null'
                 * because we don't want to check the base apk with md5 in the runtime(it is slow)
                 * tinkerId is use to identify the unique base apk when the patch is tried to apply.
                 * we can use git rev, svn rev or simply versionCode.
                 * we will gen the tinkerId in your manifest automatic
                 */
                tinkerId = getTinkerIdValue()
    
                /**
                 * if keepDexApply is true, class in which dex refer to the old apk.
                 * open this can reduce the dex diff file size.
                 */
                keepDexApply = false
    
                /**
                 * optional, default 'false'
                 * Whether tinker should treat the base apk as the one being protected by app
                 * protection tools.
                 * If this attribute is true, the generated patch package will contain a
                 * dex including all changed classes instead of any dexdiff patch-info files.
                 */
                isProtectedApp = false
    
                /**
                 * optional, default 'false'
                 * Whether tinker should support component hotplug (add new component dynamically).
                 * If this attribute is true, the component added in new apk will be available after
                 * patch is successfully loaded. Otherwise an error would be announced when generating patch
                 * on compile-time.
                 *
                 * <b>Notice that currently this feature is incubating and only support NON-EXPORTED Activity</b>
                 */
                supportHotplugComponent = false
            }
    
            dex {
                /**
                 * optional，default 'jar'
                 * only can be 'raw' or 'jar'. for raw, we would keep its original format
                 * for jar, we would repack dexes with zip format.
                 * if you want to support below 14, you must use jar
                 * or you want to save rom or check quicker, you can use raw mode also
                 */
                dexMode = "jar"
    
                /**
                 * necessary，default '[]'
                 * what dexes in apk are expected to deal with tinkerPatch
                 * it support * or ? pattern.
                 */
                pattern = ["classes*.dex",
                           "assets/secondary-dex-?.jar"]
                /**
                 * necessary，default '[]'
                 * Warning, it is very very important, loader classes can't change with patch.
                 * thus, they will be removed from patch dexes.
                 * you must put the following class into main dex.
                 * Simply, you should add your own application {@code tinker.sample.android.SampleApplication}
                 * own tinkerLoader, and the classes you use in them
                 *
                 */
                loader = [
                        //use sample, let BaseBuildInfo unchangeable with tinker
                        "tinker.sample.android.app.BaseBuildInfo"
                ]
            }
    
            lib {
                /**
                 * optional，default '[]'
                 * what library in apk are expected to deal with tinkerPatch
                 * it support * or ? pattern.
                 * for library in assets, we would just recover them in the patch directory
                 * you can get them in TinkerLoadResult with Tinker
                 */
                pattern = ["lib/*/*.so"]
            }
    
            res {
                /**
                 * optional，default '[]'
                 * what resource in apk are expected to deal with tinkerPatch
                 * it support * or ? pattern.
                 * you must include all your resources in apk here,
                 * otherwise, they won't repack in the new apk resources.
                 */
                pattern = ["res/*", "assets/*", "resources.arsc", "AndroidManifest.xml"]
    
                /**
                 * optional，default '[]'
                 * the resource file exclude patterns, ignore add, delete or modify resource change
                 * it support * or ? pattern.
                 * Warning, we can only use for files no relative with resources.arsc
                 */
                ignoreChange = ["assets/sample_meta.txt"]
    
                /**
                 * default 100kb
                 * for modify resource, if it is larger than 'largeModSize'
                 * we would like to use bsdiff algorithm to reduce patch file size
                 */
                largeModSize = 100
            }
    
            packageConfig {
                /**
                 * optional，default 'TINKER_ID, TINKER_ID_VALUE' 'NEW_TINKER_ID, NEW_TINKER_ID_VALUE'
                 * package meta file gen. path is assets/package_meta.txt in patch file
                 * you can use securityCheck.getPackageProperties() in your ownPackageCheck method
                 * or TinkerLoadResult.getPackageConfigByName
                 * we will get the TINKER_ID from the old apk manifest for you automatic,
                 * other config files (such as patchMessage below)is not necessary
                 */
                configField("patchMessage", "tinker is sample to use")
                /**
                 * just a sample case, you can use such as sdkVersion, brand, channel...
                 * you can parse it in the SamplePatchListener.
                 * Then you can use patch conditional!
                 */
                configField("platform", "all")
                /**
                 * patch version via packageConfig
                 */
                configField("patchVersion", "1.0")
            }
            //or you can add config filed outside, or get meta value from old apk
            //project.tinkerPatch.packageConfig.configField("test1", project.tinkerPatch.packageConfig.getMetaDataFromOldApk("Test"))
            //project.tinkerPatch.packageConfig.configField("test2", "sample")
    
            /**
             * if you don't use zipArtifact or path, we just use 7za to try
             */
            sevenZip {
                /**
                 * optional，default '7za'
                 * the 7zip artifact path, it will use the right 7za with your platform
                 */
                zipArtifact = "com.tencent.mm:SevenZip:1.1.10"
                /**
                 * optional，default '7za'
                 * you can specify the 7za path yourself, it will overwrite the zipArtifact value
                 */
    //        path = "/usr/local/bin/7za"
            }
        }
    
        List<String> flavors = new ArrayList<>();
        project.android.productFlavors.each { flavor ->
            flavors.add(flavor.name)
        }
        boolean hasFlavors = flavors.size() > 0
        def date = new Date().format("MMdd-HH-mm-ss")
    
        /**
         * bak apk and mapping
         */
        android.applicationVariants.all { variant ->
            /**
             * task type, you want to bak
             */
            def taskName = variant.name
    
            tasks.all {
                if ("assemble${taskName.capitalize()}".equalsIgnoreCase(it.name)) {
    
                    it.doLast {
                        copy {
                            def fileNamePrefix = "${project.name}-${variant.baseName}"
                            def newFileNamePrefix = hasFlavors ? "${fileNamePrefix}" : "${fileNamePrefix}-${date}"
    
                            def destPath = hasFlavors ? file("${bakPath}/${project.name}-${date}/${variant.flavorName}") : bakPath
                            from variant.outputs.first().outputFile
                            into destPath
                            rename { String fileName ->
                                fileName.replace("${fileNamePrefix}.apk", "${newFileNamePrefix}.apk")
                            }
    
                            from "${buildDir}/outputs/mapping/${variant.dirName}/mapping.txt"
                            into destPath
                            rename { String fileName ->
                                fileName.replace("mapping.txt", "${newFileNamePrefix}-mapping.txt")
                            }
    
                            from "${buildDir}/intermediates/symbols/${variant.dirName}/R.txt"
                            into destPath
                            rename { String fileName ->
                                fileName.replace("R.txt", "${newFileNamePrefix}-R.txt")
                            }
                        }
                    }
                }
            }
        }
        project.afterEvaluate {
            //sample use for build all flavor for one time
            if (hasFlavors) {
                task(tinkerPatchAllFlavorRelease) {
                    group = 'tinker'
                    def originOldPath = getTinkerBuildFlavorDirectory()
                    for (String flavor : flavors) {
                        def tinkerTask = tasks.getByName("tinkerPatch${flavor.capitalize()}Release")
                        dependsOn tinkerTask
                        def preAssembleTask = tasks.getByName("process${flavor.capitalize()}ReleaseManifest")
                        preAssembleTask.doFirst {
                            String flavorName = preAssembleTask.name.substring(7, 8).toLowerCase() + preAssembleTask.name.substring(8, preAssembleTask.name.length() - 15)
                            project.tinkerPatch.oldApk = "${originOldPath}/${flavorName}/${project.name}-${flavorName}-release.apk"
                            project.tinkerPatch.buildConfig.applyMapping = "${originOldPath}/${flavorName}/${project.name}-${flavorName}-release-mapping.txt"
                            project.tinkerPatch.buildConfig.applyResourceMapping = "${originOldPath}/${flavorName}/${project.name}-${flavorName}-release-R.txt"
    
                        }
    
                    }
                }
    
                task(tinkerPatchAllFlavorDebug) {
                    group = 'tinker'
                    def originOldPath = getTinkerBuildFlavorDirectory()
                    for (String flavor : flavors) {
                        def tinkerTask = tasks.getByName("tinkerPatch${flavor.capitalize()}Debug")
                        dependsOn tinkerTask
                        def preAssembleTask = tasks.getByName("process${flavor.capitalize()}DebugManifest")
                        preAssembleTask.doFirst {
                            String flavorName = preAssembleTask.name.substring(7, 8).toLowerCase() + preAssembleTask.name.substring(8, preAssembleTask.name.length() - 13)
                            project.tinkerPatch.oldApk = "${originOldPath}/${flavorName}/${project.name}-${flavorName}-debug.apk"
                            project.tinkerPatch.buildConfig.applyMapping = "${originOldPath}/${flavorName}/${project.name}-${flavorName}-debug-mapping.txt"
                            project.tinkerPatch.buildConfig.applyResourceMapping = "${originOldPath}/${flavorName}/${project.name}-${flavorName}-debug-R.txt"
                        }
    
                    }
                }
            }
        }
    }
    ```
    这里需要注意下Android-->defaultConfig下的**TINKER_ID**这个有可能报tinker_id错误，我是把这里修改成一个默认的值。
        ```
        buildConfigField "String", "TINKER_ID", "\"1.0\""
        ```
        还有一个地方需要针对性的修改:这里需要填入你原始包的对应的名词，如果没有修改过build.gradle文件，则默认在app/build/bakApk目录下
        ```
        ext {
        tinkerEnabled = true
        tinkerOldApkPath = "${bakPath}/app-release-0109-10-01-47.apk"
        tinkerApplyMappingPath = "${bakPath}/app-release-0109-10-01-47-mapping.txt"
        tinkerApplyResourcePath = "${bakPath}/app-release-0109-10-01-47-R.txt"
    }
        ```
3. 创建一个application继承DefaultApplicationLike,
```
package com.yk.skill.androidskillplatform;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDex;

import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

@DefaultLifeCycle(application = "com.yk.skill.androidskillplatform.TinkerApplicationLike",flags = ShareConstants.TINKER_ENABLE_ALL,loadVerifyFlag = false)
public class SelfTinkerApplicationLike extends DefaultApplicationLike{

    public SelfTinkerApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }
    /**
     * install multiDex before install tinker
     * so we don't need to put the tinker lib classes in the main dex
     *
     * @param base
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        //you must install multiDex whatever tinker is installed!
        MultiDex.install(base);

        /*SelfTinkerApplicationLike.application = getApplication();
        SelfTinkerApplicationLike.context = getApplication();
        TinkerManager.setTinkerApplicationLike(this);

        TinkerManager.initFastCrashProtect();
        //should set before tinker is installed
        TinkerManager.setUpgradeRetryEnable(true);

        //optional set logIml, or you can use default debug log
        TinkerInstaller.setLogIml(new MyLogImp());*/

        //installTinker after load multiDex
        //or you can put com.tencent.tinker.** to main dex
        TinkerInstaller.install(this);
        //Tinker tinker = Tinker.with(getApplication());
    }

}

```
4. 在需要patch的地方调用
```
TinkerInstaller.onReceiveUpgradePatch(context,patchPkgPath+"xxx.apk")
```
TinkerInstaller.onReceiveUpgradePatch(context,patchPkgPath+"xxx.apk")

##tinker更新包的生成
1. 先生成一个基础包：点击Android-studio的右侧的gradle，然后按图示展开，最后点击assembleRelease生成基础包（基础包的位置在app/build/bakApk目录下）
	![这里写图片描述](http://img.blog.csdn.net/20180111111526985?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQveWsyZnh5/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
2. 修改build.gradle中对应的名词：生成基础包后，按照图示的标记修改build.gradle中内容，必须要对应
![这里写图片描述](http://img.blog.csdn.net/20180111111843471?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQveWsyZnh5/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
3. 生成patch包：按照图示生成patch包
![这里写图片描述](http://img.blog.csdn.net/20180111112014655?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQveWsyZnh5/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
4. 将patch包放入到sd卡中对应的位置即可
patch包的位置对应就是“TinkerInstaller.onReceiveUpgradePatch”这个方法中对应的位置，包名和路径必须匹配

```
TinkerInstaller.onReceiveUpgradePatch(context,patchPkgPath+"xxx.apk")
```

##问题分析
    1. 出现code=-2 则检查热更新的路径，获取检查热更新文件是否有权限
    2. tinker_id出错，把tinker_id写成一个默认值
    