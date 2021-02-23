$(document).ready(function () {
  /* 修正ボタン押下時に編集モードに切り替える */
  $('#modify-btn').on('click', function () {
    $('.normal').addClass('hidden');
    $('.modify').removeClass('hidden');
    $('#modify-btn').addClass('hidden');
    $('#cancel-btn').removeClass('hidden');
    $('#submit-btn').removeClass('hidden');
  });

  /*キャンセルボタン押下時に通常モードに切り替える*/
  $('#cancel-btn').on('click', function () {
    $('.normal').removeClass('hidden');
    $('.modify').addClass('hidden');
    $('#modify-btn').removeClass('hidden');
    $('#cancel-btn').addClass('hidden');
    $('#submit-btn').addClass('hidden');
  });

  /*修正完了ダイアログの初期化*/
  var dialog = bootbox.alert({
    message: '<p class="text-center mb-0">Your profile has been changed</p>',
    backdrop: true,
    centerVertical: true,
    show: false,
    callback: function () {
      location.replace('/portfolio/admin/home');
    }
  });

  /*Admin情報更新後のアラート表示*/
  if (completeMsg) { 
    dialog.modal('show');
  }
})
