[日志发展史](https://zhuanlan.zhihu.com/p/133582429)

今天聊聊java 中的日志组件，可能log 是我们开发中太过于常见的原因，可选组件也较多，给人一种混乱的感觉，今天就大致屡下日志组件的由来

Java 中提供的日志组件太多了，例如：log4j，logback，log4j2，其中是否有关联不是很清楚(可能log 是我们开发中太过于常见)

Java 中常用的日志框架:Log4j、Log4j2、Commons Logging、Slf4j、Logback、Jul(Java Util Logging)

### 发展史
最早的日志组件是Apache 提供的Log4j
log4j 能够通过配置文件轻松的实现日志系统的管理和多样化配置，是我们接触比较早的日志组件，几乎成了java 日志的标准，期间想让SUN 公司引入到java 标准库中，但被拒绝了，可能觉得没有面子(开玩笑)，随即SUN 搞了个属于自己的日志组件
sun 公司在java1.4版本中，增加了日志库(Java Util Logging)。其实现基本模仿了Log4j的实现

这两个组件推出后，因为之间没有什么关联，有些项目使用Log4j,有些项目使用JUL，一时开发起来造成了一定的混乱

这个时候Apache 又出手了，它推出了一个Apache Commons Logging组件，JCL只是定义了一套日志接口，支持运行时动态加载日志组件的实现，也就是说，在应用代码里，只需调用Commons Logging 的接口，底层实现可以是Log4j，也可以是Java Util Logging，由于它很出色的完成了主流日志的兼容，所以基本上在后面很长一段时间是无敌的存在。连spring 也都是依赖JCL 进行日志管理
这个时候，又一个大牛加入阵营中，原Log4J 的作者觉得Apache Commons Logging 不够优秀，想搞个更牛的方案，于是slf4j 日志体系诞生了

slf4j实际上就是一个日志门面接口，它的作用类似于Commons Loggins，并且他还为slf4j 提供了一个日志的实现：logback
这个时候可以发现：Java 的日志领域被划分为两个大营: Commons Logging和slf4j

因为slf4j 以及它的实现logback诞生以后，很快就赶超了原本apache的log4j体系，此时apache又出来说话了，这就是log4j2。apache在2012 年重写了log4j， 成立了新的项目Log4j2

### 梳理到这里，日志的整个体系分为日志框架和日志系统
- 日志框架：JCL/Slf4j
- 日志系统：Log4j、Log4j2、Logback、JUL
在现在的应用中，绝大部分都是使用slf4j作为门面， 然后搭配logback或者log4j2日志系统

写到这里相信大家对log 组件的发展史有一定的认识了。是不是有种上层的"建筑者们"一直在重复造轮子的感觉(一家之言)