# AndroidSkillPlatform
	对AndroidSkillPlatform进行重构，首先对app现有功能以及对即将加入的功能进行分析，并划分成各种模；其次就是重构的具体工作。
一、功能分析（需求分析）
	AndroidSkillPlatform所展示的现有的Android技能包括：自定view（自定义导航栏、自定义码表、双缓冲画布、签字面板等）、常用的Android控件（listview封装、webview的使用、动画的使用等）、第三方框架（tinker（热修复）、butterknife（注解辅助）、vitualapk（插件化））、mvp、kotlin。
	重构后新的app将会增加模块化功能、代码由java转到kotlin、全部支持mvp模式以及其他更多的自定义view和第三方框架、常用的控件介绍等，还有就是关于生命周期管理、内存和性能优化等，重构后的代码还会参照阿里出的《Android开发规范》和《java开发规范》。	
二、步骤
	因为代码将由java转到kotlin、要全部支持mvp模式以及代码和命名规范化，所以app基本算是重新开始。
	1.模块化：
		第一步：进行模块化拆分。我遵循的原则是，按照类别划分，比如常用的控件作为一个组件、第三方框架使用作为一个组件、自定义view设计使用作为一个组件、基础库组件、路由组件、base库组件，这样总共就有6个模块加一个app壳工程。（这里有几点需要特别说明-----1）第三方框架的使用作为一个组件，其实在实际的拆分过程中，第三方的框架库是被引入到基础库组件中的，这里只是把第三方框架的使用以及使用场景作为一个组件；2）自定义view和常用控件的封装，对于一些其他组件也用得到的自定view和常用控件封装，这个还是需要放到base库组件中，比如封装好的listview、自定义的导航栏等。）
	 
	拆分后的模块化如上图所示，从下往上依次是---
	1）base库：包含常用的控件封装、常用的自定义控件、baseApplictation、baseActivity、一些工具类的封装；
	2）第三方库：包含所需要的第三方框架有rxjava（异步调用的库）、okhttp（网络通信）、retrofit（网络通信）、greendao（数据库）、glide（图片加载处理）（这里不会用到butterknife，因为kotlin已经很好用了）、aroute（工模块间调整通信用的）；
	3）基础依赖库：包含了base库和第三方依赖库，这层库主要就是工上层的几个；
	4）几个模块：第三方库使用场景介绍模块、常用控件封装使用模块、自定义view使用场景模块，模块间的通信与跳转，用aroute（也可以自己实现）；
	5）app壳工程：加载几个模块的主工程
	模块化的工程目录结构如下图所示：模块都选择android library的模式创建。
 
	第二步，
		1）在根目录下的gradle.properties文件中写个变量“isModule=true”，这个是切换模块是以app形式还是library形式运行的标志；
		2）修改每个组件的build.gradle文件，在gradle文件中增加判断是application还是library（引用上一步的“isModule”来判断），有三点是必须要修改的----①文件的起始位置，增加判断，如果是单独的app，则用“apply plugin: 'com.android.application'”（让组件以application打包运行），否则用“apply plugin: 'com.android.library'”；②判断如果是application，则需要增加一个applicationId；③在组件目录中新建一个AndroidManifest.xml文件，让组件以不同形态打包和运行时，去使用不同的AndroidManifest.xml文件，因为以单独的app运行，需要有一个默认的Activity入口。修改后的代码如下：
 
 
		到这里，组件化的框架其实已经搭建好了，已经可以进行组件化开发了。但是，还是有几个建议要做的，就是基础的application，activity以及组件间耦合需要用的路由框架，还有组件中用到的库、插件的版本号抽取（为了统一所有插件和库版本）。application和activity两个创建基础类的目的是为了更好的管理application和activity生命周期；路由框架的接入，是为了让组件之间的耦合降低。

	3、基础库的封装、第三方框架的引入、插件版本号的抽取
	1)插件版本号的抽取：

