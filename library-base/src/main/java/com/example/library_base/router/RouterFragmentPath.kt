package com.example.library_base.router

class RouterFragmentPath {

    /**
     * 首页组件
     */
    object Home {
        private const val HOME = "/home"

        /*首页*/
        const val PAGER_HOME = HOME + "/home"
    }

    /**
     * 社区组件
     */
    object Community {
        private const val COMMUNITY = "/community"

        /*工作*/
        const val PAGER_COMMUNITY = COMMUNITY + "/community"
    }

    /**
     * 通知组件
     */
    object Notice {
        private const val NOTICE = "/notice"

        /*消息*/
        const val PAGER_NOTICE = NOTICE + "/notice/Notice"
    }

    /**
     * 用户组件
     */
    object User {
        private const val USER = "/user"

        /*我的*/
        const val PAGER_ME = USER + "/Me"
    }
}