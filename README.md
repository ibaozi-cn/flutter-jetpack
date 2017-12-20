# LifeStyle 概述
android MVVM 架构实践项目
![image](https://github.com/Papeone/LifeStyle/raw/master/image/mvvm.png)
## 内容包括
今年在Google I / O大会，Google推出了包括LiveData和ViewModel在内的架构组件，这有助于使用MVVM模式开发Android应用程序。经历长时间的学习和钻研。计划将其封装成一层通用的架构组件，并开源给大家使用。该架构的优势：

1.  ViewModel 与 Activity、Fragment生命周期联动，生命结束自动回收内存，并不受横竖屏切换影响，数据自动恢复

2.  LiveData 数据更新自动更新至UI

3. Room 将Dao层封装为Restful风格，易于维护，简于扩展。数据库查询就是这么简单。

## 计划：

1.  封装Activity基类，简化ViewModels绑定操作  

2. 封装LiveData与Adapter，简化RecyclerView的数据更新操作。

3. 封装Retorfit + kotlin协程，让网络高效的运转，让callBack消失  

4. 封装Room+kotlin协程，让数据库操作工作远离UI线程。

## 完成：

封装Retorfit + kotlin协程，让网络高效的运转，让callBack消失，又加入生命周期感知，自动在界面消失时 释放网络请求
