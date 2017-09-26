/*!
 * select2-extension v1.0.0
 * Copyright 2016 liu xiaobo
 * Licensed under the MIT license
 */
;( function( $ ) {
	"use strict";

	$.fn.fireOnDisable = function( settings ) {
		// Only perform this DOM change if we have to watch changes with propertychange
		// Also only perform if getOwnPropertyDescriptor exists - IE>=8
		// I suppose I could test for "propertychange fires, but not when form element is disabled" - but it would be overkill
		if( !( 'onpropertychange' in document.createElement( 'input' ) ) || Object.getOwnPropertyDescriptor === undefined ) {
			return this;
		}

		// IE9-10 use HTMLElement proto, IE8 uses Element proto
		var someProto = window.HTMLElement === undefined ? window.Element.prototype : window.HTMLElement.prototype,
			someTrigger = function(){},
			origDisabled = Object.getOwnPropertyDescriptor( someProto, 'disabled' );

		if( document.createEvent ) {
			someTrigger = function( newVal ){
				var event = document.createEvent('MutationEvent');
				/*
				Instantiate the event as close to native as possible:
				event.initMutationEvent(eventType, canBubble, cancelable, relatedNodeArg, prevValueArg, newValueArg, attrNameArg, attrChangeArg);
				*/
				event.initMutationEvent( 'DOMAttrModified', true, false, this.getAttributeNode('disabled'), '', '', 'disabled', 1 );
				this.dispatchEvent( event );
			};
		} else if( document.fireEvent ) {
			someTrigger = function(){
				this.fireEvent( 'onpropertychange' );
			};
		}

		return this.each( function() {
			// call prototype's set, and then trigger the change.
			Object.defineProperty( this, 'disabled', {
				set: function( isDisabled ) {
					// We store preDisabled here, so that when we inquire as to the result after throwing the event, it will be accurate
					// We can't throw the event after the native send, because it won't be be sent.
					// We must do a native fire/dispatch, because native listeners don't catch jquery trigger 'propertychange' events
					$.data( this, 'preDisabled', isDisabled );
					//if ( isDisabled ) {
						// Trigger with dispatchEvent
						someTrigger.call( this, isDisabled );
					//}

					return origDisabled.set.call( this, isDisabled );
				},
				get: function() {
					var isDisabled = $.data( this, 'preDisabled' );
					if( isDisabled === undefined ) {
						isDisabled = origDisabled.get.call( this );
					}
					return isDisabled;
				}
			});
		});
	};
})( jQuery );

;(function($, window, document, undefined){
	$.fn.select2Ext=function(opt){
		var defaluts={
			minimumResultsForSearch : Infinity,
			allowClear : true,
			escapeMarkup : function(markup) {
				return markup;
			},
			width:'100%',
			templateResult : function(item){return item.text}, 
			templateSelection : function(item){return item.text}
		};
		
		return this.each(function (){
			
			var options=$.extend({},defaluts,opt);
			$(this).fireOnDisable().select2(options)
			//修复select2清空弹出两次的问题
			.on("select2:unselecting", function (e,container) {
				e.preventDefault();
				var $selection = container.$selection;
				var $placeholder = container.selection.createPlaceholder(container.selection.placeholder);
				
                $(this).val('').trigger('change');
                
                $selection.find('.select2-selection__rendered').empty().append($placeholder);
                
            });
			
	    });
	}
})(jQuery, window, document);