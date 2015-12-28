jQuery(document)
		.ready(
				function() {

					jQuery('body').css('overflowY', 'hidden');
					jQuery.waitForImages.hasImgProperties = [ 'background',
							'backgroundImage' ];
					jQuery('body').waitForImages(function() {
						// All descendant images have loaded, now slide up.
						// alert("done");
						jQuery(".page-mask").fadeOut(500);
						jQuery('body').css('overflowY', 'auto');
					});

					/*----------------------------------------------------*/
					/*
					 * Search box expand Section
					 * /*----------------------------------------------------
					 */

					jQuery(".search-text-box").focus(function() {
						jQuery("ul.social").animate({
							marginLeft : "-120px"
						}, 450, "easeInSine")
					});

					/*----------------------------------------------------*/
					/*
					 * Keyframe animations enable
					 * /*----------------------------------------------------
					 */

					jQuery().waypoint
							&& jQuery("body")
									.imagesLoaded(
											function() {
												jQuery(
														".animate_afc, .animate_afl, .animate_afr, .animate_aft, .animate_afb, .animate_wfc, .animate_hfc, .animate_rfc, .animate_rfl, .animate_rfr")
														.waypoint(
																function() {
																	if (!jQuery(
																			this)
																			.hasClass(
																					"animate_start")) {
																		var e = jQuery(this);
																		setTimeout(
																				function() {
																					e
																							.addClass("animate_start")
																				},
																				20)
																	}
																},
																{
																	offset : "85%",
																	triggerOnce : !0
																})
											});

					/*----------------------------------------------------*/
					/*
					 * Superfish Mainmenu Section
					 * /*----------------------------------------------------
					 */

					jQuery(function() {
						jQuery('ul.sf-menu').stop().superfish();
					});

					/*----------------------------------------------------*/
					/*
					 * Revolution Slider Nav Arrow Show Hide
					 * /*----------------------------------------------------
					 */

					jQuery('.fullwidthbanner-container').hover(function() {
						jQuery('.tp-leftarrow').stop().animate({
							"opacity" : 1
						}, 'easeIn');
						jQuery('.tp-rightarrow').stop().animate({
							"opacity" : 1
						}, 'easeIn');
					}, function() {
						jQuery('.tp-leftarrow').stop().animate({
							"opacity" : 0
						}, 'easeIn');
						jQuery('.tp-rightarrow').stop().animate({
							"opacity" : 0
						}, 'easeIn');
					}

					);

				});

/*----------------------------------------------------*/
/*
 * Revolution Slider Triggering
 * /*----------------------------------------------------
 */

jQuery(document).ready(function() {
	if (jQuery.fn.cssOriginal != undefined)
		jQuery.fn.css = jQuery.fn.cssOriginal;
	jQuery('.fullwidthbanner').revolution({
		delay : 9000,
		startwidth : 1170,
		startheight : 470,

		onHoverStop : "on",
		// Stop Banner Timet at Hover on Slide on/off
		thumbWidth : 100,
		// Thumb With and Height and Amount (only if navigation Tyope set to
		// thumb !)
		thumbHeight : 50,
		thumbAmount : 3,

		hideThumbs : 0,
		navigationType : "none",
		// bullet, thumb, none
		navigationArrows : "solo",
		// nexttobullets, solo (old name verticalcentered), none
		navigationStyle : "square",
		// round,square,navbar,round-old,square-old,navbar-old, or any from the
		// list in the docu (choose between 50+ different item), custom

		navigationHAlign : "center",
		// Vertical Align top,center,bottom
		navigationVAlign : "top",
		// Horizontal Align left,center,right
		navigationHOffset : 0,
		navigationVOffset : 20,

		soloArrowLeftHalign : "left",
		soloArrowLeftValign : "center",
		soloArrowLeftHOffset : 0,
		soloArrowLeftVOffset : 0,

		soloArrowRightHalign : "right",
		soloArrowRightValign : "center",
		soloArrowRightHOffset : 0,
		soloArrowRightVOffset : 0,

		touchenabled : "on",
		// Enable Swipe Function : on/off

		stopAtSlide : -1,
		// Stop Timer if Slide "x" has been Reached. If stopAfterLoops set to 0,
		// then it stops already in the first Loop at slide X which defined. -1
		// means do not stop at any slide. stopAfterLoops has no sinn in this
		// case.
		stopAfterLoops : -1,
		// Stop Timer if All slides has been played "x" times. IT will stop at
		// THe slide which is defined via stopAtSlide:x, if set to -1 slide
		// never stop automatic
		hideCaptionAtLimit : 0,
		// It Defines if a caption should be shown under a Screen Resolution (
		// Basod on The Width of Browser)
		hideAllCaptionAtLilmit : 0,
		// Hide all The Captions if Width of Browser is less then this value
		hideSliderAtLimit : 0,
		// Hide the whole slider, and stop also functions if Width of Browser is
		// less than this value

		fullWidth : "on",

		shadow : 0
	// 0 = no Shadow, 1,2,3 = 3 Different Art of Shadows - (No
	// Shadow in Fullwidth Version !)
	});

});

/*----------------------------------------------------*/
/*
 * Master Slider /*----------------------------------------------------
 */

if ($("#masterslider").length > 0) {
	var slider = new MasterSlider();
	slider.setup('masterslider', {
		width : 1200,
		height : 450,
		space : 0,
		view : 'flow',
		autoplay : true,
		loop : true,
		instantStartLayers : true,
		swipe : false,
		mouse : false
	});
	slider.control('arrows', {
		autohide : false
	});
	slider.control('timebar');
	slider.control('thumblist', {
		autohide : false,
		dir : 'v',
		type : 'tabs',
		space : 5,
		margin : 2,
		height : 250,
		hideUnder : 600
	});
}

/*----------------------------------------------------*/
/*
 * Carousel Section /*----------------------------------------------------
 */

jQuery('.portfolio-carousel').carousel({
	interval : 5000,
	wrap : false
});

jQuery('.client-carousel').carousel({
	interval : 10000
});

jQuery('.client-carousel-ads').carousel({
	interval : 3500
});

jQuery('.client-carousel-ads-2').carousel({
	interval : 3100
});

jQuery('.testimonials-carousel').carousel({
	interval : 5000,
	pause : "hover"
});

/*----------------------------------------------------*/
/*
 * Hover Overlay /*----------------------------------------------------
 */

jQuery(document).ready(
		function() {

			jQuery('.portfolio-item').hover(function() {
				jQuery(this).find('.portfolio-item-hover').animate({
					"opacity" : 0.8
				}, 100, 'easeInOutCubic');

			}, function() {
				jQuery(this).find('.portfolio-item-hover').animate({
					"opacity" : 0
				}, 100, 'easeInOutCubic');

			});

			jQuery('.portfolio-item').hover(function() {
				jQuery(this).find(".fullscreen").stop().animate({
					'top' : '60%',
					'opacity' : 1
				}, 250, 'easeOutBack');

			}, function() {
				jQuery(this).find(".fullscreen").stop().animate({
					'top' : '65%',
					'opacity' : 0
				}, 150, 'easeOutBack');

			});

			jQuery('.blog-showcase ul li').each(
					function() {
						jQuery(this).on(
								'hover',
								function() {
									jQuery(this).siblings('li').removeClass(
											'blog-first-el').end().addClass(
											'blog-first-el');
								});
					});

			jQuery('.blog-showcase-thumb').hover(function() {
				jQuery(this).find('.post-item-hover').animate({
					"opacity" : 0.8
				}, 100, 'easeInOutCubic');

			}, function() {
				jQuery(this).find('.post-item-hover').animate({
					"opacity" : 0
				}, 100, 'easeInOutCubic');

			});

			jQuery('.blog-showcase-thumb').hover(function() {
				jQuery(this).find(".fullscreen").stop().animate({
					'top' : '57%',
					'opacity' : 1
				}, 250, 'easeOutBack');

			}, function() {
				jQuery(this).find(".fullscreen").stop().animate({
					'top' : '65%',
					'opacity' : 0
				}, 150, 'easeOutBack');

			});

			/* Post Image overlay */

			jQuery('.post-image').hover(function() {
				jQuery(this).find('.img-hover').animate({
					"opacity" : 0.8
				}, 100, 'easeInOutCubic');

			}, function() {
				jQuery(this).find('.img-hover').animate({
					"opacity" : 0
				}, 100, 'easeInOutCubic');

			});

			jQuery('.post-image').hover(function() {
				jQuery(this).find(".fullscreen").stop().animate({
					'top' : '55%',
					'opacity' : 1
				}, 250, 'easeOutBack');

			}, function() {
				jQuery(this).find(".fullscreen").stop().animate({
					'top' : '65%',
					'opacity' : 0
				}, 150, 'easeOutBack');

			});

			/* Mobile device topnav opener */

			jQuery(".down-button").click(
					function() {
						jQuery(".down-button .icon-current").toggleClass(
								"fa fa-angle-up fa fa-angle-down");
					});

			/*----------------------------------------------------*/
			/*
			 * Parallax section
			 * /*----------------------------------------------------
			 */
			// jQuery('.product-lead').parallax("50%", 0.1);
			jQuery('.our-clients').parallax("50%", 0.1);
			// jQuery('.service-reasons').parallax("50%", 0.1);

			jQuery("a[data-rel^='prettyPhoto']").prettyPhoto({
				overlay_gallery : false
			});

			/*----------------------------------------------------*/
			/*
			 * Tootltip Initialize
			 * /*----------------------------------------------------
			 */

			jQuery("[data-toggle='tooltip']").tooltip();

		});

/*----------------------------------------------------*/
/*
 * Sticky Menu /*----------------------------------------------------
 */

jQuery(document).ready(function() {
	jQuery(".main-header").sticky({
		topSpacing : 0
	});
});

/*----------------------------------------------------*/
/*
 * Scroll To Top Section /*----------------------------------------------------
 */
jQuery(document).ready(function() {

	jQuery(window).scroll(function() {
		if (jQuery(this).scrollTop() > 100) {
			jQuery('.scrollup').fadeIn();
		} else {
			jQuery('.scrollup').fadeOut();
		}
	});

	jQuery('.scrollup').click(function() {
		jQuery("html, body").animate({
			scrollTop : 0
		}, 600);
		return false;
	});

});
