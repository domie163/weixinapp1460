<template>
  <view class="page_room_orders diy_table diy_table_tml" id="room_orders_table">
    <!-- 筛选模块(开始) -->
    <view>
      <view class="container">
        <view>
          <view>
            <view class="">
              <!-- 搜索栏 -->
              <uni-forms :modelValue="query">
                    <uni-forms-item label="房间名称" name="room_name">
                      <uni-easyinput type="text" v-model="query.room_name" placeholder="房间名称" />
                    </uni-forms-item>
                                      <uni-forms-item label="预订状态" name="reservation_status">
                 <uni-data-select placeholder="请选择"
                        v-model="query['reservation_status']"
                        :localdata="list_reservation_status"
       ></uni-data-select>
                          </uni-forms-item>
                  </uni-forms>
              <!-- /搜索栏 -->
              <view class="table_padding_bottom">
                <button class="btn_right" size="mini" @click="reset()" style="margin-left: 10px;margin-right: 15px">重置</button>
                <button class="btn_right" size="mini" type="primary" @click="search()">查询</button>
                <div class="clear"></div>
              </view>
            </view>
          </view>
        </view>
      </view>
    </view>

    <view>
      <view class="container">
        <view>
          <view>
            <view class="table_padding_top">
              <navigator class="nav_left" url="/pages/room_orders/view?" v-show="user_group == '管理员' || $check_action('/room_orders/table','add') || $check_action('/room_orders/view','add')">添加</navigator>

              <div class="clear"></div>
            </view>
		<!-- 列表 -->
		<view class="warp">
			<view class="container">
				<view v-for="(o,i) in list" class="row">
										<view v-if="0 && $check_field('get','order_number')" class="col">
						<view class="view">
                          <view class="diy_title" v-if="true">
                            <span>订单编号</span>
                          </view>
											<view class="diy_field diy_text text">
								<text>
									{{ o["order_number"] }}
								</text>
							</view>
										</view>
					</view>
													<view v-if="1 && $check_field('get','room_name')" class="col">
						<view class="view">
                          <view class="diy_title" v-if="true">
                            <span>房间名称</span>
                          </view>
											<view class="diy_field diy_text text">
								<text>
									{{ o["room_name"] }}
								</text>
							</view>
										</view>
					</view>
													<view v-if="1 && $check_field('get','room_type')" class="col">
						<view class="view">
                          <view class="diy_title" v-if="true">
                            <span>房间房型</span>
                          </view>
											<view class="diy_field diy_text text">
								<text>
									{{ o["room_type"] }}
								</text>
							</view>
										</view>
					</view>
													<view v-if="0 && $check_field('get','room_price')" class="col">
						<view class="view">
                          <view class="diy_title" v-if="true">
                            <span>房间价格</span>
                          </view>
											<view class="diy_field diy_text text">
								<text>
									{{ o["room_price"] }}
								</text>
							</view>
										</view>
					</view>
													<view v-if="0 && $check_field('get','customer_users')" class="col">
						<view class="view">
                          <view class="diy_title" v-if="true">
                            <span>顾客用户</span>
                          </view>
											<view class="diy_field diy_uid text">
								<text>
									{{ get_user_customer_users(o['customer_users']) }}
								</text>
							</view>
										</view>
					</view>
													<view v-if="0 && $check_field('get','user_name')" class="col">
						<view class="view">
                          <view class="diy_title" v-if="true">
                            <span>用户姓名</span>
                          </view>
											<view class="diy_field diy_text text">
								<text>
									{{ o["user_name"] }}
								</text>
							</view>
										</view>
					</view>
													<view v-if="0 && $check_field('get','contact_number')" class="col">
						<view class="view">
                          <view class="diy_title" v-if="true">
                            <span>联系电话</span>
                          </view>
											<view class="diy_field diy_text text">
								<text>
									{{ o["contact_number"] }}
								</text>
							</view>
										</view>
					</view>
													<view v-if="0 && $check_field('get','check_in_time')" class="col">
						<view class="view">
                          <view class="diy_title" v-if="true">
                            <span>入住时间</span>
                          </view>
											<view class="diy_field diy_datetime text">
								<text>
									{{ $toTime(o["check_in_time"],"yyyy-MM-dd hh:mm:ss") }}
								</text>
							</view>
										</view>
					</view>
													<view v-if="0 && $check_field('get','booking_days')" class="col">
						<view class="view">
                          <view class="diy_title" v-if="true">
                            <span>预订天数</span>
                          </view>
											<div class="diy_field diy_number text">
								<text>
									{{ o['booking_days'] }}
								</text>
							</div>
										</view>
					</view>
													<view v-if="0 && $check_field('get','total_order_price')" class="col">
						<view class="view">
                          <view class="diy_title" v-if="true">
                            <span>订单总价</span>
                          </view>
											<view class="diy_field diy_text text">
								<text>
									{{ o["total_order_price"] }}
								</text>
							</view>
										</view>
					</view>
													<view v-if="0 && $check_field('get','booking_remarks')" class="col">
						<view class="view">
                          <view class="diy_title" v-if="true">
                            <span>预订备注</span>
                          </view>
											<view class="diy_field diy_text text">
								<text>
									{{ o["booking_remarks"] }}
								</text>
							</view>
										</view>
					</view>
													<view v-if="1 && $check_field('get','reservation_status')" class="col">
						<view class="view">
                          <view class="diy_title" v-if="true">
                            <span>预订状态</span>
                          </view>
											<view class="diy_field diy_text text">
								<text>
									{{ o["reservation_status"] }}
								</text>
							</view>
										</view>
					</view>
													<view v-if="0 && $check_field('get','reservation_response')" class="col">
						<view class="view">
                          <view class="diy_title" v-if="true">
                            <span>预订回复</span>
                          </view>
											<view class="diy_field diy_text text">
								<text>
									{{ o["reservation_response"] }}
								</text>
							</view>
										</view>
					</view>
					                  <view class="bottom-view">
                                                              </view>
                  <view class="col">
                    <view class="view">
                      <view class="create_time_block diy_filed diy_time">
                        {{ $toTime(o["create_time"] ,"yyyy-MM-dd hh:mm:ss") }}
                        <button class="delete_a_btn" @click="delInfo(i)" v-show="user_group == '管理员' || $check_action('/room_orders/table','del') || $check_action('/room_orders/view','del')">删除</button>
                      </view>
                    </view>
                  </view>
					<view class="uni-padding-wrap uni-common-mt">
					<navigator  :url="'/pages/room_orders/view?' + field + '=' + o[field]"
                               v-show="user_group == '管理员' || $check_action('/room_orders/table','set') || $check_action('/room_orders/view','set') || $check_action('/room_orders/view','get')" class="btn btn-primary">详情</navigator>
	                    <a @click="goto_pay(o)" size="mini"  v-if="o.pay_state==='未支付' && $check_pay('/room_orders/table')" class='btn btn-primary'>支付</a>
	        					</view>
				</view>
			</view>
		</view>
		<!-- /列表 -->
          </view>
        </view>
      </view>
    </view>
    <!-- 分页器 -->
    <uni-pagination
        class="pager"
        show-icon="true"
        :total="count"
        :pageSize="query.size"
        :current="query.page"
        @change="page_change"
    ></uni-pagination>
    <!-- /分页器 -->


    <view class='pay_model' v-if="!!model">
      <view class='mask' @click='closemodel'></view>
      <view class='pay_model_inn'>
        <view class='pay_tab'>
          <view @click='choosepayway(idx)' v-for="(obj,idx) in payway" :key="idx"  :class="['pay_tab_one',paywayact==idx?'pay_tab_one_act':'']">{{obj.name}}</view>
        </view>
        <view v-if="payway[paywayact].id!==3" class='inclued_pay_image'>
          <image mode='widthFix' class='pay_image' :src='$fullUrl(payway[paywayact].url)'></image></view>
        <view v-else class='inclued_pay_image'>
          <div>
            <div style="width: 80%;margin: 0 auto;margin-top: 30px;">
              <div style="width: 80%;margin: 0 auto;">
                <div style="line-height: 40px">请输入网银账号：</div>
              </div>
              <div style="width: 80%;margin: 0 auto;">
                <input v-model="pay_obj.account" placeholder="请输入网银账号" />
              </div>
            </div>
            <div style="width: 80%;margin: 0 auto;margin-top: 20px;">
              <div style="width: 80%;margin: 0 auto;">
                <div style="line-height: 40px">请输入支付密码，6位数字：</div>
              </div>
              <div style="width: 80%;margin: 0 auto;">
                <input placeholder="请输入密码" type="password" v-model="pay_obj.password" maxlength="6" />
              </div>
            </div>
          </div>
        </view>
        <view>
          <button type='primary' @click="submit_pay()">确认支付</button></view>
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
				// 获取数据地址
				url_get_list: "~/api/room_orders/get_list?like=0",
				url_del: "~/api/room_orders/del?",

				// 字段ID
				field: "room_orders_id",

				// 查询
				query: {
					"size": 7,
					"page": 1,
                            "room_name": "",
                                                  "reservation_status": "",
        					"login_time": "",
					"create_time": "",
				},

				// 数据
				list: [],
                                                                // 用户列表
                list_user_customer_users: [],
                                                                        // 预订状态列表
                list_reservation_status: [{value:"",text:"全部"}],
                                model:false,
                paywayact:0,
                // 支付方式
                payway:[
                  {name:'微信',url:'../../static/img/wx.png',id:1},
                  {name:'支付宝',url:'../../static/img/aplipay.png',id:2},
                  {name:'其他',url:'https://dzgj.yuyuebus.com/static/front/dist/static/img/icon-question.png',id:3},
                ],
                        pay_obj:{
                  payActiveName:"微信",
                          account:"",
                          password:"",
                          id:""
                },
			}
		},
		methods: {
          search() {
            this.query.page = 1;
            this.get_list();
          },
          /**
           * 重置
           */
          reset: function reset() {
            uni.clear(this.query);
            uni.push(this.query, this.config);
            this.get_list();
          },
            get_list_before(param){
            var user_group = this.user.user_group;
            if(user_group != "管理员"){
                  let sqlwhere = "(";
                                                                                              if(user_group=="顾客用户"){
                  sqlwhere+= "customer_users = " + this.user.user_id + " or ";
              }
                                                                                                                                                              if (sqlwhere.length>1){
                sqlwhere = sqlwhere.substr(0,sqlwhere.length-4);
                sqlwhere += ")";
                param["sqlwhere"] = sqlwhere;
              }
        }
    return param;
  },
          delInfo(v) {
            let _this = this;
            uni.showModal({
              title: '删除',
              content: '此操作将永久删除该文件, 是否继续?',
              success: function (res) {
                if (res.confirm) {
                  let list = [v]
                  _this.delAll(list);
                } else if (res.cancel) {
                  console.log('用户点击取消');
                }
              }
            });
          },
                                                                      /**
           * 获取顾客用户用户列表
           */
        async get_list_user_customer_users() {
            var json = await this.$get("~/api/user/get_list?user_group=顾客用户");
            if(json.result && json.result.list){
              this.list_user_customer_users = json.result.list;
            }
            else if(json.error){
              console.error(json.error);
            }
          },

          get_user_customer_users(id){
            let obj = this.list_user_customer_users;
            let ret = "";
            for(let i=0;i<obj.length;i++){
              if(obj[i].user_id==id){
                ret = obj[i].nickname+"-"+obj[i].username;
              }
            }
            return ret;
          },
                                                                                          /**
           * 获取预订状态列表
           */
          async get_list_reservation_status() {
                      ['预订失败','预订成功'].map((o) => this.list_reservation_status.push({value:o,text:o}));
                            },
                                  /**
           * 打开支付
           */
          goto_pay(v) {
            //打开选择支付方式
            this.pay_obj.id = v.room_orders_id
            this.model=true;
          },
          choosepayway(idx){
            switch(idx){
              case 0:
                this.pay_obj.payActiveName="微信"
                break
              case 1:
                this.pay_obj.payActiveName="支付宝"
                break
              case 2:
                this.pay_obj.payActiveName="网银"
                break
            }
            this.paywayact=idx;
          },
          closemodel(){
            //关闭选择支付方式
            this.model=false;
          },
          /**
           * 支付
           */
          submit_pay(){
            let _this = this;
            let url = this.$toUrl(this.query, "~/api/room_orders/set?room_orders_id="+this.pay_obj.id);
            let param = {"pay_state":"已支付","pay_type":this.pay_obj.payActiveName}
            this.$post(url,param,(res)=>{
              if(res.result){
                _this.$toast("支付成功");
                setTimeout(()=>{
                  _this.$nav("/pages/room_orders/table");
                }, 700);
              }else if(res.error){
                _this.$toast(res.error.message);
                console.log(res.error);
              }
            });
          },

		},
		created() {
                                                          this.get_list_user_customer_users();
                                                                  // 初始化预订状态列表
          this.get_list_reservation_status();
                		}
	}
</script>

<style scoped type="text/css">
  .pay_model{
    width:100%;
    height: 100%;
    background:rgba(0,0,0,.3);
    position: fixed;
    top:0;
    left:0;
    z-index:90;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
  }
  .pay_model .mask{
    width:100%;
    height: 100%;
    position: absolute;
    top:0;
    left:0;
    z-index:1;
  }
  .pay_model .pay_model_inn{
    background:#fff;
    box-shadow: 0 0.25rem 0.5rem 0 #ddd;
    width:80%;
    padding:20rpx;
    position: relative;
    z-index:10;
    border-radius: 10rpx;
  }
  .pay_model .pay_model_inn .pay_tab{
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
  }
  .pay_model .pay_model_inn .pay_tab_one{
    width:29.3%;
    padding:10px 2%;
    text-align: center;
    border-bottom:2px solid #fff;
  }
  .pay_model .pay_model_inn .pay_tab_one_act{
    border-bottom:2px solid #0000FF;
    color:#0000FF;
  }

  .pay_model .inclued_pay_image{
    width:100%;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    margin:20px 0;
  }
  .pay_model .inclued_pay_image .pay_image{
    width:80%;
    border-radius: 10rpx;

  }
  .clear {
    margin: 0;
    padding: 0;
    left: 0;
    right: 0;
    clear: both;
  }

  .btn_right{
    float: right;
  }

  .btn_left{
    float: left;
  }

  .nav_left{
    float: left;
    display: inline-block;
    line-height: 2.3;
    font-size: 13px;
    padding: 0 1.34em;
    color: #fff;
    background-color: #007aff;
    box-sizing: border-box;
    border-radius: 5px;
  }

  .table_padding_top{
    padding-top: 10px;
  }

  .table_margin_top{
    margin-top: 10px;
  }

  .table_padding_bottom{
    padding-bottom: 10px;
  }

  .nav{
    display: inline-block;
    line-height: 2.3;
    font-size: 13px;
    padding: 0 1.34em;
    color: #fff;
    background-color: #007aff;
    box-sizing: border-box;
    border-radius: 5px;
  }
  .uni-forms--top {
    padding: 0px 15px;
  }

  .uni-forms-item[data-v-39373d84] {
    margin-bottom: 10px;
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
</style>
<style scoped>
.container {
  box-shadow: none;
  -webkit-box-shadow: none;
}
.pager {
  margin-top: 1rem;
}
.uni-collapse {
  background-color: inherit;
}

.pager {
  margin-top: 1rem;
}

.tab_view {
  /* background-color: #ffffff; */
  margin: 0 auto;
}

.list_orderby {
  display: flex;
  justify-content: flex-start;
  /* background-color: #ffffff; */
  /* border-top: 5px double #ccc;
		border-bottom: 5px double #ccc; */
  margin-right: -1px;
}

.list_orderby .bar_orderby {
  border-left: 1px solid #ccc;
}
.change_table {
  height: 50px;
  margin: 0 var(--margin_base);
}
.btn_change_table {
  margin-left: auto;
  font-weight: bold;
  width: 100px;
  height: 37px;
  text-align: center;
  font-size: 0.875rem;
  border: 1px solid #cccccc;
  border-radius: 1rem;
  margin: 0.25rem;
  float: right;
}
.end-title {
  display: flex;
}
.end-title view {
  flex-grow: 1;
  text-align: center;
}
.end-cont {
  display: none;
  /* background: #c8c7cc; */
}
.btna {
  color: #ffffff;
  background: #00a0ff;
}
.dis {
  display: block;
}
.box_style {
  width: 44%;
  margin-bottom: 0.75rem;
  padding: 0.375rem;
  display: inline-block !important;
  border: 0.075rem solid #ccc;
  border-radius: 0.375rem;
  overflow: hidden;
}
.box_style:nth-child(even) {
  margin-left: 0.6rem;
}
.box_style:nth-child(even) {
  margin-left: 0.6rem;
}
.bottom-view,
.create_time_block {
  font-size: 12px;
  color: #666666;
}
.bottom-view view {
  display: inline-block;
}
.bottom-view span {
  margin-left: 5px;
  margin-right: 10px;
}
</style>
<style>
/* 新加样式 */
.uni-searchbar {
  background-color: #22b8b8;
}
.search_div .uni-searchbar .uni-searchbar__box {
  border-radius: 8px !important;
  height: auto;
}

.tab_view {
  margin: 8px auto;
  font-size: 14px;
}
.list_orderby {
  margin: 1rem;
}
.list_orderby .bar_orderby {
  margin-right: 0.5rem;
  border-left: 1px solid #ccc;
  background-color: #22b8b8;
  border-radius: 10px;
  padding: 5px;
}
.sort_view .bar_orderby .text {
  color: #fff;
}
/* 列表样式 */
.dis {
  width: 50%;
  display: inline-block;
}
.end-cont .warp .container {
  font-family: Arial, Helvetica, sans-serif;
  border: 2px solid #22b8b8;
  padding: 5px;
  /* margin-top: 10px; */
  border-radius: 8px;
  margin: 10px 5px 0 5px;
  font-size: 14px;
}
.col {
  padding: 5px;
}
.end-cont .warp .container .diy_img img {
  width: 100%;
  height: 100px;
  padding: 5px 0;
}
.diy_dj {
  display: inline-block;
  width: 50%;
}
.uni-common-mt {
  width: 100%;
}
.uni-common-mt .btn {
  font-size: 14px;
  color: #fff;
  background-color: #22b8b8;
  line-height: 2;
  margin-bottom: 8px;
  display: inline-block;
	  width: 100%;
	  text-align: center;
}
.diy_text{
	padding-left: 10px;
	 color:#22b8b8;
	 text-decoration: dashed;
}
.uni-common-mt .btn{
	display: inline-block;
	  width: 100%;
	  text-align: center;
}
.container {
  padding: 8px 0s;
}
.delete_btn{
	display: inline-block;
	padding-left: 8px;
	color: #22B8B8;
	text-decoration: underline;
}
.warp .container .diy_title {
  display: inline-block;
}
.warp .container .text {
  display: inline-block;
  color: var(--color_primary);
  margin-left: 10px;
}
</style>
