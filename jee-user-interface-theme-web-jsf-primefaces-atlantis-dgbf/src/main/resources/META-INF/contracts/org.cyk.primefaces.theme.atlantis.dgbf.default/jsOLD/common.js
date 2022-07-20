$("document").ready(function() {
	$("ul.layout-tabmenu-nav li:first-child a").trigger('click');
	$('#ul.layout-tabmenu-nav li:first-child').toggleClass('menu-active');
});