# Contact Form App (React + Spring Boot)

このシステムは、React + Spring Bootを使用した簡易記事投稿アプリです。  
フロントエンドはReact + TypeScript、バックエンドはSpring BootでRESTful APIを構築し、CRUD処理を実装しています。

---

## 🌐 デモサイト
※現在準備中

---

## 🛠️ 使用技術
### バックエンド
- Spring Boot v3.x
- Spring Data JPA
- MySQL
- 3層アーキテクチャ（Controller / Service / Repository）
- CORS設定済み（Reactとの連携）

### フロントエンド
- React v18
- TypeScript
- Axios

---

## ✅ 主な機能
- データの一覧取得
- 新規登録
- 更新
- 削除

---

## 💡 工夫したポイント

- バックエンド：3層アーキテクチャによる責務分離
- バックエンド：Entity間の独立性を維持
- 3層構成でEntity間を@ManyToOneなどで結合すると責務がプレるため

---

<!-- スクリーンショットセクションは完成後に記載予定
## 📷 スクリーンショット
-->

<!-- システム構成図は完成後に記載予定
## 🖼️ システム構成図 (Mermaid)
-->

## 🚀 セットアップ手順（ローカル）
※詳細な手順は準備中です。
1. リポジトリをクローン
2. バックエンド(Spring Boot)プロジェクトをビルド・起動
