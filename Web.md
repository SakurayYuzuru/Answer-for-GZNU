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
 
### 课后题
1. 在`HTML`中的列表，无序列表使用标签`ul`，有序列表使用标签`ol`，定义列表使用标签`dl`
2. 图片的边框可以通过`border`设置宽度
3. 关于超链接，`target`属性用于规定在何处打开链接文件
4. `_blank`是中新窗口打开网页文档
5. `font`标签用来设定字体、字号和颜色等属性，是HTML中最基础的标签之一
6. `meta`标签的属性有`name`和`http-equiv`，其中`keywords`用来描述网页，以便于搜索引擎的查找和分类
7. 建立锚点后，便可以创建锚点链接，需用到`#`号以及锚点名称作为`href`属性值

## 表格与框架
1. 文本链接
   ```html
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
            <title>文本超链接<title>
        </head>

        <body>
            <a href="<html-path>" title="<file-name>">网站首页</a>
            <br/>
        </body>
    </html>
   ```
2. 表格
   1. `<table>` 
      ```html
        <table>
            <tr>
                <td></td>
            </tr>
        </table>
      ```
   2. `colspan`: 单元格跨越列数
   3. `rowspan`: 单元格跨越行数、
      ```html
      <table>
        <tr>
            <td colspan="2"></td>
            <td rowspan="2"></td>
        </tr>
      </table>
      ```
3. 分组`thead`, `tfoot`, `tbody`, `caption`
   ```html
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
            <title>表格的行分组<title>
        </head>

        <body>
            <table width="400" border="1" rules="groups">
                <caption>
                    薪资绩效表
                </caption>
                <thead>
                    <tr>
                        <th>员工编号</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>YJ5605</td>
                    </tr>
                </tbody>
                <tfoot>
                    <tr>
                        <td></td>
                    </tr>
                </tfoot>
            </table>
        </body>
    </html>
   ```

### 课后题
1. `HTML`使用`colspan`标签将表格按列进行分组
2. 下列表格按行分组使用表头标签`<thead>`, 表格主体标签`<tbody>`以及表尾标签`<tfoot>`
3. 表格的标题使用专门的标签`<caption>`
4. 框架集`<frameset>`标签使用属性`cols`对行进行划分
5. 在创建超链接时，通过`target`属性指明新的页面应该在那个框架中进行加载
6. 内联框架是嵌入到页面中的一个区域，通过`<iframe>`标签引入另外一个页面资源
7. `<thead>`用来定义表格头部信息，多用与表格的第一行或第一列
8. 表格的`<colgroup>`标签使用`<colspan>`属性说明当前分组占据几列

## 表单
1. `method`:`get`方式和`post`方式
   1. `get`: `<url> ? userName=<name> & userPwd=<pwd>`
   2. `post`: `POST <url> Host:<host-path> userName=<name> userPwd=<pwd>`
2. 表单域:
   1. 单行文本框
        ```html
        <input type="text" name="..." size="..." maxlength="..." value="..." disabled="disabled" readonly="readonly"/>
        ```
   2. 密码框
        ```html
        <input type="password" name="..." size="..." maxlength="..." value="..."/>
        ```
   3. 单选按钮
        ```html
        <input type="radio" name="..." value="..." checked="checked"/>
        ```
   4. 复选框
        ```html
        <input type="checkbox" name="..." value="..." checked="checked"/>
        ```
   5. 文件选择框
        ```html
        <input type="file" name="..." accept="..."/>
        ```
   6. 隐藏域
        ```html
        <input type="hidden" name="..." value="..."/>
        ```
   7. 多行文本框
        ```html
        <textarea name="..." rows="..." cols="..." wrap="..."></textarea>
        ```
   8. 列表选择框
        ```html
        <select name="..." size="..." multiple="multiple">
            <option value="..." selected="selected">

            </option>
        </select>
        ```
3. `<button>`: `<input type="submit | reset | button | image" name="<name>" src="..." value="..."/>`

### 课后题
1. `method`属性的取值可以是`get`或`post`，其中`get`为默认值
2. `<input>`标签的`type`常见的取值有`text`, `password`, `radio`, `checkbox`, `file`, `hidden`, `button`, `submit`, `reset`, `image`
3. 列表选择框是通过`select`和`post`构成(P76)
4. `optgroup`标签可以对列表选项框中的选项进行分组
5. 按钮主要分为`提交按钮`、重置按钮、`图片按钮`、普通按钮
6. 在`<textarea>`标签中，`rows`属性用来设置文本输入框的宽度，`cols`属性用来设置文本输入框的高度
7. 在列表选择框中，`multiple`属性规定该列表允许多选
8. 单选按钮分组是根据`name`属性进行划分的

## CSS语言基础
1. `css`的优势: 控制HTML标签的显示样式
2. 基本选择器
   1. 通用选择器`*{ }`
   2. 标签选择器`p{ font-family: 楷体}`
   3. 类选择器`.classname{ }`
   4. ID选择器`# idValue { }`
3. 通过`display`属性可以将页面元素隐藏或显示出来，也可以将元素强制改成块级元素或内敛元素
   1. `none`: 隐藏
   2. `block`: 显示为块级元素
   3. `inline`: 显示为内联元素

### 课后题
1. “CSS”是`Colorful Style Sheets`的英文缩写
2. 在以下的`HTML`中，***C***是正确引用外部样式表的方法
   - A. `<style src="mystyle.css">`
   - B. `<stylesheet>mystyle.css</stylesheet>`
   - C. `<link rel="stylesheet" type="text/css" href="mystyle.css"/>`
   - D. `<link rel="mystyle.css" type="text/css" href="stylesheet"/>`
   > `<link/>`放在`head`中
3. `HTML`标签中`<style>`用于定义内部样式表
   > `<style/>`放在`head`中
4. `HTML`中的`style`属性可用来定义内联样式
5. > `style`放在`body`中
6. 在`CSS`文件中插入注释正确的是`/* */`
7. 下面可以将超链接的下划线去掉***A***
   - A. `{text-decoration: none;}`
   - B. `{underline: none;}`
   - C. `{decoration: underline;}`
   - D. `{decoration: none;}`
8. `background-attachment`属性用来设置背景图像是否随页面内容一起滚动
   > `background-origin`为, `background-clip`为绘制区域
9. 属性`position`为`fixed`时，元素的位置固定，当拖动滚动条时，不随滚动条滚动，保持原来的位置(P111)   

## CSS页面布局
1. 边框样式
   1. `none`无
   2. `hidden`隐藏
   3. `dotted`点状
   4. `dashed`虚线
   5. `solid`实线
   6. `double`双线
   ```css
    h3{
        border-style: solid dashed;
    }
    div{
        border-top-style: solid;
        border-bottom-style: solid;
        border-left-style: dashed;
        border-right-style: dashed;
    }
    p{
        border-style: outset;
        border-color: gray;
        border-width: 8px;
    }
    span{
        border-style: inset;
        border-color: gray;
        display: block;
        border-width: 8px;
    }
   ```
2. `padding`内边距(top, bottom, left, right)

### 课后题
1. 盒子模型从外到内的顺序是`margin->borden->paddin->content`
2. 设置边框高度时，参数个数可能是`1~4`个
3. `dashed`是虚线类型
   > `solid`实线, `double`双线，`dotted`点状线
4. `TRBL`规则指的是`上->右->下->左`
   > `Top-Right-Bottom-Left`
5. 现有圆角边框样式`border-radius: 10px 20px/30px 20px 40px`, 下面相关代码不正确的是***D***
   - A. `border-top-left-radius: 10px 30px`
   - B. `border-top-right-radius: 20px 20px`
   - C. `border-bottom-right-radius: 10px 40px`
   - D. `border-bottom-left-radius: 20px 40px`
6. 通过下面`CSS`样式代码来设置边框阴影效果：
    ```css
    {
        box-shadow: 30px 20px 10px 5px #BBAACC;
    }
    ```
    其中`10px`用来设置边框阴影中的``特征  
    > `box-shadow: <> <> <> <> <color>`
7. 关于图像边框说法错误的是： ***B***
   - A.
8. 下面设置中，可以使当前元素在父容器中水平居中的是`margin: 0 auto`
9. 关于页面布局说法不正确的是***C***

> 最后一题用内部样式/外部样式，最好别用内嵌

## 作业
### 简历
```html
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width = device-width, initial-scale = 1.0" />
        <title>Sakurya's CV</title>
        <link rel="stylesheet" href="config.css" />
    </head>

    <body>
        <header>
            <h1>张博宇</h1>
            <p>
                <a>Phone: (+86) 156 6207 8732</a> | 
                <a href="mailto:sakulapii@gmail.com">sakulapii@gmail.com</a> | 
                <a href="https://github.com/SakurayYuzuru">SakurayYuzuru</a> | 
            </p>
        </header>

        <section>
            <h2>教育背景</h2>
            <ht />

            <table>
                <tr>
                    <th>2023 - 至今</th>
                    <th>贵州师范大学</th>
                    <th>计算机科学与技术</th>
                </tr>
            </table>
        </section>

        <section>
            <h2>专业技能</h2>
            <ht />

            <ul>
                <li>熟悉C++语法和STL， 熟悉C++11, 17特性，具备良好的编程习惯</li>
                <li>熟悉C# 语法，熟练使用Unity引擎</li>
                <li>熟练使用CMake, Makefile构建工具，熟练使用gdb调试工具</li>
                <li>熟练使用git版本控制工具</li>
                <li>熟悉Linux OS</li>
                <li>了解计算机图形学，熟悉Phong光照模型，PBR材质，Shadow Mapping与光线追踪</li>
                <li>熟悉OpenGL API，熟悉GLSL语法，了解其特性</li>
            </ul>
        </section>

        <section>
            <h2>项目经历</h2>
            <ht />

            <div class="project">
                <h3>Sakuray Engine</h3>
                <li>2025.05 - 至今</li>
                <li>这是一个基于Unreal Engine架构的游戏引擎，计划实现2D渲染引擎，3D渲染引擎，支持PBR材质，支持GI，支持nanite与Lumin</li>
            </div>

            <div class="project">
                <h3><a href="https://github.com/SakurayYuzuru/Rasterizer.git">软光栅器</a></h3>
                <li>2025.01 - 2025.04</li>
                <li>这是一个基于SDL2的跨平台的C++软光栅器，实现了深度缓冲，颜色混合和Phong Shader</li>
            </div>

            <div class="project">
                <h3><a herf="https://www.gmhub.com/game/6933">独立游戏Demo: 泡泡魔法师</a></h3>
                <li>2025.01 - 2025.01</li>
                <h3></h3>
                <ul>
                    <li>实现角色状态机</li>
                    <li>实现部分道具交互</li>
                    <li>辅助进行关卡构建</li>
                </ul>
            </div>

            <div class="project">
                <h3><a href="https://github.com/ppkto/PARANOIA.git">独立游戏Demo: PARANOIA</a></h3>
                <h3>我的职责：</h3>
                <ul>
                    <li>实现主角的操控与弹幕技能功能</li>
                    <li>负责部分道具的交互设计</li>
                    <li>读取与处理剧情文档内容</li>
                    <li>使用Git进行版本控制和分支合并</li>
                </ul>
            </div>
            
        </section>

        <section>
            <h2>奖项荣誉</h2>
            <ht />

            <ul>
                <li>The 2024-2025 ICPC China Guizhou Provincial Programming Contest - Bronze Award</li>
                <li>参加CCF图形学启明星计划夏令营(CGPC2024)</li>
                <li>CET-4</li>
            </ul>
        </section>

        <section>
            <h2>自我评价</h2>
            <ht />

            <ul>
                <li>具有较强的求知欲，对计算机图形学和前沿技术充满兴趣</li>
                <li>具备较强的学习能力和独立分析、解决问题的能力</li>
                <li>具备较强的上进心和责任感，工作认真严谨</li>
                <li>具有较强的自学能力和抗压能力，工作态度积极负责，能够高效完成任务</li>
            </ul>
        </section>

        <img src="amiya.jpg">
    </body>
</html>
```

```css
body{
    font-family: Arial, sans-serif;
    margin: 40px;
    background: #f9f9f9;
    color: #333;
}

header{
    text-align: center;
    margin-bottom: 30px;
}

a{
    color: #007acc;
    text-decoration: none;
}

a:hover{
    text-decoration: underline;
}

h1{
    font-size: 2em;
    margin-bottom: 0;
}

table{
    width: 100%;
}

th{
    text-align: center;
}

h2{
    color: #444;
    border-bottom: 1px solid #ccc;
    padding-bottom: 5px;
}

section{
    margin-bottom: 30px;
}

.project{
    margin-bottom: 20px;
    padding: 10px;
    background-color: #f2f2f2;
    border-left: 4px solid #ccc;
    border-radius: 5px;
}

.project h3{
    margin-top: 0;
    color: #333;
}

ul{
    list-style-type: square;
    padding-left: 20px;
}

ht{
    border: none;
    border-top: 1px solid #ccc;
    margin: 30px 0;
}
```
### 问卷
```html
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
    <meta charset="UTF-8">
    <title>大学生暑期社会实践调查问卷</title>
    <link rel="stylesheet" href="config.css">
    </head>
    <body>
        <div class="container">
            <table>
                <tr>
                    <td>
                        <h1>大学生暑期社会实践调查问卷</h1>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <div class="intro">
                            <p>亲爱的同学：<br>
                                为了了解广大学生开展暑期社会实践的具体情况和实践效果，请你如实填写本问卷。<br>
                                本问卷不记名，数据仅用于统计分析，感谢你的支持和参与！
                            </p>
                        </div>
                    </td>
                </tr>

                <form action="#" method="post">
                    <tr>
                        <td>
                            <p>1. 你的性别：</p>
                            <label><input type="radio" name="gender" value="男"> 男</label>
                            <label><input type="radio" name="gender" value="女"> 女</label>
                        </td>
                    </tr>
                    
                    <tr>
                        <td>
                            <p>2. 所在的学院： <input type="text" name="college"></p>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <p>3. 所在年级：
                                <select name="grade">
                                    <option value="大一">大一</option>
                                    <option value="大二">大二</option>
                                    <option value="大三">大三</option>
                                    <option value="大四">大四</option>
                                </select>
                            </p>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <p>4. 你参与了哪些实践活动？（可多选）</p>
                            <label><input type="checkbox" name="activity" value="调研活动"> 调研活动</label><br>
                            <label><input type="checkbox" name="activity" value="志愿服务"> 志愿服务</label><br>
                            <label><input type="checkbox" name="activity" value="基层挂职"> 基层挂职</label><br>
                            <label><input type="checkbox" name="activity" value="企业实习"> 企业实习</label><br>
                            <label><input type="checkbox" name="activity" value="返乡宣讲"> 返乡宣讲</label><br>
                            <label><input type="checkbox" name="activity" value="其他"> 其他</label>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <p>5. 你认为暑期实践对你来说收获如何：</p>
                            <label><input type="radio" name="gain" value="收获大"> 收获大</label>
                            <label><input type="radio" name="gain" value="一般"> 一般</label>
                            <label><input type="radio" name="gain" value="没什么收获"> 没什么收获</label>
                        </td>
                    </tr>
                    
                    <tr>
                        <td>
                            <p>6. 你的实践是通过什么途径组织的？</p>
                            <label><input type="radio" name="organize" value="学校组织"> 学校组织</label>
                            <label><input type="radio" name="organize" value="自己参加"> 自己参加</label>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <p>7. 你在实践中遇到的问题主要是？</p>
                            <label><input type="radio" name="problem" value="时间短，准备不足"> 时间短，准备不足</label><br>
                            <label><input type="radio" name="problem" value="信息不对称"> 信息不对称</label><br>
                            <label><input type="radio" name="problem" value="不了解实际"> 不了解实际</label>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <p>8. 你对实践的建议：</p>
                            <textarea name="suggestion" placeholder="请填写您的意见..."></textarea><br><br>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <div class="button">
                                <input type="submit" value="提交">
                                <input type="reset" value="重置">
                            </div>
                        </td>
                    </tr>
                </form>
                <tr>
                    <td>
                        <p class="footer">版权所有：请联系学生事务管理工作服务平台 &copy; 2003-2020</p>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>
```

```css
body{
    background-color: #D6EAF8;
    font-family: "宋体", sans-serif;
}
  
.container{
    background-color: #D6EAF8;
    width: 600px;
    margin: 50px auto;
    padding: 20px;
}

.intro{
    color: #003399;
}

table{
    border-collapse: collapse;
}

td{
    border: 5px solid #ffffff;
    margin: 0;
    padding: 0;
}
  
h1{
    text-align: center;
    color: #003399;
}
  
textarea {
    width: 100%;
    height: 60px;
}
  
input[type="text"],
select {
    width: 200px;
    margin: 5px 0;
}
  
input[type="submit"],
input[type="reset"] {
    margin-right: 10px;
    padding: 5px 15px;
    background-color: #3498DB;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}
  
input[type="submit"]:hover,
input[type="reset"]:hover {
    background-color: #ffffff;
}
  
.footer {
    text-align: center;
    font-size: small;
    margin-top: 20px;
    color: #3498DB;
}

.button{
    text-align: center;
}
```