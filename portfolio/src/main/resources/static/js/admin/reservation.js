/* fullCalendarの初期設定 */
document.addEventListener("DOMContentLoaded", function () {
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
      url: "/portfolio/admin/reservation/fetchAll", // DBのreservationsテーブルから全てのレコードを取得する
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
  });

  calendar.render();
});
