$(document).ready(function () {
  // idをつけてそれ用の初期化を記述すれば複数のowe-carouselができる(https://support.fruitfulcode.com/hc/en-us/articles/115004837994-Multiple-carousels-at-one-page)
  $("#testimonial-carousel").owlCarousel({
    loop: true,
    margin: 10,
    responsive: {
      0: {
        items: 1,
      },
      600: {
        items: 3,
      },
    },
  });

  $(".owl-carousel").owlCarousel({
    loop: true,
    margin: 10,
    responsive: {
      0: {
        items: 1,
      },
      600: {
        items: 3,
      },
      1000: {
        items: 5,
      },
    },
  });
});
