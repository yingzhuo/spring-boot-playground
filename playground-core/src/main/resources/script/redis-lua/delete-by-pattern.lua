--[[
被脚本用来删除符合模式的Key

作者: 应卓
参数: ARGV[1]: 要匹配的模式
返回: 删除的Key的个数 (string)
--]]

local cursor = "0";
local count = 0;
repeat
    local scanResult = redis.call("SCAN", cursor, "MATCH", ARGV[1], "COUNT", 15);
    local keys = scanResult[2];
    for i = 1, #keys do
        local key = keys[i];
        redis.replicate_commands(); -- 兼容Redis7.0以下版本
        redis.call("DEL", key);
        count = count + 1;
    end ;
    cursor = scanResult[1];
until cursor == "0";
return count .. "";
