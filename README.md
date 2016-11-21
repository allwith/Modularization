# Android 组件化

A routing framework support via the url to jump to the Activity!

*	核心原理：UrlRouter

*	目的：模块解耦
	*	可取代使用startActivity、startActivityForResult跳转的情景，便于协同开发
	*	通过一串url可任意跳转到指定界面，使用应尽可能简单
	*	支持各种类型参数传递、界面转场动画
	*	可获取起跳界面的路径和当前界面路径，以便支持后期埋点等需求
	*	支持从H5到Native，Native到H5，这是Hybrid开发模式中常用到的需求
	*	对于push、浏览器外链跳转等可方便配置化，通过一个url来跳转指定界面

*	格式：scheme://host/path
	*	scheme：APP内自己定义的，不过这个在H5内跳Native时，需要和前端协商定义好，本地间的跳转可以随自己定义，比如：activity
	*	host：这个尽可能按各个Activity的所在模块命名
	*	path：各个Activity的功能名

*	一个栗子
	*	这样一来，比如我跳转到商品详情页的url：activity://product/detail

----

# 使用
[设计核心原理](http://zhengxiaoyong.me/2016/04/24/UrlRouter%E8%B7%AF%E7%94%B1%E6%A1%86%E6%9E%B6%E7%9A%84%E8%AE%BE%E8%AE%A1/)
