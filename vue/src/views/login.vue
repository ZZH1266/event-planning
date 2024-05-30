<template>
    <div class="login">
        <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form">
            <h3 class="title">活动管理系统</h3>
            <el-form-item prop="username">
                <el-input
                        v-model="loginForm.username"
                        type="text"
                        auto-complete="off"
                        placeholder="账号"
                >
                    <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon"/>
                </el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input
                        v-model="loginForm.password"
                        type="password"
                        auto-complete="off"
                        placeholder="密码"
                        @keyup.enter.native="handleLogin"
                >
                    <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon"/>
                </el-input>
            </el-form-item>
            <!--<el-form-item prop="code" v-if="captchaEnabled">
                <el-input
                        v-model="loginForm.code"
                        auto-complete="off"
                        placeholder="验证码"
                        style="width: 63%"
                        @blur.native="checkCode"
                        @keyup.enter.native="handleLogin"
                >
                    <svg-icon slot="prefix" icon-class="validCode" class="el-input__icon input-icon"/>
                </el-input>
                <div class="login-code">
                    <img :src="codeUrl" @click="getCode" class="login-code-img"/>
                </div>
            </el-form-item>-->
            <el-checkbox v-model="loginForm.rememberMe" style="margin:0px 0px 25px 0px;">记住密码</el-checkbox>
            <el-form-item style="width:100%;">
                <el-button
                        :loading="loading"
                        size="medium"
                        type="primary"
                        style="width:100%;"
                        @click.native.prevent="handleLogin"
                >
                    <span v-if="!loading">登 录</span>
                    <span v-else>登 录 中...</span>
                </el-button>
                <div style="float: right;" v-if="register">
                    <router-link class="link-type" :to="'/register'">立即注册</router-link>

                </div>
                <div style="float: left;" v-if="forgot">
                    <router-link class="link-type" :to="'/forgotPassword'">忘记密码</router-link>
                </div>
            </el-form-item>
        </el-form>
        <!--  底部  -->
        <div class="el-login-footer">
            <span>Copyright © 2024 CMS (Campaign Management System)</span>
        </div>
    </div>
</template>

<script>
    import {getCodeImg} from "@/api/login";
    import Cookies from "js-cookie";
    import {decrypt, encrypt} from '@/utils/jsencrypt'

    export default {
        name: "Login",
        data() {
            return {
                codeUrl: "",
                loginForm: {
                    username: "admin",
                    password: "admin123",
                    rememberMe: false,
                    code: "",
                    uuid: ""
                },
                loginRules: {
                    username: [
                        {required: true, trigger: "blur", message: "请输入您的账号"}
                    ],
                    password: [
                        {required: true, trigger: "blur", message: "请输入您的密码"}
                    ],
                    code: [{required: true, trigger: "change", message: "请输入验证码"}]
                },
                loading: false,
                // 验证码开关
                captchaEnabled: true,
                // 注册开关
                register: true,
                // 忘记密码开关
                forgot: true,
                redirect: undefined
            };
        },
        watch: {
            $route: {
                handler: function (route) {
                    this.redirect = route.query && route.query.redirect;
                },
                immediate: true
            }
        },
        created() {
            this.getCode();
            this.getCookie();
        },
        methods: {
            getCode() {
                // getCodeImg().then(res => {
                //   this.captchaEnabled = res.captchaEnabled === undefined ? true : res.captchaEnabled;
                //   if (this.captchaEnabled) {
                //     this.codeUrl = "data:image/gif;base64," + res.img;
                //     this.loginForm.uuid = res.uuid;
                //   }
                // });
                this.codeUrl = "data:image/gif;base64,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"
            },
            getCookie() {
                const username = Cookies.get("username");
                const password = Cookies.get("password");
                const rememberMe = Cookies.get('rememberMe');
                this.loginForm = {
                    username: username === undefined ? this.loginForm.username : username,
                    password: password === undefined ? this.loginForm.password : decrypt(password),
                    rememberMe: rememberMe === undefined ? false : Boolean(rememberMe)
                };
            },
            checkCode() {

            },
            handleLogin() {
                // TODO 登录验证
                this.$refs.loginForm.validate(valid => {
                    if (valid) {
                        this.loading = true;
                        if (this.loginForm.rememberMe) {
                            Cookies.set("username", this.loginForm.username, {expires: 30});
                            Cookies.set("password", encrypt(this.loginForm.password), {expires: 30});
                            Cookies.set('rememberMe', this.loginForm.rememberMe, {expires: 30});
                        } else {
                            Cookies.remove("username");
                            Cookies.remove("password");
                            Cookies.remove('rememberMe');
                        }
                        this.$store.dispatch("Login", this.loginForm).then(() => {
                            this.$router.push({path: this.redirect || "/"}).catch(() => {
                            });
                        }).catch(() => {
                            this.loading = false;
                            if (this.captchaEnabled) {
                                this.getCode();
                            }
                        });
                    }
                });
            }
        }
    };
</script>

<style rel="stylesheet/scss" lang="scss">
    .login {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100%;
        background-image: url("../assets/images/login-background.jpg");
        background-size: cover;
    }

    .title {
        margin: 0px auto 30px auto;
        text-align: center;
        color: #707070;
    }

    .login-form {
        border-radius: 6px;
        background: #ffffff;
        width: 400px;
        padding: 25px 25px 5px 25px;

        .el-input {
            height: 38px;

            input {
                height: 38px;
            }
        }

        .input-icon {
            height: 39px;
            width: 14px;
            margin-left: 2px;
        }
    }

    .login-tip {
        font-size: 13px;
        text-align: center;
        color: #bfbfbf;
    }

    .login-code {
        width: 33%;
        height: 38px;
        float: right;

        img {
            cursor: pointer;
            vertical-align: middle;
        }
    }

    .el-login-footer {
        height: 40px;
        line-height: 40px;
        position: fixed;
        bottom: 0;
        width: 100%;
        text-align: center;
        color: #fff;
        font-family: Arial;
        font-size: 12px;
        letter-spacing: 1px;
    }

    .login-code-img {
        height: 38px;
    }
</style>
