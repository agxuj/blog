
# 获取字符串长度
`````
echo ${#str}
`````
# 字符串截取
## 从左边截取

`````
${str:position:length} //从字符串 str 的 position 位置截取 length 个字符串
`````
## 从右边截取

`````
${str: -length} //截取字符串 str 的后 length 个位置
`````
# 匹配删除
PS:实际使用时删除前后缀
## 从左匹配删除 # 和 \#\#
`````
${str#mact_string} //从 string 左边开始匹配，删除匹配到的字符，尽可能少删除字符

${str##mact_string} //从 string 左边开始匹配，删除匹配到的字符，尽可能多删除字符

//例子:

str=http://www.aaa.com/123.htm

echo ${str#*//} //* 做通配符理解
/**

*// 表示从左边开始删除第一个 // 号及左边的所有字符
即删除 http://

结果是: www.aaa.com/123.htm

**/
`````
## 从右匹配删除 % 和 %%
`````
${str%mact_string} 

${str%%mact_string} 
`````

# 匹配替换

## 普通替换
`````
${str/match_string/replace_string} //将 str 中第一个 match_string 替换成 replace_string

${str//match_string/replace_string} //将 str 中的 match_string 全部替换成 replace_string
`````

## 前后缀替换
`````
${str/#match_string/replace_string} //将 str 中第一个 match_string 替换成 replace_string

${str/%match_string/replace_string} //将 str 中最后一个 match_string 替换成 replace_string
`````

## 正则匹配
match_string可以是一个正则表达式 

# 是否包含字符串
result=$(echo $s1 | grep "${s2}")

# Reference
[shell 字符串操作 ${} 的截取，删除，和 替换](https://cloud.tencent.com/developer/article/1530111)


[shell脚本字符串截取的8种方](https://www.cnblogs.com/zwgblog/p/6031256.html)