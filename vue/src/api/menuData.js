const menuData = {
    "msg": "操作成功",
    "code": 200,
    "data":{
        "admin":[
            {
                "name": "System",
                "path": "/system",
                "hidden": false,
                "redirect": "noRedirect",
                "component": "Layout",
                "alwaysShow": true,
                "meta": {
                    "title": "系统管理",
                    "icon": "system",
                    "noCache": false,
                    "link": null
                },
                "children": [
                    {
                        "name": "User",
                        "path": "user",
                        "hidden": false,
                        "component": "system/user/index",
                        "meta": {
                            "title": "用户管理",
                            "icon": "user",
                            "noCache": false,
                            "link": null
                        }
                    }
                ]
            },
            {
                "name": "Activities",
                "path": "/activities",
                "hidden": false,
                "redirect": "noRedirect",
                "component": "Layout",
                "alwaysShow": true,
                "meta": {
                    "title": "活动管理",
                    "icon": "phone",
                    "noCache": false,
                    "link": null
                },
                "children": [
                    {
                        "name": "Act",
                        "path": "act",
                        "hidden": false,
                        "component": "system/act/index",
                        "meta": {
                            "title": "活动管理",
                            "icon": "phone",
                            "noCache": false,
                            "link": null
                        }
                    },
                    {
                        "name": "Template",
                        "path": "template",
                        "hidden": false,
                        "component": "system/template/index",
                        "meta": {
                            "title": "模板管理",
                            "icon": "phone",
                            "noCache": false,
                            "link": null
                        }
                    }
                ]
            },
            {
                "name": "Resources",
                "path": "/resources",
                "hidden": false,
                "redirect": "noRedirect",
                "component": "Layout",
                "alwaysShow": true,
                "meta": {
                    "title": "资源",
                    "icon": "date",
                    "noCache": false,
                    "link": null
                },
                "children": [
                    {
                        "name": "Index",
                        "path": "index",
                        "hidden": false,
                        "component": "system/resources/index",
                        "meta": {
                            "title": "资源管理",
                            "icon": "date",
                            "noCache": false,
                            "link": null
                        }
                    },
                    {
                        "name": "Booking",
                        "path": "booking",
                        "hidden": false,
                        "component": "system/resources/booking",
                        "meta": {
                            "title": "资源预约",
                            "icon": "date",
                            "noCache": false,
                            "link": null
                        }
                    }
                ]
            },
            {
                "name": "Expense",
                "path": "/expense",
                "hidden": false,
                "redirect": "noRedirect",
                "component": "Layout",
                "alwaysShow": true,
                "meta": {
                    "title": "报销",
                    "icon": "email",
                    "noCache": false,
                    "link": null
                },
                "children": [
                    {
                        "name": "Index",
                        "path": "index",
                        "hidden": false,
                        "component": "system/expense/index",
                        "meta": {
                            "title": "报销管理",
                            "icon": "email",
                            "noCache": false,
                            "link": null
                        }
                    },
                    {
                        "name": "Apply",
                        "path": "apply",
                        "hidden": false,
                        "component": "system/expense/apply",
                        "meta": {
                            "title": "报销申请",
                            "icon": "email",
                            "noCache": false,
                            "link": null
                        }
                    },

                ]
            },
            {
                "name": "Notification",
                "path": "/notification",
                "hidden": false,
                "redirect": "noRedirect",
                "component": "Layout",
                "alwaysShow": true,
                "meta": {
                    "title": "活动通知",
                    "icon": "time",
                    "noCache": false,
                    "link": null
                },
                "children": [
                    {
                        "name": "Manage",
                        "path": "manage",
                        "hidden": false,
                        "component": "system/notification/manage",
                        "meta": {
                            "title": "管理通知",
                            "icon": "user",
                            "noCache": false,
                            "link": null
                        }
                    }
                ]
            },
            {
                "name": "Schedule",
                "path": "/schedule",
                "hidden": false,
                "redirect": "noRedirect",
                "component": "Layout",
                "alwaysShow": true,
                "meta": {
                    "title": "日程管理",
                    "icon": "date",
                    "noCache": false,
                    "link": null
                },
                "children": [
                    {
                        "name": "User",
                        "path": "user",
                        "hidden": false,
                        "component": "system/schedule/index",
                        "meta": {
                            "title": "日程管理",
                            "icon": "date",
                            "noCache": false,
                            "link": null
                        }
                    }
                ]
            },
        ],
        "user":[
            {
                "name": "Activities",
                "path": "/activities",
                "hidden": false,
                "redirect": "noRedirect",
                "component": "Layout",
                "alwaysShow": true,
                "meta": {
                    "title": "活动管理",
                    "icon": "phone",
                    "noCache": false,
                    "link": null
                },
                "children": [
                    {
                        "name": "Act",
                        "path": "act",
                        "hidden": false,
                        "component": "system/act/index",
                        "meta": {
                            "title": "活动管理",
                            "icon": "phone",
                            "noCache": false,
                            "link": null
                        }
                    },
                    {
                        "name": "Template",
                        "path": "template",
                        "hidden": false,
                        "component": "system/template/index",
                        "meta": {
                            "title": "模板管理",
                            "icon": "phone",
                            "noCache": false,
                            "link": null
                        }
                    }
                ]
            },
            {
                "name": "Expense",
                "path": "/expense",
                "hidden": false,
                "redirect": "noRedirect",
                "component": "Layout",
                "alwaysShow": true,
                "meta": {
                    "title": "报销",
                    "icon": "email",
                    "noCache": false,
                    "link": null
                },
                "children": [
                    {
                        "name": "Index",
                        "path": "index",
                        "hidden": false,
                        "component": "system/expense/index",
                        "meta": {
                            "title": "报销管理",
                            "icon": "email",
                            "noCache": false,
                            "link": null
                        }
                    },
                    {
                        "name": "Apply",
                        "path": "apply",
                        "hidden": false,
                        "component": "system/expense/apply",
                        "meta": {
                            "title": "报销申请",
                            "icon": "user",
                            "noCache": false,
                            "link": null
                        }
                    },

                ]
            },
            {
                "name": "Notification",
                "path": "/notification",
                "hidden": false,
                "redirect": "noRedirect",
                "component": "Layout",
                "alwaysShow": true,
                "meta": {
                    "title": "活动通知",
                    "icon": "time",
                    "noCache": false,
                    "link": null
                },
                "children": [
                    {
                        "name": "Index",
                        "path": "index",
                        "hidden": false,
                        "component": "system/notification/index",
                        "meta": {
                            "title": "查看通知",
                            "icon": "message",
                            "noCache": false,
                            "link": null
                        }
                    },
                ]
            },
            {
                "name": "Schedule",
                "path": "/schedule",
                "hidden": false,
                "redirect": "noRedirect",
                "component": "Layout",
                "alwaysShow": true,
                "meta": {
                    "title": "日程管理",
                    "icon": "date",
                    "noCache": false,
                    "link": null
                },
                "children": [
                    {
                        "name": "User",
                        "path": "user",
                        "hidden": false,
                        "component": "system/schedule/index",
                        "meta": {
                            "title": "日程管理",
                            "icon": "date",
                            "noCache": false,
                            "link": null
                        }
                    }
                ]
            },
        ]
    },
};
export default menuData;
