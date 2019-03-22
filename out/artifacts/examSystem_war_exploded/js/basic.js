// JavaScript Document

var VH = 0;
function fixLayout(){
    var h = [];
    VH = sanView.viewport.height() - parseInt($('#main-page-head').outerHeight()) - parseInt($('#main-page-foot').outerHeight());
    $('#main-page-body').css('min-height', VH + 'px');
    h.push(VH);
    var subPageHeight = parseFloat($('#main-page-body-content').children('.sub-page').outerHeight(true)),
        menuHeight = parseFloat($('#main-menu').outerHeight(true));
    h.push(subPageHeight, menuHeight);
    h.sort(function(a, b){return b - a});
    $('#main-page-body').css('height', h[0] + 'px');
    //$.parser.parse('#main-page-body-content');
}


 /**
 * sanPlaceholder
 * placeholder模拟器
 * @author: zhxming
 */
;(function($) {
    'use strict';

    var config = {
        placeholder: 'san-placeholder-text',     //class，placeholder元素
        input: 'san-placeholder-input',          //class，placeholder对应的输入域
        active: 'san-placeholder-text-active'    //class，placeholder显示状态
    }

    var methods = {
        init: function(option){
            return this.each(function(){
                var $this = $(this);
                var opt = $.extend({}, config, option);
                $this.data('opt', opt);

                var $input = $this.find('.' + opt.input),
                    $placeholder = $this.find('.' + opt.placeholder);

                if (!$placeholder.length) return;

                if ($input.val() == ''){
                    $placeholder.addClass(opt.active);
                }else{
                    $placeholder.removeClass(opt.active);
                }

                $placeholder.on('click.sanPlaceholder', function(){
                    $input[0].focus();
                });

                $input.on('blur.sanPlaceholder', function(){
                    if ($(this).val() != ''  || $placeholder.hasClass(opt.active)) {return;}
                    $placeholder.addClass(opt.active);
                });

                $input.on('keyup.sanPlaceholder', function(){
                    if ($(this).val() != ''){
                        $placeholder.removeClass(opt.active);
                    }else{
                        $placeholder.addClass(opt.active);
                    }
                });
            });
        }
    }

    $.fn.sanPlaceholder = function(option) {
        var o = arguments[0];
        if (typeof o === 'object' || !o) return methods['init'].apply(this, arguments);
        if (!methods[o]) return;
        return methods[o].apply(this, Array.prototype.slice.call(arguments, 1));
    }
})(jQuery);



 /**
 * sanMainMenu
 * 主菜单控制器
 * @author: zhxming
 */
;(function($) {
    'use strict';

    var config = {
        'level1': 'level-1',            //class，一级菜单项(<a>)
        'subMenu': 'sub-menu',          //class, 下拉菜单
        'level2': 'level-2',            //class，二级菜单项(<a>)
        'current': 'current',           //class，当前选中菜单项(<li>)
        'active': 'active',             //class，当前活动菜单项(<li>)
        'default': true,                //是否有默认显示项
        'defaultItem': [0, 0],          //菜单默认显示项，数组项1为一级菜单项，数组项2为二级菜单项
        'url': 'data-url',              //加载链接
        'hash': true,                   //记录hash值
        'hashFlag': 'data-hash',        //记录hash值标记，=1时记录当前的hash值
        'hashValue': 'data-url',        //hash值
        'slide': true
    }

    var methods = {
        init: function(option){
            return this.each(function(){
                var $this = $(this);
                var opt = $.extend({}, config, option);
                $this.data('opt', opt);
                //标注菜单项级别
                $this.find('a').each(function(){
                    if ($(this).hasClass(opt.level1)){
                        $(this).attr('data-level', '1');
                        return;
                    }
                    if ($(this).hasClass(opt.level2)){
                        $(this).attr('data-level', '2');
                        return;
                    }
                    $(this).attr('data-level', '0');
                });
                
                // default
                if (opt.default) methods.defaultItem.call($this);

                // event
                $this.on('click.sanMainMenu', 'a', function(){
                    if (!parseInt($(this).attr('data-level'))) return;

                    // level 1
                    if ($(this).attr('data-level') == 1){
                        // sub-menu
                        if ($(this).nextAll('.' + opt.subMenu).length){
                            if ($(this).parent().hasClass(opt.current) || $(this).parent().hasClass(opt.active)) return;
                            methods.show.call($this, $(this));
                            if ($(this).attr(opt.url)){
                                methods.load.call($this, $(this));
                            }
                            return;
                        }
                        //jumpTo
                        // methods.setCurrent.call($this, $(this));
                        methods.load.call($this, $(this));
                    }
                    // level 2
                    if ($(this).attr('data-level') == 2){
                        //jumpTo
                        // methods.setCurrent.call($this, $(this));
                        methods.load.call($this, $(this));
                    }
                });

                // 隐藏/显示菜单
                if (opt.slide) methods.sliding.call($this);

                if (opt.hash){
                    methods.hashLoad.call($this);
                }
            });
        },
        /* 
         * 显示下拉菜单
         * $item - jquery对象，一级菜单项(<a>)
         */
        show: function($item){
            return this.each(function(){
                var $this = $(this),
                    $lv1 = $item.parent(),
                    opt = $this.data('opt');

                $item.next('.' + opt.subMenu).slideDown(200, function(){
                    layoutPatch.call($this);
                });

                $lv1.addClass(opt.active);
                // 清除其它项目的active状态
                $lv1.siblings('.' + opt.active).each(function(){
                    methods.hide.call($this, $(this).find('.' + opt.level1));
                });
            });
        },
        /* 
         * 隐藏下拉菜单
         * $item - jquery对象，一级菜单项(<a>)
         */
        hide: function($item){
            return this.each(function(){
                var $this = $(this),
                    $lv1 = $item.parent(),
                    opt = $this.data('opt');

                // active
                if ($lv1.hasClass(opt.active)){
                    $lv1.removeClass(opt.active).find('.' + opt.subMenu).slideUp(10, function(){
                        layoutPatch.call($this);
                    });
                    return;
                }
                // current
                if ($lv1.hasClass(opt.current)){
                    $lv1.find('.' + opt.current).removeClass(opt.current);
                    $lv1.removeClass(opt.current).find('.' + opt.subMenu).slideUp(10, function(){
                        layoutPatch.call($this);
                    });
                    return;
                }
            });
        },
        /* 
         * 设置当前菜单项
         * $item - jquery对象，菜单项(<a>)
         */
        setCurrent: function($item){
            return this.each(function(){
                var $this = $(this),
                    opt = $this.data('opt');

                // $item不存在，反向操作，去除菜单项所有状态
                if (!$item) {
                    methods.clear.call($this);
                    return;
                }

                var level = parseInt($item.attr('data-level')),
                    $lv1 = null,
                    $lv2 = null;

                // level
                if (level == 1){
                    $lv1 = $item.parent();
                }else{
                    $lv2 = $item.parent();
                    $lv1 = $lv2.closest('.' + opt.subMenu).prev('.' + opt.level1).parent();
                }

                // level 2
                if ($lv2){
                    var $sub = $lv2.closest('.' + opt.subMenu);
                    // 显示下拉菜单
                    if ($sub.is(':hidden')) methods.show.call($this, $sub.prev('.' + opt.level1));
                    $lv2.addClass(opt.current).siblings().removeClass(opt.current);
                }
                //level 1
                $lv1.addClass(opt.current).removeClass(opt.active);

                //清除其它菜单项状态
                $lv1.siblings('.' + opt.current).each(function(){
                    methods.hide.call($this, $(this).find('.' + opt.level1));
                });
                $lv1.siblings('.' + opt.active).each(function(){
                    methods.hide.call($this, $(this).find('.' + opt.level1));
                });
            });
        },
        /* 
         * 设置默认选中菜单项
         * 未设置参数或参数设置错误均默认为0
         */
        defaultItem: function(){
            return this.each(function(){
                var $this = $(this),
                    opt = $this.data('opt');

                var $def = null;
                var $item1 = $this.find('.' + opt.level1 +':eq(' + opt.defaultItem[0] + ')');
                if (!$item1.length) $item1 = $this.find('.' + opt.level1 + ':eq(0)');
                $def = $item1;
                var $sub = $item1.nextAll('.' + opt.subMenu);
                if ($sub.length){
                    var $item2 = $sub.find('.' + opt.level2 +':eq(' + opt.defaultItem[1] + ')');
                    if (!$item2.length) $item2 = $sub.find('.' + opt.level2 + ':eq(0)');
                    $def = $item2;
                }
                methods.setCurrent.call($this, $def);
            });
        },
        /* 
         * 加载指定页面
         * $item - jquery对象，菜单项(<a>)
         */
        load: function($item){
            return this.each(function(){
                var $this = $(this),
                    opt = $this.data('opt');

                if (opt.hash && $item.attr(opt.hashFlag) == '1' && $item.attr(opt.hashValue)){
                    //console.log('load: ' + encodeURIComponent($item.attr(opt.hashValue)));
                    window.location.hash = encodeURIComponent('!' + $item.attr(opt.hashValue));
                    return;
                }
                console.log('no hash');
                $('#main-page-body-content').sanJumpto({
                    url: $item.attr(opt.url),
                    onBefore: function(){
                        $('#main-page-body').removeClass('bg-161014');
                        // ...
                    },
                    onLoaded: function(){
                        // ...
                    },
                    onDone: function(){
                        // $.parser.parse('#main-page-body-content');
                        window.location.hash = encodeURIComponent('!');
                        layoutPatch.call($this);
                    },
                    onFail: function(){
                        layoutPatch.call($this);
                    }
                });
            });
        },
        hashLoad: function(){
            return this.each(function(){
                var $this = $(this),
                    opt = $this.data('opt');

                $(window).bind('hashchange.sanMainMenu', function(){
                    //console.log('window.hash: ' + window.location.hash.toLocaleLowerCase());
                    var hash = decodeURIComponent(window.location.hash.toLocaleLowerCase()).substring(2);
                    if (!hash || hash == '!'){
                        return;
                    }
                    $('#main-page-body-content').sanJumpto({
                        url: hash,
                        onBefore: function(){
                            $('#main-page-body').removeClass('bg-161014');
                        },
                        onLoaded: function(){
                            // ...
                        },
                        onDone: function(){
                            // $.parser.parse('#main-page-body-content');
                            layoutPatch.call($this);
                        },
                        onFail: function(){
                            layoutPatch.call($this);
                        }
                    });
                }).trigger('hashchange.sanMainMenu');
            });
        },
        /* 
         * 清楚所有菜单项的状态
         */
        clear: function(){
            return this.each(function(){
                var $this = $(this),
                    opt = $this.data('opt');
                
                var $active = $this.children('.' + opt.active);
                if ($active.length){
                     methods.hide.call($this, $active.find('.' + opt.level1));
                }
                var $item = $this.find('.' + opt.subMenu).find('.' + opt.current);
                if ($item.length){
                    methods.hide.call($this, $item.closest('.' + opt.subMenu).prev('.' + opt.level1));
                    return;
                }
                $item = $this.children('.' + opt.current);
                if ($item.length){
                    methods.hide.call($this, $item.find('.' + opt.level1));
                    return;
                }
            });
        },
        /* 
         * 主菜单的侧边方向上的显示/隐藏/滑动效果
         * 必须与HTML结构对应
         * main-menu-slide： class，隐藏式主菜单
         * main-menu-slide-active： class，隐藏式主菜单显示状态
         */
        sliding: function(){
            return this.each(function(){
                var $bodyLeft = $('#main-page-body-left'),
                    $bodyContent = $('#main-page-body-content'),
                    $slideHandle = $('#main-menu-slide-handle'),
                    $menu = $('#main-menu');

                var slide = $bodyLeft.hasClass('main-menu-slide'),
                    active = $bodyLeft.hasClass('main-menu-slide-active');

                $slideHandle.find('.san-icon-32').attr('title', '隐藏主菜单');

                $slideHandle.on('click.sanMainMenu', 'a', function(){
                    var wl = parseInt($(window).scrollLeft());
                    if (!slide){
                        slide = true;
                        $bodyLeft.addClass('main-menu-slide').stop(true, true).animate({'left':'-160px'}, 200);
                        $slideHandle.animate({'left':-160 - wl + 'px'}, 200).find('.san-icon-32').attr('title', '固定主菜单');
                        $slideHandle.find('a').animate({'left':'160px'}, 200);
                        $bodyContent.stop(true, true).animate({'margin-left':'40px'}, 200, function(){
                            $bodyContent.find('.datagrid-f').datagrid('resize');
                        });
                        $menu.stop(true, true).animate({'opacity':0}, 200, function(){
                            $(this).css('margin-left', '-40px');
                        });
                    }else{
                        slide = false;
                        active = false;
                        $bodyLeft.removeClass('main-menu-slide main-menu-slide-active').stop(true, true).animate({'left':0}, 200);
                        $slideHandle.animate({'left': 0 - wl + 'px'}, 200).find('.san-icon-32').attr('title', '隐藏主菜单');
                        $slideHandle.find('a').animate({'left': 0}, 200);
                        $bodyContent.stop(true, true).animate({'margin-left':'200px'}, 200, function(){
                            $bodyContent.find('.datagrid-f').datagrid('resize');
                        });
                        $menu.css('margin-left', 0).stop(true, true).animate({'opacity':1}, 200);
                    };
                });

                $bodyLeft.on('mouseenter.sanMainMenu', function(){
                    if (!slide) return;
                    var wl = parseInt($(window).scrollLeft());
                    $bodyLeft.stop(true, true).animate({'left':0}, 200, function(){
                        $bodyLeft.addClass('main-menu-slide-active');
                        active = true;
                    });
                    $slideHandle.stop(true, true).animate({'left': 0 - wl + 'px'}, 200);
                    $slideHandle.find('a').stop(true, true).animate({'left': 0}, 200);
                    $menu.css('margin-left', 0).stop(true, true).animate({'opacity':1}, 200);
                }).on('mouseleave.sanMainMenu', function(){
                    if (!slide) return;
                    var wl = parseInt($(window).scrollLeft());
                    $bodyLeft.stop(true, true).animate({'left':'-160px'}, 200, function(){
                        $('#main-page-body-left').removeClass('main-menu-slide-active');
                        active = false;
                    });
                    $slideHandle.stop(true, true).animate({'left':-160 - wl + 'px'}, 200);
                    $slideHandle.find('a').stop(true, true).animate({'left': '160px'}, 200);
                    $menu.stop(true, true).animate({'opacity':0}, 200, function(){
                        $(this).css('margin-left', '-40px');
                    });
                });

                $(window).on('scroll.sanMainMenu', function(){
                    if (active) return;
                    var wl = $(window).scrollLeft();
                    if (slide) {wl = wl + 160;}
                    $slideHandle.css('left', '-' + wl + 'px');
                });
            });
        }
    }

    function layoutPatch(){
        fixLayout();
    }

    $.fn.sanMainMenu = function(option) {
        var o = arguments[0];
        if (typeof o === 'object' || !o) return methods['init'].apply(this, arguments);
        if (!methods[o]) return;
        return methods[o].apply(this, Array.prototype.slice.call(arguments, 1));
    }
})(jQuery);



/**
 * 
 * 获取菜单项标记
 * 菜单项标记用于设置指定菜单项的选中状态
 * 例：
 * // 菜单项标记为隐藏域chanel-sign的值
 * <input type="hidden" id="chanel-sign" value="subpage-form">
 * // 调用setCurrent方法设置状态
 * $('#main-menu').sanMainMenu('setCurrent', setMenu());
 */
function setMenu(option){
    var config = {
        'signId': 'chanel-sign',
        'menuId': 'main-menu',
        'chanel': 'data-chanel'
    }
    var opt = $.extend({}, config, option);
    var ch = $.trim($('#' + opt.signId).val()).toLowerCase();
    var $target = $('#' + opt.menuId).find('[' + opt.chanel + '="' + ch + '"]');
    if (!ch || !$target.length) $target = null;
    return $target;
}



 /**
 * sanJumpto
 * ajax加载
 * @author: zhxming
 */
;(function($){
    'use strict';

    var config = {
        jumpType: 'ajax',
        //加载文件路径，默认从自定义属性data-url获取
        target: 'data-url',
        //指定加载文件路径，优先于target
        url: '',
        //加载对象不存在时默认指向url
        defaultUrl: 'temp.html',
        //将过滤掉多余标签，只保留<body>内部分
        filterKey: 'body',
        //是否允许注入替换
        inject: false,
        //注入变量。对象。在页面中必须以 {{ 和 }} 包裹住，当数据获取成功并且还未加载时，替换成下列自定义的属性值
        injectedVar: {},
        async: true,
        cache: false,
        //type: 'GET',
        type: 'POST',
        //回调方法，在请求发起前执行。
        onBefore: function(){},
        //回调方法，在请求成功后执行，在获取数据之后，DOM操作之前执行。
        onLoaded: function(){},            
        //回调方法，在请求成功后执行，在DOM操作完成之后执行。
        onDone: function(){},
        //回调方法，在请求失败后执行。
        onFail: function(){},  
        //回调方法，在请求完成后的执行，不论请求成功或失败。
        onAlways: function(){}
    }

    var methods = {
        init: function(option){
            return this.each(function(){
                var opt = $.extend({}, config, option);
                var $this = $(this);
                $this.data('opt', opt);

                if (opt.jumpType == 'ajax'){
                    methods.ajax.call($this);
                }
            });
        },
        ajax: function(){
            return this.each(function(){
                var $this = $(this);
                var opt = $this.data('opt');

                var u;
                u = opt.url || $this.attr(opt.target) || opt.defaultUrl;

                $.ajax({
                    url: convertURL(u),
                    cache: opt.cache,
                    async: opt.async,
                    type: opt.type,
                    beforeSend: function(){
                        //easyui dialog
                        //$('body').find('.z-easyui-dialog').dialog('destroy');
                        opt.onBefore.call($this);
                    }
                }).done(function(data){
                    var html = data;
                    if (parseHTML(data)) {
                        var str = data.slice(data.indexOf('<' + opt.filterKey));
                        var start = str.indexOf('>') + 1,
                            end = str.lastIndexOf('</' + opt.filterKey + '>');
                        html = $.trim(str.slice(start, end));
                    }
                    
                    //替换注入变量
                    if (opt.inject){
                        html = html.replace(/\{\{[^\}\}]*\}\}/g, function(m){
                            var _m = m.replace(/(\{\{|\}\})/g, '');
                            var s = opt.injectedVar[_m];
                            if (s) return s;
                            return m;
                        });
                        
                    }

                    opt.onLoaded.call($this);

                    $.when($this.html(html).promise()).done(function(){
                        opt.onDone.call($this);
                    });

                    function parseHTML(data){
                        var page = data.indexOf('<' + opt.filterKey);
                        if (page == -1) return false;
                        return true;
                    }
                }).always(function(){
                    opt.onAlways.call($this);
                }).fail(function(){
                    opt.onFail.call($this);
                });
            });
        }
    }

    $.fn.sanJumpto = function(){
        var o = arguments[0];
        if (typeof o === 'object' || !o) return methods['init'].apply(this, arguments);
        if (!methods[o]) return;
        return methods[o].apply(this, Array.prototype.slice.call(arguments, 1));
    };
})(jQuery);   



//为URL添加时间戳
function convertURL(url){  
    var timstamp = (new Date()).valueOf();  
    if (url.indexOf('?') >= 0){  
        url = url + '&t=' + timstamp;   
    }else{  
        url = url + '?t=' + timstamp;  
    };  
    return url;  
}; 



/**
 * sanView
 * 获取视口和文档大小
 * @author: zhxming
 */
var sanView = {
    viewport: {
        width: function(){
            return window.innerWidth || document.documentElement.clientWidth;
        },
        height: function(){
            return window.innerHeight || document.documentElement.clientHeight;
        }
    },
    documentViewport: {
        width: function(){
            return document.documentElement.clientWidth || document.body.clientWidth;
        },
        height: function(){
            return document.documentElement.clientHeight || document.body.clientHeight;
        }
    },
    document: {
        width: function(){
            if (document.compatMode == "BackCompat"){
                return Math.max(document.body.scrollWidth, document.body.clientWidth);
            }else{
                return Math.max(document.documentElement.scrollWidth, document.documentElement.clientWidth);
            }
        },
        height: function(){
            if (document.compatMode == "BackCompat"){
                return Math.max(document.body.scrollHeight, document.body.scrollHeight);
            }else{
                return Math.max(document.documentElement.scrollHeight, document.documentElement.clientHeight);
            }
        }
    }
};



var MONTH_CHS = new Array("1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月");
var WEEK_CHS = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六","星期日");
function showToday(id, interval){
    setInterval(function(){
        var today = new Date();
        var _year = today.getFullYear(),
            _month = today.getMonth(),
            _date = today.getDate(),
            _week = today.getDay()
        $('#' + id).text(_year + '-' + (_month + 1) + '-' + _date + ' ' + WEEK_CHS[_week]);
    }, interval);
}




