<template>
  <view>
    <view class="container diy_view">
      <view>
        <view>
          <view class="">
            <uni-forms :modelValue="form"  v-if="is_view()">

              <uni-forms-item v-if="$check_field('get','order_number') || $check_field('add','order_number') || $check_field('set','order_number')" label="订单编号" name="order_number">
                            <uni-easyinput type="text" v-model="form['order_number']" v-if="user_group === '管理员' || (form['room_orders_id'] && $check_field('set','order_number')) || (!form['room_orders_id'] && $check_field('add','order_number'))"  :disabled="true" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','order_number')">
                  {{ form['order_number'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','room_name') || $check_field('add','room_name') || $check_field('set','room_name')" label="房间名称" name="room_name">
                            <uni-easyinput type="text" v-model="form['room_name']" v-if="user_group === '管理员' || (form['room_orders_id'] && $check_field('set','room_name')) || (!form['room_orders_id'] && $check_field('add','room_name'))" :disabled="disabledObj['room_name_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','room_name')">
                  {{ form['room_name'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','room_type') || $check_field('add','room_type') || $check_field('set','room_type')" label="房间房型" name="room_type">
                            <uni-easyinput type="text" v-model="form['room_type']" v-if="user_group === '管理员' || (form['room_orders_id'] && $check_field('set','room_type')) || (!form['room_orders_id'] && $check_field('add','room_type'))" :disabled="disabledObj['room_type_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','room_type')">
                  {{ form['room_type'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','room_price') || $check_field('add','room_price') || $check_field('set','room_price')" label="房间价格" name="room_price">
                            <uni-easyinput type="text" v-model="form['room_price']" v-if="user_group === '管理员' || (form['room_orders_id'] && $check_field('set','room_price')) || (!form['room_orders_id'] && $check_field('add','room_price'))" :disabled="disabledObj['room_price_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','room_price')">
                  {{ form['room_price'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','customer_users') || $check_field('add','customer_users') || $check_field('set','customer_users')" label="顾客用户" name="customer_users">
                    <uni-data-select
                  id="form_customer_users"
                  v-model="form['customer_users']"
                  :localdata="list_user_customer_users"
                  :clear="!disabledObj['customer_users_isDisabled']"
                  :disabled="disabledObj['customer_users_isDisabled']"
                  v-if="user_group === '管理员' || (form['room_orders_id'] && $check_field('set','customer_users')) || (!form['room_orders_id'] && $check_field('add','customer_users'))"
                ></uni-data-select>
                <uni-data-select
                  v-model="form['customer_users']"
                  :localdata="list_user_customer_users"
                  :clear="false"
                  :disabled="true"
                  v-else-if="$check_field('get','customer_users')" id="customer_users"
                ></uni-data-select>
                  </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','user_name') || $check_field('add','user_name') || $check_field('set','user_name')" label="用户姓名" name="user_name">
                            <uni-easyinput type="text" v-model="form['user_name']" v-if="user_group === '管理员' || (form['room_orders_id'] && $check_field('set','user_name')) || (!form['room_orders_id'] && $check_field('add','user_name'))" :disabled="disabledObj['user_name_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','user_name')">
                  {{ form['user_name'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','contact_number') || $check_field('add','contact_number') || $check_field('set','contact_number')" label="联系电话" name="contact_number">
                            <uni-easyinput type="text" v-model="form['contact_number']" v-if="user_group === '管理员' || (form['room_orders_id'] && $check_field('set','contact_number')) || (!form['room_orders_id'] && $check_field('add','contact_number'))" :disabled="disabledObj['contact_number_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','contact_number')">
                  {{ form['contact_number'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','check_in_time') || $check_field('add','check_in_time') || $check_field('set','check_in_time')" label="入住时间" name="check_in_time">
                    <uni-datetime-picker v-if="user_group === '管理员' || (form['room_orders_id'] && $check_field('set','check_in_time')) || (!form['room_orders_id'] && $check_field('add','check_in_time'))" @change="changeLog($event,'check_in_time')" v-model="form['check_in_time']" type="datetime" :disabled="disabledObj['check_in_time_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','check_in_time')">
                  {{ form['check_in_time'] }}
                </text>
                  </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','booking_days') || $check_field('add','booking_days') || $check_field('set','booking_days')" label="预订天数" name="booking_days">
                            <uni-easyinput type="text" v-model="form['booking_days']" v-if="user_group === '管理员' || (form['room_orders_id'] && $check_field('set','booking_days')) || (!form['room_orders_id'] && $check_field('add','booking_days'))" :disabled="disabledObj['booking_days_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','booking_days')">
                  {{ form['booking_days'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','total_order_price') || $check_field('add','total_order_price') || $check_field('set','total_order_price')" label="订单总价" name="total_order_price">
                            <uni-easyinput type="text" v-model="form['total_order_price']" v-if="user_group === '管理员' || (form['room_orders_id'] && $check_field('set','total_order_price')) || (!form['room_orders_id'] && $check_field('add','total_order_price'))"  @focus="set_total_order_price()" :disabled="disabledObj['total_order_price_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','total_order_price')">
                  {{ form['total_order_price'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','booking_remarks') || $check_field('add','booking_remarks') || $check_field('set','booking_remarks')" label="预订备注" name="booking_remarks">
                    <uni-easyinput type="textarea" v-model="form['booking_remarks']" v-if="user_group === '管理员' || (form['room_orders_id'] && $check_field('set','booking_remarks')) || (!form['room_orders_id'] && $check_field('add','booking_remarks'))" :disabled="disabledObj['booking_remarks_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','booking_remarks')">
                  {{ form['booking_remarks'] }}
                </text>
                  </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','reservation_status') || $check_field('add','reservation_status') || $check_field('set','reservation_status')" label="预订状态" name="reservation_status">
                    <uni-data-select
                  v-model="form.reservation_status"
                  :localdata="list_reservation_status"
                  :clear="!disabledObj['reservation_status_isDisabled']"
                  :disabled="disabledObj['reservation_status_isDisabled']"
                  v-if="user_group === '管理员' || (form['room_orders_id'] && $check_field('set','reservation_status')) || (!form['room_orders_id'] && $check_field('add','reservation_status'))"
                ></uni-data-select>
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','reservation_status')">
                  {{ form['reservation_status'] }}
                </text>
                  </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','reservation_response') || $check_field('add','reservation_response') || $check_field('set','reservation_response')" label="预订回复" name="reservation_response">
                    <uni-easyinput type="textarea" v-model="form['reservation_response']" v-if="user_group === '管理员' || (form['room_orders_id'] && $check_field('set','reservation_response')) || (!form['room_orders_id'] && $check_field('add','reservation_response'))" :disabled="disabledObj['reservation_response_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','reservation_response')">
                  {{ form['reservation_response'] }}
                </text>
                  </uni-forms-item>


            </uni-forms>
            <view class="form_button">
              <button size="mini" type="primary" @click="submit()" class="primary_btn">提交</button>
              <button size="mini" @click="cancel()">取消</button>
            </view>
          </view>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
import mixin from "@/libs/mixins/page.js";

export default {
  mixins: [mixin],
  data() {
    return {
      field: "room_orders_id",
      url_add: "~/api/room_orders/add?",
      url_set: "~/api/room_orders/set?",
      url_get_obj: "~/api/room_orders/get_obj?",
      url_upload: "~/api/room_orders/upload?",

      query: {
        "room_orders_id": 0,
      },

      form: {
            "order_number": this.$get_stamp(), // 订单编号
                    "room_name":  '', // 房间名称
                    "room_type":  '', // 房间房型
                    "room_price":  '', // 房间价格
                    "customer_users": 0, // 顾客用户
                    "user_name":  '', // 用户姓名
                    "contact_number":  '', // 联系电话
                    "check_in_time": this.$toTime(new Date().getTime(), "yyyy-MM-dd hh:mm:ss"),
                    "booking_days":  0 , // 预订天数
                    "total_order_price":  '', // 订单总价
                    "booking_remarks":  '', // 预订备注
                    "reservation_status":  '', // 预订状态
                    "reservation_response":  '', // 预订回复
                                "room_orders_id": 0, // ID
                
              },
          disabledObj:{
                        "order_number_isDisabled": false,
                                "room_name_isDisabled": false,
                                "room_type_isDisabled": false,
                                "room_price_isDisabled": false,
                                "customer_users_isDisabled": false,
                                "user_name_isDisabled": false,
                                "contact_number_isDisabled": false,
                                "check_in_time_isDisabled": false,
                                                    "total_order_price_isDisabled": false,
                                "booking_remarks_isDisabled": false,
                                "reservation_status_isDisabled": false,
                                "reservation_response_isDisabled": false,
                                  },
                                                                                                                // 用户列表
            list_user_customer_users: [],
                        // 用户组
            group_user_customer_users: "",
                                                                                                                                                                  // 预订状态选项列表
          list_reservation_status: [],
                                                          }
  },
  methods: {
    changeLog(v,value){
      this.form[value] = v
    },
    /**
     * 上传文件
     * @param {Object} param文件参数
     */
    change_file(key_name){
      var _self = this;
      // 选择图像方法
      uni.chooseFile({
        count: 1,
        sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
        sourceType: ['album'], //从相册选择
        success: function(res) {
          const tempFilePaths = res.tempFilePaths;
          const uploadTask = uni.uploadFile({
            url: _self.$fullUrl('/api/room_orders/upload?'),
            filePath: tempFilePaths[0],
            name: 'file',
            formData: {
              'i_want_to_customize': 'test'
            },
            header: {
              'x-auth-token': _self.$store.state.user.token
            },
            success: function(uploadFileRes) {
              var filename = JSON.parse(uploadFileRes.data).result.url
              _self.form[key_name] = filename
            }
          });

          uploadTask.onProgressUpdate(function(res) {
            _self.percent = res.progress;
            console.log('上传进度' + res.progress);
            console.log('已经上传的数据长度' + res.totalBytesSent);
            console.log('预期需要上传的数据总长度' + res.totalBytesExpectedToSend);
          });
        },
        error: function(e) {
          console.log(e);
        }
      });
    },
    /**
     * 上传图片
     * @param {Object} param文件参数
     */
    change_img(key_name){
      var _self = this;
      _self.upload_img_flag = false
      // 选择图像方法
      uni.chooseImage({
        count: 1,
        sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
        sourceType: ['album'], //从相册选择
        success: function(res) {
          const tempFilePaths = res.tempFilePaths;
          const uploadTask = uni.uploadFile({
            url: _self.$fullUrl('/api/room_orders/upload?'),
            filePath: tempFilePaths[0],
            name: 'file',
            formData: {
              'i_want_to_customize': 'test'
            },
            header: {
              'x-auth-token': _self.$store.state.user.token
            },
            success: function(uploadFileRes) {
              var filename = JSON.parse(uploadFileRes.data).result.url
              _self.form[key_name] = filename
            }
          });

          uploadTask.onProgressUpdate(function(res) {
            _self.percent = res.progress;
            console.log('上传进度' + res.progress);
            console.log('已经上传的数据长度' + res.totalBytesSent);
            console.log('预期需要上传的数据总长度' + res.totalBytesExpectedToSend);
          });
        },
        error: function(e) {
          console.log(e);
        }
      });
    },
    /**
     * 获取对象后获取缓存表单
     * @param {Object} json
     * @param {Object} func
     */
    get_obj_before(param){
      var form = uni.db.get("form");
      if (form) {
        delete(form.examine_state)
        delete(form.examine_reply)
        this.obj = uni.push(this.obj ,form);
        this.form = uni.push(this.form ,form);
      }
      var arr = []
      for (let key in form) {
        arr.push(key)
      }
      for (var i=0;i<arr.length;i++){
        this.disabledObj[arr[i] + '_isDisabled'] = true
      }
                                                                  if (this.form["check_in_time"] && JSON.stringify(this.form["check_in_time"]).indexOf("-")===-1) {
        this.form["check_in_time"] = this.$toTime(parseInt(this.form["check_in_time"]), "yyyy-MM-dd hh:mm:ss")
      }
                                                      uni.db.del("form");
      return param;
    },
        
            
            
            
                /**
     * 获取顾客用户用户列表
     */
    async get_list_user_customer_users() {
      // if(this.user_group !== "管理员" && this.form["customer_users"] === 0) {
      //     this.form["customer_users"] = this.user.user_id;
      // }
      var json = await this.$get("~/api/user/get_list?user_group=顾客用户");
      if(json.result && json.result.list){
        json.result.list.map((o) => this.list_user_customer_users.push({value:o.user_id,text:o.nickname + '-' + o.username}));
      }
      else if(json.error){
        console.error(json.error);
      }
    },
            /**
     * 获取顾客用户用户组
     */
    async get_group_user_customer_users() {
      this.form["customer_users"] = this.user.user_id;
      var json = await this.$get("~/api/user_group/get_obj?name=顾客用户");
      if(json.result && json.result.obj){
        this.group_user_customer_users = json.result.obj;
        this.get_user_session_customer_users(this.form['customer_users'])
      }
      else if(json.error){
        console.error(json.error);
      }
    },
    get_user_session_customer_users(id){
      var _this = this;
      var user_id = {"user_id":id}
      var url = "~/api/"+_this.group_user_customer_users.source_table+"/get_obj?"
      this.$get(url, user_id, function(res) {
        if (res.result && res.result.obj) {
          var arr = []
          for (let key in res.result.obj) {
            arr.push(key)
          }
          var arrForm = []
          for (let key in _this.form) {
            arrForm.push(key)
          }
          for (var i=0;i<arr.length;i++){
            if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
              for (var j = 0; j < arrForm.length; j++) {
                if (arr[i] === arrForm[j]) {
                  if (arr[i] !== "customer_users") {
                    _this.form[arrForm[j]] = res.result.obj[arr[i]]
                    _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                    break;
                  } else {
                    _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                  }
                }
              }
            }
          }
        }
      });
    },
            
            
            
            
            
            
                            set_total_order_price(){
      this.form.total_order_price = parseFloat(this.form.room_price) * parseFloat(this.form.booking_days)
      let r = /^\+?[1-9][0-9]*$/; // 正整数
      if(!r.test(this.form.total_order_price) ){
        this.form.total_order_price = this.form.total_order_price.toFixed(2);
      }
    },
                                
            /**
     * 获取预订状态列表
     */
    async get_list_reservation_status() {
          ['预订失败','预订成功'].map((o) => this.list_reservation_status.push({value:o,text:o}));
            },
        
            
    
    /**
     * 获取对象之后
     * @param {Object} json
     * @param {Object} func
     */
    get_obj_after(json, func){
                                                                  if (this.form["check_in_time"] && JSON.stringify(this.form["check_in_time"]).indexOf("-")===-1) {
        this.form["check_in_time"] = this.$toTime(parseInt(this.form["check_in_time"]), "yyyy-MM-dd hh:mm:ss")
      }
                                                    },

    is_view(){
      var bl = this.user_group == "管理员";

      if(!bl){
        bl = this.$check_action('/room_orders/table','add');
        console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
      }
      if(!bl){
        bl = this.$check_action('/room_orders/table','set');
        console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
      }
      if(!bl){
        bl = this.$check_action('/room_orders/view','add');
        console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
      }
      if(!bl){
        bl = this.$check_action('/room_orders/view','set');
        console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
      }
      if(!bl){
        bl = this.$check_action('/room_orders/view','get');
        console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
      }

      console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

      return bl;
    },

  },
  created() {
                                            this.get_list_user_customer_users();
            this.get_group_user_customer_users();
                                                                    this.get_list_reservation_status();
                  },
}
</script>

<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.form_button{
  padding-bottom: 15px;
  display: flex;
}
.form_button button{
  width: 40%;
}
.query_select{
  border-color: rgb(229, 229, 229);
  background-color: rgb(255, 255, 255);
  border-radius: 4px;
  box-sizing: border-box;
  flex: 1;
  width: 100%;
  line-height: 2;
  font-size: 14px;
  height: 2.4em;
  min-height: 2.4em;
  display: block;
  outline:none;
}

.query_option{
  width: 100%;
}

.btn_add_img{
  color: #D3D3D3;
  text-align: center;
  border: 1px solid #eee;
  height: 5rem;
  width: 5rem;
  position: relative;
}
.btn_add_img text{
  font-size: 35px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%,-50%);
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}

.form_button {
  padding-bottom: 15px;
  display: flex;
}
.form_button button {
  width: 40%;
}
.query_select {
  border-color: rgb(229, 229, 229);
  background-color: rgb(255, 255, 255);
  border-radius: 4px;
  box-sizing: border-box;
  flex: 1;
  width: 100%;
  line-height: 2;
  font-size: 14px;
  height: 2.4em;
  min-height: 2.4em;
  display: block;
  outline: none;
}

.query_option {
  width: 100%;
}

.btn_add_img {
  color: #d3d3d3;
  text-align: center;
  border: 1px solid #eee;
  height: 5rem;
  width: 5rem;
  position: relative;
}
.btn_add_img text {
  font-size: 35px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
/*新样式*/
.uni-forms{
padding-top:1rem;
}
.uni-forms-item {
	padding: 6px 10px;
    background: #f8f6fc;
}
.uni-forms .is-input-border{
	border: 0;
}
.container{
	    -webkit-box-shadow: 0px 0px 0px #888888;
	    box-shadow: 0px 0px 0px #888888;
}
.form_button .primary_btn{
		background-color: #22B8B8;
		color: #FFFFFF;
	}
</style>
