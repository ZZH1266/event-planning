import {getInfo, login, logout} from '@/api/login'
import {getToken, removeToken, setToken} from '@/utils/auth'

const user = {
    state: {
        token: getToken(),
        id: '',
        name: '',
        avatar: '',
        roles: [],
        permissions: []
    },

    mutations: {
        SET_TOKEN: (state, token) => {
            state.token = token
        },
        SET_ID: (state, id) => {
            state.id = id
        },
        SET_NAME: (state, name) => {
            state.name = name
        },
        SET_AVATAR: (state, avatar) => {
            state.avatar = avatar
        },
        SET_ROLES: (state, roles) => {
            state.roles = roles
        },
        SET_PERMISSIONS: (state, permissions) => {
            state.permissions = permissions
        }
    },

    actions: {
        // 登录
        Login({commit}, userInfo) {
            const username = userInfo.username.trim()
            const password = userInfo.password;
            const code = userInfo.code;
            const uuid = userInfo.uuid;
            return new Promise((resolve, reject) => {
                login(username, password, code, uuid).then(res => {
                    if (res.code === 200) {
                        /*setToken(res.token);
                        commit('SET_TOKEN', res.token);
                        resolve();*/
                        commit('SET_ID', res.data.id);
                        setToken("AAAABBBBCCCCDDDDEEEEFFFFGGGGHHHH");
                         commit('SET_TOKEN', "AAAABBBBCCCCDDDDEEEEFFFFGGGGHHHH");
                         resolve();
                    }

                }).catch(error => {
                    reject(error);
                })

            })
        },

        // 获取用户信息
        GetInfo({commit, state}) {
            return new Promise((resolve, reject) => {
                //getInfo().then(res => {
                var res={};
                if (state.id >= 2) {//user
                    res = {
                        "msg": "操作成功",
                        "code": 200,
                        "permissions": [
                            "*:*:*"
                        ],
                        "roles": [
                            "user"
                        ],
                        "user": {
                            "createBy": "admin",
                            "createTime": "2023-04-23 16:11:38",
                            "updateBy": null,
                            "updateTime": null,
                            "remark": "管理员",
                            "userId": 3,
                            "deptId": 103,
                            "userName": "test",
                            "nickName": "test",
                            "email": "admin@163.com",
                            "phonenumber": "15888888888",
                            "sex": "1",
                            "avatar": "",
                            "password": "$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2",
                            "status": "0",
                            "delFlag": "0",
                            "loginIp": "117.60.233.168",
                            "loginDate": "2024-05-16T11:21:32.000+08:00",
                            "roleIds": null,
                            "postIds": null,
                            "roleId": null,
                            "admin": false
                        }
                    };
                }else{
                    res = {
                        "msg": "操作成功",
                        "code": 200,
                        "permissions": [
                            "*:*:*"
                        ],
                        "roles": [
                            "admin"
                        ],
                        "user": {
                            "createBy": "admin",
                            "createTime": "2023-04-23 16:11:38",
                            "updateBy": null,
                            "updateTime": null,
                            "remark": "管理员",
                            "userId": 1,
                            "deptId": 103,
                            "userName": "admin",
                            "nickName": "管理员",
                            "email": "admin@163.com",
                            "phonenumber": "15888888888",
                            "sex": "1",
                            "avatar": "",
                            "password": "$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2",
                            "status": "0",
                            "delFlag": "0",
                            "loginIp": "117.60.233.168",
                            "loginDate": "2024-05-16T11:21:32.000+08:00",
                            "dept": {
                                "createBy": null,
                                "createTime": null,
                                "updateBy": null,
                                "updateTime": null,
                                "remark": null,
                                "deptId": 103,
                                "parentId": 101,
                                "ancestors": "0,100,101",
                                "deptName": "研发部门",
                                "orderNum": 1,
                                "leader": "admin",
                                "phone": null,
                                "email": null,
                                "status": "0",
                                "delFlag": null,
                                "parentName": null,
                                "children": []
                            },
                            "roles": [
                                {
                                    "createBy": null,
                                    "createTime": null,
                                    "updateBy": null,
                                    "updateTime": null,
                                    "remark": null,
                                    "roleId": 1,
                                    "roleName": "超级管理员",
                                    "roleKey": "admin",
                                    "roleSort": 1,
                                    "dataScope": "1",
                                    "menuCheckStrictly": false,
                                    "deptCheckStrictly": false,
                                    "status": "0",
                                    "delFlag": null,
                                    "flag": false,
                                    "menuIds": null,
                                    "deptIds": null,
                                    "permissions": null,
                                    "admin": true
                                }
                            ],
                            "roleIds": null,
                            "postIds": null,
                            "roleId": null,
                            "admin": true
                        }
                    };
                }

                const user = res.user
                const avatar = (user.avatar == "" || user.avatar == null) ? require("@/assets/images/profile.jpg") : process.env.VUE_APP_BASE_API + user.avatar;
                if (res.roles && res.roles.length > 0) { // 验证返回的roles是否是一个非空数组
                    commit('SET_ROLES', res.roles)
                    commit('SET_PERMISSIONS', res.permissions)
                } else {
                    commit('SET_ROLES', ['ROLE_DEFAULT'])
                }
                commit('SET_ID', user.userId)
                commit('SET_NAME', user.userName)
                commit('SET_AVATAR', avatar)
                resolve(res)
                //}).catch(error => {
                //reject(error)
                //})
            })
        },

        // 退出系统
        LogOut({commit, state}) {
            return new Promise((resolve, reject) => {
                //logout(state.token).then(() => {
                commit('SET_TOKEN', '')
                commit('SET_ROLES', [])
                commit('SET_PERMISSIONS', [])
                removeToken()
                resolve()
                // }).catch(error => {
                //   reject(error)
                // })
            })
        },

        // 前端 登出
        FedLogOut({commit}) {
            return new Promise(resolve => {
                commit('SET_TOKEN', '')
                removeToken()
                resolve()
            })
        }
    }
}

export default user
