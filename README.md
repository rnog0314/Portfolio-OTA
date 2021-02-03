# Title:Adview
オンライン旅行予約サイトです。
閲覧、選択、お気に入り追加、決済、確認、削除をスムーズに行うことができます。

![スクリーンショット 2021-02-03 20 11 13](https://user-images.githubusercontent.com/63575996/106739160-280e9200-665c-11eb-9634-bed6d41a2524.png)

![ダウンロード](https://user-images.githubusercontent.com/63575996/106738648-88e99a80-665b-11eb-84e3-a0be3c522bc6.gif)

# このテーマを選んだ理由
前職で外国人向けのツアーガイドを行っており、
業務の中での使っていた予約機能に改善できる点があると感じていたため。

# 使用技術
- Java 11
- Spring Boot 2.4.2
- PostgreSQL 12.5
- HTML 5
- CSS 3
  - Bootstrap 4
- SCSS
- JQuery
  - OwlCarousel.js
  - FullCalendar.js
  - BootBox.js
- Docker
- AWS (ECS/Fargate/RDS)
- Stripe API
- Google Maps API

# ER図
![relation](https://user-images.githubusercontent.com/63575996/106725206-7320a900-664c-11eb-87ea-b463cd441fe5.png)

# インフラ構成図
<img width="1269" alt="スクリーンショット 2021-02-01 20 26 09" src="https://user-images.githubusercontent.com/63575996/106671528-01b40c80-65f2-11eb-80cf-eafe2cabfc6f.png">

# 使い方
- ユーザ画面
1. 「http://54.199.29.47:8080/portfolio」を開く
2. 「What's popular」項目から任意に画像を選んでクリックし「商品詳細」画面に遷移。確認後、ブラウザの戻るボタンをクリックし、トップページへ戻る
3. 「Where TO GO」項目から任意に画像を選んでクリックし、「デスティネーション詳細」画面に遷移。確認後、ブラウザの戻るボタンをクリックし、トップページへ戻る
4. 「What TO Do」項目から任意に画像を選んでクリックし、「カテゴリ詳細」画面に遷移する。
5. ヘッダーのロゴをクリックして、トップページに遷移する。ヘッダーの検索窓に「Tokyo Hokkaido Okinawa」と入力し、「🔍」アイコンをクリックする。
6. ヘッダーの「Sign up」をクリックし、「新規ユーザー登録」画面を開く。UserNameに任意で入力し、[Check availability]をクリックする。Full Name、E-mail、Password、Gendarに任意で入力して[Confirm]をクリックする。モーダルの[Submit]をクリック。登録完了を通知するモーダルが開き、その[OK]をクリックし、「マイページ」画面に遷移する。
7. [Choose your photo]をクリックし、任意の画像を選択し、画像が切り替わるのを確認する。
8. [Modify]クリックし、任意で登録内容を編集し、[Submit]をクリックする。
9. ヘッダーの[Log out]をクリックする。
10. ヘッダーの「Log in」リンクをクリックする。EmailとPasswordに5.で登録したユーザのものを入力し、[Login]をクリックする。
11. トップページの「What's popular」から任意の画像をクリックして、「商品詳細」画面に遷移する。[Add to Bookmark]をクリックする。ヘッダーの「Bookmark」をクリックして「ブックマーク」画面に遷移する
12. 「🗑」アイコンをクリックして、ブックマークを削除する
13. トップページの「What's popular」から任意の画像をクリックして、「商品詳細」画面に遷移する。カレンダー上で任意の日付を選択の上、「+」ボタンをクリックする。[Book Now]をクリックする。表示されるモーダルの[OK]をクリックし、「決済」画面に遷移する。[Pay with Card]をクリックする。
14. メールドレスに任意のメールアドレス、カード番号に「4242 4242 4242 4242」、カード利用期限に「02/24」、CVCに「234」と入力し、[支払う]をクリックし、決済が完了するのを待つ。
15. ヘッダーの「Reservation」をクリックし、「予約確認」画面に遷移。

- 管理者画面
1. 「http://54.199.29.47:8080/portfolio/admin」を開く
2. メニューの「Account」をクリックして、「管理者アカウント」画面に遷移する。
3. サイドメニューの「Users」をクリックして「ユーザー管理」画面に遷移する。
4. サイドメニューの「Products」をクリックして「商品管理」画面に遷移する。最終ページの最後の商品を選択して[Delete]ボタンを押下する
5. サイドメニューの「Reservations」をクリックして「予約管理(リスト)」画面に遷移する。「🗓」アイコンをクリックして「予約管理(カレンダー)」画面に切り替える
6. サイドメニューの「Testimonials」をクリックして「お客様の声」画面に遷移する。
7. サイドメニューの「Notices」をクリックして「お知らせ管理」画面に遷移する。[Create]をクリックして、任意でお知らせを作成し、[Submit]をクリックする。

# 機能一覧
- ユーザ画面
  - 商品
    - 一覧閲覧
    - 検索
    - 詳細ページ表示
      - GoogleMap表示
      - Youtube表示
    - ページング
  - ユーザ
    - 新規ユーザ登録
    - ログイン(Ajax)
    - マイページ
      - アカウント情報表示・修正(Ajax)
      - プロフィール画像編集(Ajax)
  - 予約
    - カレンダー表示
    - カレンダー選択
    - お気に入り追加・確認・削除
    - クレジットカード決済(Stripe API)
    - 予約確認メール送信
    - 予約確認・削除
  - お知らせ
- 管理者画面
  - ログイン
  - メニュー表示
  - アカウント
    - 表示
    - 修正(Ajax)
  - ユーザ情報
    - ページング
    - 一覧表示
  - 商品
    - 一覧表示
    - 選択・全選択削除
  - 予約
    - カレンダー表示
    - リスト表示
  - お知らせ
    - 一覧表示
    - 新規作成

# テスト
- 単体試験
  - JUnit5
  - Mockito
  - h2
- 結合試験(URL配置予定)

# 難しかった点
- FullCalendar.jsを使用してカレンダーとDBとの連動
- N+1問題を解決するためのリファクタリング
- モックを使用したJUnitテスト

# こだわった点
- 単にバグがなくアプリケーションが動くだけでなく、N+1問題の解消や単体試験のメソッド網羅によるリファクタリングを行いパフォーマンを改善した点
- できるだけAjaxを使用し、使いやすさを向上させた点
