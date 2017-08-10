/*!
 * lovTable v1.0.0
 * Copyright 2016 liu xiaobo
 * Licensed under the MIT license
 */
;(function($, window, document, undefined) {
	var getId = function(len) {
		len = len || 8;
		var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz_';
		var maxPos = $chars.length;
		var id = '';
		for (i = 0; i < len; i++) {
			id += $chars.charAt(Math.floor(Math.random() * maxPos));
		}
		return id;
	}

	var lovModel = function(ele, opt, topt) {
		this.$element = ele;
		this.defaults = {
			lovId : undefined,
			singleUrl : undefined,
			valueField : 'id',
			textField : 'text',
			placeholder:'',
			title : '请选择',
			width : 600,
			top : 160,
			okText : '确定',
			closeText : '关闭',
			queryText:'查询',
			validateRules:'',
			selectText:'请选择数据',
			rowSelected:function(row,obj){
				
			},
			setParam :function(){
				return {query:{}};
			},
			textClear:function(){
				
			}
		};
		this.tableDefaults = {
			url : '',
			method : "POST",
			contentType : "application/x-www-form-urlencoded",
			striped : true,
			pagination : true,
			showPagenationDetail : false, //是否显示分页详情 
			sidePagination : "server",
			paginationHAlign : 'left',
			pageSize : 6,
			uniqueId : "lovId",
			singleSelect : true,
			clickToSelect : true,
			selectItemName : "selected",
			autoQuery : false,
			columns : [],
			queryParams : function(params) {
				params.page = params.offset / params.limit + 1;
				params.pagesize = params.limit;
				params.sortname = params.sort;
				params.sortorder = params.order;
				return params;
			},
			onLoadSuccess : function(data) {

			},
			onDblClickRow : function(row, $element) {

			}
		};
		this.options = $.extend({}, this.defaults, opt);
		this.tableOptions = $.extend({}, this.tableDefaults, topt);
		var hasCheckbox = false;
		for (var i = 0; i < this.tableOptions.columns.length; i++) {
			if (this.tableOptions.columns[i].checkbox) {
				hasCheckbox = true;
				break;
			}
		}
		if (!hasCheckbox) {
			this.tableOptions.columns.unshift({
				field : this.tableOptions.selectItemName,
				checkbox : true
			});
		}
		/*
		 * this.tableOptions.onLoadSuccess=function(data){
		 * $('#'+lovId).find('.pagination-detail').remove(); }
		 */
	}

	lovModel.prototype = {
		lovTable : function() {
			if (this.options.lovId == undefined)
				this.options.lovId = getId();
			
			var lov = this;
			var lovId = this.options.lovId;
			var eleId = this.$element.prop('id')||lovId;
			var lovText='';
			if(this.$element.val()!=''){
				var dataJson={order:'asc',offset:0,page:1,pagesize:1,sortorder:'asc',limit:1};
				var textField=this.options.textField;
				dataJson[this.options.valueField]=this.$element.val();
				var exData=this.tableOptions.queryParams(dataJson);
				$.ajax({
					url:this.tableOptions.url,
					type:this.tableOptions.method,
					contentType : "application/x-www-form-urlencoded",
					dataType:'JSON',
					data:exData,
					success:function(d){
						if(d.rows&&d.rows.length){
							lovText=d.rows[0][textField];
							$('#'+eleId+'_lovtext').val(lovText);
							$('#'+eleId+'_lovtext').trigger('change');
						}else{
							$('#'+eleId).val('');
							$('#'+eleId+'_lovtext').val('');
							$('#'+eleId+'_lovtext').trigger('change');
						}
					}
				});
			}
			if(!this.$element.attr('id')){
				this.$element.attr('id',eleId);
			}
			this.$element.attr('lovid',lovId);
			this.$element.attr('valueField',this.options.valueField);
			this.$element.addClass(this.options.validateRules);
			this.$element.addClass('lov-hidden-input');
			
			this.$element.after('<div id="'+eleId+'_lovgroup" class="input-group" style="width:100%;"><input id="'+eleId
					+'_lovtext" type="text" placeholder="'+this.options.placeholder+'" class="form-control input-sm" value="'+lovText+'"><span class="input-group-btn"><button id="'
					+eleId+'_lovbtn" style="border-left:none;left:1px;border-radius:0px;" data-toggle="modal" data-target="#'+lovId+'_lovmodal" class="btn btn-default btn-sm btn-lov" type="button"><span class="glyphicon glyphicon-th"></span></button></span></div>'
					+'<div id="'+eleId+'_lovlist" style="display:none;z-index:1050;position:absolute;border:1px solid #ccc;background-color:white;"></div>');
					
			var $lovGroup = $('#' + eleId + '_lovgroup');
			var $lovList =  $('#' + eleId + '_lovlist');
			
			var queryHtml='';
			var buttonHtml='';
			if(this.options.query&&this.options.query.length){
				queryHtml='<table  style="width:100%;line-height:30px;"><tr>';
				var temp_width=130;
				if(this.options.query.length==1)
					temp_width=280;
				for(var i=0;i<this.options.query.length;i++){
					queryHtml+='<td style="text-align:right;width:80px;padding:6px 3px 7px 3px;"><label>'+this.options.query[i].text+'</label></td><td style="width:'+temp_width+'px;padding:4px 3px;"><input name="'+this.options.query[i].name+'" type="text" class="form-control input-sm"/></td>';
				}
				if(this.options.button&&this.options.button.length){
					for(var j=0;j<this.options.button.length;j++){
						buttonHtml+='&nbsp;<button id="'+lovId+'_btn'+j+'" bindex="'+j+'" class="btn btn-primary btn-sm">'+this.options.button[j].text+'</button>';
					}
				}
				queryHtml+='<td style="padding:4px 3px;"><button id="'+lovId+'_btnquery" class="btn btn-primary btn-sm" type="button">'+this.options.queryText+'</button>'+buttonHtml+'</td></tr></table>';
			}
			$(document.body).append(
							'<div class="modal fade" id="'
									+ lovId
									+ '_lovmodal" tabindex="-1" role="dialog" aria-hidden="true"><div class="modal-dialog" style="font-size: 12.2px;width:'
									+ this.options.width
									+ 'px;top:'
									+ this.options.top
									+ 'px"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button><h4 class="modal-title">'
									+ this.options.title
									+ '</h4></div><div class="modal-body">'+queryHtml+'<table id="'
									+ lovId
									+ '_table" class="table table-hover table-striped table-bordered"></table></div></div></div></div>');
			var valueField=this.options.valueField;
			var textField=this.options.textField;
			var rowSelected=this.options.rowSelected;
			var $ele=this.$element;
			this.tableOptions.onDblClickRow = function(row, $element) {
				//$ele.val(row[valueField]);
				$ele.val(row[valueField]).trigger('change');
				$('#'+eleId+'_lovtext').val(row[textField]);
				$('#'+eleId+'_lovtext').trigger('change');
				$('#' + lovId + '_lovmodal').modal('hide');
				if(typeof(rowSelected)=='function')
					rowSelected(row,$ele);
			}
			var $lovTable=$('#' + this.options.lovId + '_table');
			$lovTable.bootstrapTable(this.tableOptions);
			var $lovid=$('#' + this.options.lovId + '_lovmodal');
			$lovid.find('.modal-header').css("padding","8px 15px");
			$lovid.find('.modal-body .clearfix').append('<div class="modal-footer"><button id="'+lovId+'_btnok" type="button" class="btn btn-primary">'
									+ this.options.okText
									+ '</button><button type="button" id="'+lovId+'_btnclose" class="btn btn-default" data-dismiss="modal">'
									+ this.options.closeText
									+ '</button></div>');
			$lovid.find('.modal-body,.modal-footer').css("padding", "8px 15px");
			$lovid.on('show.bs.modal', function () {
				//var pheight=$lovid.offsetParent().top;
				var dheight=$(window).height()/2;
				var cheight=$lovid.height()/2;
				$lovid.find('.modal-dialog').animate({'top':(dheight-cheight+25)+'px'});
				$lovid.find('input[type="text"]').val('');
				
				//将setParam中的条件追加给查询条件
				var q = {};
				var param = lov.options.setParam();
 				if(param&&param.query&&!$.isEmptyObject(param.query)){
					$.extend(q, param.query);
				}
				
				$lovTable.bootstrapTable('refresh',{query : q});
			});
			
			this.$element.data('lov', {
				'$element' : $ele,
				'$lovGroup' : $lovGroup,
				'$lovList' : $lovList,
				'$lovTable' : $lovTable,
				destroy : function(){
					this.$lovGroup.remove();
					this.$lovList.remove();
					this.$lovTable.remove();
				}
			});
		}
	}

	$.fn.lovTable = function(options, tableOptions) {
		if (typeof(options) == 'string'){
			if(options=='show'){
				$('#' + this.attr('lovid') + '_lovmodal').modal('show');
				return this;
			}else if(options=='hide'){
				$('#' + this.attr('lovid') + '_lovmodal').modal('hide');
				return this;
			}else if(options=='clear'){
				this.val('').trigger('change');
				var eleId=this.prop('id')||this.attr('lovid');
				$('#'+eleId+'_lovtext').val('').trigger('change');
				return this;
			}else if(options=='refreshTable'){
				$('#' + this.attr('lovid') + '_table').bootstrapTable('refresh');
				return this;
			}else if(options=='setObj'){
				var value=null;
				var text=null;
				var silent=null;
				if(tableOptions){
					value=tableOptions.value;
					text=tableOptions.text;
					//add by Jeb Sun 2017/03/09  silent为true则不触发 change事件
					silent=tableOptions.silent;
				}
				
				var eleId=this.prop('id')||this.attr('lovid');
				if(silent){
					$('#'+eleId+'_lovtext').val(text||'');
					$(this).val(value||'');
				}else{
					$('#'+eleId+'_lovtext').val(text||'').trigger('change');
					$(this).val(value||'').trigger('change');
				}
				
				return this;
			}else if(options=='getSelected'){
				var row= $('#' + this.attr('lovid') + '_table').bootstrapTable('getSelections');
				var valueField=this.attr('valueField');
				var value=this.val();
				if(row.length)
					return row[0];
				else{
					var rows=$('#' + this.attr('lovid') + '_table').bootstrapTable('getData');
					for(var i=0;i<rows.length;i++){
						 if(rows[i][valueField]==value)
							 return rows[i];
					}
				}
				if(this.val()!=''){
					var obj={};
					obj[valueField]=this.val();
					return obj;
				}else
					return {};
			}
		}else{
			this.each(function(){	
			    //如果存在，则销毁
		    	if($(this).data('lov') != null) {
		    		$(this).data('lov').destroy();
		    	}
				
				var lov = new lovModel($(this), options, tableOptions);
				lov.lovTable();	
				var eleId=$(this).prop('id')||$(this).attr('lovid');
				$('#' + lov.options.lovId + '_lovmodal').on('shown.bs.modal', function() {
					var param=lov.options.setParam();
					if(param&&param.query&&!$.isEmptyObject(param.query))
						$('#' + lov.options.lovId + '_table').bootstrapTable('refresh',param);
				});
				$('#' + lov.options.lovId + '_btnquery').on('click',function(){
					var param=lov.options.setParam();
					var q = {};
					$(this).parent().parent().find('input').each(function(i,e){
						var key = $(e).attr('name');
						var value = $(e).val().trim();
						   if(value){
							   q[key] = value;
						}
					});
					q['page']=1;
					
					//将setParam中的条件追加给查询条件
					if(param&&param.query&&!$.isEmptyObject(param.query)){
						$.extend(q, param.query);
					}
						
					if(q){
						$('#' + lov.options.lovId + '_table').bootstrapTable('refresh',{
						   query : q
						});
					}
				});
				$('#'+lov.options.lovId+'_btnok').on('click',function(){
					if(eleId==undefined)
						eleId=lov.$element.prop('id')||lov.options.lovId;
					var row=$('#' + lov.options.lovId + '_table').bootstrapTable('getSelections');
					if(row.length){
						//lov.$element.val(row[0][lov.options.valueField]);
						lov.$element.val(row[0][lov.options.valueField]).trigger('change');
						$('#'+eleId+'_lovtext').val(row[0][lov.options.textField]);
						$('#'+eleId+'_lovtext').trigger('change');
						$('#' + lov.options.lovId + '_lovmodal').modal('hide');
						if(typeof(lov.options.rowSelected)=='function')
							lov.options.rowSelected(row[0],lov.$element);
					}else{
						if(layer)
							layer.alert(lov.options.selectText);
						else
							alert(lov.options.selectText);
					}
				});
				if(lov.options.button&&lov.options.button.length){
					for(var j=0;j<lov.options.button.length;j++){
						if(typeof(lov.options.button[j].click)=='function'){
							$('#'+lov.options.lovId+'_btn'+j).on('click',function(){
								var index=Number($(this).attr('bindex'));
								lov.options.button[index].click.call(lov.$element,lov);
							});
						}
					}
				}
				
				$('#'+eleId+'_lovtext').on('blur',function(){
					if($(this).val()!=''){
						if(eleId==undefined)
							eleId=lov.$element.prop('id')||lov.options.lovId;
						var val=$(this).val();
						var $list=$('#'+eleId+'_lovlist');
						var hasval=false;
						$list.find('.item').each(function(){
							if($(this).text()==val){
								hasval=true;
								var temp_data={};
								temp_data[lov.options.textField]=val;
								temp_data[lov.options.valueField]=$(this).attr('val');
								//lov.$element.val($(this).attr('val'));
								lov.$element.val($(this).attr('val')).trigger('change');;
								lov.options.rowSelected(temp_data,lov.$element);
							}	
						});
						//如果隐藏字段有值，则不清除数据
						var elemHasVal = lov.$element.val();
						var isItemActive = $list.find('.item').hasClass('active');
						
						if(!hasval&&!elemHasVal){
							//lov.$element.val('');
							if(isItemActive){
								lov.$element.val('');
							}else{
								lov.$element.val('').trigger('change');
							}
							
							$(this).val('');
							lov.options.textClear();
						}
					}else{
						//lov.$element.val('');
						lov.$element.val('').trigger('change');
						lov.options.textClear();
					}
				});
				
				$(window).on('click',function(){
					$('#'+eleId+'_lovlist').hide();
				});
				
				function queryOption(){
					
					if($(this).val()!=''){
						if(eleId==undefined)
							eleId=lov.$element.prop('id')||lov.options.lovId;
						var data={};
						var param=lov.options.setParam();
						if(param&&param.query&&!$.isEmptyObject(param.query)){
							$.extend(data,param.query);
						}
						var textField=lov.options.textField;
						var valueField=lov.options.valueField;
						data[textField]=$(this).val();
						
						$.ajax({
							url:lov.tableOptions.url||lov.options.singleUrl,
							type:lov.tableOptions.method,
							contentType : lov.tableOptions.contentType,
							dataType:'JSON',
							data:data,
							cache:true,
							success:function(d){
								if(d.success&&d.rows&&d.rows.length){
									var $ele=$('#'+eleId+'_lovtext');
									var selwidth=$ele.parent().width();
									var dropStart='<ul class="select2-results__options" style="max-height: 198px;overflow-y: auto;">';
									for (var i = 0; i < d.rows.length; i++) {
										dropStart=dropStart+'<li style="list-style:none;color:#333;cursor:pointer;font-size: 12.2px;padding: 6px 12px;" class="item" idx="'+i+'" val="'+d.rows[i][valueField]+'" sel="N">'+d.rows[i][textField]+'</li>';
									}
									dropStart=dropStart+'</ul>'
									
									$('#'+eleId+'_lovlist').html(dropStart).css({width:selwidth}).show();
									$('#'+eleId+'_lovlist .item').on('click',function(e){
							            var temp_data={};
							            var $this=$(this);
							            var index = $this.attr('idx');
							            temp_data[valueField]=$this.attr('val');
							            temp_data[textField]=$this.text();
							            $('#'+eleId+'_lovtext').val(temp_data[textField]);
							            //lov.$element.val(temp_data[valueField]);
							            lov.$element.val(temp_data[valueField]).trigger('change');
										//lov.options.rowSelected(temp_data,lov.$element);
							            var selectData = $.extend({},d.rows[index]);
							            lov.options.rowSelected(selectData,lov.$element);
										$this.parent().parent().hide();
									}).hover(function(){
										$(this).css({'background-color': '#3c8dbc','color': 'white'});
										$(this).addClass('active');
									},function(){
										$(this).css({'background-color': 'white','color': '#333'});
										$(this).removeClass('active');
									});	
								}else{
									//lov.$element.val('');
									lov.$element.val('');
									//$('#'+eleId+'_lovtext').val('');
									//$('#'+eleId+'_lovtext').trigger('change');
									lov.options.textClear();
								}
							}
						});
					}else{
						if(eleId==undefined)
							eleId=lov.$element.prop('id')||lov.options.lovId;
						$('#'+eleId+'_lovlist').html('').hide();
						//lov.$element.val('');
						lov.$element.val('').trigger('change');
						lov.options.textClear();
					}
				}
				
				$('#'+eleId+'_lovtext').on('keyup',
					 //空闲时间的间隔控制,大于500ms执行
					 $.debounce(500,queryOption)
				);
			    
			});
			return this;
		}
	}
})(jQuery, window, document);