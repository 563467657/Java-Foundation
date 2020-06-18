# Linux查看网卡命令

```shell
ip a
ifconfig
ip link show
ls /sys/class/net
```

```shell
本地虚拟机vagrant环境下对应网卡
lo	本地网卡
eth0	和外界互联网通信网卡
eth1	局域网网卡
```

```shell
cd /etc/sysconfig/network-scripts	本地网卡配置文件地址
ifup 网卡名称	开启网卡
ifdown 网卡名称		关闭网卡
ip netns add [namespace]	创建network namespace
ip netns list	查看已经创建的network namespace
```

