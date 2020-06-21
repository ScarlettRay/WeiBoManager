# WeiBoManager
此项目旨在帮助微博运营相关人士更加便捷的完成一些重复性的工作。你可以通过这个库内置的API或者Flow来帮助自己完成一些工作，或者你有自己的想法，完全可以定义自己的API和Flow,这是不会有限制的。
再使用这个库之前，有几个开发要了解的概念需要介绍一下。这些知识可以帮助你更加高效快速的完成开发工作。
1. 基础执行单位 API，接口位置如下
```
xyz.iamray.weiboapi.api.APIxyz.iamray.weiboapi.api.API
```
此接口是用来定义一个微博操作，比如关注，取关，点赞，转发等。
接口中的两个泛型参数分别为输入输出对象，在下面介绍的exe方法中要用到。
接口中的两个方法:
xyz.iamray.weiboapi.api.API#getNumber(),返回一个唯一标记这个API的字符串。
xyz.iamray.weiboapi.api.API#exe(I param, Context context),第一个参数为输入对象，第二个参数为运行的上下文环境，可以拿到当前操作的微博用户，会话信息和外面设置的属性等。exe执行完之后需要返回一个输出对象，用xyz.iamray.weiboapi.common.R分装，在Flow中，输出对象是下一个API的输入对象。
2.API的衍生出来的细分接口，
```
xyz.iamray.weiboapi.api.bridge.ApiBridge
和
xyz.iamray.weiboapi.api.filter.Filter
```
ApiBridge主要用来做输入输出的对象转换，比如上一个API的输出对象WeiBoer并不匹配下一个API的输入对象Blog,就需要在两个API之间加一个只有ApiBridge，进行搭桥转换。  
Filter主要在下一个API输入之前，对上一个API输出进行校验，过滤掉一些不需要的输出。

