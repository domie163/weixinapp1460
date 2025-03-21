<template>
  <view class="list_goods">
    <view v-for="(o, i) in list" :key="i" class="goods_item">
      <navigator :url="'/pages/goods/details?goods_id=' + o[vm.goods_id]">
        <image :src="$fullImgUrl(o[vm.img])"></image>
      </navigator>

      <navigator :url="'/pages/goods/details?goods_id=' + o[vm.goods_id]">
        <view class="title">
          {{ o[vm.title] }}
        </view>
      </navigator>
      <view class="bottom">
        <view class="price"> ￥{{ o[vm.price] | keepTwoNum }} </view>
        <view class="price_ago"> ￥{{ o[vm.price_ago] | keepTwoNum }} </view>
        <uni-icons
          v-if="
            !o[vm.source_table] ||
            $check_cart_page('/' + o[vm.source_table] + '/list')
          "
          class="icon_cart"
          type="cart"
          size="20"
          color="var(--color_primary)"
          @click="add_cart(o)"
        ></uni-icons>
      </view>
    </view>
  </view>
</template>

<script>
import mixin from "@/libs/mixins/component.js";
export default {
  mixins: [mixin],
  props: {
    list: {
      type: Array,
      default: function () {
        return [];
      },
    },
    vm: {
      type: Object,
      default: function () {
        return {
          goods_id: "goods_id",
          img: "img",
          title: "title",
          price: "price",
          price_ago: "price_ago",
          sales: "sales",
          hits: "hits",
          source_table: "source_table",
        };
      },
    },
    span: {
      type: Number,
      default: 2,
    },
  },
  data() {
    return {
      user_id: this.$store.state.user.user_id,
    };
  },
  methods: {
    nav_goods_details(goods_id) {
      this.$nav("/pages/goods/details?goods_id=" + goods_id);
      console.log(goods_id);
    },
    /**
     * 添加购物车
     */
    /**
     * 添加购物车
     */
    add_cart(obj) {
      var {
        title,
        img,
        price,
        price_ago,
        num_buy: num,
        description,
        goods_id,
        type,
      } = obj;
      var body = {
        title,
        img,
        price,
        price_ago,
        num,
        price_count: price,
        description,
        goods_id,
        type,
        user_id: this.$store.state.user.user_id,
      };
      body.num = 1;

      this.$get(
          "~/api/cart/get_list?",
          {
            goods_id: obj.goods_id,
            user_id: body.user_id
          },
          (res) => {
            if (res.result.count) {
              var { cart_id, num, price, price_count } = res.result.list[0];
              num += 1;
              price_count += price;
              this.$post(
                  `~/api/cart/set?cart_id=${cart_id}`,
                  {
                    num,
                    price,
                    price_count,
                  },
                  (res) => {
                    this.$toast("已加入购物车", "成功");
                    console.log("更改值");
                  }
              );
            } else {
              this.$post("~/api/cart/add?", body, (res) => {
                this.$toast("已加入购物车", "成功");
              });
            }
          }
      );
    }
  },
  filters: {
    //过滤器 保留两位
    keepTwoNum: function (value) {
      value = Number(value);
      return value.toFixed(2);
    },
  }
};
</script>

<style lang="scss" scoped>
.list_goods {
  display: flex;
  flex-wrap: wrap;
  .goods_item {
    width: 44%;
    padding: var(--padding_base);
    image {
      width: 100%;
      height: 8rem;
      &:hover {
        transform: rotate(360deg);
        transition: all 0.5s ease-in-out;
      }
    }
    .title {
      overflow: hidden;
      white-space: nowrap;
      text-overflow: ellipsis;
      margin-bottom: var(--margin_small);
    }
    .bottom {
      .price {
        display: inline-block;
        color: var(--color_primary);
        margin-right: 0.5rem;
      }
      .price_ago {
        font-size: var(--font_mini);
        color: var(--color_grey);
        display: inline-block;
        text-decoration: line-through;
      }
      .icon_cart {
        float: right;
      }
	  &:after {
		  content: "";
		  display: block;
		  clear: both;
	  }
    }
  }
}
</style>
