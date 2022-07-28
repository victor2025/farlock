-- 用于解锁的lua脚本
if (redis.call('hexists', KEYS[1], ARGV[1]) == 1) then
    if (redis.call('hget', KEYS[1], ARGV[1]) == '1') then
        return redis.call('hdel', KEYS[1], ARGV[1]);
    else
        return redis.call('hincrby', KEYS[1], ARGV[1], -1);
    end ;
end ;
return 1;