/*<![CDATA[*/
$(document).ready(function () {
  /* キャンセルボタン押下時にブックマークを削除する */
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
        $(e.target).parent().parent().parent().parent().remove();
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
