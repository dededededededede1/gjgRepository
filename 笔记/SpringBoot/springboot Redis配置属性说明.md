- springboot application.properties中关于redis的配置有哪些，作用是什么

- Spring Boot允许通过在`application.properties`文件中添加配置属性来自定义Redis的配置。以下是一些关于Redis的常见配置属性以及它们的作用： 
1. `spring.redis.host`: Redis服务器的主机名或IP地址。默认值为`localhost`。

作用：指定Redis服务器的地址。 
1. `spring.redis.port`: Redis服务器的端口。默认值为`6379`。

作用：指定Redis服务器的端口号。 
1. `spring.redis.password`: 用于连接Redis服务器的密码。默认值为空。

作用：提供Redis服务器的访问密码（如果设置了密码保护的话）。 
1. `spring.redis.database`: 要连接到的Redis数据库的索引。默认值为`0`。

作用：选择要连接的Redis数据库。 
1. `spring.redis.timeout`: 以毫秒为单位的连接超时时间。默认值为`60000`（1分钟）。

作用：设置连接Redis服务器的超时时间。 
1. `spring.redis.ssl`: 是否使用SSL连接。默认值为`false`。

作用：设置是否对Redis连接启用SSL加密。 
1. `spring.redis.ssl.endpoint.identification.algorithm`: SSL终端识别算法。默认值为空。

作用：配置SSL连接时使用的终端识别算法。 
1. `spring.redis.ssl.truststore`: SSL信任库的路径。默认值为空。

作用：设置SSL连接时使用的信任库路径。 
1. `spring.redis.ssl.truststore-password`: SSL信任库的密码。默认值为空。

作用：设置SSL连接时使用的信任库密码。 
1. `spring.redis.ssl.keystore`: SSL密钥库的路径。默认值为空。

作用：设置SSL连接时使用的密钥库路径。 
1. `spring.redis.ssl.keystore-password`: SSL密钥库的密码。默认值为空。

作用：设置SSL连接时使用的密钥库密码。 
1. `spring.redis.pool.max-active`: 连接池允许的最大连接数。默认值为`8`。

作用：设置Redis连接池的最大连接数。 
1. `spring.redis.pool.max-wait`: 当连接池中没有可用连接时，请求等待的最大毫秒数。默认值为`-1`（无限等待）。

作用：设置从连接池获取连接的最大等待时间。 
1. `spring.redis.pool.max-idle`: 连接池中允许的最大空闲连接数。默认值为`8`。

作用：设置连接池的最大空闲连接数。 
1. `spring.redis.pool.min-idle`: 连接池中允许的最小空闲连接数。默认值为`0`。

作用：设置连接池的最小空闲连接数。

这些配置属性使您能够自定义Redis连接、连接池、SSL设置等方面的行为。请根据您的应用程序需求和Redis服务器设置调整这些属性。

- 除了上述基本的配置属性之外，您还可以使用以下配置属性来进一步自定义Spring Boot应用程序中的Redis配置。 
1. `spring.redis.sentinel.master`: Redis哨兵配置的master名称。默认值为空。

作用：在使用Redis哨兵模式时，设置哨兵master的名称。 
1. `spring.redis.sentinel.nodes`: Redis哨兵的节点列表，使用逗号分隔。默认值为空。

作用：在使用Redis哨兵模式时，配置哨兵节点的地址和端口，如`host1:port1,host2:port2`。 
1. `spring.redis.cluster.nodes`: Redis集群的节点列表，使用逗号分隔。默认值为空。

作用：在使用Redis集群模式时，配置集群节点的地址和端口，如`host1:port1,host2:port2`。 
1. `spring.redis.cluster.max-redirects`: Redis集群模式下执行命令的最大重定向次数。默认值为`5`。

作用：设置在Redis集群模式下执行命令时允许的最大重定向次数。 
1. `spring.redis.lettuce.pool`: Lettuce客户端的连接池配置。默认值为空。

作用：在使用Lettuce客户端时，配置连接池相关属性。 
1. `spring.redis.lettuce.shutdown-timeout`: Lettuce客户端关闭时的超时时间（以毫秒为单位）。默认值为`100`。

作用：设置Lettuce客户端关闭时的超时时间。 
1. `spring.redis.jedis.pool`: Jedis客户端的连接池配置。默认值为空。

作用：在使用Jedis客户端时，配置连接池相关属性。 
1. `spring.redis.jedis.client-name`: 用于连接Redis服务器的Jedis客户端名称。默认值为空。

作用：设置连接Redis服务器时使用的Jedis客户端名称。 
1. `spring.redis.jedis.pub-sub-num-threads`: Jedis客户端中发布订阅线程的数量。默认值为`1`。

作用：设置Jedis客户端中发布订阅功能的线程数量。 
1. `spring.redis.jedis.shutdown-timeout`: Jedis客户端关闭时的超时时间（以毫秒为单位）。默认值为`2000`。

作用：设置Jedis客户端关闭时的超时时间。

根据您的应用程序需求和Redis服务器设置，您可以结合这些属性来配置Redis连接、连接池、哨兵、集群和客户端设置等方面的行为。请确保根据实际情况和需求来调整这些属性，以便为您的应用程序提供最佳的性能和可用性。
