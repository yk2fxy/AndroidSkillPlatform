#listview的封装
#####参考鸿洋大神的http://blog.csdn.net/lmj623565791/article/details/38902805/

###理论的东西在鸿洋大神的博客里都有了，我只是记录下自己的理解

####listview的封装分成两个部分：
        1.adapter封装：adapter的封装相对简单点，把子类基本都一样的方法实现（getCount，getItem，getItemId），然后体现不一样的方法写成抽象方法（getView），让子类去实现
        2.viewholder的封装:viewholder的封装相对复杂点，viewholder的作用就是---
     