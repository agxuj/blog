# 缓存置换算法

<script src="../js/index.js"></script>
<div id="content"></div>




Reference:

[RAND算法,FIFO算法,LFU算法,LRU算法,OPT算法](http://blog.163.com/shi_shun/blog/static/237078492010420320196/)

[十种常用的缓存替换算法](http://www.open-open.com/lib/view/open1401935263431.html)

[缓存算法（页面置换算法）-FIFO、LFU、LRU](http://www.cnblogs.com/dolphin0520/p/3749259.html)


**评价一个页面替换算法好坏的标准主要有两个，一是命中率要高，二是算法要容易实现。**

## RAND（Random algorithm，随机算法）

利用软件或硬件的随机数发生器来确定主存储器中被替换的页面。这种算法最简单，而且容易实现。但是，这种算法完全没有利用主存储器中页面调度情况的历史信息，也没有反映程序的局部性，所以命中率比较低。

 

## FIFO（First-In First-Out algorithm，先进先出算法）

核心原则就是：如果一个数据最先进入缓存中，则应该最早淘汰掉。也就是说，当缓存满的时候，应当把最先进入缓存的数据给淘汰掉。在FIFO Cache中应该支持以下操作。它的优点是比较容易实现，能够利用主存储器中页面调度情况的历史信息，但是，没有反映程序的局部性。因为最先调入主存的页面，很可能也是经常要使用的页面。
实现：
LinkHashMap

 

## LRU（Least-Recently-Used，最近最少使用）

替换掉最近被请求最少的文档。这一传统策略在实际中应用最广。在CPU缓存淘汰和虚拟内存系统中效果很好。然而直接应用与代理缓存效果欠佳，因为Web访问的时间局部性常常变化很大。
实现：
LruCache选择的是LinkedHashMap这个数据结构，LinkedHashMap是一个双向循环链表，在构造LinkedHashMap时，通过一个boolean（accessOrder）值来指定LinkedHashMap中保存数据的方式。
在LruCache中选择的是accessOrder = true；此时，当accessOrder 设置为 true时，每当我们更新（即调用put方法）或访问（即调用get方法）map中的结点时，LinkedHashMap内部都会将这个结点移动到链表的尾部，因此，在链表的尾部是最近刚刚使用的结点，在链表的头部是是最近最少使用的结点，当我们的缓存空间不足时，就应该持续把链表头部结点移除掉，直到有剩余空间放置新结点。
在LruCache中选择的是accessOrder = false；则可用在FIFO；

## LFU（Least-Frequently-Used，最不经常使用）

替换掉访问次数最少的。这一策略意图保留最常用的、最流行的对象，替换掉很少使用的那些。然而，有的文档可能有很高的使用频率，但之后再也不会用到。传统 的LFU策略没有提供任何移除这类文件的机制，因此会导致“缓存污染(Cache Pollution)”，即一个先前流行的缓存对象会在缓存中驻留很长时间，这样，就阻碍了新进来可能会流行的对象对它的替代。
实现：
用HashMap保存关系{key值 : 命中次数与上次命中时间}，当需要淘汰某个key值时，调用map.remove(key)

## SIZE

替换size最大的对象。这一策略通过淘汰一个大对象而不是多个小对象来提高命中率。不过，可能有些进入缓存的小对象永远不会再被访问。SIZE策略没有提供淘汰这类对象的机制，也会导致“缓存污染”。

## LRU-Threshold

不缓存超过某一size的对象，其它与LRU相同。

## Log(Size) + LRU

替换size最大的对象，当size相同时，按LRU进行替换

## Hyper-G

LFU的改进版，同时考虑上次访问时间和对象size

## Pitkow/Recker

替换最近最少使用的对象，除非所有对象都是今天访问过的。如果是这样，则替换掉最大的对象。这一策略试图符合每日访问web网页的特定模式。这一策略也被建议在每天结束是运行，以释放被“旧的”，最近最少使用的对象占用的空间。

## Lowest-Latency-First

替换下载时间最少的文档。显然它的目标是最小化平均延迟。

## Hybrid

Hybrid有另外一个目标，减少平均延迟。对缓存中的每个文档都会计算一个保留效用（utility of retaining）。保留效用最低的对象会被替换掉。位于服务器s的文档f的效用函数定义如下：
Cs: 与服务器s的连接时间
bs: 服务器s的带宽
frf: f的使用频率
sizef: f的size，单位字节

K1和K2是常量，Cs和bs是根据最近从服务器s获取文档的时间进行估计的。

## LRV（Lowest Relative Value）

LRV也是基于计算缓存中文档的保留效用。然后替换保留效用最低的文档。有点复杂，实际应用价值不大，就不详述了。

## OPT（OPTimal replacement algorithm，最优替换算法）

上面介绍的几种页面替换算法主要是以主存储器中页面调度情况的历史信息为依据的，它假设将来主存储器中的页面调度情况与过去一段时间内主存储器中的页面调度情况是相同的。显然，这种假设不总是正确的。最好的算法应该是选择将来最久不被访问的页面作为被替换的页面，这种替换算法的命中率一定是最高的，它就是最优替换算法。