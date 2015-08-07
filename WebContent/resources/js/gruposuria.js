/*$(document).ready(function() {
	alert("Opa!!! deu certo ;)");
});*/
$ = jQuery.noConflict();
$(document).ready(function() {
	$("input.data").mask("99/99/9999");
	$("input.cpf").mask("999.999.999-99");
	$("input.cep").mask("99.999-999");
	$("input.dinheiro").maskMoney({
		showSymbol : true,
		symbol : "R$",
		decimal : ",",
		thousands : "."
	});
	$(".valor").maskMoney({
		showSymbol : true,
		symbol : "R$",
		decimal : ".",
		thousands : ","
	});

	// Permite somente caracteres alfanumericos (sem pontos, virgulas etc... )
	$('.somenteAlfanumericos').alphanumeric();

	// Permite somente caracteres numericos
	$('.somenteNumeros').numeric();

	// Permite somente caracteres minusculos
	$('.somenteMinusculas').alpha({
		nocaps : true
	});

	// Permite somente caracteres maiusculos
	$('.somenteMaiusculas').alpha({
		allcaps : true
	});

	// Permite somente caracteres especificos
	// $('.somenteCaracteres').alphanumeric({allow:"., "});

	// Permite somente caracteres numericos e ponto
	// $('.sample5').numeric({allow:"."});

	// Permite somente caracteres numericos e ponto
	$('.bloqueiaEspacos').alphanumeric({
		ichars : ' '
	});

});

$(function() {

	$('.realce').hover(function() {
		$(this).stop().animate({
			"border-radius" : "3px",
			// "opacity" : 100.0,
			"background-color" : "#ccc"
		});
	}, function() {
		$(this).animate({
			"background-color" : "transparent"
		});
	});

	$('div[class*=bannersPeq] img').animate({
		"opacity" : 1.0
	});
	$('div[class*=bannersPeq] img').hover(function() {
		$(this).stop().animate({
			"opacity" : 0.5
		});
	}, function() {
		$(this).animate({
			"opacity" : 1.0
		});
	});

	$('.trocar').hover(function() {
		$(this).children('.front').stop().animate({
			"top" : '50px'
		}, 300); // Trabalha no hoverIn
	}, function() {
		$(this).children('.front').stop().animate({
			"top" : '0'
		}, 400); // Trabalha no hoverOut
	});

});

PrimeFaces.locales['pt_BR'] = {
	closeText : 'Fechar',
	prevText : 'Anterior',
	nextText : 'Próximo',
	currentText : 'Começo',
	monthNames : [ 'Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho',
			'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro' ],
	monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago',
			'Set', 'Out', 'Nov', 'Dez' ],
	dayNames : [ 'Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta',
			'Sábado' ],
	dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb' ],
	dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S', 'S' ],
	weekHeader : 'Semana',
	firstDay : 1,
	isRTL : false,
	showMonthAfterYear : false,
	yearSuffix : '',
	timeOnlyTitle : 'Só Horas',
	timeText : 'Tempo',
	hourText : 'Hora',
	minuteText : 'Minuto',
	secondText : 'Segundo',
	currentText : 'Data Atual',
	ampm : false,
	month : 'Mês',
	week : 'Semana',
	day : 'Dia',
	allDayText : 'Todo Dia'
};

PrimeFaces.locales['pt'] = {
	closeText : 'Fechar',
	prevText : 'Anterior',
	nextText : 'Próximo',
	currentText : 'Começo',
	monthNames : [ 'Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho',
			'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro' ],
	monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago',
			'Set', 'Out', 'Nov', 'Dez' ],
	dayNames : [ 'Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta',
			'Sábado' ],
	dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb' ],
	dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S', 'S' ],
	weekHeader : 'Semana',
	firstDay : 1,
	isRTL : false,
	showMonthAfterYear : false,
	yearSuffix : '',
	timeOnlyTitle : 'Só Horas',
	timeText : 'Tempo',
	hourText : 'Hora',
	minuteText : 'Minuto',
	secondText : 'Segundo',
	currentText : 'Data Atual',
	ampm : false,
	month : 'Mês',
	week : 'Semana',
	day : 'Dia',
	allDayText : 'Todo Dia'
};
