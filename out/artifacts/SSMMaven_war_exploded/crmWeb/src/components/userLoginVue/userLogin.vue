<template>
  <div class="app">
    <el-container class="loginMain" >
      <el-main>
        <el-row class="formStyle">
          <el-col :span="6" :offset="9">
            <el-form :label-position="labelPosition" ref="ruleLogin" label-width="80px" :model="ruleLogin" status-icon :rules="rule">
              <el-row>
                <el-col :span="24">
                  <el-form-item label="用户名" prop="userName">
                    <el-input v-model="ruleLogin.userName" auto-complete="off"></el-input>
                    <img src="../../assets/images/login.png" alt=""/>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="24">
                  <el-form-item label='用户密码'prop="userPwd" auto-complete="off">
                    <el-input v-model="ruleLogin.userPwd"></el-input>
                    <img src="../../assets/images/pwd.png" alt=""/>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="24">
                  <el-form-item class="vPwd" label="验证码" prop="validateCode" auto-complete="off">
                    <el-input v-model="ruleLogin.validateCode"></el-input>
                    <img src="../../assets/images/pwd.png" alt=""/>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="24">
                  <div class="loginBtn">
                    <el-button type="primary" plain @click="loginForm">  登录  </el-button>
                    <el-button type="warning" plain @click="resetForm('ruleLogin')" >  重置  </el-button>
                  </div>
                </el-col>
              </el-row>
            </el-form>

          </el-col>
        </el-row>
      </el-main>
    </el-container>
    <!--<Footer class="layout-footer-center">2011-2018 &copy; Visupervi</Footer>-->
  </div>
</template>
<script type="text/ecmascript-6">
  import axios from '../../servers/index.js'
  export default{
    data(){
      let checkUserName = (rule,value,callback) =>{
        if(!value){
          return callback(new Error("用户名不能为空"));
        }else{
          callback();
        }
      };
      let checkUserPwd = (rule,value,callback) =>{
        if(!value){
          return callback(new Error("用户密码不能为空"));
        }else{
          callback();
        }
      };
      let checkValidatorCode = (rule,value,callback) =>{
        if(!value){
          return callback(new Error("验证码不能为空"))
          console.log(11111)
        }else{
          callback();
        }
      }
      return {
        ruleLogin:{
          userName:'',
          userPwd:'',
          validateCode:''
        },
        labelPosition: 'left',
        rule:{
          userName:[
            {validator:checkUserName,trigger:'blur'}
          ],
          userPwd:[
            {validator:checkUserPwd,trigger:'blur'}
          ],
          validateCode:[
            {validator:checkValidatorCode,trigger:'blur'}
          ]
        }
      };
    },
    created(){

    },
    methods: {
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      loginForm(){
        console.log(this.ruleLogin.userName);
        console.log(this.ruleLogin.userPwd);
        this.$http(
          {
            type:'post',
            url:'apis/userLogin',
            data:{
              userName:this.ruleLogin.userName,
              userPassword:this.ruleLogin.userPwd
            }
          }
        ).then((res)=>{
          console.log(res)
        })
      }
    }
  }
</script>
<style lang="less">
  .app{
    height: 100%;
  }

  .loginMain{
    height: 100%;
    background:url("../../assets/images/loginbgc.jpg") 0px -100px no-repeat;
    button{
      width: 40%;
    }

    img{
      position: absolute;
      top: 10px;
      left: 10px;
    }
    .el-input__inner{
      padding: 0 30px;
    }
    .vPwd .el-input__inner:last-child{
      width: 60%;
    }
    .loginBtn{
      padding-left: 80px;
    }
    .formStyle{
      top:30%;
    }

  }
</style>
