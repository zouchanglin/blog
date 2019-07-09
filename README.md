# 非结构化数据检索
* 顺序扫描法（Serial Scanning） 适合小数据量的搜索
* 全文搜索（Full-text Search）将非结构化的一部分数据组成结构化的数据，建立索引


全文搜索的实现原理：
建立文本库--》建立索引--》执行搜索--》过滤结果

# 全文搜索实现技术
* 基于Java的开源实现 Lucene（引擎）、ElasticSearch（基于Lucene）、Solr
ElasticSearch自身有分布式的管理系统、而Solr是基于Zookeeper

# ElasticSearch特点
* 高度可扩展的开源全文搜索和分析引擎
* 快速地、近实时地对大数据进行存储、搜索和分析
* 用来支撑有复杂的数据搜索噐求的企业级应用
![](https://s2.ax1x.com/2019/06/30/ZlxL6A.png)

# ElasticSearch核心概念
* 近实时:为了效率不可能每分每秒的刷新索引
* 集群:每个集群有唯一标识
* 节点:保存数据、参与整个集群的搜索
* 索引:加快搜索速度
* 类型:根据非结构化数据的特征来分类
* 文档:使用Json格式来表示，文档是进行索引的基本单位，文档的实例对应关系型数据中的实体
* 分片:把数据分开存储
* 副本:为了高可用，提高吞吐量

# ElasticSearch与SpringBoot集成
* ElasticSearch
* Spring Data ElasticSearch
* JNA

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.data</groupId>
        <artifactId>spring-data-elasticsearch</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
    </dependency>
</dependencies>
```
需要安装elasticsearch的版本是6.7.0

# 权限管理


![](https://s2.ax1x.com/2019/06/30/Z1zGZt.png)
![](https://s2.ax1x.com/2019/06/30/Z3nGef.png)
![](https://s2.ax1x.com/2019/06/30/Z3naWj.png)
![](https://s2.ax1x.com/2019/06/30/Z3nDO0.png)
![](https://s2.ax1x.com/2019/06/30/Z3n6TU.png)
![](https://s2.ax1x.com/2019/06/30/Z3n2Y4.png)
![](https://s2.ax1x.com/2019/06/30/Z3nfp9.png)
![](https://s2.ax1x.com/2019/06/30/Z3nom6.png)

