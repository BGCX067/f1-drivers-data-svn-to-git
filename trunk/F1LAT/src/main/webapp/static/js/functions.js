function selectDriver(driverId){
	$("#driverId").val(driverId);
	$("#driverForm").submit();
}

function selectRace(raceRound, raceName, raceId) {
	$("#raceRound").val(raceRound);
	$("#raceName").val(raceName);
	$("#raceYear").val($("#fecha").val());
	$("#raceId").val(raceId);
	$("#raceForm").submit();
}

function selectSession(idSession) {
	$("#session").val(idSession);
	$('#myModal').modal('show').css({
		'margin-top' : function() {
			return window.pageYOffset + $(this).height() / 4;
		}
	});
	$("#raceInfoForm").submit();
}

function seleccionarVuelta(obj, driverT, lapT) {
	$.post("utilTiempos", {
		driver : driverT,
		lap : lapT,
		query : "U"
	}, function(data) {
		if (data) {
			$("#" + driverT).html(data);
			if ($(obj).attr("class") == 'selected') {
				$(obj).css("background-color", "#FFF");
				$(obj).attr("class", "");
			} else {
				$(obj).css("background-color", "#E6FFC7");
				$(obj).attr("class", "selected");
			}

		}
	});
}

function seleccionarTodasVueltas(driverT, lapsT) {
	$.post("utilTiempos", {
		driver : driverT,
		laps : lapsT,
		query : "A"
	}, function(data) {
		if (data) {
			$("#" + driverT).html(data);
		}
	});
}

function cargarCarrerasPorAnio(anio) {
	$
			.post(
					"utilLoadRaces",
					{
						year : anio
					},
					function(data) {
						if (data) {
							var divRaces = $("#divRaces");
							$(divRaces).empty();
							$
									.each(
											data,
											function(item, value) {
												if (item == "races") {
													$
															.each(
																	value,
																	function(i,
																			object) {
																		var divAppend = "<div class=\"col-lg-3 col-md-3 col-sm-4 col-xs-6 item\">";
																		divAppend += "<div class=\"portfolio-item\" style=\"cursor: pointer;\" onclick=\"selectRace('"
																				+ object.round
																				+ "','"
																				+ object.circuit.country.name
																				+ "','"
																				+ object.id
																				+ "')\">";
																		divAppend += "<img src=\"static/img/Races/flag-"
																				+ object.circuit.country.name
																						.toLowerCase()
																				+ ".png\" alt=\""
																				+ object.name
																				+ " title=\""
																				+ object.name
																				+ "\" />";
																		divAppend += '<div class="portfolio-item-title">';
																		divAppend += '<p>'
																				+ object.circuit.country.name
																						.toUpperCase()
																				+ ' - '
																				+ object.dates
																						.toUpperCase()
																				+ '</p>';
																		divAppend += "</div></div></div>";
																		$(
																				divRaces)
																				.append(
																						divAppend);
																		$(
																				"#spanYear")
																				.html(
																						anio);
																	});

												}
											});
						}
					});
}

function compareSelectedDrivers() {
	var driversSelected = new Array();
	jQuery(document).find("input:checkbox:checked").each(function() {
		driversSelected.push($(this).val());
	});

	if (driversSelected.length > 1) {
		jQuery("#driversSel").val(driversSelected);
		jQuery("#formCompare").submit();
	} else
		alert("You have to select more than 1 driver to compare");
}

function watchLapCharts() {
	var driversSelected = new Array();
	jQuery(document).find("input:checkbox:checked").each(function() {
		driversSelected.push($(this).val());
	});

	if (driversSelected.length > 1) {
		if (driversSelected.length > 4) {
			if (confirm("If you select more than 4 drivers, the chart will be a little bit confusing. Do you want to proceed anyway?")) {
				jQuery("#driversSelChart").val(driversSelected);
				jQuery("#formLapChart").submit();
			}
		} else {
			jQuery("#driversSelChart").val(driversSelected);
			jQuery("#formLapChart").submit();
		}
	} else
		alert("You have to select at least 1 driver to see lap chart");
}

$('.input-group.date').datepicker({
	format : "yyyy",
	startDate : '1950',
	endDate : new Date().getFullYear().toString(),
	minViewMode : 2,
	autoclose : true
}).on('changeDate', function(e) {
	cargarCarrerasPorAnio(e.date.getFullYear());
});

$(document).on({
	ajaxStart : function() {
		$('#myModal').modal('show').css({
			'margin-top' : function() {
				return window.pageYOffset + $(this).height() / 4;
			}
		});
	},
	ajaxStop : function() {
		$('#myModal').modal('hide');
	}
});

jQuery('a[class^="btn-select-all-nonPits_"]').click(
		function(e) {
			e.preventDefault();
			var seleccionar = false;
			var arrayTR = new Array();
			if ($('i', $(this)).attr("class") == 'fa fa-check') {
				$('i', $(this)).removeClass('fa fa-check').addClass(
						'fa fa-times');
				$(this).attr("title",
						"Deselects all valid laps (No pit entrance/exit)");
				$(this).attr("data-original-title",
						"Deselects all valid laps (No pit entrance/exit)");
			} else {
				$('i', $(this)).removeClass('fa fa-times').addClass(
						'fa fa-check');
				$(this).attr("title",
						"Selects all valid laps (No pit entrance/exit)");
				$(this).attr("data-original-title",
						"Selects all valid laps (No pit entrance/exit)");
				seleccionar = true;
			}
			var partes = jQuery(this).attr("class").split("_");
			var $idTR = partes[1];
			jQuery('tr[id ^= "TR_' + $idTR + ';"]').each(function() {
				if (!seleccionar) {
					if (jQuery(this).attr("class") != "selected") {
						jQuery(this).addClass("selected");
						jQuery(this).css("background-color", "#E6FFC7");
						arrayTR.push(jQuery(this).attr("id").split(";")[1]);
					}
				} else {
					if (jQuery(this).attr("class") == "selected") {
						jQuery(this).removeClass("selected");
						jQuery(this).css("background-color", "#FFF");
						arrayTR.push(jQuery(this).attr("id").split(";")[1]);
					}
				}
			});
			seleccionarTodasVueltas($idTR, arrayTR);
		});

jQuery(document).ready(function() {
	if ($(".sf-menu ul:last").text().indexOf("Logout") >= 0) {
		var ww = $(window).width();
		if (ww > 1073) {
			$(".sf-menu ul:last").css("right", 0).css("left", "initial");
		}
		$(window).resize(function() {
			ww = $(window).width();
			if (ww > 1073) {
				$(".sf-menu ul:last").css("right", 0).css("left", "initial");
			} else {
				$(".sf-menu ul:last").css("left", 0).css("right", "initial");
			}
		});
	}
});