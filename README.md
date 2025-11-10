# ⚡ EV Co-ownership & Cost-sharing System  
### 🚗 Hệ thống quản lý **Đồng sở hữu & Chia sẻ chi phí xe điện**

---
## 🌐 Frontenddd

Frontend của dự án được triển khai bằng **React / Next.js + TypeScript** và kết nối với **API Gateway** của hệ thống.  

**Link Frontend (FE):** [https://github.com/ThanhHienIT2004/fe-co-ev](https://github.com/ThanhHienIT2004/fe-co-ev)

**Chức năng FE:**
- Giao diện người dùng thân thiện: Co-owner, Staff, Admin.  
- Đặt lịch, theo dõi chi phí, quản lý nhóm.  
- Thanh toán trực tuyến và thông báo qua email / push notification.  
- Dashboard minh bạch và báo cáo chi phí trực quan.  

Frontend giao tiếp trực tiếp với **Gateway Service** của Backend, đảm bảo **đúng vai trò & quyền truy cập**, đồng thời tổng hợp dữ liệu từ các microservice khác.

---

## 🧩 Giới thiệu

**EV Co-ownership & Cost-sharing System** là một nền tảng quản lý **đồng sở hữu xe điện**, cho phép nhiều người cùng sở hữu – cùng sử dụng – cùng chia sẻ chi phí một cách **minh bạch, công bằng và tự động hóa**.  

Hệ thống được xây dựng theo **kiến trúc microservice** với các dịch vụ độc lập, dễ mở rộng và có thể tích hợp thêm AI hoặc IoT trong tương lai.

---

## 👥 Vai trò trong hệ thống

### 👤 **Co-owner (Chủ xe)**
- Quản lý tài khoản, quyền sở hữu, hợp đồng đồng sở hữu.
- Đặt lịch sử dụng xe và theo dõi chi phí.
- Tham gia nhóm đồng sở hữu, biểu quyết và theo dõi quỹ chung.

### 🧰 **Staff (Nhân viên vận hành)**
- Quản lý xe, hỗ trợ kiểm tra kỹ thuật, ghi nhận bảo dưỡng, vệ sinh.
- Theo dõi tình trạng xe, xử lý yêu cầu của Co-owner.

### 🛠️ **Admin (Quản trị hệ thống)**
- Quản lý nhóm xe, người dùng, hợp đồng điện tử.
- Giám sát, xử lý tranh chấp và xuất báo cáo tài chính minh bạch.

---

## ⚙️ Chức năng chính

### 🧑‍💼 **Dành cho Co-owner**
- Đăng ký, xác thực CMND/CCCD và giấy phép lái xe.  
- Quản lý tỉ lệ sở hữu (VD: A 40%, B 30%, C 30%).  
- Xem lịch xe trống / đặt lịch sử dụng công bằng theo tỉ lệ sở hữu.  
- Chia sẻ chi phí: sạc điện, bảo dưỡng, bảo hiểm, đăng kiểm, vệ sinh.  
- Thanh toán trực tuyến và xem báo cáo chi phí định kỳ.  
- Quản lý nhóm, bỏ phiếu quyết định chung, theo dõi quỹ nhóm.  
- AI gợi ý lịch sử dụng công bằng và tối ưu.  

### 🧾 **Dành cho Staff & Admin**
- Quản lý nhóm xe đồng sở hữu.  
- Quản lý hợp đồng điện tử (e-contract).  
- Check-in / Check-out xe (quét QR, ký số).  
- Theo dõi dịch vụ xe (bảo dưỡng, vệ sinh, sạc điện, đăng kiểm).  
- Xử lý tranh chấp và giám sát minh bạch tài chính.  

---

## 🏗️ Kiến trúc hệ thống

### ⚙️ **Kiến trúc tổng thể: Microservice**

Hệ thống được chia thành nhiều service độc lập, giao tiếp qua REST API hoặc gRPC.  
Mỗi service có thể sử dụng **ngôn ngữ và cơ sở dữ liệu phù hợp với chức năng của nó.**

