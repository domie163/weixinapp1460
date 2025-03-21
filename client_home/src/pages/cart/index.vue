<template>
  <view class="page_cart" id="cart_index">
    <!-- 购物车模块(开始) -->
    <list_cart :list="list"></list_cart>
    <!-- 购物车模块(结束) -->
  </view>
</template>

<script>
import list_cart from "@/components/diy/list_cart.vue";
import mixin from "@/libs/mixins/page.js";

export default {
  mixins: [mixin],
  components: {
    list_cart,
  },
  data() {
    return {
      // 登录权限
      oauth: {
        signIn: true,
        user_group: [],
      },
      url_get_list: "~/api/cart/get_list?",
      query: {
        user_id: 0,
      },
    };
  },
  methods: {
    get_list_before(param) {
      param.user_id = this.user.user_id;
      return param;
    },
    get_list_after(json) {
      return json.result.list.map((o) => {
        o.selected = false;
      });
    },
  },
  onShow() {},
};
</script>

<style scoped>
.page_cart {
  padding: 0.8rem 0.5rem 2.5rem;
  height: 100vh;
  background-color: #f8f8f8;
}
</style>
