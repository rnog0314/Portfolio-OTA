document.addEventListener("DOMContentLoaded", function () { // fullCalendarの初期設定
  var calendarEl = document.getElementById("calendar");

  var calendar = new FullCalendar.Calendar(calendarEl, {
    plugins: ["dayGrid", "bootstrap", "interaction"],
    editable: true,
    themeSystem: "bootstrap",
    defaultView: "dayGridMonth",
    header: {
      left: "today",
      center: "title",
      right: "prev next",
    },
    events: {
      url: "/portfolio/admin/reservation/fetchAll",
    },
    selectable: true,
    selectAllow: function (selectInfo) {
      let today = new Date();
      if (selectInfo.start < today) { // 現在日以前の日にちを選択不可にする
        return false;
      } else {
        return true;
      }
    },
  });

  calendar.render();
});
