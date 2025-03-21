<template>
  <view class="page_order" id="order_details">
    <!-- 订单细节模块(开始) -->

    <view class="">
      <template v-if="$check_action('/order/details', 'get')">
        <view class="state">
          {{ state }}
        </view>
        <view class="contact_info">
          <view class="line_1">
            <uni-icons
              class="icon_address"
              size="16"
              color="var(--color_grey)"
              type="location-filled"
            >
            </uni-icons>
            <text class="name">{{ contact_name }}</text>
            <text class="phone"> {{ contact_phone }}</text>
          </view>
          <text class="line_2">{{ contact_address }}</text>
        </view>

        <view class="goods_list" v-for="(obj, idx) in list" :key="idx">
          <view class="item_goods flex_sbc">
            <view class="img_block">
              <image
                style="width: 4.5rem; height: 4.5rem"
                :src="$fullImgUrl(obj.img) || '@/static/img/default.png'"
                mode="scaleToFill"
              ></image>
            </view>
            <view class="middle_info">
              <view class="title ellipsis_2">
                {{ obj.title }}
              </view>
              <view class="priceAndNum">
                <text class="price"> ￥{{ obj.price }} </text>
                <text class="num"> ×{{ obj.num }} </text>
              </view>
            </view>
          </view>

          <view class="num_info"> </view>
        </view>

        <view class="sum_price flex_sbc">
          <text>需付款</text>
          <text style="color: var(--color_red); font-weight: 600"
            >￥{{ sum_price }}</text
          >
        </view>

        <view class="btn_block">
          <view v-if="$check_action('/order/details', 'set')">
            <view
              class="btn_refund"
              v-if="state === '待付款'"
              @click="goto_pay()"
            >
              去支付
            </view>
            <!--<view v-if="state === '已签收' || state === '待发货'" class="btn_refund"
											@click="refund()">
											申请退款
										</view>
										<view v-if="state === '待退款'" class="btn_refund" @click="cancel_refund()">
											取消退款申请
										</view>-->
          </view>
        </view>
        <view class="bottom_order">
          <view class="order_num flex_sbc">
            <text>订单编号：</text><text>{{ order_number }}</text>
          </view>
          <view class="time flex_sbc">
            <text>下单时间：</text><text>{{ create_time }}</text>
          </view>
        </view>
      </template>
    </view>

    <!-- 订单细节模块(结束) -->

    <view class="pay_model" v-if="!!model">
      <view class="mask" @click="closemodel"></view>
      <view class="pay_model_inn">
        <view class="pay_tab">
          <view
            @click="choosepayway(idx)"
            v-for="(obj, idx) in payway"
            :key="idx"
            :class="['pay_tab_one', paywayact == idx ? 'pay_tab_one_act' : '']"
            >{{ obj.name }}</view
          >
        </view>
        <view v-if="payway[paywayact].id !== 3" class="inclued_pay_image">
          <image
            mode="widthFix"
            class="pay_image"
            :src="$fullImgUrl(payway[paywayact].url)"
          ></image
        ></view>
        <view v-else class="inclued_pay_image">
          <div>
            <div style="width: 80%; margin: 0 auto; margin-top: 30px">
              <div style="width: 80%; margin: 0 auto">
                <div style="line-height: 40px">请输入网银账号：</div>
              </div>
              <div style="width: 80%; margin: 0 auto">
                <uni-easyinput
                  v-model="pay_obj.account"
                  placeholder="请输入网银账号"
                />
              </div>
            </div>
            <div style="width: 80%; margin: 0 auto; margin-top: 20px">
              <div style="width: 80%; margin: 0 auto">
                <div style="line-height: 40px">请输入支付密码，6位数字：</div>
              </div>
              <div style="width: 80%; margin: 0 auto">
                <uni-easyinput
                  placeholder="请输入密码"
                  type="password"
                  v-model="pay_obj.password"
                  maxlength="6"
                />
              </div>
            </div>
          </div>
        </view>
        <view>
          <button type="primary" @click="submit_pay()">确认支付</button></view
        >
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
      // 支付方式
      payway: [
        { name: "微信", url: "../../static/img/wx.png", id: 1 },
        { name: "支付宝", url: "../../static/img/aplipay.png", id: 2 },
        {
          name: "其他",
          url: "https://dzgj.yuyuebus.com/static/front/dist/static/img/icon-question.png",
          id: 3,
        },
      ],
      // 登录权限
      oauth: {
        signIn: true,
        user_group: [],
      },
      url_get_list: "~/api/order/get_list",
      field: "order_number",
      query: {
        order_number: "",
      },
      contact_name: "",
      contact_phone: "",
      contact_address: "",
      order_number: "",
      create_time: "",
      state: "",
      paywayact: 0,
      model: false,
      pay_obj: {
        payActiveName: "微信",
        account: "",
        password: "",
        id: "",
      },
    };
  },
  methods: {
    choosepayway(idx) {
      this.paywayact = idx;
    },
    get_list_after(json) {
      var {
        contact_name,
        contact_phone,
        contact_address,
        order_number,
        create_time,
        state,
      } = json.result.list[0];
      this.contact_name = contact_name;
      this.contact_phone = contact_phone;
      this.contact_address = contact_address;
      this.order_number = order_number;
      this.create_time = create_time;
      this.state = state;
      return json;
    },
    /**
     * 退款
     */
    // refund() {
    // 	var _this = this;
    // 	this.$post(
    // 		"~/api/order/set?order_number=" + this.order_number, {
    // 			state: "待退款",
    // 		},
    // 		(res) => {
    // 			if(res.result){
    // 				_this.$toast("已申请退款");
    // 				setTimeout(()=>{
    // 					_this.$nav("/");
    // 				}, 700);
    // 			}else if(res.error){
    // 				_this.$toast(res.error.message);
    // 				console.log(res.error);
    // 			}
    // 		}
    // 	);
    // },
    /**
     * 取消退款
     */
    // cancel_refund() {
    // 	var _this = this;
    // 	this.$post(
    // 		"~/api/order/set?order_number=" + this.order_number, {
    // 			state: "待发货",
    // 		},
    // 		(res) => {
    // 			if(res.result){
    // 				_this.$toast("已撤销申请");
    // 				setTimeout(()=>{
    // 					_this.$nav("/");
    // 				}, 700);
    // 			}else if(res.error){
    // 				_this.$toast(res.error.message);
    // 				console.log(res.error);
    // 			}
    // 		}
    // 	);
    // },
    closemodel() {
      //关闭选择支付方式
      this.model = false;
    },
    /**
     * 打开支付
     */
    goto_pay() {
      //打开选择支付方式
      this.model = true;
    },
    async update_goods_inventory(o) {
      await this.$get(
        "~/api/goods/get_obj?",
        { goods_id: o.goods_id },
        (json) => {
          if (json.result && json.result.obj) {
            let inventory = { inventory: json.result.obj.inventory - o.num };
            this.$post(
              "~/api/goods/set?goods_id=" + o.goods_id,
              inventory,
              (res) => {
                if (
                  res.result &&
                  json.result.obj.source_table &&
                  json.result.obj.source_field &&
                  json.result.obj.source_id
                ) {
                  let inventory_sub = { cart_inventory: inventory.inventory };
                  this.$post(
                    "~/api/" +
                      json.result.obj.source_table +
                      "/set?" +
                      json.result.obj.source_field +
                      "=" +
                      json.result.obj.source_id,
                    inventory_sub,
                    (res_sub) => {
                      console.log(res_sub);
                    }
                  );
                }
              }
            );
          }
        }
      );
    },
    /**
     * 支付
     */
    submit_pay() {
      var _this = this;
      this.$post(
        "~/api/order/set?order_number=" + this.order_number,
        {
          state: "已付款",
        },
        (res) => {
          if (res.result) {
            //修改数量
            for (let i = 0; i < _this.list.length; i++) {
              _this.update_goods_inventory(_this.list[i]);
            }
            _this.$toast("支付成功");
            setTimeout(() => {
              _this.$nav("/pages/index/index");
            }, 700);
          } else if (res.error) {
            _this.$toast(res.error.message);
            console.log(res.error);
          }
        }
      );
    },
  },
  computed: {
    sum_price() {
      var sum_price = 0;
      this.list.map((o) => (sum_price += o.price * o.num));
      return sum_price;
    },
  },
};
</script>

<style>
.pay_model {
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.3);
  position: fixed;
  top: 0;
  left: 0;
  z-index: 90;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.pay_model .mask {
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  z-index: 1;
}
.pay_model .pay_model_inn {
  background: #fff;
  box-shadow: 0 0.25rem 0.5rem 0 #ddd;
  width: 80%;
  padding: 20rpx;
  position: relative;
  z-index: 10;
  border-radius: 10rpx;
}
.pay_model .pay_model_inn .pay_tab {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
}
.pay_model .pay_model_inn .pay_tab_one {
  width: 29.3%;
  padding: 10px 2%;
  text-align: center;
  border-bottom: 2px solid #fff;
}
.pay_model .pay_model_inn .pay_tab_one_act {
  border-bottom: 2px solid #0000ff;
  color: #0000ff;
}

.pay_model .inclued_pay_image {
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  margin: 20px 0;
}
.pay_model .inclued_pay_image .pay_image {
  width: 80%;
  border-radius: 10rpx;
}
.ellipsis_2 {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}

.page_order {
  min-height: 100vh;
  background-color: #f8f8f8;
}

.flex_sbc {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.contact_info {
  background-color: #fff;
  padding: 0.3rem 1rem;
  margin-bottom: 0.5rem;
}

.contact_info .line_1 {
  margin-bottom: 0.2rem;
}

.contact_info .line_2 {
  font-size: 0.9rem;
}

.contact_info .name {
  padding-right: 1rem;
  font-weight: 600;
}

.contact_info .phone {
  color: var(--color_grey);
}

.state {
  padding: 0.3rem 1rem;
  background-color: #fff;
  color: var(--color_primary);
  border-bottom: 1px solid #f8f8f8;
  font-size: 1.1rem;
}

.goods_list {
  padding: 0.2rem 0.8rem;
  background-color: #fff;
}

.goods_list .item_goods {
  align-items: stretch;
  border-bottom: 1px solid #f4f4f4;
}

.goods_list .img_block {
  padding: 0.2rem;
}

.goods_list .middle_info {
  width: calc(100% - 5.5rem);
}

.goods_list .title {
  font-size: 0.9rem;
  min-height: 1.4rem;
}

.goods_list .num_info {
  display: flex;
  justify-content: space-between;
}

.goods_list .price,
.goods_list .num {
  color: var(--color_grey);
  font-size: 0.5rem;
}

.sum_price {
  height: 2.5rem;
  background-color: #ffffff;
  padding: 0 0.8rem;
  border-bottom: 1px solid #f4f4f4;
}

.btn_block {
  padding: 0.8rem 2rem;
  background-color: #fff;
  border-bottom: 1px solid #f4f4f4;
}

.btn_refund {
  text-align: center;
  padding: 0.5rem 2rem;
  border: 1px solid var(--color_grey);
  border-radius: 4rem;
}

.bottom_order {
  background-color: #fff;
  font-size: 0.8rem;
  color: var(--color_grey);
}

.bottom_order > view {
  padding: 0.6rem;
}

.bottom_order > view + view {
  border-top: 1px solid #f4f4f4;
}

.order_num {
  margin-bottom: 0.3rem;
}
</style>
