var vueSelectionInputTemplate = ['<div :class="vclass">',
                 	            '     <input type="text" class="form-control input-sm" :class="validation" :id="id" :name="name" :placeholder="placeholder" v-model="selectedItem.id" v-on:click.stop="onInputClick" v-on:blur="onInputBlur"/>',
                	            '     <div :id="id + \'selection-list\'" class="selection-input-list" v-show="optionListShow">',
                	            '          <ul class="selection-input-options">',
                	            '               <li v-for="(option,optionIndex) in options" class="item" v-on:mousedown.stop="onItemSelected(option, optionIndex);">{{option.text}}</li>',
                	            '          </ul>',
                	            '      </div>',
                	            '</div>'].join("\n");



var VueSelectionInput = Vue.extend ({
	template : vueSelectionInputTemplate,
	props : {
	    id: String,
	    vclass: String,
	    name: String,
	    placeholder: String,
	    validation : String,
	    options : Array,
	    value : String
	},
	data : function(){
		return {
			optionListShow: false,
			selectedItem : {
				id : null,
				text : ''
			},
			options : $.map(this.options, function(item, index){
				var option = {};
				option.id = item.id;
				option.text = item.text;
				return option;
			})
	    };
	},
	watch:{
		'selectedItem.id': function (val, old){
			this.$emit('change', val);
	    }
	},
	created: function () {
	    this.selectedItem.id = this.value;
	    this.selectedItem.text = this.value;
	},
	methods : {
		onItemSelected : function(option, optionIndex){
		    this.selectedItem.id = option.id;
		    this.selectedItem.text = option.text;
			this.optionListShow = false;
			$(this.$el).find('input').trigger('change');
		},
		onInputClick : function(){
			this.optionListShow = !this.optionListShow;
		},
		onInputBlur : function(){
			this.optionListShow = false;
		}
	}
	
});

Vue.component('selectioninput',VueSelectionInput);