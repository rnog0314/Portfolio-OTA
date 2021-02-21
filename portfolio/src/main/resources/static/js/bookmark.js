/*<![CDATA[*/
$(document).ready(function () {
  /*削除ボタン押下時*/
  $(".delete-btn").on("click", function (e) {
    let productId = $(this).parent().find("input").val();
    $.ajax({
      type: "POST",
      url: "/portfolio/bookmark/delete",
      data: productId,
      contentType: "application/json",
      dataType: "json",
    })
      .done(function () {
        $(e.target).parent().parent().parent().parent().remove(); // 行全体を削除する
        location.reload();
      })
      .fail(function () {
        console.log("ajax通信に失敗しました");
      })
      .always(function () {
        console.log("ajax通信しました");
      });
  });
});

/*]]>*/
