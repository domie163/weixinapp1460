<template>
  <view class="page_goods" id="goods_list">
    <uni-search-bar
      placeholder="搜索产品"
      @confirm="search"
      @cancel="cancel"
      cancelText="取消"
      @input="input($event, 'title')"
    >
      <uni-icons slot="searchIcon" color="#999999" size="18" type="home" />
    </uni-search-bar>
    <view class="select">
      <uni-data-select
        @change="searchType"
        v-model="query.type"
        :localdata="types"
      ></uni-data-select>
    </view>
    <template v-if="$check_action('/goods/list', 'get')">
      <!-- 商品列表 -->
      <list_goods :list="list"></list_goods>
      <!-- /商品列表 -->
    </template>
    <uni-pagination
      class="pager"
      show-icon="true"
      :total="count"
      :pageSize="query.size"
      :current="query.page"
      @change="page_change"
    ></uni-pagination>
  </view>
</template>

<script>
import list_goods from "@/components/diy/list_goods.vue";
import list_tab from "@/components/diy/list_tab.vue";
import bar_orderby from "@/components/diy/bar_orderby.vue";
import mixin from "@/libs/mixins/page.js";

export default {
  mixins: [mixin],
  components: {
    list_goods,
    list_tab,
    bar_orderby,
  },
  data() {
    return {
      url_get_list: "~/api/goods/get_list?like=0",
      list: [],
      query: {
        title: "",
        page: 1,
        size: 6,
        type: "",
      },
      // 分类
      types: [{value:"",text:"全部"}],
      list_orderby: [
        {
          name: "销量",
          direction: "",
          command_asc: "`sales` asc",
          command_desc: "`sales` desc",
        },
        {
          name: "上架日期",
          direction: "",
          command_asc: "`create_time` asc",
          command_desc: "`create_time` desc",
        },
        {
          name: "价格",
          direction: "",
          command_asc: "`price` asc",
          command_desc: "`price` desc",
        },
        {
          name: "点击量",
          direction: "",
          command_asc: "`hits` asc",
          command_desc: "`hits` desc",
        },
      ],
    };
  },
  methods: {
    /**
     * 添加商品过滤
     */
    get_goods_type() {
      this.$get("~/api/goods_type/get_list", {}, (res) => {
        if (res.result) {
          let list = res.result.list;
          list.map((obj) => {
            this.types.push({value:obj.name,text:obj.name});
          });
        }
      });
    },

    input(e, key) {
      this.query[key] = e.value;
    },

    search() {
      this.query.page = 1;
      this.get_list();
    },
    cancel() {
      this.query.title = "";
      this.search();
    },
    // 改变分类标签
    searchType(v){
      this.query.type = v;
      this.query.page = 1;
      this.get_list();
    },
    // 控制排序
    handleOrderby(o) {
      // console.log(o);
      // 取出对应的orderby

      // 重置其他排序的direction
      this.list_orderby.map((val) => {
        if (val.name !== o.text) {
          return (val.direction = "");
        }
      });

      // 找到对应的排序项，发送排序请求
      var obj_orderby = this.list_orderby.find((val) => val.name === o.text);
      if (o.direction === "") {
        this.query.orderby = "";
        this.search();
      } else if (o.direction === "up") {
        this.query.orderby = obj_orderby.command_desc;
        this.search();
      } else if (o.direction === "down") {
        this.query.orderby = obj_orderby.command_asc;
        this.search();
      }
    },
  },
  mounted() {
    this.get_goods_type();
  },
};
</script>

<style lang="scss" scoped>
.page_goods {
  padding: var(--padding_base);
  .select {
    padding: 0 10px;

    &:after {
      content: "";
      display: block;
      clear: both;
    }
  }
}
</style>
