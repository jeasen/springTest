var vuePagenationTemplate = 
['<div>',
'    <div v-show="showDetail" class="pull-left  pagination-detail">',
'    <span class="pagination-info">显示第{{recordFrom__}}到第{{recordTo__}}条记录，总共 {{total}} 条记录</span>',
'    <span class="page-list">每页显示</span>',
'    <span class="btn-group dropup">',
'       <button type="button" class="btn btn-default btn-sm dropdown-toggle" style="width:50px;" data-toggle="dropdown">',
'            <span class="page-size">{{pageSize__}}</span><span class="caret"></span>',
'       </button>',
'       <ul class="dropdown-menu" role="menu">',
'            <li v-for="pagesize in pageList__"><a v-on:click="pageSizeChange(pagesize)">{{pagesize}}</a></li>',
'       </ul>',
'    </span> 条记录',
'    </div>',
'    <ul class="pagination pagination-sm no-margin pull-right">',
'        <li class="page-pre" ><a v-on:click="prePage();">«</a></li>',
'        <li class="page-first" :class="(1 == currentPage__)?\'active\':\'\'"><a v-on:click="goPage(1);">1</a></li>',
'        <li class="page-first-separator disabled" v-show="currentPage__>4"><a href="javascript:void(0)">...</a></li>',
'        <li class="page-number" v-for="pageNum in pageNumList__" :class="(pageNum == currentPage__)?\'active\':\'\'"><a v-on:click="goPage(pageNum)" >{{pageNum}}</a></li>',
'        <li class="page-last-separator disabled" v-show="currentPage__<maxPageNum__-3"><a href="javascript:void(0)">...</a></li>',
'        <li class="page-last" v-show="maxPageNum__>1" :class="(maxPageNum__ == currentPage__)?\'active\':\'\'"><a v-on:click="goPage(maxPageNum__)" >{{maxPageNum__}}</a></li>',
'        <li class="page-next" ><a v-on:click="nextPage();">»</a></li>',
'    </ul>',
'</div>'].join("\n");


var VuePagenation = Vue.extend ({
	template : vuePagenationTemplate,
	props : {
	    pageSize: {
	    	'type' : Number,
	        'default' : 10
	    },
	    pageList: {
	    	'type' : Array,
	        'default' : [10, 20, 30]
	    },
	    total : {
	    	'type' : Number,
	        'default' : 0
	    },
	    /**
	     * 获取ResponseData的方法
	     * 参数 1 ：page 页码
	     * 参数 2 : pageSize 每页显示数据条数
	     */
	    getData : {
	    	'type' : Function,
	    	required: true
	    },
	    //是否显示左侧详情
	    showDetail : true
	},
	data : function(){
		var initData = {};
		
		initData.currentPage__ = 1;
		initData.pageSize__ = this.pageSize;
		initData.maxPageNum__ = Math.ceil(this.total / this.pageSize);
		initData.recordFrom__ =  1;
		initData.recordTo__ =  this.pageSize;
		initData.pageList__ = this.pageList;
		initData.pageNumList__ = [];
		
		for(var i = i; i <= initData.maxPageNum__; i++){
			initData.pageNumList__.push(i);
			if(i == 3){
				initData.pageNumList__.push(initData.maxPageNum__);
				break;
			}
		}
	    
	    return initData;
	},
	watch : {
		'total': function (val, old){
			this.pageNumList__ = [];
			this.recordFrom__ = Math.floor((this.currentPage__ - 1)*this.pageSize__) + 1;
			this.recordTo__ = Math.floor(this.currentPage__ * this.pageSize__);
			this.maxPageNum__ = Math.ceil(this.total / this.pageSize__);
			this.caculatePageNumList();
			console.log('total changed '+ val);
	    },
	    'currentPage__' : function(val, old){
	         this.recordFrom = Math.floor((this.currentPage__ - 1)*this.pageSize__) + 1;
	         this.recordTo = Math.floor(this.currentPage__ * this.pageSize__);
	         this.caculatePageNumList();
	         console.log('currentPage changed '+ val);
        },
        'pageSize__' : function(val, old){
        	this.pageSize__ = val;
        	this.recordFrom__ = Math.floor((this.currentPage__ - 1)*this.pageSize__) + 1;
			this.recordTo__ = Math.floor(this.currentPage__ * this.pageSize__);
        	this.maxPageNum__ = Math.ceil(this.total / this.pageSize__);
        	this.caculatePageNumList();
        	console.log('pageSize changed ' + val);
        }
	},
	methods : {
		pageSizeChange : function(pagesize){
			this.$emit('refreshing');
			this.pageSize__ = pagesize;
			this.getData(1, this.pageSize__); 
		},
		prePage : function(){
			var currentPage = this.currentPage__;
			if(currentPage > 1){
				this.$emit('refreshing');
				this.currentPage__ = currentPage - 1;
				this.getData(this.currentPage__, this.pageSize__);
			}
		},
		nextPage : function(){
			var currentPage = this.currentPage__;
			if(currentPage < this.maxPageNum__){
				this.$emit('refreshing');
				this.currentPage__ = currentPage + 1;
				this.getData(this.currentPage__, this.pageSize__);
			}
		},
		goPage : function(pageNum){
			this.currentPage__ = pageNum;
			this.$emit('refreshing');
			this.getData(pageNum, this.pageSize__);
		},
		caculatePageNumList : function(){
			var currentPage = this.currentPage__;
	        var maxPageNum = this.maxPageNum__;
	        this.pageNumList__ = [];
	        
	        if(currentPage <= 4){
	        	var pageNum = maxPageNum < 6 ? maxPageNum : 6;
	        	for(var i = 2; i < pageNum; i++){
	        		this.pageNumList__.push(i);
	        	}
	        }else if(currentPage > 4 && currentPage <= maxPageNum - 4){
	        	this.pageNumList__.push(currentPage - 1);
		        this.pageNumList__.push(currentPage);
		        this.pageNumList__.push(currentPage + 1);
	        }else if(currentPage > maxPageNum - 4){
	        	for(var i = maxPageNum - 4; i < maxPageNum; i++){
	        		this.pageNumList__.push(i);
	        	}
	        }
		}
	},
	created : function(){
		this.$emit('refreshing');
		this.getData(1, this.pageSize); 
	}
});

Vue.component('pagenation',VuePagenation);