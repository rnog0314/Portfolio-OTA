$(function () {
  /*ユーザ名重複確認ボタン押下時 */
  $("#checkUserName").on("click", function () {
    let userName = $("#newUserName").val();
    $.ajax({
      type: "POST",
      url: "/portfolio/user/check",
      data: userName,
      dataType: "json",
      contentType: "application/json",
    })
      .done(function (bool) {
        if (bool) {
          bootbox.alert(
            "This user name is already used. Please try another one."
          );
        } else {
          bootbox.alert("You can use this user name");
          $("#confirm-btn").prop("disabled", false);
        }
      })
      .fail(function (bool) {
        console.log("ajax通信に失敗しました");
      })
      .always(function (bool) {
        console.log("ajax通信をしました");
      });
  });

  // 「Confirm」ボタン押下時にモーダルを作成
  $("#confirm-btn").on("click", function () {
    var box = bootbox.confirm({
      title: "Please make sure your input is correct.",
      message:
                "<table class='table table-hover table-responsve-md table-bordered text-left'>\
                <tr><th scope='row'>User Name</th><td><span id='userNameConfirm'></span></td></tr>\
                <tr><th scope='row'>Full Name</th><td><span id='familyNameConfirm'></span><span id='firstNameConfirm'></span></td></tr>\
                <tr><th scope='row'>E-mail</th><td><span id='emailConfirm'></span></td></tr>\
                <tr><th scope='row'>Password</th><td><span id='passwordConfirm'></span></td></tr>\
                <tr><th scope='row'>Gender</th><td><span id='gender'></span></td></tr>\
                </table>",
      backdrop: true,
      centerVertical: true,
      show: false,
      buttons: {
        confirm: {
          label: '<i class="fa fa-check"></i> Submit',
          className: "btn-primary",
        },
        cancel: {
          label: '<i class="fa fa-times"></i> Modify',
          className: "btn-secondary",
        },
      },
      callback: function (result) {
        if (result) {
          let jsonString = {
            userName: $("#newUserName").val(),
            familyName: $("#newFamilyName").val(),
            firstName: $("#newFirstName").val(),
            email: $("#newEmail").val(),
            password: $("#newPassword").val(),
            gender: $("input[name=gender]:checked").val(),
          };
          $.ajax({
            type: "POST",
            url: "/portfolio/user/register",
            data: JSON.stringify(jsonString),
            contentType: "application/json",
            dataType: "json",
          })
            .done(function (result) {
              if (result) {
                bootbox.alert(
                  "You have just registerd! You will move to your personal page.",
                  function () {
                    location.href = "/portfolio/mypage";
                  }
                );
              } else {
                bootbox.alert("Something wrong. Please try again.");
              }
            })
            .fail(function () {
              console.log("Error: ajax通信に失敗しました");
            })
            .always(function () {
              console.log("ajax通信しました");
            });
        }
      }, // callback
    }); // bootbox.confirm
    box.on("shown.bs.modal", function () {
      // モーダルが開かれた直後に以下を実行する
      let gender = "";
      if ($("input[name=gender]:checked").val() == "M") {
        gender = "Male";
      } else {
        gender = "Female";
      }
      $("#userNameConfirm").text($("#newUserName").val());
      $("#familyNameConfirm").text($("#newFamilyName").val());
      $("#firstNameConfirm").text($("#newFirstName").val());
      $("#emailConfirm").text($("#newEmail").val());
      $("#passwordConfirm").text($("#newPassword").val());
      $("#gender").text(gender);
    });
    box.modal("show");
  });
});
