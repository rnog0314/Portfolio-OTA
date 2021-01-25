$(document).ready(function () {
  $("#login-btn").on("click", function () {
    let jsonString = {
      adminName: $("#adminName").val(),
      password: $("#password").val(),
    };
    $.ajax({
      type: "POST",
      url: "/portfolio/admin/login",
      data: JSON.stringify(jsonString),
      contentType: "application/json",
      dataType: "json",
    })
      .done(function (bool) {
        if (bool) {
          location.replace("/portfolio/admin/home");
        } else {
          bootbox.alert({
            message:
              "Your login info is not correct. Please make sure your input.",
            backdrop: true,
            centerVertical: true,
          });
        }
      })
      .fail(function () {
        console.log("Error: ajax通信に失敗しました");
      })
      .always(function () {
        console.log("ajax通信しました");
      });
  });
});