# ContextAwareThreadPoolTaskExecutor
[Home](../README.md)

## Table of Contents
- [Table of Contents](#table-of-contents)
- [Context](#context)
- [About](#about)
- [How to Use](#how-to-use)

## Context
The term of "context" refers to the data consists of:
- MDC context map
- RequestAttributes that is retrievable from RequestContextHolder

## About
MDC context map and RequestContextHolder can't be used for reactive/multi-threading/async process
as they are bound to the local thread where the request is being processed.
Any child threads used for further processing will lose the information of those two.

[ContextAwareThreadPoolTaskExecutor](../src/main/java/com/supensour/library/thread/executor/ContextAwareThreadPoolTaskExecutor.java)
is a `org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor` that is designed to pass the context
to any child threads. Upon the completion of child thread process, the context will be cleared.

Further reading about thread pool and task executor:
- [Thread Pool - Baeldung](https://www.baeldung.com/thread-pool-java-and-guava)
- [ThreadPoolTaskExecutor - Baeldung](https://www.baeldung.com/java-threadpooltaskexecutor-core-vs-max-poolsize)
- [TaskExecutor - DZone](https://dzone.com/articles/spring-and-threads-taskexecutor)

## How to Use
```java
class Foo {

  public ContextAwareThreadPoolTaskExecutor create() {
    ContextAwareThreadPoolTaskExecutor executor = new ContextAwareThreadPoolTaskExecutor();
    // set properties for pool/queue size, etc.
    executor.afterPropertiesSet(); // initialization, it's a must
    return executor;
  }

}
```
