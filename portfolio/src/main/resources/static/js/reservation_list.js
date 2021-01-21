$(document).ready(function () {
  /* 削除ボタン押下時*/
  $(".cancel-btn").on("click", function (e) {
    bootbox.confirm({
      message: "Are you sure to cancel this reservation?",
      backdrop: true,
      centerVertical: true,
      buttons: {
        confirm: {
          label: '<i class="fa fa-check"></i> Yes',
          className: "btn-info",
        },
        cancel: {
          label: '<i class="fa fa-times"></i> Cancel',
          className: "btn-secondary",
        },
      },
      callback: function (result) {
        if (result) {
          let id = $(e.target).parent().find(".reservationId").val();
          $.ajax({
            type: "POST",
            url: "/portfolio/reservation/cancel",
            data: id,
            contentType: "application/json",
            dataType: "json",
          })
            .done(function (bool) {
              if (bool) {
                bootbox.alert({
                  message: "This reservation was canceled.",
                  backdrop: true,
                  centerVertical: true,
                  callback: function () {
                    location.replace("/portfolio/reservation");
                  },
                });
              } else {
                bootbox.alert(
                  "Something wrong, could not cancel. Please try agin"
                );
              }
            })
            .fail(function () {
              console.log("ajax通信に失敗しました");
            })
            .always(function () {
              console.log("ajax通信しました");
            });
        } else {
          console.log("✖️ボタンが押されました");
        }
      },
    });
  });

  $(".owl-carousel").owlCarousel({
    loop: true,
    margin: 10,
    responsive: {
      0: {
        items: 1,
      },
    },
  });
});
