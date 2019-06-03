# Calculator高级计算器
        目标：
        用java编程实现计算器的运算功能，包括加、减、乘、除，以及求负、取余等。
        主要用到的组件有框架、面板、文本框、按钮和菜单栏。
        编程思想：
        一、界面制作
        定义计算器类Calculator，该类继承JFrame类。
        参考思路：
        （1）界面整体窗口采用边界布局，通过另外建立若干面板组织组件。
        布局策略如下：
        North位置的上放一个面板，文本框放入其中，用于显示运算中的数据及结果。
        Center位置放一个大面板，North放Back与C按钮,Center放其他按钮。
        对于各个面板内部，可选择一定的布局策略进行布局。
        （2）添加菜单项，其中，点击“帮助”菜单后，界面如下。
![Image test](https://raw.githubusercontent.com/JinhuaM/Picture/master/界面.png)
点击“关于”菜单项，弹出对话框，如下：
![Image test](https://raw.githubusercontent.com/JinhuaM/Picture/master/提示框.png)
点击“文件”菜单后，界面如下：
该菜单第一个菜单项为空操作，单击第二个菜单项“退出”会退出程序的运行。
![Image test](https://raw.githubusercontent.com/JinhuaM/Picture/master/界面2.png)
