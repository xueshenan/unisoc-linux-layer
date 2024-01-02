#!/bin/sh

function exe_log()
{
   eval $@;
}

function getprefix() {
    ifconfig $1 | grep "Global" | awk -F' ' '{print $3}' | awk -F':' '{print $1,$2,$3,$4}' | awk -F' ' '{print $1":"$2":"$3":"$4}'
}

function create_config_radvd() {
    local ip=$1
    local dns=$2
    local iface=$3
    if [ -f "/etc/radvd.conf" ]; then
        rm /etc/radvd.conf;
    fi
    echo -e "interface $iface\n{\n\tAdvSendAdvert on;\n\tMinRtrAdvInterval 3;\n\tMaxRtrAdvInterval 10;\n\tAdvManagedFlag off;\n\tAdvOtherConfigFlag on;" >> /etc/radvd.conf
    echo -e "\tprefix $ip/64" >> /etc/radvd.conf
    echo -e "\t{\n\t\tAdvOnLink off;\n\t\tAdvAutonomous on;\n\t\tAdvValidLifetime 3600;\n\t\tAdvPreferredLifetime 3600;\n\t\tAdvRouterAddr off;\n\t};" >> /etc/radvd.conf
    echo -e "\tRDNSS $dns" >> /etc/radvd.conf
    echo -e "\t{\n\t\tAdvRDNSSLifetime 3600;\n\t};\n};" >> /etc/radvd.conf
}

function add_route()
{
    exe_log "echo 1 > /proc/sys/net/ipv4/ip_forward"
    retVal=$(cat /proc/sys/net/ipv4/ip_forward)
    echo $retVal
    exe_log "ip addr del $2 dev $1"
    exe_log "iptables -w -t mangle -A PREROUTING -s $2 -j MARK --set-mark 0x100"
    exe_log "ip rule add fwmark 0x100 table 88 pref 100"

    exe_log "ip route add 0.0.0.0/0 dev $1 table 88"

}

function add_route6()
{
    local dns6_1=$3
    local dns6_2=$4
    local prefix=$(getprefix $1)

    #create_config_radvd "$prefix::" "$dns6_1 $dns6_2" ppp0
    #exe_log "chmod 644 /etc/radvd.conf"
    #exe_log "radvd -C /etc/radvd.conf -p /var/run/radvd.pid -d 0"
    exe_log "ip -6 addr del $2/64 dev $1"
    exe_log "ip -6 route del $2 dev $1"
    #exe_log "ip -6 rule add iif ppp0 table 89 pref 101"
    exe_log "ip6tables -w -t mangle -A PREROUTING -s $2 -j MARK --set-mark 0x101"
    exe_log "ip -6 rule add fwmark 0x101 table 89 pref 101"
    exe_log "ip -6 route add default dev $1 table 89"
    exe_log "ip -6 route add $2 dev ppp0"

    #exe_log "echo 1 > /proc/sys/net/ipv6/conf/all/forwarding"
}

function del_route()
{
    exe_log "ip route del default dev $1 table 88"
    exe_log "ip rule del prio 100"
    exe_log "iptables -w -t mangle -D PREROUTING -s $2 -j MARK --set-mark 0x100"

}

function del_route6()
{
    radvdpid=$(ps | grep radvd | grep -v grep | awk '{print $1}')

    exe_log "ip -6 route del default dev $1 table 89"
    exe_log "ip -6 rule del prio 101"
    exe_log "ip6tables -w -t mangle -D PREROUTING -s $2 -j MARK --set-mark 0x101"
    exe_log "ip -6 route del $2 dev ppp0"
    exe_log "kill $radvdpid"
    #exe_log "echo 0 > /proc/sys/net/ipv6/conf/all/forwarding"
}

if [ "$1" == "IP" ]; then
 add_route $2 $3;
elif [ "$1" == "IPV6" ]; then
 add_route6 $2 $3 $4 $5;
elif [ "$1" == "IPV4V6" ]; then
 add_route $2 $3;
 add_route6 $2 $4 $5 $6;
elif [ "$1" == "ipdown" ]; then
 del_route $2 $3;
elif [ "$1" == "ip6down" ]; then
 del_route6 $2 $3;
fi