/**
 * 파일 업로드 전용 서버.
 */

const express = require("express");
const multer = require("multer");
const path = require("path");

const app = express();

// 기타 정적 파일 제공을 위한 디렉토리 설정
app.use(express.static("public"));

// 이미지 정적 파일 제공을 위한 디렉토리 설정
app.use("/uploads", express.static("uploads"));

// Multer 설정
const storage = multer.diskStorage({
  destination: function (req, file, cb) {
    cb(null, "./uploads/");
  },
  filename: function (req, file, cb) {
    cb(
      null,
      file.fieldname + "-" + Date.now() + path.extname(file.originalname)
    );
  },
});

const upload = multer({
  storage: storage,
  limits: { files: 10, fileSize: 1024 * 1024 * 10 }, // 파일당 10MB 제한
});

// 루트 경로에서 HTML 파일 응답
app.get("/", (req, res) => {
  res.sendFile(__dirname + "/public/upload.html");
});

// 업로드 라우트
app.post("/uploads", upload.array("image", 10), (req, res, next) => {
  if (req.files) {
    const files = req.files.map((file) => file.path);
    res.json({
      success: true,
      message: "파일이 업로드되었습니다.",
      files: files,
    });
  } else {
    res
      .status(400)
      .json({ success: false, message: "파일이 업로드되지 않았습니다." });
  }
});

// 에러 핸들링
app.use((err, req, res, next) => {
  if (err) {
    return res.status(500).json({ error: err.message });
  }
  next();
});

// 서버 실행
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
