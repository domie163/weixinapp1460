<template>
  <view>
    <view class="container diy_view">
      <view>
        <view>
          <view class="">
            <uni-forms :modelValue="form"  v-if="is_view()">

              <uni-forms-item v-if="$check_field('get','order_number') || $check_field('add','order_number') || $check_field('set','order_number')" label="订单编号" name="order_number">
                            <uni-easyinput type="text" v-model="form['order_number']" v-if="user_group === '管理员' || (form['food_orders_id'] && $check_field('set','order_number')) || (!form['food_orders_id'] && $check_field('add','order_number'))" :disabled="disabledObj['order_number_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','order_number')">
                  {{ form['order_number'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','customer_users') || $check_field('add','customer_users') || $check_field('set','customer_users')" label="顾客用户" name="customer_users">
                    <uni-data-select
                  id="form_customer_users"
                  v-model="form['customer_users']"
                  :localdata="list_user_customer_users"
                  :clear="!disabledObj['customer_users_isDisabled']"
                  :disabled="disabledObj['customer_users_isDisabled']"
                  v-if="user_group === '管理员' || (form['food_orders_id'] && $check_field('set','customer_users')) || (!form['food_orders_id'] && $check_field('add','customer_users'))"
                ></uni-data-select>
                <uni-data-select
                  v-model="form['customer_users']"
                  :localdata="list_user_customer_users"
                  :clear="false"
                  :disabled="true"
                  v-else-if="$check_field('get','customer_users')" id="customer_users"
                ></uni-data-select>
                  </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','food_name') || $check_field('add','food_name') || $check_field('set','food_name')" label="食物名称" name="food_name">
                            <uni-easyinput type="text" v-model="form['food_name']" v-if="user_group === '管理员' || (form['food_orders_id'] && $check_field('set','food_name')) || (!form['food_orders_id'] && $check_field('add','food_name'))" :disabled="disabledObj['food_name_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','food_name')">
                  {{ form['food_name'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','purchase_quantity') || $check_field('add','purchase_quantity') || $check_field('set','purchase_quantity')" label="购买数量" name="purchase_quantity">
                            <uni-easyinput type="text" v-model="form['purchase_quantity']" v-if="user_group === '管理员' || (form['food_orders_id'] && $check_field('set','purchase_quantity')) || (!form['food_orders_id'] && $check_field('add','purchase_quantity'))" :disabled="disabledObj['purchase_quantity_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','purchase_quantity')">
                  {{ form['purchase_quantity'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','total_order_price') || $check_field('add','total_order_price') || $check_field('set','total_order_price')" label="订单总价" name="total_order_price">
                            <uni-easyinput type="text" v-model="form['total_order_price']" v-if="user_group === '管理员' || (form['food_orders_id'] && $check_field('set','total_order_price')) || (!form['food_orders_id'] && $check_field('add','total_order_price'))" :disabled="disabledObj['total_order_price_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','total_order_price')">
                  {{ form['total_order_price'] }}
                </text>
                          </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','production_status') || $check_field('add','production_status') || $check_field('set','production_status')" label="制作状态" name="production_status">
                    <uni-data-select
                  v-model="form.production_status"
                  :localdata="list_production_status"
                  :clear="!disabledObj['production_status_isDisabled']"
                  :disabled="disabledObj['production_status_isDisabled']"
                  v-if="user_group === '管理员' || (form['food_orders_id'] && $check_field('set','production_status')) || (!form['food_orders_id'] && $check_field('add','production_status'))"
                ></uni-data-select>
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','production_status')">
                  {{ form['production_status'] }}
                </text>
                  </uni-forms-item>
              <uni-forms-item v-if="$check_field('get','order_remarks') || $check_field('add','order_remarks') || $check_field('set','order_remarks')" label="订单备注" name="order_remarks">
                    <uni-easyinput type="textarea" v-model="form['order_remarks']" v-if="user_group === '管理员' || (form['food_orders_id'] && $check_field('set','order_remarks')) || (!form['food_orders_id'] && $check_field('add','order_remarks'))" :disabled="disabledObj['order_remarks_isDisabled']" />
                <!-- 仅查看 -->
                <text v-else-if="$check_field('get','order_remarks')">
                  {{ form['order_remarks'] }}
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
      field: "food_orders_id",
      url_add: "~/api/food_orders/add?",
      url_set: "~/api/food_orders/set?",
      url_get_obj: "~/api/food_orders/get_obj?",
      url_upload: "~/api/food_orders/upload?",

      query: {
        "food_orders_id": 0,
      },

      form: {
            "order_number":  '', // 订单编号
                    "customer_users": 0, // 顾客用户
                    "food_name":  '', // 食物名称
                    "purchase_quantity":  0 , // 购买数量
                    "total_order_price":  '', // 订单总价
                    "production_status":  '', // 制作状态
                    "order_remarks":  '', // 订单备注
                                "food_orders_id": 0, // ID
                
              },
          disabledObj:{
                        "order_number_isDisabled": false,
                                "customer_users_isDisabled": false,
                                "food_name_isDisabled": false,
                                                    "total_order_price_isDisabled": false,
                                "production_status_isDisabled": false,
                                "order_remarks_isDisabled": false,
                                  },
                                                    // 用户列表
            list_user_customer_users: [],
                                                                                                      // 制作状态选项列表
          list_production_status: [],
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
            url: _self.$fullUrl('/api/food_orders/upload?'),
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
            url: _self.$fullUrl('/api/food_orders/upload?'),
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
     * 获取制作状态列表
     */
    async get_list_production_status() {
          ['制作中','送餐中','已完成'].map((o) => this.list_production_status.push({value:o,text:o}));
            },
        
            
    
    /**
     * 获取对象之后
     * @param {Object} json
     * @param {Object} func
     */
    get_obj_after(json, func){
                                                            },

    is_view(){
      var bl = this.user_group == "管理员";

      if(!bl){
        bl = this.$check_action('/food_orders/table','add');
        console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
      }
      if(!bl){
        bl = this.$check_action('/food_orders/table','set');
        console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
      }
      if(!bl){
        bl = this.$check_action('/food_orders/view','add');
        console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
      }
      if(!bl){
        bl = this.$check_action('/food_orders/view','set');
        console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
      }
      if(!bl){
        bl = this.$check_action('/food_orders/view','get');
        console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
      }

      console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

      return bl;
    },

  },
  created() {
                    this.get_list_user_customer_users();
                                            this.get_list_production_status();
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
