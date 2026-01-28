# Redis基础

## 快速入门

### 认识NoSQL

- |          | SQL                                 | NoSQL                    |

| -------- | ----------------------------------- | ------------------------ |
| 数据结构 | 结构化(Structured)                  | 非结构化                 |
| 数据关联 | 关联的(Relational)                  | 无关联的                 |
| 查询方式 | SQL查询                             | 非SQL                    |
| 事务特性 | ACID                                | BASE                     |
| 存储方式 | 磁盘                                | 内存                     |
| 扩展性   | 垂直                                | 水平                     |
| 使用场景 | 数据结构固定                        | 数据结构不固定           |
|          | 相关业务对数据安全性、—致性要求较高 | 对一致性、安全性要求不高 |
|          |                                     | 对性能要求高             |
|          |                                     |                          |

- NoSQL分类

  - 键值类型（Redis）
  - 文档类型(MongoDB)
  - 列类型（HBase)
  - Graph类型(Neo4j)

### 认识Redis

- Redis诞生于2009年全称是Remote Dictionary Server，远程词典服务器，是一个基于内存的键值型NoSQL数据库。

- 特征：
  1. 键值（key-value）型，value支持多种不同数据结构，功能丰富
  2. 单线程，每个命令具备原子性
  3. 低延迟，速度快（基于内存、IO多路复用、良好的编码）。
  4. 支持数据持久化
  5. 支持主从集群、分片集群
  6. 支持多语言客户端

### 安装Redis

### 常见数据结构

- 基本类型：String、Hash、List、Set、SortedSet
- 特殊类型：GEO、BitMap、HyperLog

### Redis常见命令

通用命令

不同数据结构的操作命令

Redis的Java客户端

Jedis客户端

SpringDataRedis客户端