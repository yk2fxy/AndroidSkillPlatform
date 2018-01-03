#index

##此目录包含IndexAcitivity、IndexPresent、IndexViewDetail三个文件，三个文件以MVP模式构成一个index界面

##此页面的比较简单，只有展示fragment和fragment的切换，涉及到业务处理和数据比较少，所以就没有写model层；其中IndexViewDetail和IndexAcitivity是view层代码，IndexPresent是present层代码。

##mvp的优势：代码结构更加清晰，更容易理解；

##做一个mvc和mvp的简单对比（都是我自己的理解，可能和标准的定义有差距），详细的百度“mvp模式”会有很多介绍的

###1.mvc：这个我们从学android开始就一直在使用的，也是一个经典的模式。
    view（视图）就是我们xml定义的layout、control（控制器）对应的是acitivity、model（数据模型）对应的数据bean
###2.mvp：对mvc进行了一些改造
    在以前的mvc模式中acitivity所承受的太多了：跟view有关的操作--设置和获取等都会在activity中完成，导致activity比较臃肿
    mvp所做的工作就是用接口把设置和获取这些操作提取出来，然后用present来实现，并在present中去和model层连接在一起。

##