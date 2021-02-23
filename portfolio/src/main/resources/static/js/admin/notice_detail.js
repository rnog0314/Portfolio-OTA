$(document).ready(function () {
  /* 修正ボタン押下時に編集モードに切り替える */
  $("#modify-btn").on("click", function () {
    $(".normal").addClass("hidden");
    $(".modify").removeClass("hidden");
    $("#modify-btn").addClass("hidden");
    $("#cancel-btn").removeClass("hidden");
    $("#submit-btn").removeClass("hidden");
  });

  /*キャンセルボタン押下時に通常モードに切り替える*/
  $("#cancel-btn").on("click", function () {
    $(".normal").removeClass("hidden");
    $(".modify").addClass("hidden");
    $("#modify-btn").removeClass("hidden");
    $("#cancel-btn").addClass("hidden");
    $("#submit-btn").addClass("hidden");
  });

  /* 送信ボタン押下時 */
  $("#submit-btn").on("click", function () {
    let jsonString = {
      id: $("#ID").val(),
      title: $("#newTitle").val(),
      text: $("#newText").val(),
    };
    $.ajax({
      url: "/portfolio/admin/notice/modify",
      type: "POST",
      data: JSON.stringify(jsonString),
      contentType: "application/json",
      dataType: "json",
    })
      .done(function () {
        bootbox.alert({
          message: "This notice has been modified",
          backdrop: true,
          centerVertical: true,
          callback: function () {
            location.reload();
          },
        });
      })
      .fail(function () {
        console.log("Error: ajax通信に失敗しました");
      })
      .always(function () {
        console.log("ajax通信しました");
      });
  });
});
