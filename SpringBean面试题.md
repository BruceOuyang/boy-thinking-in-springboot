## 第一章：Spring Framework 总览
### 1、沙雕面试题 - 什么是 Spring Framework?
Spring 是一个开源的开发框架，针对的是企业级的Java应用，他是一个 POJO的编程模型。
Spring Framework 核心概念是 DI 和 AOP 模型，可以在复杂的编程模型中进行解耦。
官方：
Spring Framework 全面的、可编程、可配置的，针对 Java 的编程框架。
Spring 可以让 Java 应用开发边的更轻松，提供了很多的组件支持。

### 2、996 面试题 - Spring Framework 有哪些核心模块？
spring-core: Spring 基础 API 模块，如资源管理、泛型处理
spring-beans: Spring Bean 相关，如依赖查找、依赖注入
spring-aop: Spring AOP 处理，如动态代理，AOP 字节码提升
spring-context: 事件驱动、注解驱动，模块驱动等
spring-expression: Spring 表达式语言模块

### 3、劝退面试题 - Spring Framework 的优势和不足时什么？
优势：（大而全，展开来说）
劣势：（深入学习之后再来看）

## 第二章：重新认识 IoC
### 1、沙雕面试题 - 什么是 IoC？
简单的说，IoC 是反转控制，类似于好莱坞原则，主要有依赖查找和依赖注入实现。  
按照 IoC 的定义，很多方面都是 IoC。  
比如说 JavaBeans 是 IoC 的一个容器实现，  
Servlet 容器也是 IoC 的一个实现，因为 Servlet 可以去依赖或者反向的通过 JNDI 的方式得到一些外部的资源（datasource或EJB组件）。

### 2、996 面试题 - 依赖查找和依赖注入的区别？
依赖查找：主动或手动的依赖查找方式，通常需要依赖容器或标准 API 实现。  
依赖注入：手动或自动依赖绑定的方式，无需依赖特定的容器和 API。

### 3、劝退面试题 - Spring 作为 IoC 容器有什么优势？
典型的 IoC 管理，依赖查找和依赖注入  
AOP 抽象  （完美的抽象）  
事物机制  （完美的抽象）  
SPI 机制 （Processor）  
强大的第三方整合  
易测试性  
更好的面向对象  （设计模式）

## 第三章：Spring IoC 容器概述
### 1、沙雕面试题 - 什么是 Spring IoC 容器？
第一个方面，Spring 容器是 IoC 的一种实现；
第二个方面，DI（依赖注入） 是 Spring IoC 的实现原则。

### 2、996 面试题 - BeanFactory 和 FactoryBean 区别？
BeanFactory 是 IoC 底层容器  
FactoryBean 是创建 Bean 的一种方式，帮助实现复杂的初始化逻辑

### 3、劝退面试题 - Spring IoC 容器启动时做了哪些准备？
IoC 配置元信息读取和解析、  
IoC 容器生命周期、  
Spring 事件发布、  
国际化（bundle）  
（后续有容器初始化的章节）


## 第四章：Spring Bean 基础
### 1、沙雕面试题 - 如何注册一个 Spring Bean?
通过 BeanDefinition 和外部单体对象来注册

### 2、996 面试题 - 什么是 Spring BeanDefinition?
回顾 “定义 Spring Bean” 和 “BeanDefinition 元信息”

### 3、劝退面试题 - Spring 容器是怎样管理注册 Bean
如：IoC 配置元信息读取和解析、依赖查找和注入以及 Bean 生命周期等。