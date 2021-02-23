$(document).ready(function () {
  /*チェックボックスに変化があったときに削除ボタンの活性/非活性を操作する*/
  $(document).on("change", ".checkbox", function () {
    // javascriptを使ってhtml要素を変更した後のものには.change()やonChangeは使えないため、このようにdocumentを先頭につける必要がある
    changeDisabled();
  });

  /* 全選択チェックボックスのつけ外し */
  $("#checkbox-all").on("click", function () {
    $(".checkbox").prop("checked", this.checked); // this.checkedで全選択チェックボックスの状態と各チェックボックスの状態を同じにする
    changeDisabled();
  });
  $(".checkbox").on("click", function () {
    if ($("#boxes:checked").length == $(".checkbox").length) {
      $("#checkbox-all").prop("checked", true);
    } else {
      $("#checkbox-all").prop("checked", false);
    }
  });

  /*削除ボタン押下時*/
  $("#delete-btn").on("click", function (e) {
    e.preventDefault();
    let checkedList = $(".checkbox:checked"); // チェックボックスにチェックの入ったものを取得
    let checkedIdList = [];
    for (checked of checkedList) {
      checkedIdList.push($(checked).val()); // ☑️のついたレコードのvalueとして設定されているidを空の配列に入れる
    }
    deleteProduct(checkedList, checkedIdList);
  });

  /*削除処理*/
  function deleteProduct(checkedList, checkedIdList) {
    $.ajax({
      type: "POST",
      url: "/portfolio/admin/product/delete",
      data: JSON.stringify(checkedIdList),
      dataType: "json",
      contentType: "application/json",
    })
      .done(function (data) {
        for (checked of checkedList) {
          $(checked).parent().parent().remove(); // ☑️のついたレコード全体を削除
        }
        let checkList = $(".checkList"); // ☑️のついたレコードを削除した後、☑️が全て無くなればそのままカートページを更新
        if (checkList.length == 0) {
          location.replace("/portfolio/admin/product");
        }
        changeDisabled(); // 削除ボタンを非活性化
      })
      .fail(function (data) {
        console.log("ajax通信に失敗しました");
      })
      .always(function name(params) {
        console.log("ajax通信しました");
      });
  }

  /*削除ボタンの活性/非活性操作*/
  function changeDisabled() {
    let checkList = $(".checkbox");
    let disabled = true;
    for (let check of checkList) {
      if ($(check).prop("checked")) {
        // もし☑️がついていればfalseを、ついていなければtrueを削除ボタンの属性につける
        disabled = false;
        break;
      }
    }
    // 削除ボタンの状態を変更する
    $("#delete-btn").prop("disabled", disabled);
  }
});
