/*<![CDATA[*/
$(function () {
  // ログインボタン押下でログインモーダル表示
  $("#login-btn").on("click", function () {
    bootbox.confirm({
      title: "Please enter your login info",
      message: "<form id='login-info'>\
                <p class='m-2'>Email</p>\
                <input id='email' type='email' name='email' required placeholder='example@abc.com'/><br/>\
                <p class='m-2'>Password</p>\
                <input id='password' type='password' name='password' required placeholder='●●●●●●'/>\
                </form>",
      backdrop: true,
      centerVertical: true,
      buttons: {
        confirm: {
          label: '<i class="fa fa-check"></i> Login',
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
            email: $("#email", ".bootbox").val(),
            password: $("#password", ".bootbox").val(),
          };
          $.ajax({
            type: "POST",
            url: "/portfolio/auth/login",
            data: JSON.stringify(jsonString),
            contentType: "application/json",
            dataType: "json",
          })
            .done(function (result) {
              let user = result;
              if (jQuery.isEmptyObject(user)) {
                bootbox.alert(
                  "You have not yet registered. Please sign up first !"
                );
              } else {
                login(user);
                loginCheck();
                console.log("ログインしました");
              }
            })
            .fail(function (result) {
              console.log("Error: ajax通信に失敗しました");
            })
            .always(function (result) {
              console.log("ajax通信しました");
            });
        }
      },
    });
  }); // ログインボタン押下

  // ログアウトボタン押下
  $("#logout-link").on("click", function () {
    $.ajax({
      type: "POST",
      url: "/portfolio/auth/logout",
      datatype: "json",
      scriptCharset: "utf-8",
    })
      .done(function (result) {
        loginCheck();
        bootbox.alert({
          message: "You loged out",
          backdrop: true,
          centerVertical: true,
          callback: function () {
            location.replace("/portfolio");
          },
        });
      })
      .fail(function (result) {
        console.log("Error: ajax通信に失敗しました");
      })
      .always(function (result) {
        console.log("ajax通信しました");
      });
  });

  // ログイン時のwelcome-msg切り替え処理
  function login(user) {
    let userName = user["userName"];
    $("#welcome-msg").text(`Welcome ${userName} !`);
  }

  // ログイン操作後のnavbar切り替え処理
  function loginCheck() {
    let loginChecker = $("#login-link").prop("class");
    // 非同期通信のため、以下のclassの切り替えを行わないと画面が更新されない
    if (jQuery.isEmptyObject(loginChecker)) {
      // ログインしている時
      $("#login-link").addClass("hidden");
      $("#signup-link").addClass("hidden");
      $("#logout-link").removeClass("hidden");
      $("#mypage-link").removeClass("hidden");
      $("#bookmark-link").removeClass("hidden");
      $("#reservation-link").removeClass("hidden");
    } else {
      // ログインしていない時
      $("#welcome-msg").text(`Welcome our Guest!`);
      $("#login-link").removeClass("hidden");
      $("#signup-link").removeClass("hidden");
      $("#logout-link").addClass("hidden");
      $("#mypage-link").addClass("hidden");
      $("#bookmark-link").addClass("hidden");
      $("#reservation-link").addClass("hidden");
    }
  }
});

/*]]>*/
