<template>
  <view class="page_order" id="order_list">
    <!-- 搜索栏 -->
    <uni-search-bar
      placeholder="搜索产品"
      @confirm="search"
      @cancel="cancel"
      cancelText="取消"
      @input="input($event, 'title')"
    >
      <uni-icons slot="searchIcon" color="#999999" size="18" type="home" />
    </uni-search-bar>

    <!-- 筛选栏 -->
    <list_tab
      activeColor="var(--color_primary)"
      lineColor="var(--color_primary)"
      :tabs="state_list"
      v-model="query['state']"
      @change="changeTab"
    ></list_tab>
    <!-- /筛选栏 -->

    <list_order v-if="$check_action('/order/list', 'get')" :list="list_diff">
    </list_order>
  </view>
</template>

<script>
import list_order from "@/components/diy/list_order.vue";
import list_tab from "@/components/diy/list_tab.vue";
import mixin from "@/libs/mixins/page.js";

export default {
  mixins: [mixin],
  components: {
    list_order,
    list_tab,
  },
  data() {
    return {
      // 登录权限
      oauth: {
        signIn: true,
        user_group: [],
      },
      url_get_list: "~/api/order/get_list?like=0",
      list_diff: [],
      query: {
        user_id: this.$store.state.user.user_id,
        state: "",
      },
      state_list: ["全部", "待付款", "已付款"],
    };
  },
  methods: {
    /**
     * 下拉商品筛选选择
     */
    filter_item(o) {
      if (o.name === "全部") {
        this.query.state = "";
      } else {
        this.query.state = o.name;
      }
      this.get_list();
    },
    /**
     * 搜索
     */
    search() {
      this.query.page = 1;
      this.get_list();
    },
    input(e, key) {
      this.query[key] = e.value;
    },
    // 改变分类标签
    changeTab(val) {
      if (val === "全部") {
        this.query.state = "";
      }else{
		  this.query.state = val
	  }
      this.search();
    },
    get_list_before(param) {
      param.user_id = this.$store.state.user.user_id;
      return param;
    },
    get_list_after(json) {
      // 根据订单号区分列表
      var list_diff = [];
      var order_num = "";
      var index = -1;
      json.result.list.map((o) => {
        console.log(o)
        if (o.order_number === order_num) {
          list_diff[index]["list"].push(o);
          list_diff[index]["sum_price"] += o.price_count;
        } else {
          index += 1;
          order_num = o.order_number;
          list_diff[index] = {};
          var obj_diff = list_diff[index];
          obj_diff["create_time"] = o.create_time;
          obj_diff["order_number"] = order_num;
          obj_diff["list"] = [];
          obj_diff["list"].push(o);
          obj_diff["sum_price"] = o.price_count;
          obj_diff["state"] = o.state;
        }
      });
	  
      this.list_diff = list_diff;
      return json;
    },
  },
};
</script>

<style scoped>
.page_order {
  background-color: #f8f8f8;
  min-height: 100vh;
}
</style>
