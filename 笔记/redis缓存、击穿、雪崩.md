## 缓存击穿

缓存击穿指的是在高并发情况下，一个缓存的key在缓存中不存在，导致每次请求都要访问数据库，从而导致数据库压力过大，甚至崩溃。这种情况通常发生在一些热点数据上，比如用户登录信息等。

### 原因

缓存击穿的原因是因为在某些热点数据的key失效或者被删除时，大量的并发请求同时访问这个key，导致缓存中不存在这个key的数据，从而每个请求都需要去访问数据库获取数据，造成数据库压力过大。

### 解决方案

* 1.设置热点数据永不过期

在缓存中设置热点数据永不过期可以有效地避免缓存击穿问题。但是这种方式会导致缓存中存在很多过期但是占用内存的数据，因此需要在设置缓存数据时进行权衡。

```java
String key = "hot_data";
String value = redis.get(key);
if (value == null) {
    value = db.get(key);
    if (value != null) {
        redis.set(key, value);
        redis.persist(key); //设置key永不过期
    }
}
复制代码
```

* 2.设置热点数据短期过期

为了避免缓存中过多占用内存的数据，可以将热点数据设置一个相对较短的过期时间，比如1分钟，这样可以避免过期数据占用过多内存。当热点数据过期后，可以在后台异步更新缓存数据。

```java
String key = "hot_data";
String value = redis.get(key);
if (value == null) {
    //添加分布式锁，避免缓存穿透
    if(redis.setNx("lock_"+key,"value")){
        value = db.get(key);
        if (value != null) {
            redis.set(key, value);
            redis.expire(key,60); //设置key过期时间为1分钟
        }
        redis.del("lock_"+key);
    }else {
        Thread.sleep(50);
        return queryDataFromCache(key);
    }
}
复制代码
```

## 缓存穿透

缓存穿透指的是当大量的并发请求同时查询一个不存在的key时，由于缓存中没有对应的数据，所以每个请求都会去访问数据库，导致数据库压力过大。

### 原因

缓存穿透的原因是由于黑客攻击或者恶意请求，可能会对某些不存在的数据进行大量的请求，从而导致缓存穿透问题。

### 解决方案

* 1.对查询结果为空的key设置空值

当缓存查询的结果为空时，可以将结果设置为空值写入缓存，这样下次查询相同的key时，可以直接从缓存中获取结果，避免了查询数据库的开销。

```java
String key = "not_exist_data";
String value = redis.get(key);
if (value == null) {
    //添加分布式锁，避免缓存穿透
    if(redis.setNx("lock_"+key,"value")){
        value = db.get(key);
        if (value != null) {
            redis.set(key, value);
        }else {
            redis.set(key, ""); //设置空值
            redis.expire(key, 60); //设置过期时间为1分钟
        }
        redis.del("lock_"+key);
    }else {
        Thread.sleep(50);
        return queryDataFromCache(key);
    }
}
复制代码
```

* 2.BloomFilter过滤非法请求

使用BloomFilter可以对请求参数进行过滤，将非法请求拦截在系统外部，从而避免了对系统的压力。

```java
BloomFilter filter = new BloomFilter(10000, 0.001); //设置布隆过滤器
String key = "not_exist_data";
if(filter.mightContain(key)){
    return null;
}
String value = redis.get(key);
if (value == null) {
    //添加分布式锁，避免缓存穿透
    if(redis.setNx("lock_"+key,"value")){
        value = db.get(key);
        if (value != null) {
            redis.set(key, value);
        }else {
            filter.put(key); //将非法key加入过滤器
        }
        redis.del("lock_"+key);
    }else {
        Thread.sleep(50);
        return queryDataFromCache(key);
    }
}
复制代码
```

## 缓存雪崩

缓存雪崩指的是在缓存中存在大量的key过期时间相同或者失效的情况下，当这些key同时失效时，大量的并发请求都会涌入数据库，导致数据库压力过大，甚至崩溃。

### 原因

缓存雪崩的原因是因为在缓存中存在大量的key同时过期，导致大量的并发请求同时涌入数据库。

### 解决方案

* 1.缓存数据随机过期时间

为了避免缓存中大量key同时过期，可以设置每个缓存数据的过期时间不同，比如可以在原有过期时间的基础上添加一个随机时间，这样可以避免大量key同时过期的情况。

```java
String key = "hot_data";
String value = redis.get(key);
if (value == null) {
    //添加分布式锁，避免缓存穿透
    if(redis.setNx("lock_"+key,"value")){
        value = db.get(key);
        if (value != null) {
            //设置随机过期时间，避免缓存雪崩
            Random random = new Random();
            int expireTime = random.nextInt(1800) + 1800; //过期时间在30~60分钟之间
            redis.set(key, value);
            redis.expire(key, expireTime);
        }
        redis.del("lock_"+key);
    }else {
        Thread.sleep(50);
        return queryDataFromCache(key);
    }
}
复制代码
```

* 2.缓存数据预加载

为了避免在缓存中大量的key失效，可以在缓存数据过期之前，提前将缓存数据刷新到缓存中，保证数据的可用性。

```java
String key = "hot_data";
String value = redis.get(key);
if (value == null) {
    //添加分布式锁，避免缓存穿透
    if(redis.setNx("lock_"+key,"value")){
        value = db.get(key);
        if (value != null) {
            redis.set(key, value);
            redis.expire(key, 1800); //设置过期时间为30分钟
        }
        redis.del("lock_"+key);
    }else {
        Thread.sleep(50);
        return queryDataFromCache(key);
    }
}else {
    //判断缓存是否需要刷新
    if(redis.ttl(key) < 300){
        new Thread(() -> {
            String newValue = db.get(key);
            if (newValue != null) {
                redis.set(key, newValue);
                redis.expire(key, 1800); //设置过期时间为30分钟
            }
        }).start();
    }
}
复制代码
```

* 3.限流降级

当缓存雪崩问题出现时，可以通过限流降级的方式来减少对数据库的请求，从而保证系统的可用性。可以通过配置Hystrix等限流降级框架来实现。

```java
String key = "hot_data";
String value = redis.get(key);
if (value == null) {
    //使用Hystrix进行限流降级
    value = HystrixCommand.execute(() -> {
        String data = db.get(key);
        redis.set(key, data);
        redis.expire(key, 1800); //设置过期时间为30分钟
        return data;
    }, () -> {
        return "系统繁忙，请稍后重试！";
    });
}
复制代码
```

## 总结

Redis的使用，可以有效地提高系统的性能和可用性。但是在使用过程中，需要注意缓存击穿、缓存穿透和缓存雪崩等问题，采用适当的解决方案来避免这些问题的发生，从而保证系统的稳定性和可靠性。
