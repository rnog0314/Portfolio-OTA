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
    selectAllow: function (selectInfo) {
      let today = new Date();
      if (selectInfo.start < today) {
        return false;
      } else {
        return true;
      }
    },
    dateClick: function (info) {
      // 日付選択をしたその値をinputのvalueに書き換え
      let selectedDate = info.dateStr;
      $("#selectedDate").text(selectedDate);
      $("#selectedDate").val(selectedDate);
    },
  });

  calendar.render();
});

$(function () {
  /* ブックマークボタン押下時 */
  $("#bookmark-btn").on("click", function () {
    console.log("ログイン状態"+ bool);
    if (!bool) {
      bootbox.alert({
        message: "Please login to add to your BOOKMARK",
        backdrop: true,
        centerVertical: true,
      });
      return false;
    }
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
  });

  /* カレンダーに値段を表示 */
  showPrice();

  /* 予約ボタン押下時*/
  $("#book-btn").on("click", function () {
    if ($("#participantsNumber").val() == 0) {
      bootbox.alert({
        message: "You need to input more than 1 person to book",
        backdrop: true,
        centerVertical: true,
      });
      return false;
    }

    if (!bool) {
      bootbox.alert({
        message: "Please login to book",
        backdrop: true,
        centerVertical: true,
      });
      return false;
    }
    var box = bootbox.confirm({
      title: "Please make sure your order is correct.",
      message: "<form id='reservConf' action='/portfolio/reservation/reserve' method='POST' name='reservationForm'>\
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
    box.modal("show");
  });

  /* 選択日の初期表示を現在日に */
  let today = new Date();
  $("#selectedDate").val(today.toLocaleDateString());

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
    price = "$" + price;
    $("tbody")
      .find(".fc-day.fc-future")
      .append('<span class="prix price"></span>');
    $(".prix").text(price);
  }
});
