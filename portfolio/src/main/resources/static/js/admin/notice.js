$(document).ready(function () {
  $("#create-btn").on("click", function () {
    bootbox.confirm({
      title: "Please create a new notice",
      message: "<form id='notice-register'>\
                <p class='m-2'>Title</p>\
                <input id='title' type='title' name='title' required placeholder='Notice Title'/><br/>\
                <p class='m-2'>Text</p>\
                <textarea id='text' type='text' name='text' class='w-100' required></textarea>\
                </form>",
      backdrop: true,
      centerVertical: true,
      buttons: {
        confirm: {
          label: '<i class="fa fa-check"></i> Submit',
          className: "btn-info",
        },
        cancel: {
          label: '<i class="fa fa-times"></i> Cancel',
          className: "btn-secondary",
        },
      },
      callback: function (result) {
        if (result) {
          let jsonString = {
            title: $("#title", ".bootbox").val(),
            text: $("#text", ".bootbox").val(),
          };
          $.ajax({
            type: "POST",
            url: "/portfolio/admin/notice/create",
            data: JSON.stringify(jsonString),
            contentType: "application/json",
            dataType: "json",
          })
            .done(function (bool) {
              if (bool) {
                bootbox.alert({
                  message: "A new notice has been created",
                  backdrop: true,
                  centerVertical: true,
                  callback: function () {
                    location.reload();
                  },
                });
              } else {
                bootbox.alert({
                  message: "Something went wrong. Please try again!",
                  backdrop: true,
                  centerVertical: true,
                });
              }
            })
            .fail(function () {
              bootbox.alert({
                message: "Something went wrong. Please try again!",
                backdrop: true,
                centerVertical: true,
              });
              console.log("Error: ajax通信に失敗しました");
            })
            .always(function (result) {
              console.log("ajax通信しました");
            });
        }
      },
    });
  });
});
