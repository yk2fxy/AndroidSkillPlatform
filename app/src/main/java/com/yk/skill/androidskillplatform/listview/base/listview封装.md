#listview的封装
#####参考鸿洋大神的http://blog.csdn.net/lmj623565791/article/details/38902805/

###理论的东西在鸿洋大神的博客里都有了，我只是记录下自己的理解

####listview的封装分成两个部分：
        1.adapter封装：adapter的封装相对简单点，把子类基本都一样的方法实现（getCount，getItem，getItemId），然后体现不一样的方法写成抽象方法（getView），让子类去实现
        2.viewholder的封装:viewholder的封装相对复杂点。viewholder的封装分成以下几点：
            1）viewholder的作用：viewholder在listview中的作用就是让item复用---
                更详细的是，当listview滑动时，我们会把已经滑动到页面外的item拿给即将进入页面item复用，这样就不用重复创建item了；
                比如有10个item，一个页面只能显示7个item，最开始就是创建7个item的view并将1--7显示出来；然后滑动，创建item8的view，将item1移出到页面外，然后就是显示2---8；再滑动时，本来要去创建item9的view，但是item1的view已经闲置，我们就不去创建item9的view，而是将item1的view拿来给item9用。。。。以此类推。
                viewholder的复用，基本代码就是
                class ViewHolder｛
                    View view;
                ｝
                getView中的代码：
                 public View getView(int i, View view, ViewGroup viewGroup) {
                        ViewHolder holder = null;
                        if(view==null){
                            view = LayoutInflater.from(mContext).inflate(R.layout.item);
                            holder = new ViewHolder();
                            holder.view = view.findViewById(R.id.view);
                            view.setTag(holder);
                        }else{
                            holder = (ViewHolder) view.getTag();
                        }
                        /**
                         * 处理item中的view
                         */
                        return view;
                    }
                  ｝  
             2）viewholder抽取：把getview中的关于ViewHolder的代码抽取出来放到ViewHolder中去做，
             class ViewHolder｛
                View rootView;
                public ViewHolder（int position，View rootView，Context context）{                    
                    rootView = LayoutInflater.from(context).inflate(R.layout.item);
                    rootView.setTag(this);
                }
                public ViewHolder getIntance(int postion,View rootView,Context context){
                    ViewHolder holder = null;
                    if(rootView ==null){
                        holder = new ViewHolder(postion,rootView,context);
                    }else{
                        holder = (ViewHolder)rootView.getTag();
                    }
                    return holder;
                }
                public View getRootView(){
                    return rootView;
                }
             ｝
             上面是基本的ViewHolder的抽取封装。还有问题就是ViewHolder是存放item的view的，我们view都还没放到holder中。
             3）ViewHolder中item中view处理：listview 中 item是可以自定义，定义成什么样我们前期无法决定，所以就用一个数组来存放item中的view
             class ViewHolder｛
                 View rootView;
                 private SparseArray<View> views;
                 public ViewHolder（int position，View rootView，Context context）{
                     rootView = LayoutInflater.from(context).inflate(R.layout.item);
                     rootView.setTag(this);
                     views = new SparseArray<View>();
                 }
                 public ViewHolder getIntance(int postion,View rootView,Context context){
                     ViewHolder holder = null;
                     if(rootView ==null){
                         holder = new ViewHolder(postion,rootView,context);
                     }else{
                         holder = (ViewHolder)rootView.getTag();
                     }
                     return holder;
                 }
                 public <T extends View> T getView(int viewId){
                     View view = views.get(viewId);
                     if(view==null){
                         view = rootView.findViewById(viewId);
                         views.put(viewId,view);
                     }
                     return (T) view;
                 }
                 public View getRootView(){
                     return rootView;
                 }
             ｝
             至此，listview的adapter就封装完成了。
 ###其实现在recyclerview的功能已经比listview强大很多了，为什么要封装listview呢？
    listview的adpter封装后，实现listview已经变的很简单了，然后就是复杂的用recyclerview，简单的用listview，处理简单。