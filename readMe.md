采用Springboot 1.4.0 版本，安全框架spring-security，数据访问层采用Spring Data Jpa，采用thymeleaf模版引擎和restful风格。后台管理使用easyUI，前台展示使用bootstrap。

主要是电影相关信息的增删改查



- 亮点1: 使用AOP 减少重复代码   


​	具体：当后台对电影信息、电影动态信息、网址等进行添加、修改、删除操作时，自动更新application中的缓存数据，保持前台展示的数据为最新状态。如果不使用AOP，则需要在多个controller里面添加同样的代码，十分累赘，不便于维护。

主要代码(已简化)：

```java
@Aspect
@Component
public class HttpAspect {

    //定义一个公用方法
    @Pointcut("execution(public * com.zjx.controller.admin.*.save(..)) || execution(public * com.zjx.controller.admin.*.delete(..))")
    public void pointcut(){

    }
    
    @After("pointcut()")
    public void doAfter(){
        logger.info("切点执行doAfter");
        startupRunner.loadData();
    }
}

    /**
     * 加载数据到applicaton缓存中
     */
    public void loadData() {
        application.setAttribute("newestInfoList", webSiteInfoService.list(null, 0, 10)); // 最新10条电影动态
        Film film = new Film();
        film.setHot(1);
        application.setAttribute("newestHotFilmList", filmService.list(film, 0, 10)); // 获取最新10条热门电影
        application.setAttribute("newestIndexHotFilmList", filmService.list(film, 0, 32)); // 获取最新32条热门电影 首页显示用到
        application.setAttribute("newestWebSiteList", webSiteService.newestList(0, 10)); // 获取最新10条电影网站收录
        application.setAttribute("newestFilmList", filmService.list(null, 0, 10)); // 获取最新10条电影信息
        application.setAttribute("linkList", linkService.listAll()); // 查询所有友情链接
    }
```





- 亮点2：刷新按钮  图标是用的easyUI的图标，但是没有提供点击事件。easyUI每个标签页默认最右边只能至多有一个关闭按钮。所以当加载标签页时通过jQuery动态的添加元素，并添加点击事件![refresh](https://raw.githubusercontent.com/duanwandao/Film/master/doc/pictures/refresh.png)


主要代码(已简化)：

```java
// $()函数的另外一个作用：动态创建元素
//在标题文字后面添加span元素，就是添加reload图片，必须要有空格作为背景，否则图片不显示
//判断是否已经添加过元素，否则每次都会添加span元素
 if($("span.tabs-title:contains("+text+") + span.icon-reload").length==0){
     $("<span class='icon-reload'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>").insertAfter("span.tabs-title:contains(" + text + ")");
 }

 //刷新按钮绑定单击事件
    $("body").on('click', '.icon-reload', function () {

        var tabs = $('#tabs').tabs('tabs');
        var tabIndex = $(this).closest('li').index();   //打开的第几个tab
        var title = $('.tabs-selected').text();    //选中的tab页的title,这个是动态获取。

        $('#tabs').tabs('update', {
            tab: tabs[tabIndex],
            options: {
                content: myTabUpdate(title)
            }
        });
    });

```

