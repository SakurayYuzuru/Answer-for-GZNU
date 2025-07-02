# Web
## HTML语言基础
1. HTML文档结构
   ```html
    <!DOCTYPE ...>
        <!-- 页面开始 -->
        <html>
            <!-- 网页头部 -->
            <head>
                <title>

                </title>
            </head>

            <!-- 网页主体 -->
            <body>

            </body>
        <!-- 页面结束 -->
        </html>
   ```
   `css`样式：外部样式/内部样式，不要使用内嵌样式
2. `HTML`不区分字母大小写，而`XHTML`区分大小写
3. `<hn>`控制字体大小，$n \in [1, 6]$，双标签
4. `<p>`段落，双标签
5. `<hr/>`水平线
6. `<ol>`有序列表
7. `<li>`某一列列表项
8. `<ul>`无序列表，  
   `<ul type="type-value">`, `type`属性用来设置列表的图形前缀，$type-value \in \{circle, disc, square, none, etc.\}$，`disc`为默认值
9. `<div>`表达一个逻辑区块
   HTML中提供了`<span>`标签来实现行内块的定义，`<span>`标签属于行内元素，用来选择特定文本，以便赋予特殊的样式
10. `<img/>`图像
11. `<a>`给超链接加标题
    `target`属性改变目标文档的显示窗口  
    `<a href=url target="target-value">`, $target-value \in \{\_blank, \_self, \_parent, \_top, frameName\}$
    | 值 | 描述 |
    | --- | --- |
    | _blank | 在新窗口打开被链接文档 |
    | _self | 默认值，在相同的框架中打开被链接文档 |
    | _parent | 在父框架中打开被链接文档 |
    | _top | 在整个窗口中打开被链接文档 |
    | frameName | 在指定的框架中打开被链接文档 |

### 样例
1. 
```html
<!DOCTYPE>
    <html>
        <head>
            <meta http-equiv="Content - Type" content="text/html; charset=utf-8" />
            <title>一个简单页面</title>
        </head>

        <body>
            <img src="address"/>
            <table>
                <tr>
                    <td>Hello World!</td>
                </tr>
            </table>
        </body>
    </html>
```
2.  
```html

```
 
### 课后题
1. 在`HTML`中的列表，无序列表使用标签`ul`，有序列表使用标签`ol`，定义列表使用标签`dl`
2. 图片的边框可以通过`border`设置宽度
3. 关于超链接，`target`属性用于规定在何处打开链接文件
4. `_blank`是中新窗口打开网页文档
5. `font`标签用来设定字体、字号和颜色等属性，是HTML中最基础的标签之一
6. `meta`标签的属性有`name`和`http-equiv`，其中`keywords`用来描述网页，以便于搜索引擎的查找和分类
7. 建立锚点后，便可以创建锚点链接，需用到`#`号以及锚点名称作为`href`属性值

## 
1. p22 1-15
2. p34 `<table>` `colspan` `rowspan`
3. p40 2-5

## 
1. p47
2. p66 `<method>`
3. p69-p78
4. p79 button

## 
1. p92 
2. p98
3. p117
4. p137
5. p150


> 最后一题用内部样式/外部样式，最好别用内嵌