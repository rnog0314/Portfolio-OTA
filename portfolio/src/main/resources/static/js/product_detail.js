/* fullcalendarの初期設定*/
document.addEventListener("DOMContentLoaded", function () {
  var calendarEl = document.getElementById("calendar");
  var calendar = new FullCalendar.Calendar(calendarEl, {
    plugins: ["dayGrid", "bootstrap", "interaction"],
    themeSystem: "bootstrap",
    defaultView: "dayGridMonth",
    header: {
      left: "today",
      center: "title",
      right: "prev next",
    },
    selectable: true,
    unselectAuto: false,
    selectAllow: function (e) {
      var today = new Date().toISOString().slice(0, 10); // yyy-MM-ddフォーマットに変換
      if (e.start <= today) {
        return false;
      } else {
        // ドラッグをDisabledにする処理をして、日付のみを選択できるようにする
        if (e.end.getTime() / 1000 - e.start.getTime() / 1000 <= 86400) {
          return true;
        }
      }
    },
    dateClick: function (info) {
      // 日付選択をしたその値をinputのvalueに書き換え
      let selectedDate = info.dateStr;
      let date = new Date();
      let today = new Date(date.getTime() - (date.getTimezoneOffset() * 60000)).toISOString(); // new Date().toISOString().slice(0,10)だとタイムゾーンが日本時間ではないため、時間帯によってはずれてしまう
      if (selectedDate > today) {
        //選択された日が翌日以降であれば選択した日を有効とする
        $("#selectedDate").text(selectedDate);
        $("#selectedDate").val(selectedDate);
      }
    },
  });
  calendar.render();
});

$(function () {
  // 予約確認モーダルの設定
  var box = bootbox.confirm({
    title: "Please make sure your order is correct.",
    message:  "<form id='reservConf' action='/portfolio/reservation/reserve' method='POST' name='reservationForm'>\
              <table class='table table-hover table-responsve-md table-bordered text-left'>\
              <tr><th scope='row'>Date</th><td><span><input name='date' id='confirmDate' readonly></span></td></tr>\
              <tr><th scope='row'>Number of Participant</th><td><span><input name='count' id='confirmCnt' readonly></span></td></tr>\
              <tr><th scope='row'>Product Name</th><td><span><input name='' id='confirmProductName' readonly></span></td></tr>\
              <tr class='hidden'><th scope='row'></th><td><span><input name='productId' id='confirmProductId' readonly></span></td></tr>\
              </table>\
              </form>",
    backdrop: true,
    centerVertical: true,
    show: false,
    buttons: {
      confirm: {
        label: '<i class="fa fa-check"></i> OK',
        className: "btn-info",
      },
      cancel: {
        label: '<i class="fa fa-times"></i> Modify',
        className: "btn-secondary",
      },
    },
    callback: function (result) {
      if (result) {
        $("#reservConf").submit();
      }
    },
  }); // bootbox.confirm
  box.on("shown.bs.modal", function () {
    // モーダルが開かれた直後に以下を実行する
    $("#confirmDate").text($("#selectedDate").val());
    $("#confirmDate").val($("#selectedDate").val());
    $("#confirmCnt").text($("#participantsNumber").val());
    $("#confirmCnt").val($("#participantsNumber").val());
    $("#confirmProductName").text($("#selectedProductName").val());
    $("#confirmProductName").val($("#selectedProductName").val());
    $("#confirmProductId").val($("#selectedProductId").val());
  });

  /* ブックマークボタン押下時 */
  $("#bookmark-btn").on("click", function () {
    console.log("ログイン状態" + bool);
    if (!bool) {
      // ログインしていなかったらログインモーダルを開く
      bootbox.confirm({
        title: "Please enter your login info",
        message:
          "<form id='login-info'>\
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
                  ns.login(user); // footer.jsに記述したプラグインを呼び出す
                  ns.loginCheck(); // footer.jsに記述したプラグインを呼び出す
                  addBookmark(); // ブックマークに追加
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
    } else { // ログイン状態であればそのままブックマークに追加
      addBookmark()
    }
  });

  // ブックマークに追加する関数
  function addBookmark() {
    let productId = $("#selectedProductId").val();
    $.ajax({
      type: "POST",
      url: "/portfolio/bookmark/add",
      data: productId,
      contentType: "application/json",
      dataType: "json",
    })
      .done(function () {
        bootbox.alert("This product has been added in you BOOKMARK");
      })
      .fail(function () {
        bootbox.alert("Something went wrong. Please try again");
      })
      .always(function () {
        console.log("ajax通信しました");
      });
  }

  /* カレンダーに値段を表示 */
  showPrice();

  /* 予約ボタン押下時*/
  $("#book-btn").on("click", function () {
    // 予約人数が0人の場合アラートを出す
    if ($("#participantsNumber").val() == 0) {
      bootbox.alert({
        message: "You need to input more than 1 person to book",
        backdrop: true,
        centerVertical: true,
      });
      return false;
    }

    if (!bool) {
      // ログインしていなかったらログインモーダルを開く
      bootbox.confirm({
        title: "Please enter your login info",
        message:"<form id='login-info'>\
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
                  ns.login(user);
                  ns.loginCheck();
                  console.log("ログインしました");
                  box.modal("show");
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
    } else {
      // ログインしている場合予約確認モーダルを開く
      box.modal("show");
    }
  });

  /* 選択日の初期表示を現在日の翌日に */
  let tomorrow = new Date();
  tomorrow.setDate(tomorrow.getDate() +1);
  console.log(tomorrow);
  $("#selectedDate").val(tomorrow.toLocaleDateString("fr-CA"));

  /* カレンダーのボタンが押下されても値段表示が消えないように */
  $(".fc-toolbar button").on("click", function () {
    showPrice();
  });

  /* マイナスボタン押下時 */
  $("#minus-counter").on("click", function () {
    let count = $("#participantsNumber").val();
    count--;
    if (count < 1) {
      $("#minus-counter").prop("disabled", true);
    }
    if (count < 9) {
      $("#plus-counter").prop("disabled", false);
    }
    $("#participantsNumber").val(count);
    culc();
  });

  /* プラスボタン押下時 */
  $("#plus-counter").on("click", function () {
    let count = $("#participantsNumber").val();
    count++;
    if (count > 0) {
      $("#minus-counter").prop("disabled", false);
    }
    if (count > 7) {
      $("#plus-counter").prop("disabled", true);
    }
    $("#participantsNumber").val(count);
    culc();
  });

  /* トータルの計算 */
  function culc() {
    let participants = $("#participantsNumber").val();
    let totalPrice = price * participants;
    $("#total-price").text(totalPrice);
  }

  /* 金額表示 */
  function showPrice() {
    price.toString();
    prix = "$" + price;
    $("tbody")
      .find(".fc-day.fc-future")
      .append('<span class="prix price"></span>');
    $(".prix").text(prix);
  }
});
