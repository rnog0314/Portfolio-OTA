$(document).ready(function () {
  var MyLatLng = new google.maps.LatLng(latitude, longitude);
  var Options = {
    zoom: 15,      //地図の縮尺値
    center: MyLatLng,    //地図の中心座標
    mapTypeId: 'roadmap'   //地図の種類
  };
  var map = new google.maps.Map(document.getElementById('map'), Options);

  // idをつけてそれ用の初期化を記述すれば複数のowe - carouselができる(https://support.fruitfulcode.com/hc/en-us/articles/115004837994-Multiple-carousels-at-one-page)
    $('#tours-carousel').owlCarousel({
      loop: true,
      margin: 10,
      responsive: {
        0: {
          items: 1
        },
        600: {
          items: 3
        }
      }
    });

  $('.owl-carousel').owlCarousel({
    loop: true,
    margin: 10,
    responsive: {
      0: {
        items: 1
      },
      600: {
        items: 3
      },
      1000: {
        items: 5
      }
    }
  });
});
