<h1 style="font-size: 2.5em;"> shell�ַ�������</h1>
 


# 获取字符串长�?
`````
echo ${#str}
`````
# 字符串截�?
## 从左边截�?

`````
${str:position:length} //从字符串 str �? position 位置截取 length 个字符串
`````
## 从右边截�?

`````
${str: -length} //截取字符�? str 的后 length 个位�?
`````
# 匹配删除
PS:实际使用时删除前后缀
## 从左匹配删除 # �? \#\#
`````
${str#mact_string} //�? string 左边�?始匹配，删除匹配到的字符，尽可能少删除字�?

${str##mact_string} //�? string 左边�?始匹配，删除匹配到的字符，尽可能多删除字�?

//例子:

str=http://www.aaa.com/123.htm

echo ${str#*//} //* 做�?�配符理�?
/**

*// 表示从左边开始删除第�?�? // 号及左边的所有字�?
即删�? http://

结果�?: www.aaa.com/123.htm

**/
`````
## 从右匹配删除 % �? %%
`````
${str%mact_string} 

${str%%mact_string} 
`````

# 匹配替换

## 普�?�替�?
`````
${str/match_string/replace_string} //�? str 中第�?�? match_string 替换�? replace_string

${str//match_string/replace_string} //�? str 中的 match_string 全部替换�? replace_string
`````

## 前后�?替换
`````
${str/#match_string/replace_string} //�? str 中第�?�? match_string 替换�? replace_string

${str/%match_string/replace_string} //�? str 中最后一�? match_string 替换�? replace_string
`````

## 正则匹配
match_string可以是一个正则表达式 

# 是否包含字符�?
result=$(echo $s1 | grep "${s2}")

# Reference
[shell 字符串操�? ${} 的截取，删除，和 替换](https://cloud.tencent.com/developer/article/1530111)


[shell脚本字符串截取的8种方](https://www.cnblogs.com/zwgblog/p/6031256.html)